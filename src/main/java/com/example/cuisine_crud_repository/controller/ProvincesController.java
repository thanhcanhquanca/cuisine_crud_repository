package com.example.cuisine_crud_repository.controller;

import com.example.cuisine_crud_repository.model.Province;
import com.example.cuisine_crud_repository.services.ICuisineService;
import com.example.cuisine_crud_repository.services.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
}
