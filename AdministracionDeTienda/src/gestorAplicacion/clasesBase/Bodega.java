package gestorAplicacion.clasesBase;

import java.util.ArrayList;

import gestorAplicacion.clasesBase.Tienda;


public class Bodega implements Inventariar,java.io.Serializable{

	
	/**
	 * 
	 */
	private final int stopBodega;
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> productosEnBodega = new ArrayList<Producto>();
	private static String resumenPedido;
	private static ArrayList<Transferencia> pagos=new ArrayList<Transferencia>();
	
	public Bodega(ArrayList<Producto> productos) {
		this.stopBodega = productos.size();
		productosEnBodega=productos;
	}
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
	int contadorAbrigo=Inventariar.calcularAbrigos(productosRetirar);
	int contadorPantalon=Inventariar.calcularPantalon(productosRetirar);
	int contadorCamisas=Inventariar.calcularCamisas(productosRetirar);
	

	
	if(this.calcularCamisas()-contadorCamisas>=minCamisas && 
	this.calcularAbrigos()-contadorAbrigo>=minAbrigo && 
	this.calcularPantalon()-contadorPantalon>=minPantalon) {
		
		this.retirarProductos(productosRetirar);
		return true;
		
	}else {
		return false;
	}
	}
		
	

	private void retirarProductos(ArrayList<Producto> productos) {	

			ArrayList<String>borrarProducto=new ArrayList<String>();
			ArrayList<Producto>productosBorrar=new ArrayList<Producto>();
			for(Producto p1:productos) {
				borrarProducto.add(p1.getNombre());
			}
			for(Producto p: productosEnBodega) {
				
				if(borrarProducto.indexOf(p.getNombre())!=-1){
					productosBorrar.add(p);
					borrarProducto.remove(p.getNombre());
				}
			}
			
			productosEnBodega.removeAll(productosBorrar);
		
		}
		
	
	
	public int getStopBodega() {return stopBodega;}
	
	
	public String toString() {
		
		return "Camisetas = " + calcularCamisas(getProductosEnBodega())+ "\nPantalones = " + calcularPantalon(getProductosEnBodega()) + "\nAbrigos = " +calcularAbrigos(getProductosEnBodega());
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
		Transferencia pago=new Transferencia(Tienda.getCuentaTienda(),cantidad);
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

	public void abastecerBodega(ControlCalidad c, ArrayList<Producto> lista) {
        if ((productosEnBodega.isEmpty() || productosEnBodega == null ) && ControlCalidad.listaSinLista(c.getCompra().getCompraLlego(), c.getProductosDefectuosos()) != null) {
            this.setProductos(ControlCalidad.listaSinLista(c.getCompra().getCompraLlego(), c.getProductosDefectuosos()));
            if (lista != null) {
                this.abastecerBodega(lista);
            }
        }  else if (lista != null){
            this.abastecerBodega(lista);
        }

    }  

    public void abastecerBodega(ArrayList<Producto> lista) {
        for (Producto producto : lista) {
            this.addProducto(producto);
        }

    }

	
    public void addProducto(Producto producto){
        productosEnBodega.add(producto);
    }

	public void setProductos(ArrayList<Producto> productos){
        this.productosEnBodega = productos;
    }

	public static String getResumenPedido() {
		return resumenPedido;
	}

	public static void setResumenPedido(String resumenPedido) {
		Bodega.resumenPedido = resumenPedido;
	}

	public static ArrayList<Transferencia> getPagos() {
		return pagos;
	}

	public static void setPagos(ArrayList<Transferencia> pagos) {
		Bodega.pagos = pagos;
	}
	

}
