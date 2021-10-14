package com.example.ebusiness.service.admin;

import com.example.ebusiness.entity.GoodsType;
import org.springframework.ui.Model;

/**
 * @author hdq
 * @create 2021-10-07 17:02
 */
public interface TypeService {
    /**
     * 添加类型
     * @param goodsType
     * @return
     */
    public String addType(GoodsType goodsType);

    String selectAllTypeByPage(Model model, int currentPage);

    String delete(int id);

    String selectTypeById(Integer id);
}
