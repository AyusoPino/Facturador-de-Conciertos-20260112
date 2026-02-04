public class Facturador{
	
	public enum TipoConcierto {HEAVY, ROCK}

	//Repertorio de conciertos del grupo
	static String[][] conciertos = {
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
	static Integer[][] actuacionesRealizadas = {{0, 2000}, {2, 1200}, {0, 950}, {3, 1140}};

	static String cliente = "Ayuntamiento de Badajoz";

	public static void main(String[] args) throws Exception{
		Double totalFactura = 0d;
		Integer creditos = 0;

		System.out.println("FACTURA DE ACTUACIONES");
		System.out.println("Cliente: " + cliente);

		for(int i = 0; i < actuacionesRealizadas.length; i++){
			Integer indiceConcierto = actuacionesRealizadas[i][0];
			
			String tipoActuacion = conciertos[indiceConcierto][1];
			Integer asistentes = actuacionesRealizadas[i][1];
			
			totalFactura += calcularImporteActuacion(tipoActuacion, asistentes);
			creditos += calcularCreditos(tipoActuacion, asistentes);
			
			System.out.println("\tConcierto: " + conciertos[indiceConcierto][0]);
			System.out.println("\t\tAsistentes: " + actuacionesRealizadas[i][1]);
		}
		System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
		System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * IVA);
		System.out.printf("TOTAL FACTURA: %.2f euros\n", totalFactura * (1 + IVA));
		System.out.println("Créditos obtenidos: " + creditos);

	}

	private static Double calcularImporteActuacion(String tipo, Integer asistentes) throws Exception {
		
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
	
	private static Integer calcularCreditos(String tipo, Integer asistentes) {
		Integer creditosActuacion = 0;
		creditosActuacion += Math.max(asistentes - 500, 0);
			if (tipo.equals("heavy"))
				creditosActuacion += asistentes / 5;

		return creditosActuacion;
	}

}