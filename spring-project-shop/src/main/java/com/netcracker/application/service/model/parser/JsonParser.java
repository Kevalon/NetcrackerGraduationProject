package com.netcracker.application.service.model.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.application.service.model.entity.Product;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {
    public static List<String> parse(List<?> parseMe) {
        List<String> res = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Object obj : parseMe) {
            try {
                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
                res.add(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static String parse(Object parseMe) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(parseMe);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Map<BigInteger, String> parseProducts(List<Product> products) {
        Map<BigInteger, String> result = new HashMap<>();
        for (Product product : products) {
            result.put(product.getId(), parse(product));
        }
        return result;
    }
}
