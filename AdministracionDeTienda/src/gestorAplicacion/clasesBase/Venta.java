package gestorAplicacion.clasesBase;

import java.io.Serializable;
import java.util.ArrayList;

import gestorAplicacion.clasesHerencia.Transportista;

public class Venta implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> productosVenta;
	private double total;
	private Object comprador;
	private OfertaPorDefecto productosOfertados;
	private double ganancias;
	private static double porcentajeBanco;
	private Transportista repartidor;

	public Venta(Object comprador) {

		this.comprador=comprador;

	}

	public Venta(Object comprador, double total, ArrayList<Producto> productosVenta, OfertaPorDefecto productosOfertados, Transportista repartidor) {
		this(comprador);
		this.total=total;
		this.productosVenta=productosVenta;
		this.setProductosOfertados(productosOfertados);
		this.repartidor=repartidor;
	}

	public static void asignarPorcentajeBanco(Venta v) {
		if(porcentajeBanco!=0){
			Tienda.getCuentaTienda().getEntidad().abonarCuentaAuxiliar(porcentajeBanco*v.ganancias);
			Tienda.getCuentaTienda().setDinero(Tienda.getCuentaTienda().getDinero()-porcentajeBanco*v.ganancias);
		}
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}


	public ArrayList<Producto> getProductosVenta() {
		return productosVenta;
	}

	public void setProductosVenta(ArrayList<Producto> productosVenta) {
		this.productosVenta = productosVenta;
	}

	public Object getComprador() {
		return comprador;
	}

	public void setComprador(Object comprador) {
		this.comprador = comprador;
	}

	public OfertaPorDefecto getProductosOfertados() {
		return productosOfertados;
	}

	public void setProductosOfertados(OfertaPorDefecto productosOfertados) {
		this.productosOfertados = productosOfertados;
	}

	public double getGanancias() {
		return ganancias;
	}

	public void setGanancias(double ganancias) {
		Tienda.getCuentaTienda().setDinero(ganancias+Tienda.getCuentaTienda().getDinero());
		this.ganancias = ganancias;
	}

	public Transportista getRepartidor() {
		return repartidor;
	}

	public void setRepartidor(Transportista repartidor) {
		this.repartidor = repartidor;
	}

	public static double getPorcentajeBanco() {
		return porcentajeBanco;
	}

	public static void setPorcentajeBanco(double porcentajeBanco) {
		Venta.porcentajeBanco = porcentajeBanco;
	}

	@Override
	public String toString() {
		String s="";
		try {
		for(Producto p: productosVenta) {
			s+=p.toString();
		}
		return String.format("\n\nProductos vendidos: %s\n\nTotal de la venta: $%g", s,total) ;
		}
		catch(NullPointerException e) {
			return "\n\n...No se encontraron productos\npara completar la venta\n\n";
		}
	}

}
