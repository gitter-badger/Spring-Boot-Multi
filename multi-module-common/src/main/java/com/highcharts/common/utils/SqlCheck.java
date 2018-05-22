package com.highcharts.common.utils;

/**
 * <p>multi-module/com.highcharts.common.utils</p>
 *
 * @author Created by BruceZheng
 * @date 2018-01-24 11:12
 **/
public class SqlCheck {

    /**
     * 检测sql，防止sql注入
     *
     * @param sql
     *            sql
     * @return 正常返回sql；异常返回""
     */
    public static String checkSql(String sql) {
        String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
        String inj_stra[] = inj_str.split("\\|");
        for (int i = 0; i < inj_stra.length; i++) {
            if (sql.indexOf(inj_stra[i]) >= 0) {
                return "";
            }
        }
        return sql;
    }
}
