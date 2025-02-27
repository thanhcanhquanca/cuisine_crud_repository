package com.example.cuisine_crud_repository.controller;

import com.example.cuisine_crud_repository.model.IProvinceDTO;
import com.example.cuisine_crud_repository.model.Province;
import com.example.cuisine_crud_repository.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("demo")
public class HandleProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/list")
    public String list(Model model) {
        Iterable<IProvinceDTO> provinces = provinceService.getAllProvinces();
        model.addAttribute("provinces", provinces);
        return "listDemo";
    }
}
