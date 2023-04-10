package gestorAplicacion.clasesBase;

import java.util.ArrayList;

public class Promocion {
    private String nombre;
    private String descripcion;
    private double descuento;
    private ArrayList<Producto> productos;

    public Promocion(String nombre, String descripcion, double descuento, ArrayList<Producto> productos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.productos = productos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public void aplicarPromocion(Tienda tienda) {
        // Verificar que hay suficientes productos en la bodega para aplicar la promoción
        boolean haySuficientes = true;
        for (Producto p : productos) {
            if (tienda.getBodega().getCantidadProducto(p) < productos.size()) {
                haySuficientes = false;
                break;
            }
        }
        if (!haySuficientes) {
            System.out.println("No hay suficientes productos en la bodega para aplicar la promoción.");
            return;
        }

        // Calcular el total de la promoción y aplicar el descuento
        double totalPromocion = 0;
        for (Producto p : productos) {
            totalPromocion += p.getCosto();
            tienda.getBodega().retirarProducto(p);
        }
        totalPromocion = totalPromocion * (1 - descuento);
        tienda.setPresupuestoCompras(tienda.getPresupuestoCompras() + totalPromocion);

        // Asignar la promoción a las ventas que se hagan mientras dure la promoción
        ArrayList<Venta> ventasConPromocion = new ArrayList<Venta>();
        for (Producto p : productos) {
            for (Venta v : p.getVentas()) {
                if (v.getTienda() == tienda && !ventasConPromocion.contains(v)) {
                    ventasConPromocion.add(v);
                }
            }
        }
        for (Venta v : ventasConPromocion) {
            v.setTotal(v.getTotal() * (1 - descuento));
        }

        System.out.println("Se ha aplicado la promoción " + nombre + " con un descuento del " + (descuento * 100) + "%.");
    }
}
