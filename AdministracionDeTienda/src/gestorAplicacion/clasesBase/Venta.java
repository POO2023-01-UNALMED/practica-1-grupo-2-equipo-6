package gestorAplicacion.clasesBase;

import java.util.ArrayList;

import gestorAplicacion.clasesBase.Nomina.meses;
import gestorAplicacion.clasesHerencia.Empleado;

public class Venta {
	private Producto[] productos;
	private Empleado empleado;
	private meses mes;
	double total;
	private Tienda tienda;
	private double calificacion;

	public void setTienda(Tienda tienda) {
		this.tienda=tienda;
	}

	public Tienda getTienda(){
		return tienda;
	}
	public Producto[] getProductos() {
		return productos;
	}
	public void setProductos(Producto[] productos) {
		this.productos = productos;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public meses getMes() {
		return mes;
	}
	public void setMes(meses mes) {
		this.mes = mes;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	//public static ArrayList<Venta> ventas=new ArrayList<Venta>();
	public Venta(Producto[] productos, Empleado empleado,meses mes) {
		this.productos = productos;
		this.empleado = empleado;
		this.calcularTotal();
		//Calcular el total recorriendo el arreglo de productos
		this.mes=mes;
		tienda.setPresupuestoCompras(tienda.getPresupuestoCompras()+total);
		//Presupuesto.gananciaBruta+=total;
		empleado.ventas.add(this);
	}
	public double getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	public void calcularTotal() {
		double total=0;
		for(Producto p:productos) {
			total+=p.getCosto();
		}
		setTotal(total);
	}

	public static ArrayList<Venta> informarVentas(Empleado empleado) {
		ArrayList<Venta> Ventas=new ArrayList<Venta>();

		if(empleado.getVentas().size()!=0) {
				for (Venta venta: empleado.getVentas()) {
					Ventas.add(venta);
				}
		}
			return Ventas;
	}

	public static ArrayList<Venta> informarVentas() {

		ArrayList<Venta> Ventas=new ArrayList<Venta>();
		for (Empleado empleado: Nomina.empleados) {
				Ventas.addAll(informarVentas(empleado));
		}
		return Ventas;

	}

	


}
