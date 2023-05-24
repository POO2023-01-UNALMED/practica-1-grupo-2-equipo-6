package gestorAplicacion.clasesBase;

import java.io.Serializable;
import java.util.ArrayList;

import gestorAplicacion.clasesBase.Credito.Cuota;
import gestorAplicacion.clasesHerencia.Empleado;
import gestorAplicacion.clasesHerencia.Transportista;

public class CuentaBancaria implements Serializable{


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Object propietario;
	private ArrayList<Transferencia> transferencias=new ArrayList<Transferencia>();
	private double dinero;
	private final String IBAN;
	private Pais pais;
	private Banco entidad;
	private byte digitoDeControl;
	private String numeroCuenta;
	private double cantidadLimite=-1;
	private ArrayList<CuentaBancaria> fondosLigados=new ArrayList<CuentaBancaria>();

	public CuentaBancaria(double dinero, Pais pais, Banco banco , CuentaBancaria cuentaLigada) {
		//Para crear fondos de empleados
		this(dinero, pais,banco);
		propietario=cuentaLigada;
		cuentaLigada.ligarFondo(this);
	}
	public CuentaBancaria(double dinero, Pais pais, Banco banco, double cantidadLimite, CuentaBancaria cuentaLigada) {

		//Para crear fondo auxiliar
		this(dinero, pais, banco, cuentaLigada);
		this.cantidadLimite=cantidadLimite;
		propietario=banco;

	}


	public CuentaBancaria(double dinero, Pais pais, Banco banco) {
		this.dinero=dinero;
		this.pais=pais;
		entidad=banco;
		String valorPosibleIBAN;
		do {
			this.digitoDeControl=(byte)(Math.random()*(100-10+1)+10);
			for(short i=0; i<10;i++){
				short digito= (byte)(Math.random()*9+1);
				this.numeroCuenta+=String.valueOf(digito);
			}
			valorPosibleIBAN=pais.getCodigoDePais()+String.valueOf(digitoDeControl)+numeroCuenta;

		}while(banco.autorizarCuenta(valorPosibleIBAN)==null);

		this.IBAN=valorPosibleIBAN;


		banco.getCuentas().add(this);

	}


	public Transferencia pagarDependientes(Persona t, double...pagosAdicionales) {

		double pagoExtra;
		double pago = 0;
		try {
			pagoExtra=0;
			for(double d:pagosAdicionales) {
					pagoExtra+=d;
		}
			}

		catch(NullPointerException e){
			pagoExtra=0;
		}
			if(t instanceof Transportista) {
				pago=pagoExtra+((Transportista)t).getPrecioBase();
			}
			else if(t instanceof Empleado){
				pago=pagoExtra+((Empleado)t).getSueldo();
			}
			else {
				pago=0;
			}

		dinero-=pago;
		return new Transferencia(this, t.getCuenta(),pago);

	}

	public void pagar(Compra compra){


		setDinero(dinero-compra.getProveedorSeleccionado().costoProductos());
		setDinero(dinero-compra.getTransportistaSeleccionado().calcularPrecioTotal(compra.getProveedorSeleccionado(), compra.getTienda()));

	}

	public void ligarFondo(CuentaBancaria cuentaLigar) {

		fondosLigados.add(cuentaLigar);
	}


	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getIBAN() {
		return IBAN;
	}

	public double getDinero() {
		return dinero;
	}

	public void setDinero(double dinero) {
		this.dinero = dinero;
	}

	public Banco getEntidad() {
		return entidad;
	}

	public void setEntidad(Banco entidad) {
		this.entidad = entidad;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public byte getDigitoDeControl() {
		return digitoDeControl;
	}

	public void setDigitoDeControl(byte digitoDeControl) {
		this.digitoDeControl = digitoDeControl;
	}

	public enum Pais{
	COLOMBIA("AL"),BRASIL("BR"),DINAMARCA("DK"), ALEMANIA("DE"), MONACO("MC"),CHIPRE("CY"), VENEZUELA("VZ");

	private String codigoDePais;

	private Pais(String codigoDePais) {
		this.setCodigoDePais(codigoDePais);
	}

	public String getCodigoDePais() {
		return codigoDePais;
	}

	public void setCodigoDePais(String codigoDePais) {
		this.codigoDePais = codigoDePais;
	}


	}

	public void pagar(Venta v) {

		if (this.dinero-v.getTotal()<0) {
			entidad.generarCredito(new Credito(this, v.getTotal(), Cuota.DOCE));
		}
		else {
			//PAGA SIN PROBLEMAS
			this.dinero-=v.getTotal();
		}
		Transferencia t= new Transferencia(this,Tienda.getCuentaTienda(),v.getTotal());
		transferencias.add(t);

	}

	public Transferencia pagar(Transportista t, double...pagosAdicionales) {

		double pagoExtra = 0;
		double pagoTransportista;
		try {
			for(double d:pagosAdicionales) {
					pagoExtra+=d;
		}
			}
		finally {
			pagoTransportista=pagoExtra+t.getPrecioBase();
		}

		dinero-=pagoTransportista;
		return new Transferencia(this, t.getCuenta(),pagoTransportista);





	}
	@Override
	public String toString() {

		String s=String.format("\n\nCodigo de la cuenta:\n%s\nCantidad de dinero:\n%f\n",IBAN, dinero);
		if(cantidadLimite!=-1) {
			return s+"\nCantidad que debera contener:\n\n"+String.valueOf(cantidadLimite)+"\n\n";
		}
		return s;
	}

	/***

	public static void main(String[] args) {
		Banco bbva=new Banco("BBVA");
		Empleado s=new Empleado("Carmen0",5, new CuentaBancaria(100000,Pais.COLOMBIA,new Banco("BANCOLOMBIA")),17536167, Cargo.ARCHIVISTA);
		Empleado s2=new Empleado("sdaj",11, new CuentaBancaria(121,Pais.ALEMANIA,bbva),123,Cargo.CONTADOR);

		Transportista t=new Transportista("Carmen1",5, new CuentaBancaria(12312,Pais.CHIPRE, bbva),12345);
		Transportista t1=new Transportista("Carmen2",11, new CuentaBancaria(12312,Pais.VENEZUELA, bbva),12345);
		Transportista t2=new Transportista("Carmen3",10, new CuentaBancaria(12312,Pais.ALEMANIA, bbva),12345);
		ControlCalidad c=new ControlCalidad();

		Bodega b=new Bodega(new ArrayList<Producto>(){{ add(new Producto(Tipo.ABRIGO,34567)); }});

		Tienda tienda=new Tienda(123,c,b,s,s2);

		ArrayList<Producto> pB=new ArrayList<Producto>(){{  add(new Producto(Tipo.ABRIGO,12739)); add(new Producto(Tipo.ABRIGO,126365)); add(new Producto(Tipo.CAMISA,127389)) ; add(new Producto(Tipo.PANTALON,123789)); }};
		Producto p3=pB.get(0);
		ArrayList<Producto> productos=new ArrayList<Producto>(){{add(new Producto(Tipo.ABRIGO,1));add(new Producto(Tipo.ABRIGO,0));add(new Producto(Tipo.ABRIGO,125));add(new Producto(Tipo.CAMISA,127389));add(new Producto(Tipo.PANTALON,123));add(p3);}};
		ArrayList<Producto> productosBuscados=new ArrayList<Producto>();

		for(Producto p: pB) {
			for (Producto p1:productos) {
				if(p.equals(p1)){//Modifiqué el equals para producto
					productosBuscados.add(p1);
					productos.remove(p1);
					break;
				}
			}
		}

		System.out.println("ESTE: "+productosBuscados);
		System.out.println(p3.equals(new Producto(Tipo.ABRIGO,-123)));

		ArrayList<Transportista> trs=new ArrayList<Transportista>() {{add(t);add(t2);add(t1);}};

		System.out.println(Transportista.mejorTransportista(trs));
		bbva.generarCredito(new Credito(s.getCuenta(),12345, Cuota.CINCO));
		bbva.generarCredito(new Credito(s.getCuenta(),12367,Cuota.CINCO));
		System.out.println(bbva.getHistorialesCrediticios().toString());
		System.out.println(bbva.getHistorialesCrediticios().get(s.getCuenta()).size());

		System.out.println(s2.getClass().getSimpleName());
		   ArrayList<String> list1 = new ArrayList<String>();
		      list1.add("JavaFx");
		      list1.add("Java");
		      list1.add("WebGL");
		      list1.add("OpenCV");
		      ArrayList<String> list2 = new ArrayList<String>();

		      list2.add("Java");
		      list2.add("JavaFx");
		      list2.add("WebGL");
		      list2.add("OpenCV");
		      System.out.println(list2);
		      System.out.println(pB.equals(pB));



		      int m=21;
		      int u=0;
		      int w=0;
		      try {
		    	  w=m/3;
		    	  System.out.println("PRUEBA"+ w);
		    	  System.out.println(5/u);
		    	  w=m/7;
		    	  System.out.println("Ya no ejecuta"+ w);
		      }
		      catch(ArithmeticException e) {
		    	  System.out.println("Imprime ESTO --> "+ w);
		      }



		      ControlCalidad control=new ControlCalidad();

		      Informe i=new Informe(TipoInforme.INFORME_VENTAS, Tienda.gestionarPago(),new Transferencia(t.getCuenta(),t1.getCuenta(),12345));
		      Informe i1=new Informe(TipoInforme.INFORME_VENTAS, Tienda.gestionarPago(),new Transferencia(t.getCuenta(),t1.getCuenta(),12345));
		      Collections.sort(Informe.getInformes());
		      System.out.println(Informe.getInformes());
		      Tienda tienda1=new Tienda(123,control,b,s);

		      ArrayList<Transferencia> lista1 = new ArrayList<Transferencia>();
		      lista1.add(new Transferencia(s.getCuenta(),s.getCuenta().getEntidad(),123));
		      lista1.add(new Transferencia(s.getCuenta(),s.getCuenta().getEntidad(),123));

		      s.getCuenta().getEntidad().getHistorialDePagos().put(s.getCuenta(), lista1);
		      int cantidadDePagos=s.getCuenta().getEntidad().getHistorialDePagos().get(s.getCuenta()).size();
		      System.out.println(s.getCuenta().getEntidad().getHistorialDePagos().get(s.getCuenta()));
		      ArrayList<Integer> entero=new ArrayList<Integer>();
		      System.out.println("Tamaño del arraylist "+entero.size());

		      for(int integer:entero) {
		    	  System.out.println(integer);
		      }

	}
***/

	public Object getPropietario() {
		return propietario;
	}


	public void setPropietario(Object propietario) {
		this.propietario = propietario;
	}


	public ArrayList<Transferencia> getTransferencias() {
		return transferencias;
	}


	public void setTransferencias(ArrayList<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}


	public double getCantidadLimite() {
		return cantidadLimite;
	}


	public void setCantidadLimite(double cantidadLimite) {
		this.cantidadLimite = cantidadLimite;
	}


	public ArrayList<CuentaBancaria> getFondosLigados() {
		return fondosLigados;
	}


	public void setFondosLigados(ArrayList<CuentaBancaria> fondosLigados) {
		this.fondosLigados = fondosLigados;
	}




}
