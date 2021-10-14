package com.example.ebusiness.service.admin;

import com.example.ebusiness.entity.AUser;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * @author hdq
 * @create 2021-10-07 15:39
 */
public interface AdminService {

    public String login(AUser aUser, HttpSession session, Model model);

}
