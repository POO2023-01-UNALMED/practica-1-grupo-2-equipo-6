from typing import List
from Producto import *
from Inventariar import *
from Producto import *


class OfertaPorDefecto(Inventariar):
    def __init__(self, productosVendidos):
        self.productosOferta = self.establecerOferta(productosVendidos)
        self.total = sum(producto.precio for producto in self.productosOferta)

    def establecerOferta(self, productosVendidos):
        contadores = [0, 0, 0]
        tipoMenosVendido = None
        listaAuxiliar = []

        contadores[0] = self.calcularCamisas(productosVendidos)
        contadores[1] = self.calcularAbrigos(productosVendidos)
        contadores[2] = self.calcularPantalon(productosVendidos)
        minContador = min(contadores)

        if contadores[0] <= minContador:
            tipoMenosVendido = Producto.Tipo.CAMISA
        if contadores[1] <= minContador:
            tipoMenosVendido = Producto.Tipo.ABRIGO
        if contadores[2] <= minContador:
            tipoMenosVendido = Producto.Tipo.PANTALON

        productoParaAgregar = Producto(tipoMenosVendido, 0.9 * tipoMenosVendido.value, 15000)

        for _ in range(9):
            if not(tipoMenosVendido.__eq__(Producto.Tipo.ABRIGO)) and self.calcularAbrigos(lista_auxiliar) < 2:
                lista_auxiliar.append(Producto(Producto.Tipo.ABRIGO, 0.9 * Producto.Tipo.ABRIGO.value(), 15000))
            elif not(tipoMenosVendido.__eq__(Producto.Tipo.CAMISA)) and self.calcular_camisas(lista_auxiliar) < 2:
                lista_auxiliar.append(Producto(Producto.Tipo.CAMISA, 0.9 * Producto.Tipo.CAMISA.value(), 15000))
            elif not(tipoMenosVendido.__eq__(Producto.Tipo.PANTALON)) and self.calcular_pantalon(lista_auxiliar) < 2:
                listaAuxiliar.append(Producto(Producto.Tipo.PANTALON, 0.9 * Producto.Tipo.PANTALON.value(), 15000))
            elif listaAuxiliar.count(productoParaAgregar) < 5:
                listaAuxiliar.append(productoParaAgregar)

        return listaAuxiliar

    def getProductosOferta(self):
        return self.productosOferta

    def setProductosOferta(self, productosOferta):
        self.productosOferta=productosOferta

    def getTotal(self):
        return self.total

    def setTotal(self, total):
        self.total=total
    def __lt__(self, otra):
        if(self.total < otra.getTotal()):
            return True
        else:
            return False


    def __str__(self):
        return f"OfertaPorDefecto: productos={self.productosOferta}, total={self.total}"