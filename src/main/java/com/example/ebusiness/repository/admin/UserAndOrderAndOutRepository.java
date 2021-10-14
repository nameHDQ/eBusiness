package com.example.ebusiness.repository.admin;

import com.example.ebusiness.entity.BUser;
import com.example.ebusiness.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author hdq
 * @create 2021-10-08 11:11
 */
@Mapper
public interface UserAndOrderAndOutRepository {
    int selectAllOrder();

    List<Map<String, Object>> selectOrderByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    int selectAllUser();

    List<BUser> selectUserByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
}
