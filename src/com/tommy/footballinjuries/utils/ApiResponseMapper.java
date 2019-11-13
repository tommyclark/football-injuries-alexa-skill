package com.tommy.footballinjuries.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tommy.footballinjuries.POJO.Footballer;

public class ApiResponseMapper {

    public static Footballer map(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return objectMapper.readValue(jsonString, Footballer.class);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
