package requests;

import Pojo.response.CreateOrderResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static configs.EndPointList.ORDER;
import static configs.ListURL.BurgerURL;
import static io.restassured.RestAssured.given;

public class PostCreateOrder {

    @Step("Отправка запроса на endpoint POST /api/auth/register для создания учетной записи пользователя")
    public CreateOrderResponse OrderCreateBody(Object object, String accessToken) {
        return given()
                .spec(BurgerURL)
                .header("Content-type", "application/json")
                .header("Authorization",accessToken)
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
                .spec(BurgerURL)
                .header("Content-type", "application/json")
                .header("Authorization",accessToken)
                .body(object)
                .when()
                .post(ORDER)
                .then()
                .extract()
                .response();
    }
}
