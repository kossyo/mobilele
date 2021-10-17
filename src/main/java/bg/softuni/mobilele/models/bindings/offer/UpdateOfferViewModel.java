package bg.softuni.mobilele.models.bindings.offer;

import bg.softuni.mobilele.models.dtos.ModelServiceModel;
import bg.softuni.mobilele.models.entities.enums.EngineType;
import bg.softuni.mobilele.models.entities.enums.TransmissionType;

import java.math.BigDecimal;
import java.util.List;

public class UpdateOfferViewModel {

    private long offerId;

    private String description;

    private String engineType;

    private String imageUrl;

    private Integer mileage;

    private BigDecimal price;

    private String transmissionType;

    private Integer year;
    private ModelServiceModel model;

    private Long brandId;
    private List<ModelServiceModel> models;
    private List<TransmissionType> transmissionTypes;
    private List<EngineType> engineTypes;

    public void setModels(List<ModelServiceModel> models) {
        this.models = models;
    }

    public List<TransmissionType> getTransmissionTypes() {
        return transmissionTypes;
    }

    public void setTransmissionTypes(List<TransmissionType> transmissionTypes) {
        this.transmissionTypes = transmissionTypes;
    }

    public List<EngineType> getEngineTypes() {
        return engineTypes;
    }

    public void setEngineTypes(List<EngineType> engineTypes) {
        this.engineTypes = engineTypes;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
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

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public ModelServiceModel getModel() {
        return model;
    }

    public void setModel(ModelServiceModel model) {
        this.model = model;
    }

    public List<ModelServiceModel> getModels() {
        return models;
    }

    //    public List<ModelServiceModel> getModels() {
//        return models;
//    }
//
//    public void setModels(List<ModelServiceModel> models) {
//        this.models = models;
//    }
//
//    public List<TransmissionType> getTransmissionTypes() {
//        return transmissionTypes;
//    }
//
//    public void setTransmissionTypes(List<TransmissionType> transmissionTypes) {
//        this.transmissionTypes = transmissionTypes;
//    }
//
//    public List<EngineType> getEngineTypes() {
//        return engineTypes;
//    }
//
//    public void setEngineTypes(List<EngineType> engineTypes) {
//        this.engineTypes = engineTypes;
//    }
}
