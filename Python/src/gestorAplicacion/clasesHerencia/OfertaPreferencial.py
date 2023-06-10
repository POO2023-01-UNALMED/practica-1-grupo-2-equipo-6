from gestorAplicacion.clasesBase import OfertaPorDefecto, Producto, Inventariar

class OfertaPreferencial(OfertaPorDefecto):
    def _init_(self, productosOferta):
        super()._init_(productosOferta)

    def establecerOferta(self, productos):
        productosOferta = []
        abrigos = Inventariar.calcularAbrigos(productos)
        camisas = Inventariar.calcularCamisas(productos)
        pantalones = Inventariar.calcularPantalon(productos)
        i = 0

        if camisas >= abrigos and camisas >= pantalones:
            while i < 3:
                productosOferta.append(Producto(Tipo.CAMISA, 0.8 * Tipo.CAMISA.getPrecioEstandar(), 15000))
                i += 1

            return productosOferta
        elif abrigos >= pantalones and abrigos >= camisas:
            while i < 3:
                productosOferta.append(Producto(Tipo.ABRIGO, 0.8 * Tipo.ABRIGO.getPrecioEstandar(), 15000))
                i += 1

            return productosOferta
        else:
            while i < 3:
                productosOferta.append(Producto(Tipo.PANTALON, 0.8 * Tipo.PANTALON.getPrecioEstandar(), 15000))
                i += 1

            return productosOferta
