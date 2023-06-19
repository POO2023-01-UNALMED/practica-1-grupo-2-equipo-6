from enum import Enum
import random

from src.baseDatos.Deserializador import Deserializador
from src.baseDatos.Serializador import Serializador
from src.gestorAplicacion.clasesBase.Persona import Persona
from src.gestorAplicacion.clasesBase.Transferencia import Transferencia


class Cliente(Persona):
    class Ciudades(Enum):
        BOGOTA = 15
        MEDELLIN = 10
        CALI = 20
        BARRANQUILLA = 20
        CARTAGENA = 16
        BUCARAMANGA = 17
        PEREIRA = 18
        CUCUTA = 21

    class Tipo(Enum):
        PRIORITARIO = "PRIORITARIO"
        NORMAL = "NORMAL"
        LIBRE = "LIBRE"

    def __init__(self, nombre, calificacion, ciudad, cuenta):
        super().__init__(nombre, calificacion)
        self.ciudad = ciudad
        self.cuenta = cuenta
        self.descuento = 0.0
        self.id = self.asignarIdAleatorio()
        self.confirmacion = "El envío no ha sido confirmado."
        self.resumenDePago = ""

    

    def puntuar(self, intervenidos):
        calificacion = self.get_calificacion() + int(len(intervenidos) * 0.1)
        if calificacion > 5:
            calificacion = 5
        self.set_calificacion(calificacion)
        self.calificar()

    def calificar(self):
        if self.get_calificacion() == 5:
            self.descuento += 0.05
        else:
            self.descuento += self.get_calificacion() * 0.01

    def ResumenDePago(self, pagoTransporte, costo, tipo):
        self.resumenDePago = f"El precio de los productos intervenidos es de ${costo} pesos.\n" \
                             f"El valor de este envío de tipo {tipo} a la ciudad {self.ciudad.name} es de ${pagoTransporte.getCantidad()} pesos.\n" \
                             f"El descuento por cliente preferencial para {self.get_nombre()} es del {self.descuento * 100}%\n" \
                             f"*el cual ya fue aplicado en el valor de envío.\n" \
                             f"El total a pagar es de ${costo + pagoTransporte.getCantidad()} pesos"

    def confirmar(self, tipo):
        diasHabiles = {
            self.Tipo.LIBRE: 30,
            self.Tipo.NORMAL: self.ciudad.value,
            self.Tipo.PRIORITARIO: 5
        }
        self.confirmacion = f"El envío ha sido confirmado llegará en un periodo de {diasHabiles.get(tipo, 10)} días hábiles."

    def __str__(self):
        return f"{self.get_nombre()} id: {self.id}"

    def asignarIdAleatorio(self):
        idAleatorio = random.randint(1000, 9999)
        return idAleatorio
    
    def enviar(self, intervenidos, tipo):
        self.puntuar(intervenidos)
        transportista=Deserializador("transportistaNacional").getObjeto()
        pagoTransporte = transportista.envioNacional(self, intervenidos, tipo)
        costo = sum(intervenido.get_precio() for intervenido in intervenidos)
        self.ResumenDePago(pagoTransporte, costo, tipo)
        transferencias = Transferencia.pagoEnvio(costo, self, pagoTransporte)
        aprobado = all(transferencia.isComprobante() for transferencia in transferencias)
        if aprobado:
            self.confirmar(tipo)
        else:
            self.confirmacion = "El envío no puede ser confirmado debido a un error en las transferencias necesarias, por favor inténtelo más tarde"
        pck=Serializador(transportista,"transportistaNacional")

    def getCiudad(self):
        return self.ciudad

    def setCiudad(self, ciudad):
        self.ciudad = ciudad

    def getCuenta(self):
        return self.cuenta

    def setCuenta(self, cuenta):
        self.cuenta = cuenta

    def getDescuento(self):
        return self.descuento

    def setDescuento(self, descuento):
        self.descuento = descuento

    def getId(self):
        return self.id

    def setId(self, id):
        self.id = id

    def getResumenDePago(self):
        return self.resumenDePago

    def setResumenDePago(self, resumenDePago):
        self.resumenDePago = resumenDePago

    def getConfirmacion(self):
        return self.confirmacion

    def setConfirmacion(self, confirmacion):
        self.confirmacion = confirmacion
