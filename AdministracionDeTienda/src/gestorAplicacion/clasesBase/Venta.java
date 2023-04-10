package gestorAplicacion.clasesBase;

import java.util.ArrayList;
import java.util.Collections;

import gestorAplicacion.clasesBase.Nomina.meses;
import gestorAplicacion.clasesHerencia.Empleado;

public class Venta {
	private ArrayList<Producto> productos;
	private Empleado empleado;
	private meses mes;
	priavte double total;
	private Tienda tienda;
	private double calificacion;
	private static ArrayList<Producto> productosVendidos;

		public static ArrayList<Producto> getProductosVendidos() {
			return productosVendidos;
		}


		public static void setProductosVendidos(ArrayList<Producto> productosVendidos) {
			Venta.productosVendidos = productosVendidos;
		}

	public void setTienda(Tienda tienda) {
		this.tienda=tienda;
	}

	public Tienda getTienda(){
		return tienda;
	}
	public ArrayList<Producto>  getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public meses getMes() {
		return mes;
	}
	public void setMes(meses mes) {
		this.mes = mes;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	//public static ArrayList<Venta> ventas=new ArrayList<Venta>();
	public Venta(ArrayList<Producto> productos, Empleado empleado,meses mes) {
		this.productos = productos;
		this.empleado = empleado;
		this.calcularTotal();
		//Calcular el total recorriendo el arreglo de productos
		this.mes=mes;
		tienda.setPresupuestoCompras(tienda.getPresupuestoCompras()+total);
		//Presupuesto.gananciaBruta+=total;
		empleado.getVentas().add(this);
		productosVendidos.addAll(productos);
	}
	public double getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	public void calcularTotal() {
		double total=0;
		for(Producto p:productos) {
			total+=p.getCosto();
		}
		setTotal(total);
	}

	public static ArrayList<Venta> informarVentas(Empleado empleado) {
		ArrayList<Venta> Ventas=new ArrayList<Venta>();

		if(empleado.getVentas().size()!=0) {
				for (Venta venta: empleado.getVentas()) {
					Ventas.add(venta);
				}
		}
			return Ventas;
	}

	public static ArrayList<Venta> informarVentas() {

		ArrayList<Venta> Ventas=new ArrayList<Venta>();
		for (Empleado empleado: Nomina.getEmpleados()) {
				Ventas.addAll(informarVentas(empleado));
		}
		return Ventas;

	}

		public static Object[] frecuenciaVentas(Producto producto) {
			int frecuenciaVentas= Collections.frequency(productosVendidos, producto);
			Object[] productoFrecuencia= {producto,frecuenciaVentas};

			return productoFrecuencia;

		}


		public ArrayList<Producto> masVendidos() {
			ArrayList <Producto> productosMasVendidos=new ArrayList<Producto>(productosVendidos);
			ArrayList<Producto> noProductosMasVendidos=new ArrayList<Producto>();
			int cantidadMaxima=Collections.frequency(productosVendidos, productosVendidos.get(0));
			Byte i=1;
			for(Producto p: productosVendidos) {
				if(Collections.frequency(productosVendidos, p)>cantidadMaxima) {
					cantidadMaxima=Collections.frequency(productosVendidos, p);
					noProductosMasVendidos.add(productosVendidos.get(i-1));
				}
				i++;
			}
			productosMasVendidos.removeAll(noProductosMasVendidos);



			return productosMasVendidos;
		}




		public ArrayList<Object> masVendidosTipo(String tipo){

			ArrayList<Producto> listaAuxiliar=new ArrayList<Producto>();
			ArrayList<Object> productosMasVendidosPorTipo=new ArrayList<Object>();
			int mayorVentasPorTipo=0;

			for(Producto p:productosVendidos) {

				if(p.getTipo()==tipo ){
					Object[] frecuencia=frecuenciaVentas(p);

					if ((int)frecuencia[1]> mayorVentasPorTipo ) {

						mayorVentasPorTipo=(int)frecuenciaVentas(p)[1];
						listaAuxiliar.clear();
						listaAuxiliar.add(p);
					}
					else if((int)frecuencia[1]==mayorVentasPorTipo) {
						listaAuxiliar.add(p);
					}
				}
			}
			productosMasVendidosPorTipo.addAll(listaAuxiliar);
			productosMasVendidosPorTipo.add(mayorVentasPorTipo);

			return productosMasVendidosPorTipo;


			}

		




}
