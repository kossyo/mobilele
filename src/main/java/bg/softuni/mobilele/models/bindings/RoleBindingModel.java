package bg.softuni.mobilele.models.bindings;

import bg.softuni.mobilele.models.dtos.BaseDto;

public class RoleBindingModel extends BaseDto {

    private String roleType;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
