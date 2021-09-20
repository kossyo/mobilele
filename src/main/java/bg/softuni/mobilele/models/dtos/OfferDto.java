package bg.softuni.mobilele.models.dtos;

import bg.softuni.mobilele.models.entities.enums.EngineType;
import bg.softuni.mobilele.models.entities.enums.TransmissionType;

import java.math.BigDecimal;

public class OfferDto {
    private Long id;

    private String description;

    private EngineType engineType;

    private String imageUrl;

    private int mileage;

    private BigDecimal price;

    private TransmissionType transmission;

    private int year;

    private ModelDto modelDto;

    private UserDto sellerDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TransmissionType getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ModelDto getModel() {
        return modelDto;
    }

    public void setModel(ModelDto model) {
        this.modelDto = model;
    }

    public UserDto getSeller() {
        return sellerDto;
    }

    public void setSeller(UserDto seller) {
        this.sellerDto = seller;
    }

    public ModelDto getModelDto() {
        return modelDto;
    }
}
