package fun.ticsmyc.dao;

import fun.ticsmyc.mapper.TimeLineMapper;
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


    public static void insertTimeLine(List<TimeLine> timeLineList){
        TimeLineMapper timeLineMapper = session.getMapper(TimeLineMapper.class);
        for(TimeLine timeLine : timeLineList){
            int res =timeLineMapper.addTimeLine(timeLine);
            System.out.print(res+" ");
        }
        session.commit();
    }



}
