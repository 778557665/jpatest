package com.wzj.jpatest.rmi;

import com.wzj.jpatest.JpatestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpatestApplication.class)
@WebAppConfiguration
public class RMITest {

    @Test
    public void testServer() throws RemoteException, MalformedURLException, AlreadyBoundException {
        HelloServer server = new HelloServer();
        server.server();
        while(true){
        }
    }

    @Test
    public void testClient() throws MalformedURLException, RemoteException, NotBoundException {
        HelloClient client = new HelloClient();
        client.client();
    }
}
