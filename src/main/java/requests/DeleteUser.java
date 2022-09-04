package requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static configs.EndPointList.USER_INFO;
import static configs.Specification.BurgerURLToken;
import static configs.Specification.BurgerURLTokenJSON;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class DeleteUser {

    @Step("Отправка запроса на endpoint DELETE /api/auth/login для удаления учетной записи пользователя")
    public Response UserDelete(String accessToken) {
        return given()
                .spec(BurgerURLToken(accessToken))
                .when()
                .delete(USER_INFO)
                .then()
                .statusCode(SC_ACCEPTED)
                .extract()
                .response();
    }
}