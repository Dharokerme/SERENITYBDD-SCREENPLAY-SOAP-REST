package co.com.sofka.stepdefinitions;

import co.com.sofka.utils.ContentBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;

import static co.com.sofka.questions.PrettierResponse.prettierResponse;
import static co.com.sofka.tasks.DoPost.doPost;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;

public class PostStepDefinitions extends SetUp {

    @Given("que el cliente entro a la pagina.")
    public void queElClienteEntroALaPagina() {

        actor = Actor.named("Juan");
        actor.can(CallAnApi.at(URL_BASE_REQRES));
        headers.put("Content-Type", "application/json");
        bodyRequest = ContentBody.JSON_BODY.getValue();

    }

    @When("crea un usuario.")
    public void creaUnUsuario() {

        actor.attemptsTo(
                doPost().usingTheResource(POST_REGISTER_RESOURCE)
                        .withHeaders(headers)
                        .andBodyRequest(bodyRequest)

        );

    }

    @Then("obtendra un token de autenticacion.")
    public void obtendraUnTokenDeAutenticacion() {

        LastResponse.received().answeredBy(actor).prettyPrint();

        actor.should(
                seeThatResponse("El código de respuesta debe ser" + HttpStatus.SC_OK,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)),

                seeThat("El token recibido debe ser: QpwL5tke4Pnpja7X4",
                        prettierResponse(), containsString("QpwL5tkasdnpja7X4")
                ));
    }
}
