package clients;

import io.restassured.response.ValidatableResponse;
import pojo.CreateOrderRequest;

import static io.restassured.RestAssured.given;

public class OrderClient extends BaseClient {
    public ValidatableResponse create(CreateOrderRequest createOrderRequest) {
        return given()
                .spec(getSpec())
                .body(createOrderRequest)
                .when()
                .post("/api/v1/orders")
                .then();
    }

    public ValidatableResponse list() {
        return given()
                .spec(getSpec())
                .get("/api/v1/orders")
                .then();
    }
}
