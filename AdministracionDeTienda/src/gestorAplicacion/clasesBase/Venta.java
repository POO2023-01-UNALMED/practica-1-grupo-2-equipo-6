package gestorAplicacion.clasesBase;

import java.util.ArrayList;

import gestorAplicacion.clasesBase.Nomina.meses;
import gestorAplicacion.clasesHerencia.Empleado;

public class Venta {
	Producto[] productos;
	Empleado empleado;
	meses mes;
	double total;
	public static ArrayList<Venta> ventas=new ArrayList<Venta>();
	public Venta(Producto[] productos, Empleado empleado, double total,meses mes) {
		this.productos = productos;
		this.empleado = empleado;
		this.total = total;
		this.mes=mes;
		Presupuesto.gananciaBruta+=total;
		ventas.add(this);
	}
	
	

}
