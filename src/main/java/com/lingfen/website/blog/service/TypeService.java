package com.lingfen.website.blog.service;

import com.lingfen.website.blog.bean.Type;

import java.util.List;

public interface TypeService {
    int saveType(Type type);

    Type getType(Integer id);

    List<Type> getAllType();

    int updateType(Type type);

    int deleteType(Integer id);

    Type selectTypeByName(String name);

    Type selectTypeById(Integer id);

    List<Type> getPreViewType(Integer typeNums);

    int getMaxNumsBlogTypeId();

    int decreaseBlogNumsByTypeId(Integer typeId);

    int increaseBlogNumsByTypeId(Integer typeId);

    int atomicUpdateTwoTypeId(Integer newTypeId, Integer oldTypeId);

    String getTypeNameByTypeId(Integer typeId);

    int updateBlogNumsToLatest();
}
