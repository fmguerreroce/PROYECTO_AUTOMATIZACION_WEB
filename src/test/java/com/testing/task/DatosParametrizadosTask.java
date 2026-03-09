package com.testing.task;

import com.testing.interactions.*;
import com.testing.model.CsvModel;
import com.testing.userinterfaces.CompraProductosUI;
import com.testing.utils.LeaDatosDelArchivo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import java.util.List;
import static com.testing.userinterfaces.CompraProductosUI.BTN_ADD_TO_CART;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DatosParametrizadosTask implements Task {


    public static DatosParametrizadosTask onTheSite() {
        return instrumented(DatosParametrizadosTask.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<CsvModel> listaDeParametros = LeaDatosDelArchivo.as(actor).datosCargados();
        for (CsvModel parametro : listaDeParametros) {
            actor.attemptsTo(
                    NavigateToTask.sauceDemoPage(),
                    ClickElement.inMenu(parametro.getCategoria()),
                    ClickDinamicoShadow.on(parametro.getNombreChaqueta()),
                    SelectElementShadow.on(parametro.getTalla(), CompraProductosUI.SELECT_TALLA),
                    ClickElementShadow.on(BTN_ADD_TO_CART)

            );
        }
    }
}
