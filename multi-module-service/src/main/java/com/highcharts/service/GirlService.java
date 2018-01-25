package com.highcharts.service;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.feature.Feature;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.github.pagehelper.PageHelper;
import com.highcharts.mapper.GirlMapper;
import com.highcharts.mapper.MedicineMapper;
import com.highcharts.pojo.Girl;
import com.highcharts.pojo.Medicine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>spring-boot-mybatis/com.boy.zjh.service</p>
 *
 * @author Created by BruceZheng
 * @date 2018-01-20 15:30
 **/
@Service
public class GirlService {

    @Autowired
    private GirlMapper girlMapper;

    public List<Girl> likeName(Integer age) {
        return girlMapper.likeAge(age);
    }

    public long saveGirl(Girl girl) {
        long l = girlMapper.saveGirl(girl);
        return l;
    }

    @Autowired
    private MedicineMapper medicineMapper;

    public Option selectRemoveCauses() {
        //查询前20
        PageHelper.startPage(1, 20, false);
        //数据库查询获取统计数据
        List<Medicine> list = medicineMapper.getMedicineList();

        //创建Option
        Option option = new Option();
        option.toolbox().show(true).feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar), Tool.restore, Tool.saveAsImage);
        option.title("药品图表").tooltip(Trigger.axis).legend("金额（元）");
        //横轴为值轴
        //option.xAxis(new ValueAxis().boundaryGap(0d, 0.01));
        option.yAxis();
        //创建类目轴
        CategoryAxis category = new CategoryAxis();
        //柱状数据
        Bar bar = new Bar("金额（元）");
        //饼图数据
        Pie pie = new Pie("金额（元）");
        //循环数据
        for (Medicine objectMap : list) {
            //设置类目
            category.data(objectMap.getName());
            //类目对应的柱状图
            bar.data(objectMap.getTotal());
            //饼图数据
            pie.data(new PieData(objectMap.getName(), objectMap.getTotal()));
        }
        //设置类目轴
        //option.yAxis(category);
        option.xAxis(category);
        //饼图的圆心和半径
        pie.center(900, 380).radius(100);
        //设置数据
        option.series(bar, pie);
        //由于药品名字过长，图表距离左侧距离设置180，关于grid可以看ECharts的官方文档
        option.grid().y(180);
        //返回Option
        return option;
    }
}
