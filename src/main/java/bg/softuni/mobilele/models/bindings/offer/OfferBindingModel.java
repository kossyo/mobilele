package bg.softuni.mobilele.models.bindings.offer;

import bg.softuni.mobilele.models.dtos.ModelDto;
import bg.softuni.mobilele.models.entities.enums.EngineType;
import bg.softuni.mobilele.models.entities.enums.TransmissionType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public abstract class OfferBindingModel {

    protected List<ModelDto> models;

    protected List<EngineType> engineTypes;

    protected List<TransmissionType> transmissionTypes;

    @Length(min = 1)
    protected  String model;

    @Length(min = 2)
    protected  String description;

    @Length(min = 1)
    protected String engineType;

    @Length(min = 1)
    protected String imageUrl;

    @NotNull
    protected int mileage;

    @NotNull
    protected BigDecimal price;

    @Length(min = 1)
    protected String transmissionType;

    @NotNull
    protected int year;

    public List<ModelDto> getModels() {
        return models;
    }

    public void setModels(List<ModelDto> models) {
        this.models = models;
    }

    public List<EngineType> getEngineTypes() {
        return engineTypes;
    }

    public void setEngineTypes(List<EngineType> engineTypes) {
        this.engineTypes = engineTypes;
    }

    public List<TransmissionType> getTransmissionTypes() {
        return transmissionTypes;
    }

    public void setTransmissionTypes(List<TransmissionType> transmissionTypes) {
        this.transmissionTypes = transmissionTypes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
