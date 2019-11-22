package com.footballinjuries.service;

import com.footballinjuries.POJO.Footballer;

import java.io.IOException;

public class InjuryService {
    private ApiIntegrationService apiIntegrationService;
    private ResponseObjectMapper responseObjectMapper;

    public InjuryService(ApiIntegrationService apiIntegrationService, ResponseObjectMapper responseObjectMapper) {
        this.apiIntegrationService = apiIntegrationService;
        this.responseObjectMapper = responseObjectMapper;
    }

    public boolean isInjured(String name) throws IOException {
        Footballer footballer;

        com.squareup.okhttp.Response response = apiIntegrationService.getPlayerStatistics(name);
        footballer = responseObjectMapper.map(response.body().string());

        return footballer.isInjured();
    }

    public String buildInjuryString(String name) throws IOException {
        String injuryString = !isInjured(name) ? "not " : "";
        return String.format("%s is " + injuryString + "currently injured",
                name);
    }
}
