package com.example.ebusiness.controller.before;

import com.example.ebusiness.service.before.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hdq
 * @create 2021-10-09 11:00
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 初始化header所需要数据
     * @param model
     * @return
     */
    @RequestMapping("/search")
    public String search(Model model,String mykey){
        return indexService.search(model,mykey);
    }

    @RequestMapping("/")
    public String index(Model model){
        return indexService.index(model);
    }

    @RequestMapping("/goodsDetail")
    public String goodsDetail(Model model,Integer id){
        return indexService.goodsDetail(model,id);
    }

    /**
     * 首页按照类型查找物品
     * @param id
     * @return
     */
    @RequestMapping("/selectGoodsByType")
    public String selectGoodsByType(Model model,Integer id){
        return indexService.selectGoodsByType(model,id);
    }

}
