package gestorAplicacion.clasesBase;
import java.util.ArrayList;


public class Socio {

	private ArrayList<Producto>productosContrato;
	private String nombre;
	private ArrayList<Compra> compras;

	public Socio(ArrayList<Producto>productosContrato, String nombre, ArrayList<Compra> compras) {
		this.productosContrato=productosContrato;
		this.nombre=nombre;
		this.compras=compras;

	}

	public ArrayList<Producto> getProductosContrato() {
		return productosContrato;
	}

	public void setProductosContrato(ArrayList<Producto> productosContrato) {
		this.productosContrato = productosContrato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Compra> getCompras() {
		return compras;
	}

	public void setCompras(ArrayList<Compra> compras) {
		this.compras = compras;
	}



}
