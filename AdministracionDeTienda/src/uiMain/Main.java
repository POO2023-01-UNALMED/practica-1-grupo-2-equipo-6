package uiMain;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		valoresIniciales();
		//serializarEnvio();

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
				System.out.println("Control de calidad");
				break;
			}
			case 4: {
				logisticaEnvio();
				break;
			}
			case 5: {
			//tionFinanciera();
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


	static public void valoresIniciales() {

		Banco bbva=new Banco("BBVA");
		Banco bancoAgrario=new Banco("Banco Agrario");
		Banco bbbank=new Banco("BBBank");

		ArrayList<Producto> productos1=new ArrayList<Producto>();
		ArrayList<Producto> productos2=new ArrayList<Producto>();
		ArrayList<Producto> productos3=new ArrayList<Producto>();
		for(int i=0;i<=5;i++) {



			productos1.add(new Producto(Tipo.PANTALON));
			productos1.add(new Producto(Tipo.CAMISA));
			productos1.add(new Producto(Tipo.ABRIGO));
			productos1.add(new Producto(Tipo.ABRIGO));
			productos2.add(new Producto(Tipo.PANTALON));
			productos2.add(new Producto(Tipo.CAMISA));
			productos2.add(new Producto(Tipo.ABRIGO));
			productos2.add(new Producto(Tipo.ABRIGO));
			productos3.add(new Producto(Tipo.PANTALON));
			productos3.add(new Producto(Tipo.CAMISA));
			productos3.add(new Producto(Tipo.ABRIGO));
			productos3.add(new Producto(Tipo.ABRIGO));
		}




		ArrayList<Producto> productosSocio=new ArrayList<Producto>() {{add(new Producto(Tipo.PANTALON)); add(new Producto(Tipo.ABRIGO)); add(new Producto(Tipo.ABRIGO)); add(new Producto(Tipo.CAMISA));  add(new Producto(Tipo.PANTALON));}};
		ArrayList<Producto> productosSocio2=new ArrayList<Producto>() {{add(new Producto(Tipo.PANTALON)); add(new Producto(Tipo.ABRIGO)); add(new Producto(Tipo.CAMISA));}};
		ArrayList<Producto> productosSocio3=new ArrayList<Producto>() {{add(new Producto(Tipo.PANTALON)); add(new Producto(Tipo.ABRIGO)); add(new Producto(Tipo.ABRIGO)); add(new Producto(Tipo.ABRIGO));  add(new Producto(Tipo.PANTALON));}};

		/*
		OfertaPorDefecto o1=new OfertaPorDefecto(productosSocio);
		OfertaPorDefecto o=new OfertaPreferencial(productosSocio);
		System.out.println(o.getProductosOferta()+" ESTE ES EL PRECIO POR PREFERENCIAS"+o.getTotal());
		System.out.println(o1.getProductosOferta()+ " ESTE ES EL PRECIO POR VENTAS: "+ o1.getTotal());
		ArrayList<OfertaPorDefecto> ofertasPropuestas=new ArrayList<OfertaPorDefecto>();
		ofertasPropuestas.add(o);
		ofertasPropuestas.add(o1);
		Collections.sort(ofertasPropuestas);
		System.out.println(" ESTA ES LA OFERTA CON MENOR CANTIDAD---> "+ ofertasPropuestas.get(0).getProductosOferta());
		*/

		Bodega bodegaTienda1 = new Bodega(productos1);
		Bodega bodegaTienda2 = new Bodega(productos2);
		Bodega bodegaTienda3 = new Bodega(productos3);

		ControlCalidad cc=new ControlCalidad();
		Empleado contador1=new Empleado("Manuel Delgado Villegas", 5,  new CuentaBancaria(50000,Pais.COLOMBIA, bbva), 8000, Cargo.CONTADOR);
		Empleado contador2=new Empleado("Juan Diaz de Garayo", 5 ,  new CuentaBancaria(50000,Pais.COLOMBIA, bbbank), 8000, Cargo.CONTADOR);
		Empleado contador3=new Empleado("Margarita Sanchez Gutierrez", 5 ,new CuentaBancaria(50000,Pais.COLOMBIA, bbva), 8000, Cargo.CONTADOR);
		Empleado operario=new Empleado("Patricia Dagorn", 5 ,new CuentaBancaria(50000,Pais.COLOMBIA, bbva), 8000, Cargo.OPERARIO);
		Empleado archivista=new Empleado("Jose Antonio Rodriguez Vega",4,new CuentaBancaria(50000,Pais.COLOMBIA, bbva), 45621,Cargo.ARCHIVISTA);



		Tienda tiendaLaureles = new Tienda(10234,bodegaTienda1, contador1, operario );
		Tienda tiendaPoblado = new Tienda (14326780,bodegaTienda2, contador2);
		Tienda tiendaEnvigado = new Tienda(47385,bodegaTienda3, contador3);

		Credito credito=Tienda.getCuentaTienda().getEntidad().generarCredito(new Credito(Tienda.getCuentaTienda(),70, Cuota.DOCE));//Necesarios para gestion financiera
		Credito credito1=Tienda.getCuentaTienda().getEntidad().generarCredito(new Credito(Tienda.getCuentaTienda(),100, Cuota.CINCO));

		//Transferencia transferencia=new Transferencia(Tienda.getCuentaTienda(),PuntajeCredito.ALTO,credito,false);
		//Tienda.pagarCuotaMensual(PuntajeCredito.ALTO,credito);


		Tienda.pagarCuotaMensual(PuntajeCredito.ALTO,credito1);

		Tienda.pagarCuotaMensual(PuntajeCredito.ALTO, credito1);
		Tienda.pagarCuotaMensual(PuntajeCredito.ALTO,credito1);
		Tienda.pagarCuotaMensual(PuntajeCredito.ALTO, credito1);
		Tienda.pagarCuotaMensual(PuntajeCredito.ALTO,credito1);


		Tienda.pagarCuotaMensual(PuntajeCredito.ALTO, credito1);
		Tienda.pagarTodo(PuntajeCredito.ALTO, credito1);
		Tienda.pagarTodo(PuntajeCredito.ALTO, credito1);






		Transportista transportista1 = new Transportista("Maria",123,123,123, 2, new CuentaBancaria(25000,Pais.COLOMBIA,bbbank));
		Transportista transportista2 = new Transportista("Carlos",123,123,12,5, new CuentaBancaria(25000,Pais.COLOMBIA,bbbank));
		Transportista transportista3 = new Transportista("Rosa",123,11,123,3, new CuentaBancaria(25000,Pais.COLOMBIA,bbbank));

		Serializador transportista1Serializado = new Serializador(transportista1, "transportista1");
		Serializador transportista2Serializado = new Serializador(transportista2, "transportista2");
		Serializador transportista3Serializado = new Serializador(transportista3, "transportista3");

		Socio exito = new Socio("EXITO",productosSocio, new ArrayList<Venta>() {{}}, new CuentaBancaria(5000000,Pais.COLOMBIA, bbva));
		Socio falabella =  new Socio("Falabella",productosSocio2, new ArrayList<Venta>() {{}}, new CuentaBancaria(1500000,Pais.VENEZUELA, bbbank));
		Socio primark = new Socio("PRIMARK",productosSocio3, new ArrayList<Venta>() {{}}, new CuentaBancaria(5000000,Pais.BRASIL, bancoAgrario));;

		Serializador socio1 = new Serializador(exito, "exito");
		Serializador socio2 = new Serializador(falabella, "falabella");
		Serializador socio3 = new Serializador(primark, "primark");



		Venta venta1=new Venta(primark,115000, productos1, null, transportista1);
		Venta venta2=new Venta(falabella,170000, productos2, null, transportista1);
		Venta venta3=new Venta(exito,1234567, productos1, null, transportista1);

		ArrayList<Venta> ventasPorDefecto=new ArrayList<Venta>();

		ventasPorDefecto.add(venta3);
		ventasPorDefecto.add(venta1);
		ventasPorDefecto.add(venta2);

		ArrayList<Transportista> transportistasPorDefecto=new ArrayList<Transportista>();
		transportistasPorDefecto.add(transportista3);
		transportistasPorDefecto.add(transportista2);
		transportistasPorDefecto.add(transportista1);

		Serializador transportistas=new Serializador(transportistasPorDefecto,"transportistas");
		Serializador ventas=new Serializador(ventasPorDefecto,"ventasPorDefecto");
		Serializador proveedor1Serializado = new Serializador(exito, "proveedor1");
		Serializador proveedor2Serializado = new Serializador(falabella, "proveedor2");
		Serializador proveedor3Serializado = new Serializador(primark, "proveedor3");


		Serializador tiendaLaurelesSerializada = new Serializador(tiendaLaureles, "tiendaLaureles");
		Serializador tiendaPobladoSerializada = new Serializador(tiendaPoblado, "tiendaPoblado");
		Serializador tiendaEnvigadoSerializada = new Serializador(tiendaEnvigado, "tiendaEnvigado");
		Serializador archivistaTienda=new Serializador(archivista, "archivistaTienda");
	}

}
