package com.lingfen.website.blog.mapper;

import com.lingfen.website.blog.bean.Tag;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TagMapper extends Mapper<Tag> {
    List<Tag> getAllTag();

    Tag selectTagByName(@Param("name") String name);

    Tag selectTagById(@Param("id") int id);

    int deleteTagById(@Param("id") int id);

    List<Tag> getPreViewTag(@Param("nums") int nums);

    int getMaxNumsBlogTagId();

    int updateTag(@Param("tag") Tag tag);


    List<Integer> getTagIdsByBlogId(@Param("blogId") long blogId);

    Tag getTagByTagId(@Param("tagId") Integer tagId);
}
