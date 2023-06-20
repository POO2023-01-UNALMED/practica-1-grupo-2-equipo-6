from typing import List
from Producto import *

class Inventariar:
    minCamisas = 1
    minAbrigo = 1
    minPantalon = 1

    @staticmethod
    def calcularCamisas(productos) -> int:
        numCamisas = 0
        for p in productos:
            if p.getNombre() == Producto.Tipo.CAMISA:
                numCamisas += 1
        return numCamisas

    @staticmethod
    def calcularAbrigos(productos) -> int:
        numAbrigos = 0
        for p in productos:
            if p.getNombre() == Producto.Tipo.ABRIGO:
                numAbrigos += 1
        return numAbrigos

    @staticmethod
    def calcularPantalon(productos) -> int:
        numPantalon = 0
        for p in productos:
            if p.getNombre() == Producto.Tipo.PANTALON:
                numPantalon += 1
        return numPantalon