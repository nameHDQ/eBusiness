package com.example.ebusiness.service.admin;

import com.example.ebusiness.entity.Goods;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author hdq
 * @create 2021-10-07 16:30
 */
public interface GoodsService {
    String toAddGoods(Goods goods, Model model);

    String addGoods(Goods goods, HttpServletRequest request, String act) throws IOException;

    String selectAllGoodsByPage(Model model, int currentPage, String act);

    String detail(Model model, int id, String act);

    String delete(int id);

    String isExistGoods(int id);
}
