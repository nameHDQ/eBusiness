package com.example.ebusiness.service.admin.impl;

import com.example.ebusiness.entity.AUser;
import com.example.ebusiness.repository.admin.AdminRepository;
import com.example.ebusiness.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author hdq
 * @create 2021-10-07 15:41
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Override
    public String login(AUser aUser,HttpSession session, Model model) {
        List<AUser> list = adminRepository.login(aUser);
        if (list.size() >0){
            session.setAttribute("auser", aUser);
            System.out.println("登陆成功");
            return "forward:/goods/selectAllGoodsByPage?currentPage=1&act=select";
        }else {
            model.addAttribute("errorMessage", "登陆失败，用户名或者密码错误");
            return "admin/login";
        }

    }
}
