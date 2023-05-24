package AdministracionDeTienda.src.gestorAplicacion.clasesHerencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Persona;


//Contribuidores
//Maria Alejandra Echavarria Correa


public class Cliente extends Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ciudades ciudad;
	private CuentaBancaria cuenta;
	private double descuento;
	private int id;
	private String confirmacion="El envio no ha sido confirmado.";
	private String resumenDePago;
	
	//constructor principal
	public Cliente(String nombre, int calificacion, Ciudades ciudad,CuentaBancaria cuenta) {
		super(nombre, calificacion);
		this.ciudad = ciudad;
		this.cuenta=cuenta;
		asignarIdAleatorio();
		
	}
	
	//Enumerado para determinar el tipo de envio
	public enum TipoEnvio{
		PRIORITARIO,NORMAL,LIBRE;
	}
	
	//Enumerado de ciudades principales  indicando el numero de dias habiles en un envio normal
	public enum Ciudades{
		BOGOTA(15),MEDELLIN(10),CALI(20),BARRANQUILLA(20),CARTAGENA(16),BUCARAMANGA(17),PEREIRA(18),CUCUTA(21);
		private int dias;
		Ciudades(int i) {
			dias=i;
		}

		public int getDias() {
			return dias;
		}

		public void setDias(int dias) {
			this.dias = dias;
		}
		
		
	}



	//TERCERA INTERACCION-ENVIO
	//Calificar al cliente basado en lo que se le envia, contratar a un transportista para el envio y gestionar los pagos del cliente para confirmar el envio
	public void enviar(ArrayList<Intervenido> intervenidos, TipoEnvio tipo) {
		calificar(intervenidos);
		Transportista transportista=(Transportista) new Deserializador("TransportistaNacional").getObj();
		Transferencia pagoTransporte=transportista.envioNacional(this,intervenidos,tipo);
		double costo=0;
		for(int i=0;i<intervenidos.size();i++) {
			costo+=intervenidos.get(i).getPrecio();
		}
		resumenDePago(pagoTransporte,costo,tipo);
		ArrayList<Transferencia> transferencias=Transferencia.pagar(costo,this,pagoTransporte);
		boolean aprobado=true;
		for(int i=0;i<transferencias.size();i++) {
			if(!transferencias.get(i).isComprobante()) {
				aprobado=false;	
			}
			
		}
		if(aprobado) {
			confirmar(tipo);
		}else {
			setConfirmacion("El envio no puede ser confirmado debido a un error en las transferencias necesarias, por favor intentelo mas tarde");
		}
		
		Serializador t= new Serializador(transportista,"TransportistaNacional");	
		}
	
	//Metodo abstracto implementado para la tercera interaccion
	//Calificar al cliente basado en el numero de productos intervenidos que se le van a enviar
	public void calificar(ArrayList<Intervenido> intervenidos) {
		int calificacion=getCalificacion()+(int)(intervenidos.size()*0.1);
		if(calificacion>5) {
			calificacion=5;
		}
		setCalificacion(calificacion);
		calificar();
	}
	@Override
	//En base a la calificacion del cliente fijar un descuento para el envio
	public void calificar() {
		
		if(getCalificacion()==5) {
			setDescuento(getDescuento() + 0.05);

		}else {
			setDescuento(getCalificacion()*0.01);
			
		}
	}
	
	//Definir todo lo que el cliente debe pagar en un resumen de pago.
	public void resumenDePago(Transferencia pagoTransporte,double costo,Tipo tipo) {
	setResumenDePago("El precio de los productos intervenidos es de $"+costo+" pesos.\n"
	+ "El valor de este envio de tipo "+tipo+" a la ciudad "+getCiudad()+" es de $"+pagoTransporte.getCantidad()+" pesos.\n"
	+ "El descuento por cliente preferencial para "+getNombre()+" es del "+getDescuento()*100+"%\n*el cual ya fue aplicado en el valor de envio.\n"
	+ "El total a pagar es de $"+(costo+pagoTransporte.getCantidad())+" pesos") ;	
   }
	
	//Fijar la confirmacion del envio despues de que las transferencias fueron aprobadas
	public void confirmar( TipoEnvio tipo) {
		int diasHabiles;
		switch(tipo) {
		case LIBRE: diasHabiles=30;
			break;
		case NORMAL: diasHabiles=ciudad.getDias();
			break;
		case PRIORITARIO: diasHabiles=5;
			break;
		default: diasHabiles=10;
			break;
		
		}
		setConfirmacion("El envio ha sido confirmado llegara en un periodo de "+diasHabiles+" dias habiles.");
		
	}
	//Sobreescritura para imprimir objetos
	@Override
	public String toString() {
		return getNombre()+""+" id:"+id;
	}
	//Metodo que se llama desde el constructor para asignarle un id a los clientes
	 public void asignarIdAleatorio() {
	        Random random = new Random();
	        int idAleatorio = random.nextInt(9000) + 1000;
	        this.id = idAleatorio;
	    }




//Setters y getters
	public Ciudades getCiudad() {
		return ciudad;
	}
	public void setCiudad(Ciudades ciudad) {
		this.ciudad = ciudad;
	}

	public CuentaBancaria getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaBancaria cuenta) {
		this.cuenta = cuenta;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getResumenDePago() {
		return resumenDePago;
	}

	public void setResumenDePago(String resumenDePago) {
		this.resumenDePago = resumenDePago;
	}

	public String getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(String confirmacion) {
		this.confirmacion = confirmacion;
	}
	
	

}
