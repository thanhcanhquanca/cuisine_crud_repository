package com.example.cuisine_crud_repository.controller;

import com.example.cuisine_crud_repository.model.Cuisine;
import com.example.cuisine_crud_repository.services.ICuisineService;
import com.example.cuisine_crud_repository.utility.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@PropertySource("classpath:upload_file.properties")
@RequestMapping("cuisine")
public class CuisineController {
    @Autowired
    private ICuisineService cuisineService;

    @Value("${file_upload}")
    private String UPLOAD_DIR;

//    @GetMapping("/list")
//    public String listCuisines(Model model,
//                               @RequestParam(defaultValue = "0") int page,
//                               @RequestParam(defaultValue = "5") int size) {
//        Page<Cuisine> cuisinePage = cuisineService.findAll(PageRequest.of(page, size));
//
//        model.addAttribute("cuisines", cuisinePage.getContent());
//        model.addAttribute("currentPage", cuisinePage.getNumber());
//        model.addAttribute("totalPages", cuisinePage.getTotalPages());
//        model.addAttribute("size", size);
//
//        return "list";
//    }

//    @GetMapping("/list")
//    public String listCuisines(Model model,
//                               @RequestParam(defaultValue = "0") int page,
//                               @RequestParam(defaultValue = "5") int size,
//                               @RequestParam(defaultValue = "") String search) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Cuisine> cuisinePage;
//
//        if (search.isEmpty()) {
//            // Nếu không có từ khóa, hiển thị toàn bộ danh sách
//            cuisinePage = cuisineService.findAll(pageable);
//        } else {
//            // Nếu có từ khóa, tìm kiếm theo tên
//            cuisinePage = cuisineService.findByNameContaining(search, pageable);
//        }
//
//        model.addAttribute("cuisines", cuisinePage.getContent());
//        model.addAttribute("currentPage", cuisinePage.getNumber());
//        model.addAttribute("totalPages", cuisinePage.getTotalPages());
//        model.addAttribute("size", size);
//        model.addAttribute("pageNumbers", PaginationUtils.getPageNumbers(cuisinePage.getTotalPages(), cuisinePage.getNumber()));
//        model.addAttribute("search", search); // Để giữ từ khóa trong form
//
//        return "list";
//    }


//    @GetMapping("/list")
//    public String listCustomer(Model model, Pageable pageable) {
//        Page<Cuisine> cuisines = cuisineService.findAll(pageable);
//        model.addAttribute("cuisines", cuisines);
//        return "list";
//    }


    @GetMapping("/list")
    public String listCuisines(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam(defaultValue = "asc") String sort) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cuisine> cuisinePage;

        switch (sort.toLowerCase()) {
            case "desc":
                cuisinePage = cuisineService.findAllSortedDesc(pageable);
                break;
            case "random":
                cuisinePage = cuisineService.findAllRandom(pageable);
                break;
            case "asc":
            default:
                cuisinePage = cuisineService.findAllSortedAsc(pageable);
                break;
        }

        model.addAttribute("cuisines", cuisinePage.getContent());
        model.addAttribute("currentPage", cuisinePage.getNumber());
        model.addAttribute("totalPages", cuisinePage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("pageNumbers", PaginationUtils.getPageNumbers(cuisinePage.getTotalPages(), cuisinePage.getNumber()));
        model.addAttribute("sort", sort);
        model.addAttribute("totalItems", cuisineService.count());

        return "list";
    }

    @GetMapping("/create")
    public String showCreate(Model model ) {
        model.addAttribute("cuisine", new Cuisine());
        return "create";
    }

    @PostMapping("/save")
    public String saveCuisine(@ModelAttribute("cuisine") Cuisine cuisine ) {
        cuisineService.save(cuisine);
        return "redirect:/cuisine/list";
    }




    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirect) {
        cuisineService.remove(id);
        redirect.addFlashAttribute("message", "Delete cuisine successfully");
        return "redirect:/cuisine/list";
    }


    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model, RedirectAttributes redirect) {
        Optional<Cuisine> cuisineOptional = cuisineService.findById(id);
        if (cuisineOptional.isPresent()) {
            model.addAttribute("cuisine", cuisineOptional.get());
            return "update";
        } else {
            redirect.addFlashAttribute("error", "Cuisine with ID " + id + " not found");
            return "redirect:/cuisines/list";
        }
    }


    @PostMapping("/update")
    public String updateCuisine(@RequestParam("id") Long id,
                                @ModelAttribute Cuisine cuisine,
                                RedirectAttributes redirect) {
        cuisineService.update(id, cuisine);
        redirect.addFlashAttribute("message", "Update cuisine successfully");
        return "redirect:/cuisine/list";
    }

    @GetMapping("/search")
    public String searchCuisines(Model model,
                                 @RequestParam("search") String search,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cuisine> cuisinePage = cuisineService.findByNameContaining(search, pageable);

        model.addAttribute("cuisines", cuisinePage.getContent());
        model.addAttribute("currentPage", cuisinePage.getNumber());
        model.addAttribute("totalPages", cuisinePage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("pageNumbers", PaginationUtils.getPageNumbers(cuisinePage.getTotalPages(), cuisinePage.getNumber()));
        model.addAttribute("search", search);

        return "search"; // View riêng cho tìm kiếm
    }


}
