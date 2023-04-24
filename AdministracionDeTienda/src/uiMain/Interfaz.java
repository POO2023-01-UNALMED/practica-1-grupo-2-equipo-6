package uiMain;
import java.util.ArrayList;
import java.util.Scanner;
import gestorAplicacion.clasesBase.Nomina;
import gestorAplicacion.clasesBase.Producto;
import gestorAplicacion.clasesBase.Venta;
import gestorAplicacion.clasesBase.Nomina.meses;
import gestorAplicacion.clasesHerencia.Empleado;
import gestorAplicacion.clasesBase.Tienda;

public class Interfaz {
	static Scanner sc=new Scanner(System.in);
	static long readLong() {
		return sc.nextLong();
	}
	static String readLn() {
		sc.nextLine();
		return sc.nextLine();
	}

	public void venderSocios(Socio socio){


	}
/***
	public void crearOferta() {
			Tienda tiendaOferta;
			ArrayList<Tienda> tiendasOferta=new ArrayList<Tienda>();

			for(Tienda t: tiendas) {
				if(t.generarOferta()) {
					tiendasOferta.add(t);
				}
			}
			if (tiendasOferta.size()!=0) {
				String s="";
				Byte i=0;
				for(Tienda t: tiendasOferta) {
					i++;
					s+=String.valueOf(i)+t.getNombre()+"\n";
				}
				System.out.println("Seleccione la tienda en la cual desea crear una oferta: ");

				Byte opcion=sc.nextByte();
				do{
					System.out.println("Ingrese una opcion valida: ");
					opcion=sc.nextByte();

				}while(opcion<=0 || opcion>tiendasOferta.size());

				tiendaOferta=tiendasOferta.get(opcion-1);

				ArrayList<String> tiposDisponibles=tiendaOferta.tiposProductos();

				if(tiposDisponibles.size()!=0) {
					System.out.println("No hay productos disponibles en esta tienda");
				}
				else {
					Byte contador=0;
					for(String m:tiposDisponibles) {
						contador++;
						System.out.println(contador+". "+m);
					}
					System.out.println("Ingrese el tipo de producto al cual desea aplicar la oferta: ");
					Byte opcion2=sc.nextByte();
					do{
						System.out.println("Ingrese una opcion valida: ");
						opcion2=sc.nextByte();

					}while(opcion2<=0 || opcion2>contador);

					ArrayList<Object>mercanciaDisponibleDeTipo=tiendaOferta.getBodega().disponibilidadProductos(tiposDisponibles.get(opcion2-1));
				}
			}
		}
***/
	public static void adminNomina() {
		System.out.println("//Administración de Nómina\n");
		System.out.println("Seleccione una de las tiendas disponibles\n");
		for(int i=0;i<Tienda.tiendas.size();i++) {
			Tienda a = Tienda.tiendas.get(i);
			System.out.println(i+" "+a.getNombre());

		}
		String nombre=readLn();
		Tienda tienda=null;
		for(int i=0;i<Tienda.tiendas.size();i++) {
			if(Tienda.tiendas.get(i).getNombre()==nombre) {
				tienda=Tienda.tiendas.get(i);

			}

		}
		System.out.println("//"+tienda);
		System.out.println("Seleccione uno de los meses disponibles\n");
		for(int mes=0;mes<Tienda.disponibles.size();mes++) {
			System.out.println(Tienda.disponibles.get(mes).getNum()+". "+Tienda.disponibles.get(mes));

		}
		long opcion=readLong();
		System.out.println("Usted escogio el mes "+opcion);
		meses mesE=null;
		for(meses mes: meses.values()) {
			if(mes.getNum()==opcion) {
				mesE=mes;
				System.out.println("\n//"+mesE);
			}
		}
		//Aqui se iria a la clase Venta y devolveria un array de los objetos de tipo venta que se realizaron ese mes
		ArrayList<Venta> seleccionadas = Venta.resumenVentas(mesE,tienda);
		//Aqui se deberia hacer el llamado al producto del mes

		//Aqui nos dice quienes fueron los empleados que realizaron las ventas seleccionadas
		ArrayList<Empleado> integrantes= Venta.resumenEmpleados(seleccionadas);

		//Aqui se deberia hacer el llamado al empleado del mes
		//Aqui se recorre el arreglo de empleados para determinar cual es el suelo con comisiones de cada uno
		for (int i=0;i<integrantes.size();i++) {
			integrantes.get(i).determinacionSueldo(integrantes.get(i), seleccionadas);

		}


		System.out.println("\nLos elementos de esta nomina son:");
		System.out.println("\nEmpleado Sueldo");
		for(int i=0;i<integrantes.size();i++) {
			Empleado a = integrantes.get(i);
			System.out.println(a.getNombre()+" "+a.getSueldo());

		}
		Empleado empleadoM = null;
		Producto productoM=null;
		System.out.println("Este mes se realizaron "+seleccionadas.size()+" ventas");
		System.out.println("El vendedor del mes fue "+empleadoM);
		System.out.println("El producto más vendido fue "+productoM);


		Nomina nomina=new Nomina(tienda,mesE,empleadoM,productoM,seleccionadas);
		System.out.println(nomina.realizacionPago());
		double aumento=nomina.aumento();
		System.out.println("Se le realizo un aumento de "+aumento+" al empleado "+empleadoM.getNombre());
		double precio=nomina.precio();
		System.out.println("El nuevo precio del producto del mes es "+precio);
	}
}
