package tests;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import model.Message;
import model.Person;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class PersonTest {
    int port = 8888;
    private WireMock wiremock;
    @Rule
    public WireMockRule wiremockRule = new WireMockRule(8888);

//    @Test
//    public void testSend() {
//        wiremock = new WireMock(port);
//        wiremock.register(post(urlEqualTo("/Person"))
//                .withRequestBody(containing("henk"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withBody("0")));
//        Person henk = new Person("henk","henk123");
//        henk.send(new Message("jojo"));
//        wiremock.verifyThat(WireMock.postRequestedFor(urlEqualTo("/Person")));
//    }

    @Test
    public void testAddPerson() {
        Person newPerson = new Person("hans", "hans123");
        wiremock = new WireMock(port);
        wiremock.register(put(urlEqualTo("/Person"))
        .withRequestBody(containing("hans"))
        .willReturn(aResponse()
                .withStatus(200)));

        given()
                .port(port)
                .contentType("application/json")
                .body(newPerson)
                .when().put("/Person").then()
                .statusCode(200);
        wiremock.verifyThat(WireMock.putRequestedFor(urlEqualTo("/Person")));
    }
}
