package gestorAplicacion.clasesBase;

import java.io.Serializable;
import java.util.ArrayList;

public class Credito implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double cantidadCredito;
	private final Banco acreedor;
	private final CuentaBancaria deudor;
	private final double cuotaBaseMensual;
	private double cantidadAbonada=0.0;
	private final int cantidadCuotas;
	private ArrayList<Transferencia> cuotasPagadas=new ArrayList<Transferencia>();
	

	


	public Credito(CuentaBancaria cuenta, double cantidad, Cuota cuotas) {
		//Constructor para socios, personas, transportistas
		cantidadCuotas=cuotas.getCantidadMeses();
		cantidadCredito=cantidad;
		acreedor=cuenta.getEntidad();
		deudor=cuenta;
		cuenta.setDinero(cuenta.getDinero()+cantidad);
		cuotaBaseMensual=cantidad/cantidadCuotas;
		//cuenta.getEntidad().generarCredito(this);
		
	}


	public void setCantidadAbonada(double cantidadAbonada) {
		this.cantidadAbonada = cantidadAbonada;
	}
	
	public double getCantidadCredito() {
		return cantidadCredito;
	}



	public void setCantidadCredito(double cantidadCredito) {
		this.cantidadCredito = cantidadCredito;
	}

	

	public Object getAcreedor() {
		return acreedor;
	}



	public CuentaBancaria getDeudor() {
		return deudor;
	}

	public double getCuotaBaseMensual() {
		return cuotaBaseMensual;
	}

	public double getCantidadAbonada() {
		return cantidadAbonada;
	}


	
	
	public int getCantidadCuotas() {
		return cantidadCuotas;
	}




	public ArrayList<Transferencia> getCuotasPagadas() {
		return cuotasPagadas;
	}


	public void setCuotasPagadas(ArrayList<Transferencia> cuotasPagadas) {
		this.cuotasPagadas = cuotasPagadas;
	}




	enum Cuota{
		DOCE(12), DIECIOCHO(18),CINCO(5), TRES(3);
		private int cantidadMeses;
		private Cuota(int cantidadMeses) {
			this.setCantidadMeses(cantidadMeses);
		}
		public int getCantidadMeses() {
			return cantidadMeses;
		}
		public void setCantidadMeses(int cantidadMeses) {
			this.cantidadMeses = cantidadMeses;
		}
		
		
	}
	
	@Override
	public String toString() {
		return "Credito a: "+ this.getDeudor().getPropietario() +" por un valor de "+cantidadCredito;
	}



	
}

