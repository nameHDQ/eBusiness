package com.example.ebusiness.service.admin.impl;

import com.example.ebusiness.entity.Goods;
import com.example.ebusiness.entity.GoodsType;
import com.example.ebusiness.repository.admin.TypeRepository;
import com.example.ebusiness.service.admin.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/**
 * @author hdq
 * @create 2021-10-07 17:03
 */
@Service
public class TypeServiceImpl implements TypeService {


    @Autowired
    private TypeRepository typeRepository;
    /**
     * 添加类型
     * @param goodsType
     * @return
     */
    @Override
    public String addType(GoodsType goodsType) {
        typeRepository.addTyoe(goodsType);
        return "forward:/type/selectAllTypeByPage?currentPage=1";
    }

    /**
     * 查询所有类型
     * @param model
     * @param currentPage
     * @return
     */
    @Override
    public String selectAllTypeByPage(Model model, int currentPage) {
        //总共多少个类型
        int totalCount = typeRepository.selectAll();
        //每页大小
        int pageSize = 2;
        int totalPage = (int) Math.ceil(totalCount*1.0/pageSize);
        List<GoodsType> allTypes =
                typeRepository.selectAllTypeByPage((currentPage-1)*pageSize,pageSize);
        model.addAttribute("allTypes",allTypes);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPage",totalPage);
        return "admin/selectGoodsType";
    }

    /**
     * 删除类型
     * @param id
     * @return
     */
    @Override
    public String delete(int id) {

            typeRepository.deleteType(id);
            //返回查询类型页面
            return "forward:/type/selectAllTypeByPage?currentPage=1";
    }

    @Override
    public String selectTypeById(Integer id) {
        List<Goods> list = typeRepository.selectGoods(id);
        //判断是否有物品依赖这个类型
        if (list.size()>0) {
            return "no";
        }else {
            //返回查询类型页面
            return "/type/deleteType?id="+id;
        }
    }
}
