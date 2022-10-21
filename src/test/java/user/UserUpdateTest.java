package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import client.UserClient;
import pojo.User;

import static org.hamcrest.Matchers.*;

public class UserUpdateTest {
    private User user;
    private Response response;
    private final UserClient userClient = new UserClient();
    private String token, email, password, name;

    @Before
    public void setup() {
        user = User.createRandomUser();
        response = userClient.createUser(user);
        token = response.then().extract().body().path("accessToken");
    }

    @Test
    @DisplayName("Меняем email и name аккаунта с авторизацией")
    public void shouldUpdateEmailAndName() {
        email = user.getEmail();
        name = user.getName();
        user.setEmail(email + "email");
        user.setName(name + "name");
        response = userClient.updateUser(user, token);
        user.setEmail(email);
        user.setName(name);
        response.then().assertThat().body("success", equalTo(true))
                .and().statusCode(200);
    }

    @Test
    @DisplayName("Меняем password аккаунта с авторизацией")
    public void shouldUpdatePassword() {
        password = user.getPassword();
        user.setPassword(password + "password");
        response = userClient.updateUser(user, token);
        user.setPassword(password);
        response.then().assertThat().body("success", equalTo(true))
                .and().statusCode(200);
    }

    @Test
    @DisplayName("Меняем email и name аккаунта без авторизации")
    public void updateEmailAndNameShouldBeError() {
        email = user.getEmail();
        name = user.getName();
        user.setEmail(email + "email");
        user.setName(name + "name");
        response = userClient.updateUser(user, "null");
        user.setEmail(email);
        user.setName(name);
        response.then().assertThat().body("success", equalTo(false))
                .and().statusCode(401);
    }

    @Test
    @DisplayName("Меняем password аккаунта без авторизации")
    public void updatePasswordShouldBeError() {
        password = user.getPassword();
        user.setPassword(password + "password");
        response = userClient.updateUser(user, "null");
        user.setPassword(password);
        response.then().assertThat().body("success", equalTo(false))
                .and().statusCode(401);
    }

    @After
    public void teardown() {
        userClient.removeUser(token);
    }
}
