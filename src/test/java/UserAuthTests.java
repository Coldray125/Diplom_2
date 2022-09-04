import Pojo.response.AuthUserResponse;
import builders.UserBuilder;
import generator.RandomData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;

import java.util.HashMap;

public class UserAuthTests {
    static UserBuilder userBuilder = new UserBuilder();
    static RandomData randomData = new RandomData();
    static HashMap<String, String> userData = new HashMap<>();
    static HashMap<String, String> wrongUserData = new HashMap<>();

    @BeforeClass
    public static void generateUserWithRandomData() {
        userData = userBuilder.createUserRandomCredentials();
        wrongUserData = randomData.randomCredentials();
    }

    @AfterClass
    public static void deleteUser() {
        String token = userBuilder.getAuthUserAccessToken(userData);
        if (token != null) {
            userBuilder.deleteUserWithAccessToken(token);
        }
    }

    @Test
    @DisplayName("Проверка логина пользователя")
    public void validAuth() {
        AuthUserResponse userAuthBody = userBuilder.authUserBody(userData.get("email"),
                userData.get("password"),
                userData.get("name"));
        Assert.assertEquals("true", userAuthBody.getSuccess());
    }

    @Test
    @DisplayName("Проверка кода ответа после логина пользователя")
    public void validAuthResponseCode() {
        Response response = userBuilder.AuthUserResponse(userData.get("email"),
                userData.get("password"),
                userData.get("name"));
        Assert.assertEquals(SC_OK, response.statusCode());
    }

    @Test
    @DisplayName("Проверка текста ответа после логина пользователя с неконкретными авторизационными данными")
    public void wrongAuth() {
        Response response = userBuilder.AuthUserResponse(wrongUserData.get("email"),
                wrongUserData.get("password"),
                wrongUserData.get("name"));
        Assert.assertEquals("false", response.jsonPath().getString("success"));
    }

    @Test
    @DisplayName("Проверка кода ответа после логина пользователя с неконкретными авторизационными данными")
    public void wrongAuthResponseCode() {
        Response response = userBuilder.AuthUserResponse(wrongUserData.get("email"),
                wrongUserData.get("password"),
                wrongUserData.get("name"));
        Assert.assertEquals(SC_UNAUTHORIZED, response.statusCode());
    }
}