package com.footballinjuries.service;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;

import java.util.Optional;

public class AlexaService {
    public Optional<Response> respond(HandlerInput handlerInput, String s) {
        return handlerInput.getResponseBuilder()
                .withSpeech(s)
                .build();
    }
}
