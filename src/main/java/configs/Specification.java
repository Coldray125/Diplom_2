package configs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Specification {

    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    public static RequestSpecification BurgerURL(){
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Content-type","application/json")
                .build();
    }
    public static RequestSpecification BurgerURLTokenJSON(String accessToken) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Content-type","application/json")
                .addHeader("Authorization", accessToken)
                .build();
    }
    public static RequestSpecification BurgerURLToken(String accessToken) {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Authorization", accessToken)
                .build();
    }
}

