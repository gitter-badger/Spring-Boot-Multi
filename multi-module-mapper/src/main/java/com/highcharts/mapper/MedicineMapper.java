package com.highcharts.mapper;

import com.highcharts.pojo.Medicine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>multi-module/com.highcharts.mapper</p>
 *
 * @author Created by BruceZheng
 * @date 2018-01-24 15:21
 **/
@Mapper
public interface MedicineMapper {

    @Select("SELECT * FROM medicine ORDER BY total DESC")
    public List<Medicine> getMedicineList();

}
