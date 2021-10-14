package com.example.ebusiness.repository.admin;

import com.example.ebusiness.entity.AUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
    @author hdq
    @create 2021-10-07 15:49
*/
@Mapper
public interface AdminRepository {
    /**
     * 登录用户 查询一个
     * @param aUser
     * @return
     */
    public List<AUser> login(AUser aUser);
}
