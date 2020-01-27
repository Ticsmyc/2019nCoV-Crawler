package fun.ticsmyc.mapper;

import fun.ticsmyc.pojo.Statistics;
import fun.ticsmyc.pojo.TimeLine;

import java.util.List;

/**
 * @author Ticsmyc
 * @package fun.ticsmyc.mapper
 * @date 2020-01-26 13:37
 */
public interface StatisticsMapper {

    /**
     * 查询最新一条statistics数据
     * @return
     */
    Statistics selectStatistics();

    /**
     * 添加新的总体数据
     * @param statistics
     * @return
     */
    int addStatistics(Statistics statistics);
}
