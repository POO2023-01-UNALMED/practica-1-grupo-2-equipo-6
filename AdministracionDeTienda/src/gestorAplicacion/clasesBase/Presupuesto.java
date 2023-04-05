package gestorAplicacion.clasesBase;

public class Presupuesto {
	private static double gananciaBruta;
	static public double disponibleNomina() {
		return gananciaBruta*0.36;
	}
	public double getGananciaBruta() {
		return gananciaBruta;
		
	}
	
	public void setGananciaBruta(double ganancia) {
		gananciaBruta=ganancia;
		
	}
	
	public String verificacionPago(double pago) {
		if(pago<=disponibleNomina()) {
			return "Se ha hecho el pago exitosamente";
		}else if(pago>disponibleNomina()) {
			return "El dinero disponible para el pago de la nomina no es suficiente para realizar el pago";
		}else {
			return "No se ha realizado el pago";
		}
	
	}
	

}
