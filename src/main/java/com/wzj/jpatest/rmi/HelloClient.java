package com.wzj.jpatest.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class HelloClient {

    public HelloDefine hello;

    public void client() throws MalformedURLException, RemoteException, NotBoundException {
        //在RMI注册表中查找指定对象
        hello = (HelloDefine) Naming.lookup("rmi://localhost:8888/Hello");
        //调用远程对象方法
        System.out.println("client:");
        System.out.println(hello.helloWorld());
        System.out.println(hello.sayHello("神之一手"));
    }
}
