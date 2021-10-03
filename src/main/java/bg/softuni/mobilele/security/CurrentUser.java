package bg.softuni.mobilele.security;

import bg.softuni.mobilele.models.entities.enums.RoleType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;


//todo: replace all 'action'-s by 'th:action'-s

//todo: select menu with the cookie doesn't work
//add empty constructors to dtos

@Component("currentUser")
@SessionScope
public class CurrentUser {
    private static final String ANONYMOUS = "anonymous";

    private String name;
    private boolean isLoggedIn;
    private List<RoleType> roles;

    public CurrentUser() {
        this.name = ANONYMOUS;
        this.roles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean isAdmin() {
        return roles.contains(RoleType.ADMIN);
    }

    public void setRoles(List<RoleType> roles) {
        this.roles = roles;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
