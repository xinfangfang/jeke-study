package com.example.test.controller.homework.week4.homework2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Homework0303 implements Runnable {
    private CyclicBarrier cyclicBarrier;
    private int a;

    public void CycClass(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public Homework0303(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }


    @Override
    public void run() {
        a = Function.sum();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public int getA() {
        return this.a;
    }

    public static class Function {


        public static int sum() {
            return fibo(36);
        }

        private static int fibo(int a) {
            if (a < 2) {
                return 1;
            }
            return fibo(a - 1) + fibo(a - 2);
        }
    }
}