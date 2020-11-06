package com.otming.controller.user;


import com.otming.pojo.User;
import com.otming.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/loginvue")
    public Map login(@RequestParam String username, @RequestParam String password,HttpSession session) {

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);




        Map resmap = new HashMap();
        try {
            subject.login(token);
            User loginUser = (User) subject.getPrincipal();
            //User loginUser = userService.selectByName(username);
            session.setAttribute("USER_SESSION",loginUser);
            resmap.put("user",loginUser);
            resmap.put("msg", true);
            return resmap;
        } catch (UnknownAccountException e) {//用户名不存在
            resmap.put("msg", false);
            return resmap;
        }catch (IncorrectCredentialsException e){//密码不存在
            resmap.put("msg", false);
            return resmap;
        }



       /* User loginuser = userService.selectByName(username);

        if (loginuser == null) {
            resmap.put("msg", false);
        } else {
            Map map = new HashMap();
            map.put("username", username);
            map.put("password", password);


            User user = userService.selectUser(map);


            if (user == null) {
                resmap.put("msg", false);
            } else {
                session.setAttribute("USER_SESSION", user);
                session.setAttribute("loginname", user.getUsername());
                resmap.put("user", user);
                resmap.put("msg", true);


                return resmap;
            }
        }


        return resmap;*/
    }

    @RequestMapping("/registvue")
    public Map register(@RequestParam String username, @RequestParam String password, Model model) {

        Map map = new HashMap();
        map.put("username", username);
        map.put("password", password);
        int res = userService.insertUser(map);
        Map resmap = new HashMap();

        if (res >= 1) {

            resmap.put("msg", "注册成功,请登入");
            resmap.put("res", true);
            return resmap;
        } else {
            model.addAttribute("msg", "注册失败");
            model.addAttribute("res", false);
            return resmap;
        }


    }

    @RequestMapping("/checkname")
    public Map checkName(@RequestParam String username) {

        User user = userService.selectByName(username);
        Map resmap = new HashMap();

        if (user != null) {
            resmap.put("res", true);
            return resmap;
        } else {
            resmap.put("res", false);
            return resmap;
        }
    }

}
