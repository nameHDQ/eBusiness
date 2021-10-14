package com.example.ebusiness.controller.admin;

import com.example.ebusiness.entity.AUser;
import com.example.ebusiness.service.admin.UserAndOrderAndOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author hdq
 * @create 2021-10-08 11:02
 */
@Controller
public class UserAndOrderAndOutController extends AdminBaseController{

    @Autowired
    private UserAndOrderAndOutService userAndOrderAndOutService;

    @RequestMapping("/selectOrder")
    public String selectOrder(Model model,int currentPage){
        return userAndOrderAndOutService.selectOrder(model,currentPage);
    }

    @RequestMapping("/selectUser")
    public String selectUser(Model model,int currentPage){
        return userAndOrderAndOutService.selectUser(model,currentPage);
    }

    @RequestMapping("/loginOut")
    public String loginOut(@ModelAttribute("aUser") AUser aUser,HttpSession session){
        //使会话失效
        session.invalidate();
        return "admin/login";
    }
}
