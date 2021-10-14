package com.example.ebusiness.controller.admin;

import com.example.ebusiness.entity.AUser;
import com.example.ebusiness.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author hdq
 * @create 2021-10-07 15:40
 */
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("/toLogin")
    public String toLogin(@ModelAttribute("aUser") AUser aUser) {
        return "admin/login";
    }
    @RequestMapping("/login")
    public String login(@ModelAttribute("aUser")AUser aUser, HttpSession session, Model model){
        return adminService.login(aUser, session, model);
    }

}
