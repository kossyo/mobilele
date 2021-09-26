package bg.softuni.mobilele.models.bindings;

import bg.softuni.mobilele.models.dtos.ModelDto;
import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.models.entities.enums.EngineType;
import bg.softuni.mobilele.models.entities.enums.TransmissionType;

import java.math.BigDecimal;
import java.util.List;

public class OfferUpdateView {

    OfferDto offer;

    List<ModelDto> models;

    List<EngineType> engineTypes;

    List<TransmissionType> transmissionTypes;

    private Long offerId;

    private String description;

    private String engineType;

    private String imageUrl;

    private int mileage;

    private BigDecimal price;

    private String transmissionType;

    private int year;

    private String model;

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

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public OfferDto getOffer() {
        return offer;
    }

    public void setOffer(OfferDto offer) {
        this.offer = offer;
    }

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
}
