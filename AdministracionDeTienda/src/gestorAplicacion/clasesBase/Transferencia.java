package gestorAplicacion.clasesBase;

import java.io.Serializable;
import java.util.ArrayList;


public class Transferencia implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private double cantidad;
	private CuentaBancaria remitente;
	private Object destinatario;
	private EstadoPago puntualidadPago;



	public Transferencia(CuentaBancaria cuenta, Banco b, double cantidad){

		//LOS PAGOS QUE NO SEAN POR CREDITOS, NO VAN EN EL HISTORIAL DE PAGOS


		this.setDestinatario(b);
		this.cantidad=cantidad;
		//Agregarse al historial correspondiente



			try{

					b.getHistorialDePagos().get(cuenta). add(this);

			}
			catch(RuntimeException e){

					b.getHistorialDePagos().put(cuenta,new ArrayList<Transferencia>(){{ addAll(this);}});

			}

			cuenta.getTransferencias().add(this);


		}


	public Transferencia(CuentaBancaria cuentaDeudor, PuntajeCredito puntajeCrediticio,Credito credito, EstadoPago puntualidadPago, boolean pagarSoloMes) {

		this(cuentaDeudor, cuentaDeudor.getEntidad(),(1+puntajeCrediticio.getTasaDeInteres()/100)*credito.getCuotaBaseMensual());
		this.puntualidadPago=puntualidadPago;

		if(!pagarSoloMes && credito.getCuotasPagadas().size()!=credito.getCantidadCuotas()) {

			this.cantidad=(1+puntajeCrediticio.getTasaDeInteres()/100)*credito.getCuotaBaseMensual()*(credito.getCantidadCuotas()-credito.getCuotasPagadas().size());
			credito.setEstadoCredito(Estado.CANCELADO);
		}
		else {
			if(credito.getCuotasPagadas().size()==credito.getCantidadCuotas()) {
				credito.setEstadoCredito(Estado.CANCELADO);
				this.cantidad=0;
				cuentaDeudor.getTransferencias().remove(this);
				credito.getCuotasPagadas().remove(this);
			}
		}



		credito.setCantidadAbonada(cantidad+credito.getCantidadAbonada());
		cuentaDeudor.setDinero(cuentaDeudor.getDinero()-cantidad);



	}

	public Transferencia(CuentaBancaria remitente, CuentaBancaria destinatario, double cantidad) {

		//Me prestan
		if(destinatario.getPropietario()!=null) {
			this.setDestinatario(destinatario.getPropietario());
		}
		else {
			this.setDestinatario(destinatario);
		}

		this.setRemitente(remitente);
		this.cantidad=cantidad;
		remitente.getTransferencias().add(this);
		destinatario.getTransferencias().add(this);
		destinatario.setDinero(destinatario.getDinero()+cantidad);

	}


	public Transferencia() {
		// TODO Auto-generated constructor stub
	}


	public EstadoPago getPuntualidadPago() {
		return puntualidadPago;
	}

	public EstadoPago getPuntalidadPago() {
		return puntualidadPago;
	}

	public void setPuntualidadPago(EstadoPago estadoPago){
		puntualidadPago=estadoPago;
	}



	public double getCantidad() {
		return cantidad;
	}


	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	enum EstadoPago{

		A_TIEMPO,RETRASADO;

	}

	@Override
	public String toString() {
		String s="Transferencia efectuada.\nCantidad transferida: $"+cantidad;
		if(destinatario instanceof Persona){
			return s+ "\nDestinatario: "+ ((Persona) destinatario).getNombre();
		}
		else if(destinatario instanceof Socio) {
			return s+ "\nDestinatario: "+((Persona) destinatario).getNombre();
		}
		else if(destinatario instanceof CuentaBancaria) {
			return s+ "Destinatario: Cuenta "+((CuentaBancaria)destinatario).getIBAN();
		}
		return s;


	}


	public CuentaBancaria getRemitente() {
		return remitente;
	}


	public void setRemitente(CuentaBancaria remitente) {
		this.remitente = remitente;
	}


	public Object getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(Object destinatario) {
		this.destinatario = destinatario;
	}


}
