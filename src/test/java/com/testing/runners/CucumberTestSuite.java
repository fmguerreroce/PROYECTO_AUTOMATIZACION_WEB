package com.testing.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

    @RunWith(CucumberWithSerenity.class)
    @CucumberOptions(
            plugin = {"pretty"},
            features = {"src/test/resources/features"},
            glue = "com.testing.stepdefinitions",
            tags = "@SHOP"

    )
    public class CucumberTestSuite {
}
