package com.otming;

import com.otming.pojo.Blog;
import com.otming.pojo.BlogType;
import com.otming.service.BlogService;
import com.otming.service.BlogTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class SpringBoot01ApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    DataSource dataSource;

    @Autowired
    BlogService blogService;

    @Autowired
    BlogTypeService blogTypeService;


    @Test
    public void test1() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();

        System.out.println(connection);
        connection.close();
    }


    @Test
    public void test2() {

        Date date = new Date();


        LocalDate now = LocalDate.now();

        System.out.println(date);
        System.out.println(now);

    }

    @Test
    public void test3(){
        HashMap map = new HashMap();

        map.put("username","zhangsan");

        List<Blog> blogs = blogService.getBlog(map);

        for (Blog blog : blogs) {
            System.out.println(blog);
        }

    }

    @Test
    public void test4(){
        BlogType blogType = blogTypeService.getBlogType(4);

        System.out.println(blogType);
    }
}
