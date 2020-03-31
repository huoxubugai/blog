package com.lingfen.website.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfen.website.blog.bean.Blog;
import com.lingfen.website.blog.bean.Tag;
import com.lingfen.website.blog.bean.Type;
import com.lingfen.website.blog.bean.User;
import com.lingfen.website.blog.service.BlogService;
import com.lingfen.website.blog.service.TagService;
import com.lingfen.website.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;

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
        int result = blogService.deleteBlogById(id);
        if (result != 0) {
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

    @PostMapping("/blogs")
    public String addBlog(Blog blog, RedirectAttributes attributes, HttpSession httpSession) {
//        blog.setUser((User)httpSession.getAttribute("user"));
        User user = (User) httpSession.getAttribute("user");
//        blog.setTypeId(blog.getType().getId());
        blog.setUserId(user.getId());
//        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        int result = 0;
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            result = blogService.savaBlog(blog);

        } else {
            result = blogService.updateBlog(blog);
        }
        if (result != 0) {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/blogs";
    }

    //更新编辑博客
    @GetMapping("/blogs/{blogId}/input")
    public String toEditPage(@PathVariable long blogId, Model model) {
        Blog blog = blogService.getBlogById(blogId);
        blog.init();
        List<Type> types = typeService.getAllType();
        List<Tag> tags = tagService.getAllTag();
//        List<Integer> tagIds = tagService.getTagIdsByBlogId(blogId);//通过blogId拿到List<tagId>
//        List<Tag> blogTags=tagService.getTagsByTagIds(tagIds);//通过List<TagId>拿到List<Tag>
        model.addAttribute("blog", blog);
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);
//        model.addAttribute("blogTags", blogTags);
//        return "admin/blogs-update";
        return "admin/blogs-input";
    }

//    @PostMapping("/blogs/update")
////    public String editPost(Blog blog,RedirectAttributes attributes) {
////        int result = blogService.updateBlog(blog);
////        if(result!=0)
////            attributes.addFlashAttribute("message", "修改成功");
////        else
////            attributes.addFlashAttribute("message", "修改失败");
////        return "redirect:/admin/blogs";
////    }

}
