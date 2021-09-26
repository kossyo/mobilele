package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.models.bindings.offer.OfferAddView;
import bg.softuni.mobilele.models.bindings.offer.OfferUpdateView;

import java.util.List;

public interface OfferService {
    List<OfferDto> findAllOffers();

    OfferDto findById(Long id) throws IllegalArgumentException;

    void update(OfferUpdateView offerUpdateView);

    void add(OfferAddView offerAddView);

    void delete(Long id);
}
