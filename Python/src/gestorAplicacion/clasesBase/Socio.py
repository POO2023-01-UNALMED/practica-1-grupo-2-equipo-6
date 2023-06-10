from typing import List
import random, Producto

class Socio:
    def __init__(self, nombre: str, productosContrato: List[Producto], renovacionesContratos: List[Venta], cuenta: CuentaBancaria):
        self.productosContrato = productosContrato
        self.nombre = nombre
        self.renovacionesContratos = renovacionesContratos
        self.cuentaSocio = cuenta
        cuenta.getEntidad().getHistorialesCrediticios()[cuenta] = []
        cuenta.setPropietario(self)

    def registrarVenta(self, oferta: OfertaPorDefecto) -> Venta:
        v = Venta(self)
        valorEntero = random.randint(0, 11)
        v.setProductosVenta(self.productosContrato)
        if valorEntero <= 5:
            v.setProductosOfertados(oferta)
            v.setTotal(oferta.getTotal())
            if valorEntero == 5:
                self.productosContrato.extend(oferta.getProductosOferta())
        for p in v.getProductosVenta():
            v.setTotal(v.getTotal() + p.getPrecio())
        self.cuentaSocio.pagar(v)
        self.renovacionesContratos.append(v)
        return v

def getProductosContrato(self):
    return self.productosContrato

def setProductosContrato(self, productosContrato):
    self.productosContrato = productosContrato

def getNombre(self):
    return self.nombre

def setNombre(self, nombre):
    self.nombre = nombre

def getRenovacionesContratos(self):
    return self.renovacionesContratos

def setRenovacionesContratos(self, renovacionesContratos):
    self.renovacionesContratos = renovacionesContratos

def getCuentaSocio(self):
    return self.cuentaSocio

def __str__(self):
    s = ""
    for p in self.productosContrato:
        s += "\n" + str(p) + "\n"
    return "Nombre del socio: {}\n\nProductos a vender: {}".format(self.nombre, s)
