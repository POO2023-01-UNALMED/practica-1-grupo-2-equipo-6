package gestorAplicacion.clasesBase;

import java.util.ArrayList;


public class Compra {

	private Transportista transportista;
	private Tienda tienda;
	private ArrayList<Producto> pedido;
	private ArrayList<Producto> listaCompra;
	private ArrayList<Producto> productosQueLlegaron;
	private double costo;

	//Uso este constructor para la funcionalidad Venta a Socios
	public Compra(Transportista transportista, ArrayList<Producto> pedido, ArrayList<Producto>productosQueLlegaron, double costo) {
		this.transportista=transportista;
		this.pedido=pedido;
		this.productosQueLlegaron=productosQueLlegaron;
		this.costo=costo;
	}

	public Transportista getTransportista() {
		return transportista;
	}

	public void setTransportista(Transportista transportista) {
		this.transportista = transportista;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public ArrayList<Producto> getPedido() {
		return pedido;
	}

	public void setPedido(ArrayList<Producto> pedido) {
		this.pedido = pedido;
	}

	public ArrayList<Producto> getListaCompra() {
		return listaCompra;
	}

	public void setListaCompra(ArrayList<Producto> listaCompra) {
		this.listaCompra = listaCompra;
	}

	public ArrayList<Producto> getProductosQueLlegaron() {
		return productosQueLlegaron;
	}

	public void setProductosQueLlegaron(ArrayList<Producto> productosQueLlegaron) {
		this.productosQueLlegaron = productosQueLlegaron;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}



}

/***

import gestorAplicacion.clasesHerencia.Proveedor;
import java.util.Collections;


public class Compra {

	static int codigo;

	private ArrayList<ArrayList<Object>> listaCompra = new ArrayList<ArrayList<Object>>();
	private ArrayList<Object> proveedores = new ArrayList<Object>();
	private Bodega bodega;
	private double costo = 0;
	private Tienda tienda;

	public Compra(Bodega bodega, Tienda tienda) {
		this.bodega = bodega;
		this.proveedores = tienda.getProveedores();
		this.tienda = tienda;

		buscarProductosNecesitados();
		selecionarCantidadDeProductos();
		consultarProveedores(this.proveedores);
		calcularCosto();
		actualizarBodega();
		codigo++;
	}

	public void buscarProductosNecesitados() {

		for(int i = 0; i < bodega.getProductosEnBodega().size(); i++) {

			if(((Producto) bodega.getProductosEnBodega().get(i).get(0)).getCodigo().substring(0,1).equals("0") && bodega.getStopCamiseta() - bodega.getProductosEnBodega().get(i).get(1).hashCode() >=6) {
					bodega.getProductosEnBodega().get(i).set(2, bodega.getStopCamiseta() - bodega.getProductosEnBodega().get(i).get(1).hashCode());
					ArrayList<Object> clon =(ArrayList<Object>)bodega.getProductosEnBodega().get(i).clone();
					listaCompra.add(clon);
			}
			else if(((Producto) bodega.getProductosEnBodega().get(i).get(0)).getCodigo().substring(0,1).equals("1") && bodega.getStopPantalon()-bodega.getProductosEnBodega().get(i).get(1).hashCode() >=6){
					bodega.getProductosEnBodega().get(i).set(2,bodega.getStopPantalon() - bodega.getProductosEnBodega().get(i).get(1).hashCode());
					ArrayList<Object> clon =(ArrayList<Object>)bodega.getProductosEnBodega().get(i).clone();
					listaCompra.add(clon);

			}
			else if(((Producto) bodega.getProductosEnBodega().get(i).get(0)).getCodigo().substring(0,1).equals("2") && bodega.getStopAbrigo() -bodega.getProductosEnBodega().get(i).get(1).hashCode()>= 6) {
					bodega.getProductosEnBodega().get(i).set(2, bodega.getStopAbrigo() - bodega.getProductosEnBodega().get(i).get(1).hashCode());
					ArrayList<Object> clon =(ArrayList<Object>)bodega.getProductosEnBodega().get(i).clone();
					listaCompra.add(clon);
			}
		}
	}

	public void selecionarCantidadDeProductos() {
		int costoCompra = 0;
		ArrayList<ArrayList<Object>> listaOrdenada = ordenarListaCompra(listaCompra);
		ArrayList<Object> numeros = new ArrayList<Object>();

		for(int i = 0; i<listaOrdenada.size(); i++) {
			numeros.add(listaOrdenada.get(i).get(1));
		}

		int numeroCamisetas = bodega.calcularNumeroCamisetas();
		int numeroPantalones = bodega.calcularNumeroPantalon();
		int numeroAbrigos = bodega.calcularNumeroAbrigo();
		int acumulado=0;
		int control=0;
		int  i=0;

		while (true) {
			if (i==listaOrdenada.size()) {i=0;}
			costoCompra += ((Producto) listaOrdenada.get(i).get(0)).getCosto()*6;

			if(tienda. getPresupuestoCompra() - costoCompra >= 0 ) {

				while( i<=listaOrdenada.size()-2) {

					acumulado = listaOrdenada.get(i).get(1).hashCode();

					if(((Producto) listaOrdenada.get(i).get(0)).getTipo().equals("camiseta") && numeroCamisetas + 6 <bodega.getStopCamiseta() && tienda. getPresupuestoCompra() - costoCompra >= 0 ) {
						if(listaOrdenada.get(i).get(1).hashCode()<= listaOrdenada.get(i+1).get(1).hashCode()) {
							if(listaOrdenada.get(i).get(1).hashCode()<=listaOrdenada.get(0).get(1).hashCode()) {

								acumulado += 6;
								listaOrdenada.get(i).set(1, acumulado);
								numeroCamisetas+=6;
								costoCompra += ((Producto) listaOrdenada.get(i).get(0)).getCosto()*6;
							}

							else {
								costoCompra -= ((Producto) listaOrdenada.get(i).get(0)).getCosto()*6;
								break;
								}
						}
						else {
							costoCompra -= ((Producto) listaOrdenada.get(i).get(0)).getCosto()*6;
							break;
							}
					}
					else {
						costoCompra -= ((Producto) listaOrdenada.get(i).get(0)).getCosto()*6;
						control++;
						break;
					}
				}
				if(i==listaOrdenada.size()-1) {
					acumulado = listaOrdenada.get(i).get(1).hashCode();

					if(((Producto) listaOrdenada.get(i).get(0)).getTipo().equals("camiseta") && numeroCamisetas + 6 <bodega.getStopCamiseta()) {
						if(listaOrdenada.get(i).get(1).hashCode()<listaOrdenada.get(0).get(1).hashCode()) {
							acumulado+=6;
							listaOrdenada.get(i).set(1, acumulado);
							numeroCamisetas+=6;
						}
						else {costoCompra -= ((Producto) listaOrdenada.get(i).get(0)).getCosto()*6;}
					}
					else {
						costoCompra -= ((Producto) listaOrdenada.get(i).get(0)).getCosto()*6;
						control++;
						}
				}
			}
			else {
				costoCompra -= ((Producto) listaOrdenada.get(i).get(0)).getCosto()*6;
				control++;
				}
			if(control == listaOrdenada.size()) {break;}
			i++;
		}

		listaCompra = calcularPedido(numeros, listaOrdenada);
	}

	public ArrayList<ArrayList<Object>> ordenarListaCompra( ArrayList<ArrayList<Object>> lista){

		ArrayList<ArrayList<Object>> listaOrdenada = new ArrayList<ArrayList<Object>>();

		for(int j = 0; j < listaCompra.size(); j++) {
			ArrayList<Object> productoMayorPrioridad = new ArrayList<Object>();
			productoMayorPrioridad.add(0);
			productoMayorPrioridad.add(0);
			productoMayorPrioridad.add(-100);

			for(int i = 0; i < lista.size(); i++) {
				if (lista.get(i).get(2).hashCode() > productoMayorPrioridad.get(2).hashCode()) {
					productoMayorPrioridad = lista.get(i);
				}
			}
			int n=productoMayorPrioridad.get(1).hashCode();
			listaOrdenada.add(productoMayorPrioridad);
			productoMayorPrioridad.set(2, -100);
		}

		return listaOrdenada;
	}

	public ArrayList<ArrayList<Object>> calcularPedido( ArrayList<Object> numeros,ArrayList<ArrayList<Object>> listaM){
		int cantidad;
		ArrayList<ArrayList<Object>> listaNueva = new ArrayList<ArrayList<Object>>();

		for(int i =0; i<listaM.size(); i++) {
			cantidad = (listaM.get(i).get(1).hashCode() - numeros.get(i).hashCode());
			listaM.get(i).set(1, cantidad);
			listaNueva.add(listaM.get(i));
		}
		return listaNueva;
	}

	public void consultarProveedores(  ArrayList<Object> proveedores) {
	Proveedor mejorProveedor =((Proveedor) proveedores.get(0));
	int cantidadDisponible = 0;

	ArrayList<ArrayList<Object>> listaFinal = new ArrayList<>();
	ArrayList<ArrayList<Object>> l = new ArrayList<>();

		for (int i =0; i < proveedores.size(); i++) {
			l = ((Proveedor) proveedores.get(i)).verificarDisponibilidad(listaCompra);
			if(cantidadDisponible<= Collections.frequency(l.get(0), true)) {
				cantidadDisponible = Collections.frequency(l.get(0), true);
				mejorProveedor = ((Proveedor) proveedores.get(i));
				listaFinal =l;
			}
		}
		mejorProveedor.setCalificacion();
		listaCompra = listaFinal;

	}

	public void calcularCosto() {

		for(int i = 0; i < listaCompra.size(); i++) {
			if(listaCompra.get(i).get(2).equals(true)) {
				costo+= ((Producto) listaCompra.get(i).get(0)).getCosto() * listaCompra.get(i).get(1).hashCode();
			}
		}
		tienda.setPresupuestoCompra(tienda. getPresupuestoCompra() - costo);
	}

	public void actualizarBodega() {

		ArrayList<ArrayList<Object>> l = (ArrayList<ArrayList<Object>>)listaCompra;

		for(int i = 0; i < l.size(); i++) {
			int cantidad;
			if(l.get(i).get(2).equals(true)) {
				for (int k = 0; k < bodega.getProductosEnBodega().size(); k++) {
					if (((Producto) l.get(i).get(0)).getCodigo().equals(((Producto)bodega.getProductosEnBodega().get(k).get(0)).getCodigo())) {
						cantidad =  l.get(i).get(1).hashCode() + bodega.getProductosEnBodega().get(k).get(1).hashCode();
						bodega.getProductosEnBodega().get(k).set(1, cantidad);
						break;
					}
				}
			}
		}
	}

	public void setProveedores(ArrayList<Object> proveedores) {this.proveedores = proveedores;}

	public ArrayList<Object> getProveedores(){return proveedores;}
	public ArrayList<ArrayList<Object>> getListaCompra(){return listaCompra;}


	public String toString() {
		String lista = "";
		for(int i = 0; i<listaCompra.size(); i++) {
			lista += listaCompra.get(i).get(0) + " cantidad = " + listaCompra.get(i).get(1) +" disponibilidad = " + listaCompra.get(i).get(2) + "\n";
		}
		return "productos para comprar\n" + lista +"costo compra = " +costo	+ "\ncodido de compra = " + codigo;
	}

}
***/
