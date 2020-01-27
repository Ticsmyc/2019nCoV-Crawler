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

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Ticsmyc
 * @package fun.ticsmyc.dao
 * @date 2020-01-26 12:39
 */
public class InformationDao {

    private  SqlSession session;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public InformationDao() {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //使用工厂设计模式
        SqlSessionFactory factory =new SqlSessionFactoryBuilder().build(is);
        //生产SqlSession
        session = factory.openSession();
    }


    public void destory(){
        session.close();
    }


    /**
     * 添加实时消息
     * @param timeLineList
     */
    public  void insertTimeLine(List<TimeLine> timeLineList){
        StringBuilder log =new StringBuilder();
        TimeLineMapper timeLineMapper = session.getMapper(TimeLineMapper.class);
        for(TimeLine timeLine : timeLineList){
            int res =timeLineMapper.addTimeLine(timeLine);
            log.append(res+" ");
        }
        logger.info(log.toString());
        session.commit();
    }

    /**
     * 添加总体数据
     * @param statistics
     */
    public boolean insertStatistics(Statistics statistics){
        StatisticsMapper statisticsMapper=session.getMapper(StatisticsMapper.class);
        if(statisticsMapper.selectStatistics(statistics.getModifyTime())==null){
            int res = statisticsMapper.addStatistics(statistics);
            logger.info(res+"");
            session.commit();
            return true;
        }else{
            logger.info(0+"");
            return false;
        }

    }

    /**
     * 添加各省信息
     * @param areaStatsList
     */
    public  void insertProvince(List<AreaStat> areaStatsList){
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
                }
            }else{
                //新增省份
                areaStat.setModifyTime( System.currentTimeMillis()/1000);
                int res = areaStatMapper.addProvince(areaStat);
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
