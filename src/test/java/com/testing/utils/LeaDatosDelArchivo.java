package com.testing.utils;
import com.opencsv.bean.CsvToBeanBuilder;
import com.testing.model.CsvModel;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import java.io.FileReader;
import java.util.List;

    public class LeaDatosDelArchivo implements Ability {
        private final List<CsvModel> datos;

        // Constructor privado que inicializa la carga del TDM
        private LeaDatosDelArchivo(String rutaArchivo) {
            try {
                this.datos = new CsvToBeanBuilder<CsvModel>(new FileReader(rutaArchivo))
                        .withType(CsvModel.class)
                        .withSeparator(',')
                        .withIgnoreEmptyLine(true)
                        .withThrowExceptions(false)
                        .build()
                        .parse();
            } catch (Exception e) {
                throw new RuntimeException("Error crítico TDM: No se pudo procesar el CSV -> " + e.getMessage());
            }
        }

        // Método estático para asignar la habilidad al actor
        public static LeaDatosDelArchivo deRuta(String ruta) {
            return new LeaDatosDelArchivo(ruta);
        }

        // Método para recuperar la habilidad desde el actor
        public static LeaDatosDelArchivo as(Actor actor) {
            if (actor.abilityTo(LeaDatosDelArchivo.class) == null) {
                throw new RuntimeException("El actor no tiene la habilidad de leer archivos CSV.");
            }
            return actor.abilityTo(LeaDatosDelArchivo.class);
        }
        // Retorna la lista de objetos mapeados
        public List<CsvModel> datosCargados() {
            return datos;
        }
}
