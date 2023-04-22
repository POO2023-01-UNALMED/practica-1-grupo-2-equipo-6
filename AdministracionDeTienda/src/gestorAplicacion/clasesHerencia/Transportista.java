package gestorAplicacion.clasesHerencia;

import gestorAplicacion.clasesBase.Persona;
import java.util.ArrayList;



public class Transportista extends Persona{


	private static ArrayList<Transportista> transportistas;

	public Transportista(String nombre, int calificacion) {
		super(nombre, calificacion);
		Transportista.transportistas.add(this);
	}

	public static Transportista mejorTransportista() {
		if(transportistas.size()!=0) {
		Transportista transportistaDestacado=transportistas.get(0);
		int max=transportistaDestacado.getCalificacion();

		 for(Transportista t:transportistas) {
			 if(t.getCalificacion()>max) {
				 transportistaDestacado=t;
			 }
		 }
		 return transportistaDestacado;
		}
		return null;

	}

	public void entregaEspecial(Socio s, ArrayList<Producto> productosVenta) {


	}

}
