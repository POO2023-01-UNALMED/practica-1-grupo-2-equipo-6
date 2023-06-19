import pickle


class Venta:
    porcentajeBanco = 0

    def __init__(self, comprador):
        self.productos_venta = []
        self.total = 0
        self.comprador = comprador
        self.productos_ofertados = None
        self.ganancias = 0
        self.repartidor = None

    @classmethod
    def asignarPorcentajeBanco(cls,venta):
        if cls.porcentajeBanco != 0:
            Tienda.getCuentaTienda().getEntidad().abonarCuentaAuxiliar(
                cls.porcentajeBanco * venta.getGanancias()
            )
            Tienda.getCuentaTienda().setDinero(
                Tienda.getCuentaTienda().getDinero() - cls.porcentajeBanco * venta.getGanancias()
            )

    def getTotal(self):
        return self.total

    def setTotal(self, total):
        self.total = total

    def getProductosVenta(self):
        return self.productos_venta

    def setProductosVenta(self, productos_venta):
        self.productos_venta = productos_venta

    def getComprador(self):
        return self.comprador

    def setComprador(self, comprador):
        self.comprador = comprador

    def getProductosOfertados(self):
        return self.productos_ofertados

    def setProductosOfertados(self, productos_ofertados):
        self.productos_ofertados = productos_ofertados

    def getGanancias(self):
        return self.ganancias

    def setGanancias(self, ganancias):
        CuentaTienda.get_cuenta_tienda().set_dinero(ganancias + CuentaTienda.get_cuenta_tienda().get_dinero())
        self.ganancias = ganancias

    def getRepartidor(self):
        return self.repartidor

    def setRepartidor(self, repartidor):
        self.repartidor = repartidor

    @classmethod
    def getPorcentajeBanco(cls):
        return cls.porcentajeBanco

    @classmethod
    def setPorcentajeBanco(cls, porcentajeBanco):
        cls.porcentajeBanco = porcentajeBanco

    def __str__(self):
        s = ""
        try:
            for producto in self.productos_venta:
                s += str(producto)
            return f"\n\nProductos vendidos: {s}\n\nTotal de la venta: ${self.total}"
        except AttributeError:
            return "\n\n...No se encontraron productos\npara completar la venta\n\n"
