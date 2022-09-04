import Pojo.response.EditUserResponse;
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

public class UserEditTests {

    UserBuilder userBuilder = new UserBuilder();
    static HashMap<String, String> initialData = new RandomData().randomCredentials();
    static HashMap<String, String> newData = new RandomData().randomCredentials();
    private String emptyToken = "";

    @Before
    public void prepareData() {
        initialData.put("token", userBuilder.getTokenAfterCreateUser(
                initialData.get("email"),
                initialData.get("password"),
                initialData.get("name")));
    }

    @After
    public void deleteUser() {
        userBuilder.deleteUserWithAccessToken(initialData.get("token"));
    }

    @Test
    @DisplayName("Проверка изменения данных пользователя с авторизацией")
    public void editAuthUserValidData() {
        userBuilder.authUser(initialData);

        EditUserResponse response = userBuilder.editUserBody(
                initialData.get("token"),
                initialData.get("email"),
                initialData.get("password"),
                initialData.get("name"));
        Assert.assertEquals("true", response.getSuccess());
    }

    @Test
    @DisplayName("Проверка изменения почты пользователя с авторизацией")
    public void editAuthUserEmail() {
        userBuilder.authUser(initialData);

        EditUserResponse response = userBuilder.editUserBody(
                initialData.get("token"),
                newData.get("email"),
                initialData.get("password"),
                initialData.get("name"));
        Assert.assertEquals(newData.get("email"), response.getUser().getEmail());
    }

    @Test
    @DisplayName("Проверка кода ответа после изменения почты пользователя с авторизацией")
    public void editAuthUserEmailStatusCode() {
        userBuilder.authUser(initialData);

        Response response = userBuilder.editUserResponse(
                initialData.get("token"),
                newData.get("email"),
                initialData.get("password"),
                initialData.get("name"));
        Assert.assertEquals(SC_OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Проверка изменения пароля пользователя с авторизацией")
    public void editAuthUserPassword() {
        userBuilder.authUser(initialData);

        EditUserResponse response = userBuilder.editUserBody(
                initialData.get("token"),
                initialData.get("email"),
                newData.get("password"),
                initialData.get("name"));
        Assert.assertEquals("true", response.getSuccess());
    }

    @Test
    @DisplayName("Проверка кода ответа после изменения пароля пользователя с авторизацией")
    public void editAuthUserPasswordStatusCode() {
        userBuilder.authUser(initialData);

        Response response = userBuilder.editUserResponse(
                initialData.get("token"),
                initialData.get("email"),
                newData.get("password"),
                initialData.get("name"));
        Assert.assertEquals(SC_OK, response.getStatusCode());
    }


    @Test
    @DisplayName("Проверка изменения имени пользователя с авторизацией")
    public void editAuthUserName() {
        EditUserResponse response = userBuilder.editUserBody(
                initialData.get("token"),
                initialData.get("email"),
                initialData.get("password"),
                newData.get("name"));
        Assert.assertEquals("true", response.getSuccess());
    }

    @Test
    @DisplayName("Проверка кода ответа после изменения имени пользователя с авторизацией")
    public void editAuthUserNameStatusCode() {
        Response response = userBuilder.editUserResponse(
                initialData.get("token"),
                initialData.get("email"),
                initialData.get("password"),
                newData.get("name"));
        Assert.assertEquals(SC_OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Проверка изменения имени пользователя без авторизации")
    public void editNonAuthUserName() {
        Response response = userBuilder.editUserResponse(
                emptyToken,
                initialData.get("email"),
                initialData.get("password"),
                newData.get("name"));
        Assert.assertEquals("You should be authorised", response.jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Проверка кода ответа после изменения имени пользователя без авторизации")
    public void editNonAuthUserNameStatusCode() {
        Response response = userBuilder.editUserResponse(
                emptyToken,
                initialData.get("email"),
                initialData.get("password"),
                newData.get("name"));
        Assert.assertEquals(SC_UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    @DisplayName("Проверка изменения почты пользователя без авторизации")
    public void editNonAuthUserEmail() {
        Response response = userBuilder.editUserResponse(
                emptyToken,
                newData.get("email"),
                initialData.get("password"),
                initialData.get("name"));
        Assert.assertEquals("You should be authorised", response.jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Проверка кода ответа после изменения почты пользователя без авторизации")
    public void editNonAuthUserEmailStatusCode() {
        Response response = userBuilder.editUserResponse(
                emptyToken,
                newData.get("email"),
                initialData.get("password"),
                initialData.get("name"));
        Assert.assertEquals(SC_UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    @DisplayName("Проверка изменения пароля пользователя без авторизации")
    public void editNonAuthUserPassword() {
        Response response = userBuilder.editUserResponse(
                emptyToken,
                initialData.get("email"),
                newData.get("password"),
                initialData.get("name"));
        Assert.assertEquals("You should be authorised", response.jsonPath().getString("message"));
    }

    @Test
    @DisplayName("Проверка кода ответа после изменения пароля пользователя без авторизации")
    public void editNonAuthUserPasswordStatusCode() {
        Response response = userBuilder.editUserResponse(
                emptyToken,
                initialData.get("email"),
                newData.get("password"),
                initialData.get("name"));
        Assert.assertEquals(SC_UNAUTHORIZED, response.getStatusCode());
    }
}