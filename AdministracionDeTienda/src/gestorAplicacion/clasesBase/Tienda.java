package gestorAplicacion.clasesBase;

import java.util.ArrayList;

public class Tienda {
	private double presupuestoCompras;
	private ArrayList<Object> proveedores = new ArrayList<Object>();
	
	public Tienda(int presupuesto, ArrayList<Object> proveedores){
		presupuestoCompras = presupuesto;
		this.proveedores = proveedores;
	}
	
	public void setPresupuestoCompra(double dinero) { presupuestoCompras = dinero;}
	
	public double getPresupuestoCompra() {return presupuestoCompras;}
	
	public void setProveedores(ArrayList<Object> proveedores) {this.proveedores = proveedores;}
	
	public ArrayList<Object> getProveedores(){return proveedores;}
}
	

