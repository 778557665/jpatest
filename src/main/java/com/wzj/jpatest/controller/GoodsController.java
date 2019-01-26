package com.wzj.jpatest.controller;


import com.alibaba.fastjson.JSON;
import com.wzj.jpatest.bean.Goods;
import com.wzj.jpatest.service.IGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
    public String getGoodsList() throws Exception {
        List<Goods> list = goodsService.getGoodsList();
        return JSON.toJSONString(list);

    }
    //获取单个商品信息

    @RequestMapping("/mqProducer")
    public String rocketMqProducer() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        //Launch the instance.
        producer.start();
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
        return "ok";
    }

    @RequestMapping("/mqConsumer")
    public String rocketMqConsumer() throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");

        // Specify name server addresses.
        consumer.setNamesrvAddr("localhost:9876");

        // Subscribe one more more topics to consume.
        consumer.subscribe("TopicTest", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
        return "ok";
    }

    //获取商品分类

/*
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
    }*/


}
