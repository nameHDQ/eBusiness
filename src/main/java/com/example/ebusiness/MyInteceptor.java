package com.example.ebusiness;

import com.example.ebusiness.entity.BUser;
import com.example.ebusiness.repository.before.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author hdq
 * @create 2021-10-13 13:51
 */
@Service
public class MyInteceptor implements HandlerInterceptor {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //先判断，当前用户是否已经登录，已经登录就放行
        //session中没有user值，然后再根据cookie里面的token值与用户进行匹配
        BUser bUser = (BUser) request.getSession().getAttribute("bUser");
        if (bUser == null) return true;

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            String username =null;
            String password =null;
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                }
                if ("password".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
                if (username != null && password != null) {
                    BUser temp = new BUser();
                    temp.setBemail(username);
                    temp.setBpwd(password);
                    List<BUser> bUsers = userRepository.selectUser(temp);
                    if (bUsers.size()>0) {
                        request.getSession().setAttribute("bUser", bUsers.get(0));
                    }
                    System.out.println(request.getSession().getAttribute("bUser"));
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
