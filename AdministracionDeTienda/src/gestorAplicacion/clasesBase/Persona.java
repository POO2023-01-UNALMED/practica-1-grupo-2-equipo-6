package gestorAplicacion.clasesBase;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Persona implements Comparable<Persona>,Serializable{
    private int calificacion;
    private String nombre;
    private CuentaBancaria cuenta;
    
    public Persona() {
    	
    }

    public Persona(String nombre, int calificacion) {
		this.calificacion=calificacion;
		this.nombre=nombre;
	}
    public Persona(String nombre, int calificacion, CuentaBancaria cuenta){
      this(nombre,calificacion);
      this.cuenta=cuenta;
      cuenta.getEntidad().getHistorialesCrediticios().put(cuenta,new ArrayList<Credito>());
      cuenta.setPropietario(this);
    }

   

	public abstract int calificar();

    public static ArrayList<Producto> generarProductos(ArrayList<Producto> listaProductos) {
        ArrayList<Producto> productosSeleccionados = new ArrayList<>();
        Random random = new Random();
        Set<Integer> indicesSeleccionados = new HashSet<>();

        int cantidadProductos = listaProductos.size();
        int cantidadSeleccionada = random.nextInt(cantidadProductos + 1);

        while (cantidadSeleccionada > 0) {
            int indiceAleatorio = random.nextInt(cantidadProductos);
            if (!indicesSeleccionados.contains(indiceAleatorio)) {
                Producto productoSeleccionado = listaProductos.get(indiceAleatorio);
                productosSeleccionados.add(productoSeleccionado);
                indicesSeleccionados.add(indiceAleatorio);
                cantidadSeleccionada--;
            }
        }
        return productosSeleccionados;
    }

        public CuentaBancaria getCuenta(){
            return cuenta;
        }

        public void setCuenta(CuentaBancaria cuenta){
            this.cuenta = cuenta;
        }

        public String  getNombre(){
            return nombre;
        }

        public void setNombre(String nombre){
            this.nombre = nombre;
        }

        public int getCalificacion(){
            return calificacion;
        }

        public void setCalificacion(int calificacion){
            this.calificacion = calificacion;
        }

        public String toString (){
          return nombre;
        }


        public String demandar() {
      		return toString()+" ha decidido no demandarlo.\n";
      	}

      




      	@Override
      	public int compareTo(Persona otra) {
      	//La mejor oferta es aquella con menor total
      	//vengo antes que la otra si es NEGATIVO---ORDENA DE MAYOR A MENOR
      		return (int)(otra.getCalificacion()-this.calificacion);
      	}



      
}
