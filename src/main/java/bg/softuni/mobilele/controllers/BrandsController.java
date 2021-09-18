package bg.softuni.mobilele.controllers;

import bg.softuni.mobilele.models.dtos.BrandDto;
import bg.softuni.mobilele.models.entities.Brand;
import bg.softuni.mobilele.repos.BrandRepository;
import bg.softuni.mobilele.services.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("brands")
public class BrandsController {

    private final BrandService brandService ;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("all")
    public ModelAndView brands(ModelAndView mav){
        List<BrandDto> brands = brandService.findAll();
        mav.addObject(brands);
        mav.setViewName("brands");
        return mav;
    }
}
