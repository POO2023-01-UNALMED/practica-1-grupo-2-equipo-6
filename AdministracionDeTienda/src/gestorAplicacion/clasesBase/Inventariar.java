package gestorAplicacion.clasesBase;

import java.util.ArrayList;

public interface Inventariar{

	int minCamisas=33;
	int minAbrigo=34;
	int minPantalon=33;

	
	public static int calcularCamisas(ArrayList<Producto> productos) {
		int numCamisas=0;
		for(Producto p:productos) {
			if(p.getNombre()=="Camisa") {
				numCamisas++;
			}
		}
		return numCamisas;
	}
	
	public static int calcularAbrigos(ArrayList<Producto> productos) {
		int numAbrigos=0;
		for(Producto p:productos) {
			if(p.getNombre()=="Abrigo") {
				numAbrigos++;
			}
		}
		return numAbrigos;
	}
	
	public static int calcularPantalon(ArrayList<Producto> productos) {
		int numPantalon=0;
		for(Producto p:productos) {
			if(p.getNombre()=="Pantalon") {
				
				numPantalon++;
				
			}
		}
		return numPantalon;
	}
	
	
}
