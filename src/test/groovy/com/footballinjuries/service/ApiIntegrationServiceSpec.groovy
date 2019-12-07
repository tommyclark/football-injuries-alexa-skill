package com.footballinjuries.service

import com.github.tomakehurst.wiremock.WireMockServer
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.*

class ApiIntegrationServiceSpec extends Specification {
    static WireMockServer wireMockServer
    ApiIntegrationService apiIntegrationService
    String expectedResponse = this.getClass().getResource('/api_response.json').text

    void setupSpec() {
        wireMockServer = new WireMockServer()
        wireMockServer.start()
    }

    void setup() {
        apiIntegrationService = new ApiIntegrationService("http://localhost:"+wireMockServer.port())
    }

    void cleanupSpec() {
        wireMockServer.shutdown()
    }

    def "Integrates with Football API"() {

        given: "A stub for the API"
        stubFor(get(urlEqualTo("/players?search=Wijnaldum&league=2"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withBody(expectedResponse)
                        .withStatus(200)))

        when: "The api integration service is called"
        String response = apiIntegrationService.getPlayerStatistics("Wijnaldum")

        then: "The response is the expected response"
        response == expectedResponse

    }

}
