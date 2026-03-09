package com.testing.stepdefinitions;
import com.testing.interactions.ClickElementShadow;
import com.testing.model.CsvModel;
import com.testing.model.FormularioCompraModel;
import com.testing.questions.ValidacionesCompraQuestion;
import com.testing.questions.ValidarProductosCSV;
import com.testing.task.*;
import com.testing.userinterfaces.ValidacionesUI;
import com.testing.utils.LeaDatosDelArchivo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.Assert;
import java.util.List;
import static com.testing.userinterfaces.CompraProductosUI.*;
import static com.testing.userinterfaces.FormularioUI.BTN_PLACE_ORDER;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;


public class compraProductos {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el {} accede a la tienda Polymer Shop")
    public void elUsuarioAccedeALaPaginaSHOP(String actor) {
        theActorCalled(actor).attemptsTo(
                NavigateToTask.sauceDemoPage()
        );

    }

    @When("elijo la chaqueta de la categoria Men {string}")
    public void ElijoLaChaquetaDeLaCategoria(String categoria) {

        OnStage.theActorInTheSpotlight().attemptsTo(
                ChaquetaMenTask.onTheSite(categoria)
       );
    }

    @And("selecciono la talla {string}")
    public void seleccionoLaTalla(String talla) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SeleccionarTallaMenTask.onTheSite(talla)
        );
    }

    @And("añado el producto a la cesta")
    public void AñadoElProductoALaCesta() {
         OnStage.theActorInTheSpotlight().attemptsTo(
                 ClickElementShadow.on(BTN_ADD_TO_CART)

         );
    }

    @And("elijo la chaqueta de la categoria Ladie {string}")
    public void elUsuarioElijoLaChaquetaDeLaCategoriaLadie(String categorias) {

        OnStage.theActorInTheSpotlight().attemptsTo(
                ChaquetaLadieTask.onTheSite(categorias)
        );

    }

    @And("Visualizo el carrito de compras")
    public void Visualizo_el_carrito_de_compras() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ClickElementShadow.on(ICONO_CARRITO)

        );
    }

    @And("realizo el checkout")
    public void  realizo_el_checkout() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                ClickElementShadow.on(BTN_CHECKOUT)

        );
    }

    @And("completo los datos del formulario")
    public void completoLosDatosDelFormulario(DataTable dataTable) {
        theActorInTheSpotlight().attemptsTo(
                FormularioCompraTask.onTheSite(FormularioCompraModel.setData(dataTable).get(0))
        );
    }

    @And("finalizo la compra")
    public void finalizoLaCompra() {
        OnStage.theActorInTheSpotlight().attemptsTo(
      ClickElementShadow.on(BTN_PLACE_ORDER)
        );
    }

    @When("cargo los datos desde {string}")
    public void cargoLosDatosDesde(String ruta) {
        OnStage.theActorInTheSpotlight().can(LeaDatosDelArchivo.deRuta(ruta));
    }

    @And("selecciono las chaquetas y tallas del archivo")
    public void seleccionaLasChaquetasYTallasDelArchivo() {

        OnStage.theActorInTheSpotlight().attemptsTo(DatosParametrizadosTask.onTheSite());
    }

    @Then("valido la seleccion correcta")
    public void seValidaLaSeleccionCorrecta() {

        Actor actor = OnStage.theActorInTheSpotlight();

        List<CsvModel> listaDeParametros = LeaDatosDelArchivo.as(actor).datosCargados();

        CsvModel parametro = listaDeParametros.get(0);

        String resultado = parametro.getResultado();

        boolean enDetalle = ValidarProductosCSV.visible().answeredBy(actor);

        if (resultado.equalsIgnoreCase("OK")) {
            Assert.assertTrue("El producto debía abrir detalle", enDetalle);
        }

        if (resultado.equalsIgnoreCase("ERROR")) {
            Assert.assertFalse("No debía abrir detalle", enDetalle);
        }
    }

    @Then("valido el precio total {string}")
    public void validoElPrecioTotal(String precio) {

        OnStage.theActorInTheSpotlight().should(
                seeThat("el precio total del producto",
                        ValidacionesCompraQuestion.deLaRuta(ValidacionesUI.LBL_PRECIO_TOTAL),
                        containsString(precio))
        );

    }

    @And("valido las tallas requeridas {string}, {string}")
    public void validoLasTallasRequeridas(String talla1, String talla2) {

         //Validar la talla del primer producto
        OnStage.theActorInTheSpotlight().should(
                seeThat("La talla del primer producto",
                        ValidacionesCompraQuestion.deLaRuta(ValidacionesUI.TALLA_PRIMER_PRODUCTO),
                        containsString(talla1))
        );

           //Validar la talla del segundo producto
        OnStage.theActorInTheSpotlight().should(
                seeThat("La talla del segundo producto",
                        ValidacionesCompraQuestion.deLaRuta(ValidacionesUI.TALLA_SEGUNDO_PRODUCTO),
                        equalTo(talla2))
        );
    }

    @Then("valido el mensaje de exito {string}")
    public void validoElMensajeDeExito(String mensaje) {
        OnStage.theActorInTheSpotlight().should(
                seeThat(ValidacionesCompraQuestion.deLaRuta(ValidacionesUI.MSG_EXITO_PAGO),
                        containsString(mensaje))
        );
    }
}

