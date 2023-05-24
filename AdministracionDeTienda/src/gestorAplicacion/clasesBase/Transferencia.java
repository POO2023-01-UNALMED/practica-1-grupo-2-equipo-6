package gestorAplicacion.clasesBase;

import java.io.Serializable;
import java.util.ArrayList;
import gestorAplicacion.clasesBase.CuentaBancaria;
import gestorAplicacion.clasesHerencia.Cliente;




public class Transferencia implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private double cantidad;
	private CuentaBancaria remitente;
	private Object destinatario;
	private EstadoPago puntualidadPago;
	private boolean comprobante;
	private String detalle;



	public boolean isComprobante() {
		return comprobante;
	}


	public void setComprobante(boolean comprobante) {
		this.comprobante = comprobante;
	}


	public String getDetalle() {
		return detalle;
	}


	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


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
	//Metodos para envio
		public static ArrayList<Transferencia> pagar(double costo, Cliente cliente, Transferencia pagoTransporte) {
			
			double total=0;
			
			ArrayList<Transferencia> pagosPedido=Bodega.getPagos();
			for(int i=0;i<pagosPedido.size();i++) {
				total+=pagosPedido.get(i).getCantidad();
				
			}
			
			double ganancia=(costo-total)/pagosPedido.size();
			CuentaBancaria cuenta=cliente.getCuenta();
			ArrayList<Transferencia> transferencias=new ArrayList<Transferencia>();
			for(int i=0;i<pagosPedido.size();i++) {
				pagosPedido.get(i).setRemitente(cuenta);
				pagosPedido.get(i).setCantidad(pagosPedido.get(i).getCantidad()+ganancia);
				pagosPedido.get(i).realizarTransferencia();
				transferencias.add(pagosPedido.get(i));
					
			}
			ArrayList<Transferencia> pagos=new ArrayList<Transferencia>();
			Bodega.setPagos(pagos);
			
			
			
			pagoTransporte.setRemitente(cuenta);
			pagoTransporte.realizarTransferencia();
			transferencias.add(pagoTransporte);

			return transferencias;
			
			
		}
		
		public void realizarTransferencia() {
			getRemitente().setDinero(getRemitente().getDinero()-cantidad);
			((CuentaBancaria) getDestinatario()).setDinero(((CuentaBancaria) getDestinatario()).getDinero()+cantidad);
			setComprobante(true);
		}
		
		public Transferencia(CuentaBancaria cuenta, double cantidad) {
			destinatario=cuenta;
			this.cantidad=cantidad;
		}



}
