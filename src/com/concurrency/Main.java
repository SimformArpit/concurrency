package com.concurrency;

import com.concurrency.billing.BillingCounter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    public static void main(String[] args) {
        BillingCounter customer1AtQueue1 = new BillingCounter(1, 7);
        BillingCounter customer2AtQueue1 = new BillingCounter(1, 15);
        BillingCounter customer3AtQueue2 = new BillingCounter(2, 3);
        BillingCounter customer4AtQueue2 = new BillingCounter(2, 7);
        BillingCounter customer5AtQueue2 = new BillingCounter(2, 9);
        ScheduledExecutorService customerQueueExecutors = Executors.newScheduledThreadPool(10);
        System.out.println("Dmart portal:-");
        System.out.println("-----------------------------------------------");
        customerQueueExecutors.submit(customer1AtQueue1);
        customerQueueExecutors.submit(customer2AtQueue1);
        customerQueueExecutors.submit(customer3AtQueue2);
        customerQueueExecutors.submit(customer4AtQueue2);
        customerQueueExecutors.submit(customer5AtQueue2);
        customerQueueExecutors.shutdown();
        for (; ; ) {
            if (customerQueueExecutors.isTerminated()) {
                break;
            } else {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("-----------------------------------------------\n today's total no of customers visited:" + BillingCounter.getCustomerId());
    }
}