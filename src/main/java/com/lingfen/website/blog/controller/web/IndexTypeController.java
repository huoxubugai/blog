package com.lingfen.website.blog.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfen.website.blog.bean.Type;
import com.lingfen.website.blog.bean.helpbean.PreviewBlog;
import com.lingfen.website.blog.service.BlogService;
import com.lingfen.website.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexTypeController {
    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;

    //点进首页的分类
    @GetMapping("/types/{typeId}")
    public String getBlogByType(@PathVariable("typeId") Integer typeId, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //首页导航进去时 typeId=-1,变更为博客数最多的typeId
        if(typeId==-1){
            typeId=typeService.getMaxNumsBlogTypeId();
        }
        PageHelper.startPage(pageNum, 5);
        List<PreviewBlog> previewBlogs=blogService.getPreviewBlogByType(typeId);
        PageInfo<PreviewBlog> pageInfo = new PageInfo<>(previewBlogs);
        List<Type> types = typeService.getAllType();
        model.addAttribute("types", types);
        model.addAttribute("page", pageInfo);
        model.addAttribute("activeTypeId", typeId);
        return "types";
    }
}
