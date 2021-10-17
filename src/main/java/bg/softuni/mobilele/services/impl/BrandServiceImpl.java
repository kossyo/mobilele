package bg.softuni.mobilele.services.impl;

import bg.softuni.mobilele.models.dtos.BrandDto;
import bg.softuni.mobilele.models.dtos.ModelServiceModel;
import bg.softuni.mobilele.models.entities.Brand;
import bg.softuni.mobilele.models.entities.Model;
import bg.softuni.mobilele.models.service.BrandServiceModel;
import bg.softuni.mobilele.repos.BrandRepository;
import bg.softuni.mobilele.repos.ModelRepository;
import bg.softuni.mobilele.services.BaseService;
import bg.softuni.mobilele.services.BrandService;
import bg.softuni.mobilele.services.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl extends BaseService implements BrandService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final ModelService modelService;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper, BrandRepository brandRepository, ModelService modelService, ModelMapper modelMapper1) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.modelService = modelService;
        this.modelMapper = modelMapper1;
    }

    @Override
    public List<BrandDto> findAll() {

        List<BrandDto> brandDtos = new ArrayList<>();
        List<Model> modelEltities = modelRepository.findAll();

        for (Model modelEntity : modelEltities) {
            Brand brandEntity = modelEntity.getBrand();
            Optional<BrandDto> brandDtoByNameOpt = findBrandDtoByName(brandEntity.getName(), brandDtos);
            BrandDto brandDto;
            if (brandDtoByNameOpt.isEmpty()) {
                brandDto = new BrandDto();
                modelMapper.map(brandEntity, brandDto);
                brandDtos.add(brandDto);
                brandDtoByNameOpt = Optional.of(brandDto);
            }
            ModelServiceModel modelServiceModel = new ModelServiceModel();
            modelMapper.map(modelEntity, modelServiceModel);
            brandDto = brandDtoByNameOpt.get();
            brandDto.addModel(modelServiceModel);
        }
        return brandDtos;
    }

    private  static Optional<BrandDto> findBrandDtoByName(String name, List<BrandDto> brandDtos) {

        return brandDtos
                .stream()
                .filter(brandDto -> name.equals(brandDto.getName()))
                .findAny();
    }


}
