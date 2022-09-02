package requests;

import Pojo.response.CreateOrderResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static configs.EndPointList.ORDER;
import static configs.Specification.BurgerURLTokenJSON;
import static io.restassured.RestAssured.given;

public class PostCreateOrder {

    @Step("Отправка запроса на endpoint POST /api/auth/register для создания учетной записи пользователя")
    public CreateOrderResponse OrderCreateBody(Object object, String accessToken) {
        return given()
                .spec(BurgerURLTokenJSON(accessToken))
                .body(object)
                .when()
                .post(ORDER)
                .then()
                .extract()
                .body()
                .as(CreateOrderResponse.class);
    }
    @Step("Отправка запроса на endpoint POST /api/auth/register для создания учетной записи пользователя")
    public Response OrderCreateReponse(Object object, String accessToken) {
        return given()
                .spec(BurgerURLTokenJSON(accessToken))
                .body(object)
                .when()
                .post(ORDER)
                .then()
                .extract()
                .response();
    }
}
