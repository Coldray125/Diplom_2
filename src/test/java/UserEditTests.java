import Pojo.response.EditUserResponse;
import builders.UserBuilder;
import generator.RandomData;
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
    public void editNonAuthUserName() {
        Response response = userBuilder.editUserResponse(
                initialData.get("token"),
                initialData.get("email"),
                initialData.get("password"),
                newData.get("name"));
        Assert.assertEquals("You should be authorised", response.jsonPath().getString("message"));
    }

    @Test
    public void editNonAuthUserNameStatusCode() {
        Response response = userBuilder.editUserResponse(
                initialData.get("token"),
                initialData.get("email"),
                initialData.get("password"),
                newData.get("name"));
        Assert.assertEquals(SC_UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void editNonAuthUserEmail() {
        Response response = userBuilder.editUserResponse(
                initialData.get("token"),
                newData.get("email"),
                initialData.get("password"),
                initialData.get("name"));
        Assert.assertEquals("You should be authorised", response.jsonPath().getString("message"));
    }

    @Test
    public void editNonAuthUserEmailStatusCode() {
        Response response = userBuilder.editUserResponse(
                initialData.get("token"),
                newData.get("email"),
                initialData.get("password"),
                initialData.get("name"));
        Assert.assertEquals(SC_UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void editNonAuthUserPassword() {
        Response response = userBuilder.editUserResponse(
                initialData.get("token"),
                initialData.get("email"),
                newData.get("password"),
                initialData.get("name"));
        Assert.assertEquals("You should be authorised", response.jsonPath().getString("message"));
    }

    @Test
    public void editNonAuthUserPasswordStatusCode() {
        Response response = userBuilder.editUserResponse(
                initialData.get("token"),
                initialData.get("email"),
                newData.get("password"),
                initialData.get("name"));
        Assert.assertEquals(SC_UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void editAuthUserName() {
        Response response = userBuilder.editUserResponse(
                initialData.get("token"),
                initialData.get("email"),
                initialData.get("password"),
                newData.get("name"));
        Assert.assertEquals("You should be authorised", response.jsonPath().getString("message"));
    }

    @Test
    public void editAuthUserNameStatusCode() {
        Response response = userBuilder.editUserResponse(
                initialData.get("token"),
                initialData.get("email"),
                initialData.get("password"),
                newData.get("name"));
        Assert.assertEquals(SC_UNAUTHORIZED, response.getStatusCode());
    }
}