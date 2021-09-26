package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.bindings.RoleBindingModel;
import bg.softuni.mobilele.models.entities.Role;
import bg.softuni.mobilele.models.entities.enums.RoleType;

import java.util.List;

public interface RoleService {
    List<String> findAllAsStrings();

    List<Role> findAllAsEntities();

    Role findUserRoleByRole(RoleType roleType);
}
