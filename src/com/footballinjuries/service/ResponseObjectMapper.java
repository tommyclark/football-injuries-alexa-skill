package com.footballinjuries.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballinjuries.POJO.Footballer;

public class ResponseObjectMapper {

    public Footballer map(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            return objectMapper.readValue(jsonString, Footballer.class);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
