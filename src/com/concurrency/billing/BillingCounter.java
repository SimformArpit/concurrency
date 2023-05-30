package com.concurrency.billing;

public class BillingCounter implements Runnable {

    private int counterNo, purchasedItemNo;

    public static int getCustomerId() {
        return customerId;
    }

    private static int customerId = 0;

    public BillingCounter(int counterNo, int purchasedItemNo) {
        this.counterNo = counterNo;
        this.purchasedItemNo = purchasedItemNo;
    }


    @Override
    public void run() {

        synchronized (BillingCounter.class) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            customerId++;
            System.out.println("Counter no:" + counterNo + " ,Purchased item count:" + purchasedItemNo);
            if (purchasedItemNo >= 10) {
                System.out.println("Customer No:" + customerId + " = Eligible for 100off on next purchase coupon");
                System.out.println("We have sent the coupon on your registered number");
            } else {
                System.out.println("Customer No:" + customerId + " = Not Eligible for 100off on next purchase coupon");
            }
            System.out.println("Thank you for visiting us\n\n");

        }
    }
}
