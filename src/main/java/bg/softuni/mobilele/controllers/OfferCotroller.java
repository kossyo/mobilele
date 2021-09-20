package bg.softuni.mobilele.controllers;

import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("offers")
public class OfferCotroller {

    private final OfferService offerService;

    public OfferCotroller(OfferService offerService){

        this.offerService = offerService;
    }

    @GetMapping("all")
    public String allOffers(Model model){

        List<OfferDto> offers = offerService.findAllOffers();
        model.addAttribute("offers", offers);
        return "offers/all-offers";
    }

    @GetMapping("details")
    public String getDetails(Model model){
        return "offers/offer-details";
    }
}
