package bg.softuni.mobilele.models.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private boolean isActive;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public User(){
        super.setCreated(Instant.now());
        super.setUpdated(Instant.now());
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", isActive=" + isActive +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + "N/A" + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", roles=" + roles +
                '}';
    }
}
