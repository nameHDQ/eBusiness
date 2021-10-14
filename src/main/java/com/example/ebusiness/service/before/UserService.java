package com.example.ebusiness.service.before;

import com.example.ebusiness.entity.BUser;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

/**
 * @author hdq
 * @create 2021-10-09 12:35
 */
public interface UserService {
    List<BUser> hasUser(BUser bUser);

    String register(BUser bUser);

    String login(HttpServletRequest request, HttpServletResponse response, BUser bUser, HttpSession session, Model model);
}
