package com.wzj.jpatest.service.impl;

import com.wzj.jpatest.bean.Goods;
import com.wzj.jpatest.repository.GoodsRepository;
import com.wzj.jpatest.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements IGoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public List<Goods> getGoodsList() {
        return goodsRepository.findAll();
    }
}
