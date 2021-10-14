package com.example.ebusiness.service.before.impl;

import com.example.ebusiness.entity.BUser;
import com.example.ebusiness.repository.before.CartRepository;
import com.example.ebusiness.repository.before.UserRepository;
import com.example.ebusiness.service.before.UserService;
import com.example.ebusiness.util.MD5Util;
import com.example.ebusiness.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author hdq
 * @create 2021-10-09 12:35
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Override
    public List<BUser> hasUser(BUser bUser) {
        List<BUser> bUsers = userRepository.selectUser(bUser);
        return bUsers;
    }

    @Override
    public String register(BUser bUser) {
        bUser.setBemail(MD5Util.string2MD5(bUser.getBemail()));
        bUser.setBpwd(MD5Util.string2MD5(bUser.getBpwd()));
        if (userRepository.register(bUser) > 0){
            return "redirect:/user/login";
        }
        return "user/register";
    }

    @Override
    public String login(HttpServletRequest request, HttpServletResponse response,
                        BUser bUser, HttpSession session, Model model) {
        //md5加密
//        bUser.setBemail(MD5Util.string2MD5(bUser.getBemail()));
        bUser.setBpwd(MD5Util.string2MD5(bUser.getBpwd()));
        String code = (String) session.getAttribute("rand");
        if (!code.equalsIgnoreCase(bUser.getCode())){
            model.addAttribute("errorMessage", "验证码错误");
            return "user/login";
        }

        List<BUser> list = userRepository.login(bUser);
        if (list.size()>0) {
            //未登录之前选择购物车加入，登陆后自动加入
            if (session.getAttribute("preNum") !=null &&
                    session.getAttribute("GoodsId") !=null){
                cartRepository.putCart(list.get(0).getId(), (int) session.getAttribute("GoodsId"), (int) session.getAttribute("preNum"));
            }
//            System.out.println(list.get(0).getId());
            session.setAttribute("bUser",list.get(0));
            //设置cookie自动登录
            Cookie username = new Cookie("username",bUser.getBemail());
            Cookie password = new Cookie("password",bUser.getBpwd());
            username.setMaxAge(30 * 24 * 3600);
            password.setMaxAge(30 * 24 * 3600);
            response.addCookie(username);
            response.addCookie(password);
            System.out.println(123);
            return "redirect:/";
        }
        model.addAttribute("errorMessage", "用户名或者密码不正确");
        return "user/login";
    }
}
