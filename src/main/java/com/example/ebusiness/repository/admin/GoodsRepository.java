package com.example.ebusiness.repository.admin;

import com.example.ebusiness.entity.Goods;
import com.example.ebusiness.entity.GoodsType;
import com.example.ebusiness.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author hdq
 * @create 2021-10-07 16:29
 */
@Mapper
public interface GoodsRepository {
    /**
     * 查询所以的物品类型
     * @return
     */
    List<GoodsType> selectAllGoodsType();

    int addGoods(Goods goods);

//    int updateGoods(Goods goods);

    int selectAllGoods();

    List<Goods> selectAllGoodsByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    Goods selectGoodById(int id);

    int updateGoods(Goods goods);

    List<Map<String, Object>> selectFocusGoods(Integer id);
    List<Map<String, Object>> selectCartGoods(Integer id);
    List<Map<String, Object>> selectOrderGoods(Integer id);

    void deleteGoodsById(Integer id);
}
