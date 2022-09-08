package builders;

import pojo.request.AuthUserRequest;
import pojo.request.CreateOrderRequest;
import pojo.request.CreateUserRequest;
import pojo.request.EditUserRequest;
import pojo.response.*;
import generator.RandomData;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import requests.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class UserBuilder {
    RandomData randomData = new RandomData();

    @Step("Создание учетной записи пользователя через запрос POST /api/auth/register")
    public Response createUserResponse(String email, String password, String name) {
        return new PostCreateUser().UserCreateReponse(new CreateUserRequest(email, password, name));
    }

    @Step("Создание учетной записи пользователя через запрос POST /api/auth/register")
    public CreateUserResponse createUserBody(String email, String password, String name) {
        return new PostCreateUser().UserCreateBody(new CreateUserRequest(email, password, name));
    }

    @Step("Создание учетной записи пользователя через запрос POST /api/auth/register")
    public String getTokenAfterCreateUser(String email, String password, String name) {
        Response response = new PostCreateUser().UserCreateReponse(new CreateUserRequest(email, password, name));
        return response.body().jsonPath().getString("accessToken");
    }

    @Step("Создание учетной записи пользователя с генерированным учетными данными через запрос POST /api/auth/register")
    public HashMap<String, String> createUserRandomCredentials() {
        HashMap<String, String> map = randomData.randomCredentials();
        new PostCreateUser().UserCreateBody(new CreateUserRequest(map.get("email"), map.get("password"), map.get("name")));
        return map;
    }

    @Step("Авторизация пользователя через запрос POST /api/auth/login")
    public AuthUserResponse authUserBody(String email, String password, String name) {
        return new PostAuthUser().AuthUserBody(new AuthUserRequest(email, password, name));
    }

    @Step("Авторизация пользователя через запрос POST /api/auth/login")
    public Response AuthUserResponse(String email, String password, String name) {
        return new PostAuthUser().AuthUserResponse(new AuthUserRequest(email, password, name));
    }

    @Step("Авторизация пользователя через запрос POST /api/auth/login")
    public void authUser(HashMap<String,String> map) {
         new PostAuthUser().AuthUserBody(new AuthUserRequest(map.get("email"), map.get("password"), map.get("name")));
    }

    @Step("Получение Access Token через запрос POST /api/auth/login")
    public String getAuthUserAccessToken(HashMap<String, String> map) {
        AuthUserRequest createUserResponse = new AuthUserRequest(map.get("email"), map.get("password"), map.get("name"));
        AuthUserResponse authUserResponse = new PostAuthUser().AuthUserBody(createUserResponse);
        return authUserResponse.getAccessToken();
    }

    @Step("Удаление пользователя через запрос Delete /api/auth/user c использованием Access Token")
    public void deleteUserWithAccessToken(String token) {
        new DeleteUser().UserDelete(token);
    }

    @Step("Получение случайного ингридиента")
    public List<String> getRandomIngridient(int amount) {
        IngredientsResponse ingredientsResponse = new GetIngredients().IngredientsRequest().getBody().as(IngredientsResponse.class);
        List<IngredientsResponse.Data> data = ingredientsResponse.getData();
        List<String> list = data.stream().map(IngredientsResponse.Data::get_id).collect(Collectors.toList());
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            resultList.add(i, list.get((int) (Math.random() * list.size())));
        }
        return resultList;
    }

    @Step("Создание заказа через запрос POST /api/orders")
    public CreateOrderResponse createOrderBody(List<String> ingredients, String accessToken) {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setIngredients(ingredients);
        return new PostCreateOrder().OrderCreateBody(createOrderRequest, accessToken);
    }

    @Step("Создание заказа через запрос POST /api/orders")
    public Response createOrderResponse(List<String> ingredients, String accessToken) {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setIngredients(ingredients);
        return new PostCreateOrder().OrderCreateReponse(createOrderRequest, accessToken);
    }

    @Step("Получение текста ошибки (message) после запроса POST /api/orders")
    public String createOrderErrorMessage(List<String> ingredients, String accessToken) {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setIngredients(ingredients);
        Response response = new PostCreateOrder().OrderCreateReponse(createOrderRequest, accessToken);
        System.out.println(response.asString());
        return response.body().jsonPath().getString("message");
    }

    @Step("Изменение данных пользователя через запрос POST /api/orders")
    public EditUserResponse editUserBody(String accessToken,String email, String password, String name) {
        EditUserRequest editUserRequest = new EditUserRequest(email,password,name);
        return new PostEditUser().UserEditBody(editUserRequest, accessToken);
    }

    @Step("Изменение данных пользователя через запрос POST /api/orders")
    public Response editUserResponse(String accessToken,String email, String password, String name) {
        EditUserRequest editUserRequest = new EditUserRequest(email,password,name);
        return new PostEditUser().UserEditReponse(editUserRequest, accessToken);
    }

    @Step("Изменение данных пользователя через запрос POST /api/orders")
    public UserOrdersResponse userOrdersBody(String accessToken) {
        return new GetUserOrders().UserOrdersBody(accessToken);
    }

    @Step("Изменение данных пользователя через запрос POST /api/orders")
    public Response userOrdersResponse(String accessToken) {
        return new GetUserOrders().UserOrdersResponse(accessToken);
    }

}