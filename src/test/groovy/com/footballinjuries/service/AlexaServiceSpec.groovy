package com.footballinjuries.service

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.response.ResponseBuilder
import spock.lang.Specification

class AlexaServiceSpec extends Specification {

    def "Building speech response"() {

        given: "An alexa service"
        AlexaService alexaService = new AlexaService()

        and: "A handler input"
        HandlerInput handlerInput = Mock(HandlerInput)

        and: "A response builder"
        ResponseBuilder responseBuilder = Mock(ResponseBuilder)

        and: "A response builder"
        handlerInput.getResponseBuilder() >> responseBuilder

        and: "A second response builder"
        ResponseBuilder speechResponseBuilder = Mock(ResponseBuilder)

        and: "A specific speech builder is returned for the speech string"
        responseBuilder.withSpeech("sausage test") >> speechResponseBuilder

        when: "We ask the service to respond"
        alexaService.respond(handlerInput, "sausage test")

        then: "The speech response builder for that string is called"
        1 * speechResponseBuilder.build()
    }

}
