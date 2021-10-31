package bg.softuni.mobilele.controllers;

import bg.softuni.mobilele.repos.RoleRepository;
import bg.softuni.mobilele.services.RoleService;
import bg.softuni.mobilele.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(RoleRepository roleRepository, RoleService roleService, UserService userService, ModelMapper modelMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("login")
    public String login(Model model) {
        if (!model.containsAttribute("username")) {
            model.addAttribute("username", "");
            model.addAttribute("invalidCredentials", true);
        }
        return "auth-login";
    }
}
