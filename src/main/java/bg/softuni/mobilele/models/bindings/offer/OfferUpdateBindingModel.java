package bg.softuni.mobilele.models.bindings.offer;

import bg.softuni.mobilele.models.dtos.OfferDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class OfferUpdateBindingModel {

    private Long offerId;

    @NotNull(message = "mdl rqrd")
    @Length(min = 1, message = "mdl rqrd")
    private String model;

    @NotNull
    @Length(min = 2)
    private String description;

    @NotNull
    @Length(min = 1)
    private String engineType;

    @NotNull
    @Length(min = 1)
    private String imageUrl;

    @NotNull
    @Min(1)
    private int mileage;

    @NotNull
    @Min(1)
    private BigDecimal price;

    @NotNull
    @Length(min = 1)
    private String transmissionType;

    @NotNull
    @Min(1900)
    @Max(2099)
    private int year;

    private OfferDto offer;

    public OfferDto getOffer() {
        return offer;
    }

    public void setOffer(OfferDto offer) {
        this.offer = offer;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }




}