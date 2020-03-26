package com.lingfen.website.blog.mapper;

import com.lingfen.website.blog.bean.Type;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TypeMapper extends Mapper<Type> {
    Type selectTypeByName(@Param("name") String name);
    List<Type> getAllType();

    int deleteById(@Param("id") int id);

    Type selectById(@Param("id") int id);
}
