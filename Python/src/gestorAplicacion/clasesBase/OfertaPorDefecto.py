from typing import List
from Producto.Producto import Tipo


class OfertaPorDefecto(Inventariar):
    def __init__(self, productosVendidos):
        self.productosOferta = self.establecerOferta(productosVendidos)
        self.total = sum(producto.precio for producto in self.productos_oferta)

    def establecerOferta(self, productosVendidos):
        contadores = [0, 0, 0]
        tipoMenosVendido = None
        listaAuxiliar = []

        contadores[0] = self.calcularCamisas(productosVendidos)
        contadores[1] = self.calcularAbrigos(productosVendidos)
        contadores[2] = self.calcularPantalon(productosVendidos)
        minContador = min(contadores)

        if contadores[0] <= minContador:
            tipoMenosVendido = Tipo.CAMISA
        if contadores[1] <= minContador:
            tipoMenosVendido = Tipo.ABRIGO
        if contadores[2] <= minContador:
            tipoMenosVendido = Tipo.PANTALON

        productoParaAgregar = Producto(tipoMenosVendido, 0.9 * tipoMenosVendido.value(), 15000)

        for _ in range(9):
            if not(tipoMenosVendido.__eq__(Tipo.ABRIGO)) and self.calcularAbrigos(lista_auxiliar) < 2:
                lista_auxiliar.append(Producto("ABRIGO", 0.9 * Tipo.ABRIGO.value(), 15000))
            elif not(tipoMenosVendido.__eq__(Tipo.CAMISA)) and self.calcular_camisas(lista_auxiliar) < 2:
                lista_auxiliar.append(Producto("CAMISA", 0.9 * Tipo.CAMISA.value(), 15000))
            elif not(tipoMenosVendido.__eq__(Tipo.PANTALON)) and self.calcular_pantalon(lista_auxiliar) < 2:
                listaAuxiliar.append(Producto("PANTALON", 0.9 * Tipo.PANTALON.value(), 15000))
            elif listaAuxiliar.count(productoParaAgregar) < 5:
                listaAuxiliar.append(productoParaAgregar)

        return listaAuxiliar


    def __lt__(self, otra):
        return self.total < otra.total

    def __str__(self):
        return f"OfertaPorDefecto: productos={self.productos_oferta}, total={self.total}"
