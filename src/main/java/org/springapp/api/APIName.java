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

    // auth APIs
    public static final String AUTH_API = API_VERSION + "/auth";
    public static final String SESSION_DATA = "/session/data";
    public static final String USER_LOGOUT = "/logout";
    public static final String ADMIN_LOGIN_API = "/admin/login";

    //category api links
    public static final String CATEGORIES = API_VERSION+"/categories";
    public static final String CATEGORIES_ID = API_VERSION+"/categories/{id}/products";

    //menu api link
    public static final String MENU = API_VERSION+"/menu/{level}";

    //user api link
    public static final String USERS = "/users";
    public static final String USER_REGISTER = "/register";
    public static final String USERS_LOGIN = "/api/users/login";
    public static final String USERS_LOGOUT = "/api/users/logout";

    //cart api link
    public static final String CART = "/cart";
    public static final String CARTITEM_DELETE_BY_PRODUCT_ID = "/cart/delete";
    public static final String CART_CLEAR = "/cart/clear";
    public static final String CART_CHECKOUT = "/checkout";

    //orders api link
    public static final String ORDERS = "/api/users/orders";
    public static final String ORDER = API_VERSION+"/order";
    public static final String ORDER_CREATE = API_VERSION+"/create";
    public static final String ORDERS_DETAIL_BY_ID = API_VERSION+"/order/{id}";


}
