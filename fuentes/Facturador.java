import java.util.*;

public class Facturador{
	
	//Tipos de conciertos
	public enum TipoConcierto {HEAVY, ROCK}

	//Repertorio de conciertos del grupo
	static String[][] conciertos = {
		 {"Tributo Robe", "heavy"}
		,{"Homaneje Queen", "rock"}
		,{"Magia Knoppler", "rock"}
		,{"Demonios Rojos", "heavy"}
	};

	//Tarifas y umbrales
	static final Double BASE_HEAVY = 4000d;
	static final Double BASE_ROCK = 3000d;
	static final Integer UMBRAL_HEAVY = 500;
	static final Integer UMBRAL_ROCK = 1000;
	static final Integer EXTRA_HEAVY = 20;
	static final Integer EXTRA_ROCK = 30;
	static final Double IVA = 0.21;

	//Actuaciones realizadas indicando el concierto ofrecido y audiencias obtenidas.
	static Integer[][] datosActuaciones = {{0, 2000},{2, 1200},{0, 950},{3, 1140}};
	
	//Cliente al que se factura
	static String cliente = "Ayuntamiento de Badajoz";

	//Método principal
	public static void main(String[] args) throws Exception{
		Double totalFactura = 0d;
		Integer creditos = 0;

		System.out.println("FACTURA DE ACTUACIONES");
		System.out.println("Cliente: " + cliente);

		//Recorrido de las actuaciones para cálculo de la factura
		List<Actuacion> listaActuaciones = crearListaActuaciones(datosActuaciones);
		for (Actuacion actuacion : listaActuaciones) {
			Integer indiceConcierto = actuacion.indiceConcierto();
			Integer asistentes = actuacion.asistentes();

			String nombreConcierto = conciertos[indiceConcierto][0];
			String tipoConcierto = conciertos[indiceConcierto][1];

			Double importeActuacion = calcularImporteActuacion(tipoConcierto, asistentes);
			Integer creditosActuacion = calcularCreditos(tipoConcierto, asistentes);

			//Acumulación de totales
			totalFactura += importeActuacion;
			creditos += creditosActuacion;

			//Detalle de la actuación
			System.out.printf(" - %s: %d asistentes, Importe: %.2f euros, Créditos: %d\n",
					nombreConcierto, asistentes, importeActuacion, creditosActuacion);
		}

		System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
		System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * IVA);
		System.out.printf("TOTAL FACTURA: %.2f euros\n", totalFactura * (1 + IVA));
		System.out.println("Créditos obtenidos: " + creditos);

	}

	public static List<Actuacion> crearListaActuaciones(Integer[][] datosActuaciones){
		List<Actuacion> listaActuaciones = new ArrayList<>();
		
		for (Integer[] datosActuacion : datosActuaciones) {
			
			Integer indiceConcierto = datosActuacion[0];
			Integer asistentes = datosActuacion[1];
			
			Actuacion actuacion = new Actuacion(indiceConcierto, asistentes);
			listaActuaciones.add(actuacion);
		}
		return listaActuaciones;
	} 
	
	//Cálculo del importe de la actuación según tipo y asistentes
	public static Double calcularImporteActuacion(String tipo, Integer asistentes) throws Exception {
		
		TipoConcierto tipoConcierto = TipoConcierto.valueOf(tipo.toUpperCase().trim());
		
		Double importeActuacion = 0d;

		switch (tipoConcierto){
			case HEAVY:
				importeActuacion = BASE_HEAVY;
				if (asistentes > UMBRAL_HEAVY)
					importeActuacion += EXTRA_HEAVY * (asistentes - UMBRAL_HEAVY);
				break;
			case ROCK:
				importeActuacion = BASE_ROCK;
				if (asistentes > UMBRAL_ROCK)
					importeActuacion += EXTRA_ROCK * (asistentes - UMBRAL_ROCK);
				break;
			default:
				throw new Exception("Tipo de concierto desconocido.");
		}
		
		return importeActuacion;
	}
	
	//Calculo de créditos por actuación
	public static Integer calcularCreditos(String tipo, Integer asistentes) {
		Integer creditosActuacion = 0;
		creditosActuacion += Math.max(asistentes - 500, 0);
			if (tipo.equals("heavy"))
				creditosActuacion += asistentes / 5;

		return creditosActuacion;
	}
}

//Parameter Object
		record Actuacion(Integer indiceConcierto, Integer asistentes) {

		}