package com.lingfen.website.blog.mapper;

import com.lingfen.website.blog.bean.Type;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TypeMapper extends Mapper<Type> {
    Type selectTypeByName(@Param("name") String name);
    List<Type> getAllType();

    Integer deleteTypeById(@Param("id") Integer id);

    Type selectTypeById(@Param("id") Integer id);

    List<Type> getPreViewType(@Param("typeNums") Integer typeNums);

    Integer getMaxNumsBlogTypeId();

    Integer updateType(@Param("type") Type type);

    Integer decreaseBlogNumsByTypeId(@Param("typeId") Integer typeId);

    Integer increaseBlogNumsByTypeId(@Param("typeId") Integer typeId);

    String getTypeNameByTypeId(@Param("typeId") Integer typeId);

    Integer updateBlogNumsToLatest();
}
