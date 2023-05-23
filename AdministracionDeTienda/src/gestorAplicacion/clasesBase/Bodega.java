package gestorAplicacion.clasesBase;

import java.util.ArrayList;

public class Bodega implements java.io.Serializable{
	
	private final int stopBodega;
	private ArrayList<Producto> productosEnBodega = new ArrayList<Producto>();
	
	public Bodega( int max) {
		stopBodega = max;
	}
		
	public void agregarProductos(Producto producto) {
		this.productosEnBodega.add(producto);
	}
	
	public int calcularNumeroProductos() {		
		return productosEnBodega.size();
	}
	
	public int calcularNumeroCamisetas() {
		int numeroCamisetas = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if( productosEnBodega.get(i).getNombre().equals("CAMISA")) {
				numeroCamisetas ++;
			}
		}
		return numeroCamisetas;
	}
	
	public int calcularNumeroPantalon() {
		int numeroPantalones = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(productosEnBodega.get(i).getNombre().equals("PANTALON")) {
				numeroPantalones ++;
			}
		}
		return numeroPantalones;
	}
	
	public int calcularNumeroAbrigo() {
		int numeroAbrigo = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(productosEnBodega.get(i).getNombre().equals("ABRIGO")) {
				numeroAbrigo ++;
			}
		}
		return numeroAbrigo;
	}
			
	public void setProductosEnBodega(ArrayList<Producto> valor) {productosEnBodega = valor;}
	
	public int getStopBodega() {return stopBodega;}
	
	public ArrayList<Producto> getProductosEnBodega() {return productosEnBodega;}
	
	
	
	public String toString() {
	
		return "Camisetas = " + calcularNumeroCamisetas()+ "\nPantalones = " + calcularNumeroPantalon() + "\nAbrigos = " +calcularNumeroAbrigo();
	}

}
