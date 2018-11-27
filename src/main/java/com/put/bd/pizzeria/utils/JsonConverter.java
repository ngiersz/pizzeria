package com.put.bd.pizzeria.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.put.bd.pizzeria.domain.Client;
import com.put.bd.pizzeria.domain.DishMenu;
import com.put.bd.pizzeria.domain.OrderedDish;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

public class JsonConverter {

    static Gson gson = new GsonBuilder().setLenient().create();
    static StringReader stringReader;
    static ObjectMapper objectMapper = new ObjectMapper();

    public static String objectToJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String objectsListToJson(List<Object> list, String name) {
        String result = "{\"" + name + "\":\n";
        for (Object obj : list) {
            result += JsonConverter.objectToJson(obj) + ",\n";
        }
        result = result.substring(0, result.length()-2) + "\n}";
        return result;
    }

    public static Object jsonToClassObject(String jsonStr, Class c) {
        String trimmed = trim(jsonStr);
        System.out.println("trimmed = " + trimmed);
        stringReader = new StringReader(trimmed);
        return gson.fromJson(new JsonReader(stringReader), c);
    }

    public static List<Object> jsonListToClassObjectList(String jsonStr, Class c) {
        String trimmed = trim(jsonStr);
        stringReader = new StringReader(trimmed);
        System.out.println(trimmed);
//        return gson.fromJson(new JsonReader(stringReader), c);
        try {
            return objectMapper.readValue(trimmed, new TypeReference<List<DishMenu>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String trim(String jsonStr) {
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Iterator<JsonNode> it = jsonNode.elements();
        return it.next().toString();
    }

}
