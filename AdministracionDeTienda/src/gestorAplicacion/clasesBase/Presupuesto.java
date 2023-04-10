package gestorAplicacion.clasesBase;

public class Presupuesto {
	static double gananciaBruta;
	public double getGananciaBruta() {
		return gananciaBruta;
		
	}
	
	public void setGananciaBruta(double ganancia) {
		gananciaBruta=ganancia;
		
	}
	
	public String verificacionPago(double pago) {
		double disponible=gananciaBruta*0.36;
		if(pago<=disponible) {
			return "Se ha hecho el pago exitosamente";
		}else if(pago>disponible) {
			return "El dinero disponible para el pago de la nomina no es suficiente para realizar el pago";
		}else {
			return "No se ha realizado el pago";
		}
	
	}
	

}
