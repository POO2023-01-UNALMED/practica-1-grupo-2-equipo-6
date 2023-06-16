from enum import Enum

from Python.src.gestorAplicacion.clasesBase.Persona import Persona


class Cliente(Persona):
    class TipoEnvio(Enum):
        PRIORITARIO = 1
        LIBRE = 0

    class Ciudades(Enum):
        BARRANQUILLA = 1
        CUCUTA = 2
        BUCARAMANGA = 3
        CALI = 4
        CARTAGENA = 5
        BOGOTA = 6
        MEDELLIN = 7
        PEREIRA = 8
