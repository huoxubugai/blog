package com.lingfen.website.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfen.website.blog.bean.Blog;
import com.lingfen.website.blog.bean.Tag;
import com.lingfen.website.blog.bean.Type;
import com.lingfen.website.blog.bean.User;
import com.lingfen.website.blog.service.BlogRelationTag;
import com.lingfen.website.blog.service.BlogService;
import com.lingfen.website.blog.service.TagService;
import com.lingfen.website.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @Autowired
    BlogRelationTag blogRelationTag;

    int oldTypeId;//更新博客时，需要对oldTypeId对应的博客数量减一，再对newTypeId对应的博客数量加一

    @GetMapping("/blogs")
    public String listBlogs(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogs = blogService.getAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        List<Type> types = typeService.getAllType();
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", types);
        return "admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlogById(@PathVariable Long id, RedirectAttributes attributes) {
        int typeId = blogService.getTypeIdByBlogId(id);//根据博客id拿到对应的typeId
        int decreaseResult = typeService.decreaseBlogNumsByTypeId(typeId);//删除博客的同时要将type表中对应的博客数量减一
        int result = blogService.deleteBlogById(id);
        if (result != 0 && decreaseResult != 0) {
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("blogs/input")
    public String addBlogPage(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    //新增/修改博客
    @PostMapping("/blogs")
    public String addOrUpdateBlog(Blog blog, RedirectAttributes attributes, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        blog.setUserId(user.getId());
        blog.setUpdateTime(new Date());
        int result = 0;
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            result = blogService.savaBlog(blog); //拿不到id时则表示是新增博客
            int increseTypeResult = typeService.increaseBlogNumsByTypeId(blog.getTypeId()); //新增博客的同时对type表中的博客数量加1
        } else {
            result = blogService.updateBlog(blog);
            int newTypeId = blog.getTypeId();
            int atomicUpdateResult = typeService.atomicUpdateTwoTypeId(newTypeId, oldTypeId);  //以事务的方式对两个typeId进行原子更新
        }
        if (result != 0) {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/blogs";
    }

    //更新编辑博客
    @GetMapping("/blogs/{blogId}/input")
    public String editInput(@PathVariable Long blogId, Model model) {
        Blog blog = blogService.getBlogById(blogId);
        blog.init();
        oldTypeId = blog.getTypeId();//拿到博客更新前的typeId
        List<Type> types = typeService.getAllType();
        List<Tag> tags = tagService.getAllTag();
//        List<Integer> tagIds = tagService.getTagIdsByBlogId(blogId);//通过blogId拿到List<tagId>
//        List<Tag> blogTags=tagService.getTagsByTagIds(tagIds);//通过List<TagId>拿到List<Tag>
        model.addAttribute("blog", blog);
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);
        return "admin/blogs-input";

    }
}