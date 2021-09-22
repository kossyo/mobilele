package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.dtos.ModelDto;
import bg.softuni.mobilele.models.entities.Model;

import java.util.List;

public interface ModelService {
    List<ModelDto> findAllByBrandId(Long id);
    List<ModelDto> findAll();
}
