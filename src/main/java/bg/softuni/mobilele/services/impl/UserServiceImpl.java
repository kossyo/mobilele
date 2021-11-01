package bg.softuni.mobilele.services.impl;

import bg.softuni.mobilele.models.entities.User;
import bg.softuni.mobilele.models.service.UserRegisterServiceModel;
import bg.softuni.mobilele.repos.UserRepository;
import bg.softuni.mobilele.services.RoleService;
import bg.softuni.mobilele.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterServiceModel userRegisterServiceModel) {

        Optional<User> userOpt = userRepository.findUserByUsername(userRegisterServiceModel.getUsername());
        if (userOpt.isPresent()) {
            return false;
        }
        User user = modelMapper.map(userRegisterServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()));
        userRepository.save(user);
        return true;
    }
}
