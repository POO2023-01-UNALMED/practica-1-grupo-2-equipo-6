package CompraAbastecimiento;

import java.util.ArrayList;

public class Controladora {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Tienda tienda = new Tienda(120);
		
		Producto camisa1 = new Producto("camiseta","S", "Azul", 2);
		Producto camisa2 = new Producto("camiseta","M", "Azul", 2);
		Producto camisa3 = new Producto("camiseta","L", "Azul", 2);
		Producto camisa4 = new Producto("camiseta","XL", "Azul", 2);
		Producto camisa5 = new Producto("camiseta","S", "Rojo", 2);
		Producto camisa6 = new Producto("camiseta","M", "Rojo", 2);
		Producto camisa7 = new Producto("camiseta","L", "Rojo", 2);
		Producto camisa8 = new Producto("camiseta","XL", "Rojo", 2);
		Producto pantalon = new Producto("pantalon","S", "Negro", 9);
		
		Object[] lista = {camisa1,15,0};
		Object[] lista1 = {camisa2,4,0};
		Object[] lista2 = {camisa3,6,0};
		Object[] lista4 = {camisa4,1,0};
		Object[] lista5 = {camisa5,3,0};
		Object[] lista6 = {pantalon,0,0};
		
		int[] stopCamiseta = {3,50};
		int[] stopPantalon = {3,50};
		int[] stopPantaloneta = {3,50};
		int[] stopAbrigo = {3,50};
		int[] stopBuzo = {3,50};
		
		Bodega bodega1 = new Bodega(200,lista,stopCamiseta,stopPantalon,stopPantaloneta,stopAbrigo,stopBuzo);
//		Bodega bodega2 = new Bodega(50,lista1);
//		Bodega bodega3 = new Bodega(75,lista2);
		
		bodega1.agregarProductos(lista4);
		bodega1.agregarProductos(lista1);
		bodega1.agregarProductos(lista5);
		bodega1.agregarProductos(lista6);
		
		
		Compra compra1 = new Compra(bodega1, tienda);
//		compra1.buscarProductosNecesitados();
		
		
		
//		System.out.println(pantalon);
//		System.out.println(bodega1);
		System.out.println(compra1);
		
//		String prueba ="abc";
//		System.out.println(prueba.substring(0,1));
		
	}

}
