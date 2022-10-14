package client;

import config.Config;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.User;

import static io.restassured.RestAssured.*;

public class UserClient {
    public Response createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Config.URL)
                .body(user).post(Config.REGISTER);
    }

    public void removeUser(String token) {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .baseUri(Config.URL).delete(Config.USER);
    }

    public Response loginUser(User user, String token) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .baseUri(Config.URL)
                .body(user).post(Config.LOGIN);
    }
    public Response loginUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(Config.URL)
                .body(user).post(Config.LOGIN);
    }

    public Response updateUser(User newUser, String token) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .baseUri(Config.URL)
                .body(newUser).patch(Config.USER);
    }
}
