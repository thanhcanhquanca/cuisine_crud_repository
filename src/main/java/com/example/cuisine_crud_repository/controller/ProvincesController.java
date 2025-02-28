package com.example.cuisine_crud_repository.controller;

import com.example.cuisine_crud_repository.model.Cuisine;
import com.example.cuisine_crud_repository.model.Province;
import com.example.cuisine_crud_repository.services.ICuisineService;
import com.example.cuisine_crud_repository.services.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("provinces")
public class ProvincesController {
    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ICuisineService cuisineService;

    @GetMapping("/list")
    public String provinceList(Model model){
       Iterable<Province> povinces = provinceService.findAll();
       model.addAttribute("provinces", povinces);
        return "provinceList";
    }

    @GetMapping("/create")
    public String provinceCreate(Model model){
        model.addAttribute("province", new Province());
        return "provinceCreate";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute("province") Province province,
                         RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "Create new province successfully");
        return "redirect:/provinces/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProvince(@PathVariable("id") Long id) {
            Optional<Province> province = provinceService.findById(id);
            if (province.isPresent()) {
                provinceService.remove(id);
                return "redirect:/provinces/list";
            }
            return "redirect:/provinces/error_404";
    }

    @GetMapping("/view")
    public String viewCuisine( Model model) {

        return "view";
    }
}
