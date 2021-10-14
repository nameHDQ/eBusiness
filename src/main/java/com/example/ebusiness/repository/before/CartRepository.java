package com.example.ebusiness.repository.before;

import com.example.ebusiness.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author hdq
 * @create 2021-10-09 14:28
 */
@Mapper
public interface CartRepository {

    List<Map<String, Object>> isFoucs(@Param("uid") Integer uid, @Param("id") Integer id);

    void focus(@Param("uid") Integer uid, @Param("id") Integer id);

    List<Map<String, Object>> isPutCart(@Param("uid") Integer uid, @Param("id") Integer id);

    void updateCart(@Param("uid") Integer uid, @Param("id") int id, @Param("buyNumber") int buyNumber);

    void putCart(@Param("uid") Integer uid, @Param("id") int id, @Param("buyNumber") int buyNumber);

    List<Map<String, Object>> selectCart(Integer uid);

    void deleteCart(@Param("uid") Integer uid, @Param("id") Integer id);

    void clearCart(Integer uid);

    List<Map<String, Object>> myFocus(Integer uid);

    List<Order> myOrder(Integer uid);
}
