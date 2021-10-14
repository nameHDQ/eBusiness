package com.example.ebusiness.controller.admin;

import com.example.ebusiness.entity.Goods;
import com.example.ebusiness.service.admin.GoodsService;
import com.example.ebusiness.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author hdq
 * @create 2021-10-07 16:25
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends AdminBaseController{
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/toAddGoods")
    public String toAddGoods(@ModelAttribute("goods")Goods goods, Model model){
        goods.setIsAdvertisement(0);
        goods.setIsRecommend(1);
        return goodsService.toAddGoods(goods,model);
    }

    @RequestMapping("/addGoods")
    public String addGoods(Goods goods, HttpServletRequest request ,String act) throws IOException {

        return goodsService.addGoods(goods,request,act);
    }

    @RequestMapping("/selectAllGoodsByPage")
    public String selectAllGoodsByPage(Model model,int currentPage,String act){
        return goodsService.selectAllGoodsByPage(model,currentPage,act);
    }

    @RequestMapping("/detail")
    public String detail(Model model,int id,String act){
        return goodsService.detail(model,id,act);
    }

    @RequestMapping("/delete")
    public String delete(int id){
        return goodsService.delete(id);
    }
    @ResponseBody
    @RequestMapping("/isExistGoods")
    public String isExistGoods(int id){
        return goodsService.isExistGoods(id);
    }



}
