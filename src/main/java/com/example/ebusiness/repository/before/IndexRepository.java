package com.example.ebusiness.repository.before;

import com.example.ebusiness.entity.Goods;
import com.example.ebusiness.entity.GoodsType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hdq
 * @create 2021-10-09 11:26
 */
@Mapper
public interface IndexRepository {
    List<GoodsType> selectGoodsType();

    List<Goods> selectAdGoods();

    List<Goods> selectMykeyGoods(String mykey);

    List<Goods> selectRecommendGoods();

    List<Goods> selectLastGoods();

    Goods selectGoodsById(Integer id);

    List<Goods> selectGoodsByType(Integer id);
}
