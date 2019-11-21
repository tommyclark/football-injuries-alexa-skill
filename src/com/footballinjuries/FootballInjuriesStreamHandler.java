package com.footballinjuries;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.footballinjuries.handlers.FootballInjuryIntentHandler;

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
