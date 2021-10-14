package com.example.ebusiness.controller.admin;

import com.example.ebusiness.NoLoginException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * @author hdq
 * @create 2021-10-07 12:16
 */
@Controller
public class AdminBaseController {

    /**
     * 登陆权限控制，@ModelAttribute会在继承该控制器的方法之前执行
     * 用来检测是否登录
     * @param session
     * @throws NoLoginException
     */
    @ModelAttribute
    public void isLogin(HttpSession session) throws NoLoginException {
        if (session.getAttribute("auser") == null){
            throw new NoLoginException("没有登录");
        }
    }
}
