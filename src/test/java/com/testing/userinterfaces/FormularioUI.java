package com.testing.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class FormularioUI {

    public static final String[] EMAIL_FIELD_PATH = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#accountEmail"
    };
    public static final String[] PHONE_FIELD_PATH = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#accountPhone"
    };
    public static final String[] SHIP_ADDRESS_PATH = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#shipAddress"
    };
    public static final String[] SHIP_CITY_PATH = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#shipCity"
    };
    public static final String[] SHIP_STATE_PATH = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#shipState"
    };
    public static final String[] SHIP_ZIP_PATH = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#shipZip"
    };
    public static final String[] SHIP_COUNTRY_PATH = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#shipCountry"
    };
    public static final String[] CC_NAME_PATH = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#ccName"
    };
    public static final String[] CC_NUMBER_PATH = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#ccNumber"
    };
    public static final String[] CC_EXP_MONTH_PATH = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#ccExpMonth"

    };
    public static final String[] CC_EXP_YEAR_PATH = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#ccExpYear"

    };
    public static final String[] CC_CVV_PATH = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#ccCVV"
    };
    public static final String[] BTN_PLACE_ORDER = {
            "shop-app",
            "iron-pages > shop-checkout",
            "#submitBox > input[type=button]"
    };

}
