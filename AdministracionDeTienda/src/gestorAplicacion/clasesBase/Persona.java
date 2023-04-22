package gestorAplicacion.clasesBase;


public abstract class Persona{

	private String nombre;
	private int calificacion;
	public Persona(String nombre, int calificacion) {
		this.nombre=nombre;
		this.calificacion=calificacion;
	}



	public int getCalificacion() {
		return calificacion;
	}



	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}



	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
/***
<<<<<<< HEAD
public class Persona {
	private String nombre;
	private int cedula;

=======
public class Persona{
	String nombre;
	int cedula;
>>>>>>> 727ef42c551917b2ff040155fbfb4978889ca4bd
	public Persona(String nombre, int cedula) {
		this.nombre = nombre;
		this.cedula = cedula;
	}
<<<<<<< HEAD
=======

>>>>>>> 727ef42c551917b2ff040155fbfb4978889ca4bd


}
***/
