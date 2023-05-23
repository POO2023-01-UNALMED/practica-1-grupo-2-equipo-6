package gestorAplicacion.clasesBase;

import java.io.Serializable;

public class Producto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private double precio;
	
	
	//private double costo;


	
	
	public Producto(Tipo tipo) {
		this.nombre=tipo.toString();
		this.precio=tipo.getPrecioEstandar();
		//Constructor para socio
	}
	
	public Producto(Tipo tipo, double precio) {
		
		this(tipo);
		this.precio=precio;
		
		
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



	enum Tipo{
		
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


		
	
	
}
