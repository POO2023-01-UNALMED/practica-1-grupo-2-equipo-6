from enum import Enum

from Deserializador import *
from Serializador import *
from Persona import *
from Producto import *
from Informe import Informe


class Empleado(Persona):
    class CARGOS(Enum):
        OPERARIO = 1
        ARCHIVISTA = 2
        CONTADOR = 3

    def __init__(self, nombre, calificacion, cuenta, hd, ht, cargo, sueldo):
        super().__init__(nombre, calificacion, cuenta)
        self.horas_disponibles = hd
        self.horas_trabajadas = ht
        self.cargo = cargo
        self.sueldo = sueldo

    def calificar(self):
        if self.cargo == Empleado.CARGOS.OPERARIO:
            if self.calificacion == 5:
                self.horas_disponibles += self.horas_trabajadas // 2
                self.sueldo += 100000
                self.horas_trabajadas = 0
            else:
                calificacion = self.calificacion + round(self.horas_trabajadas / 10)
                if calificacion < 5:
                    calificacion = 5
                self.horas_trabajadas = 0
                self.calificacion = calificacion

    def valorCalificacion(self):
        return 5

    @staticmethod
    def seleccionar_empleados(cargo, camisas, pantalones, abrigos):
        Disponibles = Deserializador("operarios").getObjeto()
        import pickle
        operarios = []
        Disponibles.sort()
        Ccamisas = 0
        Cpantalones = 0
        Cabrigos = 0
        Operario = Disponibles[0]

        operarios.append(Operario)

        for i1 in range(len(camisas)):
            if Operario.horas_disponibles == 0:
                Disponibles.pop(0)
                Operario = Disponibles[0]
                operarios.append(Operario)

            if Ccamisas >= len(camisas):
                break

            if Operario.horas_disponibles != 0:
                Operario.horas_disponibles -= 1
                Operario.horas_trabajadas += 1
                Ccamisas += 1 * Operario.calificacion

        for i1 in range(len(pantalones)):
            if Operario.horas_disponibles == 0:
                Disponibles.pop(0)
                Operario = Disponibles[0]

            if Cpantalones >= len(pantalones):
                break

            if Operario.horas_disponibles != 0:
                Operario.horas_disponibles -= 1
                Operario.horas_trabajadas += 1
                Cpantalones += round(0.5 * Operario.calificacion)

        for i1 in range(len(abrigos)):
            if Operario.horas_disponibles == 0:
                Disponibles.pop(0)
                Operario = Disponibles[0]

            if Cabrigos >= len(abrigos):
                break

            if Operario.horas_disponibles != 0:
                Operario.horas_disponibles -= 1
                Operario.horas_trabajadas += 1
                Cabrigos += round(0.3 * Operario.calificacion)

        for operario in operarios:
            operario.calificar()

        empleados = []
        for o in Disponibles:
            empleados.append(o)
        for o in operarios:
            empleados.append(o)
        Soper = Serializador(operarios, "operarios")

        return operarios

    def generarInformeControlCalidad(self, c, p, t):
        if self.cargo == self.CARGOS.ARCHIVISTA:
            if c.getProveedor() == p and c.getTransportista() == t:
                if c.getContactarP() and not c.getContactarT():
                    p.calificar(c)
                    return Informe(Informe.TipoInforme.INFORME_CONTROL_CALIDAD, c)
                elif not c.getContactarP() and c.getContactarT():
                    t.calificar(c)
                    return Informe(Informe.TipoInforme.INFORME_CONTROL_CALIDAD, c)
                elif c.getContactarP() and c.getContactarT():
                    p.calificar(c)
                    t.calificar(c)
                    return Informe(Informe.TipoInforme.INFORME_CONTROL_CALIDAD, c)
                else:
                    return Informe(Informe.TipoInforme.INFORME_CONTROL_CALIDAD, c)
        return None

    def get_sueldo(self):
        return self.sueldo

    def set_sueldo(self, sueldo):
        self.sueldo = sueldo

    def get_cargo(self):
        return self.cargo

    def set_cargo(self, cargo):
        self.cargo = cargo

    def get_horas_disponibles(self):
        return self.horas_disponibles

    def set_horas_disponibles(self, horas_disponibles):
        self.horas_disponibles = horas_disponibles

    def get_horas_trabajadas(self):
        return self.horas_trabajadas

    def set_horas_trabajadas(self, horas_trabajadas):
        self.horas_trabajadas = horas_trabajadas

    def __lt__(self, other):
        return self.calificacion < other.calificacion