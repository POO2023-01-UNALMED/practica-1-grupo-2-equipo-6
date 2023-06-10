import random
import pickle


class Persona:
    def __init__(self, nombre, calificacion):
        self.calificacion = calificacion
        self.nombre = nombre
        self.cuenta = None

    def calificar(self):
        pass

    @staticmethod
    def generar_productos(lista_productos):
        productos_seleccionados = []
        indices_seleccionados = set()
        cantidad_productos = len(lista_productos)
        cantidad_seleccionada = random.randint(0, cantidad_productos)

        while cantidad_seleccionada > 0:
            indice_aleatorio = random.randint(0, cantidad_productos - 1)
            if indice_aleatorio not in indices_seleccionados:
                producto_seleccionado = lista_productos[indice_aleatorio]
                productos_seleccionados.append(producto_seleccionado)
                indices_seleccionados.add(indice_aleatorio)
                cantidad_seleccionada -= 1

        return productos_seleccionados

    def get_cuenta(self):
        return self.cuenta

    def set_cuenta(self, cuenta):
        self.cuenta = cuenta

    def get_nombre(self):
        return self.nombre

    def set_nombre(self, nombre):
        self.nombre = nombre

    def get_calificacion(self):
        return self.calificacion

    def set_calificacion(self, calificacion):
        self.calificacion = calificacion

    def __str__(self):
        return self.nombre

    def demandar(self):
        return f"{str(self)} ha decidido no demandarlo.\n"

    def __lt__(self, other):
        return self.calificacion > other.calificacion