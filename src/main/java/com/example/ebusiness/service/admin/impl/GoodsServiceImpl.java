package com.example.ebusiness.service.admin.impl;

import com.example.ebusiness.entity.Goods;
import com.example.ebusiness.entity.GoodsType;
import com.example.ebusiness.repository.admin.GoodsRepository;
import com.example.ebusiness.service.admin.GoodsService;
import com.example.ebusiness.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author hdq
 * @create 2021-10-07 16:30
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public String toAddGoods(Goods goods, Model model) {
        model.addAttribute("goodsType", goodsRepository.selectAllGoodsType());
        return "admin/addGoods";
    }

    @Override
    public String addGoods(Goods goods, HttpServletRequest request, String act) throws IOException {
        MultipartFile myfile = goods.getFileName();
        if (!myfile.isEmpty()){
//            String path = request.getServletContext().getRealPath("/images/");


            String path = "D:\\IDEA_workspace\\springboot_learning\\eBusiness\\src\\main\\resources\\static\\images";

            String originalFilename = myfile.getOriginalFilename();
            String newFileName = MyUtil.getNewFileName(originalFilename);
            File file = new File(path + File.separator + newFileName);
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            myfile.transferTo(file);
            goods.setGpicture(newFileName);
        }
        if("add".equals(act)) {
            int n = goodsRepository.addGoods(goods);
            if(n > 0)//成功
                return "redirect:/goods/selectAllGoodsByPage?currentPage=1&act=select";
            //失败
            return "admin/addGoods";
        }else {//修改
            int n = goodsRepository.updateGoods(goods);
            if(n > 0)//成功
                return "redirect:/goods/selectAllGoodsByPage?currentPage=1&act=updateSelect";
            //失败
            return "admin/UpdateAGoods";
        }
    }

    @Override
    public String selectAllGoodsByPage(Model model, int currentPage, String act) {
        int totalCount = goodsRepository.selectAllGoods();
        System.out.println(1);
        int pageSize = 5;
        int totalPage = (int) Math.ceil(totalCount*1.0/pageSize);
        System.out.println(2);
        List<Goods> allGoods = goodsRepository.selectAllGoodsByPage((currentPage-1)*pageSize,pageSize);
        System.out.println(3);
        model.addAttribute("allGoods",allGoods);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("act", act);
        return "admin/selectGoods";
    }

    @Override
    public String detail(Model model, int id, String act) {
        Goods good =goodsRepository.selectGoodById(id);
        model.addAttribute("goods", good);
        if ("detail".equals(act)){
            return "admin/detail";
        }else {
            List<GoodsType> goodsType = goodsRepository.selectAllGoodsType();
            model.addAttribute("goodsType", goodsType);
            return "admin/updateAGoods";
        }

    }

    @Override
    public String delete(int id) {

            goodsRepository.deleteGoodsById(id);
            return "forward:/goods/selectAllGoodsByPage?currentPage=1&act=deleteSelect";
    }

    @Override
    public String isExistGoods(int id) {
        if (goodsRepository.selectOrderGoods(id).size() > 0
                || goodsRepository.selectFocusGoods(id).size() > 0
                || goodsRepository.selectCartGoods(id).size() > 0){
            return "no";
        }else {
            return "/goods/delete?id="+id;
        }
    }
}
