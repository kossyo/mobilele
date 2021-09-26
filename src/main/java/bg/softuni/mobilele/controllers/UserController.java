package bg.softuni.mobilele.controllers;

import bg.softuni.mobilele.models.bindings.user.UserLoginBindingModel;
import bg.softuni.mobilele.models.bindings.user.UserRegisterBindingModel;
import bg.softuni.mobilele.models.service.UserRegisterServiceModel;
import bg.softuni.mobilele.repos.RoleRepository;
import bg.softuni.mobilele.security.CurrentUser;
import bg.softuni.mobilele.services.RoleService;
import bg.softuni.mobilele.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {

    private CurrentUser currentUser;
    private final RoleService roleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(CurrentUser currentUser, RoleRepository roleRepository, RoleService roleService, UserService userService, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.roleService = roleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "auth-login";
    }

    @PostMapping("confirmLogin")
    public String confirmLogin(Model model, @ModelAttribute UserLoginBindingModel userLoginBindingModel
            , BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!userService.login(userLoginBindingModel)) {
            userLoginBindingModel.setPassword(null);
            model.addAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);

            return "redirect:/users/login";
        }
        return "brands";
    }

    @GetMapping("register")
    public String register(Model model) {
        List<String> roles = roleService.findAllAsStrings();
        if (!roles.isEmpty()) {
            model.addAttribute("roles", roles);
        }

        return "auth-register";
    }

    @PostMapping("confirmRegister")
    public String confirmRegister(@ModelAttribute UserRegisterBindingModel userRegisterBindingModel) {

        UserRegisterServiceModel userRegisterServiceModel =
                modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);
        boolean isRegistered = userService.register(userRegisterServiceModel);
        return "auth-register";

    }
}
