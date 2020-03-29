package com.lingfen.website.blog.controller.web;

import com.lingfen.website.blog.bean.helpbean.ArchivesBlogBean;
import com.lingfen.website.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchivesController {
    @Autowired
    BlogService blogService;
    @GetMapping("/archives")
    public String toArchivesPage(Model model){
        int totalPublishedBlogNums = blogService.getTotalPublishedBlogNums();
        Map<String, List<ArchivesBlogBean>> archivesBlog = blogService.getArchivesBlog();
        model.addAttribute("archives", archivesBlog);
        model.addAttribute("total", totalPublishedBlogNums);
        return "/archives";
    }
}
