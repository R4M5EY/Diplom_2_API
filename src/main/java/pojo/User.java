package pojo;

import org.apache.commons.lang3.RandomStringUtils;

public class User {
    private String email, password, name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User createRandomUser() {
        return new User(RandomStringUtils.randomAlphanumeric(15)+"@ya.ru", "password", "name");
    }
    public static User getExistUser() {
        return new User("roha@email.com", "qwertyuiop2000", "Roha");
    }
    public static User createUserWithoutName() {
        return new User(RandomStringUtils.randomAlphanumeric(15)+"@ya.ru", "password", null);
    }
    public static User createUserWithoutEmail() {
        return new User(null, "password", RandomStringUtils.randomAlphabetic(10));
    }
    public static User createUserWithoutPassword() {
        return new User(RandomStringUtils.randomAlphanumeric(15)+"@ya.ru", null, "name");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
