package ru.javawebinar.basejava;

public class MainConcurrency {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();

        deadLock(object1, object2);
        deadLock(object2, object1);
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
