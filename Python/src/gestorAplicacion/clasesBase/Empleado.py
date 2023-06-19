from enum import Enum

from Deserializador import *
from Serializador import *
from Persona import *
from Producto import *


class Empleado(Persona):
    
    def __init__(self,nombre,calificacion,hd,ht,cargo,sueldo):
        super().__init__(nombre,calificacion)
        self.horas_disponibles = hd
        self.horas_trabajadas = ht
        self.cargo = cargo
        self.sueldo = sueldo
        
    
    
   
        

    class CARGOS(Enum):
        OPERARIO = 1

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

    @staticmethod
    def seleccionar_empleados(cargo, camisas, pantalones, abrigos):
        Disponibles=Deserializador("operarios").getObjeto()
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
            
        
        empleados=[]
        for o in Disponibles:
            empleados.append(o)
        for o in operarios:
            empleados.append(o)
        Soper=Serializador(operarios,"operarios")

        return operarios

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
