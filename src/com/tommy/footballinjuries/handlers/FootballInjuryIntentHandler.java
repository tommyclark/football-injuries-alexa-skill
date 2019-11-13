/*
     Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.tommy.footballinjuries.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.tommy.footballinjuries.POJO.Footballer;
import com.tommy.footballinjuries.utils.ApiResponseMapper;
import com.tommy.footballinjuries.utils.HttpUtils;

import java.io.IOException;
import java.util.Optional;

public class FootballInjuryIntentHandler implements IntentRequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("MPIntent");
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        final Slot name = intentRequest.getIntent().getSlots().get("name");

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

//    @Override
//    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
//        final Slot distanceSlot = intentRequest.getIntent().getSlots().get("distance");
//        double radius = MAX_RADIUS;
//        if (distanceSlot != null
//                && distanceSlot.getResolutions() != null
//                && distanceSlot.getResolutions().toString().contains("ER_SUCCESS_MATCH")) {
//            radius = Double.valueOf(distanceSlot.getValue());
//        }
//
//        final List<Attraction> attractions = SkillUtils.getAttractionsWithinDistance(radius, SkillData.CITY_INFORMATION.getAttractions());
//
//        final Attraction nearestAttraction = attractions.get(SkillUtils.getRandomIndexInRange(attractions.size(), 0));
//        final String speechOutput = String.format("Try %s which is %s miles away. Have fun! %s",
//                nearestAttraction.getName(),
//                nearestAttraction.getDistance(),
//                nearestAttraction.getDescription());
//        return handlerInput.getResponseBuilder()
//                .withSpeech(speechOutput)
//                .build();
//    }
}
