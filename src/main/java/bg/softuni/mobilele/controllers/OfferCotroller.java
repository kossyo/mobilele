package bg.softuni.mobilele.controllers;

import bg.softuni.mobilele.models.bindings.offer.OfferAddBindingModel;
import bg.softuni.mobilele.models.bindings.offer.OfferUpdateBindingModel;
import bg.softuni.mobilele.models.bindings.offer.UpdateOfferViewModel;
import bg.softuni.mobilele.models.dtos.OfferDto;
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

    public OfferCotroller(OfferService offerService) {

        this.offerService = offerService;
    }

    @GetMapping("all")
    public String allOffers(Model model,
                            HttpSession session) {

        List<OfferDto> offers = offerService.findAllOffers();
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
        OfferDto offerDto;
        offerDto = offerService.findById(id);
        model.addAttribute("offer", offerDto);
        return "offers/offer-details";
    }

    @GetMapping("updateOffer/{id}")
    public String updateOffer(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean wasRedirected = model.containsAttribute("offerUpdateBindingModel");
        if (wasRedirected) {

            //взимам си offerUpdateBindingModel-а, защото ми е нужен, за да създам updateOfferViewModel на следващия ред:
            OfferUpdateBindingModel offerUpdateBindingModel = (OfferUpdateBindingModel) model.getAttribute("offerUpdateBindingModel");

            UpdateOfferViewModel updateOfferViewModel = offerService.initOfferUpdateViewModelAfterRedirect(offerUpdateBindingModel);
            //добавям updateOfferViewModel към модела, на първо четене нищо необичайно.
            model.addAttribute("updateOfferViewModel", updateOfferViewModel);//
        } else {
            UpdateOfferViewModel updateOfferViewModel = offerService.initUpdateOfferViewModelFromDb(id);
            model.addAttribute("updateOfferViewModel", updateOfferViewModel);
        }
        return "offers/update";
    }

    @PostMapping("confirmUpdate")
    public String confirmUpdate(Model model,
                                @Valid @ModelAttribute OfferUpdateBindingModel offerUpdateBindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerUpdateBindingModel", offerUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerUpdateBindingModel",
                    bindingResult);

            return String.format("redirect:/offers/updateOffer/%d", offerUpdateBindingModel.getOfferId());

        }
        return "offers/all-offers";
    }

    @GetMapping("deleteOffer/{id}")
    public String deleteOffer(Model model, @PathVariable Long id) {
        offerService.delete(id);
        return "offers/all-offers";
    }


    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("offerAddBindingModel", offerService.initOfferAddBindingModel());
        return "offers/add-offer";
    }

    @PostMapping("confirmAdd")
    public String confirmAdd(@Valid @ModelAttribute OfferAddBindingModel offerAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerAddBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("offerAddBindingModel", offerAddBindingModel);

            return "redirect:/offers/add";
        }
        offerService.add(offerAddBindingModel);
        return "offers/all-offers";
    }

    @PostMapping("cookie")
    public String cookie(HttpServletResponse response, @RequestParam String valSelected, HttpSession session) {
        session.setAttribute("valSelFromCookie", valSelected);//this is not a cookie at all, just a wrong var name
        return "redirect:/offers/all";
    }
}
