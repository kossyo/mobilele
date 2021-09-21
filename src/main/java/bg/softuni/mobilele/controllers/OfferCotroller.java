package bg.softuni.mobilele.controllers;

import bg.softuni.mobilele.models.dtos.ModelDto;
import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.services.ModelService;
import bg.softuni.mobilele.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("offers")
public class OfferCotroller {

    private final OfferService offerService;
    private final ModelService modelService;

    public OfferCotroller(OfferService offerService, ModelService modelService) {

        this.offerService = offerService;
        this.modelService = modelService;
    }

    @GetMapping("all")
    public String allOffers(Model model) {

        List<OfferDto> offers = offerService.findAllOffers();
        model.addAttribute("offers", offers);
        return "offers/all-offers";
    }

    @GetMapping("details/{id}")
    public String getDetails(Model model, @PathVariable Long id) throws IllegalArgumentException {
        OfferDto offerDto;
        offerDto = offerService.findById(id);
        model.addAttribute("offer", offerDto);
        return "offers/offer-details";
    }

    @GetMapping("updateOffer/{id}")
    public String updateOffer(Model model, @PathVariable Long id){
        OfferDto offer = offerService.findById(id);
        List<ModelDto> models = modelService.findAllByBrandId(offer.getModel().getBrand().getId());
        model.addAttribute("offer", offer);
        model.addAttribute("models", models);
        return "offers/update";
    }

    @PostMapping("confirmUpdate")
    public String confirmUpdate(Model model, @ModelAttribute OfferDto offerDto){

        return "offers/all-offers";
    }
}
