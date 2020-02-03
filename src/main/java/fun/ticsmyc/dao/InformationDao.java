package fun.ticsmyc.dao;

import fun.ticsmyc.mapper.AreaStatMapper;
import fun.ticsmyc.mapper.StatisticsMapper;
import fun.ticsmyc.mapper.TimeLineMapper;
import fun.ticsmyc.pojo.AreaStat;
import fun.ticsmyc.pojo.Statistics;
import fun.ticsmyc.pojo.TimeLine;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @author Ticsmyc
 * @package fun.ticsmyc.dao
 * @date 2020-01-26 12:39
 */
public class InformationDao {

    private  SqlSession session;
    public static final Logger logger = Logger.getLogger(InformationDao.class);

    public InformationDao() {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis.xml");
        } catch (IOException e) {
            logger.error("mybatis.xml加载错误");
        }
        //使用工厂设计模式
        SqlSessionFactory factory =new SqlSessionFactoryBuilder().build(is);
        //生产SqlSession
        session = factory.openSession();
        if(is != null){
            try {
                is.close();
            } catch (IOException e) {
                logger.debug("mybatis.xml输入流关闭错误");
            }
        }
    }


    public void destory(){
        session.close();
    }


    /**
     * 添加实时消息
     * @param timeLineList
     */
    public String insertTimeLine(List<TimeLine> timeLineList){
        StringBuilder timeLineNews = new StringBuilder();
        StringBuilder log =new StringBuilder();
        TimeLineMapper timeLineMapper = session.getMapper(TimeLineMapper.class);
        for(TimeLine timeLine : timeLineList){
            int res =timeLineMapper.addTimeLine(timeLine);
            if(res ==1){
                //新消息
                timeLineNews.append(timeLine.getProvinceName() +"<br>"+timeLine.getTitle()+"<br>"+timeLine.getSummary()+"<br><br>");
            }
            log.append(res+" ");
        }
        logger.info(log.toString());
        session.commit();
        return timeLineNews.toString();
    }

    /**
     * 添加总体数据
     * @param statistics
     */
    public String insertStatistics(Statistics statistics){
        StringBuilder statisticsNews = new StringBuilder();

        StatisticsMapper statisticsMapper=session.getMapper(StatisticsMapper.class);

        //数据库中最新的一条statistics数据
        Statistics oldStatistics = statisticsMapper.selectStatistics();
        if(statistics.equals(oldStatistics)){
            logger.info(0+"");
            return null;
        }else{
            statistics.setCountRemark("确诊 "+statistics.getConfirmedCount()+"例，重症"+statistics.getSeriousCount()+
                    "例，疑似 "+statistics.getSuspectedCount()+ "例，死亡 "+statistics.getDeadCount()+"例，治愈 "+statistics.getCuredCount()+"例");
            int res = statisticsMapper.addStatistics(statistics);
            logger.info(res+"");
            session.commit();
            String increaseMessage="较昨日增长: "+"<br/>确诊："+statistics.getConfirmedIncr()+"<br/>重症："+statistics.getSeriousIncr()+
                    "<br/>疑似："+statistics.getSuspectedIncr()+"<br/>死亡："+statistics.getDeadIncr()+"<br/>治愈："+statistics.getCuredIncr();
            statisticsNews.append(statistics.getCountRemark()+"<br/><br/>"+increaseMessage+"<br/><br/>");

            return statisticsNews.toString();
        }


    }

    /**
     * 添加各省信息
     * @param areaStatsList
     */
    public  String insertProvince(List<AreaStat> areaStatsList){
        StringBuilder provinceNews = new StringBuilder();
        StringBuilder log = new StringBuilder();

        AreaStatMapper areaStatMapper = session.getMapper(AreaStatMapper.class);
        for(AreaStat areaStat : areaStatsList){
            AreaStat oldAreaStat =selectProvince(areaStat.getProvinceName());
            if(oldAreaStat!= null ){
                //库中已经有了这个省份
                if(areaStat.equals(oldAreaStat)){
                    //数据一致 不添加
                    log.append("+E0"+"  ");
                }else{
                    areaStat.setModifyTime( System.currentTimeMillis()/1000);
                    int res=areaStatMapper.addProvince(areaStat);
                    log.append("+M"+res+"  ");
                    provinceNews.append("变动："+areaStat.getProvinceName()+"<br/>");
                    provinceNews.append(getNumber(areaStat));
                    provinceNews.append(getNumberChange(oldAreaStat,areaStat)+"<br><br>");
                }
            }else{
                //新增省份
                areaStat.setModifyTime( System.currentTimeMillis()/1000);
                int res = areaStatMapper.addProvince(areaStat);
                provinceNews.append("新增："+areaStat.getProvinceName()+"<br/>");
                provinceNews.append(getNumber(areaStat)+"<br><br>");
                log.append("+N"+res+"  ");
            }
            List<AreaStat.CitiesBean> cityList =areaStat.getCities();
            for(AreaStat.CitiesBean city :cityList){
                city.setProvinceName(areaStat.getProvinceName());
                AreaStat.CitiesBean oldCity = selectCity(city.getCityName());
                if(oldCity!= null){
                    //已有该城
                    if(oldCity.equals(city)){
                        //数据一致
                        log.append("E0"+"  ");
                    }else{
                        city.setModifyTime(System.currentTimeMillis()/1000);
                        int res=areaStatMapper.addCity(city);
                        log.append("M"+res+"  ");
                    }
                }else{
                    //新增城市
                    city.setModifyTime(System.currentTimeMillis()/1000);
                    int res = areaStatMapper.addCity(city);
                    log.append("N"+res+"  ");
                }
            }
        }
        logger.info(log.toString());
        session.commit();

        return provinceNews.toString();
    }

    public String getNumberChange(AreaStat oldAreaStat,AreaStat newAreaStat){
        StringBuilder sb = new StringBuilder();

        sb.append("确诊人数变化："+(newAreaStat.getConfirmedCount()-oldAreaStat.getConfirmedCount())+"<br>");
        sb.append("死亡人数变化："+(newAreaStat.getDeadCount()-oldAreaStat.getDeadCount())+"<br>");
        sb.append("治愈人数变化："+(newAreaStat.getCuredCount()-oldAreaStat.getCuredCount())+"<br>");

        return sb.toString();

    }
    public String getNumber(AreaStat areaStat){
        StringBuilder sb = new StringBuilder();

        sb.append("确诊人数："+areaStat.getConfirmedCount()+"<br>");
        sb.append("死亡人数："+areaStat.getDeadCount()+"<br>");
        sb.append("治愈人数："+areaStat.getCuredCount()+"<br>");

        return sb.toString();

    }

    /**
     * 查询某省信息
     * @param provinceName
     * @return
     */
    public  AreaStat selectProvince(String provinceName){
        AreaStatMapper areaStatMapper = session.getMapper(AreaStatMapper.class);
        AreaStat province =areaStatMapper.selProvince(provinceName);
        return province;
    }

    /**
     * 查询某市信息
     * @param cityName
     * @return
     */
    public  AreaStat.CitiesBean selectCity(String cityName){
        AreaStatMapper areaStatMapper = session.getMapper(AreaStatMapper.class);
        AreaStat.CitiesBean city =areaStatMapper.selCity(cityName);
        return city;
    }


}
