package org.springapp.api;

public class APIName {

    //redirect
    public static final String REDIRECT = "redirect:";
    public static final String INDEX = "/index";
    public static final String ROOT = "/";


    //category api links
    public static final String CATEGORIES = "/categories";
    public static final String CATEGORIES_ID = "/catalog/{id}";

    //company api link
    public static final String COMPANIES = "/companies";
    public static final String COMPANIES_SEARCH_BY_ID = COMPANIES + "/{id}";

    //user api link
    public static final String USER_REGISTER = "/register";
    public static final String USER_LOGIN = "/login";


    //cart api link
    public static final String CART = "/cart";
    public static final String CARTITEM_DELETE_BY_PRODUCT_ID = "/cart/delete";
    public static final String CART_CLEAR = "/cart/clear";
    public static final String CART_CHECKOUT = "/checkout";


    //Orders
    public static final String ORDERS = "/orders";
    public static final String ORDER = "/order";
    public static final String ORDER_CREATE = "/create";
    public static final String ORDERS_DETAIL_BY_ID = "/order/{id}";

    //auth APIs
    public static final String AUTH_API = "/auth";
    public static final String SESSION_DATA = "/session/data";
    public static final String USER_LOGOUT = "/logout";
    public static final String ADMIN_LOGIN_API = "/admin/login";
    
    //Categories APIs
    public static final String CATEGORIES_API = "/categories";
    public static final String CATEGORIES_ADD = "/create";
    public static final String CATEGORIES_UPDATE = "/update";
    public static final String CATEGORIES_DELETE = "/delete";
    public static final String CATEGORIES_DETAIL = "/detail/{category_id}";
    public static final String CATEGORIES_LIST = "/list";

}
