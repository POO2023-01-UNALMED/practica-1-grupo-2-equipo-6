from abc import ABC, abstractmethod
import random
import pickle


class Persona(ABC):
    def __init__(self, nombre, calificacion, cuenta):
        self.calificacion = calificacion
        self.nombre = nombre
        self.cuenta = cuenta
    
    @abstractmethod
    def valorCalificacion(self, *args):
        pass

    @staticmethod
    def generarProductos(lista_productos):
        productosSeleccionados = []
        indices_seleccionados = set()
        cantidad_productos = len(lista_productos)
        cantidad_seleccionada = random.randint(0, cantidad_productos)

        while cantidad_seleccionada > 0:
            indice_aleatorio = random.randint(0, cantidad_productos - 1)
            if indice_aleatorio not in indices_seleccionados:
                producto_seleccionado = lista_productos[indice_aleatorio]
                productosSeleccionados.append(producto_seleccionado)
                indices_seleccionados.add(indice_aleatorio)
                cantidad_seleccionada -= 1

        return productosSeleccionados

    def getCuenta(self):
        return self.cuenta

    def setCuenta(self, cuenta):
        self.cuenta = cuenta

    def getNombre(self):
        return self.nombre

    def setNombre(self, nombre):
        self.nombre = nombre

    def getCalificacion(self):
        return self.calificacion

    def setCalificacion(self, calificacion):
        self.calificacion = calificacion

    def __str__(self):
        return self.nombre

    def demandar(self):
        return f"{str(self)} ha decidido no demandarlo.\n"

    def __lt__(self, other):
        return self.calificacion > other.calificacion