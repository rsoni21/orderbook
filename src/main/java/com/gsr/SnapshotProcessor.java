package com.gsr;

public class SnapshotProcessor implements ResponseProcessor {

    @Override
    public void processMessage(OrderBook orderBook, OrderMessage orderMessage) {
        orderBook.addBuyOrders(orderMessage.bids);
        orderBook.addSellOrders(orderMessage.asks);
    }
}
