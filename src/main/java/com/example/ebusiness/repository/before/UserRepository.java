package com.example.ebusiness.repository.before;

import com.example.ebusiness.entity.BUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hdq
 * @create 2021-10-09 12:53
 */
@Mapper
public interface UserRepository {

    List<BUser> selectUser(BUser bUser);

    int register(BUser bUser);

    List<BUser> login(BUser bUser);
}
