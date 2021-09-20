package bg.softuni.mobilele.models.dtos;

import bg.softuni.mobilele.models.entities.enums.UserRoleType;

public class UserRoleDto {

    private Long id;

    private UserRoleType role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserRoleType getRole() {
        return role;
    }

    public void setRole(UserRoleType role) {
        this.role = role;
    }
}
