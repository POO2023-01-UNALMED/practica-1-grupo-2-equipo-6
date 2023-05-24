package gestorAplicacion.clasesHerencia;


import java.util.ArrayList;

import AdministracionDeTienda.src.gestorAplicacion.clasesHerencia.Cliente.Ciudades;
import AdministracionDeTienda.src.gestorAplicacion.clasesHerencia.Cliente.TipoEnvio;
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
	
	//Metodo envio
	public  Transferencia envioNacional(Cliente cliente, ArrayList<Intervenido> intervenidos, TipoEnvio tipo) {
			Ciudades ciudad=cliente.getCiudad();
			double precio;
			switch(ciudad) {
			case BARRANQUILLA: precio=2800;
				break;
			case BOGOTA: precio=2300;
				break;
			case BUCARAMANGA: precio=2900;
				break;
			case CALI: precio=2400;
				break;
			case CARTAGENA: precio=2700;
				break;
			case CUCUTA: precio=2300;
				break;
			case MEDELLIN: precio=1500;
				break;
			case PEREIRA: precio=2300;
				break;
			default: precio=2000;
				break;
			
			}
			int tamañoPaquete=intervenidos.size();
			precio+=tamañoPaquete*1000;
			switch(tipo){
			case PRIORITARIO: precio+=15000;
			break;
			case NORMAL:;
			break;
			case LIBRE: precio-=15000;
			break;	
			}
			precio-=precio*cliente.getDescuento();
			if(precio<0) {
				precio=0;
			}
			Transferencia transferencia=new Transferencia(this.getCuenta(),precio);
			return transferencia;
			
			
		}

}
