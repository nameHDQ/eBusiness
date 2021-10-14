package com.example.ebusiness.controller.before;

import com.example.ebusiness.entity.BUser;
import com.example.ebusiness.service.before.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author hdq
 * @create 2021-10-09 12:33
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toRegister")
    public String toRegister(@ModelAttribute("bUser") BUser bUser){
        return "user/register";
    }

    /**
     * 查看用户名是否可用
     * @return
     */
    @RequestMapping("/isUse")
    @ResponseBody
    public String isUse(@RequestBody BUser bUser){
        if (userService.hasUser(bUser).size() > 0){
            return "no";
        }else {
            return "ok";
        }
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute("bUser") @Validated BUser bUser,BindingResult rs){
        if (rs.hasErrors()){
            return "user/register";
        }
        return userService.register(bUser);
    }

    @RequestMapping("/toLogin")
    public String toLogin(@ModelAttribute("bUser") BUser bUser){
        return "user/login";
    }
    @RequestMapping("/login")
    public String login(@ModelAttribute("bUser") @Validated BUser bUser,
                        BindingResult bindingResult, HttpSession session,
                        Model model, HttpServletRequest request, HttpServletResponse response){
        if (bindingResult.hasErrors()){//验证失败

            return "user/login";
        }
        return userService.login(request,response,bUser,session,model);
    }
}
