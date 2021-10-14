package com.example.ebusiness.service.before.impl;

import com.example.ebusiness.entity.Goods;
import com.example.ebusiness.entity.GoodsType;
import com.example.ebusiness.repository.before.IndexRepository;
import com.example.ebusiness.service.before.IndexService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author hdq
 * @create 2021-10-09 11:24
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexRepository indexRepository;

    /**
     * 初始化header所需要数据
     * @param model
     * @return
     */
    @Override
    public String search(Model model,String mykey) {
        //查询商品类型 返回前台
        List<GoodsType> goodsType = indexRepository.selectGoodsType();
        model.addAttribute("goodsType",goodsType);
        List<Goods> advertisementGoods = indexRepository.selectAdGoods();
        model.addAttribute("advertisementGoods",advertisementGoods);
        List<Goods> searchgoods =indexRepository.selectMykeyGoods(mykey);
        model.addAttribute("searchgoods",searchgoods);
        return "user/searchResult";
    }

    @Override
    public String index(Model model) {
        //header所需数据
        List<GoodsType> goodsType = indexRepository.selectGoodsType();
        model.addAttribute("goodsType",goodsType);
        List<Goods> advertisementGoods = indexRepository.selectAdGoods();
        model.addAttribute("advertisementGoods",advertisementGoods);
        //页面主提所需数据
        List<Goods> recommendGoods = indexRepository.selectRecommendGoods();
        model.addAttribute("recommendGoods",recommendGoods);
        List<Goods> lastedGoods = indexRepository.selectLastGoods();
        model.addAttribute("lastedGoods",lastedGoods);
        return "user/index";
    }

    /**
     * 点击商品图片，进入商品信息页面
     * @param model
     * @param id
     * @return
     */
    @Override
    public String goodsDetail(Model model, Integer id) {
        //header所需数据
        List<GoodsType> goodsType = indexRepository.selectGoodsType();
        model.addAttribute("goodsType",goodsType);
        List<Goods> advertisementGoods = indexRepository.selectAdGoods();
        model.addAttribute("advertisementGoods",advertisementGoods);
        //查询特定的一个商品信息
        Goods goods = indexRepository.selectGoodsById(id);
        model.addAttribute("goods",goods);
        return "user/goodsDetail";
    }

    @Override
    public String selectGoodsByType(Model model,  Integer id) {
        //header所需数据
        System.out.println(id);
        List<GoodsType> goodsType = indexRepository.selectGoodsType();
        model.addAttribute("goodsType",goodsType);
        List<Goods> advertisementGoods = indexRepository.selectAdGoods();
        model.addAttribute("advertisementGoods",advertisementGoods);
        //类型物品显示
        List<Goods> searchgoods = indexRepository.selectGoodsByType(id);
        model.addAttribute("searchgoods", searchgoods);
        return "user/searchResult";
    }
}
