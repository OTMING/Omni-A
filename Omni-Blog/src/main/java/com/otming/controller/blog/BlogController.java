package com.otming.controller.blog;

import cn.hutool.http.HtmlUtil;
import com.otming.pojo.Blog;
import com.otming.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    @Qualifier("blogServiceImpl")
    private BlogService blogService;

    @RequestMapping("/releasevue")
    public Map doRelease(String title,String content,String owner,String blogtype) {

        Map map = new HashMap();
        String summaryText = HtmlUtil.cleanHtmlTag(content);
        if (summaryText.length() > 150) {
            String summary = summaryText.substring(0, 150);
            map.put("summaryText",summary);
        } else {
            map.put("summaryText",summaryText);
        }



        LocalDate createDate = LocalDate.now();
        map.put("title", title);
        map.put("content", content);
        map.put("createDate", createDate);
        map.put("owner", owner);
        map.put("blogType", blogtype);
        int res = blogService.insertBlog(map);



        Map resmap = new HashMap();
        if (res >= 1) {
            resmap.put("res", true);
            return resmap;
        } else {
            resmap.put("res", false);
            return resmap;
        }


    }

    @RequestMapping("/getblogbyid")
    public Map getBlogById(@RequestParam int id) {

        Map map = new HashMap();
        map.put("bid",id);

        List<Blog> blog = blogService.getBlog(map);



        /*String scontent = HtmlUtils.htmlUnescape(blog.get(0).getContent());

        blog.get(0).setContent(scontent);*/

        Map resmap = new HashMap();

        resmap.put("blog", blog);

        return resmap;
    }

    @RequestMapping("/getblogbyname")
    public Map getBlogByName(String username, int currentPage, int pageSize) {


        Map map = new HashMap();
        map.put("username",username);
        List<Blog> blogs = blogService.getBlog(map);
        Map resmap = new HashMap();

        if (blogs.isEmpty()) {
            resmap.put("res", false);
            return resmap;

        } else {
            Map cmap = new HashMap();
            cmap.put("username", username);
            cmap.put("startIndex", (currentPage - 1) * pageSize);
            cmap.put("pageSize", pageSize);
            List<Blog> blogList = blogService.getBlog(cmap);
            resmap.put("rows", blogs.size());
            resmap.put("res", true);
            resmap.put("blogs", blogList);
            return resmap;
        }
    }

    @RequestMapping("/updateblogbyid")
    public Map updateBlogById(String title, String content, String blogtype,int id) {

        Map map = new HashMap();

        String summaryText = HtmlUtil.cleanHtmlTag(content);
        if (summaryText.length() > 150) {
            String summary = summaryText.substring(0, 150);
            map.put("summaryText",summary);
        } else {
            map.put("summaryText",summaryText);
        }


        map.put("bid", id);
        map.put("content", content);
        map.put("title", title);
        map.put("blogType", blogtype);
        int res = blogService.updateBlogById(map);

        Map resmap = new HashMap();

        if (res >= 1) {
            resmap.put("res", true);
            return resmap;
        } else {
            resmap.put("res", true);
            return resmap;
        }


    }

    @RequestMapping("/deleteblogbyid")
    public Map deleteBlogById(int id) {


        int res = blogService.deleteBlogById(id);


        Map resmap = new HashMap();

        if (res >= 1) {
            resmap.put("res", true);
            return resmap;
        } else {
            resmap.put("res", true);
            return resmap;
        }


    }

    @RequestMapping("/getblogbytype")
    public Map getBlogByType(String blogtype, int currentPage, int pageSize) {


        Map cmap = new HashMap();
        cmap.put("blogType",blogtype);
        List<Blog> blogs = blogService.getBlog(cmap);
        Map resmap = new HashMap();

        if (blogs.isEmpty()) {
            resmap.put("res", false);
            return resmap;

        } else {
            Map map = new HashMap();
            map.put("blogType", blogtype);
            map.put("startIndex", (currentPage - 1) * pageSize);
            map.put("pageSize", pageSize);
            List<Blog> blogList = blogService.getBlog(map);
            resmap.put("rows", blogs.size());
            resmap.put("res", true);
            resmap.put("blogs", blogList);
            return resmap;
        }
    }



}
