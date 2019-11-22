package com.footballinjuries.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.footballinjuries.service.AlexaService;
import com.footballinjuries.service.InjuryService;

import java.io.IOException;
import java.util.Optional;

import static java.util.Objects.isNull;

public class FootballInjuryIntentHandler implements IntentRequestHandler {
    private InjuryService injuryService;
    private AlexaService alexaService;

    public FootballInjuryIntentHandler(InjuryService injuryService,
                                       AlexaService alexaService) {
        this.injuryService = injuryService;
        this.alexaService = alexaService;

    }

    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("FootballInjuryIntent");
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        final Slot name = intentRequest.getIntent().getSlots().get("name");
        if (isNull(name)) return alexaService.respond(handlerInput, "Hmm, I could not find a footballer by that name.");

        try {
            return alexaService.respond(handlerInput, injuryService.buildInjuryString(name.getValue()));
        } catch (IOException e) {
            return alexaService.respond(handlerInput,
                    "The football injuries skill is currently experiencing problems. Please try again later.");
        }
    }

}
