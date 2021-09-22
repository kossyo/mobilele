package bg.softuni.mobilele.config;

import bg.softuni.mobilele.models.entities.*;
import bg.softuni.mobilele.models.entities.enums.EngineType;
import bg.softuni.mobilele.models.entities.enums.TransmissionType;
import bg.softuni.mobilele.models.entities.enums.UserRoleType;
import bg.softuni.mobilele.repos.*;
import bg.softuni.mobilele.models.entities.enums.ModelCategory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbInitializer implements CommandLineRunner {

    private static final String IMAGE_XTRAIL = "https://images.ams.bg/images/galleries/109571/nissan-x-trail-1460795988_big.jpg";
    private static final String IMAGE_PATHFINDER = "http://img.autoabc.lv/Nissan-Pathfinder/Nissan-Pathfinder_2010_Apvidus_151130102945_10.jpg";
    private static final String IMAGE_PAJERO = "https://images.ams.bg/images/galleries/109335/mitsubishi-pajero-1460793942_big.jpg";
    private static final String IMAGE_S_CLASS = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/2014_Mercedes-Benz_S550_%28US%29_lwb.jpg/1920px-2014_Mercedes-Benz_S550_%28US%29_lwb.jpg";
    private static final String IMAGE_GLS_CLASS = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Mercedes-Benz_GL_350_BlueTEC_4MATIC_Sport-Paket_AMG_%28X_166%29_%E2%80%93_Frontansicht%2C_31._Dezember_2012%2C_D%C3%BCsseldorf.jpg/1920px-Mercedes-Benz_GL_350_BlueTEC_4MATIC_Sport-Paket_AMG_%28X_166%29_%E2%80%93_Frontansicht%2C_31._Dezember_2012%2C_D%C3%BCsseldorf.jpg";
    private static final String IMAGE_NAVIGATOR = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/2019_Lincoln_Navigator_%27Reserve%27%2C_front_8.29.20.jpg/1920px-2019_Lincoln_Navigator_%27Reserve%27%2C_front_8.29.20.jpg";
    private static final String IMAGE_ASTRA = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Opel_Astra_1.6_CDTI_ecoFLEX_Dynamic_%28K%29_%E2%80%93_Frontansicht%2C_23._Dezember_2016%2C_D%C3%BCsseldorf_%28cropped%29.jpg/1920px-Opel_Astra_1.6_CDTI_ecoFLEX_Dynamic_%28K%29_%E2%80%93_Frontansicht%2C_23._Dezember_2016%2C_D%C3%BCsseldorf_%28cropped%29.jpg";
    private static final String IMAGE_ANTARA = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Opel_Antara_2.0_CDTI_front_20100516.jpg/1280px-Opel_Antara_2.0_CDTI_front_20100516.jpg";
    private static final String IMAGE_INSIGNIA = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ed/Opel_Insignia_Sports_Tourer_1.5_DIT_Innovation_%28B%29_%E2%80%93_Frontansicht%2C_12._Mai_2017%2C_D%C3%BCsseldorf.jpg/1920px-Opel_Insignia_Sports_Tourer_1.5_DIT_Innovation_%28B%29_%E2%80%93_Frontansicht%2C_12._Mai_2017%2C_D%C3%BCsseldorf.jpg";
    private static final String IMAGE_BATMOBILE = "https://upload.wikimedia.org/wikipedia/en/2/27/Batmobile_%28circa_2018%29.png";

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private final UserRoleRepository userRoleRepository;

    public DbInitializer(BrandRepository brandRepository, ModelRepository modelRepository, UserRepository userRepository, OfferRepository offerRepository, UserRoleRepository userRoleRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.offerRepository = offerRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initRoles();
        initCarsAndModels();
        initUsers();
        initOffers();
    }

    private void initRoles() {
        if(!userRoleRepository.findAll().isEmpty()){
            return;
        }
        UserRole adminUserRole = new UserRole(UserRoleType.ADMIN);
        UserRole userUserRole = new UserRole(UserRoleType.USER);

        userRoleRepository.save(adminUserRole);
        userRoleRepository.save(userUserRole);
    }

    private void initOffers() {
        if(!offerRepository.findAll().isEmpty()){
            return;
        }
        Offer astraOffer = new Offer();
        User kossyo = userRepository.findUserByUsername("kossyo");
        Model astra = modelRepository.findModelByName("Astra");
        astraOffer.setSeller(kossyo);
        astraOffer.setModel(astra);
        astraOffer.setDescription("Astra for sale");
        astraOffer.setEngineType(EngineType.DIESEL);
        astraOffer.setMileage(265000);
        astraOffer.setImageUrl("https://pazarluk.com/storage/listings/1604237822_533aa840-1c47-11eb-8d9a-47d76d64d154.jpg");
        astraOffer.setPrice(BigDecimal.valueOf(2000));
        astraOffer.setTransmission(TransmissionType.MANUAL);
        astraOffer.setYear(2006);
        setTimeInstances(astraOffer);
        offerRepository.save(astraOffer);

        Offer batMobileOffer = new Offer();
        User batman = this.userRepository.findUserByUsername("batman");
        Model batmobile = modelRepository.findModelByName("Batmobile model");
        batMobileOffer.setSeller(batman);
        batMobileOffer.setModel(batmobile);
        batMobileOffer.setDescription("Batmobile for sale");
        batMobileOffer.setEngineType(EngineType.GASOLINE);
        batMobileOffer.setMileage(50000);
        batMobileOffer.setImageUrl("https://www.ubuy.co.id/productimg/?image=aHR0cHM6Ly9tLm1lZGlhLWFtYXpvbi5jb20vaW1hZ2VzL0kvNzFrd1diSXRMNEwuX0FDX1NMMTUwMF8uanBn.jpg");
        batMobileOffer.setPrice(BigDecimal.valueOf(1000000));
        batMobileOffer.setTransmission(TransmissionType.AUTOMATIC);
        batMobileOffer.setYear(1990);
        setTimeInstances(batMobileOffer);
        offerRepository.save(batMobileOffer);

    }

    private void initUsers() {
        if(!userRepository.findAll().isEmpty()){
            return;
        }
        List<UserRole> allUserRoles = this.userRoleRepository.findAll();
        User kossyo = new User();
        kossyo.setActive(true);
        kossyo.setFirstName("Konstantin");
        kossyo.setLastName("Koev");
        kossyo.setImageUrl("https://files.worldwildlife.org/wwfcmsprod/images/HERO_Chimpanzee_Uganda/hero_full/5sgqq60jdd_Medium_WW215321.jpg");
        kossyo.setUpdated(Instant.now());
        kossyo.setUsername("kossyo");
        kossyo.setPassword("password");
        kossyo.setRoles(allUserRoles);
        setTimeInstances(kossyo);
        userRepository.save(kossyo);

        User batman = new User();
        batman.setActive(true);
        batman.setFirstName("Bruce");
        batman.setLastName("Wayne");
        batman.setImageUrl("https://e7.pngegg.com/pngimages/899/116/png-clipart-batman-batman-face-the-face-drawing-batman-comics-cat-like-mammal.png");
        batman.setUpdated( Instant.now());
        batman.setUsername("batman");
        batman.setPassword("password");
        List<UserRole> batmanRoles = new ArrayList<>();
        batmanRoles.add(userRoleRepository.findUserRoleByRole(UserRoleType.USER));
        batman.setRoles(batmanRoles);
        setTimeInstances(batman);
        userRepository.save(batman);
    }

    private void initCarsAndModels() {
        if(!brandRepository.findAll().isEmpty() || !modelRepository.findAll().isEmpty()){
            return;
        }
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
        modelRepository.save(glsClass);

        Brand lincoln = initBrand("Lincoln");
        Model navigator = initModel("Navigator", ModelCategory.SUV, IMAGE_NAVIGATOR, 1997, null);
        navigator.setBrand(lincoln);
        brandRepository.save(lincoln);
        modelRepository.save(navigator);

        Brand opel = initBrand("Opel");
        Model astra = initModel("Astra", ModelCategory.CAR,IMAGE_ASTRA, 1991, null);
        Model antara = initModel("Antara", ModelCategory.SUV,IMAGE_ANTARA, 2006, 2015);
        Model insignia = initModel("Insignia", ModelCategory.CAR,IMAGE_INSIGNIA, 2008, null);
        astra.setBrand(opel);
        antara.setBrand(opel);
        insignia.setBrand(opel);
        brandRepository.save(opel);
        modelRepository.save(astra);
        modelRepository.save(antara);
        modelRepository.save(insignia);

        Brand batmobileBrand = initBrand("BatMobile");
        Model batmobileModel = initModel("Batmobile model", ModelCategory.CAR, IMAGE_BATMOBILE, 1990, 2000);
        batmobileModel.setBrand(batmobileBrand);
        brandRepository.save(batmobileBrand);
        modelRepository.save(batmobileModel);

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

    private static BaseEntity setTimeInstances(BaseEntity entity) {
        entity.setCreated(Instant.now());
        entity.setUpdated(Instant.now());
        return entity;
    }
}
