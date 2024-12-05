package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.*;
import static org.hamcrest.Matchers.is;

public class SwaggerTests extends TestBase {
    /*
    Pet
    +(POST) /pet/{petId}/uploadImage (uploads an image)
    +(POST) /pet (Add a new pet to the store)
    +(PUT) /pet (Update an existing pet)
    +(GET) /pet/findByStatus (Finds Pets by status)
    +(GET) /pet/{petId} (Find per by ID)
    +(POST) /pet/{petId} (Updates a pet in the store with form data)
    +(DELETE) /pet/{petId} (Deletes a pet)
    */

    @Test
    @Tag("Pet")
    @Tag("POST")
    @DisplayName("Add a new pet to the store")
    void addANewPetToTheStore() {
        String lion = "{\n" +
                "  \"id\": 878665454,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"dog\"\n" +
                "  },\n" +
                "  \"name\": \"lion\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 777,\n" +
                "      \"name\": \"runner\"\n" +
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
                .log().body()
                .statusCode(200);
    }

    @Test
    @Tag("Pet")
    @Tag("POST")
    @DisplayName("Uploads an image")
    void uploadAnImageToPet() {

        given()
                .multiPart("file", "src/test/java/resources/hasbik.gif")
                .multiPart("additionalMetadata", "for example")
                .log().uri()
        .when()
                .post("/pet/878665454/uploadImage")
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Tag("Pet")
    @Tag("GET")
    @DisplayName("Finds pet by ID")
    void getPetById() {
        given()
                .log().uri()
        .when()
                .get("/pet/878665454")
        .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    @Tag("Pet")
    @Tag("GET")
    @DisplayName("Find Pets by status")
    void findPetsByStatus() {
        given()
                .log().uri()
        .when()
                .get("/pet/findByStatus?status=available")
        .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    @Tag("Pet")
    @Tag("PUT")
    @DisplayName("Update an existing pet")
    void updatePet() {
        String lion = "{\n" +
                "  \"id\": 878665454,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"dog\"\n" +
                "  },\n" +
                "  \"name\": \"lion\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 778,\n" +
                "      \"name\": \"puppy\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given()
                .body(lion)
                .contentType(JSON)
                .log().uri()
                .log().method()
        .when()
                .put("/pet")
        .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    @Tag("Pet")
    @Tag("POST")
    @DisplayName("Updates a pet in the store with form data")
    void updatesAPetInTheStore() {

        given()
                .contentType(URLENC)
                .formParam("name", "Lion")
                .formParam("status","update")
                .log().uri()
        .when()
                .post("/pet/878665454")
        .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    @Tag("Pet")
    @Tag("DELETE")
    @DisplayName("Deletes a pet")
    void deletesAPet() {
        given()
                .log().uri()
                .log().method()
        .when()
                .delete("/pet/878665454")
        .then()
                .log().status()
                .statusCode(200);

    }

    /*
    Store
    +(GET) /store/inventory (Returns pet inventories by status)
    +(POST) /store/order (Place an order for a pet)
    +(GET) /store/order/{orderId} (Find purchase order by ID)
    (DELETE) /store/order/{orderId} (Delete purchase order by ID)
    */

    @Test
    @Tag("Store")
    @Tag("GET")
    @DisplayName("Returns pet inventories by status")
    void returnsPetInventories() {
        given()
                .log().uri()
        .when()
                .get("/store/inventory")
        .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    @Tag("Store")
    @Tag("POST")
    @DisplayName("Place an order for a pet")
    void placeAnOrderForAPet() {

        String place = "{\n" +
                "  \"id\": 7,\n" +
                "  \"petId\": 878665454,\n" +
                "  \"quantity\": 1,\n" +
                "  \"shipDate\": \"2024-12-05T15:16:00.009Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";
        given()
                .body(place)
                .contentType(JSON)
                .log().uri()
        .when()
                .post("/store/order")
        .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    @Tag("Store")
    @Tag("GET")
    @DisplayName("Find purchase order by ID")
    void findPurchaseOrderById() {
        given()
                .log().uri()
        .when()
                .get("/store/order/7")
        .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

    @Test
    @Tag("Store")
    @Tag("DELETE")
    @DisplayName("Delete purchase order by ID")
    void deletePurchaseOrderById() {
        given()
                .log().uri()
        .when()
                .delete("/store/order/7")
        .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }
    /*
    User
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
