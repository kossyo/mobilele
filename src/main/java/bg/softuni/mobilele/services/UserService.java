package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.bindings.user.UserLoginBindingModel;
import bg.softuni.mobilele.models.service.UserRegisterServiceModel;

public interface UserService {
    boolean register(UserRegisterServiceModel UserRegisterSericeModel);

    boolean login(UserLoginBindingModel userLoginBindingModel);
}
