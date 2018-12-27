package org.bala.c4;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StepDefinitions {
    private static final Logger log = LoggerFactory.getLogger(StepDefinitions.class);

    @Given("that there is a list of Trades") // cucumber expression
    public void that_there_is_a_list_of_Trades(final List<Trade> trades) {
        log.info("Trades count: {}", trades.size());
        trades.forEach(t -> log.info("{}", t));
    }

    @When("^vwap is calculated$") // regular expression
    public void vwap_is_calculated() {
    }

    @Then("calculated vwap should be {double}") // cucumber expression that is supposedly more readable
    public void calculated_vwap_should_be(Double vwap) {
        log.info("vwap: {}", vwap);
    }
}
