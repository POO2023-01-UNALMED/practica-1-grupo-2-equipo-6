package gestorAplicacion.clasesHerencia;


import gestorAplicacion.clasesBase.*;

public class Transportista extends Persona{
	private float precioBase, precioDistancia, precioCarga, precioTotal;
	
	public Transportista(String nombre, float precioB, float precioD, float precioC,int calificacion, CuentaBancaria cuenta ) {
		super(nombre, calificacion,cuenta);
		precioBase = precioB;
		precioDistancia = precioD;
		precioCarga = precioC;
	}
	
	public float calcularPrecioTotal(Proveedor provedor, Tienda tienda) {
		int costo = 0;
		costo += precioBase + Math.abs(provedor.getCalle() - tienda.getCalle())*precioDistancia + provedor.getBodega().calcularNumeroProductos()*precioCarga;
		
		return costo;
	}
	
	public float getPrecioTotal() {return precioTotal;}
	public void setPrecioTotal(float p) {precioTotal = p;}
	public  int calificar() {
		return 0;
	}
	
	public String toString() {
		return " nombre :"+ super.getNombre() + "\nprecio del domicilio : " + precioTotal;
	}

}
