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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {

    //    private CurrentUser currentUser;
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

    @PostMapping("confirmLogin")
    public String confirmLogin(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:/users/login";
        }

        if (!userService.login(userLoginBindingModel)) {
            redirectAttributes.addFlashAttribute("username", userLoginBindingModel.getUsername());
            redirectAttributes.addFlashAttribute("invalidCredentials", false);
            return "redirect:/users/login";
        }
        return "redirect:/brands/all";
    }

    @GetMapping("logout")
    public String confirmLogout(@ModelAttribute UserLoginBindingModel userLoginBindingModel) {
        userService.logout(userLoginBindingModel.getUsername());
        return "index";
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
