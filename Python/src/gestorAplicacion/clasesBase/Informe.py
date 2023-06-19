from datetime import datetime
from enum import Enum
from ControlCalidad import *

class Informe:

    informesVentas=[]
    informesControl=[]
    informes=0

    def __init__(self, tipoInforme, ventaEfectuada = None, contable=None, puntajeCrediticioActual = None, controlC = None):
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

    class TipoInforme(Enum):
        INFORME_VENTAS = "IVE"
        INFORME_CONTROL_CALIDAD = "CAL"
        INFORME_FINANCIERO = "FIN"

        def __init__(self, identificador):
            self.identificador = identificador

    def setTipoInforme(self, tipoInforme):
        self.tipoInforme = tipoInforme

    def getTipoInforme(self):
        return self.tipoInforme

    def getCodigo(self):
        return self.codigo

    def setCodigo(self, codigo):
        self.codigo=codigo

    @classmethod
    def setInformesControl(cls, informesControl):
        cls.informesControl = informesControl
    
    @classmethod
    def setInformesVentas(cls, informesVentas):
        cls.informesVentas = informesVentas

    @classmethod
    def getInformesControl(cls):
        return cls.informesControl

    @classmethod
    def getInformesVentas(cls):
        return cls.informesVentas

    def __str__(self):
        if self.tipoInforme == Informe.TipoInforme.INFORME_VENTAS:
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
        elif self.tipoInforme == Informe.TipoInforme.INFORME_CONTROL_CALIDAD:
            report = []
            report.append(f"\nCodigo: {self.codigo}\n")
            report.append(f"Tipo de informe: {self.getTipoInforme()}\n")
            report.append(f"Fecha: {datetime.datetime.now().strftime('%d/%m/%Y %H:%M:%S')}\n")
            report.append("Pedido original:\n")
            for p in self.controlC.getCompra().getPedido():
                report.append(f"{p.getNombre()} {p.getPrecio()}\n")
            report.append("\nProductos comprados:\n")
            for p in self.controlC.getCompra().getProductos():
                report.append(f"{p.getNombre()} {p.getPrecio()}\n")
            report.append("\nRevisión:\n")
            if not self.controlC.getRevision():
                report.append("\nNo hay productos para revisar\n")
            else:
                for producto in self.controlC.getRevision():
                    report.append(f"{producto.getNombre()} {producto.getPrecio()}\n")

            if not self.controlC.getProductosDefectuosos():
                report.append("\nNo hay productos defectuosos\n")
            else:
                report.append("\nProductos defectuosos:\n")
                for producto in self.controlC.getProductosDefectuosos():
                    report.append(f"{producto.getNombre()} {producto.getPrecio()}\n")

            if not self.controlC.getProductosAReponerP():
                report.append("\nNo hay productos para reponer por el proveedor\n")
            else:
                report.append("\nProductos repuestos por el proveedor:\n")
                for producto in self.controlC.getProductosAReponerP():
                    report.append(f"{producto.getNombre()} {producto.getPrecio()}\n")

            if not self.controlC.getProductosExtraviados():
                report.append("\nNo hay productos extraviados\n")
            else:
                report.append("\nProductos extraviados:\n")
                for producto in self.controlC.getProductosExtraviados():
                    report.append(f"{producto.getNombre()} {producto.getPrecio()}\n")

            if not self.controlC.getProductosAReponerT():
                report.append("\nNo hay productos para reponer por el transportista\n")
            else:
                report.append("\nProductos repuestos por el transportista:\n")
                for producto in self.controlC.getProductosAReponerT():
                    report.append(f"{producto.getNombre()} {producto.getPrecio()}\n")

            if not self.controlC.getContactarP() and not self.controlC.getContactarT():
                report.append("\nSe debe contactar al proveedor y al transportista\n")
            elif not self.controlC.getContactarP() and self.controlC.getContactarT():
                report.append(f"Calificación del transportista: {self.controlC.getTransportista().getCalificacion()}\n")
                report.append("\nSe debe contactar al proveedor\n")
            elif not self.controlC.getContactarT() and self.controlC.getContactarP():
                report.append(f"\nCalificación del proveedor: {self.controlC.getProveedor().getCalificacion()}\n")
                report.append("\nSe debe contactar al transportista\n")
            else:
                report.append(f"\nCalificación del proveedor: {self.controlC.getProveedor().getCalificacion()}\n")
                report.append(f"Calificación del transportista: {self.controlC.getTransportista().getCalificacion()}\n")

            return "".join(report)
