package fun.ticsmyc.service;

import com.alibaba.fastjson.JSONException;
import fun.ticsmyc.crawler.Crawler;
import fun.ticsmyc.crawler.Parse;
import fun.ticsmyc.crawler.Tools;
import fun.ticsmyc.dao.InformationDao;
import fun.ticsmyc.email.EmailUtil;
import fun.ticsmyc.pojo.AreaStat;
import fun.ticsmyc.pojo.Statistics;
import fun.ticsmyc.pojo.TimeLine;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.GeneralSecurityException;
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
    private final Logger logger = Logger.getLogger(InformationService.class);

    private InformationDao informationDao;

    public InformationService() {
        this.informationDao = new InformationDao();
    }

    public void getNews(){
        //获取HTML数据
        Tools.getPageByJSoup(Crawler.URL);

        //提取static信息的json数据

        String staticInformation=null;
        //解析static信息的json数据
        Statistics statisticsInformation=null;
        try{
            staticInformation=Tools.getInformation(Crawler.STATIC_INFORMATION_REGEX_TEMPLATE_1,"id",Crawler.STATIC_INFORMATION_ATTRIBUTE);
            statisticsInformation= Parse.parseStatisticsInformation(staticInformation);
        }catch(JSONException e ){
            logger.error("static信息正则1匹配失败，切换正则2");
            staticInformation=Tools.getInformation(Crawler.STATIC_INFORMATION_REGEX_TEMPLATE_2,"id",Crawler.STATIC_INFORMATION_ATTRIBUTE);
            statisticsInformation= Parse.parseStatisticsInformation(staticInformation);
        }

        //数据持久化
        String timeLineNews =null;
        String provinceNews=null;
        String statisticsNews = informationDao.insertStatistics(statisticsInformation);

        if (statisticsNews != null){
            //总数据发生变化，各省数据更新
      //提取其他信息的json数据
            String timelineServiceInformation= Tools.getInformation(Crawler.TIME_LINE_REGEX_TEMPLATE,"id",Crawler.TIME_LINE_ATTRIBUTE);
            String areaInformation=Tools.getInformation(Crawler.AREA_INFORMATION_REGEX_TEMPLATE,"id",Crawler.AREA_INFORMATION_ATTRIBUTE);
            //解析
            List<TimeLine> timeLineList = Parse.parseTimeLineInformation(timelineServiceInformation);
            List<AreaStat> areaStatList = Parse.parseAreaInformation(areaInformation);

            timeLineNews = informationDao.insertTimeLine(timeLineList);
            provinceNews = informationDao.insertProvince(areaStatList);

            sendEmail(timeLineNews,provinceNews,statisticsNews);
        }

        informationDao.destory();
    }

    public void sendEmail(String timeLineNews,String provinceNews,String statisticsNews) {
        //读取收件人列表
        Properties properties = null;
        List<String> toEmailList = null;
        ClassLoader classLoader = getClass().getClassLoader();
        if(classLoader != null){
            URL toEmailFile = classLoader.getResource("emailReceiver.properties");
            properties = new Properties();
            try {
                properties.load(classLoader.getResource("email.properties").openStream());
            } catch (IOException e) {
                logger.error("邮件配置文件读取失败");
            }

            try {
                toEmailList = FileUtils.readLines(new File(URLDecoder.decode(toEmailFile.getPath(), "utf-8")));
            } catch (IOException e) {
                logger.error("邮件收件人读取失败");
            }
        }

        //邮件通知
        StringBuilder emailContent=new StringBuilder();
        if(timeLineNews!=null && timeLineNews.length()!=0){
            emailContent.append("----------------------新闻--------------------<br/>");
            emailContent.append(timeLineNews);
        }
        if(statisticsNews.length()!=0){
            emailContent.append("----------------------总人数-------------------<br/>");
            emailContent.append(statisticsNews);
        }
        if(provinceNews!= null && provinceNews.length()!=0){
            emailContent.append("---------------------各省变化-------------------<br/>");
            emailContent.append(provinceNews);
        }
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if(toEmailList!= null){
                for(String toUserEmail : toEmailList){
                    if(!toUserEmail .equals("") ){
                        EmailUtil.sendEmail((String) properties.get("email.authCode"), (String) properties.get("email.fromEmail"),toUserEmail,dateFormat.format(new Date())+"疫情动态",emailContent.toString());
                        Thread.sleep(5000);
                    }
                }
            }
        } catch (InterruptedException e) {
            logger.error("邮件发送失败");
        } catch (GeneralSecurityException e) {
            logger.error("邮件发送失败");
        } catch (MessagingException e) {
            logger.error("邮件发送失败");
        }
    }




}
