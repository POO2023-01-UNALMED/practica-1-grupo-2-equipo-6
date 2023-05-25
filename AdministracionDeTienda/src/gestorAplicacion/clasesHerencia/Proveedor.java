package gestorAplicacion.clasesHerencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import gestorAplicacion.clasesBase.*;

public class Proveedor extends Persona{
	
	private float descuento;  
	private Bodega bodega;
	private int calle;
	private float costoCamiseta, costoPantalon, costoAbrigo;
	private float calificacion;
	
	public Proveedor(String nombre, int calificar, CuentaBancaria cuenta, Bodega bodega, float camiseta, float pantalon, float abrigo) {
		super(nombre,calificar,cuenta);
		this.bodega = bodega;
		costoCamiseta = camiseta;
		costoPantalon = pantalon;
		costoAbrigo = abrigo;
	}
	
	public void verificarDisponibilidad(ArrayList<Producto> pedido) {
		
		ArrayList<Producto> productosDisponibles = new ArrayList<Producto>();
		float descuentoCamiseta = 0;
		float descuentoPantalon = 0;
		float descuentoAbrigo = 0;
		int nCamisetas = 0;
		int nPantalones = 0;
		int nAbrigos = 0;
		
		for (int i=0; i< pedido.size();i++) {
			int aleatorio = (int)(Math.random()*5+1);
			
			if (aleatorio != 3) {
				if (pedido.get(i).getNombre().equals("CAMISA")) {
					nCamisetas++;
//					pedido.get(i).setCosto(costoCamiseta);
					if (aleatorio == 4) {descuentoCamiseta=descuentoCamiseta+2000f;}
					
				}
				else if(pedido.get(i).getNombre().equals("PANTALON")) {
					nPantalones++;
//					pedido.get(i).setCosto(costoPantalon);
					if (aleatorio == 4) {descuentoPantalon=descuentoPantalon+2500f;}
				}
				else {
//					pedido.get(i).setCosto(costoAbrigo);
					nAbrigos++;
					if (aleatorio == 4) {descuentoAbrigo=descuentoAbrigo+3000f;}
				}
				
				productosDisponibles.add(pedido.get(i));
				}
		}
		
		for (int i=0; i< productosDisponibles.size();i++) {
			if (productosDisponibles.get(i).getNombre().equals("CAMISA")) {
				productosDisponibles.get(i).setCosto(costoCamiseta-(descuentoCamiseta/nCamisetas));
				setCostoCamiseta(costoCamiseta-(descuentoCamiseta/nCamisetas));
			}
			else if(productosDisponibles.get(i).getNombre().equals("PANTALON")) {
				productosDisponibles.get(i).setCosto(costoPantalon-(descuentoPantalon/nPantalones));
				setCostoPantalon(costoPantalon-(descuentoPantalon/nPantalones));
			}
			else {
				productosDisponibles.get(i).setCosto(costoAbrigo-(descuentoAbrigo/nAbrigos));
				setCostoAbrigo(costoAbrigo-(descuentoAbrigo/nAbrigos));
			}
			this.descuento = descuentoCamiseta + descuentoPantalon + descuentoAbrigo;
		}
		
		bodega.setProductosEnBodega(productosDisponibles);
	}
	
	public  int calificar() {
		return 5;
	}

	public ArrayList<Producto> pDefectuososAReponer(ControlCalidad control) {
        ArrayList<Producto> productosAReponer = new ArrayList<>();
        ArrayList<Producto> listaProductos = control.getProductosDefectuosos();
        Random random = new Random();
        Set<Integer> indicesSeleccionados = new HashSet<>();

        int cantidadProductos = listaProductos.size();
        int cantidadSeleccionada = random.nextInt(cantidadProductos + 1);

        while (cantidadSeleccionada > 0) {
            int indiceAleatorio = random.nextInt(cantidadProductos);
            if (!indicesSeleccionados.contains(indiceAleatorio)) {
                Producto productoSeleccionado = listaProductos.get(indiceAleatorio);
                productosAReponer.add(productoSeleccionado);
                indicesSeleccionados.add(indiceAleatorio);
                cantidadSeleccionada--;
            }
        }

        return productosAReponer;
    }
	
	public void calificar(ControlCalidad c) {
        if (this != c.getProveedor()) {
            return;
        }
    
        ArrayList<Producto> productosAReponer = c.getProductosAReponerP();
        ArrayList<Producto> productosDefectuosos = c.getProductosDefectuosos();
        if (productosAReponer == null) {
            productosAReponer = new ArrayList<>();
        }
    
        int totalProductos = productosDefectuosos.size();
        int productosReemplazados = 0;
    
        for (Producto producto : productosDefectuosos) {
            if (productosAReponer.contains(producto)) {
                productosReemplazados++;
            }
        }
    
        int productosFaltantes = c.getProdsFaltantesCompra().size();
        int pedido = c.getCompra().getPedido().size();
    
        double porcentajeDeFaltanntes = ((double) productosFaltantes) / pedido;
        double porcentajeReemplazo = ((double) productosReemplazados) / totalProductos;
    
        int calificacion = this.calificar();
    
        if (productosDefectuosos.isEmpty() && productosAReponer.isEmpty() && porcentajeDeFaltanntes == 0) {
            this.setCalificacion(calificacion);
            return;
        } else if (productosDefectuosos.isEmpty() && productosAReponer.isEmpty() && porcentajeDeFaltanntes < 0.5) {
            this.setCalificacion(calificacion - 1);
            return;
        } else if (productosDefectuosos.isEmpty() && productosAReponer.isEmpty() && porcentajeDeFaltanntes >= 0.5) {
            this.setCalificacion(calificacion - 2);
            return;
        }
    
        if (productosReemplazados == totalProductos && porcentajeDeFaltanntes == 0) {
            this.setCalificacion(calificacion);
            return;
        } else if (productosReemplazados == totalProductos && porcentajeDeFaltanntes < 0.5) {
            this.setCalificacion(calificacion - 1);
            return;
        } else if (productosReemplazados == totalProductos && porcentajeDeFaltanntes >= 0.5) {
            this.setCalificacion(calificacion - 2);
            return;
        }
    
        if (porcentajeDeFaltanntes == 0) {
            if (porcentajeReemplazo >= 0.8) {
                this.setCalificacion(calificacion - 1);
            } else if (porcentajeReemplazo >= 0.6) {
                this.setCalificacion(calificacion - 2);
            } else if (porcentajeReemplazo > 0) {
                this.setCalificacion(calificacion - 3);
            } else {
                this.setCalificacion(calificacion - 4);
            }
            return;
        } else if (porcentajeDeFaltanntes < 0.5) {
            if (porcentajeReemplazo >= 0.8) {
                this.setCalificacion(calificacion - 2);
            } else if (porcentajeReemplazo >= 0.6) {
                this.setCalificacion(calificacion - 3);
            } else if (porcentajeReemplazo >= 0) {
                this.setCalificacion(calificacion - 4);
            }
            return;
        } else {
            if (porcentajeReemplazo >= 0.8) {
                this.setCalificacion(calificacion - 3);
            } else {
                this.setCalificacion(calificacion - 4);
            }
            return;
        }
    }
	

	public void setCostoCamiseta(float costo) { costoCamiseta = costo;}
	
	public void setCostoPantalon(float costo) { costoPantalon = costo;}
	
	public void setCostoAbrigo(float costo) { costoAbrigo = costo;}
	
	public float getCostoCamiseta() { return costoCamiseta;}
	
	public float getCostoPantalon() { return costoPantalon;}
	
	public float getCostoAbrigo() { return costoAbrigo;}
	
	public float getDescuento() {return descuento;}
	
	public Bodega getBodega() {return bodega;}
	
	public int getCalle() {return calle;}
	
	public float costoProductos() {
		return bodega.calcularCamisas() * getCostoCamiseta()+ bodega.calcularPantalon()*getCostoPantalon()+ bodega.calcularAbrigos()*costoAbrigo;	
	}
	
	
	public String toString() {
	  return "camisetas: "+bodega.calcularCamisas(getBodega().getProductosEnBodega())+ " X " +getCostoCamiseta() + "\nPantaloes: " + bodega.calcularPantalon(getBodega().getProductosEnBodega()) + " X " + getCostoPantalon() + "\nAbrigos: " + bodega.calcularAbrigos(getBodega().getProductosEnBodega()) + " X " + getCostoAbrigo();
		
	}

}
