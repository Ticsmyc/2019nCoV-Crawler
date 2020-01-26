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

    Statistics selectStatistics(long modifyTime);

    int addStatistics(Statistics statistics);
}
