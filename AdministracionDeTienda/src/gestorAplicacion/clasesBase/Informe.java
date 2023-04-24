package gestorAplicacion.clasesBase;
public class Informe {

	private static ArrayList<Informe> informes;
	private Object comprador;
	private double ganancia;

	//Informe de ventas a socios
	public Informe(Venta venta,Compra compraConfirmada, Socio socio,double ganancia) {
		this.comprador=socio;
		this.ganancia=ganancia;
		informes.add(this);
	}

	public static Informe generarInformeVentas(Venta venta,Compra compraConfirmada, Socio socio) {

		double ganancia=venta.getTotal()-GestionHumana.pagar(compraConfirmada, socio);
		return new Informe(venta,compraConfirmada,socio,ganancia);
	}

	public static ArrayList<Informe> getInformes() {
		return informes;
	}

	public static void setInformes(ArrayList<Informe> informes) {
		Informe.informes = informes;
	}

	public Object getComprador() {
		return comprador;
	}

	public void setComprador(Object comprador) {
		this.comprador = comprador;
	}

	public double getGanancia() {
		return ganancia;
	}

	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}





}
