from CuentaBancaria import CuentaBancaria
import random
from Transferencia import *
from Credito import *
class Tienda:
    from Banco import Banco
    cuentaTienda = CuentaBancaria(145000,CuentaBancaria.Pais.COLOMBIA,Banco("BANCOLOMBIA"))

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
        return cls.cuentaTienda

    @classmethod
    def pagarCuotaMensual(cls, puntajeCrediticio, credito):
        numero = random.uniform(0.0, 10.0)
        puntualidadPago = None

        if (numero >= 0.5):
            puntualidadPago = EstadoPago.A_TIEMPO

        else:
            puntualidadPago=EstadoPago.RETRASADO

        if credito.getEstadoCredito() != Estado.CANCELADO:

            credito.getCuotasPagadas().append(Transferencia(Tienda.getCuentaTienda(), puntajeCrediticio, credito, puntualidadPago, True))
'''  def pagarTodo(puntajeCrediticio,credito):
		numero=random.uniform(0.0, 10.0)
		puntualidadPago;
				if(numero>=0.5) {
					puntualidadPago=EstadoPago.A_TIEMPO;
				}
				else {
					puntualidadPago=EstadoPago.RETRASADO;
				}

				if(credito.getEstadoCredito()!=Estado.CANCELADO) {
					credito.getCuotasPagadas().add(new Transferencia(Tienda.getCuentaTienda(),puntajeCrediticio,credito,puntualidadPago,false));
				}

				}
'''