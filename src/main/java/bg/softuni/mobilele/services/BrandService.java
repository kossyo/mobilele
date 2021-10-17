package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.dtos.BrandDto;
import bg.softuni.mobilele.models.dtos.ModelServiceModel;
import bg.softuni.mobilele.models.entities.Brand;
import bg.softuni.mobilele.models.service.BrandServiceModel;

import java.util.List;

public interface BrandService {
    List<BrandDto> findAll();

}
