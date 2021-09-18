package bg.softuni.mobilele.config;

import bg.softuni.mobilele.repos.BrandRepository;
import bg.softuni.mobilele.models.entities.BaseEntity;
import bg.softuni.mobilele.models.entities.Brand;
import bg.softuni.mobilele.models.entities.Model;
import bg.softuni.mobilele.models.entities.enums.ModelCategory;
import bg.softuni.mobilele.repos.ModelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DbInitializer implements CommandLineRunner {

    private static final String IMAGE_XTRAIL = "https://images.ams.bg/images/galleries/109571/nissan-x-trail-1460795988_big.jpg";
    private static final String IMAGE_PAJERO = "https://images.ams.bg/images/galleries/109335/mitsubishi-pajero-1460793942_big.jpg";

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    public DbInitializer(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Model xtrail = initModel("X-Trail", ModelCategory.SUV, IMAGE_XTRAIL, 2000, null);
        Brand nissan = initBrand("Nissan");
        Model pajero = initModel("Pajero", ModelCategory.SUV, IMAGE_PAJERO, 1981, 2021);
        Brand mitsubishi = initBrand("Mitsubishi");
        xtrail.setBrand(nissan);
        pajero.setBrand(mitsubishi);

        brandRepository.save(nissan);
        brandRepository.save(mitsubishi);
        modelRepository.save(xtrail);
        modelRepository.save(pajero);
    }

    private Model initModel(String name, ModelCategory category, String imageUrl, Integer startYear, Integer endYear) {
        Model model = new Model();
        model.setName(name);
        model.setCategory(category);
        model.setImageUrl(imageUrl);
        model.setStartYear(startYear);
        model.setEndYear(endYear);
        model.setCreated(Instant.now());
        model.setUpdated(Instant.now());
        return (Model) setTimeInstances(model);
    }

    private Brand initBrand(String name) {
        Brand brand = new Brand();
        brand.setName(name);
        return (Brand) setTimeInstances(brand);
    }

    private static BaseEntity setTimeInstances(BaseEntity entity){
        entity.setCreated(Instant.now());
        entity.setUpdated(Instant.now());
        return entity;
    }
}
