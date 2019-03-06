package org.springapp.controllers;

import org.springapp.auth.AuthUser;
import org.springapp.entity.*;
import org.springapp.repository.CategoryRepository;
import org.springapp.service.categories.CategoryService;
import org.springapp.service.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {

    private static final int pageableDefault = 20;
    private CategoryService service;
    private ProductService productService;
    private String sortDateMethod = "ASC";
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
    public String getMain(Model model) {

        List<Category> catname = service.findCatPathById(-1);
        List<Category> topmenu = service.findCatnameByLevel(2);
        List<Category> submenu = service.findCatnameByLevel(3);

        model.addAttribute("topmenu", topmenu);
        model.addAttribute("submenu", submenu);
        model.addAttribute("catname", catname);

        AuthUser user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("principal", user);

        return "index";
    }

    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        List<Category> catname = service.findCatPathById(-1);
        List<Category> topmenu = service.findCatnameByLevel(2);
        List<Category> submenu = service.findCatnameByLevel(3);

        model.addAttribute("topmenu", topmenu);
        model.addAttribute("submenu", submenu);
        model.addAttribute("catname", catname);
        model.addAttribute("login", "login");
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @GetMapping("/catalog/{id}")
    public String list(@PathVariable Integer id, Model model) {

        List<Category> catname = service.findCatPathById(id);
        List<Category> topmenu = service.findCatnameByLevel(2);
        List<Category> submenu = service.findCatnameByLevel(3);
        Category category = repository.findByIdEquals(id);
        List<Product> products = productService.findByCategory(category);

        model.addAttribute("topmenu", topmenu);
        model.addAttribute("submenu", submenu);
        model.addAttribute("catname", catname);
        model.addAttribute("products", products);
        //System.out.println(request.getSession().getId());
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

    private List<Category> findCatnameById(Integer id) {
        List<Category> categoryList = repository.findCatPathById(id);
        return categoryList;
    }

}
