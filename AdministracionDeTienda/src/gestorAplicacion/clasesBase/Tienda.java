package gestorAplicacion.clasesBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import gestorAplicacion.clasesBase.Banco.PuntajeCredito;
import gestorAplicacion.clasesBase.Credito.Estado;
import gestorAplicacion.clasesBase.CuentaBancaria.Pais;
import gestorAplicacion.clasesBase.Transferencia.EstadoPago;
import gestorAplicacion.clasesHerencia.Empleado;
import gestorAplicacion.clasesHerencia.Empleado.Cargo;
import gestorAplicacion.clasesHerencia.OfertaPreferencial;

public class Tienda implements Serializable {
	private double presupuestoCompras;
	private Bodega bodega;
	private int calle;
	private String nomnbre;
	private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
	private static final CuentaBancaria cuentaTienda=new CuentaBancaria(145000,Pais.COLOMBIA,new Banco("BANCOLOMBIA"));
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Tienda(double presupuesto, Bodega bodega, Empleado...empleados){
		this.presupuestoCompras = presupuesto;
		this.bodega=bodega;
		Tienda.cuentaTienda.setDinero(cuentaTienda.getDinero()+presupuesto);
		this.empleados.addAll(Arrays.asList(empleados));
	}

	public void setPresupuestoCompra(double dinero) { presupuestoCompras = dinero;}

	public double getPresupuestoCompra() {return presupuestoCompras;}

	public void setBodega(Bodega bodega) {this.bodega = bodega;}

	public Bodega getBodega(){return this.bodega;}

	public int getCalle() {return calle;}

	public void setCalle(int c) {calle = c;}

	public void setNombre(String nombre) {nomnbre = nombre;}

	public String getNombre() {return nomnbre;}


	public Empleado gestionarPago() {

		try {
		for(Empleado e: empleados) {
			if(e.getCargo()==Cargo.CONTADOR) {
				return e;
			}
		}
		}
		catch(NullPointerException e) {
			return new Empleado("ContadorPorDefecto",5, new CuentaBancaria(1500000,Pais.COLOMBIA,new Banco("BANCOLOMBIA")),17536167, Cargo.CONTADOR);
		}
		return null;
	}


	public  static CuentaBancaria getCuentaTienda() {
		return cuentaTienda;
	}

	public  ArrayList<Empleado> getEmpleados() {
		return empleados;
	}


		public static OfertaPorDefecto sugerirOferta(ArrayList<Venta> ventasPorDefecto, Socio s) {

			ArrayList<Producto>productosVendidos=new ArrayList<Producto>();

			for(Venta v: ventasPorDefecto) {
				productosVendidos.addAll(v.getProductosVenta());
			}

			OfertaPorDefecto ofertaPorFrecuenciaVentas=new OfertaPorDefecto(productosVendidos);
			OfertaPorDefecto ofertaPorPreferencias= new OfertaPreferencial(s.getProductosContrato());

			ArrayList<OfertaPorDefecto> ofertasPropuestas=new ArrayList<OfertaPorDefecto>();
			ofertasPropuestas.add(ofertaPorFrecuenciaVentas);
			ofertasPropuestas.add(ofertaPorPreferencias);
			Collections.sort(ofertasPropuestas);
			if(ofertasPropuestas.get(0).equals(ofertaPorFrecuenciaVentas)) {
				
				return ofertaPorFrecuenciaVentas;
			}
			else {
				
				return ofertaPorPreferencias;
			}
		}


			public static void pagarTodo(PuntajeCredito puntajeCrediticio,Credito credito) {
				double numero=Math.random();
				EstadoPago puntualidadPago;
				if(numero>=0.5) {
					puntualidadPago=EstadoPago.A_TIEMPO;
				}
				else {
					puntualidadPago=EstadoPago.RETRASADO;
				}

				if(credito.getEstadoCredito()!=Estado.CANCELADO) {
					credito.getCuotasPagadas().add(new Transferencia(Tienda.getCuentaTienda(),puntajeCrediticio,credito,puntualidadPago,false));
				}

				}



	public static ArrayList<Transferencia> pagarSueldo(ArrayList<Empleado> empleados, CuentaBancaria fondoDeEmpleados) {
		ArrayList<Transferencia> transferenciasPagos=new ArrayList<Transferencia>();

		if(fondoDeEmpleados!=null) {

			for(Empleado e:empleados){
				fondoDeEmpleados.setDinero(fondoDeEmpleados.getDinero()+(e.getSueldo()*0.1));
				Transferencia t= new Transferencia(Tienda.getCuentaTienda(), e.getCuenta(),(0.9*e.getSueldo()));
				transferenciasPagos.add(t);
			}

		}
		else {

			for(Empleado e:empleados){
				Transferencia t= new Transferencia(Tienda.getCuentaTienda(), e.getCuenta(),e.getSueldo());
				transferenciasPagos.add(t);
			}
		}
		return transferenciasPagos;

	}

	public static Object despedir(Empleado e,CuentaBancaria fondoDeEmpleados) {

		try {
			if(fondoDeEmpleados.getDinero()>0.10*e.getSueldo()) {
				Transferencia liquidacion=new Transferencia(Tienda.getCuentaTienda(),e.getCuenta(),0.10*e.getSueldo());
				return liquidacion;
			}
			return null;
		}catch(NullPointerException f) {
			return null;
		}
	}
	@Override
	public void finalize() {
		System.out.println("Estado de la tienda: Bancarrota");
	}




		public static void pagarCuotaMensual(PuntajeCredito puntajeCrediticio,Credito credito) {


				double numero=Math.random();
				EstadoPago puntualidadPago;


				if(numero>=0.5) {
					puntualidadPago=EstadoPago.A_TIEMPO;
				}
				else {
					puntualidadPago=EstadoPago.RETRASADO;
				}


			if(credito.getEstadoCredito()!=Estado.CANCELADO) {


				//valorPagar+=((1+puntajeCrediticio.getTasaDeInteres()/100)*credito.getCuotaBaseMensual()*(credito.getCantidadCuotas()-credito.getCuotasPagadas().size()));
				credito.getCuotasPagadas().add(new Transferencia(Tienda.getCuentaTienda(),puntajeCrediticio,credito,puntualidadPago,true));

			}


		}
		public void generarID() {
	        Random random = new Random();
	        int numero = random.nextInt(900) + 100; // Genera un número aleatorio entre 100 y 999
	        setId(numero);
	    }


}
