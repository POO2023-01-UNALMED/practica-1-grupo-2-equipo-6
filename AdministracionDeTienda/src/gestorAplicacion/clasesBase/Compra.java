package gestorAplicacion.clasesBase;

import java.util.ArrayList;
import gestorAplicacion.clasesHerencia.Proveedor;
import java.util.Collections;

public class Compra {
	
	static int codigo;

	private ArrayList<ArrayList<Object>> listaCompra = new ArrayList<ArrayList<Object>>();
	private Bodega bodega;
	private Tienda tienda;
	private double costo = 0;
		
	public Compra(Bodega bodega, Tienda tienda) {
		this.bodega = bodega;
		this.tienda = tienda;
		
		buscarProductosNecesitados();
		selecionarCantidadDeProductos();
		consultarProveedores(tienda);
		calcularCosto();
		codigo++;
	}
	
	public void buscarProductosNecesitados() {
	
		for(int i = 0; i < bodega.getProductosEnBodega().size(); i++) {
			
			if(((Producto) bodega.getProductosEnBodega().get(i).get(0)).getCodigo().substring(0,1).equals("0") && bodega.getProductosEnBodega().get(i).get(1).hashCode()< bodega.getStopCamiseta()[1] - bodega.getStopCamiseta()[0]) {
					bodega.getProductosEnBodega().get(i).set(2, bodega.getStopCamiseta()[0] - bodega.getProductosEnBodega().get(i).get(1).hashCode());
					listaCompra.add(bodega.getProductosEnBodega().get(i));
			}
			else if(((Producto) bodega.getProductosEnBodega().get(i).get(0)).getCodigo().substring(0,1).equals("1") && bodega.getProductosEnBodega().get(i).get(1).hashCode()< bodega.getStopPantalon()[1] - bodega.getStopPantalon()[0]){
					bodega.getProductosEnBodega().get(i).set(2,bodega.getStopPantalon()[0] - bodega.getProductosEnBodega().get(i).get(1).hashCode());
					listaCompra.add(bodega.getProductosEnBodega().get(i));
				
			}
			else if(((Producto) bodega.getProductosEnBodega().get(i).get(0)).getCodigo().substring(0,1).equals("2") && bodega.getProductosEnBodega().get(i).get(1).hashCode()< bodega.getStopAbrigo()[1]-bodega.getStopAbrigo()[0]) {
					bodega.getProductosEnBodega().get(i).set(2, bodega.getStopAbrigo()[0] - bodega.getProductosEnBodega().get(i).get(1).hashCode());
					listaCompra.add(bodega.getProductosEnBodega().get(i));
			}
			else if(((Producto) bodega.getProductosEnBodega().get(i).get(0)).getCodigo().substring(0,1).equals("3") && bodega.getProductosEnBodega().get(i).get(1).hashCode()< bodega.getStopBuzo()[1] - bodega.getStopBuzo()[0]) {
				bodega.getProductosEnBodega().get(i).set(2, bodega.getStopBuzo()[0] - bodega.getProductosEnBodega().get(i).get(1).hashCode());
				listaCompra.add(bodega.getProductosEnBodega().get(i));
			}
			else if(((Producto) bodega.getProductosEnBodega().get(i).get(0)).getCodigo().substring(0,1).equals("4") && bodega.getProductosEnBodega().get(i).get(1).hashCode()< bodega.getStopPantaloneta()[1] - bodega.getStopPantaloneta()[0]) {
				bodega.getProductosEnBodega().get(i).set(2, bodega.getStopPantaloneta()[0] - bodega.getProductosEnBodega().get(i).get(1).hashCode());
				listaCompra.add(bodega.getProductosEnBodega().get(i));
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
		
		int numeroCamisetas = bodega.calcularNumeroCamisetas(bodega.getProductosEnBodega());
		int numeroPantalones = bodega.calcularNumeroPantalon(bodega.getProductosEnBodega());
		int numeroAbrigos = bodega.calcularNumeroAbrigo(bodega.getProductosEnBodega());
		int numeroBuzos = bodega.calcularNumeroBuzo(bodega.getProductosEnBodega());
		int numeroPantalonetas = bodega.calcularNumeroPantalonetas(bodega.getProductosEnBodega());
		int acumulado=0;
		int control=0;
//		int controlInterno = 1;
		int  i=0;
		
		while (true) {
			if (i==listaOrdenada.size()) {i=0;}
			costoCompra += ((Producto) listaOrdenada.get(i).get(0)).getCosto()*6;
			
			if(tienda.getPresupuestoCompra() - costoCompra >= 0 ) {
			
				while( i<=listaOrdenada.size()-2) {
					
					acumulado = listaOrdenada.get(i).get(1).hashCode();
					
					if(((Producto) listaOrdenada.get(i).get(0)).getTipo().equals("camiseta") && numeroCamisetas + 6 <bodega.getStopCamiseta()[1] && tienda.getPresupuestoCompra() - costoCompra >= 0 ) {
						if(listaOrdenada.get(i).get(1).hashCode()<= listaOrdenada.get(i+1).get(1).hashCode()) {
							if(listaOrdenada.get(i).get(1).hashCode()<=listaOrdenada.get(0).get(1).hashCode()) {
//								System.out.println(tienda.getPresupuestoCompra());
//								System.out.println(costoCompra);
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
//					controlInterno++;
				}
//				controlInterno =1;
				if(i==listaOrdenada.size()-1) {
					acumulado = listaOrdenada.get(i).get(1).hashCode();
					
					if(((Producto) listaOrdenada.get(i).get(0)).getTipo().equals("camiseta") && numeroCamisetas + 6 <bodega.getStopCamiseta()[1]) {
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
		
//		listaCompra = listaOrdenada;
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
	
	public void consultarProveedores(Tienda tienda) {
	Proveedor mejorProveedor =((Proveedor) tienda.getProveedores().get(0));
	int cantidadDisponible = 0;
	
	ArrayList<ArrayList<Object>> listaFinal = new ArrayList<>();
	ArrayList<ArrayList<Object>> l = new ArrayList<>();
	
		for (int i =0; i < tienda.getProveedores().size(); i++) {
			l = ((Proveedor) tienda.getProveedores().get(i)).verificarDisponibilidad(listaCompra);
			if(cantidadDisponible<= Collections.frequency(l.get(0), true)) {
				cantidadDisponible = Collections.frequency(l.get(0), true);
				mejorProveedor = ((Proveedor) tienda.getProveedores().get(i));
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
//			costo+= ((Producto) listaCompra.get(i).get(0)).getCosto() * listaCompra.get(i).get(1).hashCode();
		}
		tienda.setPresupuestoCompra(tienda.getPresupuestoCompra() - costo);
	}

	public String toString() {
		String lista = "";
		for(int i = 0; i<listaCompra.size(); i++) {
			lista += listaCompra.get(i).get(0) + " cantidad = " + listaCompra.get(i).get(1) +" disponibilidad = " + listaCompra.get(i).get(2) + "\n";
		}
		return "productos para comprar\n" + lista +"costo compra = " +costo	+ "\ncodido de compra = " + codigo;
	}
		
}
