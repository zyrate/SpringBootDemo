package com.zyr.webapi;

import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class MyService {
    int a = 1;
    CountDownLatch latch = new CountDownLatch(3);
    Thread firstThread = null;

    Object lock = new Object();
    public int test(){
        try {
            synchronized (lock) {
                firstThread = Thread.currentThread();
                lock.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(a++);
        return a;
    }
    public void countdown(){
        latch.countDown();
    }
    public void breakwait(){
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
