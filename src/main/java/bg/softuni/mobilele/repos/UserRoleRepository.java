package bg.softuni.mobilele.repos;

import bg.softuni.mobilele.models.entities.UserRole;
import bg.softuni.mobilele.models.entities.enums.UserRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findUserRoleByRole(UserRoleType userRoleType);
    List<UserRole> findAll();
}
