import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

public class PetstoreTest {
    @Test
    @DisplayName("Создание нового питомца в Petstore")
    public void addNewPet() {
        String body = "{ \"id\": 0, \"category\": { \"id\": 0, \"name\": \"string\" }, \"name\": \"charlie\", \"photoUrls\": [ \"string\" ], \"tags\": [ { \"id\": 0, \"name\": \"string\" } ], \"status\": \"pending\" }";
        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .log().status()
                .log().body();
    }

    @Test
    @DisplayName("Проверка созданного питомца в Petstore")
    public void findPetById() {
        given()
                .log().uri()
                .when()
                .get("https://petstore.swagger.io/v2/pet/9223372036854775807")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(9223372036854775807L));
    }

    @Test
    @DisplayName("Изменение существующего питомца в Petstore")
    public void updateAnExistingPet() {
        String body = "{ \"id\": 9223372016900017859, \"category\": { \"id\": 0, \"name\": \"string\" }, \"name\": \"charlie\",\"photoUrls\": [ \"string\" ], \"tags\": [ { \"id\": 0, \"name\": \"string\" } ], \"status\": \"sold\" }";
        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .log().status()
                .log().body();
    }

    @Test
    @DisplayName("Проверка статуса питомца в Petstore")
    public void findByStatus() {
        given()
                .log().uri()
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=pending")
                .then()
                .log().status()
                .log().body();
    }
        @Test
    @DisplayName("Загрузка изображения питомца в Petstore")
    public void uploadImage() {
        String file = "form-data";
        given()
                .log().uri()
                .contentType(ContentType.JSON)

                .when()
                .post("https://petstore.swagger.io/v2/pet/9223372036854775807/uploadImage")
                .then()
                .log().status()
                .statusCode(200)
    }


    @Test
    @DisplayName("Удаление питомца в Petstore")
    public void deletePet() {
        given()
                .log().uri()
                .when()
                .delete("https://petstore.swagger.io/v2/pet/9223372016900017859")
                .then()
                .log().status()
                .log().body();
    }


}
