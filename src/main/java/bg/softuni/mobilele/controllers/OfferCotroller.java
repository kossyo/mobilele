package bg.softuni.mobilele.controllers;

import bg.softuni.mobilele.models.bindings.offer.AddOfferBindingModel;
import bg.softuni.mobilele.models.bindings.offer.AddOfferViewModel;
import bg.softuni.mobilele.models.bindings.offer.UpdateOfferBindingModel;
import bg.softuni.mobilele.models.bindings.offer.UpdateOfferViewModel;
import bg.softuni.mobilele.models.dtos.OfferServiceModel;
import bg.softuni.mobilele.services.ModelService;
import bg.softuni.mobilele.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/offers/")
public class OfferCotroller {

    private final OfferService offerService;
    private final ModelService modelService;

    public OfferCotroller(OfferService offerService, ModelService modelService) {

        this.offerService = offerService;
        this.modelService = modelService;
    }

    @GetMapping("all")
    public String allOffers(Model model,
                            HttpSession session) {

        List<OfferServiceModel> offers = offerService.findAllOffers();
        model.addAttribute("offers", offers);
        List<String> selectOptions = List.of("bg", "en", "nl", "sr");
        model.addAttribute("selectOptions", selectOptions);

        //managed with session
        String valSelFromCookie = (String) session.getAttribute("valSelFromCookie");// not a cookie, just a wrong var name
        if (valSelFromCookie == null) {
            valSelFromCookie = "nl";
        }
        model.addAttribute("valSelFromCookie", valSelFromCookie);

        return "offers/all-offers";
    }

    @GetMapping("details/{id}")
    public String getDetails(Model model, @PathVariable Long id) throws IllegalArgumentException {
        OfferServiceModel offerDto = offerService.findById(id);
        model.addAttribute("offer", offerDto);
        return "offers/offer-details";
    }

    @GetMapping("updateOffer/{id}")
    public String updateOffer(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean wasRedirected = model.containsAttribute("updateOfferBindingModel");
        UpdateOfferViewModel updateOfferViewModel = offerService.getUpdateOfferViewModel(id);
        model.addAttribute("updateOfferViewModel", updateOfferViewModel);

        if (!wasRedirected) {
            model.addAttribute("updateOfferBindingModel", new UpdateOfferBindingModel());
        }else{
            offerService.removeErroneousFields((BindingResult) model.getAttribute("org.springframework.validation.BindingResult.updateOfferBindingModel"), updateOfferViewModel);
        }


        //todo: remove correct values of mistaken fields so they don't reappear again upon redirect
        return "offers/update";
    }


    @PostMapping("confirmUpdate")
    public String confirmUpdate(Model model,
                                @Valid @ModelAttribute UpdateOfferBindingModel updateOfferBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateOfferBindingModel", updateOfferBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateOfferBindingModel",
                    bindingResult);

            return String.format("redirect:/offers/updateOffer/%d", updateOfferBindingModel.getOfferId());

        }
        offerService.update(updateOfferBindingModel);
        return "offers/all-offers";
    }

    @GetMapping("deleteOffer/{id}")
    public String deleteOffer(Model model, @PathVariable Long id) {
        offerService.delete(id);
        return "offers/all-offers";
    }


    @GetMapping("add")
    public String add(Model model) {
        if (!model.containsAttribute("addOfferBindingModel")) {
            model.addAttribute("addOfferBindingModel", new AddOfferBindingModel());
        }
        AddOfferViewModel addOfferViewModel = offerService.getAddOfferViewModel();
        model.addAttribute("addOfferViewModel", addOfferViewModel);
        return "offers/add-offer";
    }

    @PostMapping("confirmAdd")
    public String confirmAdd(@Valid @ModelAttribute AddOfferBindingModel addOfferBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferBindingModel", addOfferBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferBindingModel", bindingResult);

            return "redirect:/offers/add";
        }
        offerService.add(addOfferBindingModel);
        return "offers/all-offers";
    }

    @PostMapping("cookie")
    public String cookie(HttpServletResponse response, @RequestParam String valSelected, HttpSession session) {
        session.setAttribute("valSelFromCookie", valSelected);//this is not a cookie at all, just a wrong var name
        return "redirect:/offers/all";
    }
}