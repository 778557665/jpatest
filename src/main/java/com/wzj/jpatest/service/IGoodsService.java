package com.wzj.jpatest.service;


import com.wzj.jpatest.bean.Goods;

import java.util.List;

public interface IGoodsService {

    //获取商品列表
    public List<Goods> getGoodsList();
}
