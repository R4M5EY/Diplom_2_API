package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import client.UserClient;
import pojo.User;

import static org.hamcrest.Matchers.*;

public class UserRegTest {
    private User user;
    private Response response;
    private final UserClient userClient = new UserClient();

    @Test
    @DisplayName("Регистрация нового аккаунта")
    public void successRegTest() {
        user = User.createRandomUser();
        response = userClient.createUser(user);
        String token = response.then().extract().body().path("accessToken");
        userClient.removeUser(token);
        response.then().assertThat().body("accessToken", notNullValue())
                .and().statusCode(200);
    }

    @Test
    @DisplayName("Регистрация уже существующего аккаунта")
    public void existRegTest() {
        user = User.getExistUser();
        response = userClient.createUser(user);
        response.then().assertThat().body("message", equalTo("User already exists"))
                .and().statusCode(403);
    }

    @Test
    @DisplayName("Регистрация акккаунта без имени")
    public void regWithoutNameTest() {
        user = User.createUserWithoutName();
        response = userClient.createUser(user);
        response.then().assertThat().body("message", equalTo("Email, password and name are required fields"))
                .and().statusCode(403);
    }

    @Test
    @DisplayName("Регистрация акккаунта без почты")
    public void regWithoutEmailTest() {
        user = User.createUserWithoutEmail();
        response = userClient.createUser(user);
        response.then().assertThat().body("message", equalTo("Email, password and name are required fields"))
                .and().statusCode(403);
    }

    @Test
    @DisplayName("Регистрация акккаунта без пароля")
    public void regWithoutPassword() {
        user = User.createUserWithoutPassword();
        response = userClient.createUser(user);
        response.then().assertThat().body("message", equalTo("Email, password and name are required fields"))
                .and().statusCode(403);
    }
}
