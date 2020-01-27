package fun.ticsmyc;

import static org.junit.Assert.assertTrue;

import fun.ticsmyc.service.InformationService;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;


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


    @Test
    public void testIo(){

        String filename ="11.txt";

        //一般指定文件夹而不是文件。
        File path = new File("d:\\java");

        if(! path.exists()){
            //如果文件所在目录不存在,必须先创建目录， 不然会报错“找不到指定路径”
            path.mkdirs();
        }

        BufferedWriter bw = null;
        try {
                //true: 在文件后面追加
             bw= new BufferedWriter(new OutputStreamWriter(new FileOutputStream( new File(path,filename),true)));
             bw.write("在这里写要输出的东西就好了");
             bw.flush(); //刷新缓存 不然不输出
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
