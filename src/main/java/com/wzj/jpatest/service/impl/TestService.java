package com.wzj.jpatest.service.impl;

import com.wzj.jpatest.bean.Goods;
import com.wzj.jpatest.repository.GoodsRepository;
import com.wzj.jpatest.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService implements ITestService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public void test1() throws Exception {
        Goods goods = goodsRepository.getOne(1L);
        goods.setGoodsName("test");
        goodsRepository.save(goods);
        throw new Exception("这也是一个错误");
    }
}
