package gestorAplicacion.clasesBase;

import java.util.ArrayList;
import java.io.*;

public class Bodega implements java.io.Serializable{


		private ArrayList<Producto> productosEnBodega = new ArrayList<Producto>();
		private final int minCamisas, minAbrigo, minPantalon;

		public Bodega(ArrayList<Producto>productosEnBodega, int minCamisas,int minPantalon, int minAbrigo) {

			this.productosEnBodega=productosEnBodega;
			this.minAbrigo=minAbrigo;
			this.minCamisas=minCamisas;
			this.minPantalon=minPantalon;
		}


		public int calcularCamisas() {
			int numCamisas=0;
			for(Producto p:productosEnBodega) {
				if(p.getTipo().toString()=="CAMISA") {
					numCamisas++;
				}
			}
			return numCamisas;
		}

		public int calcularAbrigos() {
			int numAbrigos=0;
			for(Producto p:productosEnBodega) {
				if(p.getTipo().toString()=="ABRIGO") {
					numAbrigos++;
				}
			}
			return numAbrigos;
		}

		public int calcularPantalon() {
			int numPantalon=0;
			for(Producto p:productosEnBodega) {
				if(p.getTipo().toString()=="PANTALON") {

					numPantalon++;

				}
			}
			return numPantalon;
		}




	public ArrayList<Producto> getProductosEnBodega() {
		return productosEnBodega;
	}


	public void setProductosEnBodega(ArrayList<Producto> productosEnBodega) {
		this.productosEnBodega = productosEnBodega;
	}


	public int getMinCamisas() {
		return minCamisas;
	}


	public int getMinAbrigo() {
		return minAbrigo;
	}


	public int getMinPantalon() {
		return minPantalon;
	}

		public ArrayList<Producto> buscarProducto(ArrayList<Producto>productos) {

			ArrayList<Producto>productosBuscados=new ArrayList<Producto>();
			for(Producto p: productos) {
				if (productosEnBodega.indexOf(p)!=-1) {
					productosBuscados.add(p);
				}
			}
			return productosBuscados;

		}



		public ArrayList<Producto> retirarProductos(ArrayList<Producto> productos) {

			ArrayList<Producto>productosRetirados = null;
			int contadorAbrigo=0,contadorPantalon=0,contadorCamisas=0;
			if(contadorCamisas-this.calcularCamisas()>=minCamisas && contadorAbrigo-this.calcularAbrigos()>=minAbrigo && contadorPantalon-this.calcularPantalon()>=minPantalon) {
				productosRetirados=productos;
				productosEnBodega.removeIf(p -> productos.indexOf(p)!=-1);

			}
			return productosRetirados;


		}

	}



/***
	private final int stopCamiseta, stopPantalon, stopAbrigo;
	private ArrayList<ArrayList<Object>> productosEnBodega = new ArrayList<ArrayList<Object>>();


	public Bodega( int camiseta,int pantalon, int abrigo) {
		stopCamiseta = camiseta;
		stopPantalon = pantalon;
		stopAbrigo = abrigo;
	}

	public void agregarProductos(ArrayList<Object> producto) {
		this.productosEnBodega.add(producto);
	}

	public int calcularNumeroProductos() {
		int numeroDeProductos = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			numeroDeProductos += productosEnBodega.get(i).get(1).hashCode();
		}
		return numeroDeProductos;
	}

	public int calcularNumeroCamisetas() {
		int numeroCamisetas = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(((Producto) productosEnBodega.get(i).get(0)).getCodigo().substring(0, 1).equals("0")) {
				numeroCamisetas += productosEnBodega.get(i).get(1).hashCode();
			}
		}
		return numeroCamisetas;
	}

	public int calcularNumeroPantalon() {
		int numeroPantalones = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(((Producto) productosEnBodega.get(i).get(0)).getCodigo().substring(0, 1).equals("1")) {
				numeroPantalones += productosEnBodega.get(i).get(1).hashCode();
			}
		}
		return numeroPantalones;
	}

	public int calcularNumeroAbrigo() {
		int numeroAbrigo = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			if(((Producto) productosEnBodega.get(i).get(0)).getCodigo().substring(0, 1).equals("2")) {
				numeroAbrigo += productosEnBodega.get(i).get(1).hashCode();
			}
		}
		return numeroAbrigo;
	}


	public void setProductosEnBodega(ArrayList<ArrayList<Object>> valor) {productosEnBodega = valor;}

	public int getStopCamiseta() {return stopCamiseta;}

	public int getStopPantalon() {return stopPantalon;}

	public int getStopAbrigo() {return stopAbrigo;}

	public ArrayList<ArrayList<Object>> getProductosEnBodega() {return productosEnBodega;}

	public String toString() {
		int numeroDeProductos = 0;
		for (int i = 0; i < productosEnBodega.size(); i++) {
			numeroDeProductos += productosEnBodega.get(i).get(1).hashCode();
		}

		return "productos en bodega = " + numeroDeProductos;
	}

	public ArrayList<Object> disponibilidadProductos(String tipo) {
		ArrayList<Object> productosTipo=new ArrayList<Object>();
		for(ArrayList<Object> i: productosEnBodega) {
			int cantidad=1;
			if (((Producto)i.get(0)).getTipo()==tipo ) {
				if (productosTipo.indexOf((Producto)i.get(0))!=-1) {
					cantidad+=1;
				}
				Object[] lista1={(Producto)i.get(0), cantidad};
				productosTipo.add(lista1);
			}
		}
		return productosTipo;
	}
	public ArrayList<Producto> promocionar(ArrayList<Producto> producto, double porcentaje ){
		ArrayList<Producto> productosPromocion=new ArrayList<Producto>();
		for(Producto p: producto) {
			if(p.isEnPromocion()==false) {
				productosPromocion.add(p);
				p.setEnPromocion(true);
				p.setPrecio(p.getPrecio()*(1-(porcentaje*100)));
			}
		}
		return productosPromocion;

	}
	***/


}
