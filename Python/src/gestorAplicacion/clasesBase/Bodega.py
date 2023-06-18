from typing import List
from Python.src.gestorAplicacion.clasesBase import Producto
from Python.src.gestorAplicacion.clasesBase import Inventariar
from Python.src.gestorAplicacion.clasesBase import Transferencia
from Python.src.gestorAplicacion.clasesBase import ControlCalidad
from Python.src.gestorAplicacion.clasesBase import Tienda

class Bodega(Inventariar):
    pagos = []
    resumen_pedido = None


    def _init_(self, productos: List[Producto]):
        self.stopBodega = len(productos)
        self.productosEnBodega = productos
        self.resumenPedido = None
        self.pagos = []

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

    def calcularCamisas(self, pedido: List[Producto]):
        numeroCamisetas = 0
        for producto in pedido:
            if producto.getNombre() == "CAMISA":
                numeroCamisetas += 1
        return numeroCamisetas

    def calcularPantalon(self, pedido: List[Producto]):
        numeroPantalones = 0
        for producto in pedido:
            if producto.getNombre() == "PANTALON":
                numeroPantalones += 1
        return numeroPantalones

    def calcularAbrigos(self, pedido: List[Producto]):
        numeroAbrigo = 0
        for producto in pedido:
            if producto.getNombre() == "ABRIGO":
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

        if (self.calcularCamisas() - contadorCamisas >= self.getMinCamisas() and
            self.calcularAbrigos() - contadorAbrigo >= self.getMinAbrigo() and
            self.calcularPantalon() - contadorPantalon >= self.getMinPantalon()):
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

    def _str_(self):
        return f"Camisetas = {self.calcularCamisas(self.getProductosEnBodega())}\nPantalones = {self.calcularPantalon(self.getProductosEnBodega())}\nAbrigos = {self.calcularAbrigos(self.getProductosEnBodega())}"

    @staticmethod
    def realizar_pedido(tiendas, set_tipo, cantidad):

        from Python.src.gestorAplicacion.clasesBase.Producto import Producto
        necesarios = Producto.seleccionar_productos(set_tipo, cantidad)
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
            Bodega.resumen_pedido = "El pedido se ha completado exitosamente"
        else:
            completados = len(pedido)
            total = completados + len(necesarios)
            Bodega.resumen_pedido = f"Por falta de disponibilidad de productos en las tiendas el pedido solo ha podido completar {completados} de los {total} necesarios."

    @staticmethod
    def gestionar_pago(tienda, productos):
        cantidad = sum(producto.get_costo() for producto in productos)
        pago = Transferencia(Tienda.cuentaTienda,Tienda.cuentaTienda.getEntidad(),cantidad)
        pago.setDestinatario(Tienda.cuentaTienda)
        tienda.presupuesto+=cantidad
        Bodega.pagos.append(pago)

    def pedir_productos(self, productos):
        pedido = []
        completado = []
        for producto in productos:
            for i in range(len(self.productos_en_bodega)):
                if producto.get_tipo() == self.productos_en_bodega[i].get_tipo():
                    pedido.append(self.productos_en_bodega[i])
                    completado.append(producto)
                    del self.productos_en_bodega[i]
                    break

        for producto in completado:
            productos.remove(producto)

        return pedido

    def abastecerBodega(self, c, lista):
        if (not self.productosEnBodega and ControlCalidad.listaSinLista(c.getCompra().getCompraLlego(), c.getProductosDefectuosos())):
            self.setProductos(ControlCalidad.listaSinLista(c.getCompra().getCompraLlego(), c.getProductosDefectuosos()))
            if lista:
                self.abastecerBodega(lista)
        elif lista:
            self.abastecerBodega(lista)

    def abastecerBodega(self, lista):
        for producto in lista:
            self.addProducto(producto)

    def addProducto(self, producto):
        self.productosEnBodega.append(producto)

    def setProductos(self, productos):
        self.productosEnBodega = productos

    @staticmethod
    def getResumenPedido():
        return Bodega.resumenPedido

    @staticmethod
    def setResumenPedido(resumenPedido):
        Bodega.resumenPedido = resumenPedido

    @staticmethod
    def getPagos():
        return Bodega.pagos

    @staticmethod
    def setPagos(pagos):
        Bodega.pagos = pagos
