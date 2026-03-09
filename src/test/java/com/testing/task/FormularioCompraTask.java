package com.testing.task;

import com.testing.interactions.EnterTextShadow;
import com.testing.interactions.SelectElementShadow;
import com.testing.model.FormularioCompraModel;
import com.testing.userinterfaces.FormularioUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import static net.serenitybdd.screenplay.Tasks.instrumented;
public class FormularioCompraTask implements Task {

    FormularioCompraModel formularioCompraModel;
    public FormularioCompraTask(FormularioCompraModel formularioCompraModel)
    {
        this.formularioCompraModel = formularioCompraModel;
    }

    public static FormularioCompraTask onTheSite(FormularioCompraModel formularioCompraModel) {
        return instrumented(FormularioCompraTask.class, formularioCompraModel);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                EnterTextShadow.on(formularioCompraModel.getEmail(), FormularioUI.EMAIL_FIELD_PATH),
                EnterTextShadow.on(formularioCompraModel.getTelefono(), FormularioUI.PHONE_FIELD_PATH),
                EnterTextShadow.on(formularioCompraModel.getDireccion(), FormularioUI.SHIP_ADDRESS_PATH),
                EnterTextShadow.on(formularioCompraModel.getCiudad(), FormularioUI.SHIP_CITY_PATH),
                EnterTextShadow.on(formularioCompraModel.getProvincia(), FormularioUI.SHIP_STATE_PATH),
                EnterTextShadow.on(formularioCompraModel.getCodigoPostal(), FormularioUI.SHIP_ZIP_PATH),
                SelectElementShadow.on(formularioCompraModel.getPais(), FormularioUI.SHIP_COUNTRY_PATH),
                EnterTextShadow.on(formularioCompraModel.getNombreTitularTarjeta(), FormularioUI.CC_NAME_PATH),
                EnterTextShadow.on(formularioCompraModel.getNumeroTarjeta(), FormularioUI.CC_NUMBER_PATH),
                SelectElementShadow.on(formularioCompraModel.getmExpiracion(), FormularioUI.CC_EXP_MONTH_PATH),
                SelectElementShadow.on(formularioCompraModel.getaExpiracion(), FormularioUI.CC_EXP_YEAR_PATH),
                EnterTextShadow.on(formularioCompraModel.getCVV(), FormularioUI.CC_CVV_PATH)

        );
    }
}
