package bg.softuni.mobilele.services.impl;

import bg.softuni.mobilele.models.dtos.BrandDto;
import bg.softuni.mobilele.models.dtos.ModelDto;
import bg.softuni.mobilele.models.entities.Brand;
import bg.softuni.mobilele.models.entities.Model;
import bg.softuni.mobilele.repos.ModelRepository;
import bg.softuni.mobilele.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final ModelRepository modelRepository;
    private ModelMapper modelMapper;

    public BrandServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper, ModelMapper modelMapper1) {
        this.modelRepository = modelRepository;
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
            ModelDto modelDto = new ModelDto();
            modelMapper.map(modelEntity, modelDto);
            brandDto = brandDtoByNameOpt.get();
            brandDto.addModel(modelDto);
        }
        return brandDtos;
    }

    private static Optional<BrandDto> findBrandDtoByName(String name, List<BrandDto> brandDtos) {

        return brandDtos
                .stream()
                .filter(brandDto -> name.equals(brandDto.getName()))
                .findAny();
    }
}
