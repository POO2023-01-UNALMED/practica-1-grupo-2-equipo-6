package gestorAplicacion.clasesBase;

import java.util.ArrayList;

public class Nomina {
	meses mes;
	public static ArrayList<meses> disponibles=new ArrayList<meses>();
	
	public Nomina(meses mes) {
		this.mes=mes;
		disponibles.remove(mes);
		
	}
	
	public enum meses{
			ENERO(1),FEBRERO(2),MARZO(3),ABRIL(4),MAYO(5),JUNIO(6),JULIO(7),AGOSTO(8),SEPTIEMBRE(9),OCTUBRE(10),NOVIEMBRE(11),DICIEMBRE(12);

		
			private int num;
			meses(int num) {
				this.setNum(num);
			}
			public int getNum() {
				return num;
			}
			public void setNum(int num) {
				this.num = num;
			}
		
	}

}
