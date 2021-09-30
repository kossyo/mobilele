package bg.softuni.mobilele.models.bindings.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserLoginBindingModel {

    @NotNull
    @Length(min = 2, message = "username too short")
    private String username;

    @NotNull
    @Length(min = 3, message = "password too short")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
