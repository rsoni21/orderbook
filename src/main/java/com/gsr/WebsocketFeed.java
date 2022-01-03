package com.gsr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

public class WebsocketFeed extends WebSocketClient{

    private final String productId;

    public WebsocketFeed(String websocketUrl,
                         String productId) {
        super(URI.create(websocketUrl));
        this.productId = productId;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        if(handshakedata.getHttpStatus() != 101){
            System.out.println("Failed to establish connection "+handshakedata.getHttpStatusMessage());
        }

        this.subscribe(Subscribe.getMessage(productId));
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closing websocket connection" + reason);
    }

    @Override
    public void onError(Exception e) {
        System.out.println("websocket error" + e.getMessage());
    }

    @Override
    public void onMessage(String message) {
        ResponseProcessor responseProcessor = new ResponseProcessor();
        responseProcessor.processMessage(message);
    }

    public void subscribe(String msg) {
        try{
            this.send(msg);
        }catch(WebsocketNotConnectedException e){
            System.out.println("Subscription failed. Please enter valid product ID");
            throw new RuntimeException();
        }
    }

}
