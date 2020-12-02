package com.otming.controller.shiro;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/shiro")
public class ShiroController {




    @RequestMapping("/toLogin")
    public Boolean toLogin(HttpServletResponse response){

        response.setHeader("Access-Control-Allow-Origin","*");

        Boolean lisAllow = false;

        return lisAllow;




    }




}
