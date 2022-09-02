import Pojo.response.CreateOrderResponse;
import builders.UserBuilder;
import generator.RandomData;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.apache.http.HttpStatus.*;

public class OrderCreateTests {

    UserBuilder userBuilder = new UserBuilder();

    static HashMap<String, String> data = new RandomData().randomCredentials();
    static List <String> ingredient = new ArrayList<>();

    @Before
    public void prepareData() {
        data.put("token", userBuilder.getTokenAfterCreateUser(
                data.get("email"),
                data.get("password"),
                data.get("name")));
        ingredient = userBuilder.getRandomIngridient(2);
    }

    @After
    public void deleteUser() {
            userBuilder.deleteUserWithAccessToken(data.get("token"));
    }

    @Test
    public void createOrderAuthUser() {
        userBuilder.authUserBody(
                data.get("email"),
                data.get("password"),
                data.get("name"));
        CreateOrderResponse createOrderResponse = userBuilder.createOrderBody(ingredient,data.get("token"));
        Assert.assertEquals("true",createOrderResponse.getSuccess());
    }

    @Test
    public void createOrderNoAuthUser() {
        CreateOrderResponse createOrderResponse = userBuilder.createOrderBody(ingredient,data.get("token"));
        Assert.assertEquals("false",createOrderResponse.getSuccess());
    }

    @Test
    public void createOrderNoIngredient() {
        List<String> noIngredient = new ArrayList<>();
        String message = userBuilder.createOrderErrorMessage(noIngredient,data.get("token"));
        Assert.assertEquals("Ingredient ids must be provided",message);
    }

    @Test
    public void createOrderNoIngredientResponseCode() {
        List<String> noIngredient = new ArrayList<>();
        Response response = userBuilder.createOrderResponse(noIngredient,data.get("token"));
        Assert.assertEquals(SC_BAD_REQUEST,response.statusCode());
    }

    @Test
    public void createOrderWrongIngredient() {
        List<String> noIngredient = new ArrayList<>();
        noIngredient.add(RandomStringUtils.randomNumeric(8));
        Response response = userBuilder.createOrderResponse(noIngredient,data.get("token"));
        Assert.assertEquals(SC_INTERNAL_SERVER_ERROR,response.statusCode());
    }
}
