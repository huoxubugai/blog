package com.lingfen.website.blog.service;

import com.lingfen.website.blog.bean.Type;

import java.util.List;

public interface TypeService {
    int saveType(Type type);

    Type getType(int id);

    List<Type> getAllType();

    int updateType(Type type);

    int deleteType(int id);

    Type selectTypeByName(String name);

    Type selectTypeById(int id);

    List<Type> getPreViewType(int typeNums);

}
