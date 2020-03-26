package com.lingfen.website.blog.controller.admin;

import com.lingfen.website.blog.bean.User;
import com.lingfen.website.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession httpSession, RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        if(user!=null){
            user.setPassword(" ");
            httpSession.setAttribute("user",user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("messeage", "用户名和密码错误");
            return "redirect:/admin ";
        }
    }

    @GetMapping("/logout")
    public String loginout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
