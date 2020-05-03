package com.lingfen.website.blog.mapper;

import com.lingfen.website.blog.bean.Tag;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TagMapper extends Mapper<Tag> {
    List<Tag> getAllTag();

    Tag selectTagByName(@Param("name") String name);

    Tag selectTagById(@Param("id") Integer id);

    Integer deleteTagById(@Param("id") Integer id);

    List<Tag> getPreViewTag(@Param("nums") Integer nums);

    Integer getMaxNumsBlogTagId();

    Integer updateTag(@Param("tag") Tag tag);


    List<Integer> getTagIdsByBlogId(@Param("blogId") Long blogId);

    Tag getTagByTagId(@Param("tagId") Integer tagId);

    List<Integer> getTagIds();

    int updateBlogNumsByTagId(@Param("tagId") Integer tagId);
}
