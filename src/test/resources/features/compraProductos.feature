@SHOP
Feature: Compra de productos en la tienda Polymer Shop
  Como usuario de la pagina web
  Quiero realizar validaciones
  Para realizar una compra correctamente

  Background:
    Given el usuario accede a la tienda Polymer Shop
      #ESC02
  @VALIDAR_PRECIO_TALLAS
  Scenario: Validar seleccion de chaquetas y tallas
    When elijo la chaqueta de la categoria Men "Men's Outerwear"
    And selecciono la talla "XL"
    And añado el producto a la cesta
    And elijo la chaqueta de la categoria Ladie "Ladies Outerwear"
    And selecciono la talla "S"
    And añado el producto a la cesta
    And Visualizo el carrito de compras
    Then valido el precio total "91.80"
    And valido las tallas requeridas "XL", "S"

      #ESC02
  @VALIDAR_PROCESO_TOTAL
  Scenario Outline: Validar que se realice el proceso completo de compra
    When elijo la chaqueta de la categoria Men "Men's Outerwear"
    And selecciono la talla "L"
    And añado el producto a la cesta
    And elijo la chaqueta de la categoria Ladie "Ladies Outerwear"
    And selecciono la talla "XS"
    And añado el producto a la cesta
    And  Visualizo el carrito de compras
    And realizo el checkout
    And completo los datos del formulario
      | email   | telefono   | direccion   | ciudad   | provincia   | codigoPostal   | pais   | nombreTitularTarjeta   | numeroTarjeta   | mExpiracion   | aExpiracion   | cvv   |
      | <email> | <telefono> | <direccion> | <ciudad> | <provincia> | <codigoPostal> | <pais> | <nombreTitularTarjeta> | <numeroTarjeta> | <mExpiracion> | <aExpiracion> | <cvv> |
    And finalizo la compra
    Then valido el mensaje de exito "Demo checkout process complete."
    Examples:
      | email          | telefono   | direccion | ciudad | provincia | codigoPostal | pais          | nombreTitularTarjeta | numeroTarjeta   | mExpiracion | aExpiracion | cvv |
      | flor@gmail.com | 9864567832 | fre12     | Lima   | Lima      | 123213       | United States | Flor Guerrero        | 542345675434567 | May         | 2026        | 342 |

     #ESC03
  @DATOS_PARAMETRIZADOS_CP-POSITIVOS_CP-NEGATIVOS
  Scenario: Validar la seleccion multiple de chaquetas y tallas incluyendo datos negativos mediante el consumo de datos de csv
    When cargo los datos desde "src/test/resources/data/chaquetas.csv"
    And selecciono las chaquetas y tallas del archivo
    Then valido la seleccion correcta







