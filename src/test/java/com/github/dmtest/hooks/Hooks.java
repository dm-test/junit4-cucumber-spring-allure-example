package com.github.dmtest.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.MDC;

public class Hooks {

    @Before
    public void setMDC(Scenario scenario) {
        String allureId = scenario.getSourceTagNames().stream()
                .filter(tag -> tag.matches("^@allure\\.id:\\d+$"))
                .findFirst()
                .orElse("@allure.id:?");
        MDC.put("allureId", allureId);
    }

}
