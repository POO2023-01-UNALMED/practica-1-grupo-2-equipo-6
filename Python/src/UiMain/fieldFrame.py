from tkinter import *
from tkinter.ttk import Combobox

class FieldFrame(Frame):
    def __init__(self, ventana, tituloCriterios, criterios, tituloValores, valores, habilitado, texto, comando):
        super().__init__(ventana)
        self.label_criterios = Label(self, text=tituloCriterios)
        self.label_criterios.grid(row=0, column=0, padx=0, pady=0)
        self.label_valores = Label(self, text=tituloValores)
        self.label_valores.grid(row=0, column=0, padx=0, pady=0)
        self.label_criterio = Label(self, text=criterios)
        self.label_criterio.grid(row=0, column=0, padx=0, pady=0)
        self.boton = Button(self, text=texto, command=comando)


        # Crear etiquetas de título para las columnas "Criterio" y "Valor"
        label_criterios = Label(self, text=tituloCriterios)
        label_criterios.grid(row=0, column=0, padx=10, pady=5)
        self.label_criterios = label_criterios

        label_valores = Label(self, text=tituloValores)
        label_valores.grid(row=0, column=1, padx=10, pady=5)
        self.label_valores = label_valores

        # Crear campos de entrada y etiquetas para cada criterio y valor
        self.entries = {}  # Diccionario para almacenar las referencias a los campos de entrada

        for i, criterio in enumerate(criterios):
            # Etiqueta del criterio
            label_criterio = Label(self, text=criterio)
            label_criterio.grid(row=i+1, column=0, padx=10, pady=5)
            self.label_criterio = label_criterio

            # Campo de entrada o lista desplegable para el valor correspondiente
            valor = valores[i] if valores else None
            habilitar = habilitado[i] if habilitado else True

            if isinstance(valor, list):
                # Si el valor es una lista, crear una lista desplegable
                combobox = Combobox(self, state='readonly')
                combobox['values'] = valor
                combobox.grid(row=i+1, column=1, padx=10, pady=5)

                # Guardar la referencia a la lista desplegable en el diccionario
                self.entries[criterio] = combobox
            else:
                # Si el valor es normal, crear una casilla de entrada
                entry_valor = Entry(self, state='normal' if habilitar else 'disabled')
                entry_valor.insert(0, valor)  # Insertar el valor inicial si está presente
                entry_valor.grid(row=i+1, column=1, padx=10, pady=5)

                # Guardar la referencia a la casilla de entrada en el diccionario
                self.entries[criterio] = entry_valor
            
        
        boton = Button(self, text=texto, command=comando)
        self.boton = boton

        boton.grid(row=len(criterios) + 1, columnspan=2, pady=10)
        

    def getBoton(self):
        return self.boton

    def getValue(self, criterio):
        if criterio in self.entries:
            return self.entries[criterio].get()
        else:
            return None

    def obtenerValores(self):
        valores = {}
        for criterio in self.entries:
            campo = self.entries[criterio]
            if isinstance(campo, Entry):
                valor = campo.get()
            elif isinstance(campo, Combobox):
                valor = campo.get()
            else:
                valor = None
            valores[criterio] = valor
        return valores
    
    def getLabelCriterios(self):
        return self.label_criterios

    def getLabelValores(self):
        return self.label_valores
    
    def getLabelCriterio(self):
        return self.label_criterio

#Ejemplo de uso
"""marco_pedido1= Frame(ventana_menu, width=800, height=300,relief="sunken",bd=10)
marco_pedido1.pack()
sets= ["CAMISETA", "CAMISETA,PANTALON", "CAMISETA,ABRIGO,PANTALON"]
cantidades=[1,2,3,4,5]
criterios = ["SET","CANTIDAD"]
valores = [sets,cantidades]
habilitado = [True,True]
field_frame = FieldFrame(marco_pedido1, "Criterio", criterios, "Valor", valores, habilitado,"Realizar Pedido",realizarPedido)
field_frame.pack(side="left")"""
"""Atributos 
Frame->marco_pedido1
Nombre de criterio -> "Criterio"
lista de los nombres de los criterios-> criterios
Nombre del valor-> "Valor"
listas de los Valores (pueden ser listas, un valoriable determinada o None)->valores
habilitado (True si se puede editar o False si no)->habilitado
las listas criterios,valores y habilitados deben tener el mismo numero de elementos cada uno correspondiente entre si
Titulo del boton->"Realizar pedido"
Comando del boton->realizarPedido


"""