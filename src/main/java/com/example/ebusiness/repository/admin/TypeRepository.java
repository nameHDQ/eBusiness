package com.example.ebusiness.repository.admin;

import com.example.ebusiness.entity.Goods;
import com.example.ebusiness.entity.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hdq
 * @create 2021-10-07 17:04
 */
@Mapper
public interface TypeRepository {
    /**
     * 增加类型
     * @param goodsType
     */
    public void addTyoe(GoodsType goodsType);

    int selectAll();

    List<GoodsType> selectAllTypeByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    List<Goods> selectGoods(int id);

    void deleteType(int id);
}
