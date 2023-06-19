from datetime import datetime
from enum import Enum


class Informe:

    informesVentas=[]
    informeControl=[]
    informes=0

    def __init__(self, tipoInforme, ventaEfectuada = None, contable, puntajeCrediticioActual = None
    controlC = None):
        Informe.informes += 1
        self.contable = contable
        self.tipoInforme = tipoInforme
        timestamp = datetime.now().strftime("%d%m%Y%H%M%S")
        self.codigo = tipoInforme.identificador + timestamp + str(Informe.informes)
        self.acreditacionesPagos = []
        self.soluciones = []
        self.cantidadActualDeuda = 0.0
        self.ventaEfectuada = ventaEfectuada
        self.puntajeCrediticioActual = puntajeCrediticio
        self.controlC = controlC


    def getCodigo(self):
        return self.codigo

    def setCodigo(self, codigo):
        self.codigo=codigo

    @classmethod
    def getInformesVentas(cls):
        return cls.informesVentas

    def __str__(self):
        if self.tipoInforme == TipoInforme.INFORME_VENTAS:
            nota = "Notas:"
            if Venta.get_porcentaje_banco() != 0:
                nota += "\nLa tienda tiene una deuda no saldada\n" \
                        "con el banco, por lo cual se ha lle-\n" \
                        "gado al acuerdo de destinar el 20% de\n" \
                        "cada venta a la entidad bancaria."
            return f"------------------------------------------------\n" \
                   f"                Informe venta                   \n" \
                   f"------------------------------------------------\n" \
                   f"\n\nCodigo del Informe: {self.codigo} \nContador: {self.contable}\n" \
                   f"Comprador: {self.ventaEfectuada.get_comprador()}\n" \
                   f"{self.ventaEfectuada}\n" \
                   f"Ganancias netas: ${self.ventaEfectuada.get_ganancias()}\n" \
                   f"{nota}"
