package com.testing.interactions;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ClickDinamicoShadow implements Interaction {

    private final String nombreProducto;

    public ClickDinamicoShadow(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public static ClickDinamicoShadow on(String nombreProducto) {
        return instrumented(ClickDinamicoShadow.class, nombreProducto);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(d -> {

            try {

                SearchContext shadowApp =
                        d.findElement(By.cssSelector("shop-app")).getShadowRoot();

                SearchContext shadowList =
                        shadowApp.findElement(By.cssSelector("iron-pages > shop-list")).getShadowRoot();

                List<WebElement> productos =
                        shadowList.findElements(By.cssSelector("ul > li > a > shop-list-item"));

                if (productos.isEmpty()) {
                    return false;
                }

                for (WebElement producto : productos) {

                    SearchContext shadowItem = producto.getShadowRoot();

                    WebElement tituloElemento =
                            shadowItem.findElement(By.cssSelector("div.title"));

                    String titulo = tituloElemento.getText().trim();

                    System.out.println("Producto detectado: " + titulo);

                    if (titulo.equalsIgnoreCase(nombreProducto.trim())) {

                        js.executeScript("arguments[0].click();", tituloElemento);

                        return true;
                    }
                }

                return false;

            } catch (Exception e) {
                return false;
            }

        });
    }
}