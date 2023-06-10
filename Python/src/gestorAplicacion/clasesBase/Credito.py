from enum import Enum
from typing import List

class Credito:
    def _init_(self, cuenta, cantidad, cuotas):
        self.cantidadCredito = cantidad
        self.acreedor = cuenta.getEntidad()
        self.deudor = cuenta
        self.cuotaBaseMensual = cantidad / cuotas.getCantidadMeses()
        self.cantidadAbonada = 0.0
        self.cantidadCuotas = cuotas.getCantidadMeses()
        self.estadoCredito = Estado.PENDIENTE
        self.cuotasPagadas = []

        cuenta.setDinero(cuenta.getDinero() + cantidad)

    def setCantidadAbonada(self, cantidadAbonada):
        self.cantidadAbonada = cantidadAbonada

    def getCantidadCredito(self):
        return self.cantidadCredito

    def setCantidadCredito(self, cantidadCredito):
        self.cantidadCredito = cantidadCredito

    def getAcreedor(self):
        return self.acreedor

    def getDeudor(self):
        return self.deudor

    def getCuotaBaseMensual(self):
        return self.cuotaBaseMensual

    def getCantidadAbonada(self):
        return self.cantidadAbonada

    def getCantidadCuotas(self):
        return self.cantidadCuotas

    def getCuotasPagadas(self):
        return self.cuotasPagadas

    def setCuotasPagadas(self, cuotasPagadas):
        self.cuotasPagadas = cuotasPagadas


class Cuota(Enum):
    DOCE = 12
    DIECIOCHO = 18
    CINCO = 5
    TRES = 3

    def _init_(self, cantidadMeses):
        self.cantidadMeses = cantidadMeses

    def getCantidadMeses(self):
        return self.cantidadMeses

    def setCantidadMeses(self, cantidadMeses):
        self.cantidadMeses = cantidadMeses


class Estado(Enum):
    CANCELADO = "CANCELADO"
    PENDIENTE = "PENDIENTE"
