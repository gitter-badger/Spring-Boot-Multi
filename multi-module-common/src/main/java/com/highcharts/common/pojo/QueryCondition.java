package com.highcharts.common.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>BMap/com.highcharts.bean</p>
 *
 * @author Created by BruceZheng
 * @date 2018-03-07 15:46
 **/
@Data
@Accessors(chain = true)
public class QueryCondition {
    private String condition1 = null;
    private String condition2 = null;
    private String condition3 = null;
}
