package gestorAplicacion.clasesBase;

public class Producto {
<<<<<<< HEAD
	private String tipo, talla, color;
	private float costo;
	private String codigo = "tipo/talla/color";
	private double precio;
=======
	String tipo, talla, color;
	float costo;
	String codigo = "tipo/talla/color";
	double precio;
	private double calificacion=precio*0.1;
>>>>>>> 727ef42c551917b2ff040155fbfb4978889ca4bd

	public Producto(String tipo, String talla, String color) {
		this.tipo = tipo.toLowerCase();
		this.talla = talla.toUpperCase();
		this.color = color.toLowerCase();
		generarCodigo();
}

	public Producto(String tipo, String talla, String color, float costo) {
		this(tipo,talla,color);
		this.costo = costo;
		ponerPrecio();

	}

	public Producto(String tipo, String talla, String color, double precio) {
		this(tipo,talla,color);
		this.precio=precio;

	}
public void ponerPrecio(){

	this.precio=this.costo*1.3;

}

public void setPrecio(double precio){
	this.precio=precio;
}
public double getPrecio(){
	return this.precio;
}

	public void generarCodigo() {

//		para tipo
		if (tipo.equals("camiseta")) {codigo = codigo.replace("tipo", "0");}
		else if (tipo.equals("pantalon")) {codigo = codigo.replace("tipo", "1");}
		else if (tipo.equals("abrigo")) {codigo = codigo.replace("tipo", "2");}
		else if (tipo.equals("buzo")) {codigo = codigo.replace("tipo", "3");}
		else {codigo = codigo.replace("tipo", "4");}
//		para talla
		if (talla.equals("S")) {codigo = codigo.replace("/talla/", "0");}
		else if (talla.equals("M")) {codigo = codigo.replace("/talla/", "1");}
		else if (talla.equals("L")) {codigo = codigo.replace("/talla/", "2");}
		else {codigo = codigo.replace("/talla/", "4");}
//		para color
		if (color.equals("azul")) {codigo = codigo.replace("color", "0");}
		else if (color.equals("rojo")) {codigo = codigo.replace("color", "1");}
		else if (color.equals("negro")) {codigo = codigo.replace("color", "2");}
		else if (color.equals("blanco")) {codigo = codigo.replace("color", "3");}
		else if (color.equals("verde")) {codigo = codigo.replace("color", "4");}
	}

	public void setTipo(String valor) {tipo=valor;}
	public void setTalla(String valor) {talla=valor;}
	public void secColorTipo(String valor) {color=valor;}
	public void setCosto(float valor) {costo=valor;}
	public void setCodigo(String valor) {codigo=valor;}

	public String getTipo() {return tipo;}
	public String getTalla() {return talla;}
	public String getColor() {return color;}
	public float getCosto() {return costo;}
	public String getCodigo() {return codigo;}

	public String toString() {
		return  tipo + "  " + talla + " " + color + " " + codigo;
	}

	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	public void ganancia(double porcentaje) {
		precio=costo*porcentaje;

	}


}
