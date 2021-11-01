package bg.softuni.mobilele.models.bindings.user;

public class UserRegisterBindingModel {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String confirmPassword;

//    private String rolesSelected;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRolesSelected() {
//        return rolesSelected;
//    }
//
//    public void setRolesSelected(String rolesSelected) {
//        this.rolesSelected = rolesSelected;
//    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
