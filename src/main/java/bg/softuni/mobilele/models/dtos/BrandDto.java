package bg.softuni.mobilele.models.dtos;

import java.util.ArrayList;
import java.util.List;

public class BrandDto {
    private String name;
    private List<ModelDto> models;

    public BrandDto(){
        this.models = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelDto> getModels() {
        return models;
    }

    public void setModels(List<ModelDto> models) {
        this.models = models;
    }
    public void addModel(ModelDto model) {
        this.models.add(model);
    }
}
