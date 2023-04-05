package gestorAplicacion.clasesBase;

public class Presupuesto {
	private static double gananciaBruta;
	public double pagoNomina() {
		return gananciaBruta*0.36;
	}
	public double getGananciaBruta() {
		return gananciaBruta;
		
	}
	
	public void setGananciaBruta(double ganancia) {
		gananciaBruta=ganancia;
		
	}
	

}
