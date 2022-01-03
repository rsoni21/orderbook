package com.gsr;

public class OrderBookApp {

    public static void main(String[] args) {
        System.out.println("Order Book App");
        Runtime.getRuntime().addShutdownHook(new ShutDownHook());

        OrderBookExecutor orderBookExecutor = new OrderBookExecutor();
        String productId = args[0];

        orderBookExecutor.startApplication(productId);
    }
}