package gestorAplicacion.clasesBase;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import gestorAplicacion.clasesBase.Banco.PuntajeCredito;
import gestorAplicacion.clasesBase.Banco.SolucionesProblemaFinanciero;
import gestorAplicacion.clasesHerencia.Empleado;

public class Informe implements Comparable<Informe>{
	private Venta ventaEfectuada;
	private ArrayList<Transferencia> acreditacionesPagos;
	private ArrayList<SolucionesProblemaFinanciero> soluciones;
	/*Una vez convertido, al intentar añadir un elemento más con el
	 * método add, se genera la excepción UnsupportedOperationException.
	 * Esto se debe a que el método Arrays.asList devuelve un objeto
	 * ArrayList de una clase estática privada dentro de Arrays
	 * (java.util.Arrays.ArrayList), no de la clase java.util.ArrayList.
	 */
	private double cantidadActualDeuda;
	private Empleado contable;
	private static int informes=0;
	private final String codigo;
	private TipoInforme tipoInforme;
	private PuntajeCredito puntajeCrediticioActual;
	private ControlCalidad controlC;
	public Informe(TipoInforme tipoInforme, Empleado contable) {
			informes++;
			this.contable=contable;
			this.tipoInforme=tipoInforme;
			String timestamp = ZonedDateTime.now(ZoneId.of("America/Bogota")).format(DateTimeFormatter.ofPattern("ddMMyyyhhmmss"));
			codigo=tipoInforme.getIdentificador()+timestamp+String.valueOf(informes);
			//informes.add(this);
		}

		public Informe(TipoInforme tipoInforme,Empleado contable, Transferencia...acreditacionesPagos) {

			this(tipoInforme, contable);
			ArrayList<Transferencia> t=new ArrayList<Transferencia>(Arrays.asList(acreditacionesPagos));
			try {
			this.acreditacionesPagos.addAll(t);
			}
			catch(NullPointerException e) {
				this.acreditacionesPagos=t;
			}


		}

		public Informe(Empleado contador, ArrayList<SolucionesProblemaFinanciero>soluciones, PuntajeCredito puntajeCredito, double cantidadActualDeuda) {
			this(TipoInforme.FINANCIERO,contador);
			this.cantidadActualDeuda=cantidadActualDeuda;
			this.soluciones=soluciones;
			puntajeCrediticioActual=puntajeCredito;


		}


		public Informe(Venta v,Empleado contable, Transferencia...acreditacionesPagos) {
			this(TipoInforme.INFORME_VENTAS,contable,acreditacionesPagos);
			ventaEfectuada=v;
		}

		public Informe(TipoInforme tipoInforme, ControlCalidad controlCalidad) {

			this.tipoInforme=tipoInforme;
			this.controlC=controlCalidad;

			String timestamp = ZonedDateTime.now(ZoneId.of("America/Bogota")).format(DateTimeFormatter.ofPattern("MMddyyyhhmmss"));

			codigo=tipoInforme.getIdentificador()+timestamp+String.valueOf(informes);


		}



	public enum TipoInforme {
		INFORME_VENTAS("IVE"), INFORME_CONTROL_CALIDAD("CAL"), FINANCIERO("FIN");

		private String identificador;

		private TipoInforme(String identificador) {
			this.setIdentificador(identificador);
		}

		public String getIdentificador() {
			return identificador;
		}

		public void setIdentificador(String identificador) {
			this.identificador = identificador;
		}
	}

	public TipoInforme getTipoInforme() {
		return tipoInforme;
	}

	public void setTipoInforme(TipoInforme tipoInforme) {
		this.tipoInforme = tipoInforme;
	}


	public String getCodigo() {
		return codigo;
	}

	@Override
	public int compareTo(Informe i) {

		return this.getCodigo().compareTo(i.getCodigo());
	}

	@Override
	public String toString() {
		if(tipoInforme==TipoInforme.INFORME_VENTAS){

			String nota="Notas:";
			if(Venta.getPorcentajeBanco()!=0) {
	  nota+="\nLa tienda tiene una deuda no saldada\n"
	  		+ "con el banco, por lo cual se ha lle-\n"
	  		+ "gado al acuerdo de destinar el 20% de\n"
	  		+ "cada venta a la entidad bancaria.";
			}
			return String.format("------------------------------------------------\n"
			+"                Informe venta                   \n"
			+"------------------------------------------------\n"
			+"\n\nCodigo del Informe: %s \nContador:%s\n"
			+"Comprador:%s\n"
			+ "%s\n"
			+"Ganancias netas:$%g\n"
			+"%s",codigo,contable,ventaEfectuada.getComprador(),ventaEfectuada,ventaEfectuada.getGanancias(),nota);
		}
		else if(tipoInforme==TipoInforme.INFORME_CONTROL_CALIDAD) {
			StringBuilder report = new StringBuilder();
			report.append("\nCodigo:" ).append(codigo).append("\n");
			report.append("Tipo de informe: ").append(this.getTipoInforme()).append("\n");
			report.append("Fecha: ").append(ZonedDateTime.now(ZoneId.of("America/Bogota")).format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))).append("\n");
			report.append("Pedido original: \n");
			for (Producto p: controlC.getCompra().getPedido()) {
				report.append(p.getNombre()).append(" ").append(p.getPrecio()).append("\n");
			}
			report.append("\nProductos comprados: \n");
			for (Producto p: controlC.getCompra().getProveedorSeleccionado().getBodega().getProductosEnBodega()) {
				report.append(p.getNombre()).append(" ").append(p.getPrecio()).append("\n");
			}
			report.append("\nRevisión:\n");
			if (controlC.getRevision() == null || controlC.getRevision().isEmpty()) {
				report.append("\nNo hay productos para revisar\n");
			} else {
				for (Producto producto : controlC.getRevision()) {
					report.append(producto.getNombre()).append(" ").append(producto.getPrecio()).append("\n");
				}
			}

			if (controlC.getProductosDefectuosos() == null || controlC.getProductosDefectuosos().isEmpty()) {
				report.append("\nNo hay productos defectuosos\n");
			} else {
				report.append("\nProductos defectuosos:\n");
				for (Producto producto : controlC.getProductosDefectuosos()) {
					report.append(producto.getNombre()).append(" ").append(producto.getPrecio()).append("\n");
				}
			}

			if (controlC.getProductosAReponerP() == null || controlC.getProductosAReponerP().isEmpty()) {
				report.append("\nNo hay productos para reponer por el proveedor\n");
			} else {
				report.append("\nProductos repuestos por el proveedor:\n");
				for (Producto producto : controlC.getProductosAReponerP()) {
					report.append(producto.getNombre()).append(" ").append(producto.getPrecio()).append("\n");
				}
			}

			if (controlC.getProductosExtraviados() == null || controlC.getProductosExtraviados().isEmpty()) {
				report.append("\nNo hay productos extraviados\n");
			} else {
				report.append("\nProductos extraviados:\n");
				for (Producto producto : controlC.getProductosExtraviados()) {
					report.append(producto.getNombre()).append(" ").append(producto.getPrecio()).append("\n");
				}
			}

			if (controlC.getProductosAReponerT() == null || controlC.getProductosAReponerT().isEmpty()) {
				report.append("\nNo hay productos para reponer por el transportista\n");
			} else {
				report.append("\nProductos repuestos por el transportista:\n");
				for (Producto producto : controlC.getProductosAReponerT()) {
					report.append(producto.getNombre()).append(" ").append(producto.getPrecio()).append("\n");
				}
			}

			if (!controlC.getContactarP() && !controlC.getContactarT()) {
				report.append("\nSe debe contactar al proveedor y al transportista\n");
			} else if (!controlC.getContactarP() && controlC.getContactarT()) {
				report.append("Calificación del transportista: ").append(controlC.getTransportista().getCalificacion()).append("\n");
				report.append("\nSe debe contactar al proveedor\n");
			} else if (!controlC.getContactarT() && controlC.getContactarP()) {
				report.append("\nCalificación del proveedor: ").append(controlC.getProveedor().getCalificacion()).append("\n");
				report.append("\nSe debe contactar al transportista\n");
			} else {
				report.append("\nCalificación del proveedor: ").append(controlC.getProveedor().getCalificacion()).append("\n");
				report.append("Calificación del transportista: ").append(controlC.getTransportista().getCalificacion()).append("\n");

			}

			return report.toString();
		}
		else if(tipoInforme==TipoInforme.FINANCIERO){
			return String.format("------------------------------------------------\n"
			+"             Informe Estado Financiero          \n"
			+"------------------------------------------------\n"
			+"\n\nCodigo del Informe: %s \nContador: %s\n"
			+"Puntaje crediticio actual: %s\n"
			+"\nSe le ha asignado una tasa de interes\n"
			+ "del %% %g."
			+"\nIncluyendo intereses, la tienda tiene\n"
			+ "una deuda de $%f \n\nPresupuesto actual:\n"
			+ "%f",codigo,contable.getNombre(),
			puntajeCrediticioActual.toString(),puntajeCrediticioActual.getTasaDeInteres()
			, cantidadActualDeuda, Tienda.getCuentaTienda().getDinero());
		}

		return null;//Por si es necesario añadir otro tipo de informes
	}

	public void setPuntajeCrediticioActual(PuntajeCredito puntajeCrediticioActual) {
		this.puntajeCrediticioActual = puntajeCrediticioActual;
	}

	public double getCantidadActualDeuda() {
		return cantidadActualDeuda;
	}

	public void setCantidadActualDeuda(double cantidadActualDeuda) {
		this.cantidadActualDeuda = cantidadActualDeuda;
	}
	public ArrayList<SolucionesProblemaFinanciero> getSoluciones() {
		return soluciones;
	}

	public void setSoluciones(ArrayList<SolucionesProblemaFinanciero> soluciones) {
		this.soluciones = soluciones;
	}

	public PuntajeCredito getPuntajeCrediticioActual() {
		return puntajeCrediticioActual;
	}

}
