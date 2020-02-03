package fun.ticsmyc.service;

import fun.ticsmyc.crawler.Crawler;
import fun.ticsmyc.crawler.Parse;
import fun.ticsmyc.crawler.Tools;
import fun.ticsmyc.dao.InformationDao;
import fun.ticsmyc.email.EmailUtil;
import fun.ticsmyc.pojo.AreaStat;
import fun.ticsmyc.pojo.Statistics;
import fun.ticsmyc.pojo.TimeLine;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author Ticsmyc
 * @package fun.ticsmyc.service
 * @date 2020-01-27 15:48
 */
public class InformationService {

    private InformationDao informationDao;

    public InformationService() {
        this.informationDao = new InformationDao();
    }

    public void getNews(){


        boolean sendEmail = false;
        //获取HTML数据
        Tools.getPageByJSoup(Crawler.URL);

        //提取json数据
        Crawler.timelineServiceInformation= Tools.getInformation(Crawler.TIME_LINE_REGEX_TEMPLATE,"id",Crawler.TIME_LINE_ATTRIBUTE);
        Crawler.areaInformation=Tools.getInformation(Crawler.AREA_INFORMATION_REGEX_TEMPLATE,"id",Crawler.AREA_INFORMATION_ATTRIBUTE);
        Crawler.staticInformation=Tools.getInformation(Crawler.STATIC_INFORMATION_REGEX_TEMPLATE,"id",Crawler.STATIC_INFORMATION_ATTRIBUTE);


        //解析json数据
        List<TimeLine> timeLineList = Parse.parseTimeLineInformation(Crawler.timelineServiceInformation);
        Statistics statisticsInformation = Parse.parseStatisticsInformation(Crawler.staticInformation);
        List<AreaStat> areaStatList = Parse.parseAreaInformation(Crawler.areaInformation);

        //数据持久化

        String timeLineNews = informationDao.insertTimeLine(timeLineList);
        String statisticsNews = informationDao.insertStatistics(statisticsInformation);
        String provinceNews=null;
        if (statisticsNews != null){
            //总数据发生变化，各省数据更新
            provinceNews=informationDao.insertProvince(areaStatList);
            sendEmail=true;

        }

        if(sendEmail ){
            //读取收件人列表
            ClassLoader classLoader = getClass().getClassLoader();
            URL toEmailFile = classLoader.getResource("emailReceiver.properties");
            Properties properties = new Properties();
            try {
                properties.load(classLoader.getResource("email.properties").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            List<String> toEmailList = null;
            try {
                toEmailList = FileUtils.readLines(new File(URLDecoder.decode(toEmailFile.getPath(), "utf-8")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //邮件通知
            StringBuilder emailContent=new StringBuilder();
            if(timeLineNews.length()!=0){
                emailContent.append("----------------------新闻--------------------<br/>");
                emailContent.append(timeLineNews);
            }
            if(statisticsNews.length()!=0){
                emailContent.append("----------------------总人数-------------------<br/>");
                emailContent.append(statisticsNews);
            }
            if(provinceNews.length()!=0){
                emailContent.append("---------------------各省变化-------------------<br/>");
                emailContent.append(provinceNews);
            }
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                for(String toUserEmail : toEmailList){
                    if(toUserEmail != ""){
                        EmailUtil.sendEmail((String) properties.get("email.authCode"), (String) properties.get("email.fromEmail"),toUserEmail,dateFormat.format(new Date())+"疫情动态",emailContent.toString());
                        Thread.sleep(5000);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        informationDao.destory();
    }




}
