import pickle

class SETS(Enum):
	ONLY =1
	TU = 2
	COMPLETO = 3


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


    # def __eq__(self, p):
    #     if isintansce(p, Producto):
    #         if self.nombre==p.getNombre():
    #             return True
    #         else:
    #             return False






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


	@staticmethod
	def seleccionar_productos(set, cantidad):
		productos = []
		if set == SETS.ONLY:
			for _ in range(cantidad):
				productos.append(Producto(Producto.Tipo.CAMISA))
		elif set == SETS.TU:
			for _ in range(cantidad):
				productos.append(Producto(Producto.Tipo.CAMISA))
				productos.append(Producto(Producto.Tipo.PANTALON))
		elif set == SETS.COMPLETO:
			for _ in range(cantidad):
				productos.append(Producto(Producto.Tipo.CAMISA))
				productos.append(Producto(Producto.Tipo.PANTALON))
				productos.append(Producto(Producto.Tipo.ABRIGO))
		return productos

	@staticmethod
	def clasificar(productos, tipo):
		clasificados = []
		for producto in productos:
			if producto.tipo == tipo:
				clasificados.append(producto)
		return clasificados