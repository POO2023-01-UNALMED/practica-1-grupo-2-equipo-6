package gestorAplicacion.clasesHerencia;

import java.util.ArrayList;

import gestorAplicacion.clasesBase.*;

public class Proveedor extends Persona{
	
	private float descuento;  
	private Bodega bodega;
	private int calle;
	private float costoCamiseta, costoPantalon, costoAbrigo;
	private float calificacion;
	
	public Proveedor(String nombre, int calificar, CuentaBancaria cuenta, Bodega bodega, float camiseta, float pantalon, float abrigo) {
		super(nombre,calificar,cuenta);
		this.bodega = bodega;
		costoCamiseta = camiseta;
		costoPantalon = pantalon;
		costoAbrigo = abrigo;
	}
	
	public void verificarDisponibilidad(ArrayList<Producto> pedido) {
		
		ArrayList<Producto> productosDisponibles = new ArrayList<Producto>();
		float descuentoCamiseta = 0;
		float descuentoPantalon = 0;
		float descuentoAbrigo = 0;
		int nCamisetas = 0;
		int nPantalones = 0;
		int nAbrigos = 0;
		
		for (int i=0; i< pedido.size();i++) {
			int aleatorio = (int)(Math.random()*5+1);
			
			if (aleatorio != 3) {
				if (pedido.get(i).getNombre().equals("CAMISA")) {
					nCamisetas++;
//					pedido.get(i).setCosto(costoCamiseta);
					if (aleatorio == 4) {descuentoCamiseta = 0.1f;}
					
				}
				else if(pedido.get(i).getNombre().equals("PANTALON")) {
					nPantalones++;
//					pedido.get(i).setCosto(costoPantalon);
					if (aleatorio == 4) {descuentoPantalon = 0.2f;}
				}
				else {
//					pedido.get(i).setCosto(costoAbrigo);
					nAbrigos++;
					if (aleatorio == 4) {descuentoCamiseta = 0.3f;}
				}
				
				productosDisponibles.add(pedido.get(i));
				}
		}
		
		for (int i=0; i< productosDisponibles.size();i++) {
			if (productosDisponibles.get(i).getNombre().equals("CAMISA")) {
				productosDisponibles.get(i).setCosto(costoCamiseta-(descuentoCamiseta/nCamisetas));
				setCostoCamiseta(costoCamiseta-(descuentoCamiseta/nCamisetas));
			}
			else if(productosDisponibles.get(i).getNombre().equals("PANTALON")) {
				productosDisponibles.get(i).setCosto(costoPantalon-(descuentoPantalon/nPantalones));
				setCostoPantalon(costoPantalon-(descuentoPantalon/nPantalones));
			}
			else {
				productosDisponibles.get(i).setCosto(costoAbrigo-(descuentoAbrigo/nAbrigos));
				setCostoAbrigo(costoAbrigo-(descuentoAbrigo/nAbrigos));
			}
			this.descuento = descuentoCamiseta + descuentoPantalon + descuentoAbrigo;
		}
		
		bodega.setProductosEnBodega(productosDisponibles);
	}
	
	public  int calificar() {
		return 0;
	}
	
	public void setCostoCamiseta(float costo) { costoCamiseta = costo;}
	
	public void setCostoPantalon(float costo) { costoPantalon = costo;}
	
	public void setCostoAbrigo(float costo) { costoAbrigo = costo;}
	
	public float getCostoCamiseta() { return costoCamiseta;}
	
	public float getCostoPantalon() { return costoPantalon;}
	
	public float getCostoAbrigo() { return costoAbrigo;}
	
	public float getDescuento() {return descuento;}
	
	public Bodega getBodega() {return bodega;}
	
	public int getCalle() {return calle;}
	
	public float costoProductos() {
		return bodega.calcularCamisas() * getCostoCamiseta()+ bodega.calcularPantalon()*getCostoPantalon()+ bodega.calcularAbrigos()*costoAbrigo;	
	}
	
	
	public String toString() {
		return "camisetas: "+bodega.calcularCamisas()+ " X " +getCostoCamiseta() + "\nPantaloes: " + bodega.calcularPantalon() + " X " + getCostoPantalon() + "\nAbrigos: " + bodega.calcularAbrigos() + " X " + getCostoAbrigo();
		
	}

}
