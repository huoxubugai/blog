package com.lingfen.website.blog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

@Controller
public class FileController {

    // TODO: 2020/5/5  markdowm里的文件上传


    //图片上传
    //博客图片上传问题
    @RequestMapping("/file/upload")
    @ResponseBody
    public JSONObject fileUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request) throws IOException {
        //上传路径保存设置

        //获得SpringBoot当前项目的路径：System.getProperty("user.dir")
        String path = System.getProperty("user.dir") + "/upload/";

        //按照月份进行分类：
        Calendar instance = Calendar.getInstance();
        String month = (instance.get(Calendar.MONTH) + 1) + "月";
        path = path + month;

        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdirs();
        }

        //上传文件地址
        System.out.println("上传文件保存地址：" + realPath);

        //解决文件名字问题：我们使用uuid;
        String filename = "pg-" + UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
        File newfile = new File(realPath, filename);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newfile);

        //给editormd进行回调
        JSONObject res = new JSONObject();
        res.put("url", "/upload/" + month + "/" + filename);
        res.put("success", 1);
        res.put("message", "upload success!");

        return res;
    }
}
