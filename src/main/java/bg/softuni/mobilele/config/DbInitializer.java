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
    private static final String IMAGE_PATHFINDER = "http://img.autoabc.lv/Nissan-Pathfinder/Nissan-Pathfinder_2010_Apvidus_151130102945_10.jpg";
    private static final String IMAGE_PAJERO = "https://images.ams.bg/images/galleries/109335/mitsubishi-pajero-1460793942_big.jpg";
    private static final String IMAGE_S_CLASS = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/2014_Mercedes-Benz_S550_%28US%29_lwb.jpg/1920px-2014_Mercedes-Benz_S550_%28US%29_lwb.jpg";
    private static final String IMAGE_GLS_CLASS = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Mercedes-Benz_GL_350_BlueTEC_4MATIC_Sport-Paket_AMG_%28X_166%29_%E2%80%93_Frontansicht%2C_31._Dezember_2012%2C_D%C3%BCsseldorf.jpg/1920px-Mercedes-Benz_GL_350_BlueTEC_4MATIC_Sport-Paket_AMG_%28X_166%29_%E2%80%93_Frontansicht%2C_31._Dezember_2012%2C_D%C3%BCsseldorf.jpg";
    private static final String IMAGE_NAVIGATOR = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/2019_Lincoln_Navigator_%27Reserve%27%2C_front_8.29.20.jpg/1920px-2019_Lincoln_Navigator_%27Reserve%27%2C_front_8.29.20.jpg";

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    public DbInitializer(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Brand nissan = initBrand("Nissan");
        Model xtrail = initModel("X-Trail", ModelCategory.SUV, IMAGE_XTRAIL, 2000, null);
        Model pathfinder = initModel("Pathfinder", ModelCategory.SUV, IMAGE_PATHFINDER, 1985, null);
        xtrail.setBrand(nissan);
        pathfinder.setBrand(nissan);
        brandRepository.save(nissan);
        modelRepository.save(xtrail);
        modelRepository.save(pathfinder);

        Brand mitsubishi = initBrand("Mitsubishi");
        Model pajero = initModel("Pajero", ModelCategory.SUV, IMAGE_PAJERO, 1981, 2021);
        pajero.setBrand(mitsubishi);
        brandRepository.save(mitsubishi);
        modelRepository.save(pajero);

        Brand mercedes = initBrand("Mercedes");
        Model sClass = initModel("S Class", ModelCategory.CAR, IMAGE_S_CLASS, 1972, null);
        sClass.setBrand(mercedes);
        Model glsClass = initModel("GLS Class", ModelCategory.SUV, IMAGE_GLS_CLASS, 2006, null);
        glsClass.setBrand(mercedes);
        brandRepository.save(mercedes);
        modelRepository.save(sClass);
        modelRepository.save(sClass);
        modelRepository.save(glsClass);

        Brand lincoln = initBrand("Lincoln");
        Model navigator = initModel("Navigator", ModelCategory.SUV, IMAGE_NAVIGATOR, 1997, null);
        navigator.setBrand(lincoln);
        brandRepository.save(lincoln);
        modelRepository.save(navigator);
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
