package com.example.ebusiness.controller.before;

import com.example.ebusiness.NoLoginException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * @author hdq
 * @create 2021-10-07 14:30
 */
@Controller
public class BeforeBaseController {
    /**
     * 登陆权限控制，@ModelAttribute会在继承该控制器的方法之前执行
     * 用来检测是否登录
     * @param session
     * @throws NoLoginException
     */
    @ModelAttribute
    public void isLogin(HttpSession session) throws NoLoginException {
        if (session.getAttribute("bUser") == null){
            throw new NoLoginException("没有登录");
        }
    }
}
