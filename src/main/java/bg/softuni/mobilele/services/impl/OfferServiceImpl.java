package bg.softuni.mobilele.services.impl;

import bg.softuni.mobilele.models.bindings.offer.AddOfferBindingModel;
import bg.softuni.mobilele.models.bindings.offer.AddOfferViewModel;
import bg.softuni.mobilele.models.bindings.offer.UpdateOfferViewModel;
import bg.softuni.mobilele.models.dtos.ModelDto;
import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.models.entities.Brand;
import bg.softuni.mobilele.models.entities.Model;
import bg.softuni.mobilele.models.entities.Offer;
import bg.softuni.mobilele.models.entities.User;
import bg.softuni.mobilele.models.bindings.offer.UpdateOfferBindingModel;
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
    public List<OfferDto> findAllOffers() {
        List<OfferDto> offerDtos = new ArrayList<>();

        for (Offer offer : offerRepository.findAll()) {
            OfferDto offerDto = new OfferDto();
            modelMapper.map(offer, offerDto);
            offerDtos.add(offerDto);
        }
        return offerDtos;
    }

    @Override
    public OfferDto findById(Long id) throws IllegalArgumentException {
        Optional<Offer> offerEntityOpt = offerRepository.findById(id);
        OfferDto offerDto = new OfferDto();
        if (offerEntityOpt.isEmpty()) {
            throw new IllegalArgumentException("Entity not found");
        }
        modelMapper.map(offerEntityOpt.get(), offerDto);
        //todo: maybe search by Brand object, not by brand.id. change the repo method?
        List<ModelDto> modelsByBrand = modelService.findAllByBrandId(offerDto.getModel().getBrand().getId());
        offerDto.getModel().getBrand().setModels(modelsByBrand);
        return offerDto;
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

        List<ModelDto> models = modelService.findAllByBrandId(brand.getId());

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
        OfferDto offer = findById(id);
        UpdateOfferViewModel updateOfferViewModel = modelMapper.map(offer, UpdateOfferViewModel.class);

        List<ModelDto> models = modelService.findAllByBrandId(offer.getModel().getBrand().getId());

        updateOfferViewModel.setModels(models);

        updateOfferViewModel.setEngineTypes(initEngineTypes());
        updateOfferViewModel.setTransmissionTypes(initTransmissionTypes());
        updateOfferViewModel.setModel(offer.getModel().getName());
        return updateOfferViewModel;
    }

    @Override
    public UpdateOfferViewModel getUpdateOfferViewModel(Long id) {
        OfferDto offerDto = findById(id);
        List<ModelDto> models = offerDto.getModel().getBrand().getModels();
        List<EngineType> engineTypes = initEngineTypes();
        List<TransmissionType> transmissionTypes = initTransmissionTypes();

        UpdateOfferViewModel updateOfferViewModel = new UpdateOfferViewModel();
        updateOfferViewModel.setOfferDto(offerDto);
        updateOfferViewModel.setModels(models);
        updateOfferViewModel.setEngineTypes(engineTypes);
        updateOfferViewModel.setTransmissionTypes(transmissionTypes);
        return updateOfferViewModel;
    }

    @Override
    public AddOfferViewModel getAddOfferViewModel(){

        AddOfferViewModel addOfferViewModel = new AddOfferViewModel();
        List<ModelDto> models = modelService.findAll();
        List<EngineType> engineTypes = initEngineTypes();
        List<TransmissionType> transmissionTypes = initTransmissionTypes();
        addOfferViewModel.setModels(models);
        addOfferViewModel.setEngineTypes(engineTypes);
        addOfferViewModel.setTransmissionTypes(transmissionTypes);

        return addOfferViewModel;
    }
}
