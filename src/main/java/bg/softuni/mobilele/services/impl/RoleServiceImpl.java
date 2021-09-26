package bg.softuni.mobilele.services.impl;

import bg.softuni.mobilele.models.bindings.RoleBindingModel;
import bg.softuni.mobilele.models.entities.Role;
import bg.softuni.mobilele.models.entities.enums.RoleType;
import bg.softuni.mobilele.repos.RoleRepository;
import bg.softuni.mobilele.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<String> findAllAsStrings() {

//        List<String> roles = new ArrayList<>();
//        for (Role role : roleRepository.findAll()) {
//            roles.add(role.getRole().toString());
//        }
//        return roles;

        //todo: replace by a Jpa projection interface
        return roleRepository
                .findAll()
                .stream()
                .map(role -> role.getRole().toString())
                .collect(Collectors.toList());
    }

    @Override
    public List<Role> findAllAsEntities() {
        return roleRepository.findAll();
    }

    //todo:replace return type by a dto type
    @Override
    public Role findUserRoleByRole(RoleType roleType){
        return roleRepository.findUserRoleByRole(roleType);
    }
}
