package com.lingfen.website.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfen.website.blog.bean.Tag;
import com.lingfen.website.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    TagService tagService;
    @GetMapping("/tags")
    public String tags(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, 5);
        List<Tag> tags = tagService.getAllTag();
        PageInfo<Tag> tagInfo = new PageInfo<>(tags);
        model.addAttribute("tagInfo",tagInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(){
        return "admin/tags-input";
    }

    //增加tag
    @PostMapping("/tags/add")
    public String add(Tag tag, RedirectAttributes attributes, BindingResult result){
        Tag existTag=tagService.selectTagByName(tag.getName());
        if(existTag!=null){
            attributes.addFlashAttribute("message", "不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }
        int i = tagService.saveTag(tag);
        if(i!=0){
//            attributes.addFlashAttribute("message", "添加成功");
            return "redirect:/admin/tags";
        }
        return "redirect:/admin/tags/input";
    }

    //到tag更新页面
    @GetMapping("/tags/{id}/input")
    public String toTagUpdatePage(@PathVariable Integer id, Model model) {
        model.addAttribute("tag", tagService.selectTagById(id));
        return "admin/tags-update";
    }

    @PostMapping("/tags/update")
    public String update(Tag tag, RedirectAttributes attributes) {
        int result = tagService.updateTag(tag);
        if (result != 0) {
            attributes.addFlashAttribute("message", "更新成功");
        } else {
            attributes.addFlashAttribute("message", "更新失败，请重试");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
        int i = tagService.deleteTag(id);
        if(i!=0){
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/tags";
    }
}
