package gestorAplicacion.clasesHerencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Persona;
import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Transferencia;
import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Tienda;
import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Venta;
import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Informe;
import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Banco.SolucionesProblemaFinanciero;
import baseDatos.Deserializador;
import gestorAplicacion.clasesBase.Producto;
import gestorAplicacion.clasesBase.Persona;
import baseDatos.Serializador;







public class Empleado extends Persona implements Serializable,Comparable<Empleado>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Cargo cargo;
	private double sueldo;
	private int horasDisponibles;
	private int horasTrabajadas;

	public Empleado(String nombre, int calificacion, CuentaBancaria cuenta, double sueldo, Cargo cargo) {
		super(nombre, calificacion,cuenta);
		this.sueldo=sueldo;
		this.cargo=cargo;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

  public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Transferencia pagarContratistasPorVenta(Venta v) {

		Transferencia t;
		if(cargo.equals(Cargo.CONTADOR)){

			if(v.getComprador() instanceof Socio){
				//Le pago el 10% de la venta
				t=Tienda.getCuentaTienda().pagarDependientes(v.getRepartidor(),0.1*v.getTotal());
			}
			else {
				t=Tienda.getCuentaTienda().pagarDependientes(v.getRepartidor());
			}

			v.setGanancias(v.getTotal()-t.getCantidad());

			return t;
		}
		else {
			return null;

		}

	}

	CuentaBancaria cuentaTienda=Tienda.getCuentaTienda();


	public Informe reportarSituacion(Banco b, CuentaBancaria cuenta){
		if(cargo.equals(Cargo.CONTADOR)){
			PuntajeCredito puntajeActual=b.darPuntajeCrediticio(cuenta);

			double cantidadDeuda=0.0;

			for(Credito c:b.getHistorialesCrediticios().get(cuenta)) {
				cantidadDeuda+=(c.getCuotaBaseMensual()*(1+puntajeActual.getTasaDeInteres()/100))*(c.getCantidadCuotas()-c.getCuotasPagadas().size());
			}
			ArrayList<SolucionesProblemaFinanciero> soluciones=new ArrayList<SolucionesProblemaFinanciero>();

			if(puntajeActual.equals(PuntajeCredito.BAJO)) {

					soluciones.add(SolucionesProblemaFinanciero.PAGAR_DEUDAS);
					soluciones.add(SolucionesProblemaFinanciero.PORCENTAJE_VENTAS);

			}
			else{
				soluciones.add(SolucionesProblemaFinanciero.PAGAR_DEUDAS);
				soluciones.add(SolucionesProblemaFinanciero.SOLICITAR_CREDITO);
				 if(puntajeActual.equals(PuntajeCredito.ALTO)) {
					 soluciones.add(SolucionesProblemaFinanciero.NONE);
				 }

			}



			return new Informe(this, soluciones, puntajeActual,cantidadDeuda);

		}
		else{
			return null;
		}


	}




	public Informe generarReporteVentas(Venta v, Empleado contador) {

		if(cargo.equals(Cargo.ARCHIVISTA)){
			Transferencia t=contador.pagarContratistasPorVenta(v);
			Venta.asignarPorcentajeBanco(v);
			return new Informe(v,contador,t);
		}
		return null;
	}



	public String demandar() {

		return toString().concat("\nha decidido demandarlo por\nincumplimiento de contrato.");
	}

	public String demandar(Object object) {

		return super.demandar();
	}

	public enum Cargo{

		ARCHIVISTA,CONTADOR,OPERARIO;

	}




	public String mostrarInformacion() {
		return toString()+"\nCalificacion: "+getCalificacion()+"\nCargo: "+cargo.toString()+"\nSueldo: "+ sueldo;
	}
	//Metodo llamado en la segunda interaccion para calificar operarios
		@Override
		public int calificar() {
			if(this.cargo==Cargo.OPERARIO) {
				if(this.getCalificacion()==5) {
					horasDisponibles+=horasTrabajadas/2;
					sueldo+=100000;
					horasTrabajadas=0;
				}else {
					int calificacion=this.getCalificacion()+Math.round(horasTrabajadas/10);
					if(calificacion<5) {
						calificacion=5;
					}
					horasTrabajadas=0;
					this.setCalificacion(calificacion);
				}
			}return getCalificacion();
		}
		//Metodo llamado desde la segunda interaccion
		public static ArrayList<Empleado> seleccionarEmpleados(Cargo cargo,ArrayList<Producto> camisas,ArrayList<Producto> pantalones,ArrayList<Producto> abrigos){
			ArrayList<Empleado> Disponibles=new ArrayList<Empleado>();
			for(int i=0;i<3;i++) {
				Empleado operario=(Empleado)new Deserializador("operario"+i).getObj();
				Disponibles.add(operario);
				}
			ArrayList<Empleado> operarios=new ArrayList<Empleado>();
			Collections.sort(Disponibles);
			int Ccamisas=0;
			int Cpantalones=0;
			int Cabrigos=0;
			Empleado Operario=Disponibles.get(0);
			operarios.add(Operario);
			
			
			for(int i1=0;i1<camisas.size();i1++) {
				if(Operario.horasDisponibles==0) {
					Disponibles.remove(0);
					Operario=Disponibles.get(0);
					operarios.add(Operario);
					
				}
				if(Ccamisas>=camisas.size()) {
					break;
				}
				
				if(Operario.horasDisponibles!=0) {
					Operario.horasDisponibles-=1;
					Operario.horasTrabajadas+=1;
					Ccamisas+=1*Operario.getCalificacion();
				
				}
				
			}
			for(int i1=0;i1<pantalones.size();i1++) {
				if(Operario.horasDisponibles==0) {
					Disponibles.remove(0);
					Operario=Disponibles.get(0);
					
				}
				if(Cpantalones>=pantalones.size()) {
					break;
				}
				
				if(Operario.horasDisponibles!=0) {
					Operario.horasDisponibles-=1;
					Operario.horasTrabajadas+=1;
					Cpantalones+=(int)Math.round(0.5*Operario.getCalificacion());
				
				}
				
			}
			
			for(int i1=0;i1<abrigos.size();i1++) {
				if(Operario.horasDisponibles==0) {
					Disponibles.remove(0);
					Operario=Disponibles.get(0);
					
				}
				if(Cabrigos>=abrigos.size()) {
					break;
				}
				
				if(Operario.horasDisponibles!=0) {
					Operario.horasDisponibles-=1;
					Operario.horasTrabajadas+=1;
					Cabrigos+=(int)Math.round(0.3*Operario.getCalificacion());
				
				}
				
			}
			for(int i=0;i<operarios.size();i++) {
				operarios.get(i).calificar();
			}
			for(int i=0;i<operarios.size();i++) {
				Serializador operario=new Serializador(operarios.get(i),"operario"+i);
			}
			
			
			return operarios;
				
			}
		//Sobreescritura del compareTo para comparar empleados segun su calificacion
		@Override
		public int compareTo(Empleado o) {
			
			return Integer.compare(this.getCalificacion(), o.getCalificacion());
		}
		





}
