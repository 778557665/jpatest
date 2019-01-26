package com.wzj.jpatest.service.impl;

import com.wzj.jpatest.bean.Goods;
import com.wzj.jpatest.repository.GoodsRepository;
import com.wzj.jpatest.service.IGoodsService;
import com.wzj.jpatest.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodServiceImpl implements IGoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private ITestService testService;

    @Override
    public List<Goods> getGoodsList() {
        return goodsRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testTran() throws Exception {
        Goods goods = new Goods();
        goods.setGoodsName("测试");
        goods.setClassify(1);
        goods.setGoodsImage("www.baidu.com");
        goods.setId(4L);
        goodsRepository.save(goods);
        testService.test1();
//        throw new Exception("这是一个错误");
    }
}
