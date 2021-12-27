package com.netcracker.application.service.model.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

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
}
