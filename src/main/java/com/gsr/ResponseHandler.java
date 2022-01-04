package com.gsr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseHandler {

    private ObjectMapper objectMapper;
    public static final String SNAPSHOT = "snapshot";
    public static final String L_2_UPDATE = "l2update";
    public static final int ORDER_BOOK_DEPTH = 10;
    public static final String ERROR = "error";

    ResponseHandler(){
        this.objectMapper = new ObjectMapper();
    }

    public void process(String message) {
        try {
            synchronized (this){
                OrderMessage orderMessage = objectMapper.readValue(message, OrderMessage.class);
                OrderBook orderBook = OrderBook.getInstance();
                String type = orderMessage.getType();
                ResponseProcessor responseProcessor;

                if(SNAPSHOT.equals(type)){
                    responseProcessor = new SnapshotProcessor();
                }else if(L_2_UPDATE.equals(type)){
                    responseProcessor = new UpdateProcessor();
                }else if(ERROR.equals(type)){
                    responseProcessor = new ErrorProcessor();
                }else{
                    responseProcessor = new DefaultProcessor();
                }

                responseProcessor.processMessage(orderBook, orderMessage);
                Thread.sleep(350);
            }
        }
        catch (JsonProcessingException | InterruptedException e) {
            System.out.println("Error processing json "+message);
            e.printStackTrace();
        }
    }
}
