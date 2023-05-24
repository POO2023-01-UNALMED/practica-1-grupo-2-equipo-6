package gestorAplicacion.clasesHerencia;


import java.util.ArrayList;
import java.util.Collections;

import gestorAplicacion.clasesHerencia.Cliente.Ciudades;
import gestorAplicacion.clasesHerencia.Cliente.TipoEnvio;
import gestorAplicacion.clasesBase.*;


public class Transportista extends Persona {
	private float precioBase, precioDistancia, precioCarga, precioTotal;

	public Transportista(String nombre, float precioB, float precioD, float precioC,int calificacion, CuentaBancaria cuenta ) {
		super(nombre, calificacion,cuenta);
		precioBase = precioB;
		precioDistancia = precioD;
		precioCarga = precioC;
	}








		public Transportista(String string, int i) {
		super(string,i);
	}








		public static Transportista mejorTransportista(ArrayList<Transportista> transportistasPorDefecto) {
			Collections.sort(transportistasPorDefecto);
			 return transportistasPorDefecto.get(0);
		}

		public Venta entregaEspecial(OfertaPorDefecto ofertaSugerida, Socio s, ArrayList<Tienda>tiendas) {

			Venta ventaConfirmada=s.registrarVenta(ofertaSugerida);

			Bodega bodegaEscogida = null;


			for (Tienda t: tiendas) {
				if(t.getBodega().evaluarDisponibilidad(ventaConfirmada.getProductosVenta())) {

					bodegaEscogida=t.getBodega();
					break;
				}
			}

			if(bodegaEscogida==null) {
				return null;
			}

			ventaConfirmada.setRepartidor(this);
			return ventaConfirmada;

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

		public void calificar(ControlCalidad ct) {
			int calificacion = this.calificar();
			if (this == ct.getTransportista()) {
				ArrayList<Producto> productosExtraviados = ct.getProductosExtraviados();
				ArrayList<Producto> productosAReponerT = ct.getProductosAReponerT();
				if (productosAReponerT == null) {
					productosAReponerT = new ArrayList<>();
				}

				if ((productosExtraviados.isEmpty() || productosExtraviados == null) && productosAReponerT.isEmpty()) {
					this.setCalificacion(calificacion);
					return;
				}

				int totalProductos = 0;
				int productosReemplazados = 0;

				for (Producto p: productosExtraviados) {
					totalProductos++;
					if (productosAReponerT.contains(p)) {
						productosReemplazados++;
					}
				}

				if (productosReemplazados == totalProductos) {
					this.setCalificacion(calificacion);
					return;
				}

				double porcentajeReemplazo = ((double) productosReemplazados) / totalProductos;

				if (porcentajeReemplazo >= 0.8) {
					this.setCalificacion(calificacion - 1);
				} else if (porcentajeReemplazo >= 0.6) {
					this.setCalificacion(calificacion - 2);
				} else if (porcentajeReemplazo> 0) {
					this.setCalificacion(calificacion - 3);
				} else {
					this.setCalificacion(calificacion - 4);
				}

			}
		}








		public float getPrecioBase() {
			return precioBase;
		}








		public void setPrecioBase(float precioBase) {
			this.precioBase = precioBase;
		}








		public float getPrecioDistancia() {
			return precioDistancia;
		}








		public void setPrecioDistancia(float precioDistancia) {
			this.precioDistancia = precioDistancia;
		}








		public float getPrecioCarga() {
			return precioCarga;
		}








		public void setPrecioCarga(float precioCarga) {
			this.precioCarga = precioCarga;
		}
		

}
