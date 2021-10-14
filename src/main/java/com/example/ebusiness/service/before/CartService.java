package com.example.ebusiness.service.before;

import com.example.ebusiness.entity.Goods;
import com.example.ebusiness.entity.Order;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * @author hdq
 * @create 2021-10-09 14:27
 */
public interface CartService {

    String focus(Integer id, HttpSession session);

    String putCart(Goods goods, Model model, HttpSession session);

    String selectCart(Model model, HttpSession session, String act);

    String deleteCart(Model model, HttpSession session, Integer gid);

    String clearCart(HttpSession session);

    String submitOrder(Order order, HttpSession session, Model model);

    String myFocus(Model model, HttpSession session);

    String myOrder(Model model, HttpSession session);
}
