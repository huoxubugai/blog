package com.lingfen.website.blog.controller;

import com.lingfen.website.blog.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.print.Pageable;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
//        int i=9/0;
//        String blog=null;
//        if(blog==null)
//            throw new NotFoundException("博客不存在");
        return "index";
    }
    @GetMapping("/test")
    public String test() {

        return "test";
    }
    @GetMapping("/blog")
    public String blog() {

        return "blog";
    }
}
