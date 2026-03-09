package com.testing.interactions;
import net.serenitybdd.screenplay.Interaction;
import org.openqa.selenium.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;



import java.util.List;


public class ClickElement implements Interaction {

    private final String category;

    public ClickElement(String category) {
        this.category = category;
    }

    public static ClickElement inMenu(String category) {
        return Tasks.instrumented(ClickElement.class, category);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebElement shopApp = BrowseTheWeb.as(actor)
                .find(By.cssSelector("shop-app"));

        SearchContext shadowRoot = shopApp.getShadowRoot();

        List<WebElement> links = shadowRoot.findElements(By.cssSelector("a"));

        WebElement link = links.stream()
                .filter(el -> el.getText().trim().equals(category))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("No se encontró la categoría: " + category)
                );

        link.click();
    }
}
