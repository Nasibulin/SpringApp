package org.springapp.controllers;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springapp.auth.AuthUser;
import org.springapp.auth.UserAuthentication;
import org.springapp.auth.service.AuthUserDetailsService;
import org.springapp.entity.*;
import org.springapp.repository.CategoryRepository;
import org.springapp.service.categories.CategoryService;
import org.springapp.service.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
@SessionAttributes("cart")
public class MainController {

    private static final int pageableDefault = 20;
    private final static String loginmsg = "loginmsg";
    private CategoryService categoryService;
    private ProductService productService;
    private AuthUser guest;


    @ModelAttribute("cart")
         public Cart cart() {
        return new Cart();
    }

    @Autowired
    private AuthUserDetailsService authUserDetailsService;


    @Autowired
    public void setCategoryService(CategoryService service) {
        this.categoryService = service;
    }

    @Autowired
    public void setProductService(ProductService service) {
        this.productService = service;
    }

    @PostConstruct
    public void init() {
        guest = (AuthUser) authUserDetailsService.loadUserByUsername("guest@gmail.com");
    }

    @ModelAttribute
    public void globalAttributes(Model model) {
        List<Category> topmenu = categoryService.findCatnameByLevel(2);
        List<Category> submenu = categoryService.findCatnameByLevel(3);
        List<Category> catname = categoryService.findCatPathById(-1);
        model.addAttribute("catname", catname);
        model.addAttribute("topmenu", topmenu);
        model.addAttribute("submenu", submenu);
        AuthUser user = null;
        try {
            user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (ClassCastException ex) {
            SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(guest));
        }
        model.addAttribute("principal", user);
    }


    @GetMapping("/")
    public String getMain(Model model) {
        return "index";
    }

    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        model.addAttribute("loginmsg", loginmsg);
        return "login";
    }


    @GetMapping("/catalog/{id}")
    public String list(@PathVariable Integer id, Model model) {
        List<Category> catname = categoryService.findCatPathById(id);
        Category category = categoryService.findByIdEquals(id);
        List<Product> products = productService.findByCategory(category);
        model.addAttribute("catname", catname);
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Integer id, @RequestParam Integer amount, @ModelAttribute("cart") Cart cart, Model model, HttpServletRequest request) {
        if (cart != null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(productService.getProductById(id));
            cartItem.setQuantity(amount);
            cartItem.setId(id);
            cartItem.updateSubTotal();
            cart.addCartItems(cartItem);
            cart.updateGrandTotal();
            cart.updateQuantity();
            model.addAttribute("cart", cart);
            System.out.println(cart);
        } else {
            //Cart newcart = new Cart();
            CartItem cartItem = new CartItem();
            cartItem.setProduct(productService.getProductById(id));
            cartItem.setId(id);
            cartItem.setQuantity(amount);
            cartItem.updateSubTotal();
            cart.addCartItems(cartItem);
            cart.updateGrandTotal();
            cart.updateQuantity();
            model.addAttribute("cart", cart);
            System.out.println(cart);
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
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

}
