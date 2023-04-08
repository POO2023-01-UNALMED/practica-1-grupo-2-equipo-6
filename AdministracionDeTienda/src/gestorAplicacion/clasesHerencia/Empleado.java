package gestorAplicacion.clasesHerencia;

import gestorAplicacion.clasesBase.Persona;

public class Empleado extends Persona {
	cargos cargo;
	private double sueldo;
	public Empleado(String nombre, int cedula,cargos cargo) {
		super(nombre,cedula);
		this.cargo = cargo;
		asignacionSueldo();
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
	

}
