package org.springapp.controllers;

import org.springapp.api.APIName;
import org.springapp.auth.AuthUser;
import org.springapp.auth.UserAuthentication;
import org.springapp.auth.service.AuthUserDetailsService;
import org.springapp.entity.*;
import org.springapp.service.categories.CategoryService;
import org.springapp.service.orders.OrderService;
import org.springapp.service.products.ProductService;
import org.springapp.service.roles.RoleService;
import org.springapp.service.users.UserAddressService;
import org.springapp.service.users.UserService;
import org.springapp.util.Constant;
import org.springapp.util.StringUtil;
import org.springapp.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;


@RestController
@SessionAttributes({"cart", "search"})
public class MainController {

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private AuthUserDetailsService authUserDetailsService;
    private AuthUser customer;

    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

    @ModelAttribute("search")
    public List<Product> search() {
        return new ArrayList<Product>();
    }

    @PostConstruct
    public void init() {
        customer = (AuthUser) authUserDetailsService.loadUserByUsername("denzel@gmail.com");
    }


    @ModelAttribute
    public void globalAttributes(@ModelAttribute("cart") Cart cart, Model model) {
//        List<Category> catname = categoryService.findCatPathById(Constant.CATALOG_LEVEL.ROOT.getLevel());
//        model.addAttribute("catname", catname);
//        model.addAttribute("cart", cart);
        AuthUser user = null;
        try {
            AuthUser authUser = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User customer = userService.findByEmail(authUser.getUsername());
            model.addAttribute("principal", authUser);
            model.addAttribute("customer", customer);
        } catch (ClassCastException ex) {
            SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(customer));
        }

    }

//    @GetMapping(APIName.ROOT)
//    public String getMain(Model model) {
//        return APIName.INDEX;
//    }

    @RequestMapping(value = APIName.MENU, //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Category> getMenu(@PathVariable Integer level) {
        List<Category> menu = categoryService.findCatnameByLevel(level);
        return menu;
    }

    @PostMapping(APIName.SEARCH)
    public String postSearch(@RequestParam String product, @RequestParam Integer category, Model model, @ModelAttribute("search") ArrayList<Product> search) {
        List<Category> catname = categoryService.findCatPathById(category);
        List<Category> cat = categoryService.findByParentIdEquals(category);
        search = (ArrayList<Product>) productService.findByCategoryInAndDescriptionContainingIgnoreCase(cat, product);
        model.addAttribute("catname", catname);
        model.addAttribute("search", search);
        return APIName.REDIRECT.concat(APIName.SEARCH);
    }

    @GetMapping(APIName.SEARCH)
    public String getSearch(@ModelAttribute("catname") ArrayList<Category> catname, @ModelAttribute("search") ArrayList<Product> search, Model model) {
        model.addAttribute("catname", catname);
        model.addAttribute("search", search);
        return APIName.SEARCH;
    }

//    @GetMapping(APIName.CART)
//    public String getCart(@ModelAttribute("cart") Cart cart, Model model) {
//
//        return APIName.CART;
//    }

//    @PostMapping(APIName.CART)
//    public String addToCart(@RequestParam Integer id, @RequestParam Integer amount, @ModelAttribute("cart") Cart cart,
//                            Model model, HttpServletRequest request) {
//        CartItem cartItem = new CartItem();
//        cartItem.setId(id);
//        cartItem.setProduct(productService.getProductById(id));
//        cartItem.setQuantity(amount);
//        cart.addCartItems(cartItem);
//        model.addAttribute("cart", cart);
//        String referer = request.getHeader("Referer");
//        return APIName.REDIRECT.concat(referer);
//    }

//    @PostMapping(APIName.CARTITEM_DELETE_BY_PRODUCT_ID)
//    public String deleteFromCart(@RequestParam Integer id, @ModelAttribute("cart") Cart cart,
//                                 Model model, HttpServletRequest request) {
//
//        CartItem cartItem = new CartItem();
//        cartItem.setId(id);
//        cartItem.setProduct(productService.getProductById(id));
//        cartItem.setQuantity(1);
//        cart.getCartItems().remove(cartItem);
//        model.addAttribute("cart", cart);
//
//        String referer = request.getHeader("Referer");
//        return APIName.REDIRECT.concat(referer);
//    }

//    @PostMapping(APIName.CART_CLEAR)
//    public String clearCart(@ModelAttribute("cart") Cart cart, Model model) {
//        cart.clearCart();
//        return APIName.CART;
//    }

    @GetMapping(APIName.CART_CHECKOUT)
    public String getCheck(@ModelAttribute("customer") User customer, Model model) {
        model.addAttribute("customer", customer);
        model.addAttribute("userAddress", customer.getUserAddress() != null ? customer.getUserAddress() : new UserAddress());
        return APIName.CART_CHECKOUT;
    }

    @GetMapping(APIName.ORDERS)
    public Set<Order> getOrders(@ModelAttribute("customer") User customer, Model model) {
        Set<Order> orderSet = orderService.findAllByUserOrderByIdAsc(customer);
//        model.addAttribute("orderset", orderSet);
//        model.addAttribute("orderstotal", orderSet.stream().map(i -> i.getOrderTotal()).reduce(BigDecimal.ZERO, BigDecimal::add));
        return orderSet;
    }

    @GetMapping(APIName.ORDERS_DETAIL_BY_ID)
    public String getOrder(@PathVariable Integer id, @ModelAttribute("customer") User customer, Model model) {
        Order order = orderService.getOrderByIdAndUser(id, customer);
        model.addAttribute("order", order);
        return APIName.ORDER;
    }

    @PostMapping(APIName.ORDER_CREATE)
    public String create(UserAddress userAddress, @ModelAttribute("cart") Cart cart, @ModelAttribute("customer") User customer, @RequestParam(value = "same-address", required = false) boolean sameAddress, @RequestParam(value = "save-info", required = false) boolean saveInfo, Model model) {
        if (cart.getCartItems().isEmpty()) return APIName.CART;
        UserAddress customerAddress;
        if (customer.getUserAddress() == null && saveInfo) {
            customerAddress = new UserAddress();
            customerAddress.setUser(customer);
            customerAddress.setAddress(userAddress.getAddress());
            customerAddress.setApartment(userAddress.getApartment());
            userAddressService.save(customerAddress);
        } else {
            customerAddress = userAddressService.getAddressByUser(customer);
            customerAddress.setAddress(userAddress.getAddress());
            customerAddress.setApartment(userAddress.getApartment());
            userAddressService.save(customerAddress);
        }

        final Order order = new Order();
        order.setUser(customer);
        order.setCreatedAt(new Date());
        order.setStatus(Constant.ORDER_STATUS.PENDING.getStatus());

        OrderAddress orderAddress = new OrderAddress();
        if (sameAddress) {
            orderAddress.setRegion(customerAddress.getAddress());
            orderAddress.setSuffix(customerAddress.getApartment());
        } else {
            orderAddress.setRegion(userAddress.getAddress());
            orderAddress.setSuffix(userAddress.getApartment());
        }
        orderAddress.setOrder(order);
        orderAddress.setCreatedAt(new Date());

        order.setOrderAddress(orderAddress);

        cart.getCartItems().forEach(i -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(i.getProduct());
            orderDetail.setQuantity(i.getQuantity());
            order.getOrderDetailsSet().add(orderDetail);
        });

        orderService.saveOrder(order);
        cart.clearCart();
        return APIName.REDIRECT.concat(APIName.ORDERS);
    }

    @GetMapping(APIName.USER_REGISTER)
    public String getRegister(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return APIName.USER_REGISTER;
    }

    @RequestMapping(value = APIName.USER_REGISTER, method = RequestMethod.POST)
    public String createNewUser(@Valid User user, Model model, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return APIName.USER_REGISTER;
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
            return APIName.USER_REGISTER;
        }
    }

    @RequestMapping(APIName.USERS_LOGIN)
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return APIName.USERS_LOGIN;
    }

    @RequestMapping(value = APIName.CATEGORIES_ID, //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Product> list(@PathVariable Integer id, Model model) {
        List<Category> catname = categoryService.findCatPathById(id);
        Category category = categoryService.findByIdEquals(id);
        List<Product> products = productService.findByCategory(category);
        model.addAttribute("catname", catname);
        model.addAttribute("products", products);
        return products;
    }

}
