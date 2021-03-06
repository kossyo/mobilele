package bg.softuni.mobilele.models.dtos;

import java.util.ArrayList;
import java.util.List;

public class BrandDto extends BaseDto{
    private String name;
    private List<ModelServiceModel> models;

    public BrandDto(){
        this.models = new ArrayList<>();
    }

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
    public void addModel(ModelServiceModel model) {
        this.models.add(model);
    }
}
