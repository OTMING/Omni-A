package com.otming.controller.home;

import com.otming.pojo.Blog;
import com.otming.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/home")
public class HomeController {




        @Autowired
        private BlogService blogService;

        @RequestMapping("/page")
        public Map getData(@RequestParam int currentPage, @RequestParam int pageSize) {

            HashMap map = new HashMap();
            List<Blog> blogs = blogService.getBlog(map);

            int size = blogs.size();


            Map cmap = new HashMap();
            cmap.put("startIndex", (currentPage - 1) * pageSize);
            cmap.put("pageSize", pageSize);

            List<Blog> blogs1 = blogService.getBlog(cmap);


            Map mapData = new HashMap();

            mapData.put("blogs", blogs1);
            mapData.put("rows", size);


            return mapData;

        }



}
