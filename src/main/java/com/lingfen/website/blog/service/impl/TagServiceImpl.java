package com.lingfen.website.blog.service.impl;

import com.lingfen.website.blog.bean.Tag;
import com.lingfen.website.blog.bean.Type;
import com.lingfen.website.blog.mapper.TagMapper;
import com.lingfen.website.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;

    @Override
    public List<Tag> getAllTag() {
        List<Tag> tags = tagMapper.getAllTag();
        return tags;
    }

    @Override
    public Tag selectTagByName(String name) {
        Tag tag=tagMapper.selectTagByName(name);
        return tag;
    }

    @Override
    public int saveTag(Tag tag) {
        int i = tagMapper.insertSelective(tag);
        return i;
    }

    @Override
    public Tag selectTagById(int id) {
        Tag result = tagMapper.selectTagById(id);
        return result;
    }

    @Override
    public int deleteTag(int id) {
        int result = tagMapper.deleteTagById(id);
        return result;
    }
}
