package fun.ticsmyc.main;

import fun.ticsmyc.crawler.Crawler;
import fun.ticsmyc.crawler.Parse;
import fun.ticsmyc.crawler.Tools;
import fun.ticsmyc.dao.InformationDao;
import fun.ticsmyc.pojo.AreaStat;
import fun.ticsmyc.pojo.Statistics;
import fun.ticsmyc.pojo.TimeLine;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 用于可执行jar包。。。
 * @author Ticsmyc
 * @package fun.ticsmyc.main
 * @date 2020-01-26 21:51
 */
public class Main {
    public static void main(String[] args) {
        //每十分钟执行一次
        new Timer("testTimer").schedule(new TimerTask() {
            @Override
            public void run() {
                fffffuck("TimerTask");
            }
        }, 1000,600000);
    }

    public static void fffffuck(String timerTask){
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
        InformationDao.insertStatistics(statisticsInformation);
        InformationDao.insertProvince(areaStatList);
    }
}
