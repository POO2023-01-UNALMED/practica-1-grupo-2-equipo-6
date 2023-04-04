package clasesBase;
import java.util.ArrayList;
import java.util.Collections;

public class Compra {
	
	static int codigo;
	ArrayList<Object[]> listaCompra = new ArrayList<Object[]>();
	Bodega bodega;
	Tienda tienda;
	ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
	double costo;
		
	public Compra(Bodega bodega, Tienda tienda) {
		this.bodega = bodega;
		this.tienda = tienda;
		buscarProductosNecesitados();
		selecionarCantidadDeProductos();
		codigo++;
	}
	
	public void buscarProductosNecesitados() {
	
		for(int i = 0; i < bodega.productosEnBodega.size(); i++) {
			
			if(((Producto) bodega.productosEnBodega.get(i)[0]).codigo.substring(0,1).equals("0") && bodega.productosEnBodega.get(i)[1].hashCode()< bodega.stopCamiseta[0]) {
					bodega.productosEnBodega.get(i)[2]= bodega.stopCamiseta[0] - bodega.productosEnBodega.get(i)[1].hashCode();
					listaCompra.add(bodega.productosEnBodega.get(i));
			}
			else if(((Producto) bodega.productosEnBodega.get(i)[0]).codigo.substring(0,1).equals("1") && bodega.productosEnBodega.get(i)[1].hashCode()< bodega.stopPantalon[0]){
					bodega.productosEnBodega.get(i)[2]= bodega.stopPantalon[0] - bodega.productosEnBodega.get(i)[1].hashCode();
					listaCompra.add(bodega.productosEnBodega.get(i));
				
			}
			else if(((Producto) bodega.productosEnBodega.get(i)[0]).codigo.substring(0,1).equals("2") && bodega.productosEnBodega.get(i)[1].hashCode()< bodega.stopAbrigo[0]) {
					bodega.productosEnBodega.get(i)[2]= bodega.stopAbrigo[0] - bodega.productosEnBodega.get(i)[1].hashCode();
					listaCompra.add(bodega.productosEnBodega.get(i));
			}
			else if(((Producto) bodega.productosEnBodega.get(i)[0]).codigo.substring(0,1).equals("3") && bodega.productosEnBodega.get(i)[1].hashCode()< bodega.stopBuzo[0]) {
				bodega.productosEnBodega.get(i)[2]= bodega.stopBuzo[0] - bodega.productosEnBodega.get(i)[1].hashCode();
				listaCompra.add(bodega.productosEnBodega.get(i));
			}
			else if(((Producto) bodega.productosEnBodega.get(i)[0]).codigo.substring(0,1).equals("4") && bodega.productosEnBodega.get(i)[1].hashCode()< bodega.stopPantaloneta[0]) {
				bodega.productosEnBodega.get(i)[2]= bodega.stopPantaloneta[0] - bodega.productosEnBodega.get(i)[1].hashCode();
				listaCompra.add(bodega.productosEnBodega.get(i));
			}
		}
	}
	
	public void selecionarCantidadDeProductos() {
		int costoCompra = 0;
		ArrayList<Object[]> listaOrdenada = ordenarListaCompra(listaCompra);
		int numeroCamisetas = bodega.calcularNumeroCamisetas(bodega.productosEnBodega);
		int numeroPantalones = bodega.calcularNumeroPantalon(bodega.productosEnBodega);
		int numeroAbrigos = bodega.calcularNumeroAbrigo(bodega.productosEnBodega);
		int numeroBuzos = bodega.calcularNumeroBuzo(bodega.productosEnBodega);
		int numeroPantalonetas = bodega.calcularNumeroPantalonetas(bodega.productosEnBodega);
		
		int multiplicador = 1;
		while (true){
			int controlWhilePrecio = 0;
			int controlWhileStop = 0;
			
			for (int i = 0; i < listaOrdenada.size(); i++) {
				costoCompra += ((Producto) listaOrdenada.get(i)[0]).costo*6;
				
				if (tienda.presupuestoCompras - costoCompra >-1){
					
					if(((Producto) listaOrdenada.get(i)[0]).tipo.equals("camiseta") && numeroCamisetas + (6*multiplicador) < bodega.stopCamiseta[1]) {
						listaOrdenada.get(i)[1] =  6*multiplicador;
					}
					else if(((Producto) listaOrdenada.get(i)[0]).tipo.equals("pantalon") && numeroPantalones + (6*multiplicador) < bodega.stopPantalon[1]) {
						listaOrdenada.get(i)[1] =  6*multiplicador;
					}
					else if(((Producto) listaOrdenada.get(i)[0]).tipo.equals("abrigo") && numeroAbrigos + (6*multiplicador) < bodega.stopAbrigo[1]) {
						listaOrdenada.get(i)[1] =  6*multiplicador;
					}
					else if(((Producto) listaOrdenada.get(i)[0]).tipo.equals("buzo") && numeroBuzos + (6*multiplicador) < bodega.stopBuzo[1]) {
						listaOrdenada.get(i)[1] =  6*multiplicador;
					}
					else if(((Producto) listaOrdenada.get(i)[0]).tipo.equals("pantaloneta") && numeroPantalonetas + (6*multiplicador) < bodega.stopPantaloneta[1]) {
						listaOrdenada.get(i)[1] =  6*multiplicador;
					}
					else {
						controlWhileStop += 1;
					}
				}
				else {
					costoCompra -= ((Producto) listaOrdenada.get(i)[0]).costo*6;
					controlWhilePrecio += 1;
				}
			}
			if(controlWhilePrecio == listaOrdenada.size() || controlWhileStop == listaOrdenada.size()) {
				listaCompra = listaOrdenada;
				break;
			}
			multiplicador++;
		}
	}
	
	public ArrayList<Object[]> ordenarListaCompra( ArrayList<Object[]> lista){
		ArrayList<Object[]> listaOrdenada = new ArrayList<Object[]>();
		Object[] ini = {0,0,0};
		
		
		for(int j = 0; j < listaCompra.size(); j++) {
			Object[] productoMayorPrioridad = {0,0,0};
			
			for(int i = 0; i < lista.size(); i++) {
				if (lista.get(i)[2].hashCode() > productoMayorPrioridad[2].hashCode()) {
					productoMayorPrioridad = lista.get(i);
					productoMayorPrioridad[1] = 0;
				}
			}
			listaOrdenada.add(productoMayorPrioridad);
			Collections.replaceAll(lista, productoMayorPrioridad, ini);
		}
		
		return listaOrdenada;	
	}
	
	public String toString() {
		return "productos para comprar" + listaCompra.toString() + "codido de compra = " + codigo;
	}
	
	

}
