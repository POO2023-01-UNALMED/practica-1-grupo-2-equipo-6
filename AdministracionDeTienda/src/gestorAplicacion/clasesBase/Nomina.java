package gestorAplicacion.clasesBase;

import java.util.ArrayList;

import gestorAplicacion.clasesBase.Nomina.meses;
import gestorAplicacion.clasesHerencia.Empleado;

public class Nomina {
	meses mes;
	private static ArrayList<meses> disponibles=new ArrayList<meses>();
	private static ArrayList<Empleado> empleados=new ArrayList<Empleado>();
	



	public Nomina(meses mes, ArrayList<Empleado>empleados) {
		this.mes=mes;
		//disponibles.remove(mes);
		Nomina.empleados=empleados;

	}

	public enum meses{
			ENERO(1),FEBRERO(2),MARZO(3),ABRIL(4),MAYO(5),JUNIO(6),JULIO(7),AGOSTO(8),SEPTIEMBRE(9),OCTUBRE(10),NOVIEMBRE(11),DICIEMBRE(12);

			private int num;
			meses(int num) {
				this.setNum(num);
			}
			public int getNum() {
				return num;
			}
			public void setNum(int num) {
				this.num = num;
			}

	}
	public ArrayList<Empleado> empleados() {
		//recorrer la lista de ventas y sacar los objetos que tengan el mes que se pide y añadir a esa lista, los empleados y las comisiones
		return empleados;
	}


	public static ArrayList<meses> getDisponibles() {
		return disponibles;
	}




	public static void setDisponibles(ArrayList<meses> disponibles) {
		Nomina.disponibles = disponibles;
	}




	public static ArrayList<Empleado> getEmpleados() {
		return empleados;
	}




	public static void setEmpleados(ArrayList<Empleado> empleados) {
		Nomina.empleados = empleados;
	}

	///
	Empleado EmpleadoM;
	Producto ProdutoM;
	Tienda tienda;
	public ArrayList<Venta> ventas;
	public Nomina(Tienda tienda,meses mes, Empleado empleadoM, Producto produtoM, ArrayList<Venta> ventas) {
		this.mes = mes;
		EmpleadoM = empleadoM;
		ProdutoM = produtoM;
		this.ventas = ventas;
		this.tienda=tienda;
	}
	
	public String realizacionPago() {
		return "Verifica que el valor total de los sueldos es menor que el presupuesto disponible";
		
	}
		
	public double aumento() {
		//Se le realiza un aumento al empleado del mes basado en cuanto vendio
		return 0;
	}
	
	public double precio() {
		//Se aumenta el precio del producto del mes
		return 0;
		
	}



}
