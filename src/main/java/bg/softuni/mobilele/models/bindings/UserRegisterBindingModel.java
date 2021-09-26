package bg.softuni.mobilele.models.bindings;

public class UserRegisterBindingModel {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

//    private List<String> roles;

    private String rolesSelected;

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

    public void setPassword(String password) {
        this.password = password;
    }
//
//    public List<String> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<String> roles) {
//        this.roles = roles;
//    }

    public String getRolesSelected() {
        return rolesSelected;
    }

    public void setRolesSelected(String rolesSelected) {
        this.rolesSelected = rolesSelected;
    }
}
