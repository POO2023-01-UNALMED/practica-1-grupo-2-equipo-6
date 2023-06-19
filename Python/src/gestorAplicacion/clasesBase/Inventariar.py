from typing import List
from src.gestorAplicacion.clasesBase import Producto

class Inventariar:
    minCamisas = 1
    minAbrigo = 1
    minPantalon = 1

    @staticmethod
    def calcularCamisas(productos: List[Producto]) -> int:
        numCamisas = 0
        for p in productos:
            if p.getNombre() == "CAMISA":
                numCamisas += 1
        return numCamisas

    @staticmethod
    def calcularAbrigos(productos: List[Producto]) -> int:
        numAbrigos = 0
        for p in productos:
            if p.getNombre() == "ABRIGO":
                numAbrigos += 1
        return numAbrigos

    @staticmethod
    def calcularPantalon(productos: List[Producto]) -> int:
        numPantalon = 0
        for p in productos:
            if p.getNombre() == "PANTALON":
                numPantalon += 1
        return numPantalon
