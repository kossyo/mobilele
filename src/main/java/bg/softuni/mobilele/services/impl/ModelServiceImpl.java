package bg.softuni.mobilele.services.impl;

import bg.softuni.mobilele.models.dtos.ModelDto;
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
    public List<ModelDto> findAllByBrandId(Long id) {
        List<ModelDto> modelDtos = new ArrayList<>();
        Optional<Brand> brand = brandRepository.findById(id);
        List<Model> models = modelRepository.findModelsByBrand(brand.get());
        for (Model model : models) {
            ModelDto modelDto = new ModelDto();
            modelMapper.map(model, modelDto);
            modelDtos.add(modelDto);
        }
        return modelDtos;
    }
}
