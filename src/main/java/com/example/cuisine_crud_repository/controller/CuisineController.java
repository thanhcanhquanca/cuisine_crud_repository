package com.example.cuisine_crud_repository.controller;

import com.example.cuisine_crud_repository.model.Cuisine;
import com.example.cuisine_crud_repository.services.ICuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("cuisine")
public class CuisineController {
    @Autowired
    private ICuisineService cuisineService;

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

    @GetMapping("/list")
    public String listCuisines(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size) {
        Page<Cuisine> cuisinePage = cuisineService.findAll(PageRequest.of(page, size));

        model.addAttribute("cuisines", cuisinePage.getContent());
        model.addAttribute("currentPage", cuisinePage.getNumber());
        model.addAttribute("totalPages", cuisinePage.getTotalPages());
        model.addAttribute("size", size);
        // Thêm danh sách số trang (tùy chọn)
        model.addAttribute("pageNumbers", getPageNumbers(cuisinePage.getTotalPages(), cuisinePage.getNumber()));

        return "list"; // Đổi "list" thành "cuisine-list" nếu cần
    }

    // Hàm tạo danh sách số trang
    private List<Integer> getPageNumbers(int totalPages, int currentPage) {
        List<Integer> pageNumbers = new ArrayList<>();
        int start = Math.max(0, currentPage - 2); // Hiển thị 2 trang trước
        int end = Math.min(totalPages - 1, currentPage + 2); // Hiển thị 2 trang sau
        for (int i = start; i <= end; i++) {
            pageNumbers.add(i);
        }
        return pageNumbers;
    }

    @GetMapping("/create")
    public String showCreate(Model model) {
        model.addAttribute("cuisine", new Cuisine());
        return "create";
    }

    @PostMapping("/save")
    public String saveCuisine(Cuisine cuisine) {
        cuisineService.save(cuisine);
        return "redirect:/cuisine/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteCuisine(@PathVariable long id , RedirectAttributes redirect) {
        cuisineService.remove(id);
        redirect.addFlashAttribute("message", "Delete customer successfully");
        return "redirect:/cuisine/list";
    }

    // Hiển thị form chỉnh sửa
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

    // Xử lý cập nhật
    @PostMapping("/update")
    public String updateCuisine(@RequestParam("id") Long id,
                                @ModelAttribute Cuisine cuisine,
                                RedirectAttributes redirect) {
        cuisineService.update(id, cuisine);
        redirect.addFlashAttribute("message", "Update cuisine successfully");
        return "redirect:/cuisine/list";
    }
}
