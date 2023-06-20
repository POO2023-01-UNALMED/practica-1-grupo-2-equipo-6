import pickle


class Venta:
    porcentajeBanco = 0

    def __init__(self, comprador, productosOfertados = None, repartidor = None):
        self.productosVenta = []
        self.total = 0
        self.comprador = comprador
        self.productosOfertados = productosOfertados
        self.ganancias = 0
        self.repartidor = repartidor

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
        return self.productosVenta

    def setProductosVenta(self, productosVenta):
        self.productosVenta = productosVenta

    def getComprador(self):
        return self.comprador

    def setComprador(self, comprador):
        self.comprador = comprador

    def getProductosOfertados(self):
        return self.productos_ofertados

    def setProductosOfertados(self, productosOfertados):
        self.productosOfertados = productosOfertados

    def getGanancias(self):
        return self.ganancias

    def setGanancias(self, ganancias):
        CuentaTienda.getCuentaTienda().setDinero(ganancias + CuentaTienda.getCuentaTienda().getDinero())
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
