from Persona import *
from random import randint
import random
import math


class Proveedor(Persona):
    def __init__(self, nombre, calificacion, cuenta, bodega, camisa, pantalon, abrigo, calle):
        super().__init__(nombre, calificacion, cuenta)
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

                if producto.getNombre() == "CAMISA":
                    numeroCamisetas += 1
                    if aleatorio == 4 or aleatorio == 5:
                        descuentoCamiseta += producto.getCosto()*0.20

                elif producto.getNombre() == "PANTALON":
                    numeroPantalon += 1
                    if aleatorio == 4 or aleatorio == 5:
                        descuentoPantalon += producto.getCosto() * 0.20

                else:
                    numeroAbrigo += 1
                    if aleatorio == 4 or aleatorio == 5:
                        descuentoAbrigo += producto.getCosto() * 0.20

        #Se le asigna el precio a cada producto en funcion del costo

        for producto1 in productosDisponibles:
            if producto1.getNombre() == "CAMISA":
                producto1.setCosto(round(self.costoCamisa - descuentoCamiseta / numeroCamisetas, 2))

            elif producto1.getNombre() == "PANTALON":
                producto1.setCosto(round(self.costoPantalon - descuentoPantalon / numeroPantalon,2))

            else:
                producto1.setCosto(round(self.costoAbrigo - descuentoAbrigo / numeroAbrigo, 2))

            producto1.setPrecio(round(producto1.getCosto()*2, 2))

        self.bodega.setProductosEnBodega(productosDisponibles)
        self.setDescuento(round(descuentoPantalon + descuentoCamiseta + descuentoAbrigo, 2))

    def pDefectuososAReponer(self, control):
        productosSeleccionados = []
        indicesSeleccionados = set()
        listaProductos = control.getProductosDefectuosos()

        cantidadProductos = len(listaProductos)
        cantidadSeleccionada = random.randint(0, cantidadProductos)

        while cantidadSeleccionada > 0:
            indiceAleatorio = random.randint(0, cantidadProductos - 1)
            if indiceAleatorio not in indicesSeleccionados:
                productoSeleccionado = listaProductos[indiceAleatorio]
                productosSeleccionados.append(productoSeleccionado)
                indicesSeleccionados.add(indiceAleatorio)
                cantidadSeleccionada -= 1
        return productosSeleccionados


    def calificar(self, c):
        if self != c.getProveedor():
            return

        productosAReponer = c.getProductosAReponerP()
        productosDefectuosos = c.getProductosDefectuosos()
        if productosAReponer is None:
            productosAReponer = []

        totalProductos = len(productosDefectuosos)
        productosReemplazados = 0

        for producto in productosDefectuosos:
            if producto in productosAReponer:
                productosReemplazados += 1

        productosFaltantes = len(c.getProdsFaltantesCompra())
        pedido = len(c.getCompra().getPedido())

        porcentajeDeFaltanntes = productosFaltantes / pedido

        if totalProductos > 0:
            porcentajeReemplazo = productosReemplazados / totalProductos
        else:
            porcentajeReemplazo = 0.0

        calificacion = self.valorCalificacion()

        if productosDefectuosos == [] and productosAReponer == [] and porcentajeDeFaltanntes == 0:
            self.setCalificacion(calificacion)
            return
        elif productosDefectuosos == [] and productosAReponer == [] and porcentajeDeFaltanntes < 0.5:
            self.setCalificacion(calificacion - 1)
            return
        elif productosDefectuosos == [] and productosAReponer == [] and porcentajeDeFaltanntes >= 0.5:
            self.setCalificacion(calificacion - 2)
            return

        if productosReemplazados == totalProductos and porcentajeDeFaltanntes == 0:
            self.setCalificacion(calificacion)
            return
        elif productosReemplazados == totalProductos and porcentajeDeFaltanntes < 0.5:
            self.setCalificacion(calificacion - 1)
            return
        elif productosReemplazados == totalProductos and porcentajeDeFaltanntes >= 0.5:
            self.setCalificacion(calificacion - 2)
            return

        if porcentajeDeFaltanntes == 0:
            if porcentajeReemplazo >= 0.8:
                self.setCalificacion(calificacion - 1)
            elif porcentajeReemplazo >= 0.6:
                self.setCalificacion(calificacion - 2)
            elif porcentajeReemplazo > 0:
                self.setCalificacion(calificacion - 3)
            else:
                self.setCalificacion(calificacion - 4)
            return
        elif porcentajeDeFaltanntes < 0.5:
            if porcentajeReemplazo >= 0.8:
                self.setCalificacion(calificacion - 2)
            elif porcentajeReemplazo >= 0.6:
                self.setCalificacion(calificacion - 3)
            elif porcentajeReemplazo >= 0:
                self.setCalificacion(calificacion - 4)
            return
        else:
            if porcentajeReemplazo >= 0.8:
                self.setCalificacion(calificacion - 3)
            else:
                self.setCalificacion(calificacion - 4)
            return
            
    def getCuenta(self):
        return self.cuenta

    def setCuenta(self, cuenta):
        self.cuenta = cuenta
    
    def valorCalificacion(self):
        return 5

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






