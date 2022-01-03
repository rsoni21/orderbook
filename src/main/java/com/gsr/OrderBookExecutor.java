package com.gsr;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OrderBookExecutor {
    static final String WEBSOCKET_URL = "wss://ws-feed.exchange.coinbase.com";

    void startApplication(String productId){
        establishWebSocketConnection(productId);
    }

    private void establishWebSocketConnection(String productId) {
        WebsocketFeed websocketFeed = new WebsocketFeed(WEBSOCKET_URL, productId);
        websocketFeed.connect();
    }

}
