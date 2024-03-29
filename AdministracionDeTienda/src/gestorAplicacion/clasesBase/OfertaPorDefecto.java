package gestorAplicacion.clasesBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import gestorAplicacion.clasesBase.Producto.Tipo;

public class OfertaPorDefecto implements Comparable<OfertaPorDefecto>,Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Producto> productosOferta;
	private double total=0;


	public OfertaPorDefecto(ArrayList<Producto> productosVendidos) {
		productosOferta=establecerOferta(productosVendidos);
		for(Producto p: productosOferta) {
			total+=p.getPrecio();
		}


	}

	/*
	protected OfertaPorDefecto(int i) {
		this.i=ponerNumero(i);
		System.out.println(this.i);

	}

	public int ponerNumero(int i) {
		return i++;
	}
	*/
	public ArrayList<Producto> establecerOferta(ArrayList<Producto> productosVendidos) {
		Integer[] contadores= {0,0,0};

		Tipo tipoMenosVendido = null;
		ArrayList<Producto> listaAuxiliar= new ArrayList<Producto>();
		contadores[0]=Inventariar.calcularCamisas(productosVendidos);
		contadores[1]=Inventariar.calcularAbrigos(productosVendidos);
		contadores[2]=Inventariar.calcularPantalon(productosVendidos);
		List<Integer> ints = Arrays.asList(contadores);
		int min=Collections.min(ints);


		if(contadores[0]<=min){
				tipoMenosVendido=Tipo.CAMISA;
		}
		if(contadores[1]<=min) {
			tipoMenosVendido=Tipo.ABRIGO;
		}
		if(contadores[2]<=min) {
			tipoMenosVendido=Tipo.PANTALON;
		}

		Producto productoParaAgregar=new Producto(tipoMenosVendido,0.9f*tipoMenosVendido.getPrecioEstandar(),15000);


		for(int i=0; i<9;i++) {
		//Relleno la lista

			if(!(tipoMenosVendido.equals(Tipo.ABRIGO)) && Inventariar.calcularAbrigos(listaAuxiliar)<2 ) {

				listaAuxiliar.add(new Producto(Tipo.ABRIGO,0.9f*Tipo.ABRIGO.getPrecioEstandar(),15000));

			}
			else if(!(tipoMenosVendido.equals(Tipo.CAMISA)) && Inventariar.calcularCamisas(listaAuxiliar)<2) {
				listaAuxiliar.add(new Producto(Tipo.CAMISA,0.9f*Tipo.CAMISA.getPrecioEstandar(),15000));

			}
			else if(!(tipoMenosVendido.equals(Tipo.PANTALON)) && Inventariar.calcularPantalon(listaAuxiliar)<2) {
				listaAuxiliar.add(new Producto(Tipo.PANTALON,0.9f*Tipo.PANTALON.getPrecioEstandar(),15000));

			}
			else if(Collections.frequency(listaAuxiliar, productoParaAgregar)<5) {
				listaAuxiliar.add(productoParaAgregar);

			}

		}

		return listaAuxiliar;
	}


	public ArrayList<Producto> getProductosOferta() {
		return productosOferta;
	}

	public void setProdutosOferta(ArrayList<Producto> productosOferta){
		this.productosOferta=productosOferta;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	//La mejor oferta es aquella con menor total
		//Yo vengo antes que la otra si es NEGATIVO---ORDENA DE MENOR A MAYOR

	@Override
	public int compareTo(OfertaPorDefecto otra) {
		return (int)(this.total-otra.getTotal());
	}


}
