package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class SwaggerTests extends TestBase {
    /*
    pet
    (POST) /pet/{petId}/uploadImage (uploads an image)
    (POST) /pet (Add a new pet to the store)
    (PUT) /pet (Update an existing pet)
    (GET) /pet/findByStatus (Finds Pets by status)
    (GET) /pet/{petId} (Find  per by ID)
    (POST) /pet/{petId} (Updates a pet in the store with form data)
    (DELETE) /pet/{petId} (Deletes a pet)
    */

    @Test
    @Tag("Pet")
    @DisplayName("Add a new pet to the store")
    public void addANewPetToTheStore() {
        String lion = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        given()
                .body(lion)
                .contentType(JSON)
                .log().uri()
        .when()
                .post("/pet")
        .then()
                .log().status()
                .log().body();
    }


    /*
    store
    (GET) /store/inventory
    (POST) /store/order
    (GET) /store/order/{orderId}
    (DELETE) /store/order/{orderId}
    */

    /*
    user
    (POST) /user/createWithList
    (GET) /user/{username}
    (PUT) /user/{username}
    (DELETE) /user/{username}
    (GET) /user/login
    (GET) /user/logout
    (POST) /user/createWithArray
    (POST) /user
    */

}
