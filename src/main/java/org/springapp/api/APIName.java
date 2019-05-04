package org.springapp.api;

public class APIName {

    public static final String API_VERSION = "/api";


    // charset
    public static final String CHARSET = "application/json;charset=utf-8";

    //redirect
    public static final String REDIRECT = "redirect:";
    public static final String INDEX = "/index";
    public static final String ROOT = "/";
    public static final String SEARCH = "/search";


    //category api links
    public static final String CATEGORIES = API_VERSION+"/categories";
    public static final String CATEGORIES_ID = API_VERSION+"/categories/{id}/products";

    //menu api link
    public static final String MENU = API_VERSION+"/menu/{level}";

    //user api link
    public static final String USERS = "/users";
    public static final String USER_REGISTER = "/register";
    public static final String USERS_LOGIN = "/login";
    public static final String USERS_LOGOUT = "/logout";

    //cart api link
    public static final String CART = "/cart";
    public static final String CARTITEM_DELETE_BY_PRODUCT_ID = "/cart/delete";
    public static final String CART_CLEAR = "/cart/clear";
    public static final String CART_CHECKOUT = "/checkout";

    //orders api link
    public static final String ORDERS = API_VERSION+"/orders";
    public static final String ORDER = API_VERSION+"/order";
    public static final String ORDER_CREATE = API_VERSION+"/create";
    public static final String ORDERS_DETAIL_BY_ID = API_VERSION+"/order/{id}";


}
