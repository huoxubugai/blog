package com.lingfen.website.blog.controller.web;

import com.lingfen.website.blog.bean.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @PostMapping("/comments")
    public String postComment(Comment comment, HttpSession session) {

        return "";
    }
}
