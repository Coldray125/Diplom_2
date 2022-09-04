import Pojo.response.CreateOrderResponse;
import Pojo.response.UserOrdersResponse;
import builders.UserBuilder;
import generator.RandomData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.*;

import java.util.HashMap;

public class UserOrdersTests {

    UserBuilder userBuilder = new UserBuilder();
    static HashMap<String, String> userData = new RandomData().randomCredentials();
    Integer orderNumber;
    private String emptyToken = "";

    @Before
    public void prepareData() {
        userData.put("token", userBuilder.getTokenAfterCreateUser(
                userData.get("email"),
                userData.get("password"),
                userData.get("name")));
       CreateOrderResponse response = userBuilder.createOrderBody(userBuilder.getRandomIngridient(2), userData.get("token"));
        orderNumber = response.getOrder().getNumber();
    }

    @After
    public void deleteUser() {
        userBuilder.deleteUserWithAccessToken(userData.get("token"));
    }


    @Test
    @DisplayName("Проверка получения заказов пользователя без авторизации")
    public void noAuthUserOrderInfo() {
        Response response = userBuilder.userOrdersResponse(emptyToken);
        Assert.assertEquals("You should be authorised", response.jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Проверка кода ответа после получения заказов без авторизации")
    public void noAuthUserOrderInfoResponseCode() {
        Response response = userBuilder.userOrdersResponse(emptyToken);
        Assert.assertEquals(SC_UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    @DisplayName("Проверка получения заказов пользователя с авторизацией")
    public void AuthUserOrderInfo() {
        UserOrdersResponse response = userBuilder.userOrdersBody(userData.get("token"));
        Assert.assertEquals(orderNumber, response.getOrders().get(0).getNumber());
    }
}
