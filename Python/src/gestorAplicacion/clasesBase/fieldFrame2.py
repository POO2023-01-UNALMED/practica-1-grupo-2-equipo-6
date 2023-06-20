from tkinter import *
from fieldFrame import FieldFrame
from tkinter.ttk import Scrollbar

class FieldFrame2(Frame):
    def __init__(self, master, tituloCriterios, criterios, tituloValores, valores, habilitado, tipo):
        super().__init__(master)
        self.pack( pady=70, anchor='s')
        self.config(relief=SUNKEN, bd=10)


        self.titulo = Label(self,text="Bienvenido al apartado de archivos", font=("Arial Bold", 16, "bold"))
        self.titulo.pack(side="top", fill="x", padx=80, pady=5)
        self.descripcion_In = Label(self, text="En este apartado, se encuentra a su disposición el archivo con todos los informes {}".format(tipo), font=("Arial", 12))
        self.descripcion_In.pack(side="top", fill="x", padx=80, pady=10)



        # Crear el frame izquierdo con el FieldFrame existente y los dos botones

        self.frame_izquierdo = Frame(self)
        self.field_frame = FieldFrame(self.frame_izquierdo, tituloCriterios, criterios, tituloValores, valores, habilitado, 'Ver', lambda: self.mostrarEleccion)
        self.field_frame.pack(side='top')

        #self.frame_botones = Frame(self.frame_izquierdo)
        self.field_frame.getBoton().configure(width=21)
        #boton1 = Button(self.frame_botones, text='Ver', command=lambda: self.agregarTexto(self.getValue(criterio)))
        boton2 = Button(self.frame_izquierdo, text='Volver al menú principal', command=self.acabar)
        #boton1.grid(row=2, column=0, padx=5, pady=5)
        #boton2.grid(row=3, column=0, padx=5, pady=5)
        #boton1.config(width=10)
        boton2.pack()
        boton2.config(width=21)
        #self.frame_botones.pack(side='bottom')
        self.frame_izquierdo.pack(side='left')
        #self.boton1 = boton1
        self.boton2 = boton2

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




    def agregarTexto(self, texto):
        # Habilitar el widget para poder modificar su contenido
        self.text_widget.configure(state='normal')

        # Borrar el contenido existente
        self.text_widget.delete('1.0', 'end')

        # Insertar el nuevo texto
        self.text_widget.insert('1.0', texto)

        # Deshabilitar el widget nuevamente
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



'''
# Crear la ventana principal
ventana = Tk()
ventana.geometry("1000x620")
ventana.title("Control de calidad")
ventana.config(bg="light blue")
titulo = Label(ventana,text="Bievenido al apartado de archivos", font=("Arial Bold", 16, "bold"), bg="light blue")
titulo.pack(side="top", fill="x", padx=80, pady=(30, 0))
descripcion_In = Label(ventana, text="En este apartado, se genera el informe de calidad con el fin de saber detalladamente\n cada uno de los procesos ejecutados en el departamento de  Control de Calidad", font=("Arial", 12), bg="light blue")
descripcion_In.pack(side="top", fill="x", padx=80, pady=(10,0))
frame_bar = Frame(ventana, height=60, bg="light blue", highlightthickness=3, highlightbackground="black")
frame_bar.pack(padx=80, pady=(50,0), fill="x")


# Crear una instancia de la clase FieldFrame
compras = ["Informe 1","Informe 2", "Informe 3", "Informe 4"]
sets =[]
for compra in compras:
    sets.append(compra)
criterios = ["Informes ventas"]
valores = [sets]
habilitado = [True]

field_frame_2 = FieldFrame2(ventana, "Criterio", criterios, "Valor", valores, habilitado,'')
field_frame_2.pack()


field_frame_2.agregarTexto('Quiero volver a tierras ninas, llévenme a un blando país de aguas. En grandes pastos envejezca y haga al río fábula y fábula.Tenga una fuente por mi madre y en la siesta salga a buscarla, y en jarras baje de una peña un agua dulce, aguda y áspera. Me venza y pare los alientos el agua acérrima y helada. ¡Rompa mi vaso y al beberla me vuelva ninas las entranas!\n\n El Decreto de Guerra a Muerte fue una declaración hecha por el general Simón Bolívar el 15 de junio de 1813 en la ciudad venezolana de Trujillo durante el desarrollo de la Campaña Admirable. La declaración viene precedida meses antes por el Convenio de Cartagena de Antonio Nicolás Briceño. Este decreto significaba que los españoles y canarios que no participasen activamente en favor de la independencia venezolana se les daría la muerte, mientras que a los que lo hicieran "se les invita a vivir entre nosotros pacíficamente"1​. Por otro lado, los americanos serían perdonados, incluso si cooperaban con las autoridades españolas. Además, añadía el objetivo de comprometer de forma irreversible a los individuos con la revolución.')




ventana.mainloop()
'''
