package com.example.ebusiness.controller.admin;

import com.example.ebusiness.entity.GoodsType;
import com.example.ebusiness.service.admin.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hdq
 * @create 2021-10-07 16:58
 */
@RequestMapping("/type")
@Controller
public class TypeController extends AdminBaseController{
    @Autowired
    private TypeService typeService;
    @RequestMapping("/toAddType")
    public String toAddType(@ModelAttribute("goodsType")GoodsType goodsType){
        return "admin/addType";
    }

    @RequestMapping("/addType")
    public String addType(@ModelAttribute("goodsType")GoodsType goodsType){
        return typeService.addType(goodsType);
    }

    /**
     * 查询所有类型
     * @param model
     * @param currentPage
     * @return
     */
    @RequestMapping("/selectAllTypeByPage")
    public String selectAllTypeByPage(Model model , int currentPage){
        return typeService.selectAllTypeByPage(model,currentPage);
    }

    @RequestMapping("/deleteType")

    public String delete(int id){
        return typeService.delete(id);
    }

    @ResponseBody
    @RequestMapping("/selectTypeById")
    public String selectTypeById(int id){
        return typeService.selectTypeById(id);
    }
}
