package uiMain;
import java.util.ArrayList;
import java.util.Scanner;
import gestorAplicacion.clasesBase.Nomina;
import gestorAplicacion.clasesBase.Venta;
import gestorAplicacion.clasesBase.Nomina.meses;
import gestorAplicacion.clasesHerencia.Empleado;

public class Interfaz {
	static Scanner sc=new Scanner(System.in);
	static long readLong() {
		return sc.nextLong();
	}
	static String readLn() {
		sc.nextLine();
		return sc.nextLine();
	}

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

	public static void adminNomina() {
		System.out.println("Seleccione uno de los meses disponibles\n");
		for(int mes=0;mes<Nomina.disponibles.size();mes++) {
			System.out.println(Nomina.disponibles.get(mes).getNum()+". "+Nomina.disponibles.get(mes));

		}
		long opcion=readLong();
		meses mesE=null;
		for(meses mes: meses.values()) {
			if(mes.getNum()==opcion) {
				mesE=mes;
				System.out.println("\n//"+mesE);
			}
		}
		//Aqui se iria a la clase Venta y devolveria un array de los objetos de tipo venta que se realizaron ese mes
		ArrayList<Venta> seleccionadas=new ArrayList<Venta>();
		ArrayList<Empleado> integrantes=new ArrayList<Empleado>();
		for(int i=0;i<Venta.ventas.size();i++) {
			if(Venta.ventas.get(i).getMes()==mesE) {
				seleccionadas.add(Venta.ventas.get(i));
				integrantes.add(Venta.ventas.get(i).getEmpleado());

			}
		}
		for (int i=0;i<integrantes.size();i++) {
			double sueldo=integrantes.get(i).getSueldo();
			double comision=0;
			for(int e=0;e<Venta.ventas.size();e++) {
				if(Venta.ventas.get(e).getEmpleado()==integrantes.get(i)) {
					comision+=Venta.ventas.get(e).getCalificacion();
				}
			}
			//Aqui debe ir la relacion del sueldo con la comisiones
			integrantes.get(i).setSueldo(sueldo);
		}
		System.out.println("\nLos elementos de esta nomina son:");
		System.out.println("\nEmpleado Sueldo");
		for(int i=0;i<integrantes.size();i++) {
			Empleado a = integrantes.get(i);
			System.out.println(a.getNombre()+" "+a.getSueldo());

		}




	}


}
