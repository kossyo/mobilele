package bg.softuni.mobilele.repos;

import bg.softuni.mobilele.models.entities.Role;
import bg.softuni.mobilele.models.entities.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findUserRoleByRole(RoleType roleType);
    List<Role> findAll();
}
