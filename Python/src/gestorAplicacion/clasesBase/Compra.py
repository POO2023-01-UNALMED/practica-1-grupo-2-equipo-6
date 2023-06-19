import math
from Producto import *
import random

class Compra:

    def __init__(self, tienda):
        self.tienda = tienda
        self.pedido = []
        self.proveedor = None
        self.transportista = None
        self.productosExtraviados = []
        self.revisado = []
        self.compraLlego = []

    def hacerPedido(self, tienda):
        numCamisetas = int(tienda.getBodega().calcularCamisas(tienda.getBodega().getProductosEnBodega()))
        numPantalones = int(tienda.getBodega().calcularPantalones(tienda.getBodega().getProductosEnBodega()))
        numAbrigos = int(tienda.getBodega().calcularAbrigos(tienda.getBodega().getProductosEnBodega()))
        numProductos = int(numAbrigos + numPantalones + numCamisetas)

        # Agrega los productos mas necesitados al pedido en orden de prioridad

        while numProductos <= int(tienda.getBodega().getStopBodega()):

            if numCamisetas <= numAbrigos and numCamisetas <= numPantalones:
                numProductos += 1
                numCamisetas += 1
                producto1 = Producto(Producto.Tipo.CAMISA, 40000, 20000)
                self.pedido += [producto1]

            elif numPantalones <= numAbrigos and numPantalones <= numCamisetas:
                numProductos += 1
                numPantalones += 1
                producto2 = Producto(Producto.Tipo.PANTALON, 50000, 25000)
                self.pedido += [producto2]

            else:
                numProductos += 1
                numAbrigos += 1
                producto3 = Producto(Producto.Tipo.ABRIGO, 60000, 30000)
                self.pedido += [producto3]

        # Se sale del metodo si la capacidad de la bodega no es suficiente

        if len(self.pedido) < 6:
            return None

        # Agrega al pedido final los productos previamente seleccionados que se puedan comprar en funcion del presupuesto

        presupuesto = tienda.getPresupuesto()
        pedidoFinal = []

        for productoPedido in self.pedido:
            if (presupuesto - productoPedido.getCosto() <= 0):
                continue
            else:
                presupuesto -= productoPedido.getCosto()
                pedidoFinal += [productoPedido]

        # Se sale del metodo si el presupuesto no alcanza para comprar al menos 6 productos

        if len(pedidoFinal) < 6:
            return False

        self.setPedido(pedidoFinal)
        return pedidoFinal

    def recomendarProveedor(self, pedido, proveedores):
        proveedorRecomendado = None
        descuento = 0

        for proveedor in proveedores:
            proveedor.verificarDisponibilidad(pedido)
            if proveedor.getDescuento() >= descuento:
                descuento = proveedor.getDescuento()
                proveedorRecomendado = proveedor

        self.setProveedor(proveedorRecomendado)
        return proveedorRecomendado

    def recomendarTransportista(self, tienda, proveedor, transportistas):
        costo = 99999999
        transportistaRecomendado = None

        for transportista in transportistas:
            if transportista.calcularprecioDeEnvio(proveedor, tienda) <= costo:
                costo = transportista.calcularprecioDeEnvio(proveedor, tienda)
                transportistaRecomendado = transportista
        self.setTransportista(transportistaRecomendado)
        return transportistaRecomendado
    
    def generarProductosExtraviados(self):
        productosSeleccionados = []
        listaProductos = self.getProveedor().getBodega().getProductosEnBodega()
        random.seed()

        cantidadProductos = len(listaProductos)
        maxCantidadSeleccionada = int(math.ceil(cantidadProductos * 0.6))

        cantidadSeleccionada = random.randint(0, maxCantidadSeleccionada + 1)

        indicesSeleccionados = set()

        while cantidadSeleccionada > 0:
            indiceAleatorio = random.randint(0, cantidadProductos - 1)
            if indiceAleatorio not in indicesSeleccionados:
                productoSeleccionado = listaProductos[indiceAleatorio]
                productosSeleccionados.append(productoSeleccionado)
                indicesSeleccionados.add(indiceAleatorio)
                cantidadSeleccionada -= 1

        self.productosExtraviados = productosSeleccionados
        return productosSeleccionados
    
    def generarCompraLlego(self):
        lista1 = self.proveedor.getBodega().getProductosEnBodega()
        lista2 = self.productosExtraviados
        lista3 = []

        for producto in lista1:
            if producto not in lista2:
                lista3.append(producto)

        self.compraLlego = lista3
        return lista3
    
    def getCompraLlego(self):
        return self.compraLlego
    
    def setCompraLlego(self, compraLlego):
        self.compraLlego = compraLlego

    def getRevisado(self):
        return self.revisado

    def setRevisado(self, revisado):
        self.revisado = revisado

    def getProductosExtraviados(self):
        return self.productosExtraviados

    def getTienda(self):
        return self.tienda

    def setTienda(self, tienda):
        self.tienda = tienda

    def getPedido(self):
        return self.pedido

    def setPedido(self, pedido):
        self.pedido = pedido

    def getProveedor(self):
        return self.proveedor

    def setProveedor(self, proveedor):
        self.proveedor = proveedor

    def getTransportista(self):
        return self.transportista

    def setTransportista(self, transportista):
        self.transportista = transportista



