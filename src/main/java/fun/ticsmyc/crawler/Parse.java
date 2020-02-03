package fun.ticsmyc.crawler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fun.ticsmyc.pojo.AreaStat;
import fun.ticsmyc.pojo.Statistics;
import fun.ticsmyc.pojo.TimeLine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * JSON解析器
 * @author Ticsmyc
 * @package fun.ticsmyc.crawler
 * @date 2020-01-26 18:05
 */
public class Parse {

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
            System.out.println(timeLine);
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
        System.out.println(statistics);
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
            System.out.println(areaStat);
        }
        return list;
    }

    @Test
    public void test(){
        String data = "{\"id\":1,\"createTime\":1579537899000,\"modifyTime\":1580691174000,\"infectSource\":\"野生动物，可能为中华菊头蝠\",\"passWay\":\"经呼吸道飞沫传播，亦可通过接触传播，存在粪-口传播可能性\",\"imgUrl\":\"https://img1.dxycdn.com/2020/0201/450/3394153392393266839-135.png\",\"dailyPic\":\"https://img1.dxycdn.com/2020/0203/070/3394505951226077471-135.png\",\"summary\":\"\",\"deleted\":false,\"countRemark\":\"\",\"confirmedCount\":17238,\"suspectedCount\":21558,\"curedCount\":475,\"deadCount\":361,\"seriousCount\":2296,\"suspectedIncr\":2014,\"confirmedIncr\":2748,\"curedIncr\":41,\"deadIncr\":57,\"seriousIncr\":2296,\"virus\":\"新型冠状病毒 2019-nCoV\",\"remark1\":\"易感人群：人群普遍易感。老年人及有基础疾病者感染后病情较重，儿童及婴幼儿也有发病\",\"remark2\":\"潜伏期：一般为 3～7 天，最长不超过 14 天，潜伏期内存在传染性\",\"remark3\":\"\",\"remark4\":\"\",\"remark5\":\"\",\"generalRemark\":\"疑似病例数来自国家卫健委数据，目前为全国数据，未分省市自治区等\",\"abroadRemark\":\"\",\"marquee\":[{\"id\":42,\"marqueeLabel\":\"日报\",\"marqueeContent\":\" 七日内治愈人数首次超越死亡人数\",\"marqueeLink\":\"https://mama.dxy.com/japi/platform/200720055?index=20200202\"}";
        parseStatisticsInformation(data);
    }
}
