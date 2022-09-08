import pojo.response.CreateUserResponse;
import builders.UserBuilder;
import generator.RandomData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.apache.http.HttpStatus.*;

public class UserCreationTests {
    static UserBuilder userBuilder = new UserBuilder();
    static RandomData randomData = new RandomData();
    static HashMap<String, String> userData = new HashMap<>();

    @Before
    public void generateRandomData() {
        userData = randomData.randomCredentials();
    }

    @After
    public void deleteUser() {
        String token = userBuilder.getAuthUserAccessToken(userData);
        if (token != null) {
            userBuilder.deleteUserWithAccessToken(token);
        }
    }

    @Test
    @DisplayName("Cоздание уникального пользователя")
    public void validUserCreate() {
        CreateUserResponse userBody = userBuilder.createUserBody(
                userData.get("email"),
                userData.get("password"),
                userData.get("name"));
        Assert.assertEquals("true", userBody.getSuccess());
    }

    @Test
    @DisplayName("Проверка кода ответа после создание уникального пользователя")
    public void validUserCreateResponseCode() {
        Response response = userBuilder.createUserResponse(
                userData.get("email"),
                userData.get("password"),
                userData.get("name"));
        Assert.assertEquals(SC_OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Проверка текста ошибки после создания пользователя с незаполненным именем")
    public void noNameError() {
        Response response = userBuilder.createUserResponse(
                userData.get("email"),
                userData.get("password"),
                "");
        Assert.assertEquals("Email, password and name are required fields",response.jsonPath().getString("message") );
    }

    @Test
    @DisplayName("Проверка кода ошибки после создания пользователя с незаполненным именем")
    public void noNameErrorResponseCode() {
        Response response = userBuilder.createUserResponse(
                userData.get("email"),
                userData.get("password"),
                "");
        Assert.assertEquals(SC_FORBIDDEN, response.getStatusCode());
    }

    @Test
    @DisplayName("Проверка текста ошибки после создания пользователя с незаполненным паролем")
    public void noPasswordError() {
        Response response = userBuilder.createUserResponse(
                userData.get("email"),
                "",
                userData.get("name"));
        Assert.assertEquals("Email, password and name are required fields", response.jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Проверка кода ошибки после создания пользователя с незаполненным паролем")
    public void noPasswordErrorResponseCode() {
        Response response = userBuilder.createUserResponse(
                userData.get("email"),
                "",
                userData.get("name"));
        Assert.assertEquals(SC_FORBIDDEN, response.getStatusCode());
    }

    @Test
    @DisplayName("Проверка текста ошибки после создания пользователя с незаполненной почтой")
    public void noEmailError() {
        Response response = userBuilder.createUserResponse(
                "",
                userData.get("password"),
                userData.get("name"));
        Assert.assertEquals("Email, password and name are required fields", response.jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Проверка кода ошибки после создания пользователя с незаполненной почтой")
    public void noEmailErrorResponseCode() {
        Response response = userBuilder.createUserResponse(
                "",
                userData.get("password"),
                userData.get("name"));
        Assert.assertEquals(SC_FORBIDDEN, response.getStatusCode());
    }

    @Test
    @DisplayName("Проверка текста ошибки после создания пользователя , который уже зарегистрирован")
    public void existsUserError() {
        userBuilder.createUserResponse(
                userData.get("email"),
                userData.get("password"),
                userData.get("name"));

        Response response = userBuilder.createUserResponse(
                userData.get("email"),
                userData.get("password"),
                userData.get("name"));

        Assert.assertEquals("User already exists", response.jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Проверка кода ошибки после создания пользователя , который уже зарегистрирован")
    public void existsUserErrorResponseCode() {
        userBuilder.createUserResponse(
                userData.get("email"),
                userData.get("password"),
                userData.get("name"));

        Response response = userBuilder.createUserResponse(
                userData.get("email"),
                userData.get("password"),
                userData.get("name"));

        Assert.assertEquals(SC_FORBIDDEN, response.getStatusCode());
    }
}