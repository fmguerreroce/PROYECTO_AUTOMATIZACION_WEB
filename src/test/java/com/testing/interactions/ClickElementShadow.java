package com.testing.interactions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static net.serenitybdd.screenplay.Tasks.instrumented;

    public class ClickElementShadow implements Task {

        private final String[] selectores;

        public ClickElementShadow(String... selectores) {
            this.selectores = selectores;
        }

        public static ClickElementShadow on(String... selectores) {
            return instrumented(ClickElementShadow.class, (Object) selectores);
        }

        @Override
        public <T extends Actor> void performAs(T actor) {
            WebDriver driver = BrowseTheWeb.as(actor).getDriver();
            // Espera explícita para asegurar que cada nivel de Shadow DOM se expanda
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            SearchContext contextoActual = driver;

            for (int i = 0; i < selectores.length; i++) {
                final String selector = selectores[i];
                final SearchContext contextoFijo = contextoActual;

                WebElement elemento = wait.until(d -> contextoFijo.findElement(By.cssSelector(selector)));

                if (i < selectores.length - 1) {
                    contextoActual = wait.until(d -> {
                        SearchContext shadow = elemento.getShadowRoot();
                        if (shadow == null) throw new RuntimeException("Shadow Root no listo en: " + selector);
                        return shadow;
                    });
                } else {
                    elemento.click();
                }
            }
        }
    }

