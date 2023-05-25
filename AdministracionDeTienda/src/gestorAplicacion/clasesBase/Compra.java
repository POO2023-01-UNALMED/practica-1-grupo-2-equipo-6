package gestorAplicacion.clasesBase;
import java.sql.Struct;
import java.text.DecimalFormat;
import java.util.ArrayList;

import gestorAplicacion.clasesBase.Producto.Tipo;
import gestorAplicacion.clasesHerencia.Proveedor;
import gestorAplicacion.clasesHerencia.Transportista;
import baseDatos.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.io.Serializable;

import baseDatos.Deserializador;

public class Compra implements Serializable{
	
	static int codigo;	
	private double costo = 0;
	private boolean revisado;
	private Tienda tienda;
	private ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
	private Proveedor proveedorSeleccionado;
	private Transportista transportistaSeleccionado;
	private ArrayList<Producto> pedido = new ArrayList<Producto>();
	private ArrayList<Transportista> transportistas = new ArrayList<Transportista>();
	private ArrayList<Producto> productosExtraviados;
	private ArrayList<Producto> compraLlego;
	
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
		
//		hacerPedido(tienda);
//		hacerOrdenDeCompra(pedido);
//		ordenarEnvio(tienda, provedores.get(0));
//		calcularCosto(provedores.get(0), transportistas.get(0));
		codigo++;
	}
	
	public ArrayList<Producto> hacerPedido(Tienda tienda) {
		
		int numCamisetas = tienda.getBodega().calcularCamisas(tienda.getBodega().getProductosEnBodega());
		int numPantalones = tienda.getBodega().calcularPantalon(tienda.getBodega().getProductosEnBodega());
		int numAbrigo = tienda.getBodega().calcularAbrigos(tienda.getBodega().getProductosEnBodega());
		int numeroDeProductos = numCamisetas + numPantalones + numAbrigo + 1;
		double presupuesto = tienda.getPresupuestoCompra();
		ArrayList<Producto> pedidoFinal = new ArrayList<Producto>();
		
		while (numeroDeProductos <= tienda.getBodega().getStopBodega()) {
			
			if (numCamisetas <= numPantalones && numCamisetas <= numAbrigo) {
		
				Producto producto = new Producto(Tipo.CAMISA,40000,20000);
				numeroDeProductos += 1;
				numCamisetas += 1;
				this.pedido.add(producto);
			}
			
			else if (numPantalones <= numCamisetas && numPantalones <= numAbrigo) {
				
				Producto producto = new Producto(Tipo.PANTALON,50000,25000);
				numeroDeProductos += 1;
				numPantalones += 1;
				this.pedido.add(producto);
			}
			
			else {
				
				Producto producto = new Producto(Tipo.ABRIGO,60000,30000);
				numeroDeProductos += 1;
				numAbrigo += 1;
				this.pedido.add(producto);	
			}
		}
		if(pedido.size()<6) {
			ArrayList<Producto> p=null;
			return p;
		}

		
		for (int i = 0; i < pedido.size(); i++) {
			if(presupuesto -pedido.get(i).getCosto()<=0) {}
			else {
				presupuesto= presupuesto-pedido.get(i).getCosto();
				pedidoFinal.add(pedido.get(i));
			}
		}
		
		if (pedidoFinal.size()<6) {
			return pedidoFinal;
		}
			
		
		int camisetas = 0;
		int pantalones = 0;
		int abrigos = 0;
		for (int i = 0; i < pedidoFinal.size(); i++) {
			
			
			
			if( pedidoFinal.get(i).getNombre().equals("CAMISA")) {
				camisetas++;
			}
			else if (pedidoFinal.get(i).getNombre().equals("PANTALON")) {
				pantalones++;
			}
			else {abrigos++;}
		}
		setPedido(pedidoFinal); 
//		return"Se necesita:\nCamisetas: "+camisetas+"\nPantalones: "+pantalones+"\nAbrigos: "+abrigos;
		return pedidoFinal;
	}
	
	public Proveedor hacerOrdenDeCompra(ArrayList<Producto> pedido) {
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
//		proveedorSeleccionado = proRecomendado;
		return proRecomendado;
	}
	
	public Transportista ordenarEnvio(Tienda tienda, Proveedor proveedor) {
		float costo = 999999999;
		Transportista transRecomendado = transportistas.get(0); 
		
		for(int i = 0; i < transportistas.size(); i++) {
			float costoTransportista = transportistas.get(i).calcularPrecioTotal(proveedor, tienda);
			if (costoTransportista < costo) {
				costo = costoTransportista;
				transRecomendado = transportistas.get(i);
			}	
		}
		return transRecomendado;
	}
	
	public double calcularCostoProductos(Proveedor proveedor) {
		
		costo =proveedor.costoProductos();
		costo+=transportistaSeleccionado.calcularPrecioTotal(proveedor, tienda);
		return costo;
		
	}

	public ArrayList<Producto> generarProductosExtraviados() {
        ArrayList<Producto> productosSeleccionados = new ArrayList<>();
        ArrayList<Producto> listaProductos = this.proveedorSeleccionado.getBodega().getProductosEnBodega();
        Random random = new Random();
        Set<Integer> indicesSeleccionados = new HashSet<>();
    
        int cantidadProductos = listaProductos.size();
        int maxCantidadSeleccionada = (int) Math.ceil(cantidadProductos * 0.4); 
    
        int cantidadSeleccionada = random.nextInt(maxCantidadSeleccionada + 1);
    
        while (cantidadSeleccionada > 0) {
            int indiceAleatorio = random.nextInt(cantidadProductos);
            if (!indicesSeleccionados.contains(indiceAleatorio)) {
                Producto productoSeleccionado = listaProductos.get(indiceAleatorio);
                productosSeleccionados.add(productoSeleccionado);
                indicesSeleccionados.add(indiceAleatorio);
                cantidadSeleccionada--;
            }
        }
        this.productosExtraviados = productosSeleccionados;
        return productosSeleccionados;
    }

	public ArrayList<Producto> generarCompraLlego(){
        ArrayList<Producto> lista1 = this.proveedorSeleccionado.getBodega().getProductosEnBodega();
        ArrayList<Producto> lista2 = this.productosExtraviados;
    
        if (lista1.size() == lista2.size() && lista1.containsAll(lista2)) {
            return new ArrayList<>();
        }
    
        ArrayList<Producto> compraLlego = new ArrayList<>();

        HashMap<String, Integer> contadores1 = new HashMap<>();
        HashMap<String, Integer> contadores2 = new HashMap<>();
        for (Producto producto1 : lista1) {
            String clave = producto1.getNombre() + "-" + producto1.getPrecio();
            contadores1.put(clave, contadores1.getOrDefault(clave, 0) + 1);
        }
        for (Producto producto2 : lista2) {
            String clave = producto2.getNombre() + "-" + producto2.getPrecio();
            contadores2.put(clave, contadores2.getOrDefault(clave, 0) + 1);
        }
        for (Producto producto1 : lista1) {
            String clave = producto1.getNombre() + "-" + producto1.getPrecio();
            if (!contadores2.containsKey(clave) || contadores2.get(clave) <= 0) {
                compraLlego.add(producto1);
           } else {
                contadores2.put(clave, contadores2.get(clave) - 1);
            }
        }
            this.compraLlego = compraLlego;
            return compraLlego;
    } 

	public void setProductosExtraviados(ArrayList<Producto> productosExtraviados){
        this.productosExtraviados = productosExtraviados;
    }

    public ArrayList<Producto> getProductosExtraviados(){
        return productosExtraviados;
    }

	public ArrayList<Producto> getCompraLlego(){
        return compraLlego;
    }

	public boolean getRevisado(){
		return revisado;
	}

	public void setRevisado(boolean revisado){
		this.revisado = revisado;
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
				+"\ncosto del envio: "+getTransportistaSeleccionado().calcularPrecioTotal(proveedorSeleccionado, tienda)+" pesos"
				+"\nTotal: "+ calcularCostoProductos(proveedorSeleccionado)+" pesos";
		
	}
	
	public void setPedido(ArrayList<Producto> pedido) {this.pedido = pedido;}
	
	public Tienda getTienda() {return tienda;}
}
