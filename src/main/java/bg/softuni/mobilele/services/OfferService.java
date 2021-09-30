package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.bindings.offer.UpdateOfferViewModel;
import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.models.bindings.offer.OfferAddBindingModel;
import bg.softuni.mobilele.models.bindings.offer.OfferUpdateBindingModel;
import bg.softuni.mobilele.models.entities.enums.EngineType;
import bg.softuni.mobilele.models.entities.enums.TransmissionType;

import java.util.List;

public interface OfferService {
    List<OfferDto> findAllOffers();

    OfferDto findById(Long id) throws IllegalArgumentException;

    void update(OfferUpdateBindingModel offerUpdateView);

    void add(OfferAddBindingModel offerAddView);

    void delete(Long id);

    UpdateOfferViewModel initOfferUpdateViewModelAfterRedirect(OfferUpdateBindingModel offerUpdateBindingModel);

    OfferAddBindingModel initOfferAddBindingModel();

    List<TransmissionType> initTransmissionTypes();

    List<EngineType> initEngineTypes();

    UpdateOfferViewModel initUpdateOfferViewModelFromDb(Long id);
}
