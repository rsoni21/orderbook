package com.gsr;

public class ErrorProcessor implements ResponseProcessor {

    @Override
    public void processMessage(OrderBook orderBook, OrderMessage orderMessage) {
        System.out.println(orderMessage.getMessage()+ " Reason:"+ orderMessage.getReason());
        System.exit(1);
    }
}
