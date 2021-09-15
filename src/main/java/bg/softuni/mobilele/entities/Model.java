package bg.softuni.mobilele.entities;

import bg.softuni.mobilele.entities.enums.ModelCategory;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private ModelCategory category;

    @Column(length = 512)

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;

    @Column
    private int startYear;

    @Column
    private int EndYear;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelCategory getCategory() {
        return category;
    }

    public void setCategory(ModelCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return EndYear;
    }

    public void setEndYear(int endYear) {
        EndYear = endYear;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", imageUrl='" + imageUrl + '\'' +
                ", brand=" + brand +
                ", startYear=" + startYear +
                ", EndYear=" + EndYear +
                '}';
    }
}
