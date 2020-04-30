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
    public Type getType(Integer id) {
        return null;
    }

    @Override
    public List<Type> getAllType() {
        List<Type> types = typeMapper.getAllType();
        return types;
    }

    @Override
    public int updateType(Type type) {
        int result = typeMapper.updateType(type);
        return result;
    }

    @Override
    public int deleteType(Integer id) {
        int result = typeMapper.deleteTypeById(id);
        return result;
    }

    @Override
    public Type selectTypeByName(String name) {
        Type type=typeMapper.selectTypeByName(name);
        return type;
    }

    @Override
    public Type selectTypeById(Integer id) {
        Type result = typeMapper.selectTypeById(id);
        return result;
    }

    @Override
    public List<Type> getPreViewType(Integer typeNums) {
        List<Type> previewTypes=typeMapper.getPreViewType(typeNums);
        return previewTypes;
    }

    @Override
    public int getMaxNumsBlogTypeId() {
        int typeId=typeMapper.getMaxNumsBlogTypeId();
        return typeId;
    }

    @Override
    public int decreaseBlogNumsByTypeId(Integer typeId) {
        int result = typeMapper.decreaseBlogNumsByTypeId(typeId);
        return result;
    }

    @Override
    public int increaseBlogNumsByTypeId(Integer typeId) {
        int result = typeMapper.increaseBlogNumsByTypeId(typeId);
        return result;
    }

    public int atomicUpdateTwoTypeId(Integer newTypeId, Integer oldTypeId) {
        int result = typeMapper.increaseBlogNumsByTypeId(newTypeId) + typeMapper.decreaseBlogNumsByTypeId(oldTypeId);
        return result;
    }

    @Override
    public String getTypeNameByTypeId(Integer typeId) {
        String name = typeMapper.getTypeNameByTypeId(typeId);
        return name;
    }

    @Override
    public int updateBlogNumsToLatest() {
        int result = typeMapper.updateBlogNumsToLatest();
        return result;
    }
}
