package bg.softuni.mobilele.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private static final String ANONYMOUS = "anonymous";

    private String name;
    private boolean isAnonymous;

    public CurrentUser() {
        this.name = ANONYMOUS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }
}
