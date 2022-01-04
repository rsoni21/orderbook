package com.gsr;

/*
 * Handle incoming responses from web socket feed
 */
public interface ResponseProcessor {
    public void processMessage(OrderBook orderBook, OrderMessage orderMessage);
}
