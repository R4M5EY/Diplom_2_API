package user;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import client.UserClient;
import pojo.User;

import static org.hamcrest.Matchers.*;

public class LoginTest {
    private User user;
    private Response response;
    private final UserClient userClient = new UserClient();
    private String password, email, token;

    @Before
    public void setup() {
        user = User.createRandomUser();
        response = userClient.createUser(user);
        token = response.then().extract().body().path("accessToken");
    }

    @Test
    @DisplayName("Удачно логинимся существующим аккаунтом")
    public void shouldSuccessLogin() {
        response = userClient.loginUser(user = User.getExistUser());
        response.then().assertThat().body("success", equalTo(true))
                .and().statusCode(200);
    }

    @Test
    @DisplayName("Неудачно логинимся с неверным логином и паролем")
    public void loginWithInvalidEmailAndPassword() {
        email = user.getEmail();
        user.setEmail("random@mail.com");
        password = user.getPassword();
        user.setPassword("RandomPassword");
        response = userClient.loginUser(user, token);
        user.setEmail(email);
        user.setPassword(password);
        response.then().assertThat().body("success", equalTo(false))
                .and().statusCode(401);
    }

    @After
    public void teardown() {
        userClient.removeUser(token);
    }
}
