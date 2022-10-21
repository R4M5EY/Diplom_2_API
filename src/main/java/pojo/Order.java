package pojo;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<String> ingredients = new ArrayList<>();

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Order(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public static Order getOrderCorrect() {
        return new Order(List.of("61c0c5a71d1f82001bdaaa74","61c0c5a71d1f82001bdaaa6d"));
    }

    public static Order getOrderEmpty() {
        return new Order(List.of());
    }

    public static Order getOrderWrongHash() {
        return new Order(List.of("1","2"));
    }
}
