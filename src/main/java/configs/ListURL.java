package configs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public interface ListURL {
    RequestSpecification BurgerURL = new RequestSpecBuilder()
            .setBaseUri("https://stellarburgers.nomoreparties.site")
            .build();
}

