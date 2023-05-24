package gestorAplicacion.clasesHerencia;

import java.io.Serializable;
import java.util.ArrayList;
import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Persona;
import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Transferencia;
import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Tienda;
import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Venta;
import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Informe;
import AdministracionDeTienda.src.gestorAplicacion.clasesBase.Banco.SolucionesProblemaFinanciero;






public class Empleado extends Persona implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Cargo cargo;

	private double sueldo;

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

	enum Cargo{

		ARCHIVISTA,CONTADOR,OPERARIO;

	}




	public String mostrarInformacion() {
		return toString()+"\nCalificacion: "+getCalificacion()+"\nCargo: "+cargo.toString()+"\nSueldo: "+ sueldo;
	}





}
