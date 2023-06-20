from OfertaPorDefecto import *
from Producto import *

class OfertaPreferencial(OfertaPorDefecto):
    def _init_(self, productosOferta):
        super().__init__(productosOferta)

    def establecerOferta(self, productos):
        productosOferta = []
        abrigos = self.calcularAbrigos(productos)
        camisas = self.calcularCamisas(productos)
        pantalones = self.calcularPantalon(productos)
        i = 0

        if camisas >= abrigos and camisas >= pantalones:
            while i < 3:
                productosOferta.append(Producto(Producto.Tipo.CAMISA, 0.8 * Producto.Tipo.CAMISA.value, 15000))
                i += 1

            return productosOferta
        elif abrigos >= pantalones and abrigos >= camisas:
            while i < 3:
                productosOferta.append(Producto(Producto.Tipo.ABRIGO, 0.8 * Producto.Tipo.ABRIGO.value, 15000))
                i += 1

            return productosOferta
        else:
            while i < 3:
                productosOferta.append(Producto(Producto.Tipo.PANTALON, 0.8 * Producto.Tipo.PANTALON.value, 15000))
                i += 1

            return productosOferta