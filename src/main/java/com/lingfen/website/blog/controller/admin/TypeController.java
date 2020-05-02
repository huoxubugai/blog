package com.lingfen.website.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfen.website.blog.bean.Type;
import com.lingfen.website.blog.service.TypeService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public String types(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum, 5);
        List<Type> types = typeService.getAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(){
        return "admin/types-input";
    }

    //增加type
    @PostMapping("/types/add")
    public String add(Type type, RedirectAttributes attributes, BindingResult result){
        System.out.println("前端传过来的表单" + type.toString());
        Type existType=typeService.selectTypeByName(type.getName());
        if(existType!=null){
            attributes.addFlashAttribute("message", "不能添加重复的类");
            return "redirect:/admin/types/input";
        }
        int i = typeService.saveType(type);
        if(i!=0){
//            attributes.addFlashAttribute("message", "添加成功");
            return "redirect:/admin/types";
        }
        return "redirect:/admin/types/input";
    }

    //到修改页面
    @GetMapping("/types/{id}/input")
    public String toTypeUpdatePage(@PathVariable int id, Model model) {
        model.addAttribute("type", typeService.selectTypeById(id));
        return "admin/types-update";
    }

    //进行修改
    @PostMapping("/types/update")
    public String updateType(Type type, RedirectAttributes attributes) {
        int result = typeService.updateType(type);
        if (result != 0) {
            attributes.addFlashAttribute("message", "更新成功");
        } else {
            attributes.addFlashAttribute("message", "更新失败，请重试");
        }
        return "redirect:/admin/types";
    }
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable int id,RedirectAttributes attributes){
        int i = typeService.deleteType(id);
        if(i!=0){
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/types";
    }

}
