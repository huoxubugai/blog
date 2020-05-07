package com.lingfen.website.blog;

import com.alibaba.fastjson.JSONObject;
import com.lingfen.website.blog.bean.Blog;
import com.lingfen.website.blog.search.BlogSearchBean;
import com.lingfen.website.blog.search.BlogSearchRepository;
import com.lingfen.website.blog.service.BlogService;
import com.lingfen.website.blog.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {
    @Autowired
    BlogSearchRepository blogSearchRepository;
    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;

    @Test
    public void contextLoads() {

    }

    @Test
    public void addDataToES() {
        List<Blog> blogs = blogService.getAllBlog();
        List<BlogSearchBean> blogSearchBeans = new ArrayList<>();
        for (Blog blog : blogs) {
            BlogSearchBean blogSearchBean = new BlogSearchBean();
            blogSearchBean.setId(blog.getId());
            blogSearchBean.setTitle(blog.getTitle());
            blogSearchBean.setContent(blog.getContent());
            blogSearchBean.setAppreciation(blog.getAppreciation());
            blogSearchBean.setCommentabled(blog.getCommentabled());
            blogSearchBean.setCreateTime(blog.getCreateTime());
            blogSearchBean.setUpdateTime(blog.getUpdateTime());
            blogSearchBean.setDescription(blog.getDescription());
            blogSearchBean.setFirstPicture(blog.getFirstPicture());
            blogSearchBean.setFlag(blog.getFlag());
            blogSearchBean.setPublished(blog.getPublished());
            blogSearchBean.setRecommend(blog.getRecommend());
            blogSearchBean.setShareStatement(blog.getShareStatement());
            blogSearchBean.setTagIds(blog.getTagIds());
            blogSearchBean.setTypeId(blog.getTypeId());
            blogSearchBean.setUserId(blog.getUserId());
            blogSearchBean.setViews(blog.getViews());
            blogSearchBeans.add(blogSearchBean);
        }
        BlogSearchBean blog = new BlogSearchBean();
        for (BlogSearchBean blogSearchBean : blogSearchBeans) {
            blogSearchRepository.index(blogSearchBean);
        }
    }

    @Test
    public void search() {
        List<BlogSearchBean> blogSearchBeans = blogSearchRepository.findByTitleLike("es");
        for (BlogSearchBean blogSearchBean : blogSearchBeans) {
            System.out.println(blogSearchBean);
        }
    }

    @Test
    public void addBlog() {
        BlogSearchBean blogSearchBean = new BlogSearchBean();
        blogSearchBean.setId(101l);
        blogSearchBean.setTitle("es测试2");
        blogSearchBean.setContent("ss测试内容2");
        blogSearchBean.setViews(223);
        blogSearchRepository.index(blogSearchBean);
    }

    @Test
    public void delete() {
        blogSearchRepository.deleteAll();
    }

    @Test
    public void findByTitleAndContent() {
//        List<BlogSearchBean> result = blogSearchRepository.queryByTitleOrContent("ss","ss");
//        List<BlogSearchBean> result2 = blogSearchRepository.findByTitleOrContent("ss","ss");
        List<BlogSearchBean> result3 = blogSearchRepository.findByTitleOrContentLike("测试", "测试");

        for (BlogSearchBean blogSearchBean : result3) {
            System.out.println(blogSearchBean);
        }
    }

    @Test
    public void updateBlogNumsInTag() {
        List<Integer> tagIds = tagService.getTagIds();
        for (Integer tagId : tagIds) {
            int result = tagService.updateBlogNumsByTagId(tagId);
            System.out.println(result);
        }
    }

    @Test
    public void ipToRegion() {
        //这里通过ip获取的方式有很多，我这里用的腾讯
        String s = sendGet("101.37.91.71", "HIFBZ-VIJKD-AP74C-PZVHV-U7KH5-2ABLY");
        Map map = JSONObject.parseObject(s, Map.class);
        String message = (String) map.get("message");
        if ("query ok".equals(message)) {
            Map result = (Map) map.get("result");
            Map addressInfo = (Map) result.get("ad_info");
            String nation = (String) addressInfo.get("nation");
            String province = (String) addressInfo.get("province");
//        String district = (String) addressInfo.get("district");
            String city = (String) addressInfo.get("city");
            String address = nation + "-" + province + "-" + city;
            System.out.println(address);
        } else {
            System.out.println(message);
        }

    }

    public static String sendGet(String ip, String key) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = "https://apis.map.qq.com/ws/location/v1/ip?ip=" + ip + "&key=" + key;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (Map.Entry entry : map.entrySet()) {
                System.out.println(key + "--->" + entry);
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


}
