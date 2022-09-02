package requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static configs.EndPointList.INGRIDIENTS;
import static configs.Specification.BurgerURL;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GetIngredients {
    @Step("Отправка запроса на endpoint GET /api/ingredients для получения списка ингридиентов")
    public Response IngredientsRequest() {
        return given()
                .spec(BurgerURL())
                .when()
                .get(INGRIDIENTS)
                .then()
                .extract()
                .response();
    }
}
