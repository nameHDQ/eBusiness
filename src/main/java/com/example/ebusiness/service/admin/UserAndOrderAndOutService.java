package com.example.ebusiness.service.admin;

import org.springframework.ui.Model;

/**
 * @author hdq
 * @create 2021-10-08 11:04
 */
public interface UserAndOrderAndOutService {
    String selectOrder(Model model, int currentPage);

    String selectUser(Model model, int currentPage);
}
