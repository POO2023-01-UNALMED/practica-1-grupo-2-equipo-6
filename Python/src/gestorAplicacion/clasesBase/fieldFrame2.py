from tkinter import *
from fieldFrame import FieldFrame
from tkinter.ttk import Scrollbar

class FieldFrame2(Frame):
    def __init__(self, master, tituloCriterios, criterios, tituloValores, valores, habilitado, tipo):
        super().__init__(master)
        self.pack( pady=70, anchor='s')
        self.config(relief=SUNKEN, bd=10)
        self.boton2 = None


        self.titulo = Label(self,text="Bienvenido al apartado de archivos", font=("Arial Bold", 16, "bold"))
        self.titulo.pack(side="top", fill="x", padx=80, pady=5)
        self.descripcion_In = Label(self, text="En este apartado, se encuentra a su disposición el archivo con todos los informes {}".format(tipo), font=("Arial", 12))
        self.descripcion_In.pack(side="top", fill="x", padx=80, pady=10)



        # Crear el frame izquierdo con el FieldFrame existente y los dos botones

        self.frame_izquierdo = Frame(self)
        self.field_frame = FieldFrame(self.frame_izquierdo, tituloCriterios, criterios, tituloValores, valores, habilitado, 'Ver', lambda: self.mostrarEleccion (informe))
        self.field_frame.pack(side='top')

        #self.frame_botones = Frame(self.frame_izquierdo)
        self.field_frame.getBoton().configure(width=21)
        #boton1 = Button(self.frame_botones, text='Ver', command=lambda: self.agregarTexto(self.getValue(criterio)))

        #boton1.grid(row=2, column=0, padx=5, pady=5)
        #boton2.grid(row=3, column=0, padx=5, pady=5)
        #boton1.config(width=10)

        #self.frame_botones.pack(side='bottom')
        self.frame_izquierdo.pack(side='left')
        #self.boton1 = boton1


        # Crear el ScrollBar en el frame derecho
        frame_derecho = Frame(self)
        scrollbar = Scrollbar(frame_derecho)
        scrollbar.pack(side=RIGHT, fill=Y)

        # Crear un Text widget en el frame derecho y asociarlo con el ScrollBar
        self.text_widget = Text(frame_derecho, yscrollcommand=scrollbar.set, width=50)
        self.text_widget.pack(side=LEFT, fill=BOTH)
        self.text_widget.config(state='disabled')
        scrollbar.config(command=self.text_widget.yview)
        frame_derecho.pack()


    def agregarTexto(self, texto, insertar=False):
        # Habilitar el widget para poder modificar su contenido
        self.text_widget.configure(state='normal')
        
        if insertar==False:
            self.text_widget.delete('1.0', 'end')
       # Insertar el nuevo texto
        self.text_widget.insert('end', texto) 

        # Deshabilitar el widget nuevamente
        self.text_widget.configure(state='disabled')


    def actualizarTextoScrollbar(self, texto):
        self.text_widget.configure(state='normal')
        # Insertar el nuevo texto
        self.text_widget.insert(END, texto)
        # Hacer scroll hasta la última posición visible
        self.text_widget.see(END)
        self.text_widget.configure(state='disabled')

    def acabar(self):
        self.destroy()

    def ver(self, informe):
        self.agregarTexto(informe)

    
    def mostrarEleccion(self):
        seleccion = self.field_frame.combobox.get()
        self.text_widget.agregarTexto(seleccion)

    def getFrameIzquierdo(self):
        return self.frame_izquierdo

    def getValue(self, criterio):
        return self.field_frame.getValue(criterio)

    def obtenerValores(self):
        return self.field_frame.obtenerValores()

    def getLabelCriterios(self):
        return self.field_frame.getLabelCriterios()

    def getLabelValores(self):
        return self.field_frame.getLabelValores()

    def getLabelCriterio(self):
        return self.field_frame.getLabelCriterio()
