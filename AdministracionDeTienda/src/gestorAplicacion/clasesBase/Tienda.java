package gestorAplicacion.clasesBase;

import java.util.ArrayList;

public class Tienda {
	private double presupuestoCompras;
	private ArrayList<Object> proveedores = new ArrayList<Object>();
	private Nomina nomina;
	private Bodega bodega;

	public Tienda(int presupuesto, ArrayList<Object> proveedores, Nomina nomina, Bodega bodega){
		this.presupuestoCompras = presupuesto;
		this.proveedores = proveedores;
		this.bodega=bodega;
		this.nomina=nomina;
	}

  public void setBodega(Bodega bodega){this.bodega=bodega;}
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

}
