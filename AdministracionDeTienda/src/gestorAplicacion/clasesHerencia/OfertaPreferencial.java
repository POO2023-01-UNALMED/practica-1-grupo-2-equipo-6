package gestorAplicacion.clasesHerencia;

import java.io.Serializable;
import java.util.ArrayList;
import gestorAplicacion.clasesBase.OfertaPorDefecto;
import gestorAplicacion.clasesBase.Producto;
import gestorAplicacion.clasesBase.Producto.Tipo;
import gestorAplicacion.clasesBase.Inventariar;

public class OfertaPreferencial extends OfertaPorDefecto implements Serializable{

	public OfertaPreferencial(ArrayList<Producto> productosOferta) {
		super(productosOferta);

	}

	@Override
	public ArrayList<Producto> establecerOferta(ArrayList<Producto> productos) {

		ArrayList<Producto> productosOferta=new ArrayList<Producto>();
		//Le paso los del socio
		int abrigos=Inventariar.calcularAbrigos(productos);
		int camisas=Inventariar.calcularCamisas(productos);
		int pantalones=Inventariar.calcularPantalon(productos);
		int i=0;

		if(camisas>=abrigos && camisas>=pantalones) {

			do {

				productosOferta.add(new Producto(Tipo.CAMISA,0.8f*Tipo.CAMISA.getPrecioEstandar(),15000));
				i++;

			}while(i<3);

			return productosOferta;
		}
		else if(abrigos>=pantalones && abrigos>=camisas) {

			do {

				productosOferta.add(new Producto(Tipo.ABRIGO,0.8f*Tipo.ABRIGO.getPrecioEstandar(),15000));
				i++;

			}while(i<3);
			return productosOferta;
		}
		else{


			do {

				productosOferta.add(new Producto(Tipo.PANTALON,0.8*Tipo.PANTALON.getPrecioEstandar(),15000));
				i++;

			}while(i<3);
			return productosOferta;
		}



	}

}
