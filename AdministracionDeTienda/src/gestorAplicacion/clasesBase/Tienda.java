package gestorAplicacion.clasesBase;

import java.util.ArrayList;

public class Tienda {
	private String nombre;
	private double presupuestoCompras;
	private ArrayList<Object> proveedores = new ArrayList<Object>();
	private Nomina nomina;
	private Bodega bodega;

	public Tienda(String nombre, int presupuesto, ArrayList<Object> proveedores, Nomina nomina, Bodega bodega){
		this.nombre=nombre;
		this.presupuestoCompras = presupuesto;
		this.proveedores = proveedores;
		this.bodega=bodega;
		this.nomina=nomina;
	}

	public void setNombre(String nombre){this.nombre=nombre;}
	public String getNombre(){return this.nombre;}
  public void setBodega(Bodega bodega){this.bodega=bodega;}
	public Bodega getBodega(){return this.bodega;}
	public Bodega getPresupuestoCompras() {return this.bodega;}


	public void setPresupuestoCompras(double dinero) { presupuestoCompras = dinero;}

 public double getPresupuestoCompras() {return presupuestoCompras;}

	public void setProveedores(ArrayList<Object> proveedores) {this.proveedores = proveedores;}

	public ArrayList<Object> getProveedores(){return proveedores;}

	public Nomina getNomina(){return nomina;}

	public setNomina(Nomina nomina){this.nomina=nomina;}

	public boolean generarOferta() {
		double totalVentas=Nomina.informarVentas().size();
		if(totalVentas/bodega.getProductosEnBodega().size()>0.5) {

			return true;
		}
		return false;
	}
	public ArrayList<String> tiposProductos() {
		ArrayList<String> tiposDisponibles=new ArrayList<String>();
		for(ArrayList<Object>i: bodega.getProductosEnBodega()) {
				tiposDisponibles.add(((Producto)i.get(0)).getTipo());
		}
		return tiposDisponibles;
	}

}
