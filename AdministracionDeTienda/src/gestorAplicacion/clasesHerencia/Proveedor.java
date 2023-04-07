package gestorAplicacion.clasesHerencia;
import java.util.ArrayList;

import gestorAplicacion.clasesBase.Persona;
import gestorAplicacion.clasesBase.Producto;

public class Proveedor extends Persona {
	private int numeroVentas;  
	
	public Proveedor(String nombre, int cedula, int numero) {
		super(nombre,cedula);
		this.numeroVentas = numero;
	}
	
	public ArrayList<ArrayList<Object>> verificarDisponibilidad(ArrayList<ArrayList<Object>> lista) {
		
		for(int i=0; i< lista.size();i++) {
			int aleatorio = (int)(Math.random()*5+1);
			if (aleatorio == 3) {
				lista.get(i).set(2, false);
			}
			else {
				lista.get(i).set(2, true);
			}
		}
		
		return lista;
	}
	
	public void setCalificacion() { numeroVentas++;}
	
	public int getCalificacion() {return numeroVentas;}
	
	public String toString() {
		return "Numero de Ventas: "+ numeroVentas;
		
	}

}
