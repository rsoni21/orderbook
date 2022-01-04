package com.gsr;

import static com.gsr.ResponseHandler.ORDER_BOOK_DEPTH;

public class UpdateProcessor implements ResponseProcessor {

    @Override
    public void processMessage(OrderBook orderBook, OrderMessage orderMessage) {
        if(orderMessage.getChanges() != null)
            for(OrderUpdate orderUpdate : orderMessage.getChanges())
                orderBook.updateOrderBook(orderUpdate);
        orderBook.displayOrderBook(ORDER_BOOK_DEPTH);
    }
}
