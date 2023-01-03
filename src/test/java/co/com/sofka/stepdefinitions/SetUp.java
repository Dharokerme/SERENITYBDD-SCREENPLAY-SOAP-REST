package co.com.sofka.stepdefinitions;

import net.serenitybdd.screenplay.Actor;

import java.util.HashMap;
import java.util.Map;

import static co.com.sofka.runner.CalculatorSoapTest.defineBodyRequest;

public class SetUp {

    protected static final String URL_BASE = "http://www.dneonline.com";
    protected static final String RESOURCE = "/calculator.asmx";
    protected static final String URL_BASE_REQRES = "https://reqres.in";
    protected static final String POST_REGISTER_RESOURCE = "/api/register";
    protected Actor actor;
    protected Map<String, Object> headers = new HashMap<>();
    protected String bodyRequest = defineBodyRequest("10","20");
    protected String bodyResponse = "<AddResult>30</AddResult>";
}
