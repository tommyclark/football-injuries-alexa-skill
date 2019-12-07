package com.footballinjuries.service

import com.footballinjuries.POJO.Footballer
import spock.lang.Specification

class InjuryServiceSpec extends Specification {
    ApiIntegrationService apiIntegrationService = Mock(ApiIntegrationService)
    ResponseObjectMapper responseObjectMapper = Mock(ResponseObjectMapper)
    InjuryService injuryService

    def setup() {
        injuryService = new InjuryService(apiIntegrationService, responseObjectMapper)
    }

    def "Test player is injured"() {
        given: "A name"
        String name = "Neymar"

        and: "A footballer"
        Footballer footballer = new Footballer()
        footballer.name = "Neymar"
        footballer.injured = true

        and: "the API responds"
        apiIntegrationService.getPlayerStatistics(name) >> "test"

        and: "The mapper returns the footballer"
        responseObjectMapper.map("test") >> footballer

        when: "The injury string is built"
        String injuryString = injuryService.buildInjuryString(name)

        then: "The string is correct"
        injuryString == "Neymar is currently injured"

    }

    def "Test player is not injured"() {
        given: "A name"
        String name = "Neymar"

        and: "A footballer"
        Footballer footballer = new Footballer()
        footballer.name = "Neymar"
        footballer.injured = false

        and: "the API responds"
        apiIntegrationService.getPlayerStatistics(name) >> "test"

        and: "The mapper returns the footballer"
        responseObjectMapper.map("test") >> footballer

        when: "The injury string is built"
        String injuryString = injuryService.buildInjuryString(name)

        then: "The string is correct"
        injuryString == "Neymar is not currently injured"

    }
}
