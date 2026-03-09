package com.testing.task;
import com.testing.interactions.SelectElementShadow;
import com.testing.userinterfaces.CompraProductosUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

    public class SeleccionarTallaMenTask implements Task {
        public final String talla;

        public SeleccionarTallaMenTask(String talla) {
            this.talla = talla;
        }

        @Override
        public <T extends Actor> void performAs(T actor) {
            actor.attemptsTo(
                    SelectElementShadow.on(talla, CompraProductosUI.SELECT_TALLA)
            );

        }

        public static com.testing.task.SeleccionarTallaMenTask onTheSite(String talla){
            return Tasks.instrumented(com.testing.task.SeleccionarTallaMenTask.class, talla);
        }
}
