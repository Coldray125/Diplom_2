package requests;

import Pojo.response.UserOrdersResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static configs.EndPointList.ORDER;
import static configs.ListURL.BurgerURL;
import static io.restassured.RestAssured.given;

public class GetUserOrders {

    @Step("Отправка запроса на endpoint GET /api/orders для получения заказов пользователя")
    public UserOrdersResponse UserOrdersBody(String accessToken) {
        return given()
                .spec(BurgerURL)
                .header("Content-type", "application/json")
                .header("Authorization",accessToken)
                .when()
                .get(ORDER)
                .then()
                .extract()
                .body()
                .as(UserOrdersResponse.class);
    }

    @Step("Отправка запроса на endpoint GET /api/orders для получения заказов пользователя")
    public Response UserOrdersResponse(String accessToken) {
        return given()
                .spec(BurgerURL)
                .header("Content-type", "application/json")
                .header("Authorization",accessToken)
                .when()
                .get(ORDER)
                .then()
                .extract()
                .response();
    }
}