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

/**
 * @author Ticsmyc
 * @package fun.ticsmyc.dao
 * @date 2020-01-26 12:39
 */
public class InformationDao {

    private  SqlSession session;

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
        TimeLineMapper timeLineMapper = session.getMapper(TimeLineMapper.class);
        for(TimeLine timeLine : timeLineList){
            int res =timeLineMapper.addTimeLine(timeLine);
            System.out.print(res+" ");
        }
        System.out.println();
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
            System.out.println(res);
            session.commit();
            return true;
        }else{
            System.out.println(0);
            return false;
        }

    }

    /**
     * 添加各省信息
     * @param areaStatsList
     */
    public  void insertProvince(List<AreaStat> areaStatsList){
        AreaStatMapper areaStatMapper = session.getMapper(AreaStatMapper.class);
        for(AreaStat areaStat : areaStatsList){
            AreaStat oldAreaStat =selectProvince(areaStat.getProvinceName());
            if(oldAreaStat!= null ){
                //库中已经有了这个省份
                if(areaStat.equals(oldAreaStat)){
                    //数据一致 不添加
                    System.out.print("-E0"+"  ");
                }else{
                    areaStat.setModifyTime( System.currentTimeMillis()/1000);
                    int res=areaStatMapper.addProvince(areaStat);
                    System.out.print("-M"+res+"  ");
                }
            }else{
                //新增省份
                areaStat.setModifyTime( System.currentTimeMillis()/1000);
                int res = areaStatMapper.addProvince(areaStat);
                System.out.print("-N"+res+"  ");
            }
            List<AreaStat.CitiesBean> cityList =areaStat.getCities();
            for(AreaStat.CitiesBean city :cityList){
                city.setProvinceName(areaStat.getProvinceName());
                AreaStat.CitiesBean oldCity = selectCity(city.getCityName());
                if(oldCity!= null){
                    //已有该城
                    if(oldCity.equals(city)){
                        //数据一致
                        System.out.print("E0"+"  ");
                    }else{
                        city.setModifyTime(System.currentTimeMillis()/1000);
                        int res=areaStatMapper.addCity(city);
                        System.out.print("M"+res+"  ");
                    }
                }else{
                    //新增城市
                    city.setModifyTime(System.currentTimeMillis()/1000);
                    int res = areaStatMapper.addCity(city);
                    System.out.print("N"+res+"  ");
                }
            }
        }
        System.out.println();
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
