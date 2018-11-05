package com.wzj.jpatest.controller;


import com.alibaba.fastjson.JSON;
import com.wzj.jpatest.bean.Goods;
import com.wzj.jpatest.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController = @Controller + @ResponseBody
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;
    //获取商品列表信息
    @RequestMapping("/list")
    public String getGoodsList(){
        List<Goods> goodsList = goodsService.getGoodsList();
        return JSON.toJSONString(goodsList);
    }
    //获取单个商品信息

    //获取商品分类


}
