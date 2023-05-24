package gestorAplicacion.clasesBase;

import java.util.ArrayList;

public interface Inventariar{

	int minCamisas=33;
	int minAbrigo=34;
	int minPantalon=33;

	
	public static int calcularCamisas(ArrayList<Producto> productos) {
		int numCamisas=0;
		for(Producto p:productos) {
			if(p.getNombre()=="CAMISA") {
				numCamisas++;
			}
		}
		return numCamisas;
	}
	
	public static int calcularAbrigos(ArrayList<Producto> productos) {
		int numAbrigos=0;
		for(Producto p:productos) {
			if(p.getNombre()=="ABRIGO") {
				numAbrigos++;
			}
		}
		return numAbrigos;
	}
	
	public static int calcularPantalon(ArrayList<Producto> productos) {
		int numPantalon=0;
		for(Producto p:productos) {
			if(p.getNombre()=="PANTALON") {
				
				numPantalon++;
				
			}
		}
		return numPantalon;
	}
	
	
}
