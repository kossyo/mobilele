package bg.softuni.mobilele.controllers;

import bg.softuni.mobilele.models.dtos.ModelDto;
import bg.softuni.mobilele.models.dtos.OfferDto;
import bg.softuni.mobilele.models.entities.enums.EngineType;
import bg.softuni.mobilele.models.entities.enums.TransmissionType;
import bg.softuni.mobilele.models.views.OfferAddView;
import bg.softuni.mobilele.models.views.OfferUpdateView;
import bg.softuni.mobilele.services.ModelService;
import bg.softuni.mobilele.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public String updateOffer(Model model, @PathVariable Long id) {
        model.addAttribute("offerView", initOfferUpdateView(id));
        return "offers/update";
    }

    @PostMapping("confirmUpdate")
    public String confirmUpdate(Model model, @ModelAttribute OfferUpdateView offerUpdateView) {
        offerService.update(offerUpdateView);
        return "offers/all-offers";
    }

    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("offerView", initOfferAddView());
        return "offers/add-offer";
    }

    @PostMapping("confirmAdd")
    public String confirmAdd(Model model, OfferAddView offerAddView){
        offerService.add(offerAddView);
        return "offers/all-offers";
    }

    private OfferUpdateView initOfferUpdateView(Long id) {
        OfferDto offer = offerService.findById(id);
        List<ModelDto> models = modelService.findAllByBrandId(offer.getModel().getBrand().getId());

        OfferUpdateView offerUpdateView = new OfferUpdateView();
        offerUpdateView.setOffer(offer);
        offerUpdateView.setModels(models);

        offerUpdateView.setEngineTypes(initEngineTypes());
        offerUpdateView.setTransmissionTypes(initTransmissionTypes());

        return offerUpdateView;
    }

    private OfferAddView initOfferAddView(){

        OfferAddView offerUpdateView = new OfferAddView();
        List<ModelDto> models = modelService.findAll();

        offerUpdateView.setModels(models);
        offerUpdateView.setEngineTypes(initEngineTypes());
        offerUpdateView.setTransmissionTypes(initTransmissionTypes());

        return offerUpdateView;
    }

    private List<TransmissionType> initTransmissionTypes() {
        List<TransmissionType> transmissionTypes = new ArrayList<>();
        transmissionTypes.add(TransmissionType.AUTOMATIC);
        transmissionTypes.add(TransmissionType.MANUAL);
        return transmissionTypes;
    }

    private List<EngineType> initEngineTypes() {
        List<EngineType> engineTypes = new ArrayList<>();
        engineTypes.add(EngineType.GASOLINE);
        engineTypes.add(EngineType.DIESEL);
        engineTypes.add(EngineType.ELECTRIC);
        engineTypes.add(EngineType.HYBRID);
        return engineTypes;
    }
}
