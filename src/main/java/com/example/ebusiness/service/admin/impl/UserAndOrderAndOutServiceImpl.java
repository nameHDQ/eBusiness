package com.example.ebusiness.service.admin.impl;

import com.example.ebusiness.entity.BUser;
import com.example.ebusiness.entity.Order;
import com.example.ebusiness.repository.admin.UserAndOrderAndOutRepository;
import com.example.ebusiness.service.admin.UserAndOrderAndOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

/**
 * @author hdq
 * @create 2021-10-08 11:05
 */
@Service
public class UserAndOrderAndOutServiceImpl implements UserAndOrderAndOutService {

    @Autowired
    private UserAndOrderAndOutRepository userAndOrderAndOutRepository;
    @Override
    public String selectOrder(Model model, int currentPage) {
        int totalCount = userAndOrderAndOutRepository.selectAllOrder();
        int pageSize = 5;
        int totalPage = (int) Math.ceil(totalCount*1.0/pageSize);
        List<Map<String,Object>> orderByPage = userAndOrderAndOutRepository.selectOrderByPage((currentPage-1)*pageSize,pageSize);
        model.addAttribute("allOrders",orderByPage);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage", currentPage);
        return "admin/allOrder";
    }

    @Override
    public String selectUser(Model model, int currentPage) {
        int totalCount = userAndOrderAndOutRepository.selectAllUser();
        int pageSize = 5;
        int totalPage = (int) Math.ceil(totalCount*1.0/pageSize);
        List<BUser> allUsers = userAndOrderAndOutRepository.selectUserByPage((currentPage-1)*pageSize,pageSize);
        model.addAttribute("allUsers",allUsers);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPage",totalPage);
        return "admin/allUser";
    }
}
