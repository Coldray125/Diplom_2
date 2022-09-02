package requests;

import Pojo.response.AuthUserResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static configs.EndPointList.USER_AUTH;
import static configs.Specification.BurgerURL;
import static io.restassured.RestAssured.given;

public class PostAuthUser {

    @Step("Отправка запроса на endpoint POST /api/auth/login для авторизации учетной записи пользователя")
    public AuthUserResponse AuthUserBody(Object object) {
        return given()
                .spec(BurgerURL())
                .body(object)
                .when()
                .post(USER_AUTH)
                .then()
                .extract()
                .body()
                .as(AuthUserResponse.class);
    }
    @Step("Отправка запроса на endpoint POST /api/auth/login для авторизации учетной записи пользователя")
    public Response AuthUserResponse(Object object) {
        return given()
                .spec(BurgerURL())
                .body(object)
                .when()
                .post(USER_AUTH)
                .then()
                .extract()
                .response();
    }
}