package gestorAplicacion.clasesHerencia;

import gestorAplicacion.clasesBase.Persona;
import gestorAplicacion.clasesBase.Venta;

import java.util.ArrayList;

public class Empleado extends Persona {
	cargos cargo;
	private double sueldo;
	private double aumento;
	private ArrayList<Venta> ventas;

	public Empleado(String nombre, int cedula,cargos cargo) {
		super(nombre,cedula);
		this.cargo = cargo;
		asignacionSueldo();
	}

//Para vendedores
	public Empleado(String nombre, int cedula,cargos cargo, ArrayList<Venta> ventas) {
		this(nombre,cedula,cargo);
		this.ventas=ventas;
	}

	public enum cargos{
		ADMINISTRADOR,VENDEDOR,SUPERVISOR,ASISTENTE
	}

	public void asignacionSueldo() {
		switch(cargo) {
		case ADMINISTRADOR:
			setSueldo(2000000);
			break;
		case VENDEDOR:
			setSueldo(1200000);
			break;
		case SUPERVISOR:
			setSueldo(1800000);
			break;
		case ASISTENTE:
			setSueldo(1000000);
			break;
		}

	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	
	public void determinacionSueldo(Empleado empleado,ArrayList<Venta> ventas) {
		double sueldo=empleado.getSueldo();
		double comision=0;
		for(int e=0;e<ventas.size();e++) {
			if(ventas.get(e).getEmpleado()==empleado) {
				comision+=ventas.get(e).getCalificacion();
			}
		}
		//Aqui debe ir la relacion del sueldo con la comisiones
		empleado.setSueldo(sueldo);
		
	}

	public double getAumento() {
		return aumento;
	}

	public void setAumento(double aumento) {
		this.aumento = aumento;
	}


}
