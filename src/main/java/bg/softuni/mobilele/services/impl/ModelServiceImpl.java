package bg.softuni.mobilele.services.impl;

import bg.softuni.mobilele.models.dtos.ModelServiceModel;
import bg.softuni.mobilele.models.entities.Brand;
import bg.softuni.mobilele.models.entities.Model;
import bg.softuni.mobilele.repos.BrandRepository;
import bg.softuni.mobilele.repos.ModelRepository;
import bg.softuni.mobilele.services.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ModelServiceModel> findAllByBrandId(Long id) {
        List<ModelServiceModel> modelServiceModels = new ArrayList<>();
        Optional<Brand> brand = brandRepository.findById(id);
        List<Model> models = new ArrayList<>();

        if(brand.isPresent()){
            models = modelRepository.findModelsByBrand(brand.get());
            for (Model model : models) {
                ModelServiceModel modelServiceModel = new ModelServiceModel();
                modelMapper.map(model, modelServiceModel);
                modelServiceModels.add(modelServiceModel);
            }
        }
        return modelServiceModels;
    }

    @Override
    public List<ModelServiceModel> findAll() {
        List<Model> modelEntities = modelRepository.findAll();
        List<ModelServiceModel> modelServiceModels = new ArrayList<>();
        for (Model modelEntity : modelEntities) {
            ModelServiceModel modelServiceModel = new ModelServiceModel();
            modelMapper.map(modelEntity, modelServiceModel);
            modelServiceModels.add(modelServiceModel);
        }
        return modelServiceModels;
    }

    @Override
    public Model findByName(String name) {
        return modelRepository.findModelByName(name);
    }


}
