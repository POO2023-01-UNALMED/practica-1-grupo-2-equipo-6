package gestorAplicacion.clasesHerencia;

import gestorAplicacion.clasesBase.Persona;

public class Empleado extends Persona {
	cargos cargo;
	double sueldo;
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
			sueldo=2000000;
			break;
		case VENDEDOR:
			sueldo=1200000;
			break;
		case SUPERVISOR:
			sueldo=1800000;
			break;
		case ASISTENTE:
			sueldo=1000000;
			break;
		}
		
	}
	

}
