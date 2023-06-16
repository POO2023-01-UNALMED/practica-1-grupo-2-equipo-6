from Python.src.gestorAplicacion.clasesBase.Persona import Persona
from Python.src.gestorAplicacion.clasesBase.Transferencia import Transferencia
from Python.src.gestorAplicacion.clasesHerencia.Cliente import Cliente


class Transportista(Persona):
    def _init_(self, nombre, precioB, precioD, precioC, calificacion, cuenta):
        super().__init__(nombre, calificacion, cuenta)
        self.precioBase = precioB
        self.precioDistancia = precioD
        self.precioCarga = precioC
        self.precioTotal = 0

    @staticmethod
    def mejorTransportista(transportistasPorDefecto):
        transportistasPorDefecto.sort(key=lambda t: t.getCalificacion())
        return transportistasPorDefecto[0]

    def entregaEspecial(self, ofertaSugerida, s, tiendas):
        ventaConfirmada = s.registrarVenta(ofertaSugerida)
        bodegaEscogida = None

        for t in tiendas:
            if t.getBodega().evaluarDisponibilidad(ventaConfirmada.getProductosVenta()):
                bodegaEscogida = t.getBodega()
                break

        if bodegaEscogida is None:
            return None

        ventaConfirmada.setRepartidor(self)
        return ventaConfirmada

    def calcularPrecioTotal(self, provedor, tienda):
        costo = self.precioBase + abs(provedor.getCalle() - tienda.getCalle()) * self.precioDistancia + provedor.getBodega().calcularNumeroProductos() * self.precioCarga
        return costo

    def getPrecioTotal(self):
        return self.precioTotal

    def setPrecioTotal(self, p):
        self.precioTotal = p

    def calificar(self):
        return 5

    def _str_(self):
        return " nombre: " + super().getNombre() + "\nprecio del domicilio: " + str(self.precioTotal)

    def envioNacional(self, cliente, intervenidos, tipo):
        ciudad = cliente.getCiudad()
        precio = 0
        if ciudad == Cliente.Ciudades.BARRANQUILLA:
            precio = 2800
        elif ciudad == Cliente.Ciudades.BOGOTA:
            precio = 2300
        elif ciudad == Cliente.Ciudades.BUCARAMANGA:
            precio = 2900
        elif ciudad == Cliente.Ciudades.CALI:
            precio = 2400
        elif ciudad == Cliente.Ciudades.CARTAGENA:
            precio = 2700
        elif ciudad == Cliente.Ciudades.CUCUTA:
            precio = 2300
        elif ciudad == Cliente.Ciudades.MEDELLIN:
            precio = 1500
        elif ciudad == Cliente.Ciudades.PEREIRA:
            precio = 2300
        else:
            precio = 2000

        tamañoPaquete = len(intervenidos)
        precio += tamañoPaquete * 1000

        if tipo == Cliente.TipoEnvio.PRIORITARIO:
            precio += 15000
        elif tipo == Cliente.TipoEnvio.LIBRE:
            precio -= 15000

        precio -= precio * cliente.getDescuento()
        if precio < 0:
            precio = 0

        transferencia = Transferencia(self.getCuenta(), precio)
        return transferencia

    def valorCalificacion(self, ct):
        calificacion = self.calificar()
        if self == ct.getTransportista():
            productosExtraviados = ct.getProductosExtraviados()
            productosAReponerT = ct.getProductosAReponerT() if ct.getProductosAReponerT() else []

            if not productosExtraviados and not productosAReponerT:
                self.setCalificacion(calificacion)
                return

            totalProductos = len(productosExtraviados)
            productosReemplazados = sum(1 for p in productosExtraviados if p in productosAReponerT)

            if productosReemplazados == totalProductos:
                self.setCalificacion(calificacion)
                return

            porcentajeReemplazo = productosReemplazados / totalProductos

            if porcentajeReemplazo >= 0.8:
                self.setCalificacion(calificacion - 1)
            elif porcentajeReemplazo >= 0.6:
                self.setCalificacion(calificacion - 2)
            elif porcentajeReemplazo > 0:
                self.setCalificacion(calificacion - 3)
            else:
                self.setCalificacion(calificacion - 4)

    def getPrecioBase(self):
        return self.precioBase

    def setPrecioBase(self, precioBase):
        self.precioBase = precioBase

    def getPrecioDistancia(self):
        return self.precioDistancia

    def setPrecioDistancia(self, precioDistancia):
        self.precioDistancia = precioDistancia

    def getPrecioCarga(self):
        return self.precioCarga

    def setPrecioCarga(self, precioCarga):
        self.precioCarga = precioCarga
