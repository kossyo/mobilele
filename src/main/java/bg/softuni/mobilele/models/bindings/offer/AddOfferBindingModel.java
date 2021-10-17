package bg.softuni.mobilele.models.bindings.offer;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddOfferBindingModel {

    @NotNull(message = "model required")
    @Size(min = 1, message = "model required")
    private String model;

    @NotNull
    @Size(min = 2)
    private String description;

    //todo: this shouldn't be String, should be the EnumType instead
    @NotNull
    @Size(min = 1)
    private String engineType;

    @NotNull
    @Size(min = 1)
    private String imageUrl;

    @NotNull
    @Min(1)
    private int mileage;

    @NotNull
    @Min(1)
    private BigDecimal price;


    //todo: this shouldn't be String, should be the EnumType instead
    @NotNull
    @Size(min = 1)
    private String transmissionType;

    @NotNull
    @Min(1900)
    @Max(2099)
    private int year;


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
