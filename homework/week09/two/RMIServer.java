package com.example.test.controller.homework.week09.two;

public class RMIServer {

    public static void main(String[] args) {
        try {
            // 1、注册Registry实例，绑定端口
            final Registry registry = LocateRegistry.createRegistry(9998);

            // 2.创建远程对象
            IUserService iUserService = new IUserServiceImpl();

            // 3、将远程对象注册到RMI服务器上
            registry.rebind("userService", iUserService);

            System.out.println("RMI服务端启动成功");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

