package com.zylear.phone.grab.grab.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 28444
 * @date 2017/10/25.
 */
public class ThreadTest {


    private void one() {
        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    sleep(3000);
                    System.out.println("something");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //    service.submit(new Thread());

        // thread.start();
    }


    private void process() {

        SharedClass sharedClass = new SharedClass();

        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        for (int i = 0; i < 1000; i++) {
            executorService.submit(new InnerRannable(sharedClass));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) ;
        System.out.println("end: shared1:" + sharedClass.getSharedVariable() +
                "  shared2:" + sharedClass.getSharedVariable2());

    }

    public static void main(String[] args) {
        new ThreadTest().process();
//        new ThreadTest().one();
    }

    private class SharedClass {

        private Integer sharedVariable = 0;
        private Integer sharedVariable2 = 0;
        private Object lock = new Object();

        public Integer incrAndGet() {
            int temp = sharedVariable;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sharedVariable = temp + 1;
//            synchronized (SharedClass.class) {
            synchronized (lock) {
                int temp2 = sharedVariable2;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sharedVariable2 = temp2 + 1;
            }


            return sharedVariable;
        }


        public Integer getSharedVariable() {
            return sharedVariable;
        }

        public Integer getSharedVariable2() {
            return sharedVariable2;
        }
    }

    private class InnerRannable implements Runnable {
        private SharedClass sharedClass;

        InnerRannable(SharedClass sharedClass) {
            this.sharedClass = sharedClass;
        }

        @Override
        public void run() {
            System.out.println(sharedClass.incrAndGet());
        }
    }
}
