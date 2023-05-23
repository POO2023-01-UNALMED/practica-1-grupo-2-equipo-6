package gestorAplicacion.clasesBase;

import java.io.Serializable;
import java.util.ArrayList;

public class Bodega implements Inventariar,Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> productosEnBodega = new ArrayList<Producto>();
	
	public Bodega(ArrayList<Producto>productosEnBodega) {
		this.productosEnBodega=productosEnBodega;
	}
	
	public int calcularCamisas() {
		return Inventariar.calcularCamisas(productosEnBodega);
	}
	
	public int calcularAbrigos() {
		return Inventariar.calcularAbrigos(productosEnBodega);
	}
	
	public int calcularPantalon() {
			return Inventariar.calcularPantalon(productosEnBodega);
		
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
		
		ArrayList<Producto> listaAuxiliar=new ArrayList<Producto>(productosEnBodega);
		ArrayList<Producto>productosBuscados=new ArrayList<Producto>();
		for(Producto p: productos) {
			for (Producto p1:listaAuxiliar) {
				if(p.equals(p1)){
					productosBuscados.add(p1);
					listaAuxiliar.remove(p1);
					break;
				}
			}
		}
		return productosBuscados;
		
	}


	public boolean evaluarDisponibilidad(ArrayList<Producto>productosRetirar) {
		int contadorAbrigo=Inventariar.calcularAbrigos(productosRetirar),contadorPantalon=Inventariar.calcularPantalon(productosRetirar),contadorCamisas=Inventariar.calcularCamisas(productosRetirar);
		if(contadorCamisas-this.calcularCamisas()>=minCamisas && contadorAbrigo-this.calcularAbrigos()>=minAbrigo && contadorPantalon-this.calcularPantalon()>=minPantalon) {
			
			this.retirarProductos(productosRetirar);
			return true;
			
		}
		else {
			return false;
		}
		
	
		
	}

	private void retirarProductos(ArrayList<Producto> productos) {	
		
		productosEnBodega.removeIf(p -> productos.indexOf(p)!=-1);
		
		
	}

}
