package com.example.ebusiness.service.before.impl;

import com.example.ebusiness.entity.BUser;
import com.example.ebusiness.entity.Goods;
import com.example.ebusiness.entity.GoodsType;
import com.example.ebusiness.entity.Order;
import com.example.ebusiness.repository.before.CartRepository;
import com.example.ebusiness.repository.before.IndexRepository;
import com.example.ebusiness.service.before.CartService;
import com.example.ebusiness.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author hdq
 * @create 2021-10-09 14:27
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IndexRepository indexRepository;

    /**
     *
     * @param id 物品id
     * @param session
     * @return
     */
    @Override
    public String focus(Integer id, HttpSession session) {
        BUser user = MyUtil.getUser(session);
        Integer uid = user.getId();
        System.out.println(uid);
        List<Map<String,Object>> list = cartRepository.isFoucs(uid,id);
        if (list.size() > 0){
            return "no";
        }else {
            cartRepository.focus(uid,id);
            return "ok";
        }
    }

    @Override
    public String putCart(Goods goods, Model model, HttpSession session) {
        if (session.getAttribute("bUser") == null){
            session.setAttribute("preNum", goods.getBuyNumber());
            session.setAttribute("GoodsId", goods.getId());
            return "forward:/user/toLogin";
        }
        Integer uid = MyUtil.getUser(session).getId();
//        System.out.println(uid);
        ;
        if (cartRepository.isPutCart(uid,goods.getId()).size() > 0){
            System.out.println(cartRepository.isPutCart(uid,goods.getId()).size());
            cartRepository.updateCart(uid,goods.getId(),goods.getBuyNumber());
        }else {
            cartRepository.putCart(uid,goods.getId(),goods.getBuyNumber());
        }
        return "forward:/cart/selectCart";
    }

    @Override
    public String selectCart(Model model, HttpSession session, String act) {
        Integer uid = MyUtil.getUser(session).getId();
        List<Map<String,Object>> list= cartRepository.selectCart(uid);
        double sum = 0;
        for (Map<String, Object> map : list) {
            sum+=(double) map.get("smallsum");
        }
        model.addAttribute("total",sum);
        model.addAttribute("cartlist", list);
        //header所需数据
        List<GoodsType> goodsType = indexRepository.selectGoodsType();
        model.addAttribute("goodsType",goodsType);
        List<Goods> advertisementGoods = indexRepository.selectAdGoods();
        model.addAttribute("advertisementGoods",advertisementGoods);
        if ("toCount".equals(act)){
            return "user/count";
        }
        return "user/cart";
    }

    @Override
    public String deleteCart(Model model, HttpSession session, Integer gid) {
        Integer uid = MyUtil.getUser(session).getId();
        cartRepository.deleteCart(uid,gid);
        return "forward:/cart/selectCart";
    }

    @Override
    public String clearCart(HttpSession session) {
        Integer uid = MyUtil.getUser(session).getId();
        cartRepository.clearCart(uid);
        return "forward:/cart/selectCart";
    }

    @Override
    public String submitOrder(Order order, HttpSession session, Model model) {

        return order.toString();
    }

    @Override
    public String myFocus(Model model, HttpSession session) {
        Integer uid = MyUtil.getUser(session).getId();
        //header所需数据
        List<GoodsType> goodsType = indexRepository.selectGoodsType();
        model.addAttribute("goodsType",goodsType);
        List<Goods> advertisementGoods = indexRepository.selectAdGoods();
        model.addAttribute("advertisementGoods",advertisementGoods);
        List<Map<String,Object>> myFocus = cartRepository.myFocus(uid);
        model.addAttribute("myFocus",myFocus);
        return "user/myFocus";
    }

    @Override
    public String myOrder(Model model, HttpSession session) {
        Integer uid = MyUtil.getUser(session).getId();
        System.out.println(uid);
        //header所需数据
        List<GoodsType> goodsType = indexRepository.selectGoodsType();
        model.addAttribute("goodsType",goodsType);
        List<Goods> advertisementGoods = indexRepository.selectAdGoods();
        model.addAttribute("advertisementGoods",advertisementGoods);
        List<Order> myOrder = cartRepository.myOrder(uid);
        model.addAttribute("myOrder", myOrder);
        return "user/myOrder";
    }
}
