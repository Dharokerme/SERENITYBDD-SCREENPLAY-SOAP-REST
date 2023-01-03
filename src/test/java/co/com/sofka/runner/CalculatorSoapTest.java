package co.com.sofka.runner;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static co.com.sofka.questions.ReturnStringValue.returnStringValue;
import static co.com.sofka.tasks.DoPost.doPost;
import static co.com.sofka.utils.Dictionary.SOAP_ACTION;
import static co.com.sofka.utils.FileUtilities.readFile;
import static co.com.sofka.utils.soap.add.Patch.ADD;
import static co.com.sofka.utils.soap.add.ValueInt.INTA;
import static co.com.sofka.utils.soap.add.ValueInt.INTB;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;

@RunWith(SerenityRunner.class)
public class CalculatorSoapTest {

    private static final String URL_BASE = "http://www.dneonline.com";
    private static final String RESOURCE = "/calculator.asmx";
    private Actor actor;

    private final String bodyRequest = defineBodyRequest("10","20");

    private String bodyResponse = "<AddResult>30</AddResult>";


    private Map<String, Object> headers = new HashMap<>();

    @Before
    public void setUp(){

        actor = Actor.named("Juan");
        actor.can(CallAnApi.at(URL_BASE));
        headers.put("Content-Type", "text/xml;charset=UTF-8");
        headers.put(SOAP_ACTION, "http://tempuri.org/Add");

    }

    @Test
    public void add(){

        actor.attemptsTo(
                doPost()
                        .usingTheResource(RESOURCE)
                        .withHeaders(headers)
                        .andBodyRequest(bodyRequest)
        );

        String soapResponse = new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8);
        System.out.println("==================RESPONSE==================");
        System.out.println(LastResponse.received().answeredBy(actor).body().prettyPrint());

        actor.should(
                seeThatResponse("El código de respuesta HTTP debe ser:",
                        response -> response.statusCode(HttpStatus.SC_OK))
                ,seeThat("El resultado de la suma debe ser:",
                        returnStringValue().withSystemValue(soapResponse),
                        containsString(bodyResponse))
        );

    }

    @After
    public void tearDown(){

    }


    public static String defineBodyRequest(String inputA, String inputB) {
        return readFile(ADD.getValue())
                .replace(INTA.getValue(),inputA)
                .replace(INTB.getValue(),inputB);
    }
}
