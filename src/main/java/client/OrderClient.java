package client;

import config.Config;
import io.restassured.response.Response;
import pojo.Order;

import static io.restassured.RestAssured.given;

public class OrderClient {
    public Response createOrder(Order order, String token) {

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .baseUri(Config.URL)
                .body(order)
                .post(Config.ORDERS);
    }

    public Response getUserOrders(String token) {

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .baseUri(Config.URL)
                .get(Config.ORDERS);
    }
}
