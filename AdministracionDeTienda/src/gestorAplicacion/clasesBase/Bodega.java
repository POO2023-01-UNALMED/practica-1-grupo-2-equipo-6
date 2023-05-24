package gestorAplicacion.clasesBase;

import java.util.ArrayList;


public class Bodega implements Inventariar,java.io.Serializable{

	
	/**
	 * 
	 */
	private final int stopBodega;
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> productosEnBodega = new ArrayList<Producto>();
	private static String resumenPedido;
	private static ArrayList<Transferencia> pagos=new ArrayList<Transferencia>();
	
	public Bodega( int max) {
		stopBodega = max;
	}
	
	public int calcularNumeroProductos() {		
		return productosEnBodega.size();
	}
	
	public void agregarProductos(Producto producto) {
		this.productosEnBodega.add(producto);
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
	
	public int calcularCamisas(ArrayList<Producto> pedido) {
		int numeroCamisetas = 0;
		for (int i = 0; i < pedido.size(); i++) {
			if( pedido.get(i).getNombre().equals("CAMISA")) {
				numeroCamisetas ++;
			}
		}
		return numeroCamisetas;
	}
	
	
	public int calcularPantalon(ArrayList<Producto> pedido) {
		int numeroPantalones = 0;
		for (int i = 0; i < pedido.size(); i++) {
			if(pedido.get(i).getNombre().equals("PANTALON")) {
				numeroPantalones ++;
			}
		}
		return numeroPantalones;
	}
	
	
	public int calcularAbrigos(ArrayList<Producto> pedido) {
		int numeroAbrigo = 0;
		for (int i = 0; i < pedido.size(); i++) {
			if(pedido.get(i).getNombre().equals("ABRIGO")) {
				numeroAbrigo ++;
			}
		}
		return numeroAbrigo;
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
	
	public int getStopBodega() {return stopBodega;}
	
	
	public String toString() {
		
		return "Camisetas = " + calcularCamisas()+ "\nPantalones = " + calcularPantalon() + "\nAbrigos = " +calcularAbrigos();
	}
	//Relacionado con ENVIO
	public enum SETS{
		ONLY,TU,COMPLETO;	
	}
	//PRIMERA INTERACCION-ENVIO
	public static ArrayList<Producto> realizarPedido(ArrayList<Tienda> tiendas,SETS set,int cantidad){
		ArrayList<Producto>necesarios=Producto.seleccionarProductos(set,cantidad);
		ArrayList<Producto> pedido=new ArrayList<Producto>(); 
		for(int i=0;i<tiendas.size();i++) {
			if(necesarios.isEmpty()) {
				break;
				
			}else {
				Bodega bodega=tiendas.get(i).getBodega();
				ArrayList<Producto> productos=bodega.pedirProductos(necesarios);
				pedido.addAll(productos);
				gestionarPago(tiendas.get(i),productos);
				
			}	
			
		}
		crearResumen(necesarios,pedido);
		
		
		return pedido;
	}
	
	private static void crearResumen(ArrayList<Producto> necesarios, ArrayList<Producto> pedido) {
		if(necesarios.size()==0) {
			setResumenPedido("El pedido se ha completado exitosamente");
		}else {
			setResumenPedido("Por falta de disponibilidad de productos en las tiendas el pedido solo ha podido completar "+pedido.size()+" de los "+(((int)pedido.size())+((int)necesarios.size()))+" necesarios.");
		}
		
	}
	public static void gestionarPago(Tienda tienda, ArrayList<Producto> productos) {
		double cantidad=0;
		for(int i=0;i<productos.size();i++) {
			cantidad+=productos.get(i).getCosto();
			
		}
		Transferencia pago=new Transferencia(Tienda.getCuentatienda(),cantidad);
		pago.setDetalle("Id Tienda:"+tienda.getId()+" VALOR: $"+cantidad);
		getPagos().add(pago);
		
		
	}

//Metodo que se llama desde la primera interaccion
	public ArrayList<Producto> pedirProductos(ArrayList<Producto> productos){
		ArrayList<Producto> pedido=new ArrayList<Producto>();
		ArrayList<Producto> completado=new ArrayList<Producto>();
		for(int i=0;i<productos.size();i++) {
			for(int e=0;e<productosEnBodega.size();e++) {
				if(productos.get(i).getTipo()==productosEnBodega.get(e).getTipo()) {
					pedido.add(productosEnBodega.get(e));
					completado.add(productos.get(i));
					productosEnBodega.remove(e);
					break;
				}
			}
			
		}
		productos.removeAll(completado);
		return pedido;
	}
	

}
