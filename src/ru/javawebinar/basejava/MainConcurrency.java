package ru.javawebinar.basejava;

import java.util.concurrent.*;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        /*Object object1 = new Object();
        Object object2 = new Object();

        deadLock(object1, object2);
        deadLock(object2, object1);*/

        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
//                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
        //CompletionService completionService = new ExecutorCompletionService(executorService);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> submit = executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    inc();
                }
                latch.countDown();
                return 5;
            });
        }

        latch.await();
        System.out.println(counter);
    }

    private static synchronized void inc() {
        counter++;
    }

    private static void deadLock(Object object1, Object object2) {
        new Thread(() -> {
            synchronized (object1) {
                System.out.println("object " + object1 + " locked by thread " + Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + " waiting 1000ms");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() +" trying to lock " + object2);
                synchronized (object2) {
                    System.out.println("object " + object2 + " locked by thread " + Thread.currentThread().getName());
                }
            }
        }).start();
    }
}
