from Banco import Banco
from CuentaBancaria import CuentaBancaria
from CuentaBancaria import Pais

class Tienda:
    cuentaTienda = CuentaBancaria(145000,Pais.COLOMBIA,Banco("BANCOLOMBIA"))

    def __init__(self, presupuesto, bodega, calle):
        self.presupuesto = presupuesto
        self.bodega = bodega
        self.calle = calle

    def getPresupuesto(self):
        return self.presupuesto

    def setPresupuesto(self, presupuesto):
        self.presupuesto = presupuesto

    def getBodega(self):
        return self.bodega

    def setBodega(self, bodega):
        self.bodega = bodega

    def getCalle(self):
        return self.calle

    def setCalle(self, calle):
        self.calle = calle


    @classmethod
    def getCuentaTienda(cls):
        return Tienda.cuentaTienda
