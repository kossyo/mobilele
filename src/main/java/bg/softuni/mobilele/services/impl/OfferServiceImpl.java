package bg.softuni.mobilele.services.impl;

import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.models.entities.Model;
import bg.softuni.mobilele.models.entities.Offer;
import bg.softuni.mobilele.models.views.OfferUpdateView;
import bg.softuni.mobilele.repos.ModelRepository;
import bg.softuni.mobilele.repos.OfferRepository;
import bg.softuni.mobilele.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
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
        return offerDto;
    }

    @Override
    public void update(OfferUpdateView offerUpdateView) {
        Optional<Offer> offerOpt = offerRepository.findById(offerUpdateView.getOfferId());
        if (offerOpt.isEmpty()) {
            throw new IllegalArgumentException("Entity not found");
        }
        Offer offer = offerOpt.get();
        modelMapper.map(offerUpdateView, offer);
        Model model = modelRepository.findModelByName(offerUpdateView.getModel());
        offer.setModel(model);
        offer.setUpdated(Instant.now());
        offerRepository.save(offer);
    }
}
