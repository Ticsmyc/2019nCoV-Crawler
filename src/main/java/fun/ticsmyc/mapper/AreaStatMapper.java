package fun.ticsmyc.mapper;

import fun.ticsmyc.pojo.AreaStat;
import fun.ticsmyc.pojo.Statistics;

import java.awt.geom.Area;

/**
 * @author Ticsmyc
 * @package fun.ticsmyc.mapper
 * @date 2020-01-26 13:37
 */
public interface AreaStatMapper {

    AreaStat selProvince(String provinceName);

    int addProvince(AreaStat areaStat);

    int updateProvince(AreaStat areaStat);

    AreaStat.CitiesBean selCity(String city);

    int addCity(AreaStat.CitiesBean city);

    int updateCity(AreaStat.CitiesBean city);

}
