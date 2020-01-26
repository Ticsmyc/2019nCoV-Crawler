package fun.ticsmyc.mapper;

import fun.ticsmyc.pojo.AreaStat;


/**
 * @author Ticsmyc
 * @package fun.ticsmyc.mapper
 * @date 2020-01-26 13:37
 */
public interface AreaStatMapper {
    /**
     * 查询某省疫情
     * @param provinceName
     * @return
     */
    AreaStat selProvince(String provinceName);

    /**
     * 添加一个省的疫情
     * @param areaStat
     * @return
     */
    int addProvince(AreaStat areaStat);

    /**
     * 修改一个省的疫情
     * @param areaStat
     * @return
     */
    int updateProvince(AreaStat areaStat);

    /**
     * 查询一个市的疫情
     * @param city
     * @return
     */
    AreaStat.CitiesBean selCity(String city);

    /**
     * 添加一个市的疫情
     * @param city
     * @return
     */
    int addCity(AreaStat.CitiesBean city);

    /**
     * 修改一个市的疫情
     * @param city
     * @return
     */
    int updateCity(AreaStat.CitiesBean city);

}
