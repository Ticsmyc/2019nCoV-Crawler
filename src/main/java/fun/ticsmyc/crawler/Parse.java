package fun.ticsmyc.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.ticsmyc.pojo.AreaStat;
import fun.ticsmyc.pojo.Statistics;
import fun.ticsmyc.pojo.TimeLine;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON解析器
 * @author Ticsmyc
 * @package fun.ticsmyc.crawler
 * @date 2020-01-26 18:05
 */
public class Parse {

    private static final Logger logger = Logger.getLogger(Parse.class);
    /**
     * 解析TimeLine的JSON数据
     * @param timelineServiceInformation
     * @return
     */
    public static List<TimeLine> parseTimeLineInformation(String timelineServiceInformation){
        List<TimeLine> list = new ArrayList<>();
        JSONArray jsonArray = JSON.parseArray(timelineServiceInformation);
        for(Object jsonObj : jsonArray){
            TimeLine timeLine = JSON.toJavaObject((JSON) jsonObj,TimeLine.class);
            timeLine.setCreateTime(timeLine.getCreateTime()/1000);
            timeLine.setModifyTime(timeLine.getModifyTime()/1000);
            timeLine.setPubDate(timeLine.getPubDate()/1000);
            list.add(timeLine);
            logger.info(timeLine);
        }
        return list;
    }

    /**
     * 解析StatisticsJSON数据
     * @param staticInformation
     * @return
     */
    public static Statistics parseStatisticsInformation(String staticInformation){
        JSONObject jsonObj = JSON.parseObject(staticInformation);
        Statistics statistics = JSON.toJavaObject(jsonObj,Statistics.class);
        statistics.setCreateTime(statistics.getCreateTime()/1000);
        statistics.setModifyTime(statistics.getModifyTime()/1000);
        logger.info(statistics);
        return statistics;
    }

    /**
     * 解析地区信息JSON数据
     * @param areaInformation
     * @return
     */
    public static List<AreaStat> parseAreaInformation(String areaInformation){
        List<AreaStat> list = new ArrayList<>();
        JSONArray jsonArray =JSON.parseArray(areaInformation);
        for(Object jsonObj : jsonArray){
            AreaStat areaStat = JSON.toJavaObject((JSON) jsonObj, AreaStat.class);
            list.add(areaStat);
            logger.info(areaStat);
        }
        return list;
    }

}
