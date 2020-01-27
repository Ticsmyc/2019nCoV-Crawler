package fun.ticsmyc;

import static org.junit.Assert.assertTrue;

import fun.ticsmyc.service.InformationService;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;


public class AppTest 
{

    @Test
    public void test(){

        InformationService informationService = new InformationService();
        informationService.getNews();
    }

    @Test
    public void test2(){
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testEmail() throws IOException {
        Properties properties = new Properties();
        //可以用两种不同的流来加载配置文件
        //properties.load(new BufferedReader(new FileReader(filePath)));
        ClassLoader classLoader = getClass().getClassLoader();

        properties.load(classLoader.getResource("email.properties").openStream());
        System.out.println( properties.get("email.authCode"));

    }
}
