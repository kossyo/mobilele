package bg.softuni.mobilele.services.impl;

import bg.softuni.mobilele.models.dtos.BrandDto;
import bg.softuni.mobilele.models.entities.Brand;
import bg.softuni.mobilele.repos.BrandRepository;
import bg.softuni.mobilele.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper, ModelMapper modelMapper1) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper1;
    }

    @Override
    public List<BrandDto> findAll() {

        List<BrandDto> brandDtos = new ArrayList<>();
        List<Brand> brandEntities = brandRepository.findAll();

        for (Brand brandEntity : brandEntities) {
            BrandDto brandDto = modelMapper.map(brandDtos, BrandDto.class);
            brandDtos.add(brandDto);
        }
        return brandDtos;
    }
}
