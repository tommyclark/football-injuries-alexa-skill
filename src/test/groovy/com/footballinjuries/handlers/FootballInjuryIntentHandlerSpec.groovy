package com.footballinjuries.handlers

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model.Intent
import com.amazon.ask.model.IntentRequest
import com.amazon.ask.model.Response
import com.amazon.ask.model.Slot
import com.footballinjuries.service.AlexaService
import com.footballinjuries.service.InjuryService
import spock.lang.Specification

class FootballInjuryIntentHandlerSpec extends Specification {
    InjuryService injuryService = Mock(InjuryService)
    AlexaService alexaService = Mock(AlexaService)
    FootballInjuryIntentHandler footballInjuryIntentHandler

    def setup() {
        footballInjuryIntentHandler = new FootballInjuryIntentHandler(injuryService, alexaService)
    }

    def "canHandle the correct intent"() {
        given: "A football injury intent"
        IntentRequest request = buildIntentRequest("FootballInjuryIntent")

        when: "canHandle is called with the intent request"
        boolean canHandle = footballInjuryIntentHandler.canHandle(Mock(HandlerInput), request)

        then: "The intent handler can handle that request"
        canHandle
    }

    def "canHandle returns false for incorrect intent"() {
        given: "An invalid intent"
        IntentRequest request = buildIntentRequest("Sausages")

        when: "canHandle is called with the intent request"
        boolean canHandle = footballInjuryIntentHandler.canHandle(Mock(HandlerInput), request)

        then: "The intent handler can't handle that request"
        !canHandle
    }

    def "Handler can respond to intent requests"() {
        given: "A valid intent request"
        IntentRequest request = buildIntentRequest("FootballInjuryIntent", "name")

        and: "A handler input"
        HandlerInput handlerInput = Mock(HandlerInput)

        and: "Injury service responds with a valid response"
        injuryService.buildInjuryString("Wijnaldum") >> "Wijnaldum is currently injured"

        and: "A response"
        Optional<Response> response = Optional.of(Response.builder().build())

        and: "Alexa Service returns a response"
        alexaService.respond(handlerInput, "Wijnaldum is currently injured") >> response

        when: "The handle method is called"
        Optional<Response> actualResponse = footballInjuryIntentHandler.handle(handlerInput, request)

        then: "The response returns the expected result"
        actualResponse == response
    }

    def "Response when there is no provided name"() {
        given: "An invalid intent request"
        IntentRequest request = buildIntentRequest("FootballInjuryIntent", "nothing")

        and: "A handler input"
        HandlerInput handlerInput = Mock(HandlerInput)

        when: "The handle method is called"
        footballInjuryIntentHandler.handle(handlerInput, request)

        then: "No footballer response"
        1 * alexaService.respond(handlerInput, "Hmm, I could not find a footballer by that name.")
    }

    def "Fails nicely on IO exception from API"() {
        given: "A valid intent request"
        IntentRequest request = buildIntentRequest("FootballInjuryIntent", "name")

        and: "A handler input"
        HandlerInput handlerInput = Mock(HandlerInput)

        and: "Injury service responds with an exception"
        injuryService.buildInjuryString("Wijnaldum") >> {throw new IOException()}

        when: "The handle method is called"
        footballInjuryIntentHandler.handle(handlerInput, request)

        then: "There is an appropriate message to the user"
        1 * alexaService.respond(handlerInput, "The football injuries skill is currently experiencing problems. Please try again later.")
    }

    private IntentRequest buildIntentRequest(String intentName, String slotName) {
        new IntentRequest.Builder().withIntent(
                Intent.builder()
                        .withName(intentName)
                        .withSlots(buildSlots(slotName))
                        .build()
        ).build()
    }

    private Map<String, Slot> buildSlots(String slotName) {
        Map<String, Slot> slots = new HashMap<>()
        slots.put(slotName, Slot.builder().withName(slotName).withValue("Wijnaldum").build())
        return slots
    }

}
