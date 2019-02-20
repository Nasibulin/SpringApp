package org.springapp.controllers;

import org.springapp.entity.Category;
import org.springapp.repository.CategoryRepository;
import org.springapp.service.CategoryService;
import org.springapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {

    private CategoryService service;
    private ProductService productService;
    private String sortDateMethod = "ASC";
    private static final int pageableDefault = 20;

    @Autowired
    private CategoryRepository repository;

    @Autowired
    public void setCategoryService(CategoryService service) {
        this.service = service;
    }

    @Autowired
    public void setProductService(ProductService service) {
        this.productService = service;
    }

    @GetMapping("/")
    public String list(Model model) {

        List<Category> catname = service.findCatPathById(-1);
        List<Category> topmenu = service.findCatnameByLevel(2);
        List<Category> submenu = service.findCatnameByLevel(3);

        model.addAttribute("topmenu", topmenu);
        model.addAttribute("submenu", submenu);
        model.addAttribute("catname", catname);

        return "index";
    }

//    @GetMapping("/sort/{sortDate}")
//    public String sortChoose(@PathVariable String sortDate) {
//        sortDateMethod = sortDate;
//        return "redirect:/";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable Integer id, Model model, HttpServletRequest request) {
//        Category category = service.getCategoryById(id);
//        model.addAttribute("category", category);
////        @SuppressWarnings("unchecked")
////        List<String> msgs = (List<String>) request.getSession().getAttribute("MY_MESSAGES");
////        if (msgs == null) {
////            msgs = new ArrayList<>();
////            request.getSession().setAttribute("MY_MESSAGES", msgs);
////        }
////        msgs.add(String.valueOf(id));
////        request.getSession().setAttribute("MY_MESSAGES", msgs);
//        System.out.println(request.getSession().getId());
//        return "operations/edit";
//    }
//
//    @PostMapping("/update")
//    public String saveNote(@RequestParam Integer id, @RequestParam String message,
//                           @RequestParam(value = "done", required = false) boolean done) {
//        //service.updateNote(id, message, done);
//        return "redirect:/";
//    }
//
//    @GetMapping("/new")
//    public String newCategory() {
//        return "operations/new";
//    }
//
//    @PostMapping("/save")
//    public String updateCategory(@RequestParam String message) {
//        //service.saveNote(new Note(message));
//        return "redirect:/";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable Integer id) {
//        //service.deleteNote(id);
//        return "redirect:/";
//    }

    private List<Category> filterAndSort() {
        List<Category> category = null;
//        category = service.findByIdEquals(
//                productService.getProductById(1024).getCategory().getId());

        category = service.findByParentIdEquals(290);
        return category;
    }

    private List<Category> findCatnameById(Integer id) {
        List<Category> categoryList = repository.findCatPathById(id);
        return categoryList;
    }

}
