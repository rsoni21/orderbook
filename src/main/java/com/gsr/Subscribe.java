package com.gsr;

public class Subscribe {

    public static String getMessage(String product_id){
        return "{\n" +
                "    \"type\": \"subscribe\",\n" +
                "    \"product_ids\": [\n" +
                "        \""+product_id+"\"\n" +
                "        ],\n" +
                "    \"channels\": [\n" +
                "        \"level2\"\n" +
                "    ]\n" +
                "}";
    }
}
