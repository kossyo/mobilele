package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.models.entities.Model;
import bg.softuni.mobilele.models.entities.Offer;

import java.util.List;

public interface OfferService {
    List<OfferDto> findAllOffers();

    OfferDto findById(Long id) throws IllegalArgumentException;
}
