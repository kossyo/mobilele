package bg.softuni.mobilele.services;

import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.models.entities.Model;

import java.util.List;

public interface OfferService {
    List<OfferDto> findAllOffers();
}
