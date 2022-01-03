package com.gsr;

public class OrderBookApp {

    public static void main(String[] args) {
        System.out.println("Order Book App");
        Runtime.getRuntime().addShutdownHook(new ShutDownHook());

        OrderBookExecutor orderBookExecutor = new OrderBookExecutor();

        if(args == null || args.length == 0){
            System.out.println("Please pass product ID argument. Refer https://api.exchange.coinbase.com/products for supported products");
            System.exit(1);
        }

        String productId = args[0];
        orderBookExecutor.startApplication(productId);
    }
}