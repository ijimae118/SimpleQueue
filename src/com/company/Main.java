package com.company;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Loader implements Runnable {
    public void run() {
        Queue<Integer> numbers = new LinkedList<>();
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        ses.schedule(new Runnable() {
            @Override
            public void run() {
                Main.producer(numbers);
            }
        }, 1, TimeUnit.SECONDS);

        ses.schedule(new Runnable() {
            @Override
            public void run() {
                Main.consumer(numbers);
            }
        }, 2, TimeUnit.SECONDS);
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {
            while (true){
                Thread t = new Thread(new Loader());
                t.start();
                Thread.sleep(500);
            }
    }

    public static void producer(Queue<Integer> number) {
        number.add(new Date().hashCode());
    }

    public static void consumer(Queue<Integer> number) {
        for (Integer n : number){
            System.out.println(n);
        }
    }
}
