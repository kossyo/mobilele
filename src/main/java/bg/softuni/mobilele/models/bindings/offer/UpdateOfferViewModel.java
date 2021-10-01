package bg.softuni.mobilele.models.bindings.offer;

import bg.softuni.mobilele.models.dtos.ModelDto;
import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.models.entities.enums.EngineType;
import bg.softuni.mobilele.models.entities.enums.TransmissionType;

import java.math.BigDecimal;
import java.util.List;

public class UpdateOfferViewModel {

    private long offerId;

    private OfferDto offerDto;

    private String description;

    private EngineType engineType;

    private String imageUrl;

    private int mileage;

    private BigDecimal price;

    private TransmissionType transmissionType;

    private int year;
    private String model;

    private List<ModelDto> models;
    private List<TransmissionType> transmissionTypes;
    private List<EngineType> engineTypes;

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

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
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

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ModelDto> getModels() {
        return models;
    }

    public void setModels(List<ModelDto> models) {
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

    public OfferDto getOfferDto() {
        return offerDto;
    }

    public void setOfferDto(OfferDto offerDto) {
        this.offerDto = offerDto;
    }
}
