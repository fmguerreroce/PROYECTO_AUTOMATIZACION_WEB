package com.testing.task;

import com.testing.interactions.ClickElement;
import com.testing.interactions.ClickElementShadow;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static com.testing.userinterfaces.CompraProductosUI.SELECT_CHAQUETA;


public class ChaquetaLadieTask implements Task {
    public final String categorias;

    public ChaquetaLadieTask(String categorias) {
        this.categorias = categorias;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ClickElement.inMenu(categorias),
                ClickElementShadow.on(SELECT_CHAQUETA)
        );

    }




    public static ChaquetaLadieTask onTheSite(String categorias){
        return Tasks.instrumented(ChaquetaLadieTask.class, categorias);
    }
}
