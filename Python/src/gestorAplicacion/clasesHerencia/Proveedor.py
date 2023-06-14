from Python.src.gestorAplicacion.clasesBase import Persona
from random import randint


class Proveedor(Persona):
    def __init__(self, nombre, bodega, camisa, pantalon, abrigo, calle):
        super().__init__(nombre)
        self.bodega = bodega
        self.costoCamisa = camisa
        self.costoPantalon = pantalon
        self.costoAbrigo = abrigo
        self.calle = calle
        self.descuento = None


    def verificarDisponibilidad(self, pedido):
        descuentoCamiseta = 0
        descuentoPantalon = 0
        descuentoAbrigo = 0
        numeroCamisetas = 0
        numeroPantalon = 0
        numeroAbrigo = 0
        productosDisponibles = []

    #De forma aleatoria se decide que productos tiene disponible el proveedor
    #De forma aleatoria se decide si el proveedor ofrece descuento por los productos

        for producto in pedido:
            aleatorio = randint(0, 6)

            if aleatorio != 3:
                productosDisponibles += [producto]

                if producto.getTipo() == "CAMISA":
                    numeroCamisetas += 1
                    if aleatorio == 4 or aleatorio == 5:
                        descuentoCamiseta += producto.getCosto()*0.20

                elif producto.getTipo() == "PANTALON":
                    numeroPantalon += 1
                    if aleatorio == 4 or aleatorio == 5:
                        descuentoPantalon += producto.getCosto() * 0.20

                else:
                    numeroAbrigo += 1
                    if aleatorio == 4 or aleatorio == 5:
                        descuentoAbrigo += producto.getCosto() * 0.20

        #Se le asigna el precio a cada producto en funcion del costo

        for producto1 in productosDisponibles:
            if producto1.getTipo() == "CAMISA":
                producto1.setCosto(round(self.costoCamisa - descuentoCamiseta / numeroCamisetas, 2))

            elif producto1.getTipo() == "PANTALON":
                producto1.setCosto(round(self.costoPantalon - descuentoPantalon / numeroPantalon,2))

            else:
                producto1.setCosto(round(self.costoAbrigo - descuentoAbrigo / numeroAbrigo, 2))

            producto1.setPrecio(round(producto1.getCosto()*2, 2))

        self.bodega.setProductosEnBodega(productosDisponibles)
        self.setDescuento(round(descuentoPantalon + descuentoCamiseta + descuentoAbrigo, 2))

    def setDescuento(self, descuento):
        self.descuento = descuento

    def getDescuento(self):
        return self.descuento

    def getCalle(self):
        return self.calle

    def setCalle(self, calle):
        self.calle = calle

    def getBodega(self):
        return self.bodega

    def setBodega(self, bodega):
        self.bodega = bodega






