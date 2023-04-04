package gestorAplicacion.clasesBase;

public class Producto {
	String tipo, talla, color;
	float costo;
	String codigo = "tipo/talla/color";
	
	public Producto(String tipo, String talla, String color, float costo) {
		this.tipo = tipo.toLowerCase();
		this.talla = talla.toUpperCase();
		this.color = color.toLowerCase();
		this.costo = costo;
		generarCodigo();
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
		else {codigo = codigo.replace("/talla/", "0");}
//		para color
		if (color.equals("azul")) {codigo = codigo.replace("color", "0");}
		else if (color.equals("rojo")) {codigo = codigo.replace("color", "1");}
		else if (color.equals("negro")) {codigo = codigo.replace("color", "2");}
		else if (color.equals("blanco")) {codigo = codigo.replace("color", "3");}
		else if (color.equals("verde")) {codigo = codigo.replace("color", "4");}
	}
	public String toString() {
		return "tipo = "+ tipo + "\ntalla = " + talla + "\ncolor = " + color + "\ncosto = " + costo + "\ncodigo = " + codigo;
	}
}