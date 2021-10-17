package bg.softuni.mobilele.models.bindings.offer;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class UpdateOfferBindingModel {

    private Long offerId;

//    @NotBlank(message = "model required")
    @Size(min = 1, message = "model required")
    private String model;

    @NotNull
    @Size(min = 2)
    private String description;

    @NotNull
    @Size(min = 1)
    private String engineType;

    @NotNull
    @Size(min = 1)
    private String imageUrl;

    @NotNull
    @Min(1)
    private Integer mileage;

    @NotNull
    @Min(1)
    private BigDecimal price;

    @NotNull
    @Size(min = 1)
    private String transmissionType;

    @NotNull
    @Min(1900)
    @Max(2099)
    private Integer year;

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
}
