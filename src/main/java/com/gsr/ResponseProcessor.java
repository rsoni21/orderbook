package com.gsr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseProcessor {
    private ObjectMapper objectMapper;
    public static final String SNAPSHOT = "snapshot";
    public static final String L_2_UPDATE = "l2update";
    public static final int ORDER_BOOK_DEPTH = 10;
    public static final String ERROR = "error";

    ResponseProcessor(){
        this.objectMapper = new ObjectMapper();
    }

    public void processMessage(String message) {
        try {
            synchronized (this){
                OrderMessage orderMessage = objectMapper.readValue(message, OrderMessage.class);
                OrderBook orderBook = OrderBook.getInstance();
                String type = orderMessage.getType();

                if(type.equals(SNAPSHOT)){
                    orderBook.addBuyOrders(orderMessage.bids);
                    orderBook.addSellOrders(orderMessage.asks);
                }else if(type.equals(L_2_UPDATE)){
                    if(orderMessage.getChanges() != null)
                        for(OrderUpdate orderUpdate : orderMessage.getChanges())
                            orderBook.updateOrderBook(orderUpdate);
                    orderBook.displayOrderBook(ORDER_BOOK_DEPTH);
                }else if(type.equals(ERROR)){
                    JsonNode jsonNode = objectMapper.readTree(message);
                    System.out.println(jsonNode.get("message")+ " Reason:"+ jsonNode.get("reason"));
                    System.exit(1);
                }
                Thread.sleep(350);
            }
        }
        catch (JsonProcessingException | InterruptedException e) {
            System.out.println("Error processing json "+message);
            e.printStackTrace();
        }
    }
}
