package com.wzj.jpatest.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class HelloServer {

    HelloDefine hello;

    public void server() throws RemoteException, MalformedURLException, AlreadyBoundException {
        hello = new HelloDefineImp();

        //远程对象注册表实例
        LocateRegistry.createRegistry(8888);
        //把远程对象注册到RMI注册服务器上
        Naming.bind("rmi://localhost:8888/Hello", hello);
        System.out.println("server:对象绑定成功！");
    }
}
