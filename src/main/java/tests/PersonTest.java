package tests;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import model.Person;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class PersonTest {
    @Rule
    public WireMockRule wiremockRule = new WireMockRule(8888);

    @Test
    public void testSend() {
        WireMock wiremock = new WireMock(8888);
        wiremock.register(post(urlEqualTo("/Person"))
                .withRequestBody(containing("henk"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("0")));
        Person henk = new Person("henk");
        henk.send(new Message("jojo"));
        wiremock.verifyThat(WireMock.postRequestedFor(urlEqualTo("/Person")));
    }
}
