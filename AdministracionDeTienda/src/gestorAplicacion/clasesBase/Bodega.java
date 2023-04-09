package gestorAplicacion.clasesBase;

import java.util.ArrayList;

public class Bodega {
	private final int capacidadMaxima;
	private int numeroDeProductosEnBodega;
	private final int[] stopCamiseta,stopPantalon,stopPantaloneta,stopAbrigo,stopBuzo;
	private ArrayList<ArrayList<Object>> productosEnBodega = new ArrayList<ArrayList<Object>>();

	public Bodega(int capacidadMax, ArrayList<Object> productosEnBodega, int[] camiseta,int[] pantalon, int[] pantaloneta, int[] abrigo, int[] buzo) {
		capacidadMaxima = capacidadMax;
		stopCamiseta = camiseta;
		stopPantalon = pantalon;
		stopPantaloneta = pantaloneta;
		stopAbrigo = abrigo;
		stopBuzo = buzo;
		this.productosEnBodega.add(productosEnBodega);
	}

	public void agregarProductos(ArrayList<Object> producto) {
		this.productosEnBodega.add(producto);
	}

	public int calcularNumeroProductos(ArrayList<ArrayList<Object>> productosEnBodega) {
		int numeroDeProductos = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			numeroDeProductos += productosEnBodega.get(i).get(1).hashCode();
		}
		return numeroDeProductos;
	}

	public int calcularNumeroCamisetas(ArrayList<ArrayList<Object>> productosEnBodega) {
		int numeroCamisetas = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
//			System.out.println(productosEnBodega.get(i)[1].hashCode());
			if(((Producto) productosEnBodega.get(i).get(0)).getCodigo().substring(0, 1).equals("0")) {
				numeroCamisetas += productosEnBodega.get(i).get(1).hashCode();
			}
		}
			return numeroCamisetas;
	}

	public int calcularNumeroPantalon(ArrayList<ArrayList<Object>> productosEnBodega) {
		int numeroPantalones = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(((Producto) productosEnBodega.get(i).get(0)).getCodigo().substring(0, 1).equals("1")) {
				numeroPantalones += productosEnBodega.get(i).get(1).hashCode();
			}
		}
		return numeroPantalones;
	}

	public int calcularNumeroAbrigo(ArrayList<ArrayList<Object>> productosEnBodega) {
		int numeroAbrigo = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(((Producto) productosEnBodega.get(i).get(0)).getCodigo().substring(0, 1).equals("2")) {
				numeroAbrigo += productosEnBodega.get(i).get(1).hashCode();
			}
		}
		return numeroAbrigo;
	}

	public int calcularNumeroBuzo(ArrayList<ArrayList<Object>> productosEnBodega) {
		int numeroBuzo = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(((Producto) productosEnBodega.get(i).get(0)).getCodigo().substring(0, 1).equals("3")) {
				numeroBuzo += productosEnBodega.get(i).get(1).hashCode();
			}
		}
		return numeroBuzo;
	}

	public int calcularNumeroPantalonetas(ArrayList<ArrayList<Object>> productosEnBodega) {
		int numeroPantaloneta = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(((Producto) productosEnBodega.get(i).get(0)).getCodigo().substring(0, 1).equals("4")) {
				numeroPantaloneta += productosEnBodega.get(i).get(1).hashCode();
			}
		}
		return numeroPantaloneta;
	}

	public void setNumeroDeProductosEnBodega(int valor) {numeroDeProductosEnBodega = valor;}
	public void setProductosEnBodega(ArrayList<ArrayList<Object>> valor) {productosEnBodega = valor;}

	public int getCapacidadMaxima() {return capacidadMaxima;}
	public int getNumeroDeProductosEnBodega() {return numeroDeProductosEnBodega;}
	public int[] getStopCamiseta() {return stopCamiseta;}
	public int[] getStopPantalon() {return stopPantalon;}
	public int[] getStopPantaloneta() {return stopPantaloneta;}
	public int[] getStopAbrigo() {return stopAbrigo;}
	public int[] getStopBuzo() {return stopBuzo;}
	public ArrayList<ArrayList<Object>> getProductosEnBodega() {return productosEnBodega;}


	public String toString() {
		int numeroDeProductos = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			numeroDeProductos += productosEnBodega.get(i).get(1).hashCode();
		}

		return "capacidad maxima = " + capacidadMaxima + "\nproductos en bodega = " + numeroDeProductos;
	}
	public ArrayList<Producto> disponibilidadProductos(String tipo) {
		ArrayList<Producto> productosTipo=new ArrayList<Producto>();
		for(ArrayList<Object> i: productosEnBodega) {
			if (((Producto)i.get(0)).getTipo()==tipo && productosTipo.indexOf(tipo)==-1) {
				 productosTipo.add((Producto) i.get(0));
			}
		}
		return productosTipo;
	}
}
