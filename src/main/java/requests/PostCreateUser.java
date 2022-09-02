package requests;

import Pojo.response.CreateUserResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static configs.EndPointList.USER_CREATE;
import static configs.Specification.BurgerURL;
import static io.restassured.RestAssured.given;

public class PostCreateUser {

    @Step("Отправка запроса на endpoint POST /api/auth/register для создания учетной записи пользователя")
    public CreateUserResponse UserCreateBody(Object object) {
        return given()
                .spec(BurgerURL())
                .body(object)
                .when()
                .post(USER_CREATE)
                .then()
                .extract()
                .body()
                .as(CreateUserResponse.class);
    }
    @Step("Отправка запроса на endpoint POST /api/auth/register для создания учетной записи пользователя")
    public Response UserCreateReponse(Object object) {
        return given()
                .spec(BurgerURL())
                .body(object)
                .when()
                .post(USER_CREATE)
                .then()
                .extract()
                .response();
    }
}
