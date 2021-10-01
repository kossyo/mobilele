package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.bindings.offer.AddOfferBindingModel;
import bg.softuni.mobilele.models.bindings.offer.AddOfferViewModel;
import bg.softuni.mobilele.models.bindings.offer.UpdateOfferBindingModel;
import bg.softuni.mobilele.models.bindings.offer.UpdateOfferViewModel;
import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.models.entities.enums.EngineType;
import bg.softuni.mobilele.models.entities.enums.TransmissionType;
import org.springframework.ui.Model;

import java.util.List;

public interface OfferService {
    List<OfferDto> findAllOffers();

    OfferDto findById(Long id) throws IllegalArgumentException;

    void update(UpdateOfferBindingModel offerUpdateView);

    void add(AddOfferBindingModel offerAddView);

    void delete(Long id);

    UpdateOfferViewModel initOfferUpdateViewModelAfterRedirect(UpdateOfferBindingModel updateOfferBindingModel);

    List<TransmissionType> initTransmissionTypes();

    List<EngineType> initEngineTypes();

    UpdateOfferViewModel initUpdateOfferViewModelFromDb(Long id);

    UpdateOfferViewModel getUpdateOfferViewModel(Long id);

    AddOfferViewModel getAddOfferViewModel();
}
