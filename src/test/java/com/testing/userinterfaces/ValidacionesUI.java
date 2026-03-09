package com.testing.userinterfaces;

public class ValidacionesUI {

    public static final String[] LBL_PRECIO_TOTAL = {
            "shop-app",
            "shop-cart",
            "span.subtotal"
    };

    public static final String[] TALLA_PRIMER_PRODUCTO = {
            "shop-app",
            "iron-pages > shop-cart",
            "shop-cart-item:nth-child(1)",
            "div.size > span"
    };

    public static final String[] TALLA_SEGUNDO_PRODUCTO = {
            "shop-app",
            "iron-pages > shop-cart",
            "shop-cart-item:nth-child(2)",
            "div.size > span"
    };

    public static final String[] MSG_EXITO_PAGO = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#pages > header.iron-selected > p"
    };

}
