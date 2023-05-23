package gestorAplicacion.clasesBase;


import java.io.Serializable;
import java.util.ArrayList;

public class Socio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto>productosContrato;
	private String nombre;
	private ArrayList<Venta>renovacionesContratos;
	private final CuentaBancaria cuentaSocio;
	
	public Socio( String nombre,ArrayList<Producto>productosContrato, ArrayList<Venta> renovacionesContratos, CuentaBancaria cuenta) {
		this.productosContrato=productosContrato;
		this.nombre=nombre;
		this.renovacionesContratos=renovacionesContratos;
		cuentaSocio=cuenta;
		cuenta.getEntidad().getHistorialesCrediticios().put(cuenta, new ArrayList<Credito>());
		cuenta.setPropietario(this);
	}

	public Venta registrarVenta(OfertaPorDefecto oferta) {
		
		//Agregar un random ----- 
		
		Venta v=new Venta(this);
		
		 int valorEntero = (int) (Math.floor(Math.random()*(-9)+10));//Valor entre 0 y 10 ambos incluidos
		
		 v.setProductosVenta(productosContrato);
		 
		 if (valorEntero<=5) {
			
			 //El socio acepta la oferta
			 v.setProductosOfertados(oferta);
			 v.setTotal(oferta.getTotal());
			 if(valorEntero==5) {
				 productosContrato.addAll(oferta.getProductosOferta());
			 }
		 }
		 
		for(Producto p:v.getProductosVenta()){
			v.setTotal(v.getTotal() + p.getPrecio());
		}
		 
		cuentaSocio.pagar(v);
		renovacionesContratos.add(v);
	
			return v;
		
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

	public ArrayList<Venta> getRenovacionesContratos() {
		return renovacionesContratos;
	}

	public void setRenovacionesContratos(ArrayList<Venta> renovacionesContratos) {
		this.renovacionesContratos = renovacionesContratos;
	}

	public CuentaBancaria getCuentaSocio() {
		return cuentaSocio;
	}
	
	@Override
	public String toString() {
		String s="";
		for(Producto p:productosContrato) {
			s+=("\n"+p.toString()+"\n");
		}
		return String.format("Nombre del socio: %s\n\nProductos a vender: %s", nombre,s);
		
	}

	
	
}

