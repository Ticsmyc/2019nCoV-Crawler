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

    private static SqlSession session;

    static{
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

    public static void destory(){
        session.close();
    }


    /**
     * 添加实时消息
     * @param timeLineList
     */
    public static void insertTimeLine(List<TimeLine> timeLineList){
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
    public static void insertStatistics(Statistics statistics){
        StatisticsMapper statisticsMapper=session.getMapper(StatisticsMapper.class);
        int res = statisticsMapper.addStatistics(statistics);
        System.out.println(res);
        session.commit();
    }

    /**
     * 添加各省信息
     * @param areaStatsList
     */
    public static void insertProvince(List<AreaStat> areaStatsList){
        AreaStatMapper areaStatMapper = session.getMapper(AreaStatMapper.class);
        for(AreaStat areaStat : areaStatsList){
            if(selectProvince(areaStat.getProvinceName()) != null ){
                int res=areaStatMapper.updateProvince(areaStat);
                System.out.print(res+"  ");
            }else{
                int res = areaStatMapper.addProvince(areaStat);
                System.out.print(res+"  ");
            }
            List<AreaStat.CitiesBean> cityList =areaStat.getCities();
            for(AreaStat.CitiesBean city :cityList){
                if(selectCity(city.getCityName()) != null){
                    int res=areaStatMapper.updateCity(city);
                    System.out.print(res+"  ");
                }else{
                    int res = areaStatMapper.addCity(city);
                    System.out.print(res+"  ");
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
    public static AreaStat selectProvince(String provinceName){
        AreaStatMapper areaStatMapper = session.getMapper(AreaStatMapper.class);
        AreaStat province =areaStatMapper.selProvince(provinceName);
        return province;
    }

    public static AreaStat.CitiesBean selectCity(String cityName){
        AreaStatMapper areaStatMapper = session.getMapper(AreaStatMapper.class);
        AreaStat.CitiesBean city =areaStatMapper.selCity(cityName);
        return city;
    }


}
