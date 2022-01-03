package com.gsr;

public class ShutDownHook extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(200);
            System.out.println("Shutting down...");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.exit(1);
        }
    }
}
