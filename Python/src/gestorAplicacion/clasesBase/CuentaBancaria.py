import random


from Credito import Credito
from Tienda import Tienda


from enum import Enum

from Transferencia import Transferencia
from Empleado import Empleado
from Transportista import Transportista


class CuentaBancaria:

    def __init__(self, dinero, pais, banco, cuentaLigada=None, cantidadLimite=-1):
        self.propietario = cuentaLigada
        self.transferencias = []
        self.dinero = dinero
        self.IBAN = self.generateIBAN(pais, banco)
        self.pais = pais
        self.entidad = banco
        self.digitoDeControl = random.randint(10, 99)
        self.numeroCuenta = ""
        self.cantidadLimite = cantidadLimite
        self.fondosLigados = []

        if cuentaLigada:
            cuentaLigada.ligarFondo(self)

    def generateIBAN(self, pais, banco):
        while True:
            self.digitoDeControl = random.randint(10, 99)
            self.numeroCuenta = "".join(str(random.randint(1, 9)) for _ in range(10))
            valorPosibleIBAN = f"{pais.value}{self.digitoDeControl}{self.numeroCuenta}"

            if banco.autorizarCuenta(valorPosibleIBAN) is not None:
                break

        return valorPosibleIBAN

    def ligarFondo(self, cuentaLigada):
        self.fondosLigados.append(cuentaLigada)

    def pagarDependientes(self, t, *pagosAdicionales):
        pagoExtra = sum(pagosAdicionales)
        pago = 0

        if isinstance(t, Transportista):
            pago = pagoExtra + t.getPrecioBase()
        elif isinstance(t, Empleado):
            pago = pagoExtra + t.getSueldo()
        else:
            pago = 0

        self.dinero -= pago
        return self.Transferencia(self, t.getCuenta(), pago)

    def pagar(self, compra):
        self.setDinero(self.getDinero() - compra.getProveedorSeleccionado().costoProductos())
        self.setDinero(self.getDinero() - compra.getTransportistaSeleccionado().calcularPrecioTotal(
            compra.getProveedorSeleccionado(), compra.getTienda()))

    def ligarFondo(self, cuentaLigar):
        self.fondosLigados.append(cuentaLigar)

    def getNumeroCuenta(self):
        return self.numeroCuenta

    def setNumeroCuenta(self, numeroCuenta):
        self.numeroCuenta = numeroCuenta

    def getIBAN(self):
        return self.IBAN

    def getDinero(self):
        return self.dinero

    def setDinero(self, dinero):
        self.dinero = dinero

    def getEntidad(self):
        return self.entidad

    def setEntidad(self, entidad):
        self.entidad = entidad

    def getPais(self):
        return self.pais

    def setPais(self, pais):
        self.pais = pais

    def getDigitoDeControl(self):
        return self.digitoDeControl

    def setDigitoDeControl(self, digitoDeControl):
        self.digitoDeControl = digitoDeControl

    def pagar(self, v):

        from Transferencia import Transferencia
        from Tienda import Tienda
        if self.dinero - v.getTotal() < 0:
            self.entidad.generarCredito(Credito(self, v.getTotal(), Credito.Cuota.DOCE))
        else:
            self.dinero -= v.getTotal()
        t = Transferencia.Transferencia(self, Tienda.getCuentaTienda(), v.getTotal())
        self.transferencias.append(t)

    '''def pagar(self, t, *pagosAdicionales):
        pagoExtra = sum(pagosAdicionales)
        pagoTransportista = pagoExtra + t.getPrecioBase()
        self.dinero -= pagoTransportista
        return Transferencia.Transferencia(self, t.getCuenta(), pagoTransportista)'''

    def __str__(self):
        s = f"\n\nCodigo de la cuenta:\n{self.IBAN}\nCantidad de dinero:\n{self.dinero}\n"
        if self.cantidadLimite != -1:
            return s + f"\nCantidad que debera contener:\n\n{self.cantidadLimite}\n\n"
        return s

    def getPropietario(self):
        return self.propietario

    def setPropietario(self, propietario):
        self.propietario = propietario

    def getTransferencias(self):
        return self.transferencias

    def setTransferencias(self, transferencias):
        self.transferencias = transferencias

    def getCantidadLimite(self):
        return self.cantidadLimite

    def setCantidadLimite(self, cantidadLimite):
        self.cantidadLimite = cantidadLimite

    def getFondosLigados(self):
        return self.fondosLigados

    def setFondosLigados(self, fondosLigados):
        self.fondosLigados = fondosLigados

    class Pais(Enum):

        COLOMBIA = "AL"
        BRASIL = "BR"
        DINAMARCA = "DK"
        ALEMANIA = "DE"
        MONACO = "MC"
        CHIPRE = "CY"
        VENEZUELA = "VZ"
