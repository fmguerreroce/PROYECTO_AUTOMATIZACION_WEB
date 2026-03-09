package com.testing.userinterfaces;


public class CompraProductosUI {

    public static final String[] SELECT_CHAQUETA = {
            "shop-app",
            "iron-pages > shop-list",
            "ul > li:nth-child(1) > a > shop-list-item",
            "div"
    };

    public static final String[] SELECT_TALLA = {
            "shop-app",
            "iron-pages > shop-detail",
            "#sizeSelect"
    };

    public static final String[] BTN_ADD_TO_CART = {
            "shop-app",
            "iron-pages > shop-detail",
            "shop-button > button"
    };
    public static final String[] ICONO_CARRITO = {
            "shop-app",
            "#header > app-toolbar > div.cart-btn-container > a > paper-icon-button",
            "#icon"
    };
    public static final String[] BTN_CHECKOUT = {
            "shop-app",
            "iron-pages > shop-cart",
            "div.checkout-box > shop-button > a"
    };

}

