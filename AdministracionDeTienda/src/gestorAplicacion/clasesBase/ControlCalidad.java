package gestorAplicacion.clasesBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import gestorAplicacion.clasesHerencia.Proveedor;
import gestorAplicacion.clasesHerencia.Transportista;

public class ControlCalidad implements Serializable {
    private Compra compra;
    private Proveedor proveedor;
    private Transportista transportista;
    private ArrayList<Producto> productosDefectuosos;
    private ArrayList<Producto> productosExtraviados; 
    private ArrayList<Producto> productosAReponerP; 
    private ArrayList<Producto> productosAReponerT;
    private ArrayList<Producto> revision;
    private ArrayList<Producto> prodsFaltantesCompra;
    private boolean contactarP = false;
    private boolean contactarT = false;

    public ControlCalidad(Compra compra){
        this.compra = compra;
        this.proveedor = compra.getProveedorSeleccionado();
        this.transportista = compra.getTransportistaSeleccionado();
    }

    //Primera funcionalidad (Revisión)
    public ArrayList<Producto> revision(Compra c){
        if (this.compra == c) {
            // compra, controlCalidad, Proveedor
            c.generarProductosExtraviados(); 
            c.generarCompraLlego();
            ArrayList<Producto> productosDefectuosos = Proveedor.generarProductos(c.getCompraLlego());
            ArrayList<Producto> productosExtraviados = c.getProductosExtraviados();
            ArrayList<Producto> productosRevision = new ArrayList<>();
            ArrayList<Producto> prodsFaltantesCompra = ControlCalidad.listaSinLista(c.getPedido(), c.getProveedorSeleccionado().getBodega().getProductosEnBodega());
            this.prodsFaltantesCompra = prodsFaltantesCompra;
            this.productosDefectuosos = productosDefectuosos;
            this.productosExtraviados = productosExtraviados;
            productosRevision.addAll(productosDefectuosos);
            productosRevision.addAll(productosExtraviados);
            this.revision = productosRevision;
            return productosRevision;
        } else {
            return null;
        }
    } 


    //Proveeddor, bodega
    public Proveedor contactar(Proveedor proveedor){
        if (this.proveedor == proveedor) {
            this.productosAReponerP = proveedor.pDefectuososAReponer(this);
            this.compra.getTienda().getBodega().abastecerBodega(this, productosAReponerP);
            this.contactarP = true;
            return proveedor;
        } else {
            return null;
        }
    }
    //Transportista, bodega
     public Transportista contactar(Transportista transportista){
        if (this.transportista == transportista) {
            this.productosAReponerT = Transportista.generarProductos(this.productosExtraviados);
            this.compra.getTienda().getBodega().abastecerBodega(this, productosAReponerT);
            this.contactarT = true;
            return transportista;
        } else {
            return null;
        }
    } 


    // Para abastecer LA TOTALIDAD de los productos defectuosos y extraviados
    public Transportista contactarForzado(Transportista t){
        if (this.transportista == t){
            this.productosAReponerT = productosExtraviados;
            this.compra.getTienda().getBodega().abastecerBodega(this, productosAReponerT);
            this.contactarT = true;
            return t;
        } else {
            return null;
        }
    }

    public Proveedor contactarForzado(Proveedor p){
        if (this.proveedor == p){
            this.productosAReponerP = productosDefectuosos;
            this.compra.getTienda().getBodega().abastecerBodega(this, productosAReponerP);
            this.contactarP = true;
            return p;
        } else {
            return null;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////

    public Informe generarInforme(Proveedor p, Transportista t){
        if (this.proveedor == p && this.transportista == t) {
            p.calificar(this);
            t.calificar(this);
            Informe informe = new Informe(Informe.TipoInforme.INFORME_CONTROL_CALIDAD,this);
            return informe;
        } else {
            return null;
        }
    }


    public static ArrayList<Producto> listaSinLista(ArrayList<Producto> lista1, ArrayList<Producto> lista2) {
/*         ArrayList<Producto> lista1 = this.compra.getCompraLlego();
        ArrayList<Producto> lista2 = this.productosDefectuosos; */

        if (lista1.size() == lista2.size() && lista1.containsAll(lista2)) {
            return new ArrayList<>();
        }
        ArrayList<Producto> compraSinDefectuosos = new ArrayList<>();

        HashMap<String, Integer> contadores1 = new HashMap<>();
        HashMap<String, Integer> contadores2 = new HashMap<>();
        for (Producto producto1 : lista1) {


            String clave = producto1.getNombre() + "-" + producto1.getPrecio();
            contadores1.put(clave, contadores1.getOrDefault(clave, 0) + 1);
        }
        for (Producto producto2 : lista2) {
            String clave = producto2.getNombre() + "-" + producto2.getPrecio();
            contadores2.put(clave, contadores2.getOrDefault(clave, 0) + 1);
        }

        for (Producto producto1 : lista1) {
            String clave = producto1.getNombre() + "-" + producto1.getPrecio();
            if (!contadores2.containsKey(clave) || contadores2.get(clave) <= 0) {
                compraSinDefectuosos.add(producto1);
            } else {
                contadores2.put(clave, contadores2.get(clave) - 1);
            }
        }
        if (compraSinDefectuosos == null || compraSinDefectuosos.isEmpty()) {
            return new ArrayList<>();
        }
        return compraSinDefectuosos;
    }

    public void setContactarP(boolean contactarP){
        this.contactarP = contactarP;
    }

    public boolean getContactarP(){
        return contactarP;
    }

    public void setContactarT(boolean contactarT){
        this.contactarT = contactarT;
    }

    public boolean getContactarT(){
        return contactarT;
    }

    public void setProdsFaltantesCompra(ArrayList<Producto> prodsFaltanteCompra){
        this.prodsFaltantesCompra = prodsFaltanteCompra;
    }

    public ArrayList<Producto> getProdsFaltantesCompra(){
        return prodsFaltantesCompra;
    }

    public void setRevision(ArrayList<Producto> revision){
        this.revision = revision;
    }

    public ArrayList<Producto> getRevision(){
        return revision;
    }

    public void setProductosAReponerT(ArrayList<Producto> productosAReponerT){
        this.productosAReponerT = productosAReponerT;
    }

    public ArrayList<Producto> getProductosAReponerT(){
        return productosAReponerT;
    }

    public void setProductosAReponerP(ArrayList<Producto> productosAReponerP){
        this.productosAReponerP = productosAReponerP;
    }

    public ArrayList<Producto> getProductosAReponerP(){
        return productosAReponerP;
    }

    public void setProductosDefectuosos(ArrayList<Producto> productosDefectuosos){
        this.productosDefectuosos = productosDefectuosos;
    }

    public ArrayList<Producto> getProductosDefectuosos(){
        return productosDefectuosos;
    }

    public void setProductosExtraviados(ArrayList<Producto> productosExtraviados){
        this.productosExtraviados = productosExtraviados;
    }

    public ArrayList<Producto> getProductosExtraviados(){
        return productosExtraviados;
    }

    public Compra getCompra(){
        return compra;
    }

    public void setCompra(Compra compra){
        this.compra = compra;
    }

    public Proveedor getProveedor(){
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor){
        this.proveedor = proveedor;
    }

    public Transportista getTransportista(){
        return transportista;
    }

    public void setTransportista(Transportista transportista){
        this.transportista = transportista;
    }



}

