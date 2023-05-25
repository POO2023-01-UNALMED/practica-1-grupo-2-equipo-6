package gestorAplicacion.clasesBase;

import java.util.ArrayList;

public interface Inventariar{

	int minCamisas=1;
	int minAbrigo=1;
	int minPantalon=1;

	
	public static int calcularCamisas(ArrayList<Producto> productos) {
		int numCamisas=0;
		for(Producto p:productos) {
			if(p.getNombre().equals("CAMISA") ){
				numCamisas++;
			}
		}
		return numCamisas;
	}
	
	public static int calcularAbrigos(ArrayList<Producto> productos) {
		int numAbrigos=0;
		for(Producto p:productos) {
			if(p.getNombre().equals("ABRIGO") ){
				numAbrigos++;
			}
		}
		return numAbrigos;
	}
	
	public static int calcularPantalon(ArrayList<Producto> productos) {
		int numPantalon=0;
		for(Producto p:productos) {
			if(p.getNombre().equals("PANTALON")) {
				
				numPantalon++;
				
			}
		}
		return numPantalon;
	}
	
	
}
