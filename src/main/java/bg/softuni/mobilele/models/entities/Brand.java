package bg.softuni.mobilele.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BRANDS")
public class Brand extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    private List<Model> models;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
//                ", models=" + models +
                '}';
    }
}
