package clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseClient {
    protected RequestSpecification getSpec(){
        return new RequestSpecBuilder()
                .setBaseUri("http://qa-scooter.praktikum-services.ru")
                .setContentType(ContentType.JSON)
                .build();
    }
}
