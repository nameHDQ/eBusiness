package com.example.ebusiness.service.before;

import org.springframework.ui.Model;

/**
 * @author hdq
 * @create 2021-10-09 11:24
 */
public interface IndexService {
    String search(Model model,String mykey);

    String index(Model model);

    String goodsDetail(Model model, Integer id);

    String selectGoodsByType(Model model,Integer id);
}
