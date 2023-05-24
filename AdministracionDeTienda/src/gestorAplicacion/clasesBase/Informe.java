package gestorAplicacion.clasesBase;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Informe implements Comparable<Informe>{
	private static ArrayList<Informe> informes = new ArrayList<Informe>();
	private String codigo; 
	private TipoInforme tipoInforme;
	private ControlCalidad controlC;


	public Informe(TipoInforme tipoInforme, ControlCalidad controlCalidad) {
		;
		this.tipoInforme=tipoInforme;
		this.controlC=controlCalidad;
		
		String timestamp = ZonedDateTime.now(ZoneId.of("America/Bogota")).format(DateTimeFormatter.ofPattern("MMddyyyhhmmss"));
		
		codigo=tipoInforme.getIdentificador()+timestamp+String.valueOf(informes.size());
		

			
	}

	enum TipoInforme {
		INFORME_VENTAS("IVE"), INFORME_CONTROL_CALIDAD("CAL");

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

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public TipoInforme getTipoInforme() {
		return tipoInforme;
	}

	public void setTipoInforme(TipoInforme tipoInforme) {
		this.tipoInforme = tipoInforme;
	}

	public static ArrayList<Informe> getInformes() {
		return informes;
	}

	public static void setInformes(ArrayList<Informe> informes) {
		Informe.informes = informes;
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
		if(tipoInforme==TipoInforme.INFORME_VENTAS) {
			return String.format("Codigo: %s \nVenta: \nAcreditaciones de Pagos: ", codigo/* , ventaEfectuada, acreditacionesPagos  */);
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
		return null;//Por si es necesario añadir otro tipo de informes
	}

}