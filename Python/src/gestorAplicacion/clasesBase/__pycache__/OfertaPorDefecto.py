import pickle
import Inventariar, Producto
from Producto import Tipo


class OfertaPorDefecto:
    def _init_(self, productos_vendidos):
        self.productos_oferta = self.establecer_oferta(productos_vendidos)
        self.total = sum(p.getPrecio() for p in self.productos_oferta)

    def establecer_oferta(self, productos_vendidos):
        contadores = [0, 0, 0]

        tipo_menos_vendido = None
        lista_auxiliar = []
        contadores[0] = Inventariar.calcular_camisas(productos_vendidos)
        contadores[1] = Inventariar.calcular_abrigos(productos_vendidos)
        contadores[2] = Inventariar.calcular_pantalon(productos_vendidos)
        minimo = min(contadores)

        if contadores[0] <= minimo:
            tipo_menos_vendido = Tipo.CAMISA
        if contadores[1] <= minimo:
            tipo_menos_vendido = Tipo.ABRIGO
        if contadores[2] <= minimo:
            tipo_menos_vendido = Tipo.PANTALON

        producto_para_agregar = Producto(
            tipo_menos_vendido,
            0.9 * tipo_menos_vendido.getPrecioEstandar(),
            15000
        )

        for _ in range(9):
            if (
                tipo_menos_vendido != Tipo.ABRIGO and
                Inventariar.calcular_abrigos(lista_auxiliar) < 2
            ):
                lista_auxiliar.append(
                    Producto(
                        Tipo.ABRIGO,
                        0.9 * Tipo.ABRIGO.getPrecioEstandar(),
                        15000
                    )
                )
            elif (
                tipo_menos_vendido != Tipo.CAMISA and
                Inventariar.calcular_camisas(lista_auxiliar) < 2
            ):
                lista_auxiliar.append(
                    Producto(
                        Tipo.CAMISA,
                        0.9 * Tipo.CAMISA.getPrecioEstandar(),
                        15000
                    )
                )
            elif (
                tipo_menos_vendido != Tipo.PANTALON and
                Inventariar.calcular_pantalon(lista_auxiliar) < 2
            ):
                lista_auxiliar.append(
                    Producto(
                        Tipo.PANTALON,
                        0.9 * Tipo.PANTALON.getPrecioEstandar(),
                        15000
                    )
                )
            elif lista_auxiliar.count(producto_para_agregar) < 5:
                lista_auxiliar.append(producto_para_agregar)

        return lista_auxiliar

    def _lt_(self, other):
        return self.total < other.total
