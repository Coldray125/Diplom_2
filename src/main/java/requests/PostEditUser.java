package requests;

import Pojo.request.EditUserRequest;
import Pojo.response.EditUserResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static configs.EndPointList.USER_INFO;
import static configs.ListURL.BurgerURL;
import static io.restassured.RestAssured.given;

public class PostEditUser {

    @Step("Отправка запроса на endpoint POST /api/auth/user для изменения учетной записи пользователя")
    public EditUserResponse UserEditBody(EditUserRequest object, String accessToken) {
        return given()
                .spec(BurgerURL)
                .header("Content-type", "application/json")
                .header("Authorization",accessToken)
                .body(object)
                .when()
                .patch(USER_INFO)
                .then()
                .extract()
                .body()
                .as(EditUserResponse.class);
    }
    @Step("Отправка запроса на endpoint POST /api/auth/user для изменения учетной записи пользователя")
    public Response UserEditReponse(EditUserRequest object, String accessToken) {
        return given()
                .spec(BurgerURL)
                .header("Content-type", "application/json")
                .header("Authorization",accessToken)
                .body(object)
                .when()
                .patch(USER_INFO)
                .then()
                .extract()
                .response();
    }
}
