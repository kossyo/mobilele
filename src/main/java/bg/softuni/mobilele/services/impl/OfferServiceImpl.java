package bg.softuni.mobilele.services.impl;

import bg.softuni.mobilele.models.bindings.offer.AddOfferBindingModel;
import bg.softuni.mobilele.models.bindings.offer.AddOfferViewModel;
import bg.softuni.mobilele.models.bindings.offer.UpdateOfferBindingModel;
import bg.softuni.mobilele.models.bindings.offer.UpdateOfferViewModel;
import bg.softuni.mobilele.models.dtos.ModelServiceModel;
import bg.softuni.mobilele.models.dtos.OfferServiceModel;
import bg.softuni.mobilele.models.entities.Brand;
import bg.softuni.mobilele.models.entities.Model;
import bg.softuni.mobilele.models.entities.Offer;
import bg.softuni.mobilele.models.entities.User;
import bg.softuni.mobilele.models.entities.enums.EngineType;
import bg.softuni.mobilele.models.entities.enums.TransmissionType;
import bg.softuni.mobilele.repos.ModelRepository;
import bg.softuni.mobilele.repos.OfferRepository;
import bg.softuni.mobilele.repos.UserRepository;
import bg.softuni.mobilele.services.ModelService;
import bg.softuni.mobilele.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final ModelService modelService;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository, UserRepository userRepository, ModelService modelService, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
        this.modelService = modelService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OfferServiceModel> findAllOffers() {
        List<OfferServiceModel> offerServiceModels = new ArrayList<>();

        for (Offer offer : offerRepository.findAll()) {
            OfferServiceModel offerServiceModel = new OfferServiceModel();
            modelMapper.map(offer, offerServiceModel);
            offerServiceModels.add(offerServiceModel);
        }
        return offerServiceModels;
    }

    @Override
    public OfferServiceModel findById(Long id) throws IllegalArgumentException {
        OfferServiceModel offerServiceModel = offerRepository.findById(id).map(o -> modelMapper.map(o, OfferServiceModel.class))
                .orElseThrow(() -> new IllegalArgumentException("Entity not found"));
        List<ModelServiceModel> modelsByBrand = modelService.findAllByBrandId(offerServiceModel.getModel().getBrand().getId());
        offerServiceModel.getModel().getBrand().setModels(modelsByBrand);
        return offerServiceModel;
    }

    @Override
    public void update(UpdateOfferBindingModel updateOfferBindingModel) {
        Optional<Offer> offerOpt = offerRepository.findById(updateOfferBindingModel.getOfferId());
        if (offerOpt.isEmpty()) {
            throw new IllegalArgumentException("Entity not found");
        }
        Offer offer = offerOpt.get();
        modelMapper.map(updateOfferBindingModel, offer);
        Model model = modelRepository.findModelByName(updateOfferBindingModel.getModel());
        offer.setModel(model);
        offer.setUpdated(Instant.now());
        offerRepository.save(offer);
    }

    @Override
    public void add(AddOfferBindingModel offerAddView) {
        Offer offer = new Offer();
        modelMapper.map(offerAddView, offer);
        Model model = modelRepository.findModelByName(offerAddView.getModel());
        User seller = userRepository.getById(1L);
        offer.setSeller(seller);
        offer.setModel(model);
        offer.setCreated(Instant.now());
        offer.setUpdated(Instant.now());
        offerRepository.save(offer);
    }

    @Override
    public void delete(Long id) {
        Optional<Offer> offerOpt = offerRepository.findById(id);
        if (offerOpt.isEmpty()) {
            throw new IllegalArgumentException("Entity not found");
        }
        Offer offer = offerOpt.get();
        offerRepository.delete(offer);
    }

    @Override
    public UpdateOfferViewModel initOfferUpdateViewModelAfterRedirect(UpdateOfferBindingModel updateOfferBindingModel) {

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        UpdateOfferViewModel updateOfferViewModel = modelMapper.map(updateOfferBindingModel, UpdateOfferViewModel.class);

        modelMapper.getConfiguration().setAmbiguityIgnored(false);


        updateOfferViewModel.setOfferId(updateOfferBindingModel != null ? updateOfferBindingModel.getOfferId() : null);
        Long offerId = Objects.requireNonNull(updateOfferBindingModel).getOfferId();
        if (offerId == null) {
            throw new IllegalArgumentException("offerId cannot be null");
        }
        Optional<Offer> offerOpt = offerRepository.findById(offerId);
        if (offerOpt.isEmpty()) {
            throw new IllegalArgumentException("Offer entity not found");
        }
        Brand brand = offerOpt.get().getModel().getBrand();

        List<ModelServiceModel> models = modelService.findAllByBrandId(brand.getId());

        updateOfferViewModel.setModels(models);

        updateOfferViewModel.setEngineTypes(initEngineTypes());
        updateOfferViewModel.setTransmissionTypes(initTransmissionTypes());
        return updateOfferViewModel;
    }

    @Override
    public List<TransmissionType> initTransmissionTypes() {
        List<TransmissionType> transmissionTypes = new ArrayList<>();
        transmissionTypes.add(TransmissionType.AUTOMATIC);
        transmissionTypes.add(TransmissionType.MANUAL);
        return transmissionTypes;
    }

    @Override
    public List<EngineType> initEngineTypes() {
        List<EngineType> engineTypes = new ArrayList<>();
        engineTypes.add(EngineType.GASOLINE);
        engineTypes.add(EngineType.DIESEL);
        engineTypes.add(EngineType.ELECTRIC);
        engineTypes.add(EngineType.HYBRID);
        return engineTypes;
    }


    @Override
    public UpdateOfferViewModel initUpdateOfferViewModelFromDb(Long id) {
        OfferServiceModel offer = findById(id);
        UpdateOfferViewModel updateOfferViewModel = modelMapper.map(offer, UpdateOfferViewModel.class);

        List<ModelServiceModel> models = modelService.findAllByBrandId(offer.getModel().getBrand().getId());

        updateOfferViewModel.setModels(models);

        updateOfferViewModel.setEngineTypes(initEngineTypes());
        updateOfferViewModel.setTransmissionTypes(initTransmissionTypes());
        updateOfferViewModel.setModel(offer.getModel());
        return updateOfferViewModel;
    }

    @Override
    public UpdateOfferViewModel getUpdateOfferViewModel(Long offerId) {
        OfferServiceModel offerServiceModel = findById(offerId);


        UpdateOfferViewModel updateOfferViewModel = modelMapper.map(offerServiceModel, UpdateOfferViewModel.class);

        updateOfferViewModel.setModel(offerServiceModel.getModel());
        updateOfferViewModel.setTransmissionType(TransmissionType.valueOf(offerServiceModel.getTransmission()).toString());
        setModelsForThisBrand(offerServiceModel, updateOfferViewModel);
        return (UpdateOfferViewModel) fillSelectMenusAllModelsOnlyThisBrand(updateOfferViewModel);
    }

    @Override
    public void setModelsForThisBrand(OfferServiceModel offerServiceModel, UpdateOfferViewModel updateOfferViewModel) {
        Brand brand = modelMapper.map(offerServiceModel.getModel().getBrand(), Brand.class);
        List<Model> modelsByBrand = modelRepository.findModelsByBrand(brand);
        List<ModelServiceModel> modelServiceModels = new ArrayList<>();
        for (Model model : modelsByBrand) {
            ModelServiceModel modelServiceModel = modelMapper.map(model, ModelServiceModel.class);
            modelServiceModels.add(modelServiceModel);
        }
        updateOfferViewModel.setModels(modelServiceModels);
    }

    @Override
    public AddOfferViewModel fillSelectMenusAllModels(AddOfferViewModel addOfferViewModel) {
        List<ModelServiceModel> models;
        models = modelService.findAll();
        List<EngineType> engineTypes = initEngineTypes();
        List<TransmissionType> transmissionTypes = initTransmissionTypes();
        addOfferViewModel.setModels(models);
        addOfferViewModel.setEngineTypes(engineTypes);
        addOfferViewModel.setTransmissionTypes(transmissionTypes);
        return addOfferViewModel;
    }

    @Override
    public UpdateOfferViewModel fillSelectMenusAllModelsOnlyThisBrand(UpdateOfferViewModel updateOfferViewModel) {
        List<ModelServiceModel> models;
        Long brandId = offerRepository.findById(updateOfferViewModel.getOfferId()).orElse(null).getModel().getBrand().getId();
        models = modelService.findAllByBrandId(brandId);

        List<EngineType> engineTypes = initEngineTypes();
        List<TransmissionType> transmissionTypes = initTransmissionTypes();
        updateOfferViewModel.setModels(models);
        updateOfferViewModel.setEngineTypes(engineTypes);
        updateOfferViewModel.setTransmissionTypes(transmissionTypes);
        return updateOfferViewModel;
    }

    @Override
    public AddOfferViewModel getAddOfferViewModel() {

        AddOfferViewModel addOfferViewModel = new AddOfferViewModel();
        fillSelectMenusAllModels(addOfferViewModel);
//        List<ModelServiceModel> models = modelService.findAll();
//        List<EngineType> engineTypes = initEngineTypes();
//        List<TransmissionType> transmissionTypes = initTransmissionTypes();
//        addOfferViewModel.setModels(models);
//        addOfferViewModel.setEngineTypes(engineTypes);
//        addOfferViewModel.setTransmissionTypes(transmissionTypes);

        return addOfferViewModel;
    }

    @Override
    public void addBrandId(UpdateOfferViewModel updateOfferViewModel, Long offerId) {
        Offer offer = offerRepository.findById(offerId).orElse(null);
        updateOfferViewModel.setBrandId(offer.getModel().getBrand().getId());
    }

//    @Override
//    public void removeErroneousFields(UpdateOfferViewModel updateOfferViewModel, BindingResult bindingResult) {
//        for (FieldError fieldError : bindingResult.getFieldErrors()) {
//            switch (fieldError.getField()){
//                case "model": updateOfferViewModel.setModel(new ModelServiceModel()); break;
//                case "description": updateOfferViewModel.setDescription("");  break;
//                case "engineType": updateOfferViewModel.setEngineType(null); break;
//                case "imageUrl": updateOfferViewModel.setImageUrl(null); break;
//                case "mileage": updateOfferViewModel.setMileage(null); break;
//                case "price": updateOfferViewModel.setPrice(null); break;
//                case "transmissionType": updateOfferViewModel.setTransmissionType(null); break;
//                case "year": updateOfferViewModel.setYear(null); break;
//            }
//        }
//    }
////
//    @Override
//    public UpdateOfferViewModel completeInitUpdateOfferViewModel(Long id) {
////        UpdateOfferViewModel updateOfferViewModel =
////        modelMapper.map(updateOfferBindingModel, UpdateOfferViewModel.class);
//        BrandServiceModel brandByModel = brandService.findBrandByModel(updateOfferViewModel.getModel());
//
//        List<ModelServiceModel> models = modelService.findAllByBrandId(brandByModel.getId());
//        List<EngineType> engineTypes = initEngineTypes();
//        List<TransmissionType> transmissionTypes = initTransmissionTypes();
//
//        updateOfferViewModel.setModels(models);
//        updateOfferViewModel.setEngineTypes(engineTypes);
//        updateOfferViewModel.setTransmissionTypes(transmissionTypes);
//
//        return updateOfferViewModel;
//
//    }
}
