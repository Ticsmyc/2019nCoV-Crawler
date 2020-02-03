package fun.ticsmyc.crawler;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 * @author Ticsmyc
 * @package fun.ticsmyc.crawler
 * @date 2020-01-26 18:03
 */
public class Tools {

    private static final Logger logger = Logger.getLogger(Tools.class);

    public static Document page;

    /**
     * 正则匹配获取
     * @param regex
     * @param attributeKey
     * @param attributeValue
     * @return
     */
    public static String getInformation(String regex , String attributeKey, String attributeValue){
        String result=null;
        //表达式对象
        Pattern p = Pattern.compile(regex);
        //创建Matcher对象
        Elements timelineService = page.getElementsByAttributeValue(attributeKey,attributeValue);
        Matcher m = p.matcher(timelineService.toString());
        if(m.find()) {  //该方法扫描输入的序列，查找与该模式匹配的一个子序列
            result=m.group();
        }
        return result;
    }

    /**
     * 通过Jsoup获取整个html页面
     * @param url
     * @return
     */
    public static void getPageByJSoup(String url) {
        try {
            page = Jsoup.connect(url).get();
        } catch (IOException e) {
            logger.error("jsoup获取页面失败");
        }

    }

}
