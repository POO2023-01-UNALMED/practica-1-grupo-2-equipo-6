package gestorAplicacion.clasesBase;

import java.util.ArrayList;

import gestorAplicacion.clasesBase.Nomina.meses;
import gestorAplicacion.clasesHerencia.Empleado;

public class Venta {
	private Producto[] productos;
	private Empleado empleado;
	private meses mes;
	double total;
	private double calificacion;
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
	public static ArrayList<Venta> getVentas() {
		return ventas;
	}
	public static void setVentas(ArrayList<Venta> ventas) {
		Venta.ventas = ventas;
	}
	
	public static ArrayList<Venta> ventas=new ArrayList<Venta>();
	public Venta(Producto[] productos, Empleado empleado,meses mes) {
		this.productos = productos;
		this.empleado = empleado;
		//Calcular el total recorriendo el arreglo de productos
		this.mes=mes;
		Presupuesto.gananciaBruta+=total;
		ventas.add(this);
	}
	public double getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
	
	

}
