import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class StatusTests {

    /*
        1. make request to https://selenoid.autotests.cloud/status
        2. get response {...}
        3. check total is 20
    */

    @Test
    @DisplayName("API test")
    void checkTotal20() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(21));
    }

    @Test
    @DisplayName("API test with logs")
    void checkTotalWithLogs() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(20));
    }

    @Test
    @DisplayName("API test with Response logs")
    void checkTotalWithResponseLogs() {
        given()
                .log().all()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .log().all()
                .body("total", is(20));
    }

}
