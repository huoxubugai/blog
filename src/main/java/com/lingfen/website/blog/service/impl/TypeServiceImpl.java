package com.lingfen.website.blog.service.impl;

import com.lingfen.website.blog.bean.Type;
import com.lingfen.website.blog.mapper.TypeMapper;
import com.lingfen.website.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper typeMapper;

    @Override
    public int saveType(Type type) {
        int i = typeMapper.insertSelective(type);
        return i;
    }

    @Override
    public Type getType(int id) {
        return null;
    }

    @Override
    public List<Type> getAllType() {
        List<Type> types = typeMapper.getAllType();
        return types;
    }

    @Override
    public int updateType(Type type) {
        return 0;
    }

    @Override
    public int deleteType(int id) {
        int result = typeMapper.deleteTypeById(id);
        return result;
    }

    @Override
    public Type selectTypeByName(String name) {
        Type type=typeMapper.selectTypeByName(name);
        return type;
    }

    @Override
    public Type selectTypeById(int id) {
        Type result = typeMapper.selectTypeById(id);
        return result;
    }

    @Override
    public List<Type> getPreViewType(int typeNums) {
        List<Type> previewTypes=typeMapper.getPreViewType(typeNums);
        return previewTypes;
    }
}
