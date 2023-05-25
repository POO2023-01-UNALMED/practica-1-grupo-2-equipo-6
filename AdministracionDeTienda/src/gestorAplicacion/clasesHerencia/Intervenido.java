package gestorAplicacion.clasesHerencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import gestorAplicacion.clasesBase.Producto;
import gestorAplicacion.clasesHerencia.Empleado.Cargo;



//Colaboradores
//Maria Alejandra Echavarria Correa


public class Intervenido extends Producto  {
	private Colecciones coleccion;
	private String color;
	private String corte;
	private double precio;
	private String estampado;

	
	public Intervenido(Tipo tipo) {
		super(tipo);
	}
	//Constructor utilizado para crear objetos de tipo intervenido a partir de objetos de tipo producto
	public Intervenido(Producto producto,Colecciones coleccion) {
		super(producto.getTipo(),producto.getCosto());
		this.coleccion=coleccion;
	}
	
	//Enumerado de colecciones para fijar las caracteristicas de los intervenidos
	public enum Colecciones{
		INDUSTRIAL(1),GEOMETRIA(2),NATURALEZA(3);

		 int id;
		 Colecciones(int i) {
			id=i;
		}
		
		public int getId() {
			return id;
		}
	}
	
	//SEGUNDA INTERACCION-ENVIO
	//Se encarga de crear los objetos de tipo intervenido a partir de los una lista de prodcutos, mediante una seleccion de operarios.
	public static ArrayList<Intervenido> intervenir(ArrayList<Producto> productos,Colecciones coleccion){
		ArrayList<Intervenido> intervenidos=new ArrayList<Intervenido> ();
		ArrayList<Empleado> operarios=Empleado.seleccionarEmpleados(Cargo.OPERARIO,productos);
		ArrayList<String> paletaDeColores=Intervenido.paletaDeColores(coleccion);
		//Verificar que se tienen operarios disponibles para realizar la intervencion
		if(!operarios.isEmpty()) {
			for(int i=0;i<productos.size();i++) {
				Intervenido intervenido=new Intervenido(productos.get(i),coleccion);
				intervenido.cortar();
				intervenido.pintar(paletaDeColores);
				intervenido.estampar();
				intervenido.fijacionDePrecio();
				intervenidos.add(intervenido);
			}
			
		}
		return intervenidos;
			
		}
	
	//Se encarga de fijar una parleta de colores dependiendo de la coleccion
	public static ArrayList<String> paletaDeColores(Colecciones coleccion) {
		ArrayList<String> paleta=new ArrayList<String>();
		List<String> colores=null;
		switch(coleccion){
		case NATURALEZA: colores= Arrays.asList("VERDE","AMARILLO","CAFE","BEIGE","OLIVO","AZUL","NEGRO");
		break;
		case GEOMETRIA: colores= Arrays.asList("AZUL","NARANJADO","ROJO","VERDE","AMARILLO","VIOLETA","NEGRO");
		break;
		case INDUSTRIAL: colores= Arrays.asList("GRIS","AZUL","VIOLETA","VERDE","PLATEADO","ROJO","NEGRO");
		break;	
		}
		paleta.addAll(colores);
		return paleta;
			
		
	}
	
	//Fija el color de los productos intervenidos escogiendo aleatoriamente un color de la paleta de colores
	public void pintar(ArrayList<String> paleta) {
		Random random = new Random();
      int indiceAleatorio = random.nextInt(paleta.size());
      String color = paleta.get(indiceAleatorio);
      this.setColor(color);
		
	}
	
	//Fija un corte segun el tipo de producto y la coleccion
	public void cortar() {
		Tipo tipo=this.getTipo();
		switch(tipo) {
		case ABRIGO:
			switch(coleccion) {
			case GEOMETRIA:setCorte("Chaqueta");
				break;
			case INDUSTRIAL:setCorte("Cazadora");
				break;
			case NATURALEZA: setCorte("Chal");
				break;
			default:
				break;
			}
			break;
		case CAMISA:
			switch(coleccion) {
			case GEOMETRIA:setCorte("Corte Cuadrado");
			break;
		    case INDUSTRIAL:setCorte("Botones");
			break;
		    case NATURALEZA: setCorte("Manga Sisa");
			break;
		default:
			break;
		}
			break;
		case PANTALON:
			switch(coleccion) {
			case GEOMETRIA:setCorte("Bermuda");
			break;
		    case INDUSTRIAL:setCorte("Traje");
			break;
		    case NATURALEZA: setCorte("Short");
			break;
		default:
			break;
		}
			break;
		
		}
		
	}
	//Fija un estampado segun el tipo de producto y la coleccion
	public void estampar() {
		Tipo tipo=this.getTipo();
		switch(tipo) {
		case ABRIGO:
			switch(coleccion) {
			case GEOMETRIA:setEstampado("Cuadrado");
				break;
			case INDUSTRIAL:setEstampado("Manchas");
				break;
			case NATURALEZA: setEstampado("Hojas");
				break;
			default:
				break;
			}
			break;
		case CAMISA:
			switch(coleccion) {
			case GEOMETRIA:setEstampado("Triangulo");
				break;
			case INDUSTRIAL:setEstampado("Edificio");
				break;
			case NATURALEZA: setEstampado("Palma");
				break;
			}
			break;
		case PANTALON:
			switch(coleccion) {
			case GEOMETRIA:setEstampado("Rombos");
				break;
			case INDUSTRIAL:setEstampado("Rayas");
				break;
			case NATURALEZA: setEstampado("Flores");
				break;
			}
			break;
		
		}
		
	}
	//Fija el precio de los objetos intervenidos a partir del costo incial del producto y la coleccion
	public void fijacionDePrecio() {
		
		switch(coleccion) {
		case NATURALEZA: this.precio=this.getCosto()+200000;
		break;
		case INDUSTRIAL: this.precio=this.getCosto()+300000;
		break;
		case GEOMETRIA: this.precio=this.getCosto()+400000;
		default: this.precio=this.getCosto();
			break;
		}
	}
	
	@Override
	//Sobreescritura para imprimir los objetos
	public String toString() {
		return "*"+this.getTipo()+" color: " + color + ", corte:" + corte + ", estampado:" + estampado+ ", precio:" + precio; 
				
	}
//Setters y getters
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCorte() {
		return corte;
	}

	public void setCorte(String corte) {
		this.corte = corte;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Colecciones getColeccion() {
		return coleccion;
	}

	public void setColeccion(Colecciones coleccion) {
		this.coleccion = coleccion;
	}

	public String getEstampado() {
		return estampado;
	}

	public void setEstampado(String estampado) {
		this.estampado = estampado;
	}


	
}
