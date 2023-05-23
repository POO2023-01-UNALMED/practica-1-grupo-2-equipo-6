package gestorAplicacion.clasesBase;
import java.sql.Struct;
import java.text.DecimalFormat;
import java.util.ArrayList;

import gestorAplicacion.clasesBase.Producto.Tipo;
import gestorAplicacion.clasesHerencia.Proveedor;
import gestorAplicacion.clasesHerencia.Transportista;
import baseDatos.*;
import java.util.Collections;
import java.util.Iterator;

import baseDatos.Deserializador;

public class Compra implements java.io.Serializable{
	
		static int codigo;	
		private double costo = 0;
		private Tienda tienda;
		private ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		private Proveedor proveedorSeleccionado;
		private Transportista transportistaSeleccionado;
		private ArrayList<Producto> pedido = new ArrayList<Producto>();
		private ArrayList<Transportista> transportistas = new ArrayList<Transportista>();
		
		public Compra( Tienda tienda) {
			
			this.tienda = tienda;
			Proveedor p1 = (Proveedor) new Deserializador("proveedor1").getObj();
			Proveedor p2 = (Proveedor) new Deserializador("proveedor2").getObj();
			Proveedor p3 = (Proveedor) new Deserializador("proveedor3").getObj();
			proveedores.add(p1);
			proveedores.add(p2);
			proveedores.add(p3);
			
			transportistas.add((Transportista) new Deserializador("transportista1").getObj());
			transportistas.add((Transportista) new Deserializador("transportista2").getObj());
			transportistas.add((Transportista) new Deserializador("transportista3").getObj());
			
		}
		
		public String hacerPedido(Tienda tienda) {
			
			int numCamisetas = tienda.getBodega().calcularNumeroCamisetas();
			int numPantalones = tienda.getBodega().calcularNumeroPantalon();
			int numAbrigo = tienda.getBodega().calcularNumeroAbrigo();
			int numeroDeProductos = numCamisetas + numPantalones + numAbrigo + 1;
			double presupuesto = tienda.getPresupuestoCompra();
			ArrayList<Producto> pedidoFinal = new ArrayList<Producto>();
			
			while (numeroDeProductos <= tienda.getBodega().getStopBodega()) {
				
				if (numCamisetas <= numPantalones && numCamisetas <= numAbrigo) {
			
					Producto producto = new Producto(Tipo.CAMISA,2,1);
					numeroDeProductos += 1;
					numCamisetas += 1;
					this.pedido.add(producto);
				}
				
				else if (numPantalones <= numCamisetas && numPantalones <= numAbrigo) {
					
					Producto producto = new Producto(Tipo.PANTALON,4,2);
					numeroDeProductos += 1;
					numPantalones += 1;
					this.pedido.add(producto);
				}
				
				else {
					
					Producto producto = new Producto(Tipo.ABRIGO,6,3);
					numeroDeProductos += 1;
					numAbrigo += 1;
					this.pedido.add(producto);	
				}
			}
			
			for (int i = 0; i < pedido.size(); i++) {
				if(presupuesto -pedido.get(i).getCosto()<=0) {}
				else {
					presupuesto= presupuesto-pedido.get(i).getCosto();
					pedidoFinal.add(pedido.get(i));
				}
			}
				
			
			int camisetas = 0;
			int pantalones = 0;
			int abrigos = 0;
			for (int i = 0; i < pedidoFinal.size(); i++) {
				
				if (pedidoFinal.size()<6) {
					return "Solo se pueden crear pedidos apartir de la media docena";
				}
				
				else if( pedidoFinal.get(i).getNombre().equals("CAMISA")) {
					camisetas++;
				}
				else if (pedidoFinal.get(i).getNombre().equals("PANTALON")) {
					pantalones++;
				}
				else {abrigos++;}
			}
			pedido = pedidoFinal;
			return"Se necesita:\nCamisetas: "+camisetas+"\nPantalones: "+pantalones+"\nAbrigos: "+abrigos;
		}
		
		public String hacerOrdenDeCompra(ArrayList<Producto> pedido) {
			Proveedor proRecomendado= getProveedores().get(0);
			float descuento = 0;
			
			for (int i = 0; i < getProveedores().size(); i++) {
				((Proveedor) getProveedores().get(i)).verificarDisponibilidad(pedido);
				if(getProveedores().get(i).getDescuento()>=descuento) {
					descuento = getProveedores().get(i).getDescuento();
					proRecomendado = getProveedores().get(i);
				}
				
				ArrayList<Producto> productosDisponibles = new ArrayList<Producto>();
				
			}
//			proveedorSeleccionado = proRecomendado;
			return proRecomendado.getNombre();
		}
		
		public String ordenarEnvio(Tienda tienda, Proveedor proveedor) {
			float costo = 999999999;
			Transportista transRecomendado = transportistas.get(0); 
			
			for(int i = 0; i < transportistas.size(); i++) {
				float costoTransportista = transportistas.get(i).calcularPrecioTotal(proveedor, tienda);
				if (costoTransportista < costo) {
					costo = costoTransportista;
					transRecomendado = transportistas.get(i);
				}	
			}
			return transRecomendado.getNombre();
		}
		
		public double calcularCostoProductos(Proveedor proveedor) {
			
			costo =proveedor.costoProductos();
			costo+=transportistaSeleccionado.calcularPrecioTotal(proveedor, tienda);
			return costo;
			
		}
		
		public ArrayList<Proveedor> getProveedores() {return proveedores;}
		
		public ArrayList<Transportista> getTransportistas() {return transportistas;}
		
		public ArrayList<Producto> getPedido() {return pedido;}
		
		public Proveedor getProveedorSeleccionado() {return proveedorSeleccionado;}
		
		public void setProveedorSeleccionado(Proveedor p) {proveedorSeleccionado = p;}
		
		public Transportista getTransportistaSeleccionado() {return transportistaSeleccionado;}
		
		public void setTransportistaSeleccionado(Transportista t) {transportistaSeleccionado = t;}
		
		public String toString() {
			return "---------------------------------\n"
					+ "      Factura de la compra       \n"
					+ "---------------------------------\n"
					+ "Proveedor: "+getProveedorSeleccionado().getNombre()
					+"\nTransportista: "+getTransportistaSeleccionado().getNombre()
					+"\n---------------------------------"
					+"\nProductos comprados:\n"
					+getProveedorSeleccionado().toString()
					+"\ncosto del envio: "+getTransportistaSeleccionado().calcularPrecioTotal(proveedorSeleccionado, tienda)
					+"\nTotal: "+ calcularCostoProductos(proveedorSeleccionado);
			
		}
		
		public Tienda getTienda() {return tienda;}
}
