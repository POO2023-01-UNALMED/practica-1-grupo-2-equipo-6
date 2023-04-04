package clasesBase;

import java.util.ArrayList;

public class Bodega {
	final int capacidadMaxima;
	int numeroDeProductosEnBodega;
	final int[] stopCamiseta,stopPantalon,stopPantaloneta,stopAbrigo,stopBuzo;
	ArrayList<Object[]> productosEnBodega = new ArrayList<Object[]>();
	
	public Bodega(int capacidadMax, Object[] productosEnBodega, int[] camiseta,int[] pantalon, int[] pantaloneta, int[] abrigo, int[] buzo) {
		capacidadMaxima = capacidadMax;
		stopCamiseta = camiseta;
		stopPantalon = pantalon;
		stopPantaloneta = pantaloneta;
		stopAbrigo = abrigo;
		stopBuzo = buzo;
		this.productosEnBodega.add(productosEnBodega);		
	}
		
	public Bodega(int capacidadMax, ArrayList<Object[]> productosEnBodega, int[] camiseta,int[] pantalon, int[] pantaloneta, int[] abrigo, int[] buzo) {
		capacidadMaxima = capacidadMax;
		stopCamiseta = camiseta;
		stopPantalon = pantalon;
		stopPantaloneta = pantaloneta;
		stopAbrigo = abrigo;
		stopBuzo = buzo;
		this.productosEnBodega = productosEnBodega;
	}
	
	public void agregarProductos(Object[] producto) {
		this.productosEnBodega.add(producto);
	}
	
	public void agregarProductos(ArrayList<Object[]> productosEnBodega) {
		for (int i = 0; i < productosEnBodega.size(); i++) {
			this.productosEnBodega.add(productosEnBodega.get(i));
		}
	}
	
	public int calcularNumeroProductos(ArrayList<Object[]> productosEnBodega) {
		int numeroDeProductos = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			numeroDeProductos += productosEnBodega.get(i)[1].hashCode();
		}
		return numeroDeProductos;
	}
	
	public int calcularNumeroCamisetas(ArrayList<Object[]> productosEnBodega) {
		int numeroCamisetas = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(((Producto) productosEnBodega.get(i)[0]).codigo.substring(0, 1).equals("0")) {
				numeroCamisetas += productosEnBodega.get(i)[1].hashCode();
			}
		}
			return numeroCamisetas;
	}
	
	public int calcularNumeroPantalon(ArrayList<Object[]> productosEnBodega) {
		int numeroPantalones = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(((Producto) productosEnBodega.get(i)[0]).codigo.substring(0, 1).equals("1")) {
				numeroPantalones += productosEnBodega.get(i)[1].hashCode();
			}
		}
		return numeroPantalones;
	}
	
	public int calcularNumeroAbrigo(ArrayList<Object[]> productosEnBodega) {
		int numeroAbrigo = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(((Producto) productosEnBodega.get(i)[0]).codigo.substring(0, 1).equals("2")) {
				numeroAbrigo += productosEnBodega.get(i)[1].hashCode();
			}
		}
		return numeroAbrigo;
	}
			
	public int calcularNumeroBuzo(ArrayList<Object[]> productosEnBodega) {
		int numeroBuzo = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(((Producto) productosEnBodega.get(i)[0]).codigo.substring(0, 1).equals("3")) {
				numeroBuzo += productosEnBodega.get(i)[1].hashCode();
			}
		}
		return numeroBuzo;
	}
	
	public int calcularNumeroPantalonetas(ArrayList<Object[]> productosEnBodega) {
		int numeroPantaloneta = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(((Producto) productosEnBodega.get(i)[0]).codigo.substring(0, 1).equals("4")) {
				numeroPantaloneta += productosEnBodega.get(i)[1].hashCode();
			}
		}
		return numeroPantaloneta;
	}
	
	public String toString() {
		int numeroDeProductos = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			numeroDeProductos += productosEnBodega.get(i)[1].hashCode();
//			System.out.println(productosEnBodega.get(i)[1].hashCode());
		}
		
		return "capacidad maxima = " + capacidadMaxima + "\nproductos en bodega = " + numeroDeProductos;
	}
}
