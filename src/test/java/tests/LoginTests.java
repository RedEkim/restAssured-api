package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class LoginTests {
    /*
    1. Make request (POST) to https://reqres.in/api/login
        with body { "email": "eve.holt@reqres.in", "password": "cityslicka" }
    2. Get response { "token": "QpwL5tke4Pnpja7X4" }
    3. Check "token" is "QpwL5tke4Pnpja7X4" and status code 200
  */

    @Test
    @Tag("Login")
    @DisplayName("Login test with status code 200")
    void successfulLoginTest() {

        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
        // Arrange Act Assert
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
        .when()
                .post("https://reqres.in/api/login")
        .then()
                .log().status()
                .log().body()
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .statusCode(200);
    }

    @Test
    @Tag("Login")
    @DisplayName("Login test with status code 400 and Missing email or username")
    void MissingEmailOrUsernameTestStatusCode400() {

        String authData = "{\"email\": \"\", \"password\": \"cityslicka\"}";
        // Arrange Act Assert
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
        .when()
                .post("https://reqres.in/api/login")
        .then()
                .log().status()
                .log().body()
                .body("error", is("Missing email or username"))
                .statusCode(400);
    }

    @Test
    @Tag("Login")
    @DisplayName("Login test with status code 400 and Missing password")
    void MissingPasswordTest() {

        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"\"}";
        // Arrange Act Assert
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
        .when()
                .post("https://reqres.in/api/login")
        .then()
                .log().status()
                .log().body()
                .body("error", is("Missing password"))
                .statusCode(400);
    }

    @Test
    @Tag("Login")
    @DisplayName("Login test with bad JSON")
    void unsuccessful1LoginTestWithBadJson() {

        String authData = "}";
        // Arrange Act Assert
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
        .when()
                .post("https://reqres.in/api/login")
        .then()
                .log().status()
                .log().body()
                .statusCode(400);
    }
}
