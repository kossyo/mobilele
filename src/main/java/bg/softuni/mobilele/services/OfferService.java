package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.bindings.offer.*;
import bg.softuni.mobilele.models.dtos.OfferServiceModel;
import bg.softuni.mobilele.models.entities.enums.EngineType;
import bg.softuni.mobilele.models.entities.enums.TransmissionType;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface OfferService {
    List<OfferServiceModel> findAllOffers();

    OfferServiceModel findById(Long id) throws IllegalArgumentException;

    void update(UpdateOfferBindingModel offerUpdateView);

    void add(AddOfferBindingModel offerAddView);

    void delete(Long id);

    UpdateOfferViewModel initOfferUpdateViewModelAfterRedirect(UpdateOfferBindingModel updateOfferBindingModel);

    List<TransmissionType> initTransmissionTypes();

    List<EngineType> initEngineTypes();

    UpdateOfferViewModel initUpdateOfferViewModelFromDb(Long id);

    UpdateOfferViewModel getUpdateOfferViewModel(Long id);

    void setModelsForThisBrand(OfferServiceModel offerServiceModel, UpdateOfferViewModel updateOfferViewModel);

    AddOfferViewModel fillSelectMenusAllModels(AddOfferViewModel addOfferViewModel);

    UpdateOfferViewModel fillSelectMenusAllModelsOnlyThisBrand(UpdateOfferViewModel updateOfferViewModel);

    AddOfferViewModel getAddOfferViewModel();

    void addBrandId(UpdateOfferViewModel updateOfferViewModel, Long id);

    void removeErroneousFields(BindingResult updateOfferBindingModel, UpdateOfferViewModel updateOfferViewModel);
}
