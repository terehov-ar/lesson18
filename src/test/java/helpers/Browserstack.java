package helpers;

import config.RemoteMobileConfig;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class Browserstack {

    private static final RemoteMobileConfig config = ConfigFactory.create(RemoteMobileConfig.class, System.getProperties());

    public static String videoUrl(String sessionId) {

        String url = String.format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(config.browserstackUser(), config.browserstackKey())
                .get(url)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}