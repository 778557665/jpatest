package com.wzj.jpatest.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloDefineImp extends UnicastRemoteObject implements HelloDefine {

    private static final long serialVersionUID = 1L;

    public HelloDefineImp() throws RemoteException {
        super();
    }

    @Override
    public String helloWorld() throws RemoteException {
        return "Hello AlphaGo!";
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello" + name +"!";
    }
}
