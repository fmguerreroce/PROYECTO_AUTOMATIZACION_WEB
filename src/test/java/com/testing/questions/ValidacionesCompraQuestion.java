package com.testing.questions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ValidacionesCompraQuestion implements Question<String> {

    private final String[] ruta;

    public ValidacionesCompraQuestion(String[] ruta) {
        this.ruta = ruta;
    }

    public static ValidacionesCompraQuestion deLaRuta(String[] ruta) {
        return new ValidacionesCompraQuestion(ruta);
    }

    @Override
    public String answeredBy(Actor actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        return wait.until(d -> {

            SearchContext contextoActual = d;
            WebElement elemento = null;

            for (int i = 0; i < ruta.length; i++) {
                elemento = contextoActual.findElement(By.cssSelector(ruta[i]));

                if (i < ruta.length - 1) {
                    contextoActual = elemento.getShadowRoot();
                }
            }

            String texto = elemento.getText().trim();

            if (texto.isEmpty()) {
                return null;
            }

            // Limpieza del texto
            texto = texto.replace("Total", "")
                    .replace(":", "")
                    .replace("$", "")
                    .trim();

            return texto;
        });
    }
}
