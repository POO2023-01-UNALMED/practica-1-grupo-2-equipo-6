package gestorAplicacion.clasesBase;
import gestorAplicacion.clasesHerencia.Transportista;


public class GestionHumana {

	public static void pagar(Compra compra,Object comprador) {
		//Se busca al transportista de la compra y se le paga

		Transportista t =compra.getTransportista();
		if(comprador instanceof Socio) {
			t.setPago(t.getPago()+ compra.getCosto()*0.25);
		}
		else {
			t.setPago(t.getPago()+compra.getCosto()*0.1);
		}

	}

}
