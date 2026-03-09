package com.testing.questions;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class ValidarProductosCSV implements Question<Boolean> {

        public static ValidarProductosCSV visible(){
            return new ValidarProductosCSV();
        }

        @Override
        public Boolean answeredBy(Actor actor) {

            String url = BrowseTheWeb.as(actor)
                    .getDriver()
                    .getCurrentUrl();

            return url.contains("/detail/");
        }
    }

