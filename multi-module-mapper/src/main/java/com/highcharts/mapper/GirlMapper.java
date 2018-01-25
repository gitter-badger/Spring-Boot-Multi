package com.highcharts.mapper;
import com.highcharts.pojo.Girl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>spring-boot-mybatis/com.boy.zjh.dao</p>
 *
 * @author Created by BruceZheng
 * @date 2018-01-20 15:28
 **/
@Mapper
public interface GirlMapper {
        @Select("select * from girl where age = #{age}")
        public List<Girl> likeAge(Integer name);

        @Select("select * from girl where id = #{id}")
        public Girl getById(long id);

        @Select("select name from girl where id = #{id}")
        public String getNameById(long id);

        @Insert(" insert into girl(age,size_cup) values(#{age},#{size_cup})")
        @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
        public long saveGirl(Girl girl);

        Girl findById(int id);
}
