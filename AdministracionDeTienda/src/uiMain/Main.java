package uiMain;

import java.util.ArrayList;
import java.util.Scanner;

import baseDatos.Deserializador;
import gestorAplicacion.clasesBase.*;
import gestorAplicacion.clasesBase.Banco.PuntajeCredito;
import gestorAplicacion.clasesBase.Banco.SolucionesProblemaFinanciero;
import gestorAplicacion.clasesBase.Bodega.SETS;
import gestorAplicacion.clasesBase.Credito.Cuota;
import gestorAplicacion.clasesBase.Credito.Estado;
import gestorAplicacion.clasesBase.CuentaBancaria.Pais;
import gestorAplicacion.clasesBase.Producto.Tipo;
import gestorAplicacion.clasesHerencia.*;
import gestorAplicacion.clasesHerencia.Cliente.Ciudades;
import gestorAplicacion.clasesHerencia.Cliente.TipoEnvio;
import gestorAplicacion.clasesHerencia.Empleado.Cargo;
import gestorAplicacion.clasesHerencia.Intervenido.Colecciones;
import baseDatos.Serializador;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		valoresIniciales();
		serializarEnvio();

		int opcion = -1;

		do {

			System.out.println("---------------------------------");
			System.out.println("    Sistema de Administracion    ");
			System.out.println("---------------------------------");
			System.out.println("1) Gestion de alianzas estrategicas");
			System.out.println("2) Modulo de compra");
			System.out.println("3) Control de calidad");
			System.out.println("4) Logistica de envio");
			System.out.println("5) Gestion de credito");
			System.out.println("0) Salir");
			System.out.println("---------------------------------");
			System.out.print("Seleccione una opcion: ");

			try {
				Scanner stdIn = new Scanner(System.in);
				opcion = stdIn.nextInt();

			}catch(Exception e) {
				System.out.println("Error de excepcion, ingresar variable entre 0 y 5");
			}

			switch (opcion) {
			case 0: {
				System.out.println("Saliendo del sistema...");
				break;
			}
			case 1: {
				gestionAlianzasEstrategicas();
				break;
			}
			case 2: {
				menuCompra();
				break;
			}
			case 3: {
				menuControlCalidad();
				break;
			}
			case 4: {
				logisticaEnvio();
				break;
			}
			case 5: {
			gestionFinanciera();
				break;
			}
			default:
				System.out.println("Opcion fuera de rango");
			}
		}while(opcion!=0);

	}

	static void menuCompra() {

		int opcionMenuCompra = -1;

		do {

			System.out.println("---------------------------------");
			System.out.println("        Modulo de Compra         ");
			System.out.println("---------------------------------");
			System.out.println("1) Consultar Bodega");
			System.out.println("2) Realizar compra");
			System.out.println("3) Historial de compras");
			System.out.println("0) Regresar");
			System.out.println("---------------------------------");
			System.out.print("Seleccione una opcion: ");



			try {
				Scanner stdIn = new Scanner(System.in);
				opcionMenuCompra = stdIn.nextInt();

			}catch(Exception e) {
				System.out.println("Error de excepcion, ingresar variable entre 0 y 3");
			}

			switch (opcionMenuCompra) {
				case 0: {
					System.out.println("Regresando...");
					break;
				}
				case 1: {
					menuConsultarBodega();
					break;
				}
				case 2: {

					menuPedidoInteligente();
					break;
				}
				case 3: {
					historialDeCompra();
					break;
				}
				default:
					System.out.println("Opcion fuera de rango");
			}

		}while(opcionMenuCompra!=0);

	}

	static void menuConsultarBodega() {

		int opcionConsultarBodega = -1;

		do {

			System.out.println("---------------------------------");
			System.out.println("        Consultar Bodega         ");
			System.out.println("---------------------------------");
			System.out.println("1) Tienda-Laureles");
			System.out.println("2) Tienda-Poblado");
			System.out.println("3) Tienda-Envigado");
			System.out.println("0) Regresar");
			System.out.println("---------------------------------");
			System.out.print("Seleccione una opcion: ");


			Tienda tienda;
			try {
				Scanner stdIn = new Scanner(System.in);
				opcionConsultarBodega = stdIn.nextInt();

			}catch(Exception e) {
				System.out.println("Error de excepcion, ingresar variable entre 0 y 3");
			}


			switch (opcionConsultarBodega) {
				case 0: {
					System.out.println("Regresando...");
					break;
				}
				case 1: {

					tienda = (Tienda) new Deserializador("tiendaLaureles").getObj();
					System.out.println("---------------------------------");
					System.out.println("        Tienda Laureles          ");
					System.out.println("---------------------------------");
					System.out.println("Presupuesto:" +tienda.getPresupuestoCompra());
					System.out.println("Capacidad de la bodega: " +tienda.getBodega().getStopBodega()+" productos");
					System.out.println("En en la bodega de la tienda hay\n"
							+ "disponible:");
					System.out.println(tienda.getBodega().toString());
					System.out.println("Total de productos:" +tienda.getBodega().calcularNumeroProductos());


					break;
				}
				case 2: {

					tienda = (Tienda) new Deserializador("tiendaPoblado").getObj();
					System.out.println("---------------------------------");
					System.out.println("        Tienda Poblado           ");

					System.out.println("---------------------------------");
					System.out.println("Presupuesto:" +tienda.getPresupuestoCompra());
					System.out.println("Capacidad de la bodega: " +tienda.getBodega().getStopBodega()+" productos");
					System.out.println("En en la bodega de la tienda hay\n"
							+ "disponible:");
					System.out.println(tienda.getBodega().toString());;
					System.out.println("Total de productos:" +tienda.getBodega().calcularNumeroProductos());
					break;
				}
				case 3: {

					tienda = (Tienda) new Deserializador("tiendaEnvigado").getObj();
					System.out.println("---------------------------------");
					System.out.println("        Tienda Envigado          ");

					System.out.println("---------------------------------");
					System.out.println("Presupuesto:" +tienda.getPresupuestoCompra());
					System.out.println("Capacidad de la bodega: " +tienda.getBodega().getStopBodega()+" productos");
					System.out.println("En en la bodega de la tienda hay\n"
							+ "disponible:");
					System.out.println(tienda.getBodega().toString());
					System.out.println("Total de productos:" +tienda.getBodega().calcularNumeroProductos());
					break;
				}
				default:
					System.out.println("Opcion fuera de rango");
			}

		}while(opcionConsultarBodega!=0);
	}

	static void menuPedidoInteligente() {

		int opcionPedido = -1;
		String tecla = null;
		Tienda tienda = null;
		String nombreTienda = "";
		String comprasPorRevisar = (String) new Deserializador("comprasPorRevisar").getObj();


		do {

			System.out.println("---------------------------------");
			System.out.println("Seleccione tienda para crear pedido");
			System.out.println("---------------------------------");
			System.out.println("1) Tienda-Laureles");
			System.out.println("2) Tienda-Poblado");
			System.out.println("3) Tienda-Envigado");
			System.out.println("---------------------------------");
			System.out.print("Seleccione una opcion: ");



			try {
				Scanner stdIn = new Scanner(System.in);
				opcionPedido = stdIn.nextInt();


			}catch(Exception e) {
				System.out.println("Error de excepcion, ingresar variable entre 1 y 3");
			}


			if(opcionPedido<1 || opcionPedido>3)
				System.out.println("Opcion fuera de rango");

			else
				break;

		}while(true);

		switch (opcionPedido) {

			case 1: {
				tienda = (Tienda) new Deserializador("tiendaLaureles").getObj();
				nombreTienda = "Laureles";
				break;
			}
			case 2: {
				tienda = (Tienda) new Deserializador("tiendaPoblado").getObj();
				nombreTienda = "Poblado";
				break;
			}
			case 3: {
				tienda = (Tienda) new Deserializador("tiendaEnvigado").getObj();
				nombreTienda = "Envigado";
				break;
			}
		}

		Compra compra = new Compra(tienda);
		ArrayList<Producto> pedido = compra.hacerPedido(tienda);

		if(comprasPorRevisar.contains(nombreTienda))
				System.out.println("Tienes una compra por revisar en esta tienda,\nutiliza la funcionalidad de \ncontrol de calidad");

		else if(pedido==null) {
			System.out.println("No tienes espacio en bodega Para realizar un pedido de al menos 6 productos");
		}
		else if(pedido.size()<6) {
			System.out.println("No tienes suficiente presupuesto para realizar un pedido de al menos 6 productos");
		}

		else {


			System.out.println("---------------------------------");
			System.out.println("        Pedido Inteligente       ");
			System.out.println("---------------------------------");
			System.out.println("Luego de una revision exhaustiva \n"
					+ "en bodega para determinar los\nproductos que mas se necesitan \n"
					+ "y tomando en cuenta la capaci-\ndad maxima de la bodega y el\n"
					+ "presupuesto de la tienda se \nha creado un pedido inteligente\n");
			System.out.println("Se necesita:");
			System.out.println("Camisas: "+compra.getTienda().getBodega().calcularCamisas(pedido));
			System.out.println("Pantalones: "+compra.getTienda().getBodega().calcularPantalon(pedido));
			System.out.println("Abrigos: "+compra.getTienda().getBodega().calcularAbrigos(pedido));
//			System.out.println(compra.hacerPedido(tienda));
			System.out.println("\nPresione una tecla para continuar");
			System.out.println("---------------------------------");

			Scanner stdIn2 = new Scanner(System.in);
			tecla = stdIn2.next();

			procesoDeCompra(compra, nombreTienda);
		}
	}

	static void procesoDeCompra(Compra compra, String nombreTienda) {
		int opcionProveedores = -1;
		int opcionTransportista = -1;
		int contadorCompras = 0;
		String comprasPorRevisar = "";

		String recomendacion = compra.hacerOrdenDeCompra(compra.getPedido()).getNombre();
		Tienda tienda =  compra.getTienda();
		Proveedor proveedor = null;
		Transportista transportista = null;

		do {

			System.out.println("---------------------------------");
			System.out.println("    Seleccion de Proveedor       ");
			System.out.println("---------------------------------");
			System.out.println("Luego de contactar a todos los pro-\n"
					+ "veedores y analizar sus ofertas y su\n"
					+ "disponibilidad, te recomendamos al\n"
					+ "proveedor "+recomendacion+", porque ofrece el\n"
					+ "mayor descuento por la compra\n"
					+ "igualmente puede seleccionar al\n"
					+ "proveedor de su preferencia");
			System.out.println("\n1) "+compra.getProveedores().get(0).getNombre()+"\nDescuento: "+compra.getProveedores().get(0).getDescuento()+" pesos"+"\nProductos disponibles:\n"+compra.getProveedores().get(0).getBodega());
			System.out.println("\n2) "+compra.getProveedores().get(1).getNombre()+"\nDescuento: "+compra.getProveedores().get(1).getDescuento()+" pesos"+"\nProductos disponibles:\n"+compra.getProveedores().get(1).getBodega());
			System.out.println("\n3) "+compra.getProveedores().get(2).getNombre()+"\nDescuento: "+compra.getProveedores().get(2).getDescuento()+" pesos"+"\nProductos disponibles:\n2"+compra.getProveedores().get(2).getBodega());
			System.out.println("---------------------------------");
			System.out.print("Seleccione una opcion: ");


			try {

				Scanner stdIn1 = new Scanner(System.in);
				opcionProveedores = stdIn1.nextInt();

			}catch(Exception e) {
				System.out.println("Error de excepcion, ingresar variable entre 1 y 3");
			}

			if(opcionProveedores<1 || opcionProveedores>3)
				System.out.println("Fuera de rango");
			else
				break;

		}while(true);

		switch (opcionProveedores) {

			case 1: {
				proveedor = compra.getProveedores().get(0);
				break;
			}
			case 2: {
				proveedor = compra.getProveedores().get(1);
				break;
			}
			case 3: {
				proveedor = compra.getProveedores().get(2);
				break;
			}
		}
		compra.setProveedorSeleccionado(proveedor);

		String transportistaR = compra.ordenarEnvio(tienda, proveedor).getNombre();

		System.out.println("---------------------------------");
		System.out.println("      proveedor seleccionado     ");
		System.out.println("---------------------------------");
		System.out.println(proveedor.getNombre());

		do {
			System.out.println("---------------------------------");
			System.out.println("   Seleccione de Transportista   ");
			System.out.println("---------------------------------");
			System.out.println("Luego de consultar con todos los\n"
					+ "transportistas te recomendamos a\n"
					+ transportistaR +" ya que nos ofrece el mejor \n"
					+ "precio por su servicio");
			System.out.println("\n1) Trasnportista "+compra.getTransportistas().get(0).getNombre()+"\nprecio por el servicio: "+compra.getTransportistas().get(0).calcularPrecioTotal(proveedor, tienda));
			System.out.println("\n2) Trasnportista "+compra.getTransportistas().get(1).getNombre()+"\nprecio por el servicio: "+compra.getTransportistas().get(1).calcularPrecioTotal(proveedor, tienda));
			System.out.println("\n3) Trasnportista "+compra.getTransportistas().get(2).getNombre()+"\nprecio por el servicio: "+compra.getTransportistas().get(2).calcularPrecioTotal(proveedor, tienda));
			System.out.println("---------------------------------");
			System.out.print("Seleccione una opcion: ");

			try {
				Scanner stdIn2 = new Scanner(System.in);
				opcionTransportista = stdIn2.nextInt();

			}catch(Exception e) {
				System.out.println("Error de excepcion, ingresar variable entre 1 y 3");
			}

			if(opcionTransportista<1 || opcionTransportista>3)
				System.out.println("Opcion fuera de rango");
			else
				break;

		}while(true);


		switch (opcionTransportista) {

			case 1: {
				transportista = compra.getTransportistas().get(0);
				break;
			}
			case 2: {
				transportista = compra.getTransportistas().get(1);
				break;
			}
			case 3: {
				transportista = compra.getTransportistas().get(2);
				break;
			}
		}
		compra.setTransportistaSeleccionado(transportista);
		float total = proveedor.costoProductos()+transportista.calcularPrecioTotal(proveedor, tienda);
		System.out.println("---------------------------------");
		System.out.println("      Factura de la compra       ");
		System.out.println("---------------------------------");
		System.out.println("Tienda: "+nombreTienda);
		System.out.println("Proveedor: "+proveedor.getNombre());
		System.out.println("Transportista: "+transportista.getNombre());
		System.out.println("---------------------------------");
		System.out.println("Productos comprados:");
		System.out.println(proveedor.toString());
		System.out.println("Costo del envio: " + transportista.calcularPrecioTotal(proveedor, tienda));
		System.out.println("Ahorraste: "+proveedor.getDescuento()+" pesos");
		System.out.println("Total: "+ total+" pesos");
		System.out.println("---------------------------------");

		double presupuesto = tienda.getPresupuestoCompra();
		tienda.setPresupuestoCompra(presupuesto - total);
		Tienda.getCuentaTienda().setDinero(Tienda.getCuentaTienda().getDinero() - total);


		Serializador tiendaSerializada = new Serializador(tienda, "tienda"+nombreTienda);

		contadorCompras = (int) new Deserializador("contadorCompras").getObj();
		contadorCompras++;
		Serializador contadorComprasSerializado = new Serializador(contadorCompras, "contadorCompras");

		comprasPorRevisar = (String) new Deserializador("comprasPorRevisar").getObj();
		comprasPorRevisar += nombreTienda ;
		Serializador comprasPorRevisarSerializado = new Serializador(comprasPorRevisar, "comprasPorRevisar");



		Serializador compra1 = new Serializador(compra,"compra"+contadorCompras);

	}

	static void historialDeCompra() {
		int opcionHistorial = -1;
		String tecla = null;
		Tienda tienda = null;
		String nombreTienda = "";

		do {

			System.out.println("---------------------------------");
			System.out.println("      Historial de Compras       ");
			System.out.println("---------------------------------");
			System.out.println("1) Ver");
			System.out.println("0) Regresar");
			System.out.println("---------------------------------");
			System.out.print("Seleccione una opcion: ");



			try {
				Scanner stdIn = new Scanner(System.in);
				opcionHistorial = stdIn.nextInt();

			}catch(Exception e) {
				System.out.println("Error de excepcion, ingresar variable entre 1 y 3");
			}



			if(opcionHistorial==1 || opcionHistorial==0)
				break;

			else
				System.out.println("Opcion fuera de rango");


		}while(true);

		switch (opcionHistorial) {

		case 0: {
			System.out.println("Regresando...");
			break;
		}
		case 1: {

			int contadorCompras = (int) new Deserializador("contadorCompras").getObj();

			if(contadorCompras<1)
				System.out.println("No se han realizado compras");
			else
				for (int i = 1; i<contadorCompras+1;i++) {
					Compra compra = (Compra) new Deserializador("compra"+i).getObj();
					System.out.println(compra);
					System.out.println("Referencia de la compra: "+i);
					System.out.println("---------------------------------");
				}
			break;
			}
		}
	}



	static void gestionAlianzasEstrategicas() {
		Socio socioSeleccionado = null;
		int opcion=0;
		Socio socio1 = (Socio) new Deserializador("exito").getObj();
		Socio socio2 = (Socio) new Deserializador("falabella").getObj();
		Socio socio3 = (Socio) new Deserializador("primark").getObj();
		do {


			System.out.println("--------------------------------------------");
			System.out.println("Para efectuar una venta, seleccione un socio");
			System.out.println("--------------------------------------------");
			System.out.println("1) "+socio1);
			System.out.println("\n2) "+socio2);
			System.out.println("\n3) "+socio3);

			System.out.println("Opcion:");
			Scanner stdIn = new Scanner(System.in);
			opcion = stdIn.nextInt();

	}while(opcion<=0 || opcion>3);


		switch(opcion) {
		case 1:
			socioSeleccionado=socio1;
			break;
		case 2:
			socioSeleccionado=socio2;
			break;
		case 3:
			socioSeleccionado=socio3;
			break;
		}

		@SuppressWarnings("unchecked")
		ArrayList<Venta>ventasPorDefecto=(ArrayList<Venta>) new Deserializador("ventasPorDefecto").getObj();


		OfertaPorDefecto oferta=Tienda.sugerirOferta(ventasPorDefecto,socioSeleccionado);

		@SuppressWarnings("unchecked")

		ArrayList<Transportista>transportistasPorDefecto=(ArrayList<Transportista>) new Deserializador("transportistas").getObj();
		Tienda tienda1 =(Tienda) new Deserializador("tiendaPoblado").getObj();
		Tienda tienda2=(Tienda) new Deserializador("tiendaLaureles").getObj();
		Tienda tienda3=(Tienda) new Deserializador("tiendaEnvigado").getObj();

		ArrayList<Tienda> tiendasPorDefecto=new ArrayList<Tienda>();
		tiendasPorDefecto.add(tienda3);
		tiendasPorDefecto.add(tienda2);
		tiendasPorDefecto.add(tienda1);

		Transportista transportistaElegido=Transportista.mejorTransportista(transportistasPorDefecto);



		Venta confirmacionVenta=transportistaElegido.entregaEspecial(oferta,socioSeleccionado,tiendasPorDefecto);


		System.out.println("\nEl socio ha confirmado la compra: \n"+confirmacionVenta);

		Empleado contadorSeleccionado = null;


		int opcion1=0;

		ArrayList<Empleado> empleados=new ArrayList<Empleado>();

		empleados.addAll(tienda1.getEmpleados());
		empleados.addAll(tienda2.getEmpleados());
		empleados.addAll(tienda3.getEmpleados());


		do {


			System.out.println("-------------------------------------------");
			System.out.println(" Para realizar el registro contable de la  ");
			System.out.println("       venta, seleccione un contador       ");
			System.out.println("-------------------------------------------");
		    System.out.println("1) "+tienda1.gestionarPago());
			System.out.println("2) "+tienda2.gestionarPago());
			System.out.println("3) "+tienda3.gestionarPago());
			Scanner stdIn = new Scanner(System.in);
			opcion1= stdIn.nextInt();

	}while(opcion1<=0 || opcion1>3);

		switch(opcion1) {
			case 1:
				contadorSeleccionado=tienda1.gestionarPago();
				break;
			case 2:
				contadorSeleccionado=tienda2.gestionarPago();
				break;
			case 3:
				contadorSeleccionado=tienda3.gestionarPago();
				break;
		}

		Empleado archivistaTienda=(Empleado) new Deserializador("archivistaTienda").getObj();

		Informe informeVentaSocio=archivistaTienda.generarReporteVentas(confirmacionVenta, contadorSeleccionado);

		System.out.println(informeVentaSocio);



	}

	static void menuControlCalidad() {
		
		int opcionMenuCompra = 0;
		
		do {

/* 			Deserializador */
			System.out.println("----------------------------------------");
			System.out.println("      Modulo de Control de Calidad      ");
			System.out.println("----------------------------------------");
			System.out.println("1) Realizar una revision");
			System.out.println("2) Contactar al proveedor/transportista");
			System.out.println("3) Consultar bodega");
			System.out.println("4) Informe de calidad");
			System.out.println("0) Regresar");
			System.out.println("----------------------------------------");
			System.out.print("Seleccione una opcion: ");
			
			Scanner stdIn = new Scanner(System.in);
			opcionMenuCompra = stdIn.nextInt();
			
			switch (opcionMenuCompra) {
				case 0: {
					System.out.println("Regresando...");
					break;
				}
				case 1: {
					// Aquí se aplica lo del contador
					int contadorCompras = (int) new Deserializador("contadorCompras").getObj();
					ArrayList<Compra> productos = new ArrayList<Compra>();
					for (int i=1; i<contadorCompras+1;i++) {
						Compra compra = (Compra) new Deserializador("compra"+i).getObj();
						productos.add(compra);
					}
					 int cont = 1;
					for (Compra compra : productos) {
						System.out.println("---------------------------------");
						System.out.println("compra " + cont);
						System.out.println("---------------------------------");
						cont++;
						for (int i=0; i<compra.getProveedorSeleccionado().getBodega().getProductosEnBodega().size(); i++) {
							System.out.println(compra.getProveedorSeleccionado().getBodega().getProductosEnBodega().get(i));
						}
						System.out.println();
					}
					menuRevision(); 
					break;
				}
				case 2: {
					
					menuContactar();
					break;
				}
				case 3: {
					menuBodegaCl();
					break;
				}
				case 4: {
					menuInforme();
					break;
				}
				default:
					System.out.println("Opcion fuera de rango");
			}
			
		}while(opcionMenuCompra!=0);	
		

	}
	static void menuRevision() {
		
		int opcionMenuCompra = 0;
		
		do {
			System.out.println("---------------------------------");
			System.out.println("Seleccione el número de la compra");
			System.out.println("          a revisar                  ");
			System.out.println("---------------------------------");
			System.out.println("0) Regresar");
			System.out.println("---------------------------------");
			System.out.println("Seleccione una opcion:            ");
			System.out.println("---------------------------------\n");

			Scanner stdIn = new Scanner(System.in);
			opcionMenuCompra = stdIn.nextInt();
/* 			Serializador posicionCompra = new Serializador(opcionMenuCompra,"posicionCompra"); */
			
			if (opcionMenuCompra==0) {
				System.out.println("Regresando...");
				break; 
			}


			int contadorCompras = (int) new Deserializador("contadorCompras").getObj();
			ArrayList<Compra> productos = new ArrayList<Compra>();
			for (int i=1; i<contadorCompras+1;i++) {
				Compra compra = (Compra) new Deserializador("compra"+i).getObj();
				productos.add(compra);
			}

			//Obtener la compra con el numero de compra seleccionado
			Compra compra = productos.get(opcionMenuCompra-1);
			if (compra.getRevisado()) {
				System.out.println("La compra ya ha sido revisada");
				break;
			}

			ControlCalidad control = new ControlCalidad(compra);
			ArrayList<Producto> revision = control.revision(compra);
			System.out.println("---------------------------------");
			System.out.println("Revision de la compra " + opcionMenuCompra);
			System.out.println("---------------------------------");
			for (Producto p: revision) {
				System.out.println(p);
			}
			System.out.println("---------------------------------");
			compra.setRevisado(true);
			Serializador compraSerializado = new Serializador(compra,"compra"+opcionMenuCompra);
			Serializador revisionSerializado = new Serializador(revision,"revision");
			Serializador controlSerializado = new Serializador(control,"control");
			break;

		} while(opcionMenuCompra!=0);	

	}

	static void menuContactar() {
		int opcionMenuContactar = 0;
	
		do {
			ControlCalidad control = (ControlCalidad) new Deserializador("control").getObj();
	
			System.out.println("-------------------------------------");
			System.out.println("    ¿A quién desea contactar?");
			System.out.println("-------------------------------------");
			System.out.println("1) Proveedor");
			System.out.println("2) Transportista");
			System.out.println("0) Regresar");
			System.out.println("---------------------------------");
			System.out.println("Seleccione una opción: ");
	
			Scanner stdIn = new Scanner(System.in);
			opcionMenuContactar = stdIn.nextInt();

			switch(opcionMenuContactar) {
				case 0: {
					System.out.println("Regresando...");
					break;
				} case 1: {
					control.contactar(control.getProveedor());
					System.out.println("-------------------------------------");
					System.out.println("    Productos a reponer proveedor"    );
					System.out.println("-------------------------------------");
					if (control.getProductosAReponerP() == null || control.getProductosAReponerP().size() == 0) {
						System.out.println("No hay productos a reponer");
					} else {
						for (Producto p : control.getProductosAReponerP()) {
							System.out.println(p);
						}
					}
					break;
				} case 2: {
					control.contactar(control.getTransportista());
					System.out.println("-------------------------------------");
					System.out.println("   Productos a reponer transportista ");
					System.out.println("-------------------------------------");
					if (control.getProductosAReponerT() == null || control.getProductosAReponerT().size() == 0) {
						System.out.println("No hay productos a reponer");
					} else {
						for (Producto p : control.getProductosAReponerT()) {
							System.out.println(p);
						}
					}
					break;
				} default:
					System.out.println("Opcion fuera de rango");
					break;
			}
			String comprasPorRevisar = (String) new Deserializador("comprasPorRevisar").getObj();
			// Sustituir un string por otro
			comprasPorRevisar = comprasPorRevisar.replaceAll(control.getCompra().getTienda().getNombre(), "");
			Serializador comprasPorRevisarSerializado = new Serializador(comprasPorRevisar,"comprasPorRevisar");
			Serializador tienda = new Serializador(control.getCompra().getTienda(), "tienda" + control.getCompra().getTienda().getNombre());
			Serializador controlSerializado2 = new Serializador(control,"control");
		} while (opcionMenuContactar != 0);

	} 

	static void menuBodegaCl() {
		int opcionMenuBodegaCl = 0;
	
		do {
			ControlCalidad control = (ControlCalidad) new Deserializador("control").getObj();
			System.out.println("----------------------------------------");
			System.out.println("----------------------------------------");
			System.out.println("    Productos en la bodega asociada     ");
			System.out.println("              a la compra:              ");
			System.out.println("----------------------------------------");
			for (Producto p : control.getCompra().getProveedorSeleccionado().getBodega().getProductosEnBodega()) {
				System.out.println(p);
			}
			System.out.println("----------------------------------------");
			System.out.println("0) Regresar");
			System.out.println("---------------------------------");
			System.out.println("Seleccione una opción: ");

			Scanner stdIn = new Scanner(System.in);
			opcionMenuBodegaCl = stdIn.nextInt();

			if (opcionMenuBodegaCl == 0) {
				System.out.println("Regresando...");
				break;
			} else {
				System.out.println("Opcion fuera de rango");
			}

			Serializador controlSerializado2 = new Serializador(control,"control");
		} while (opcionMenuBodegaCl != 0);

	} 

	static void menuInforme() {
		
		int opcionMenuInforme = 0;
		
		do {
			ControlCalidad control = (ControlCalidad) new Deserializador("control").getObj();
			Empleado archivista = (Empleado) new Deserializador("archivistaTienda").getObj();

			System.out.println("----------------------------------------");
			System.out.println("        Generar informe de Compra        ");
			System.out.println("----------------------------------------");
			System.out.println(archivista.generarInformeControlCalidad(control, control.getProveedor(), control.getTransportista()));
			System.out.println("----------------------------------------");
			System.out.println("----------------------------------------");
			System.out.println("0) Regresar");
			System.out.println("---------------------------------");
			System.out.println("Seleccione una opción: ");

			Scanner stdIn = new Scanner(System.in);
			opcionMenuInforme = stdIn.nextInt();
			
			if (opcionMenuInforme == 0) {
				System.out.println("Regresando...");
				break;
			} else {
				System.out.println("Opcion fuera de rango");
			}
			
		}while(opcionMenuInforme!=0);
	}


	static public void valoresIniciales() {

		ArrayList<Producto> productosSocio=new ArrayList<Producto>() {{add(new Producto(Tipo.PANTALON)); add(new Producto(Tipo.ABRIGO)); add(new Producto(Tipo.ABRIGO)); add(new Producto(Tipo.CAMISA));  add(new Producto(Tipo.PANTALON));}};
		ArrayList<Producto> productosSocio2=new ArrayList<Producto>() {{add(new Producto(Tipo.PANTALON)); add(new Producto(Tipo.ABRIGO)); add(new Producto(Tipo.CAMISA));}};
		ArrayList<Producto> productosSocio3=new ArrayList<Producto>() {{add(new Producto(Tipo.PANTALON)); add(new Producto(Tipo.ABRIGO)); add(new Producto(Tipo.ABRIGO)); add(new Producto(Tipo.PANTALON));}};
		
		
		ArrayList<Producto> productos1= new ArrayList<Producto>();
		ArrayList<Producto> productos2= new ArrayList<Producto>();


		
		Banco bbva=new Banco("BBVA");
		Banco bancoAgrario=new Banco("Banco Agrario");
		Banco bbbank=new Banco("BBBank");
		Empleado contador1=new Empleado("Manuel Delgado Villegas", 5,  new CuentaBancaria(50000,Pais.COLOMBIA, bbva), 8000, Cargo.CONTADOR);
		Empleado contador2=new Empleado("Juan Diaz de Garayo", 5 ,  new CuentaBancaria(50000,Pais.COLOMBIA, bbbank), 8000, Cargo.CONTADOR);
		Empleado contador3=new Empleado("Margarita Sanchez Gutierrez", 5 ,new CuentaBancaria(50000,Pais.COLOMBIA, bbva), 8000, Cargo.CONTADOR);
		Empleado operario=new Empleado("Patricia Dagorn", 5 ,new CuentaBancaria(50000,Pais.COLOMBIA, bbva), 8000, Cargo.OPERARIO);
		Empleado archivista=new Empleado("Jose Antonio Rodriguez Vega",4,new CuentaBancaria(50000,Pais.COLOMBIA, bbva), 45621,Cargo.ARCHIVISTA);
		
		Bodega bodegaTienda1 = new Bodega(50);
		Bodega bodegaTienda2 = new Bodega(70);
		Bodega bodegaTienda3 = new Bodega(90);
		
		Producto camisa = new Producto(Tipo.CAMISA, 40000, 20000);
		Producto pantalon = new Producto(Tipo.PANTALON, 50000, 25000);
		Producto abrigo = new Producto(Tipo.ABRIGO, 60000, 30000);
		//Tienda1
		for(int i=0;i<10;i++) {
			bodegaTienda1.agregarProductos(camisa);
			productos2.add(camisa);
		}
		for(int i=0;i<5;i++) {
			bodegaTienda1.agregarProductos(pantalon);
		}
		for(int i=0;i<10;i++) {
			bodegaTienda1.agregarProductos(abrigo);
		}
		//Tienda2
		for(int i=0;i<9;i++) {
			bodegaTienda2.agregarProductos(camisa);
		}
		for(int i=0;i<8;i++) {
			bodegaTienda2.agregarProductos(pantalon);
			productos1.add(camisa);
		}
		for(int i=0;i<17;i++) {
			bodegaTienda2.agregarProductos(abrigo);
			productos1.add(abrigo);
		}
		//Tienda3
		for(int i=0;i<10;i++) {
			bodegaTienda3.agregarProductos(camisa);
		}
		for(int i=0;i<6;i++) {
			bodegaTienda3.agregarProductos(pantalon);
			productos1.add(pantalon);
		}
		for(int i=0;i<10;i++) {
			bodegaTienda3.agregarProductos(abrigo);
			productos2.add(pantalon);
		}

		Tienda tiendaLaureles = new Tienda(1000000,bodegaTienda1, contador1, operario );
		Tienda tiendaPoblado = new Tienda (1200000,bodegaTienda2, contador2);
		Tienda tiendaEnvigado = new Tienda(1100000,bodegaTienda3, contador3);
		tiendaLaureles.setNombre("Laureles");
		tiendaPoblado.setNombre("Poblado");
		tiendaEnvigado.setNombre("Envigado");

		Credito credito=Tienda.getCuentaTienda().getEntidad().generarCredito(new Credito(Tienda.getCuentaTienda(),70, Cuota.DOCE));//Necesarios para gestion financiera 
		Credito credito1=Tienda.getCuentaTienda().getEntidad().generarCredito(new Credito(Tienda.getCuentaTienda(),100, Cuota.CINCO));

		Tienda.pagarCuotaMensual(PuntajeCredito.MEDIO,credito1);
	
		Tienda.pagarCuotaMensual(PuntajeCredito.BAJO, credito1);
		Tienda.pagarCuotaMensual(PuntajeCredito.ALTO,credito1);
		Tienda.pagarCuotaMensual(PuntajeCredito.MEDIO, credito1);
		Tienda.pagarCuotaMensual(PuntajeCredito.BAJO,credito1);
		
		
		Tienda.pagarCuotaMensual(PuntajeCredito.BAJO, credito1);
		Tienda.pagarTodo(PuntajeCredito.MEDIO, credito1);
		Tienda.pagarTodo(PuntajeCredito.ALTO, credito1);

		Socio exito = new Socio("EXITO",productosSocio, new ArrayList<Venta>() {{}}, new CuentaBancaria(5000000,Pais.COLOMBIA, bbva));
		Socio falabella =  new Socio("Falabella",productosSocio2, new ArrayList<Venta>() {{}}, new CuentaBancaria(1500000,Pais.VENEZUELA, bbbank));
		Socio primark = new Socio("PRIMARK",productosSocio3, new ArrayList<Venta>() {{}}, new CuentaBancaria(5000000,Pais.BRASIL, bancoAgrario));;
		
		Bodega bodegap1 = new Bodega(100);
		Bodega bodegap2 = new Bodega(100);
		Bodega bodegap3 = new Bodega(100);
		
		CuentaBancaria cu = new CuentaBancaria(25000,Pais.COLOMBIA,bbbank);

		Proveedor proveedor1 = new Proveedor("Maria", 0, cu, bodegap1, 20000, 25000, 30000);
		Proveedor proveedor2 = new Proveedor("Carlo", 0, cu , bodegap2, 20000, 25000, 30000);
		Proveedor proveedor3 = new Proveedor("Julio", 0, cu, bodegap3, 20000, 25000, 30000);
		
		Serializador p1 = new Serializador(proveedor1, "proveedor1");
		Serializador p2 = new Serializador(proveedor2, "proveedor2");
		Serializador p3 = new Serializador(proveedor3, "proveedor3");

		Transportista transportista1 = new Transportista("Maria",1000,500,16, 2, new CuentaBancaria(25000,Pais.COLOMBIA,bbbank));
		Transportista transportista2 = new Transportista("Carlos",2000,400,15,5, new CuentaBancaria(25000,Pais.COLOMBIA,bbbank));
		Transportista transportista3 = new Transportista("Rosa",3000,300,10,3, new CuentaBancaria(25000,Pais.COLOMBIA,bbbank));
		ArrayList<Transportista> transportistasPorDefecto=new ArrayList<Transportista>();
		transportistasPorDefecto.add(transportista3);
		transportistasPorDefecto.add(transportista2);
		transportistasPorDefecto.add(transportista1);
		
		
		Venta venta1= new Venta(proveedor1, 23456, productos1, null, transportista1);
		Venta venta2=new Venta(falabella,213198,productos2,null,transportista2);
		ArrayList<Venta> ventasPorDefecto=new ArrayList<Venta>();
		ventasPorDefecto.add(venta1);
		ventasPorDefecto.add(venta2);
		Serializador ventas=new Serializador(ventasPorDefecto,"ventasPorDefecto");
		

		Serializador socio1 = new Serializador(exito, "exito");
		Serializador socio2 = new Serializador(falabella, "falabella");
		Serializador socio3 = new Serializador(primark, "primark");
		
		Serializador transportista1Serializado = new Serializador(transportista1, "transportista1");
		Serializador transportista2Serializado = new Serializador(transportista2, "transportista2");
		Serializador transportista3Serializado = new Serializador(transportista3, "transportista3");
		
		Serializador transportistas= new Serializador(transportistasPorDefecto,"transportistas");
		
		Serializador tiendaLaurelesSerializada = new Serializador(tiendaLaureles, "tiendaLaureles");
		Serializador tiendaPobladoSerializada = new Serializador(tiendaPoblado, "tiendaPoblado");
		Serializador tiendaEnvigadoSerializada = new Serializador(tiendaEnvigado, "tiendaEnvigado");

		Serializador archivistaTienda=new Serializador(archivista, "archivistaTienda");
		
		String comprasPorRevisar = "";
		Serializador c1 = new Serializador(comprasPorRevisar, "comprasPorRevisar");
		
		int contadorCompras = 0;
		Serializador contador = new Serializador(contadorCompras, "contadorCompras");
	}
	//Scanner
			static Scanner sc=new Scanner(System.in);
			static int readOpcion() {
				return sc.nextInt();
			}
			static int verificar(int n,int o) {
				do {
		            
		            o = readOpcion();

		            if (o <= 0 || o> n) {
		                System.out.println("El numero ingresado no esta en las opciones. Intenta nuevamente:");
		            }
		        } while (o <= 0 || o >n);
				return o;
			}

	public static void logisticaEnvio() {

			int o=2;
			do {
			System.out.println("---------------------------------");
			System.out.println("          //Logistica de Envio       ");
			System.out.println("---------------------------------");
			System.out.println("Seleccione uno de los sets disponibles");
			System.out.println("1. SET ONLY:CAMISA");
			System.out.println("2. SET TU:CAMISA Y PANTALON");
			System.out.println("3. SET COMPLETO:ABRIGO,CAMISA Y PANTALON");
			int Oset=verificar(3,0);
			SETS set=null;
			switch(Oset) {
			case 1: set=SETS.ONLY;
					break;
			case 2: set=SETS.TU;
					break;
			case 3: set=SETS.COMPLETO;
					break;
			}

			System.out.println();
			System.out.println("---------------------------------");
			System.out.println("          //"+set);
			System.out.println("---------------------------------");
			System.out.println("Seleccione el numero de sets que necesita:");
			System.out.println("Puede pedir minimo 1 set y maximo 5 sets");
			int cantidad=verificar(5,0);

			//Desrializar las tiendas para sacar los productos de las bodegas
			ArrayList<Tienda> tiendas=new ArrayList<Tienda>();
			Tienda tienda1=(Tienda) new Deserializador("tiendaEnvigado").getObj();
			tiendas.add(tienda1);
			Tienda tienda2=(Tienda) new Deserializador("tiendaPoblado").getObj();
			tiendas.add(tienda2);
			Tienda tienda3=(Tienda) new Deserializador("tiendaLaureles").getObj();
			tiendas.add(tienda3);

			//Primera interaccion
			ArrayList<Producto> productos=Bodega.realizarPedido(tiendas,set,cantidad);
			System.out.println(Bodega.getResumenPedido());
			if(Bodega.getResumenPedido()!="El pedido se ha completado exitosamente") {
				System.out.println("Seleccione una opcion:");
				System.out.println("1. Seguir con el proceso de Logistica de Envio con los productos disponibles");
				System.out.println("2. Volver al menu principal para ejecutar los procesos de Compra y Control de calidad\n para tener el stock necesario de productos");
				int decision=verificar(2,0);
				if(decision==2) {
					o=1;
					break;
				}
			}
			System.out.println("");

			System.out.println("---------------------------------");
			System.out.println("          //COLECCION       ");
			System.out.println("---------------------------------");
			System.out.println("Seleccione una de las colecciones disponibles:");
			int i=1;
			for(Colecciones coleccion:Colecciones.values()) {
				System.out.println(coleccion.getId()+". "+coleccion);
				i++;
			}
			int Ocoleccion= verificar(i-1,0);
			Colecciones coleccion=null;

			for(Colecciones colec:Colecciones.values()) {
				if(colec.getId()==Ocoleccion) {
					coleccion=colec;
					break;
				}
			}
			System.out.println();
			System.out.println("---------------------------------");
			System.out.println("          //"+coleccion);
			System.out.println("---------------------------------");
			//Segunda interaccion
			ArrayList<Intervenido> intervenidos=Intervenido.intervenir(productos, coleccion);
			System.out.println("Las caracterisiticas de los productos intervenidos son:");
			for(int i1=0;i1<intervenidos.size();i1++) {
				System.out.println(intervenidos.get(i1));
			}

			System.out.println();
			System.out.println("---------------------------------");
			System.out.println("  //Informacion del cliente      ");
			System.out.println("---------------------------------");
			System.out.println("Seleccione uno de los clientes registrados:");
			//Deserializar clientes
			ArrayList<Cliente> clientes=new ArrayList<Cliente>();
			for(int i1=0;i1<10;i1++) {
				Cliente cliente= (Cliente)new Deserializador("cliente"+i1).getObj();
				clientes.add(cliente);
		     }
			for(int i1=0;i1<clientes.size();i1++) {
				System.out.println(i1+1+". "+clientes.get(i1));
			}

			int Ocliente=verificar(clientes.size(),0);
			Cliente cliente=clientes.get(Ocliente-1);
			System.out.println();
			System.out.println("---------------------------------");
			System.out.println("          //"+cliente.getNombre());
			System.out.println("---------------------------------");
			System.out.println("CIUDAD DE ENVIO:"+cliente.getCiudad());
			System.out.println("Seleccione el tipo de envio:");
			System.out.println("1. Envio prioritario 5 dias habiles sobrecargo de $15.000");
			System.out.println("2. Envio normal "+cliente.getCiudad().getDias()+" dias habiles");
			System.out.println("3. Envio libre 30 dias habiles descuento de $15.000");
			int Oenvio=verificar(3,0);
			TipoEnvio tipo;
			switch(Oenvio) {
			case 1: tipo=TipoEnvio.PRIORITARIO;
			break;
			case 2: tipo=TipoEnvio.NORMAL;
			break;
			case 3: tipo=TipoEnvio.LIBRE;
			break;
			default: tipo=TipoEnvio.NORMAL;
			}

			//Tercera interaccion
			cliente.enviar(intervenidos,tipo);
			Serializador cliente1=new Serializador(cliente,"cliente"+(Ocliente-1));



			System.out.println();
			System.out.println("---------------------------------");
			System.out.println("          //Resumen de Pago");
			System.out.println("---------------------------------");
			System.out.println(cliente.getResumenDePago());

			System.out.println("\nRealizando Transferencias...");
			System.out.println(cliente.getConfirmacion());

			//Serializar las tiendas para que los productos en bodega y la cuenta bancaria queden actualizadas
			Serializador t1=new Serializador(tiendas.get(0),"tiendaEnvigado");
			Serializador t2=new Serializador(tiendas.get(1),"tiendaLaureles");
			Serializador t3=new Serializador(tiendas.get(2),"tiendaPoblado");

			System.out.println("\nSeleccione una opcion:");
			System.out.println("1. Volver al menu principal");
			System.out.println("2. Realizar nuevo proceso de Envio");


			o=verificar(2,0);



			}while(o==2);
	}
			
			public static void serializarEnvio() {
				Banco BancoElectronico=new Banco("BancoElectronico");
				CuentaBancaria cuenta1 = new CuentaBancaria(1000000, Pais.COLOMBIA, BancoElectronico);
				CuentaBancaria cuenta2 = new CuentaBancaria(2000000, Pais.BRASIL, BancoElectronico);
				CuentaBancaria cuenta3 = new CuentaBancaria(3000000, Pais.DINAMARCA, BancoElectronico);
				CuentaBancaria cuenta4 = new CuentaBancaria(4000000, Pais.ALEMANIA, BancoElectronico);
				CuentaBancaria cuenta5 = new CuentaBancaria(5000000, Pais.MONACO, BancoElectronico);
				CuentaBancaria cuenta6 = new CuentaBancaria(6000000, Pais.CHIPRE, BancoElectronico);
				CuentaBancaria cuenta7 = new CuentaBancaria(7000000, Pais.VENEZUELA, BancoElectronico);
				CuentaBancaria cuenta8 = new CuentaBancaria(8000000, Pais.COLOMBIA, BancoElectronico);
				CuentaBancaria cuenta9 = new CuentaBancaria(9000000, Pais.BRASIL, BancoElectronico);
				CuentaBancaria cuenta10 = new CuentaBancaria(10000000, Pais.DINAMARCA, BancoElectronico);

				Cliente cliente1 = new Cliente("Juan Perez", 2, Ciudades.BOGOTA, cuenta1);
		        Cliente cliente2 = new Cliente("Maria Rodriguez", 4, Ciudades.MEDELLIN, cuenta2);
		        Cliente cliente3 = new Cliente("Carlos Gomez", 1, Ciudades.CALI, cuenta3);
		        Cliente cliente4 = new Cliente("Ana Lopez", 3, Ciudades.BARRANQUILLA, cuenta4);
		        Cliente cliente5 = new Cliente("Pedro Garcia", 5, Ciudades.CARTAGENA, cuenta5);
		        Cliente cliente6 = new Cliente("Laura Martinez", 2, Ciudades.BUCARAMANGA, cuenta6);
		        Cliente cliente7 = new Cliente("Andres Herrera", 3, Ciudades.PEREIRA, cuenta7);
		        Cliente cliente8 = new Cliente("Carolina Vargas", 1, Ciudades.CUCUTA, cuenta8);
		        Cliente cliente9 = new Cliente("Luisa Fernandez", 4, Ciudades.BOGOTA, cuenta9);
		        Cliente cliente10 = new Cliente("Mario Ramirez", 2, Ciudades.MEDELLIN, cuenta10);



				ArrayList<Cliente> listaClientes = new ArrayList<>();


				listaClientes.add(cliente1);
				listaClientes.add(cliente2);
				listaClientes.add(cliente3);
				listaClientes.add(cliente4);
				listaClientes.add(cliente5);
				listaClientes.add(cliente6);
				listaClientes.add(cliente7);
				listaClientes.add(cliente8);
				listaClientes.add(cliente9);
				listaClientes.add(cliente10);

				for(int i=0;i<listaClientes.size();i++) {
					Serializador cliente=new Serializador(listaClientes.get(i),"cliente"+i);
				}
				Empleado operario1 = new Empleado("Nombre completo", 1, 234, 0, Cargo.OPERARIO, 1200000);
				Empleado operario2 = new Empleado("Nombre completo", 3, 189, 0, Cargo.OPERARIO, 1200000);
			    Empleado operario3 = new Empleado("Nombre completo", 5, 276, 0, Cargo.OPERARIO, 1200000);


				ArrayList<Empleado> listaEmpleados=new ArrayList<Empleado>();
				listaEmpleados.add(operario1);
		        listaEmpleados.add(operario2);
		        listaEmpleados.add(operario3);

		        for(int i=0;i<listaEmpleados.size();i++) {
		        	Serializador empleado=new Serializador(listaEmpleados.get(i),"operario"+i);
		        }

				Transportista transportista=new Transportista("Jimena Salgado",3);
				CuentaBancaria cuenta = new CuentaBancaria(1044450000, Pais.COLOMBIA, BancoElectronico);
				cuenta.setPropietario(transportista);
				transportista.setCuenta(cuenta);
				Serializador t= new Serializador(transportista,"TransportistaNacional");
			}


		
			static void gestionFinanciera() {
		
		
				CuentaBancaria fondoDeEmpleados = null;
				
				Empleado contadorSeleccionado = null;
				
				
				int opcion=0;
				Tienda tienda1 =(Tienda) new Deserializador("tiendaPoblado").getObj();
				Tienda tienda2=(Tienda) new Deserializador("tiendaLaureles").getObj();
				Tienda tienda3=(Tienda) new Deserializador("tiendaEnvigado").getObj();
				ArrayList<Empleado> empleados=new ArrayList<Empleado>();
				
				empleados.addAll(tienda1.getEmpleados());
				empleados.addAll(tienda2.getEmpleados());
				empleados.addAll(tienda3.getEmpleados());
				
				double cantidadSueldos=0.0;
				for(Empleado e: empleados) {
					cantidadSueldos+=e.getSueldo();
				}
				
				do {
					
					
					System.out.println("-------------------------------------------");
					System.out.println("           Seleccione un contador          ");
					System.out.println("-------------------------------------------");
					System.out.println("1) "+tienda1.gestionarPago().mostrarInformacion());
					System.out.println("2) "+tienda2.gestionarPago().mostrarInformacion());
					System.out.println("3) "+tienda3.gestionarPago().mostrarInformacion());
					Scanner stdIn = new Scanner(System.in);
					opcion = stdIn.nextInt();
					
			}while(opcion<=0 || opcion>3);	
				
				switch(opcion) {
					case 1:
						contadorSeleccionado=tienda1.gestionarPago();
						break;
					case 2:
						contadorSeleccionado=tienda2.gestionarPago();
						break;
					case 3:
						contadorSeleccionado=tienda3.gestionarPago();
						break;
						
				}
				
					Informe reporteFinanciero=contadorSeleccionado.reportarSituacion(Tienda.getCuentaTienda().getEntidad(),Tienda.getCuentaTienda());
					System.out.println(reporteFinanciero+"\n\n***El siguiente valor no ha sido descontado\ndel presupuesto total de la tienda:\n\n-Cantidad a pagar a los empleados:\n\n$"+cantidadSueldos);
					System.out.println("-------------------------------------------");
					System.out.println("   Soluciones sugeridas por la entidad     ");
					System.out.println("                  bancaria                 ");
					System.out.println("-------------------------------------------");
					System.out.println("\nEn base a la situacion financiera actual ");
					System.out.println("       la/s soluciones sugeridas son:    \n");
					
					int i=1;
					
					if(reporteFinanciero.getSoluciones().contains(SolucionesProblemaFinanciero.PAGAR_DEUDAS) && reporteFinanciero.getCantidadActualDeuda()>=Tienda.getCuentaTienda().getDinero()) {
						reporteFinanciero.getSoluciones().remove(SolucionesProblemaFinanciero.PAGAR_DEUDAS);
					}
					
					for(SolucionesProblemaFinanciero s: reporteFinanciero.getSoluciones()) {
						
						System.out.println((i++)+s.getDescripcion());
						
					}
		
				
				int opcionSolucion=0;
				do {
		
					System.out.print("Seleccione una solucion: ");
					
					Scanner stdIn = new Scanner(System.in);
					opcionSolucion = stdIn.nextInt();
					
				}while(opcionSolucion<=0 || opcionSolucion>reporteFinanciero.getSoluciones().size());
				
				Banco banco=Tienda.getCuentaTienda().getEntidad();
				
				if(reporteFinanciero.getSoluciones().get(opcionSolucion-1).equals(SolucionesProblemaFinanciero.PAGAR_DEUDAS)) {
					
					ArrayList<Credito>deudas=new ArrayList<Credito>();
					
					for(Credito c:Tienda.getCuentaTienda().getEntidad().getHistorialesCrediticios().get(Tienda.getCuentaTienda())) {
						if(c.getEstadoCredito()==Estado.PENDIENTE)
						{
							deudas.add(c);
						}	
					}
					
					ArrayList<Transferencia>confirmacionesPagos=banco.solucionarProblema(deudas,reporteFinanciero.getPuntajeCrediticioActual());
					double cantidadPagada=0.0;
					
					for(Transferencia t: confirmacionesPagos) {
						cantidadPagada+=t.getCantidad();
						
					}
					System.out.println("\nSe ha hecho una transferencia por un va-\nlor de: $"+cantidadPagada+"\n\n");
					System.out.println(String.format("Presupuesto actual de la tienda:\n$ %f\n\n",Tienda.getCuentaTienda().getDinero()));
				}
				
				else if(reporteFinanciero.getSoluciones().get(opcionSolucion-1).equals(SolucionesProblemaFinanciero.NONE)) {
					
					boolean crearFondo=true;
					try {
					for(CuentaBancaria c: Tienda.getCuentaTienda().getFondosLigados()){
						if(c.getPropietario().equals(Tienda.getCuentaTienda())) {
							crearFondo=false;
							fondoDeEmpleados=c;
							break;
						}
					}
					if(crearFondo) {
						fondoDeEmpleados=banco.solucionarProblema();
						System.out.println("Se ha creado un fondo de empleados con\nla siguiente informacion: "+fondoDeEmpleados+"\n\nRecuerde que el 10% del salario de\ncada empleado sera destinado a di-\ncho fondo.\n");
					}
					else {
						System.out.println("\nSe ha destinado un 5% del presupuesto\nactual para el fondo de empleados");
						System.out.println(banco.solucionarProblema(fondoDeEmpleados)); 
					}
					
					
				}catch(NullPointerException e){
					fondoDeEmpleados=banco.solucionarProblema();
					System.out.println("Se ha creado un fondo de empleados con\nla siguiente informacion: "+fondoDeEmpleados+"\n\nRecuerde que el 10% del salario de\ncada empleado sera destinado a di-\ncho fondo.\n");
				}
					
				}
				else if(reporteFinanciero.getSoluciones().get(opcionSolucion-1).equals(SolucionesProblemaFinanciero.PORCENTAJE_VENTAS)) {
					
					
					CuentaBancaria fondoAuxiliar=null;
					
					boolean crearFondo=true;
					//try {
					for(CuentaBancaria c: Tienda.getCuentaTienda().getFondosLigados()){
						if(c.getPropietario().equals(banco)) {
							crearFondo=false;
							fondoAuxiliar=c;
							break;
						}
					}
					if(crearFondo) {
						fondoAuxiliar=banco.solucionarProblema(reporteFinanciero.getCantidadActualDeuda());
						System.out.println("\n\nSe ha creado un fondo auxiliar al\ncual estaran destinadas las ganan-\ncias acordadas con el banco.\n\nInformacion del fondo: "+fondoAuxiliar+"\nSegun las directrices del banco,\nsu deuda se saldara una vez que\nla cantidad de dinero almacenada\nen dicho fondo supere o iguale\nla deuda actual.\n");
					}
					else {
						double abonoCuentaAuxiliar; 
						do {
						System.out.println(String.format("\nUsted ya tiene un fondo auxiliar. Des-\ntine una cantidad inferior a $ %f",Math.min(fondoAuxiliar.getCantidadLimite()-fondoAuxiliar.getDinero(), Tienda.getCuentaTienda().getDinero())));
		
						Scanner stdIn = new Scanner(System.in);
						abonoCuentaAuxiliar= stdIn.nextDouble();
						}while(abonoCuentaAuxiliar<=0 || abonoCuentaAuxiliar>Math.min(fondoAuxiliar.getCantidadLimite()-fondoAuxiliar.getDinero(), Tienda.getCuentaTienda().getDinero()));
						System.out.println(banco.abonarCuentaAuxiliar(abonoCuentaAuxiliar)); 
					}
						
				/*	
				}catch(NullPointerException e){
					fondoAuxiliar=banco.solucionarProblema(reporteFinanciero.getCantidadActualDeuda());
					System.out.println("\n\nSe ha creado un fondo auxiliar al\ncual estaran destinadas las ganan-\ncias acordadas con el banco.\n\nInformacion del fondo: "+fondoAuxiliar+"\nSegun las directrices del banco,\nsu deuda se saldara una vez que\nla cantidad de dinero almacenada\nen dicho fondo supere o iguale\nla deuda actual.\n");
				}
					*/
					
					
				}
				else if(reporteFinanciero.getSoluciones().get(opcionSolucion-1).equals(SolucionesProblemaFinanciero.SOLICITAR_CREDITO)){
					
					double opcion1;
					int opcion2;
					Cuota cuotaSeleccionada=null;
					do {
						
						
						
						System.out.println("Cantidad a solicitar(menor a 50000): ");
						Scanner stdIn = new Scanner(System.in);
						opcion1 = stdIn.nextDouble();
						System.out.println("Ingrese el numero de cuotas: \n");
						System.out.println("1) Tres\n2)Cinco\n3)Doce\n4)Dieciocho");
						opcion2=stdIn.nextInt();
					}
					while(opcion2<0 || opcion2>4 || opcion1<0 || opcion>50000);
					
					switch(opcion2) {
					
						case 1:
							cuotaSeleccionada=Cuota.TRES;
							break;
						case 2:
							cuotaSeleccionada=Cuota.CINCO;
							break;
						case 3:
							cuotaSeleccionada=Cuota.DOCE;
							break;
						case 4:
							cuotaSeleccionada=Cuota.DIECIOCHO;
							break;
					}
					banco.generarCredito(new Credito(Tienda.getCuentaTienda(),opcion1,cuotaSeleccionada));
					
				}
		
				
				System.out.println("-------------------------------------------");
				System.out.println("     MODULO DE PAGO A LOS EMPLEADOS        ");
				System.out.println("-------------------------------------------");
				System.out.println("\n                                         ");
		
				
				ArrayList<Persona> despedidos=new ArrayList<Persona>();
				
				while(cantidadSueldos>=Tienda.getCuentaTienda().getDinero()) {
					System.out.println("\nEs necesario despedir a algunos empleados.   ");
					System.out.println("\n\nSeleccione a aquellos que desea despedir:");
					Persona p;
					
				for(Empleado emp: empleados){
					int despido=0;
					Scanner stdIn = new Scanner(System.in);
					do {
						System.out.println("\n\n"+emp.mostrarInformacion()+"\n\n");
						System.out.println("1. Despedir\n2. No despedir");
						despido = stdIn.nextInt();
					}while(despido<=0 || despido>2);
					
					if(despido==1) {
						p=emp;
						despedidos.add(p);
						if(Tienda.despedir(emp, fondoDeEmpleados)==null) {
							System.out.println(p.demandar());
						}
						else {
							System.out.println(((Empleado)p).demandar(Tienda.despedir(emp, fondoDeEmpleados)));
						}
						
						
					}
					
				}
				empleados.removeAll(despedidos);
				cantidadSueldos=0;
				for(Empleado empleado: empleados) {
					cantidadSueldos+=empleado.getSueldo();
				}
				
				//System.out.println("Dinero de la Tienda: "+ Tienda.getCuentaTienda().getDinero());
				//System.out.println("PAGO: "+cantidadSueldos);
				
				
				}	
				
				if(empleados.size()!=0) {
					ArrayList<Transferencia>pagosEmpleados=new ArrayList<Transferencia>();
						System.out.println("\nAhora es posible pagar los sueldos correspon-\ndientes.");
						pagosEmpleados=Tienda.pagarSueldo(empleados,fondoDeEmpleados);
		
					for(Transferencia t:pagosEmpleados) {
						System.out.println("\n"+t+"\n");
					}
				}
				else {
					System.out.println("\n\nUSTED HA QUEBRADO!");
					tienda1.declararBancarrota();
					tienda2.declararBancarrota();
					tienda3.declararBancarrota();
				}
				
			}

}
