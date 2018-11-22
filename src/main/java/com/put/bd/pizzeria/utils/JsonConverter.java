package com.put.bd.pizzeria.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;

public class JsonConverter {

    static Gson gson = new GsonBuilder().setLenient().create();
    static StringReader stringReader;
    static ObjectMapper objectMapper = new ObjectMapper();

    public static Object jsonToClassObject(String jsonStr, Class c) throws IOException {
        String trimmed = trim(jsonStr);
        System.out.println("trimmed = " + trimmed);
        stringReader = new StringReader(trimmed);
        return gson.fromJson(new JsonReader(stringReader), c);
    }

    private static String trim(String jsonStr) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        Iterator<JsonNode> it = jsonNode.elements();
        return it.next().toString();
    }

}
