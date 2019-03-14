package com.wzj.jpatest.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloDefine extends Remote {

    public String helloWorld() throws RemoteException;

    public String sayHello(String name) throws RemoteException;
}
