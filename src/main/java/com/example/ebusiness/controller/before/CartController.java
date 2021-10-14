package com.example.ebusiness.controller.before;

import com.example.ebusiness.entity.BUser;
import com.example.ebusiness.entity.Goods;
import com.example.ebusiness.entity.Order;
import com.example.ebusiness.service.before.CartService;
import com.example.ebusiness.util.MyUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author hdq
 * @create 2021-10-09 14:15
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/focus")
    @ResponseBody
    public String focus(@RequestBody Goods goods, Model model, HttpSession session){
        System.out.println(goods.getId());
        return cartService.focus(goods.getId(),session);
    }

    @RequestMapping("/putCart")
    public String putCart(Goods goods,Model model, HttpSession session){

        return cartService.putCart(goods,model,session);
    }
    @RequestMapping("/deleteCart")
    public String deleteCart(Model model,HttpSession session,@Param("gid") Integer gid){
        return cartService.deleteCart(model,session,gid);
    }
    @RequestMapping("/selectCart")
    public String selectCart(Model model,HttpSession session,String act){
        return cartService.selectCart(model,session,act);
    }
    @RequestMapping("/clearCart")
    public String clearCart(HttpSession session){
        return cartService.clearCart(session);
    }


    @RequestMapping("/submitOrder")
    @ResponseBody
    public String submitOrder(Order order,HttpSession session,Model model){
        return cartService.submitOrder(order,session,model);
    }

    @RequestMapping("/myFocus")
    public String myFocus(Model model,HttpSession session){
        return cartService.myFocus(model,session);
    }

    @RequestMapping("/myOrder")
    public String myOder(Model model,HttpSession session){
        return cartService.myOrder(model,session);
    }

    @RequestMapping("/userInfo")
    public String userInfo(HttpSession session){
        return "user/userInfo";
    }
}
