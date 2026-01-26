import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

// Clase de prueba para Facturador
public class FacturadorTest {

    @Test
    @DisplayName("Test de constructor válido")
    public void testConstructorValido() {
        Facturador facturador = new Facturador();
        assertNotNull(facturador, "El objeto Facturador no debe ser nulo");
    }

    @Test
    @DisplayName("Test de cabecera de factura")
    public void testCabeceraCorrecta() throws Exception {
        PrintStream salidaConsola = System.out;
        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        PrintStream salidaTest = new PrintStream(salidaCapturada);
        System.setOut(salidaTest);

        try {
            Facturador.main(null);
        }
        catch (Exception e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        } finally {
            System.setOut(salidaConsola);
        }
        String salida = salidaCapturada.toString();
        assertTrue(salida.contains("FACTURA"), "La salida debe contener la cabecera 'FACTURA'");
    }
    @Test
    @DisplayName("Test de cliente") //Salida contiene cliente
    public void testCliente() {
        PrintStream salidaConsola = System.out;
        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        PrintStream salidaTest = new PrintStream(salidaCapturada);
        System.setOut(salidaTest);

        try {
            Facturador.main(null);
        }
        catch (Exception e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        } finally {
            System.setOut(salidaConsola);
        }
        String salida = salidaCapturada.toString();
        assertTrue(salida.contains("Cliente: Ayuntamiento de Badajoz"), "La salida debe contener el cliente 'Ayuntamiento de Badajoz'");
    }

    //Test base imponible
    @Test
    @DisplayName("Test de base imponible")
    public void testBaseImponible() {
        PrintStream salidaConsola = System.out;
        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        PrintStream salidaTest = new PrintStream(salidaCapturada);
        System.setOut(salidaTest);

        try {
            Facturador.main(null);
        }
        catch (Exception e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        } finally {
            System.setOut(salidaConsola);
        }
        String salida = salidaCapturada.toString();
        assertTrue(salida.contains("BASE IMPONIBLE: 72800.0"), "La salida debe contener la base imponible de la factura");
    }
    @Test
    @DisplayName("Test de IVA esperado")
    public void testIVA() {
        PrintStream salidaConsola = System.out;
        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        PrintStream salidaTest = new PrintStream(salidaCapturada);
        System.setOut(salidaTest);

        try {
            Facturador.main(null);
        }
        catch (Exception e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        } finally {
            System.setOut(salidaConsola);
        }
        String salida = salidaCapturada.toString();
        System.out.println(salida);
        assertTrue(salida.contains("IVA (21%): 15288,00"), "La salida debe contener el IVA de la factura");
    }
    @Test
    @DisplayName("Test de total factura esperado")
    public void testTotalFactura() {
        PrintStream salidaConsola = System.out;
        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        PrintStream salidaTest = new PrintStream(salidaCapturada);
        System.setOut(salidaTest);

        try {
            Facturador.main(null);
        }
        catch (Exception e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        } finally {
            System.setOut(salidaConsola);
        }
        String salida = salidaCapturada.toString();
        assertTrue(salida.contains("TOTAL FACTURA: 88088,00"), "La salida debe contener el total de la factura");
    }
    @Test
    @DisplayName("Test de créditos obtenidos")
    public void testCreditosObtenidos() {
        PrintStream salidaConsola = System.out;
        ByteArrayOutputStream salidaCapturada = new ByteArrayOutputStream();
        PrintStream salidaTest = new PrintStream(salidaCapturada);
        System.setOut(salidaTest);

        try {
            Facturador.main(null);
        }
        catch (Exception e) {
            fail("No se esperaba una excepción: " + e.getMessage());
        } finally {
            System.setOut(salidaConsola);
        }
        String salida = salidaCapturada.toString();
        assertTrue(salida.contains("Créditos obtenidos: 4108"), "La salida debe contener los créditos obtenidos");
    }

}