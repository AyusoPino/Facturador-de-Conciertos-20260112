public class Facturador{

	//Repertorio de conciertos del grupo
	static String[][] repertorio = {
		 {"Tributo Robe", "heavy"}
		,{"Homaneje Queen", "rock"}
		,{"Magia Knoppler", "rock"}
		,{"Demonios Rojos", "heavy"}
	};
	static final Double BASE_HEAVY = 4000d;
	static final Double BASE_ROCK = 3000d;
	static final Integer UMBRAL_HEAVY = 500;
	static final Integer UMBRAL_ROCK = 1000;
	static final Integer EXTRA_HEAVY = 20;
	static final Integer EXTRA_ROCK = 30;
	static final Double IVA = 0.21;

	//Actuaciones realizadas indicando el concierto ofrecido y audiencias obtenidas.
	static Integer[][] actuaciones = {{0, 2000}, {2, 1200}, {0, 950}, {3, 1140}};

	static String cliente = "Ayuntamiento de Badajoz";

	public static void main(String[] args) throws Exception{
		Double totalFactura = 0d;
		Integer creditos = 0;

		System.out.println("FACTURA DE ACTUACIONES");
		System.out.println("Cliente: " + cliente);

		for(int i = 0; i < actuaciones.length; i++){
			Integer iConcierto = actuaciones[i][0];
			
			String tipoActuacion = repertorio[iConcierto][1];
			Integer asistentes = actuaciones[i][1];
			
			totalFactura += calcularImporteActuacion(tipoActuacion, asistentes);
			creditos += calcularCreditos(tipoActuacion, asistentes);
			
			System.out.println("\tConcierto: " + repertorio[iConcierto][0]);
			System.out.println("\t\tAsistentes: " + actuaciones[i][1]);
		}
		System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
		System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * 0.21);
		System.out.printf("TOTAL FACTURA: %.2f euros\n", totalFactura * 1.21);
		System.out.println("Créditos obtenidos: " + creditos);

	}
	
	// Método extraído para calcular el importe de una actuación
	private static Double calcularImporteActuacion(String tipo, Integer asistentes) throws Exception {
		Double importeActuacion = 0d;

		switch (tipo){
			case "heavy":
				importeActuacion = 4000d;
				if (asistentes > 500)
					importeActuacion += 20 * (asistentes - 500);
				break;
			case "rock":
				importeActuacion = 3000d;
				if (asistentes > 1000)
					importeActuacion += 30 * (asistentes - 1000);
				break;
			default:
				throw new Exception("Tipo de concierto desconocido.");
		}
		
		return importeActuacion;
	}
	
	// Método extraído para calcular los créditos de una actuación
	private static Integer calcularCreditos(String tipo, Integer asistentes) {
		Integer creditosActuacion = 0;
		creditosActuacion += Math.max(asistentes - 500, 0);
			if (tipo.equals("heavy"))
				creditosActuacion += asistentes / 5;

		return creditosActuacion;
	}
}