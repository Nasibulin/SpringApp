package org.springapp.controllers;

import org.springapp.auth.AuthUser;
import org.springapp.auth.UserAuthentication;
import org.springapp.auth.service.AuthUserDetailsService;
import org.springapp.entity.*;
import org.springapp.service.categories.CategoryService;
import org.springapp.service.products.ProductService;
import org.springapp.service.role.RoleService;
import org.springapp.service.user.UserService;
import org.springapp.util.Constant;
import org.springapp.util.StringUtil;
import org.springapp.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("cart")
public class MainController {

    private static final int pageableDefault = 20;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthUserDetailsService authUserDetailsService;
    //private AuthUser guest;

    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

//    @PostConstruct
//    public void init() {
//        guest = (AuthUser) authUserDetailsService.loadUserByUsername("guest@gmail.com");
//    }

    @ModelAttribute
    public void globalAttributes(@ModelAttribute("cart") Cart cart, Model model) {
        List<Category> topmenu = categoryService.findCatnameByLevel(2);
        List<Category> submenu = categoryService.findCatnameByLevel(3);
        List<Category> catname = categoryService.findCatPathById(-1);
        model.addAttribute("catname", catname);
        model.addAttribute("topmenu", topmenu);
        model.addAttribute("submenu", submenu);
        model.addAttribute("cart", cart);
        //AuthUser user = null;
        try {
            AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("principal", authUser);
        } catch (ClassCastException ex) {
//            SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(guest));
        }

    }

    @GetMapping("/")
    public String getMain(Model model) {
        return "index";
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        return "cart";
    }

    @GetMapping("/checkout")
    public String getCheck(Model model) {
        return "checkout";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, Model model, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/register";
        } else {

            User newUser = new User();
            newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            newUser.setRole(roleService.findRoleByAuthority(Constant.USER_ROLE.NORMAL_USER.name()));
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            newUser.setStatus(Constant.STATUS.ACTIVE_STATUS.getValue());
            newUser.setPhone(StringUtil.deleteCharacters(user.getPhone(), "+()- "));

            userService.saveUser(newUser);

            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
            return "/register";
        }
    }

    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        //model.addAttribute("loginmsg", loginmsg);
//        AuthUser user = null;
//        try {
//            user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        } catch (ClassCastException ex) {
////            SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(guest));
//        }
//        model.addAttribute("principal", user);
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

    @PostMapping("/cart")
    public String addToCart(@RequestParam Integer id, @RequestParam Integer amount, @ModelAttribute("cart") Cart cart, Model model, HttpServletRequest request) {
//        if (cart != null) {
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setProduct(productService.getProductById(id));
        cartItem.setQuantity(amount);
        cart.addCartItems(cartItem);
        model.addAttribute("cart", cart);
//        } else {
//            CartItem cartItem = new CartItem();
//            cartItem.setId(id);
//            cartItem.setProduct(productService.getProductById(id));
//            cartItem.setQuantity(amount);
//            cart.addCartItems(cartItem);
//            model.addAttribute("cart", cart);
//        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("/cart/clear")
    public String clearCart(@ModelAttribute("cart") Cart cart, Model model) {
        cart.clearCart();
        return "cart";
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