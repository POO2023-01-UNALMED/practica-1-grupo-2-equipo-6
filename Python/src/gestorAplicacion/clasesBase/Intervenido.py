from enum import Enum
import random
from src.gestorAplicacion.clasesBase.Producto import Producto
Tipo=Producto.Tipo


    
    

class Intervenido(Producto):
    class Colecciones(Enum):
        INDUSTRIAL = 1
        GEOMETRIA = 2
        NATURALEZA = 3
    

    def __init__(self, producto,coleccion):
        super().__init__(producto.get_tipo(),producto.get_costo())
        self.coleccion = coleccion
        self.color = ""
        self.corte = ""
        self.precio = 0.0
        self.estampado = ""

    def __str__(self):

        return "*" + self.get_tipo() + " color: " + self.color + ", corte: " + self.corte + ", estampado: " + self.estampado + ", precio: " + str(self.precio)

    def pintar(self, paleta):
        indice_aleatorio = random.randint(0, len(paleta) - 1)
        self.color = paleta[indice_aleatorio]

    def cortar(self):
        tipo = self.get_tipo()
        mapeo_cortes = {
            (Tipo.ABRIGO, Intervenido.Colecciones.GEOMETRIA): "Chaqueta",
            (Tipo.ABRIGO, Intervenido.Colecciones.INDUSTRIAL): "Cazadora",
            (Tipo.ABRIGO, Intervenido.Colecciones.NATURALEZA): "Chal",
            (Tipo.CAMISA, Intervenido.Colecciones.GEOMETRIA): "Corte Cuadrado",
            (Tipo.CAMISA, Intervenido.Colecciones.INDUSTRIAL): "Botones",
            (Tipo.CAMISA, Intervenido.Colecciones.NATURALEZA): "Manga Sisa",
            (Tipo.PANTALON, Intervenido.Colecciones.GEOMETRIA): "Bermuda",
            (Tipo.PANTALON, Intervenido.Colecciones.INDUSTRIAL): "Traje",
            (Tipo.PANTALON, Intervenido.Colecciones.NATURALEZA): "Short",
        }

        corte = mapeo_cortes.get((tipo, self.coleccion))
        if corte:
            self.corte = corte

    def estampar(self):
        tipo = self.get_tipo()
        mapeo_estampados = {
            (Tipo.ABRIGO, Intervenido.Colecciones.GEOMETRIA): "Cuadrado",
            (Tipo.ABRIGO, Intervenido.Colecciones.INDUSTRIAL): "Manchas",
            (Tipo.ABRIGO, Intervenido.Colecciones.NATURALEZA): "Hojas",
            (Tipo.CAMISA, Intervenido.Colecciones.GEOMETRIA): "Triangulo",
            (Tipo.CAMISA, Intervenido.Colecciones.INDUSTRIAL): "Edificio",
            (Tipo.CAMISA, Intervenido.Colecciones.NATURALEZA): "Palma",
            (Tipo.PANTALON, Intervenido.Colecciones.GEOMETRIA): "Rombos",
            (Tipo.PANTALON, Intervenido.Colecciones.INDUSTRIAL): "Rayas",
            (Tipo.PANTALON, Intervenido.Colecciones.NATURALEZA): "Flores",
        }

        estampado = mapeo_estampados.get((tipo, self.coleccion))
        if estampado:
            self.estampado = estampado

    def fijacion_de_precio(self):
        costo=self.costo
        costo=int(costo)
        if self.coleccion == Intervenido.Colecciones.NATURALEZA:
            self.precio = costo + 200000
        elif self.coleccion == Intervenido.Colecciones.INDUSTRIAL:
            self.precio = costo + 300000
        elif self.coleccion == Intervenido.Colecciones.GEOMETRIA:
            self.precio = costo + 400000
        else:
            self.precio = self.get_costo()

    @staticmethod
    def paleta_de_colores(coleccion):
        colores=[]
        if coleccion == Intervenido.Colecciones.NATURALEZA:
            colores = ["VERDE", "AMARILLO", "CAFE", "BEIGE", "OLIVO", "AZUL", "NEGRO"]
        elif coleccion == Intervenido.Colecciones.GEOMETRIA:
            colores = ["AZUL", "NARANJADO", "ROJO", "VERDE", "AMARILLO", "VIOLETA", "NEGRO"]
        elif coleccion == Intervenido.Colecciones.INDUSTRIAL:
            colores = ["GRIS", "AZUL", "VIOLETA", "VERDE", "PLATEADO", "ROJO", "NEGRO"]
        return colores
    @staticmethod
    def colores(coleccion):
        colores=[]
        if coleccion == Intervenido.Colecciones.NATURALEZA:
            colores = ["#00FF00", "#FFFF00", "#964B00", "#F5F5DC", "#808000", "#ADD8E6", "#000000"]
        elif coleccion == Intervenido.Colecciones.GEOMETRIA:
            colores = ["#0000FF", "#FFA500", "#FF0000", "#00FF00", "#FFFF00", "#EE82EE", "#000000"]
        elif coleccion == Intervenido.Colecciones.INDUSTRIAL:
            colores = ["#999999", "#6666CC", "#8080A0", "#669966", "#AAAAAA", "#CC6666", "#555555"]
        return colores

    @staticmethod
    def intervenir(productos, coleccion):
        intervenidos = []
    
        camisas = Producto.clasificar(productos, Tipo.CAMISA)
        pantalones = Producto.clasificar(productos, Tipo.PANTALON)
        abrigos = Producto.clasificar(productos, Tipo.ABRIGO)
        from Python.src.gestorAplicacion.clasesHerencia.Empleado import Empleado
        CARGOS=Empleado.CARGOS
        operarios = Empleado.seleccionar_empleados(CARGOS.OPERARIO, camisas, pantalones, abrigos)
        paleta_de_colores = Intervenido.paleta_de_colores(coleccion)

        if operarios:
            for producto in productos:
                intervenido = Intervenido(producto, coleccion)
                intervenido.cortar()
                intervenido.pintar(paleta_de_colores)
                intervenido.estampar()
                intervenido.fijacion_de_precio()
                intervenidos.append(intervenido)

        return intervenidos

    # Getters
    def get_coleccion(self):
        return self.coleccion

    def get_color(self):
        return self.color

    def get_corte(self):
        return self.corte

    def get_precio(self):
        return self.precio

    def get_estampado(self):
        return self.estampado

    # Setters
    def set_coleccion(self, coleccion):
        self.coleccion = coleccion

    def set_color(self, color):
        self.color = color

    def set_corte(self, corte):
        self.corte = corte

    def set_precio(self, precio):
        self.precio = precio

    def set_estampado(self, estampado):
        self.estampado = estampado

   

    
