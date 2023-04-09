package gestorAplicacion.clasesBase;

import java.util.ArrayList;

public class Tienda {
	private double presupuestoCompras;
	private ArrayList<Object> proveedores = new ArrayList<Object>();
	private Nomina nomina;

	public Tienda(int presupuesto, ArrayList<Object> proveedores){
		presupuestoCompras = presupuesto;
		this.proveedores = proveedores;
	}

	public void setPresupuestoCompra(double dinero) { presupuestoCompras = dinero;}

	public double getPresupuestoCompra() {return presupuestoCompras;}

	public void setProveedores(ArrayList<Object> proveedores) {this.proveedores = proveedores;}

	public ArrayList<Object> getProveedores(){return proveedores;}

	public Nomina getNomina(){return Nomina;}

	public setNomina(Nomina nomina){this.nomina=nomina;}

	public boolean generarOferta() {
		double totalVentas=0.0;
		for(Venta v:Nomina.informarVentas()) {

			totalVentas+=v.getTotal();
		}
		if(presupuestoCompras<=totalVentas) {
			return true;
		}
		return false;
	}

}
