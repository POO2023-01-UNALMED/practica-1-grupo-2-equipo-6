package uiMain;
import java.util.Scanner;
import gestorAplicacion.clasesBase.Nomina;
import gestorAplicacion.clasesBase.Nomina.meses;

public class Interfaz {
	static Scanner sc=new Scanner(System.in);
	static long readLong() {
		return sc.nextLong();
	}
	static String readLn() {
		sc.nextLine();
		return sc.nextLine();
	}
	public static void adminNomina() {
		System.out.println("Seleccione uno de los meses disponibles\n");
		for(int mes=0;mes<Nomina.disponibles.size();mes++) {
			System.out.println(Nomina.disponibles.get(mes).getNum()+". "+Nomina.disponibles.get(mes));
			
		}
		long opcion=readLong();
		Nomina nomina = null;
		for(meses mes: meses.values()) {
			if(mes.getNum()==opcion) {
				nomina = new Nomina(mes);
				System.out.println("//"+mes);
			}
		}
	
		nomina.empleados();
		
		
		
		
	}
	

}
