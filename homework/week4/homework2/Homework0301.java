package com.example.test.controller.homework.week4.homework2;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.LockSupport;

public class Homework0301 {
    private Thread main;
    private Result result;

    public Homework0301(Thread currentThread, Result resultObj) {
    }

    public void GetResult3(Thread main, Result result){
        this.main = main;
        this.result = result;
    }

    private static class Result{
        private volatile int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Result resultObj = new Result();
        Homework0301 work =new Homework0301(Thread.currentThread(),resultObj);
        Thread thread = new Thread((Runnable) work);
        String mainThreadName = Thread.currentThread().getName();
        String workThreadName = thread.getName();
        System.out.println(mainThreadName+" 启动 "+workThreadName);
        thread.start();
        LockSupport.park();
        int result = resultObj.getValue();
        System.out.println(mainThreadName+" 获得 "+workThreadName+" 结果 "+result);
    }

    public void run() {
        String name = Thread.currentThread().getName();
        try {
            Thread.sleep(1000);
            this.result.setValue(new Random().nextInt());
            System.out.println(name+" 干完了，结果"+result.getValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 执行完成，唤醒主线程
            LockSupport.unpark(main);
        }
    }
}
