package generator;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import java.util.HashMap;

public class RandomData {

Faker faker = new Faker();
    @Step("Генерация случайных данных для создания пользователя")
    public HashMap<String, String> randomCredentials() {
        HashMap<String, String> map = new HashMap<>();
        map.put("email", faker.internet().emailAddress());
        map.put("password", faker.internet().password());
        map.put("name", faker.name().firstName());
        return map;
    }
}
