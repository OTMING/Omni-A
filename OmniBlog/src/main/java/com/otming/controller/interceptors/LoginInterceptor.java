package com.otming.controller.interceptors;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/intercept")
public class LoginInterceptor {

    @RequestMapping("/torelease")
    public Boolean toRelease(){


        return true;
    }


}
