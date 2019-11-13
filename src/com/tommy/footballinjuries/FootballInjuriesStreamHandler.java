/*
     Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.tommy.footballinjuries;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.tommy.footballinjuries.handlers.FootballInjuryIntentHandler;

public class FootballInjuriesStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new FootballInjuryIntentHandler()
                )
                // Add your skill id below
                .withSkillId("amzn1.ask.skill.e74bf868-2b6e-4669-9a88-2569c5259894")
                .build();
    }

    public FootballInjuriesStreamHandler() {
        super(getSkill());
    }
}
