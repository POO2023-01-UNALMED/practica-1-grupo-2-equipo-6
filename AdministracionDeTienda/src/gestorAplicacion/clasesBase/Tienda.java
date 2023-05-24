package gestorAplicacion.clasesBase;

public class Tienda implements java.io.Serializable {
	private double presupuestoCompras;
	private Bodega bodega;
	private int calle;
	private int id;
	
	public Tienda(int presupuesto, Bodega bodega){
		presupuestoCompras = presupuesto;
		this.bodega = bodega;
	}
	
	public void setPresupuestoCompra(double dinero) { presupuestoCompras = dinero;}
	
	public double getPresupuestoCompra() {return presupuestoCompras;}
	
	public void setBodega(Bodega bodega) {this.bodega = bodega;}
	
	public Bodega getBodega(){return this.bodega;}
	
	public int getCalle() {return calle;}
	
	public void setCalle(int c) {calle = c;}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
