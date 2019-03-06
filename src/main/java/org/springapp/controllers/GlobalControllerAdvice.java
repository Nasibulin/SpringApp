package org.springapp.controllers;

import org.springapp.entity.Category;
import org.springapp.repository.CategoryRepository;
import org.springapp.service.categories.CategoryService;
import org.springapp.service.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

//@ControllerAdvice
//public class GlobalControllerAdvice {
//
//    private CategoryService categoryService;
//    private ProductService productService;
//
//    @Autowired
//    public void setCategoryService(CategoryService service) {
//        this.categoryService = service;
//    }
//
//    @Autowired
//    public void setProductService(ProductService service) {
//        this.productService = service;
//    }
//
//    @ModelAttribute
//    public void globalAttributes1(Model model) {
//        List<Category> catname = categoryService.findCatPathById(-1);
//        model.addAttribute("catname", catname);
//    }
//    @ModelAttribute
//    public void globalAttributes2(Model model) {
//        List<Category> topmenu = categoryService.findCatnameByLevel(2);
//        model.addAttribute("topmenu", topmenu);
//    }
//    @ModelAttribute
//    public void globalAttributes3(Model model) {
//        List<Category> submenu = categoryService.findCatnameByLevel(3);
//        model.addAttribute("submenu", submenu);
//    }
//
//}
