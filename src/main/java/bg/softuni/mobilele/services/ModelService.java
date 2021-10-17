package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.dtos.ModelServiceModel;
import bg.softuni.mobilele.models.entities.Model;

import java.util.List;

public interface ModelService {
    List<ModelServiceModel> findAllByBrandId(Long id);
    List<ModelServiceModel> findAll();

    Model findByName(String name);

//     findBrandByModel(String model);
}
