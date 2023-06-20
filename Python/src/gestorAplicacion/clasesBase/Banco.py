from enum import Enum
from typing import List, Dict
from CuentaBancaria import CuentaBancaria
from Credito import Credito
from Transferencia import Transferencia
from Venta import Venta


class Banco:
    class SolucionesProblemaFinanciero(Enum):
        PAGAR_DEUDAS = ") Saldar las deudas pendientes: Hay suficiente cantidad de dinero en la tienda para liquidar las deudas.\n"
        NONE = ") Crear un fondo de empleados o, en caso de ya contar con uno, destinar el 5% del presupuesto actual de la tienda al mismo.\n"
        SOLICITAR_CREDITO = ") Solicitar un nuevo crédito: Puede que la tienda tenga un presupuesto insuficiente para cubrir sus actividades comerciales.\n"
        PORCENTAJE_VENTAS = ") Dar al banco el 20% de las ganancias por cada venta a un socio\n"

        def __init__(self, descripcion):
            self.descripcion = descripcion

        def getDescripcion(self):
            return self.descripcion

        def setDescripcion(self, descripcion):
            self.descripcion = descripcion

    def __init__(self, nombre: str):
        self.nombre = nombre
        self.historialesCrediticios = {}  # type: Dict[CuentaBancaria, List[Credito]]
        self.cuentas = []  # type: List[CuentaBancaria]
        self.historialDePagos = {}  # type: Dict[CuentaBancaria, List[Transferencia]]

    def generarCredito(self, credito: Credito) -> Credito:
        try:
            self.historialesCrediticios[credito.getDeudor()].append(credito)
        except KeyError:
            self.historialesCrediticios[credito.getDeudor()] = [credito]

        return credito


    def getHistorialesCrediticios(self) -> Dict[CuentaBancaria, List[Credito]]:
        return self.historialesCrediticios

    def setHistorialesCrediticios(self, historialesCrediticios: Dict[CuentaBancaria, List[Credito]]):
        self.historialesCrediticios = historialesCrediticios

    def getCuentas(self) -> List[CuentaBancaria]:
        return self.cuentas

    def setCuentas(self, cuentas: List[CuentaBancaria]):
        self.cuentas = cuentas

    def getHistorialDePagos(self) -> Dict[CuentaBancaria, List[Transferencia]]:
        return self.historialDePagos

    def setHistorialDePagos(self, historialDePagos: Dict[CuentaBancaria, List[Transferencia]]):
        self.historialDePagos = historialDePagos

    def autorizarCuenta(self, IBAN: str) -> 'Banco':
        try:
            for c in self.cuentas:
                if c.getIBAN() == IBAN:
                    return None
            return self
        except AttributeError:
            return self
    def getNombre(self) -> str:
        return self.nombre

    def setNombre(self, nombre: str):
        self.nombre = nombre


    def darPuntajeCrediticio(self, cuenta: CuentaBancaria) -> Venta.PuntajeCredito:
        puntuacion = -180
        proporcionRetrasos = 0.0
        AmortizacionFaltante = 0

        try:
            for c in self.historialesCrediticios[cuenta]:
                AmortizacionFaltante = (c.getCuotaBaseMensual() * (c.getCantidadCuotas() - len(c.getCuotasPagadas())))
                puntuacion += 100 * (1 - AmortizacionFaltante / c.getCantidadCredito())

            cantidadDePagos = 0
            cantidadRetrasos = 0

            for t in self.historialDePagos[cuenta]:
                cantidadDePagos += 1
                if t.getPuntualidadPago() == Transferencia.EstadoPago.RETRASADO:
                    cantidadRetrasos += 1

            proporcionRetrasos = cantidadRetrasos / cantidadDePagos

            if proporcionRetrasos < 0.1:
                puntuacion += 50
            elif 0.1 < proporcionRetrasos <= 0.25:
                puntuacion += 30
            elif 0.25 < proporcionRetrasos < 0.50:
                puntuacion += 20
            elif 0.5 <= proporcionRetrasos:
                puntuacion += 5

        except (KeyError, ZeroDivisionError):
            if puntuacion == 0.0:
                return None
            else:
                return self.PuntajeCredito.BAJO

        finally:
            if puntuacion >= 300:
                return self.PuntajeCredito.ALTO
            elif 150 <= puntuacion < 300:
                return self.PuntajeCredito.MEDIO
            else:
                return self.PuntajeCredito.BAJO



    def solucionarProblema(self, deudas: List[Credito], puntaje: Venta.PuntajeCredito) -> List[Transferencia]:
            from Tienda import Tienda
            pagosDeudas = []
            for credito in deudas:
                Tienda.pagarTodo(puntaje, credito)
                pagosDeudas.extend(credito.getCuotasPagadas())
            return pagosDeudas

    def solucionarProblema(self, cantidadDeuda: float) -> CuentaBancaria:
            from Tienda import Tienda
            Venta.setPorcentajeBanco(0.2)
            fondoAuxiliar = CuentaBancaria(0, CuentaBancaria.Pais.COLOMBIA, self, cantidadDeuda, Tienda.getCuentaTienda())
            return fondoAuxiliar

    def abonarCuentaAuxiliar(self, abono: float) -> Transferencia:
            from Tienda import Tienda
            fondoAuxiliar = None
            for cuenta in self.cuentas:
                if isinstance(cuenta.getPropietario(), Banco) and cuenta.getDinero() + abono < cuenta.getCantidadLimite():
                    fondoAuxiliar = cuenta
            return Transferencia(Tienda.getCuentaTienda(), fondoAuxiliar, abono)

    def solucionarProblema(self, c: CuentaBancaria) -> Transferencia:
        from Tienda import Tienda
        return Transferencia(Tienda.getCuentaTienda(), c, Tienda.getCuentaTienda().getDinero() * 0.05)

    def solucionarProblema(self) -> CuentaBancaria:
        from Tienda import Tienda
        return CuentaBancaria(0, CuentaBancaria.Pais.COLOMBIA, self, Tienda.getCuentaTienda().getDinero())

    class PuntajeCredito(Enum):
        BAJO = 16.347
        MEDIO = 10.53
        ALTO = 4.216

        def __init__(self, tasaDeInteres):
            self.tasaDeInteres = tasaDeInteres

        def getTasaDeInteres(self):
            return self.tasaDeInteres

        def setTasaDeInteres(self, tasaDeInteres):
            self.tasaDeInteres = tasaDeInteres
