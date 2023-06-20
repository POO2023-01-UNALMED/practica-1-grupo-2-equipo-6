from typing import List
from Producto import Producto
from Inventariar import Inventariar
from Transferencia import Transferencia
from ControlCalidad import ControlCalidad
from Tienda import Tienda

class Bodega(Inventariar):
    pagos = []
    resumenPedido = ""


    def __init__(self, productos,stop):
        self.stopBodega = stop
        self.productosEnBodega = productos

    def calcularNumeroProductos(self):
        return len(self.productosEnBodega)

    def agregarProductos(self, producto):
        self.productosEnBodega.append(producto)

    def calcularCamisas(self):
        return Inventariar.calcularCamisas(self.productosEnBodega)

    def calcularAbrigos(self):
        return Inventariar.calcularAbrigos(self.productosEnBodega)

    def calcularPantalon(self):
        return Inventariar.calcularPantalon(self.productosEnBodega)

    def calcularCamisas(self, pedido):
        numeroCamisetas = 0
        for producto in pedido:
            if producto.getNombre() == Producto.Tipo.CAMISA:
                numeroCamisetas += 1
        return numeroCamisetas

    def calcularPantalon(self, pedido):
        numeroPantalones = 0
        for producto in pedido:
            if producto.getNombre() == Producto.Tipo.PANTALON:
                numeroPantalones += 1
        return numeroPantalones

    def calcularAbrigos(self, pedido):
        numeroAbrigo = 0
        for producto in pedido:

            if producto.getNombre() == Producto.Tipo.ABRIGO:

                numeroAbrigo += 1
        return numeroAbrigo

    def getProductosEnBodega(self):
        return self.productosEnBodega

    def setProductosEnBodega(self, productosEnBodega):
        self.productosEnBodega = productosEnBodega

    def getMinCamisas(self):
        return Inventariar.minCamisas

    def getMinAbrigo(self):
        return Inventariar.minAbrigo

    def getMinPantalon(self):
        return Inventariar.minPantalon

    def buscarProducto(self, productos):
        listaAuxiliar = self.getProductosEnBodega().copy()
        productosBuscados = []
        for producto in productos:
            for p in listaAuxiliar:
                if producto == p:
                    productosBuscados.append(p)
                    listaAuxiliar.remove(p)
                    break
        return productosBuscados

    def evaluarDisponibilidad(self, productosRetirar):
        contadorAbrigo = self.calcularAbrigos(productosRetirar)
        contadorPantalon = self.calcularPantalon(productosRetirar)
        contadorCamisas = self.calcularCamisas(productosRetirar)

        if (Inventariar.calcularCamisas(self.productosEnBodega) - contadorCamisas >= self.getMinCamisas() and
            Inventariar.calcularAbrigos(self.productosEnBodega) - contadorAbrigo >= self.getMinAbrigo() and
            Inventariar.calcularPantalon(self.productosEnBodega) - contadorPantalon >= self.getMinPantalon()):
            self.retirarProductos(productosRetirar)
            return True
        else:
            return False

    def retirarProductos(self, productos):
        borrarProducto = [p.getNombre() for p in productos]
        productosBorrar = []
        for p in self.productosEnBodega:
            if p.getNombre() in borrarProducto:
                productosBorrar.append(p)
                borrarProducto.remove(p.getNombre())
        for p in productosBorrar:
            self.productosEnBodega.remove(p)

    def getStopBodega(self):
        return self.stopBodega

    def __str__(self):
        return f"Camisetas = {self.calcularCamisas(self.getProductosEnBodega())}\nPantalones = {self.calcularPantalon(self.getProductosEnBodega())}\nAbrigos = {self.calcularAbrigos(self.getProductosEnBodega())}"

    @staticmethod
    def realizar_pedido(tiendas, set_tipo, cantidad):

        necesarios = Producto.seleccionarProductos(set_tipo, cantidad)
        pedido = []
        for tienda in tiendas:
            if not necesarios:
                break
            else:
                bodega = tienda.getBodega()
                productos = bodega.pedir_productos(necesarios)
                pedido.extend(productos)
                Bodega.gestionar_pago(tienda, productos)
        Bodega.crear_resumen(necesarios, pedido)
        return pedido

    @staticmethod
    def crear_resumen(necesarios, pedido):
        if not necesarios:
            Bodega.resumenPedido = "El pedido se ha completado exitosamente"
        else:
            completados = len(pedido)
            total = completados + len(necesarios)
            Bodega.resumenPedido = f"Por falta de disponibilidad de productos en las tiendas el pedido solo ha podido completar {completados} de los {total} necesarios."

    @staticmethod
    def gestionar_pago(tienda, productos):
        cantidad=0
        for p in productos:
            cantidad+=p.getCosto()

        pago = Transferencia(Tienda.cuentaTienda,Tienda.cuentaTienda.getEntidad(),cantidad)
        pago.setDestinatario(Tienda.cuentaTienda)
        tienda.presupuesto+=cantidad
        Bodega.pagos.append(pago)

    def pedir_productos(self, productos):
        pedido = []
        completado = []
        for producto in productos:
            for i in range(len(self.productosEnBodega)):
                if producto.get_tipo() == self.productosEnBodega[i].get_tipo():
                    pedido.append(self.productosEnBodega[i])
                    completado.append(producto)
                    del self.productosEnBodega[i]
                    break

        for producto in completado:
            productos.remove(producto)

        return pedido

    def abastecerBodega(self, c, lista):
        if (len(self.productosEnBodega) == 0 or self.productosEnBodega is None) and ControlCalidad.listaSinLista(c.getCompra().getCompraLlego(), c.getProductosDefectuosos()) is not None:
            self.setProductos(ControlCalidad.listaSinLista(c.getCompra().getCompraLlego(), c.getProductosDefectuosos()))
            if lista is not None:
                self.abastecerBodega2(lista)
        elif lista is not None:
            self.abastecerBodega2(lista)

    def abastecerBodega2(self, lista):
        for producto in lista:
            self.addProducto(producto)

    def addProducto(self, producto):
        self.productosEnBodega.append(producto)

    def setProductos(self, productos):
        self.productosEnBodega = productos

    @staticmethod
    def getResumenPedido():
        return Bodega.resumenPedido


    def setResumenPedido(resumenPedido):
        Bodega.resumenPedido = resumenPedido


    def getPagos(self):
        return self.pagos

    def setPagos(self,pagos):
        self.pagos = pagos

    # def __str__(self):
    #     return f"Camisas: {self.calcularCamisas()} \nPantalones: {self.calcularPantalon()}"
    #
