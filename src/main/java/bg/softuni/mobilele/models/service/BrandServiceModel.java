package bg.softuni.mobilele.models.service;

import bg.softuni.mobilele.models.dtos.ModelServiceModel;

import java.util.List;

public class BrandServiceModel {

    private Long id;
    private String name;

    private List<ModelServiceModel> models;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelServiceModel> getModels() {
        return models;
    }

    public void setModels(List<ModelServiceModel> models) {
        this.models = models;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
