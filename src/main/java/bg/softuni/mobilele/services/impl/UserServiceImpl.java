package bg.softuni.mobilele.services.impl;

import bg.softuni.mobilele.models.bindings.user.UserLoginBindingModel;
import bg.softuni.mobilele.models.entities.Role;
import bg.softuni.mobilele.models.entities.User;
import bg.softuni.mobilele.models.entities.enums.RoleType;
import bg.softuni.mobilele.models.service.UserRegisterServiceModel;
import bg.softuni.mobilele.repos.UserRepository;
import bg.softuni.mobilele.security.CurrentUser;
import bg.softuni.mobilele.services.RoleService;
import bg.softuni.mobilele.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public boolean register(UserRegisterServiceModel userRegisterServiceModel) {

        Optional<User> userOpt = userRepository.findUserByUsername(userRegisterServiceModel.getUsername());
        if (userOpt.isPresent()) {
            return false;
        }
        List<Role> roles = new ArrayList<>();
        User user = modelMapper.map(userRegisterServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));
        String rolesSelected = userRegisterServiceModel.getRolesSelected();

        for (String roleString : rolesSelected.split(",")) {
            Role role = roleService.findUserRoleByRole(RoleType.valueOf(roleString));
            roles.add(role);
        }
        user.setRoles(roles);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean login(UserLoginBindingModel userLoginBindingModel) {

        Optional<User> userOpt = findUserByUsername(userLoginBindingModel.getUsername());
        if (userOpt.isEmpty()) {
            return false;
        }
        User user = userOpt.get();
        if (!passwordEncoder.matches(
                userLoginBindingModel.getPassword(),
                user.getPassword()
        )) {
            return false;
        }
        currentUser.setLoggedIn(true);
        List<Role> roles = user.getRoles();
        List<RoleType> rolesAsd = roles.stream().map(Role::getRole).collect(Collectors.toList());
        currentUser.setRoles(rolesAsd);
        currentUser.setName(user.getUsername());
        return true;
    }

    @Override
    public void logout(String username) {
        currentUser.setLoggedIn(false);
    }

    private Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
