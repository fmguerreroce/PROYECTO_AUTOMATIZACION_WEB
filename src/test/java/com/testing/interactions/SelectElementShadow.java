package com.testing.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SelectElementShadow implements Interaction {

    private final String valorSeleccionar;
    private final String[] rutaSelectores;

    // El constructor recibe la variable (valor) y la ruta (selectores)
    public SelectElementShadow(String valorSeleccionar, String... rutaSelectores) {
        this.valorSeleccionar = valorSeleccionar;
        this.rutaSelectores = rutaSelectores;
    }

    public static SelectElementShadow on(String valor, String... ruta) {
        return instrumented(SelectElementShadow.class, valor, (Object) ruta);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        SearchContext contextoActual = driver;

        for (int i = 0; i < rutaSelectores.length; i++) {
            String selector = rutaSelectores[i];
            final SearchContext contextoFijo = contextoActual;

            WebElement host = wait.until(d -> contextoFijo.findElement(By.cssSelector(selector)));

            if (i < rutaSelectores.length - 1) {
                contextoActual = wait.until(d -> {
                    SearchContext shadow = host.getShadowRoot();
                    if (shadow == null) throw new RuntimeException("Shadow Root no listo: " + selector);
                    return shadow;
                });
            } else {
                // Al llegar al final, usamos la variable 'valorSeleccionar'
                Select select = new Select(host);
                select.selectByVisibleText(valorSeleccionar);
            }
        }
    }
}
