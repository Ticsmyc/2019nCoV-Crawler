package fun.ticsmyc;

import static org.junit.Assert.assertTrue;

import fun.ticsmyc.crawler.Crawler;
import fun.ticsmyc.crawler.Parse;
import fun.ticsmyc.crawler.Tools;
import fun.ticsmyc.dao.InformationDao;
import fun.ticsmyc.pojo.AreaStat;
import fun.ticsmyc.pojo.Statistics;
import fun.ticsmyc.pojo.TimeLine;
import org.junit.Test;

import java.util.List;


public class AppTest 
{

    @Test
    public void test(){
        //获取json数据
        Crawler.timelineServiceInformation= Tools.getInformation(Crawler.TIME_LINE_REGEX_TEMPLATE,"id",Crawler.TIME_LINE_ATTRIBUTE);
        Crawler.areaInformation=Tools.getInformation(Crawler.AREA_INFORMATION_REGEX_TEMPLATE,"id",Crawler.AREA_INFORMATION_ATTRIBUTE);
        Crawler.staticInformation=Tools.getInformation(Crawler.STATIC_INFORMATION_REGEX_TEMPLATE,"id",Crawler.STATIC_INFORMATION_ATTRIBUTE);


        //解析json数据
        List<TimeLine> timeLineList = Parse.parseTimeLineInformation(Crawler.timelineServiceInformation);
        Statistics statisticsInformation = Parse.parseStatisticsInformation(Crawler.staticInformation);
        List<AreaStat> areaStatList = Parse.parseAreaInformation(Crawler.areaInformation);

        //数据持久化
        InformationDao.insertTimeLine(timeLineList);
    }
}
