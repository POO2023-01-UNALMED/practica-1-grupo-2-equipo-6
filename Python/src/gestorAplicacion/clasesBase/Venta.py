import pickle


class Venta:
    porcentaje_banco = 0

    def _init_(self, comprador):
        self.productos_venta = []
        self.total = 0
        self.comprador = comprador
        self.productos_ofertados = None
        self.ganancias = 0
        self.repartidor = None

    @staticmethod
    def asignar_porcentaje_banco(venta):
        if Venta.porcentaje_banco != 0:
            CuentaTienda.get_cuenta_tienda().get_entidad().abonar_cuenta_auxiliar(
                Venta.porcentaje_banco * venta.ganancias
            )
            CuentaTienda.get_cuenta_tienda().set_dinero(
                CuentaTienda.get_cuenta_tienda().get_dinero() - Venta.porcentaje_banco * venta.ganancias
            )

    def get_total(self):
        return self.total

    def set_total(self, total):
        self.total = total

    def get_productos_venta(self):
        return self.productos_venta

    def set_productos_venta(self, productos_venta):
        self.productos_venta = productos_venta

    def get_comprador(self):
        return self.comprador

    def set_comprador(self, comprador):
        self.comprador = comprador

    def get_productos_ofertados(self):
        return self.productos_ofertados

    def set_productos_ofertados(self, productos_ofertados):
        self.productos_ofertados = productos_ofertados

    def get_ganancias(self):
        return self.ganancias

    def set_ganancias(self, ganancias):
        CuentaTienda.get_cuenta_tienda().set_dinero(ganancias + CuentaTienda.get_cuenta_tienda().get_dinero())
        self.ganancias = ganancias

    def get_repartidor(self):
        return self.repartidor

    def set_repartidor(self, repartidor):
        self.repartidor = repartidor

    @staticmethod
    def get_porcentaje_banco():
        return Venta.porcentaje_banco

    @staticmethod
    def set_porcentaje_banco(porcentaje_banco):
        Venta.porcentaje_banco = porcentaje_banco

    def _str_(self):
        s = ""
        try:
            for producto in self.productos_venta:
                s += str(producto)
            return f"\n\nProductos vendidos: {s}\n\nTotal de la venta: ${self.total}"
        except AttributeError:
            return "\n\n...No se encontraron productos\npara completar la venta\n\n"
