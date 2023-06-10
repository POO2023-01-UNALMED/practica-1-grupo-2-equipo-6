import pickle
from Bodega import SETS

class Producto:
    class Tipo:
        CAMISA = 40000
        PANTALON = 50000
        ABRIGO = 60000

        def __init__(self, precioEstandar):
            self.precioEstandar = precioEstandar

    def __init__(self, tipo, precio=None, costo=None):
        self.nombre = tipo.name
        self.precio = tipo.precioEstandar if precio is None else precio
        self.costo = costo


    def __eq__(self, p):
        if isintansce(p, Producto):
            if self.nombre==p.getNombre():
                return True
            else:
                return False

    




    def getNombre(self)->str:
        return self.nombre

    def setNombre(self, nombre):
        self.nombre=nombre

    def getPrecio(self)->float:
        return self.precio

    def setPrecio(self, precio:float):
        self.precio=precio

    def getCosto(self)->float:
        return self.costo

    def setCosto(self, costo:float):
        self.costo=costo

    def __str__(self):
        return "\nTipo: "+self.nombre+"\nPrecio: "+self.precio;


    def seleccionarProductos(self):


    	public static ArrayList<Producto> seleccionarProductos(SETS set,int cantidad) {
			ArrayList<Producto> productos=new ArrayList<Producto>();
			switch(set) {
			case ONLY:
				for(int i=0;i<cantidad;i++) {
					productos.add(new Producto(Tipo.CAMISA));

			}
			break;
			case TU:
				for(int i=0;i<cantidad;i++) {
					productos.add(new Producto(Tipo.CAMISA));
					productos.add(new Producto(Tipo.PANTALON));

			}
			break;
			case COMPLETO:
				for(int i=0;i<cantidad;i++) {
					productos.add(new Producto(Tipo.CAMISA));
					productos.add(new Producto(Tipo.PANTALON));
					productos.add(new Producto(Tipo.ABRIGO));


			}
			break;

			}
			return productos;
		}

		public static ArrayList<Producto> clasificar(ArrayList<Producto> productos, Tipo tipo){
			ArrayList<Producto> clasificados=new ArrayList<Producto>();
			for(int i=0;i<productos.size();i++) {
				Tipo tipado=productos.get(i).getTipo();
				if(tipado==tipo) {
					clasificados.add(productos.get(i));
				}
			}
			return clasificados;
		}
