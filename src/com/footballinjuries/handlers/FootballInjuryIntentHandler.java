package com.footballinjuries.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.footballinjuries.POJO.Footballer;
import com.footballinjuries.utils.ApiResponseMapper;
import com.footballinjuries.utils.HttpUtils;

import java.io.IOException;
import java.util.Optional;

import static java.util.Objects.isNull;

public class FootballInjuryIntentHandler implements IntentRequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("FootballInjuryIntent");
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        final Slot name = intentRequest.getIntent().getSlots().get("name");

        if (isNull(name)) {
            return handlerInput.getResponseBuilder()
                    .withSpeech("Hmm, I could not find a footballer by that name.")
                    .build();
        }

        Footballer footballer;
        try {
            com.squareup.okhttp.Response response = HttpUtils.getPlayerStatistics(name.getValue());
            footballer = ApiResponseMapper.map(response.body().string());
        } catch (IOException e) {
            throw new IllegalStateException();
        }

        String injuryString = !footballer.isInjured() ? "not " : "";
        final String speechOutput = String.format("%s is " + injuryString + "currently injured",
                name.getValue());

        return handlerInput.getResponseBuilder()
                .withSpeech(speechOutput)
                .build();
    }

}
