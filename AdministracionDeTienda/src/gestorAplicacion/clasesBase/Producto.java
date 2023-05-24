package gestorAplicacion.clasesBase;

import java.io.Serializable;
import java.util.ArrayList;

import gestorAplicacion.clasesBase.Bodega.SETS;


public class Producto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private double precio;
	private double costo;
	private Tipo tipo;

	
	
	//private double costo;


	
	
	public Producto(Tipo tipo) {
		this.nombre=tipo.toString();
		this.precio=tipo.getPrecioEstandar();
		//Constructor para socio
	}
	
	public Producto(Tipo tipo, double precio, double costo) {
		
		this(tipo);
		this.precio=precio;
		this.costo = costo;
		this.tipo=tipo;
		
		
	}

	

	public Producto(Tipo tipo, double costo) {
		this.tipo=tipo;
		this.costo=costo;
		//Constructor para intervenido
	}

	public boolean equals(Producto p) {
		
		if(p.getNombre()==this.nombre) {

			return true;
		}
		else {
			return false;
		}
		
	}
	
	public void descontar( float dcto) {
		if(dcto<0.7 || dcto >=1) {
			this.setPrecio(getPrecio()*0.9);
		}
		else {
		this.setPrecio(getPrecio()*dcto);
		}
	}
	
	
	
	public double getPrecio() {
		return precio;
	}




	public void setPrecio(double precio) {
		this.precio = precio;
	}




	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
		
	}
	
	public void setCosto(double valor) {costo=valor;}
	
	public double getCosto() {return costo;}


	public enum Tipo{
		
		CAMISA(25000),PANTALON(30000),ABRIGO(27000);
		
		private double precioEstandar;
		
		private Tipo(double precioEstandar) {
			this.setPrecioEstandar(precioEstandar);
		}

		public double getPrecioEstandar() {
			return precioEstandar;
		}

		public void setPrecioEstandar(double precioEstandar) {
			this.precioEstandar = precioEstandar;
		}
		
		
		
	}

	@Override
	public String toString() {
		return "\nTipo: "+nombre+"\nPrecio: "+precio;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	//Metodos ENVIO
		public static ArrayList<Producto> seleccionarProductos(SETS set,int cantidad) {
			ArrayList<Producto> productos=new ArrayList<Producto>();
			switch(set) {
			case ONLY: 
				for(int i=0;i<cantidad;i++) {
					productos.add(new Producto(Tipo.CAMISA));
				
			}
			break;
			case TU: 
				for(int i=0;i<cantidad;i++) {
					productos.add(new Producto(Tipo.CAMISA));
					productos.add(new Producto(Tipo.PANTALON));
				
			}
			break;
			case COMPLETO: 
				for(int i=0;i<cantidad;i++) {
					productos.add(new Producto(Tipo.CAMISA));
					productos.add(new Producto(Tipo.PANTALON));
					productos.add(new Producto(Tipo.ABRIGO));
					
				
			}
			break;
			
			}
			return productos;
		}
	    
		public static ArrayList<Producto> clasificar(ArrayList<Producto> productos, Tipo tipo){
			ArrayList<Producto> clasificados=new ArrayList<Producto>();
			for(int i=0;i<productos.size();i++) {
				Tipo tipado=productos.get(i).getTipo();
				if(tipado==tipo) {
					clasificados.add(productos.get(i));
				}
			}
			return clasificados;
		}


		
	
	
}
