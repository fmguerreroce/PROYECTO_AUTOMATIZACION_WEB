package com.testing.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EnterTextShadow implements Interaction {

    private final String texto;
    private final String[] selectores;

    public EnterTextShadow(String texto, String... selectores) {
        this.texto = texto;
        this.selectores = selectores;
    }

    public static EnterTextShadow on(String texto, String... selectores) {
        return instrumented(EnterTextShadow.class, texto, selectores);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        SearchContext contextoActual = driver;

        for (int i = 0; i < selectores.length; i++) {
            final String selector = selectores[i];
            final SearchContext contextoFijo = contextoActual;

            WebElement elemento = wait.until(d -> contextoFijo.findElement(By.cssSelector(selector)));

            if (i < selectores.length - 1) {
                // Navegar al siguiente Shadow Root
                contextoActual = wait.until(d -> elemento.getShadowRoot());
            } else {

                // Scroll para evitar intercepción
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", elemento);

                // Esperar a que el elemento esté habilitado
                wait.until(d -> elemento.isEnabled());

                // Limpiar y escribir
                elemento.clear();
                elemento.sendKeys(texto);
            }
        }
    }
}
