package com.testing.task;

import com.testing.interactions.ClickElement;
import com.testing.interactions.ClickElementShadow;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;


import static com.testing.userinterfaces.CompraProductosUI.*;

public class ChaquetaMenTask implements Task {
  public final String categoria;

  public ChaquetaMenTask(String categoria) {
      this.categoria = categoria;
  }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ClickElement.inMenu(categoria),
                ClickElementShadow.on(SELECT_CHAQUETA)
        );

    }

    public static ChaquetaMenTask onTheSite(String categoria){
        return Tasks.instrumented(ChaquetaMenTask.class, categoria);
    }
}
