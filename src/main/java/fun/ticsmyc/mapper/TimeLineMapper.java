package fun.ticsmyc.mapper;

import fun.ticsmyc.pojo.TimeLine;

import java.util.List;

/**
 * @author Ticsmyc
 * @package fun.ticsmyc.mapper
 * @date 2020-01-26 13:37
 */
public interface TimeLineMapper {

    /**
     * 查询所有
     * @return
     */
    List<TimeLine> selAll();

    /**
     * 添加实时新闻，如果id冲突则不添加。
     * @param timeLine
     * @return
     */
    int addTimeLine(TimeLine timeLine);
}
