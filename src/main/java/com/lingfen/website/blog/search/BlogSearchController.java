package com.lingfen.website.blog.search;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfen.website.blog.bean.User;
import com.lingfen.website.blog.service.TypeService;
import com.lingfen.website.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogSearchController {
    @Autowired
    BlogSearchService blogSearchService;
    @Autowired
    TypeService typeService;
    @Autowired
    UserService userService;

    @PostMapping("/search")
    public String search(@RequestParam String query, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        List<BlogSearchBean> searchBlogs = blogSearchService.searchBlog(query);
        setOtherContent(searchBlogs);
        PageInfo<BlogSearchBean> page = new PageInfo<>(searchBlogs);
        model.addAttribute("page", page);
        model.addAttribute("query", query);
        return "/search";
    }

    private void setOtherContent(List<BlogSearchBean> blogSearchBeans) {
        for (BlogSearchBean blogSearchBean : blogSearchBeans) {
            String typeName = typeService.getTypeNameByTypeId(blogSearchBean.getTypeId());
            blogSearchBean.setTypeName(typeName); //根据typeId拿到typeName并设置给搜索出来的博客
            User user = userService.getUserById(blogSearchBean.getUserId());
            blogSearchBean.setUserAvatar(user.getAvatar());
            blogSearchBean.setUserNickname(user.getNickname());//根据userId拿到nickname和avatar 并赋值给搜索出来的博客
        }
    }
}
