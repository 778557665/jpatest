package com.wzj.jpatest.controller;


import com.alibaba.fastjson.JSON;
import com.wzj.jpatest.bean.Goods;
import com.wzj.jpatest.service.IGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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


    public static void main(String[] args) {
        List<String> blackWordList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("E:\\QQ\\msg\\778557665\\FileRecv\\zz\\zz.txt");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String lineTxt;
            while ((lineTxt = br.readLine()) != null) {
                if (StringUtils.isNotBlank(lineTxt)) {
                    System.out.println(lineTxt);
                        String [] a2 = lineTxt.split("\t");
                        System.out.println(a2[3]);
                    blackWordList.add(lineTxt);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateOctoberWaitingData() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Calendar calendar = Calendar.getInstance();
        Integer initialDelay = 60 - calendar.get(Calendar.SECOND);
        executor.scheduleAtFixedRate(() -> {
            try {
//                logger.info("updateOctoberWaitingData start...");
                FileInputStream fis = new FileInputStream("/home/maijiabus/data/smsmass/zz.txt");
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String lineTxt;
                Date resultTime = new Date();
                while ((lineTxt = br.readLine()) != null) {
                    if (StringUtils.isNotBlank(lineTxt)) {
                        String [] data = lineTxt.split("\t");
                        String phone = data[0];
                        String statusDesc = data[2];
                        String taskId = data[3];
//                        List<MassSummary> list = smsMassSummaryService.findByTaskListId(taskId);
//                        for (MassSummary summary : list) {
//                            operatePhoneResult(summary, phone, resultTime, statusDesc);
//                        }
                    }
                }
//                logger.info("updateOctoberWaitingData end...");
            } catch (Exception e) {
//                logger.info("updateOctoberWaitingData exception..." + e);
                e.printStackTrace();
            }
        }, initialDelay, 24 * 3600 * 3, TimeUnit.SECONDS);
//        logger.info("updateOctoberWaitingData exit...");
    }


}
