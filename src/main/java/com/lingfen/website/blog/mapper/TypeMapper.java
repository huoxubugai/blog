package com.lingfen.website.blog.mapper;

import com.lingfen.website.blog.bean.Type;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TypeMapper extends Mapper<Type> {
    Type selectTypeByName(@Param("name") String name);
    List<Type> getAllType();

    int deleteTypeById(@Param("id") int id);

    Type selectTypeById(@Param("id") int id);

    List<Type> getPreViewType(@Param("typeNums") int typeNums);

    int getMaxNumsBlogTypeId();
}
