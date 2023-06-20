from tkinter import *
import tkinter as tk
from tkinter import messagebox, StringVar
from tkinter import messagebox as MessageBox
from tkinter import ttk
from fieldFrame import *
from fieldFrame2 import *
from Excepciones import *
from Persona import *
from Bodega import *
from Cliente import *
from Compra import *
from Proveedor import *
from Transportista import *
from Deserializador import *
from Serializador import *
from Intervenido import *
from Socio import *
from Inventariar import *




class Controladora:

    controlDescripcion = 0
    controlPresentacion = 0
    ventana = None
    frame = None
    frame_mensaje = None

    @classmethod
    def setFrame(cls, frame):

        cls.frame = frame
        # if cls.frame_mensaje != None:
        #     # f = Frame(ventana_menu, width=1000, height=620, bg="light blue")
        #     # f.place(x=0,y=260)
        #     cls.frame_mensaje.config(bg="light blue")
        #     cls.frame_mensaje.pack_forget()

    @classmethod
    def setFrameMensaje(cls, frame):

        if cls.frame_mensaje != None:

            cls.frame_mensaje = frame
        else:
            cls.frame_mensaje = frame



def Salir():
    ventana_inicio.destroy()
    ventana_menu.destroy()


def salirMenu():
    controladora.ventana.destroy()
    controladora.ventana = None
    controladora.setFrame(None)
    ventana_inicio.deiconify()


def ingresar():

    if entrada.get() == "123":
        # ventana_menu.deiconify()
        ventana_inicio.withdraw()

        # ventana2
        ventana_menu = Tk()
        ventana_menu.title("Sistema de administracion")
        ventana_menu.geometry("1000x620")
        ventana_menu.config(bg="light blue")
        # ventana_menu.withdraw()

        # menu

        menu_bar = Menu(ventana_menu)
        ventana_menu.config(menu=menu_bar)

        # menu archivo
        menu_archivo = Menu(menu_bar, tearoff=0)

        menu_bar.add_cascade(label="Archivo", menu=menu_archivo)
        menu_archivo.add_command(label="Aplicacion", command=aplicacion)
        menu_archivo.add_command(label="Salir", command=salirMenu)

        # menu Procesos
        menu_procesos = Menu(menu_bar, tearoff=0)

        menu_bar.add_cascade(label="Procesos y Consultas", menu=menu_procesos)
        menu_procesos.add_command(label="Gestion de alianzas estrategicas", command=gestionAlianzasEstrategicas)
        menu_procesos.add_command(label="Modulo de compra", command=moduloCompra)
        menu_procesos.add_command(label="Control de calidad", command = controlCalidad)
        menu_procesos.add_command(label="Logistica de envios",command=logisticaEnvio)
        menu_procesos.add_command(label="Gestion de creditos")

        # Menu ayuda
        menu_bar.add_command(label="Ayuda", command=ayuda)

        controladora.ventana = ventana_menu


    else:
        messagebox.showerror("Error", "clave incorrecta")

def infoSistema():
    mensaje_bienvenida.config(text="Nuestro sistema de gestión de franquicias es una solución integral diseña-\n" +
                                   "da específicamente para ayudar a los gerentes a enfrentar los desafíos ad-\n" +
                                   "ministrativos que conlleva administrar una franquicia. Este sistema se ha \n" +
                                   "desarrollado teniendo en cuenta las necesidades únicas de las franquicias \n" +
                                   "y busca brindar herramientas y funcionalidades que optimicen la eficiencia\n" +
                                   " operativa y maximicen el éxito.", font=("Arial", 10))
    mensaje_bienvenida.place(x=0, y=0)


def cambioImgPresentacion():
    if controladora.controlPresentacion == 4:
        controladora.controlPresentacion = 0
    else:
        controladora.controlPresentacion += 1

    nueva_imagen = PhotoImage(
        file="presentacion" + str(controladora.controlPresentacion) + ".png")
    img_presentacion.config(image=nueva_imagen)
    img_presentacion.image = nueva_imagen

def mostrarBodega():
    try:


        tiendas = Deserializador("tiendas").getObjeto()
        variableD = controladora.frame.obtenerValores()
        opcion = variableD["tienda:"]

        if opcion == "Tienda Laureles":
            Controladora.setFrameMensaje(Frame(controladora.ventana, width=620, height=350, bd=5))
            Controladora.frame_mensaje.place(x=200, y=200)
            la = Label(Controladora.frame_mensaje, text= f"Tienda Laureles\nPresupuesto:{tiendas[0].getPresupuesto()} \n{tiendas[0].getBodega()}")
            la.place(x=240,y=50)
        elif opcion == "Tienda Poblado":
            Controladora.setFrameMensaje(Frame(controladora.ventana, width=620, height=350, bd=5))
            Controladora.frame_mensaje.place(x=200, y=200)
            la = Label(Controladora.frame_mensaje,
                       text=f"Tienda Poblado\nPresupuesto:{tiendas[1].getPresupuesto()} \n{tiendas[1].getBodega()}")
            la.place(x=240, y=50)

        elif opcion == "Tienda Envigado":
            Controladora.setFrameMensaje(Frame(controladora.ventana, width=620, height=350, bd=5))
            Controladora.frame_mensaje.place(x=200, y=200)
            la = Label(Controladora.frame_mensaje,
                       text=f"Tienda Envigado\nPresupuesto:{tiendas[2].getPresupuesto()} \n{tiendas[2].getBodega()}")
            la.place(x=240, y=50)
        else:
            raise ErrorDatosIncompletos('')

    except ErrorDatosIncompletos:
        MessageBox.showwarning("Error",ErrorDatosIncompletos('Tienda').mostrarMensaje())


def mostrarCompra():

    t = None
    p = None
    tr = None
    con = 0
    try:
        tiendas = Deserializador("tiendas").getObjeto()
        dtiendas = controladora.frame.obtenerValores()
        opcion1 = dtiendas["tienda:"]




        if opcion1 == "Tienda Laureles":
            t = tiendas[0]
        elif opcion1 == "Tienda Poblado":
            t = tiendas[1]
        elif opcion1 == "Tienda Envigado":
            t = tiendas[2]
        else:
            con+=1
            raise ErrorDatosIncompletos()
    except ErrorDatosIncompletos:
        MessageBox.showwarning("Error", ErrorDatosIncompletos('Tienda').mostrarMensaje())

    try:
        proveedo = Deserializador("proveedores").getObjeto()
        dProvee = controladora.frame.obtenerValores()
        opcion2 = dProvee["proveedor:"]

        if opcion2 == "Miguel":
            p = proveedo[0]
        elif opcion2 == "Carla":
            p = proveedo[1]
        elif opcion2 == "Isa":
            p = proveedo[2]
        else:
            con += 1
            raise ErrorDatosIncompletos()
    except ErrorDatosIncompletos:
        MessageBox.showwarning("Error", ErrorDatosIncompletos('Proveedor').mostrarMensaje())

    try:

        trans = Deserializador("transportistas").getObjeto()
        dtrans = controladora.frame.obtenerValores()
        opcion3 = dtrans["Transportista"]

        if opcion3 == "julian":
            tr = trans[0]
        elif opcion3 == "Maria":
            tr = trans[1]
        elif opcion3 == "Andrea":
            tr = trans[2]
        else:
            con += 1
            raise ErrorDatosIncompletos()
    except ErrorDatosIncompletos:
        MessageBox.showwarning("Error", ErrorDatosIncompletos('Transportista').mostrarMensaje())

    if con == 0:
        Controladora.setFrameMensaje(Frame(controladora.ventana, width=620, height=350, bd=5))
        Controladora.frame_mensaje.place(x=200, y=200)
        compra = Compra(t,p,tr)
        compras = Deserializador("compras").getObjeto()


        la = Label(Controladora.frame_mensaje,
                   text=f"{compra}\nCosto: {compra.calcularCosto()}")
        la.place(x=240, y=50)

        compras+=[compra]
        Serializador(compras,"compras")



def cambioDescripcion():
    # gabriel
    img_g1 = PhotoImage(file="gabriel1.png")
    img_g2 = PhotoImage(file="gabriel2.png")
    img_g3 = PhotoImage(file="gabriel3.png")
    img_g4 = PhotoImage(file="gabriel4.png")
    # aleja
    img_a1 = PhotoImage(file="aleja1.png")
    img_a2 = PhotoImage(file="aleja2.png")
    img_a3 = PhotoImage(file="aleja3.png")
    img_a4 = PhotoImage(file="aleja4.png")
    # Carmen
    img_c1 = PhotoImage(file="carmen1.png")
    img_c2 = PhotoImage(file="carmen2.png")
    img_c3 = PhotoImage(file="carmen3.png")
    img_c4 = PhotoImage(file="carmen4.png")
    # Juan
    img_j1 = PhotoImage(file="juan1.png")
    img_j2 = PhotoImage(file="juan2.png")
    img_j3 = PhotoImage(file="juan3.png")
    img_j4 = PhotoImage(file="juan4.png")
    # Mauricio
    img_m1 = PhotoImage(file="mauro1.png")
    img_m2 = PhotoImage(file="mauro2.png")
    img_m3 = PhotoImage(file="mauro3.png")
    img_m4 = PhotoImage(file="mauro4.png")

    listaDescripciones = ["Mi nombre es Gabriel Serrano, tengo 22 anos y estudio \n" +
                          "ciencias de la computacion, me suelen llamar de muchas\n " +
                          "maneras, la maquina, el maravilloso, semidios,\n " +
                          "boycode y la bestia. Me gusta comer, el futbol y las\n"
                          "mujeres",
                          "Mi nombre es Alejandra Echavarría, una estudiante de 19\n" +
                          "años de ingeniería en sistemas e informática. Me apasiona\n" +
                          "la creatividad y me enorgullezco de mi disciplina y resi-\n" +
                          "liencia. Disfruto involucrarme en proyectos manuales, exp-\n" +
                          "lorar nuevos libros y sumergirme en la música.",
                          "Mi nombre es Carmen Johana Calderón Chona. Tengo 19 \naños;" +
                          " estudio Ciencias de la Computación. Me gusta la \nmúsica" +
                          " y los libros, el ajedrez y algunas cosas más.",
                          "Me llamo Juan Fernando Castaño, tengo 20 años. Me \n" +
                          "gustan las matemáticas y programar, me interesa \n" +
                          "especialmente el aprendizaje automático. En mis \n" +
                          "tiempos libres leo, escucho música y comparto con\n" +
                          " mi familia y amigos. Tengo dos mascotas que amo. \n" +
                          "Me gustan también los idiomas.",
                          "Me llamo Johan Ortega, tengo 21 años, estudio Ingeniería\n" +
                          "de sistemas e informática. Me gusta comer, ver anime\n" +
                          "y escuchar música. Subcampeon nacional en taekwondo\n" +
                          "dos años seguidos y amante de los gatos"]
    listaImagenes = [[img_g1, img_g2, img_g3, img_g4], [img_a1, img_a2, img_a3, img_a4],
                     [img_c1, img_c2, img_c3, img_c4], [img_j1, img_j2, img_j3, img_j4],
                     [img_m1, img_m2,img_m3,img_m4]]
    if controladora.controlDescripcion == 4:
        controladora.controlDescripcion = 0
    else:
        controladora.controlDescripcion += 1

    descripcion_integrante.config(text=listaDescripciones[controladora.controlDescripcion])
    img1_descripcion.config(image=listaImagenes[controladora.controlDescripcion][0])
    img1_descripcion.image = listaImagenes[controladora.controlDescripcion][0]
    img2_descripcion.config(image=listaImagenes[controladora.controlDescripcion][1])
    img2_descripcion.image = listaImagenes[controladora.controlDescripcion][1]
    img3_descripcion.config(image=listaImagenes[controladora.controlDescripcion][2])
    img3_descripcion.image = listaImagenes[controladora.controlDescripcion][2]
    img4_descripcion.config(image=listaImagenes[controladora.controlDescripcion][3])
    img4_descripcion.image = listaImagenes[controladora.controlDescripcion][3]


def aplicacion():
    messagebox.showinfo("Aplicacion", "Nuestro sistema de gestión de franquicias es una solución integral diseña" +
                        "da específicamente para ayudar a los gerentes a enfrentar los desafíos ad" +
                        "ministrativos que conlleva administrar una franquicia. Este sistema se ha " +
                        "desarrollado teniendo en cuenta las necesidades únicas de las franquicias " +
                        "y busca brindar herramientas y funcionalidades que optimicen la eficiencia" +
                        " operativa y maximicen el éxito.")


def ayuda():
    messagebox.showinfo("Ayuda",
                        "Autores:\n-JUAN FERNANDO CASTAÑO DURAN\n-CARMEN JOHANA CALDERON CHONA\n-JOHAN MAURICIO ORTEGA IPUCHIMA\n-MARIA ALEJANDRA ECHAVARRIA CORREA\n-GABRIEL ANTONIO SERRANO PINEDA")



def seleccionDeFrames(combo, mensaje):
    global n
    try:

        if combo.get() == "Realizar compra":
            frame_ini = Frame(controladora.ventana, width=1000, height=600, bd=5, bg="light blue")
            frame_ini.place(x=0, y=200)

            if Controladora.frame != None:
                # Controladora.frame.pack_forget()
                n = FieldFrame(frame_ini, "Criterios", ["tienda:", "proveedor:", "Transportista"], "Valor", [["Tienda Laureles", "Tienda Poblado", "Tienda Envigado"], ["Miguel", "Carla", "Isa"], ["julian", "Maria", "Andrea"]], [True, True, True], "continuar",
                                          mostrarCompra)
                Controladora.setFrame(n)

            else:
                Controladora.setFrame(FieldFrame(frame_ini, "Criterios", ["tienda:", "proveedor:", "Transportista"], "Valor", [["Tienda Laureles", "Tienda Poblado", "Tienda Envigado"], ["Miguel", "Carla", "Isa"], ["julian", "Maria", "Andrea"]], [True,True,True], "continuar", mostrarCompra))
            mensaje.config(text="Seleciona un tienda, un transportista y un proveedor para realizar tu compra")
            # controladora.frame.lift()
            controladora.frame.place(x=350, y=0)

        elif combo.get() == "Consultar bodegas":
            frame_ini = Frame(controladora.ventana, width=1000, height=600, bd=5, bg="light blue")
            frame_ini.place(x=0, y=200)

            if Controladora.frame != None:
                # Controladora.frame.pack_forget()
                controladora.setFrame(FieldFrame(frame_ini, "Criterios", ["tienda:"], "Valor", [["Tienda Laureles", "Tienda Poblado", "Tienda Envigado"]], [True],
                                          "continuar", mostrarBodega))

            else:
                controladora.setFrame(
                    FieldFrame(frame_ini, "Criterios", ["tienda:"], "Valor", [["Tienda Laureles", "Tienda Poblado", "Tienda Envigado"]], [True],
                                          "continuar", mostrarBodega))
            mensaje.config(text="Seleciona una tienda para consultar su bodega")
            # controladora.frame.lift()
            controladora.frame.place(x=390, y=0)

        elif combo.get() == "Historial de compras":
            frame_ini = Frame(controladora.ventana, width=1000, height=600, bd=5, bg="light blue")
            frame_ini.place(x=0, y=200)

            comprasD = Deserializador("compras").getObjeto()
            f = Frame(controladora.ventana, width=620, height=350, bd=5)

            lista = Listbox(f, width=97, height=20)
            lista.grid(row=0, column=0)

            barra = Scrollbar(f, orient="vertical", command=lista.yview())
            barra.grid(row=0, column=1, sticky="NSW")

            lista.configure(yscrollcommand=barra.set)
            n=0
            for compra in comprasD:
                n+=1

                lista.insert("end", f'Codigo de compra: {n}')
                lista.insert("end",f'Proveedor: {compra.proveedor}')
                lista.insert("end", f'Transportista: {compra.transportista}')
                lista.insert("end", f'Productos Comprados:')
                lista.insert("end", f'Camisas: {compra.proveedor.bodega.calcularCamisas(compra.proveedor.bodega.productosEnBodega)}')
                lista.insert("end",f'Pantalones: {compra.proveedor.bodega.calcularPantalon(compra.proveedor.bodega.productosEnBodega)}')
                lista.insert("end",f'Abrigos: {compra.proveedor.bodega.calcularAbrigos(compra.proveedor.bodega.productosEnBodega)}')
                lista.insert("end", f"Costo compra: {compra.calcularCosto()}")
                lista.insert("end","")





            Controladora.setFrameMensaje(f)
            Controladora.frame_mensaje.place(x=200, y=200)

            # if Controladora.frame != None:
            #     # Controladora.frame.pack_forget()
            #     controladora.setFrame(
            #         FieldFrame(frame_ini, "Criterios", ["tienda:"], "Valor", [["Tienda Laureles", "Tienda Poblado", "Tienda Envigado"]], [True],
            #                               "continuar", mostrarHistorial))
            #
            # else:
            #     controladora.setFrame(FieldFrame(frame_ini,"Criterios", ["tienda:"], "Valor" ,[["Tienda Laureles", "Tienda Poblado", "Tienda Envigado"]], [True], "continuar", mostrarHistorial))
            # mensaje.config(text="Seleciona un tienda para ver el historial de compra")
            # # controladora.frame.lift()
            # controladora.frame.place(x=390, y=0)

        else:
            raise ErrorDatosIncompletos('')
    except ErrorDatosIncompletos:
        MessageBox.showwarning("Error", ErrorDatosIncompletos('Error').mostrarMensaje())


def gestionAlianzasEstrategicas():
    # Imagenes ventas

    def vender():
        global frameSubbtones
        global Fproductos
        global confirmar
        global socioSeleccionado
        global transportistaSeleccionado
        global transportistas

        try:
            eleccionUsuario = Fsocios.obtenerValores()

            socios = Deserializador('Socios').getObjeto()

            for s in socios:
                if (s.getNombre() == eleccionUsuario['Seleccione un socio']):
                    socioSeleccionado = s
                    break
                if (eleccionUsuario['Seleccione un socio'] == ''):
                    raise ErrorDatosIncompletos('Socio seleccionado')

            productosSocio = socioSeleccionado.getProductosContrato()
            transportistas = Deserializador('transportistas').getObjeto()

            i1 = Inventariar.calcularCamisas(productosSocio)
            i2 = Inventariar.calcularAbrigos(productosSocio)
            i3 = Inventariar.calcularPantalon(productosSocio)

            criterios = ["Camisas[{c}]".format(c=i1), "Pantalones[{b}]".format(b=i2), "Abrigos[{a}]".format(a=i3),
                         "Seleccione un transportista"]

            Fsocios.pack_forget()
            verHistorial.grid_forget()

            valores = [Producto.Tipo.CAMISA.value, Producto.Tipo.PANTALON.value, Producto.Tipo.ABRIGO.value, transportistas]

            habilitado = [False, False, False, True]

            Fproductos = FieldFrame(marco_socio, "Productos", criterios, "Precio por unidad", valores, habilitado,
                                    "Sugerir Oferta", sugerirOferta)

            Fproductos.pack(pady=100)

            confirmar = Button(frameSubbtones, text="Realizar venta", command=confirmarVenta)
            confirmar.grid(row=0, column=0, pady=5)
            # side=BOTTOM, pady=5, anchor="e"



        except ErrorDatosIncompletos:
            MessageBox.showwarning("Error", ErrorDatosIncompletos('Socio seleccionado').mostrarMensaje())

    def calcular():
        global porcentaje
        global porcentajes
        porcentajes = porcentaje.obtenerValores()

        try:
            m = 1
            for i in porcentajes.values():
                o = float(i)
                # print(i)
                if o < 0 or o > 50:
                    raise ErrorDatosIncorrectos()
            porcentaje.setValores([1, 2, 1])

        except Exception:
            MessageBox.showwarning("Error", ErrorDatosIncorrectos().mostrarMensaje())
    def sugerirOferta():

        global Fproductos
        global oferta
        global porcentaje
        global porcentajes
        global ofertaDos
        global transportistaSeleccionado
        global socioSeleccionado

        try:
            for t in transportistas:
                if (t.getNombre() == Fproductos.obtenerValores()['Seleccione un transportista']):
                    transportistaSeleccionado = t
                if Fproductos.obtenerValores()['Seleccione un transportista'] == '':
                    raise ErrorDatosIncompletos('\nTransportista seleccionado')

            vecto = Deserializador('ventasPorDefecto').getObjeto()

            resultadosOfertas = Tienda.sugerirOferta(vecto, socioSeleccionado)
            oferta1 = (resultadosOfertas[0]).getProductosOferta()
            oferta2 = (resultadosOfertas[1]).getProductosOferta()

            i1 = Inventariar.calcularCamisas(oferta1)
            i2 = Inventariar.calcularAbrigos(oferta1)
            i3 = Inventariar.calcularPantalon(oferta1)

            i4 = Inventariar.calcularCamisas(oferta2)
            i5 = Inventariar.calcularAbrigos(oferta2)
            i6 = Inventariar.calcularPantalon(oferta2)





            Fproductos.pack_forget()
            verHistorial.grid_forget()
            confirmar.grid_forget()

            criterios0 = ['Descuento por camisa', 'Descuento por abrigo', 'Descuento por pantalón']
            habilitado = [True, True, True]
            porcentaje = FieldFrame(marco_socio,"Ingrese el porcentaje que desea descontar\n(numeros entre 0.0 y 50.0)", criterios0,"Valor", [0.0, 0.0, 0.0], habilitado, "Calcular", calcular)

            porcentaje.pack(side='top', expand=True, fill='both')


            habilitado = [True, True, True]
            habilitado1 = [False, False, False]

            # "Criterio", criterios, "Valor"
            criterios1 = ['Camisas[{a}]'.format(a=i1), 'Abrigos[{b}]'.format(b=i2),
                           'Pantalones[{c}]'.format(c=i3)]  # Oferta Frecuencia Ventas
            oferta = FieldFrame(marco_socio, 'Producto', criterios1, 'Precio\npor unidad', [Producto.Tipo.CAMISA.value, Producto.Tipo.ABRIGO.value, Producto.Tipo.PANTALON.value], habilitado1,
                                "Realizar venta", lambda: confirmarVenta(1))
            oferta.pack(side='left', expand=True, fill='both')

            criterios2 = ['Camisas[{d}]'.format(d=i4), 'Abrigos[{e}]'.format(e=i4),
                           'Pantalones[{f}]'.format(f=i6)]  # Oferta Preferencial
            # Para la segunda oferta

            ofertaDos = FieldFrame(marco_socio, 'Producto', criterios2, "Precio\npor unidad", [Producto.Tipo.CAMISA.value, Producto.Tipo.ABRIGO.value, Producto.Tipo.PANTALON.value], habilitado1,
                                   "Cancelar oferta", cancelar)
            ofertaDos.pack(side='right', expand=True, fill='both')

            # side=BOTTOM, pady=5, anchor="w"
        except ErrorDatosIncompletos:
            MessageBox.showwarning("Error", ErrorDatosIncompletos('Transportista seleccionado').mostrarMensaje())



    def confirmarVenta():
        global transportistaSeleccionado
        global socioSeleccionado

        siOferta = ''



        venta = transportistaSeleccionado.entregaEspecial(productosVenta)

        if (venta == None):

            MessageBox.showwarning("Error", ErrorProductosInsuficientes().mostrarMensaje())
            siOferta = 'unicamente'
        elif venta.getProductosOferta != None:
            siOferta = ' y los productos ofertados '

        MessageBox.showinfo("Confirmacion de venta",
                            'El socio ha confirmado la compra de los productos del contrato' + siOferta)
        marco_socio.pack_forget()

        contador = Deserializar('contador0').getObjeto()
        archivista = Deserializar('archivista0').getoObjeto()

        historialVentas()

    def cancelar():
        porcentaje.pack_forget()
        oferta.pack_forget()
        ofertaDos.pack_forget()
        vender()

    def seleccionOferta(oferta):
        print('Hola')

    def historialVentas():
        verHistorial.destroy()
        marco_socio.pack_forget()

        '''
        archivista = #Deserializar un archivista
        contador = #Deserializar un contador

        informeVenta = archivista.generarInformeVentas()


        for informe in Informe.getInformesVentas():
            informes.append(informe.getCodigo())
            sets.append(informe)

        criterios = ["Informes ventas"]

        habilitado = [True]

        El valor por defecto del frame de visualización será el último informe
        '''
        criterios = ["Informes ventas"]
        informes = ['producto1', 'producto2', 'producto3']
        habilitado = [True]
        sets = ['i1', 'i2', 'i3']
        valores = [sets]

        field_frame_2 = FieldFrame2(marcoFuncionalidad, "Criterio", criterios, "Valor", valores, habilitado, '')
        field_frame_2.pack()
        field_frame_2.agregarTexto("INSERTE INFORME")








    global marcoFuncionalidad
    global marco_socio
    global Fsocios
    global cabecera

    global Fproductos
    global oferta
    global oferta2
    global frameSubbtones
    global listaO1
    global listaO2
    global imagen

    f = Frame(controladora.ventana, width=1000, height=620, bg="light blue")
    f.place(x=0, y=0)

    marcoFuncionalidad = Frame(f, height=2000, width=1500, bg="gray", pady=10, padx=50, relief="sunken")
    marcoFuncionalidad.place(relx=0.5, rely=0.5, anchor='center')

    cabecera = Entry(marcoFuncionalidad, background="gray", font=('Helvetica', 11, 'bold'), width=50)
    cabecera.insert(0, 'Gestion Alianzas Estrategicas')
    cabecera.configure(justify='center', state='disabled')
    # cabecera.insert(0, "Default Text")
    cabecera.pack(pady=10)

    marco_socio = Frame(marcoFuncionalidad, width=1000, height=500, relief="sunken", bd=10)
    marco_socio.pack(fill='both', expand=True)
    socios = ['Exito', 'Falabella', 'Primark']
    criterios = ["Seleccione un socio"]
    valores = [socios]
    habilitado = [True]

    Fsocios = FieldFrame(marco_socio, "Criterio", criterios, "Valor", valores, habilitado, "Vender", vender)
    Fsocios.pack(pady=110)

    frameSubbtones = Frame(marcoFuncionalidad, bg='gray')
    frameSubbtones.pack()

    verHistorial = Button(frameSubbtones, text="Ver historial de ventas", command=historialVentas)
    verHistorial.grid(row=0, column=0, pady=11)
    # side='bottom', pady=10
def moduloCompra():
    # Nombre funcionalidad
    f = Frame(controladora.ventana, width=1000, height=620, bg="light blue")
    f.place(x=0, y=0)
    frame_1 = Frame(f, width=400, height=50, relief="sunken", bd=5)
    frame_1.place(x=380, y=20)
    mensaje_compra = Label(frame_1, text="Modulo de Compra", font=("arial", 20))
    mensaje_compra.pack()

    # descripcion de funcionalidad
    frame_2 = Frame(f, width=620, height=30, relief="sunken", bd=5)
    frame_2.place(x=200, y=80)
    mensaje_descripcion = Label(frame_2,
                                text="Selecione el proceso que desea realizar, luego introduzca la informacion solicitada en los campos correspondientes")
    mensaje_descripcion.place(x=0, y=0)

    # acciones
    # frame_3 = Frame(ventana_menu,width=600, height=400,relief="sunken",bd=5)
    # frame_3.place(x=200,y=120)

    valor_defecto = StringVar(value="Selecione...")

    combo = ttk.Combobox(controladora.ventana,
                         values=["Consultar bodegas", "Realizar compra", "Historial de compras"],
                         textvariable=valor_defecto)
    combo.place(x=200, y=120)
    combo.bind("<<ComboboxSelected>>", lambda event: seleccionDeFrames(combo, mensaje_descripcion))


def logisticaEnvio():
    ventana_menu = Frame(controladora.ventana, width=1000, height=620, bg="light blue")
    ventana_menu.place(x=0, y=0, relx=0.5, rely=0.5, anchor="center")

    def ConsultaPedido():
        empezar.forget()
        titulo.config(text="Realizar Pedido")
        descripcion.config(
            text="Para realizar el pedido, es necesario que escojas un SET y una CANTIDAD.Los sets son combinaciones de prendas que se pueden pedir en combo.La cantidad establecerá cuántos combos son necesarios.")

        global marco_pedido1
        marco_pedido1 = Frame(ventanaEnvio, width=800, height=300, relief="solid", bd=5)
        marco_pedido1.pack()
        sets = ["ONLY", "TU", "COMPLETO"]
        cantidades = [1, 2, 3, 4, 5]
        nombres = ["SET", "CANTIDAD"]
        valores = [sets, cantidades]
        habilitado = [True, True]
        global Fset
        Fset = FieldFrame(marco_pedido1, "Criterios", nombres, "Pedido", valores, habilitado, "Realizar Pedido",
                          realizarPedido)
        Fset.pack(side="bottom")
        global label
        label = Label(ventanaEnvio,
                      text="Los sets disponibles son:\n- ONLY: compuesto por una camisa.\n- TU: compuesto por una camisa y un pantalón.\n- COMPLETO: compuesto por una camisa, un abrigo y un pantalón",
                      font=("Verdana", 13, "bold"), relief="flat", bg="white", wraplength=450)
        label.pack(pady=7, padx=10)

    def realizarPedido():

        pedido = Fset.obtenerValores()
        try:
            if any(not valor for valor in pedido.values()):
                messagebox.showwarning("Error", ErrorDatosIncompletos('SET', 'CANTIDAD').mostrarMensaje())
                raise ErrorDatosIncompletos('SET', 'CANTIDAD')
        except ErrorDatosIncompletos:
            return

        marco_pedido1.forget()
        label.forget()
        titulo.config(text="Estado del pedido")
        descripcion.config(
            text="Hay tres posibles casos para el pedido: el ideal es que tu pedido se haya completado exitosamente, lo que significa que todos los productos necesarios fueron extraídos de las tiendas. También puede ocurrir que no se haya podido completar todo el pedido, pero sí algunos productos. Por último, existe la opción de que no se haya podido completar ninguno de los productos.")

        pedidoSet = str(pedido["SET"])
        cantidad = int(pedido["CANTIDAD"])
        set = ""
        for item in SETS:
            if item.name == pedidoSet:
                set = item
        global tiendas
        tiendas = Deserializador("tiendas").getObjeto()

        # Primera interacción
        global productos
        productos = Bodega.realizar_pedido(tiendas, set, cantidad)
        global marco_pedido2
        marco_pedido2 = Frame(ventanaEnvio, width=800, height=300, relief="ridge", bd=5)
        resumenpedido = Label(marco_pedido2, text=Bodega.getResumenPedido(), wraplength=200)
        resumenpedido.pack()
        marco_pedido2.pack()
        if Bodega.getResumenPedido() != "El pedido se ha completado exitosamente":
            descripcion.config(
                text="Su pedido no se pudo completar todos los productos necesarios sin embargo puede continuar el proceso de envio con los productos disponibles o realizar los procesos de compra y control de calidad para tener disponibilidad de productos")
            try:
                if Bodega.getResumenPedido().split()[15] == "0" and Bodega.getResumenPedido() != "":
                    descripcion.config(
                        text="No es posible continuar con el proceso de envio si no hay productos disponibles, es necesario que ejecute los procesos de compra y control de calidad para abastecer las tiendas y tener disponibilidad")
                    messagebox.showwarning("Error", ErrorPedido().mostrarMensaje())
                    raise ErrorDatosIncompletos()
            except ErrorDatosIncompletos:
                return

        continuar = Button(marco_pedido2, text="Continuar", command=consultaColeccion)
        continuar.pack()

    def consultaColeccion():
        marco_pedido2.forget()
        titulo.config(text="Coleccion")
        descripcion.config(
            text="La colección que escojas determinará el proceso de intervención de los productos seleccionados. Esto incluye el color, corte y estampado, además del precio final que tendrán los productos intervenidos.")
        global marco_coleccion1
        marco_coleccion1 = Frame(ventanaEnvio, width=800, height=300, relief="solid", bd=5)
        marco_coleccion1.pack()
        colecciones = ["NATURALEZA", "GEOMETRIA", "INDUSTRIAL"]
        criterios = ["COLECCION"]
        valores = [colecciones]
        habilitado = [True]
        global Fcoleccion
        Fcoleccion = FieldFrame(marco_coleccion1, "Colecciones", criterios, "Coleccion", valores, habilitado,
                                "Intervenir productos", intervenir)
        Fcoleccion.pack()

    def caracteristicas():
        string = ""
        for i in intervenidos:
            string += i.__str__() + "\n"

        # Crear una nueva ventana emergente
        ventana_emergente = Toplevel()

        # Configurar propiedades de la ventana emergente
        ventana_emergente.title("Características")
        ventana_emergente.geometry("500x300")

        # Crear un frame con borde
        marco = Frame(ventana_emergente, relief="solid", bd=1)
        marco.pack(pady=20, padx=20)

        # Agregar contenido al frame
        label = Label(marco, text=string)
        label.pack()

        # Mostrar la ventana emergente
        ventana_emergente.mainloop()

    def intervenir():
        colec = Fcoleccion.obtenerValores()
        try:
            if any(not valor for valor in colec.values()):
                messagebox.showwarning("Error", ErrorDatosIncompletos('COLECCION').mostrarMensaje())
                raise ErrorDatosIncompletos('COLECCION')
        except ErrorDatosIncompletos:
            return
        marco_coleccion1.forget()
        descripcion.config(
            text="El proceso de intervención se ha realizado exitosamente. A continuación, se le presentará la paleta de la colección escogida. Haga clic en cualquiera de los colores para ver las características de los productos intervenidos."
            )
        colecSet = str(colec["COLECCION"])
        coleccion = ""
        Colecciones = Intervenido.Colecciones
        for item in Colecciones:
            if item.name == colecSet:
                coleccion = item
        titulo.config(text=coleccion.name)
        # Segunda interacción
        global intervenidos
        intervenidos = Intervenido.intervenir(productos, coleccion)
        colores = Intervenido.colores(coleccion)
        global marco_coleccion2
        marco_coleccion2 = Frame(ventanaEnvio, width=800, height=300, relief="sunken", bd=10)
        resumenpedido = Label(marco_coleccion2, text="Paleta de colores\n", wraplength=200)
        resumenpedido.pack()
        paleta = Frame(marco_coleccion2, width=300, height=200)
        for i, color in enumerate(colores):
            boton = Button(paleta, bg=color, width=10, height=2, command=caracteristicas)
            boton.grid(row=i // 3, column=i % 3, padx=10, pady=10)
        paleta.pack()
        marco_coleccion2.pack()
        continuar = Button(marco_coleccion2, text="Continuar", command=buscarClientes)
        continuar.pack()

    def buscarClientes():
        marco_coleccion2.forget()
        titulo.config(text="Datos de envio")
        descripcion.config(
            text="Para poder procesar el envío, es fundamental que especifique el destinatario al que se dirigirá. Por favor, elija uno de los clientes registrados en nuestra base de datos para completar esta información.")
        global marco_envio1
        marco_envio1 = Frame(ventanaEnvio, width=800, height=300, relief="solid", bd=5)
        marco_envio1.pack()
        global clientes
        clientes = Deserializador("clientes").getObjeto()
        Vclientes = [Deserializador("clientes").getObjeto()]
        criterios = ["Usuario"]
        habilitado = [True]
        global Fclientes
        Fclientes = FieldFrame(marco_envio1, "Criterio", criterios, "Usuario", Vclientes, habilitado, "Buscar Cliente",
                               consultaEnvio)
        Fclientes.pack()

    def consultaEnvio():

        dicC = Fclientes.obtenerValores()
        try:
            if any(not valor for valor in dicC.values()):
                messagebox.showwarning("Error", ErrorDatosIncompletos('USUARIO').mostrarMensaje())
                raise ErrorDatosIncompletos('USUARIO')
        except ErrorDatosIncompletos:
            return
        usuario = dicC["Usuario"]
        global cliente
        cliente = ""
        for c in clientes:
            if usuario == c.__str__():
                cliente = c
        string = "Los datos actuales del usuario escogido indican una calificacion de " + str(
            cliente.getCalificacion()) + " puntos,la ciudad registrada es " + cliente.getCiudad().name + ". Por favor confirme o cambie el destino del envio.Además seleccione el tipo de envio."
        marco_envio1.forget()
        titulo.config(text=usuario)
        descripcion.config(text=string)
        global marco_envio2
        marco_envio2 = Frame(ventanaEnvio, width=800, height=300, relief="solid", bd=5)
        marco_envio2.pack()
        ciudades = [ciudad.name for ciudad in Cliente.Ciudades]

        tipos = ["NORMAL", "PRIORITARIO", "LIBRE"]
        criterios = ["Ciudades disponibles", "Tipo de Envio"]
        valores = [ciudades, tipos]
        habilitado = [True, True]
        global Fenvio
        Fenvio = FieldFrame(marco_envio2, "Criterio", criterios, "Valor", valores, habilitado, "Realizar Envio", enviar)
        Fenvio.pack()
        global label
        label = Label(ventanaEnvio,
                      text="Los envios prioritarios tienen un sobrecargo de 15.000 y demoran 5 dias habiles Los envios libres tienen un descuento de 15.000 y demoran 30 dias habiles. Los envios normales variaran su costo y dias habiles dependiendo de la ciudad.",
                      font=("Verdana", 13, "bold"), relief="flat", bg="white", wraplength=450)
        label.pack(pady=7, padx=10)

    def enviar():
        Senvio = Fenvio.obtenerValores()
        try:
            if any(not valor for valor in Senvio.values()):
                messagebox.showwarning("Error",
                                       ErrorDatosIncompletos('Ciudades disponibles', 'Tipo Envio').mostrarMensaje())
                raise ErrorDatosIncompletos('Ciudades disponibles', 'Tipo Envio')
        except ErrorDatosIncompletos:
            return

        marco_envio2.forget()
        label.forget()
        Sciudad = Senvio["Ciudades disponibles"]
        Stipo = Senvio["Tipo de Envio"]
        ciudad = ""
        tipo = ""
        for item in Cliente.Ciudades:
            if item.name == Sciudad:
                ciudad = item
                break
        for item in Cliente.Tipo:
            if item.name == Stipo:
                tipo = item
                break

        titulo.config(text="Proceso de Logistica de envio finalizado")
        descripcion.config(
            text="El proceso de envío ha sido finalizado. A continuación, se mostrará en pantalla la factura del pedido y la confirmación del envío una vez se hayan procesado todas las transferencias necesarias."
            )
        # Tercera interacción
        cliente.setCiudad(ciudad)
        cliente.enviar(intervenidos, tipo)
        pck = Serializador(clientes, "clientes")
        pck = Serializador(tiendas, "tiendas")

        global marco_envio3
        marco_envio3 = Frame(ventanaEnvio, width=800, height=300, relief="sunken", bd=10)
        resumenPago = Label(marco_envio3, text=cliente.getResumenDePago(), font=("Arial", 10), wraplength=800)
        resumenPago.pack(pady=10)
        confirmacion = Label(marco_envio3, text=cliente.getConfirmacion(), font=("Arial", 10), wraplength=200)
        confirmacion.pack(pady=10)
        marco_envio3.pack()

    ventanaEnvio = Frame(ventana_menu, bg="#AB91C1", width=600, height=500, relief="ridge", bd=10)
    ventanaEnvio.place(relx=0.5, rely=0.5, anchor="center")
    ventanaEnvio.pack_propagate(False)

    titulo = Label(ventanaEnvio, text="Logistica de Envio ", font=("Arial", 16, "bold"), relief="raised", bg="#FFA532")

    titulo.pack(side="top", pady=10)

    frame_descripcion = Frame(ventanaEnvio, bg="white", relief="groove", bd=5, width=500, height=105)

    descripcion = Label(frame_descripcion,
                        text="Bienvenido a la funcionalidad Logística de Envío. En esta sección, podrás realizar un pedido de los productos que necesitas, gestionar su intervención según la colección a la que pertenezcan y, finalmente, llevar a cabo las transferencias correspondientes para confirmar el envío de los productos."
                        , font=("Verdana", 10), wraplength=450, bg="white")
    descripcion.pack()
    frame_descripcion.pack(pady=20)
    frame_descripcion.pack_propagate(False)

    empezar = Button(ventanaEnvio, text="Empezar proceso", command=ConsultaPedido)
    empezar.pack(pady=50)

def controlCalidad():
    def administradorVentanas():
        opcion = field_frame.obtenerValores()['Opciones']
        compraRevisada = False
        compras = Deserializador("compras").getObjeto()
        for compra in compras:
            if compra.getRevisado() == True:
                compraRevisada= True
                break
        try:
            try:
                if opcion == "Realizar revisión":
                    ventanaRevision()
                elif opcion == "Contactar al proveedor/transportista":
                    if compraRevisada == False:
                        raise ErrorRevision()
                    ventanaContactar()
                elif opcion == "Consultar bodega":
                    if compraRevisada == False:
                        raise ErrorRevision()
                    try:
                        control = Deserializador("control").getObjeto()
                        if control.getContactarP() == False and control.getContactarT() == False:
                            raise ErrorNoContacted()
                        else:
                            ventanaConsultarB()
                    except ErrorNoContacted as e:
                            messagebox.showerror("Error", e.message)
                elif opcion == "Informe de calidad":
                    if compraRevisada == False:
                        raise ErrorRevision()
                    ventanaInforme()
                else:
                    raise ErrorDatosIncompletos("Opciones")
            except ErrorDatosIncompletos as e:
                messagebox.showerror("Error", e.message)
        except ErrorRevision as e:
            messagebox.showerror("Error", e.message)

    def ventanaRevision():
        def capturarOpcion():
            try:
                opcion = field_frame_re.obtenerValores()['Compras'] 
                for i in range(len(compras)):
                    if str(opcion) == "Compra " + str(i + 1):
                        indice = i
                if not opcion:
                    raise ErrorDatosIncompletos("Compras")
                try:
                    control = ControlCalidad(compras[indice])
                    if (compras[indice]).getRevisado() == True:
                        raise ErrorCompraRevisada()
                    control.revisar(compras[indice])
                    field_frame_re.forget()
                    ventana = Frame(controladora.ventana, width=1000, height=620, bg="light blue")   
                    ventana.place(x=0,y=0,relx=0.5, rely=0.5, anchor='center')
                    frameI= Frame(ventana, width=1000, height=60, bg="light blue")
                    frameI.place(x=95, y=20)
                    proceso = Label(frameI, text="Revisión", font=("Arial Bold", 14, "bold"), bg="light blue")
                    proceso.pack()
                    descripcion_proceso = Label(frameI, text="A continuación, se le presenta la revisión de la compra seleccionada. ", font=("Arial", 12), bg="light blue")
                    descripcion_proceso.pack( fill= 'both', expand=True)
                    scroll = Scrollbar(frameI)
                    scroll.pack(side="right", fill="y")
                    text_widget = Text(frameI, yscrollcommand=scroll.set, width=100, height=20)
                    text_widget.pack(side="left", fill="both")
                    scroll.config(command=text_widget.yview)
                    
                    text_widget.insert("end", "La revisión de la " + "Compra " + str(indice + 1) + " es: \n")
                    boton = Button(ventana, text="Continuar", command=ventanaContactar, font=("Arial", 12), highlightthickness=3, highlightbackground="black")
                    boton.place(x =450, y=450)
                    for producto in control.getRevision():
                        text_widget.insert("end", str(producto) + "\n")
                    text_widget.config(state="disabled")
                    pck = Serializador(control, "control")
                    pck2 = Serializador(compras, "compras")
                except ErrorCompraRevisada as e:
                    messagebox.showerror("Error", e.message)

            except ErrorDatosIncompletos as e:
                messagebox.showerror("Error", e.message) 

        ventana = Frame(controladora.ventana, width=1000, height=620, bg="light blue")
        ventana.place(x=0,y=0,relx=0.5, rely=0.5, anchor='center')
        frameI= Frame(ventana, width=1000, height=100, bg="light blue")
        frameI.place(x=190, y=100)
        procesoR = Label(frameI, text="Revisión", font=("Arial Bold", 16, "bold"), bg="light blue")
        procesoR.pack()
        descripcion_r = Label(frameI, text="En este apartado se revisan las compras con el fin de detectar productos \n defectuosos, así como los extraviados.", font=("Arial", 12), bg="light blue")
        descripcion_r.pack(fill="both", expand = True)
        global compras
        
        compras = Deserializador("compras").getObjeto()

        sets = []
        
        for i in range(len(compras)):
            sets.append("Compra " + str(i+1))
        criterios = ["Compras"]
        valores = [sets]
        habilitado = [True]
        ventana.place_forget()
        
        pck = Serializador(compras, "compras")

        
        global field_frame_re

        field_frame_re = FieldFrame2(controladora.ventana, None, criterios, "Valor", valores, habilitado, "Revisión de la compra asociada:")
        field_frame_re.titulo.config(text="Revisión de la compra asociada:")
        field_frame_re.descripcion_In.config(text="En este apartado se revisan las compras con el fin de detectar productos \n defectuosos, así como los extraviados.")
        field_frame_re.boton2.config(text="Continuar", command=capturarOpcion)
        field_frame_re.field_frame.getBoton().destroy()
    
        
        cont = 0

        for compra in compras:
            cont += 1
            s = '\nCompra ' + str(cont) + " \n"
            field_frame_re.agregarTexto(s, True)
            for producto in compra.getProveedor().getBodega().getProductosEnBodega():
                field_frame_re.actualizarTextoScrollbar(str(producto))
            field_frame_re.agregarTexto("\n", True)

    def ventanaContactar():
        def capturarOpcion():
            def contacT():
                control = Deserializador("control").getObjeto()
                control.contactar(control.getTransportista())
                field_frame_c.forget()
                ventana = Frame(controladora.ventana, width=1000, height=620, bg="light blue")
                ventana.place(x=0,y=0,relx=0.5, rely=0.5, anchor='center')
                frameI= Frame(ventana, width=1000, height=100, bg="light blue")
                frameI.place(x=60, y=20)

                procesoR = Label(frameI, text="Contactar al proveedor/transportista", font=("Arial Bold", 16, "bold"), bg="light blue")
                procesoR.pack(side="top", fill="x", padx=80, pady=(30, 0))
                descripcion_r = Label(frameI, text="A continuación se le presentan los productos extraviados que el transportista repuso.", font=("Arial", 12), bg="light blue")
                descripcion_r.pack(side="top", fill="x", padx=80, pady=(15,0))
                frameScrollR = Frame(frameI, background="white", height=60)
                frameScrollR.pack(padx=50, pady=10, fill="x")
                frameScrollR.config(highlightbackground="black", highlightthickness=3)
                scroll = Scrollbar(frameScrollR)
                scroll.pack(side="right", fill="y")
                text_widget = Text(frameScrollR, yscrollcommand=scroll.set, width=100, height=20)
                text_widget.pack(side="left", fill="both")
                scroll.config(command=text_widget.yview)
                if control.getProductosAReponerT() == []:
                    text_widget.insert("end", "El transportista no ha repuesto productos")
                    text_widget.config(state="disabled")
                else:
                    text_widget.insert("end", "Productos repuestos por el transportista: \n")
                    for producto in control.getProductosAReponerT():
                        text_widget.insert("end", str(producto))
                    text_widget.config(state="disabled")
                boton1 = Button(frameI, text="Continuar", font=("Arial", 12), command=ventanaConsultarB)
                boton1.pack(pady=10)
                pck = Serializador(control, "control")
            def contacP():
                control = Deserializador("control").getObjeto()
                control.contactar(control.getProveedor())
                field_frame_c.forget()
                ventana = Frame(controladora.ventana, width=1000, height=620, bg="light blue")
                ventana.place(x=0,y=0,relx=0.5, rely=0.5, anchor='center')
                frameI= Frame(ventana, width=1000, height=100, bg="light blue")
                frameI.place(x=60, y=20)

                procesoR = Label(frameI, text="Contactar al proveedor/transportista", font=("Arial Bold", 16, "bold"), bg="light blue")
                procesoR.pack(side="top", fill="x", padx=80, pady=(30, 0))
                descripcion_r = Label(frameI, text="A continuación se le presentan los productos defectusos que el proveedor repuso.", font=("Arial", 12), bg="light blue")
                descripcion_r.pack(side="top", fill="x", padx=80, pady=(15,0))
                frameScrollR = Frame(frameI, background="white", height=60)
                frameScrollR.pack(padx=50, pady=10, fill="x")
                frameScrollR.config(highlightbackground="black", highlightthickness=3)
                scroll = Scrollbar(frameScrollR)
                scroll.pack(side="right", fill="y")
                text_widget = Text(frameScrollR, yscrollcommand=scroll.set, width=100, height=20)
                text_widget.pack(side="left", fill="both")
                scroll.config(command=text_widget.yview)

                if control.getProductosAReponerP() == []:
                    text_widget.insert("end", "El proveedor no ha repuesto productos")
                    text_widget.config(state="disabled")
                else:
                    text_widget.insert("end", "Productos repuestos por el proveedor: \n")
                    for producto in control.getProductosAReponerP():
                        text_widget.insert("end", str(producto))
                    text_widget.config(state="disabled")
                boton1 = Button(frameI, text="Continuar", font=("Arial", 12), command=ventanaConsultarB)
                boton1.pack(pady=10)
                pck = Serializador(control, "control")
            try:
                opcion = field_frame_c.obtenerValores()["Contactar"]
                if not opcion:
                    raise ErrorDatosIncompletos("Contactar")
                if opcion == "Proveedor":
                    control = Deserializador("control").getObjeto()
                    control.contactar(control.getProveedor())
                    field_frame_re.forget()
                    ventana = Frame(controladora.ventana, width=1000, height=620, bg="light blue")   
                    ventana.place(x=0,y=0,relx=0.5, rely=0.5, anchor='center')
                    frameI= Frame(ventana, width=1000, height=60, bg="light blue")
                    frameI.place(x=60, y=20)
                    procesoR = Label(frameI, text="Contactar al proveedor/transportista", font=("Arial Bold", 16, "bold"), bg="light blue")
                    procesoR.pack(side="top", fill="x", padx=80, pady=(30, 0))
                    descripcion_r = Label(frameI, text="A continuación se le presentan los productos defectusos que el proveedor repuso.", font=("Arial", 12), bg="light blue")
                    descripcion_r.pack(side="top", fill="x", padx=80, pady=(15,0))
                    frameScrollR = Frame(frameI, background="white", height=60)
                    frameScrollR.pack(padx=50, pady=10, fill="x")
                    frameScrollR.config(highlightbackground="black", highlightthickness=3)
                    scroll = Scrollbar(frameScrollR)
                    scroll.pack(side="right", fill="y")
                    text_widget = Text(frameScrollR, yscrollcommand=scroll.set, width=100, height=20)
                    text_widget.pack(side="left", fill="both")
                    scroll.config(command=text_widget.yview)
                    if control.getProductosAReponerP() == []:
                        text_widget.insert("end", "El proveedor no ha repuesto productos")
                        text_widget.config(state="disabled")
                    else:
                        text_widget.insert("end", "Productos repuestos por el proveedor: \n")
                        for producto in control.getProductosAReponerP():
                            text_widget.insert("end", str(producto))
                        text_widget.config(state="disabled")
                    if control.getContactarT() == False:
                        frameBotones = Frame(frameI, bg="light blue")
                        frameBotones.pack(pady=10)
                    
                        boton1 = Button(frameBotones, text="Continuar", font=("Arial", 12), command=ventanaConsultarB)
                        boton1.pack(side="left", padx=10)
                    
                        boton2 = Button(frameBotones, text="Contactar al transportista", font=("Arial", 12), command= contacT)
                        boton2.pack(side="left", padx=10)
                    else:
                        boton1 = Button(frameI, text="Continuar", font=("Arial", 12), command=ventanaConsultarB)
                        boton1.pack(pady=10)
                    pck = Serializador(control, "control")
                else:
                    control = Deserializador("control").getObjeto()
                    control.contactar(control.getTransportista())
                    field_frame_re.forget()
                    ventana = Frame(controladora.ventana, width=1000, height=620, bg="light blue")   
                    ventana.place(x=0,y=0,relx=0.5, rely=0.5, anchor='center')
                    frameI= Frame(ventana, width=1000, height=60, bg="light blue")
                    frameI.place(x=60, y=20)
                    procesoR = Label(frameI, text="Contactar al proveedor/transportista", font=("Arial Bold", 16, "bold"), bg="light blue")
                    procesoR.pack(side="top", fill="x", padx=80, pady=(30, 0))
                    descripcion_r = Label(frameI, text="A continuación se le presentan los productos extraviados que el transportista repuso.", font=("Arial", 12), bg="light blue")
                    descripcion_r.pack(side="top", fill="x", padx=80, pady=(15,0))
                    frameScrollR = Frame(frameI, background="white", height=60)
                    frameScrollR.pack(padx=50, pady=10, fill="x")
                    frameScrollR.config(highlightbackground="black", highlightthickness=3)
                    scroll = Scrollbar(frameScrollR)
                    scroll.pack(side="right", fill="y")
                    text_widget = Text(frameScrollR, yscrollcommand=scroll.set, width=100, height=20)
                    text_widget.pack(side="left", fill="both")
                    scroll.config(command=text_widget.yview)
                    if control.getProductosAReponerT() == []:
                        text_widget.insert("end", "El transportista no ha repuesto productos")
                        text_widget.config(state="disabled")
                    else:
                        text_widget.insert("end", "Productos repuestos por el transportista: \n")
                        for producto in control.getProductosAReponerT():
                            text_widget.insert("end", str(producto))
                        text_widget.config(state="disabled")
                    if control.getContactarP() == False:
                        frameBotones = Frame(frameI, bg="light blue")
                        frameBotones.pack(pady=10)

                        boton1 = Button(frameBotones, text="Continuar", font=("Arial", 12), command=ventanaConsultarB)
                        boton1.pack(side="left", padx=10)

                        boton2 = Button(frameBotones, text="Contactar al proveedor", font=("Arial", 12), command = contacP)
                        boton2.pack(side="left", padx=10)
                    else:
                        boton1 = Button(frameI, text="Continuar", font=("Arial", 12), command=ventanaConsultarB)
                        boton1.pack(pady=10)
                    pck = Serializador(control, "control")
            except ErrorDatosIncompletos as e:
                messagebox.showerror("Error", e.message)
            

        field_frame_re.forget()
        ventana = Frame(controladora.ventana, width=1000, height=620, bg="light blue")   
        ventana.place(x=0,y=0,relx=0.5, rely=0.5, anchor='center')
        frameI= Frame(ventana, width=1000, height=60, bg="light blue")
        frameI.place(x=25, y=20)
        procesoC = Label(frameI, text="Contactar al proveedor/transportista", font=("Arial Bold", 16, "bold"), bg="light blue")
        procesoC.pack(side="top", fill="x", padx=80, pady=(30, 0))

        descripcion_c = Label(frameI, text="En este apartado, se establece contacto con el proveedor y/o transportista \n para facilitar la devolución de productos defectuosos y resolver situaciones de extravío.", font=("Arial", 12), bg="light blue")
        descripcion_c.pack(side="top", fill="x", padx=80, pady=20)
        frame_c = Frame(frameI, height=60, bg="light blue")
        frame_c.pack(padx=80, pady=10, fill="x")
        opcionesRe = Label(frame_c, text="Por favor despliegue el menú correspondiente a Contactar para visualizar  al proveedor y transportista.", font=("Arial", 12, "bold"), background="light blue")
        opcionesRe.grid(row=0, column=0, padx=10, pady=10, sticky="w")


        contact = ["Proveedor","Transportista"]
        sets = []
        for persona in contact:
            sets.append(persona)
        criterios = ["Contactar"]
        valores = [sets]
        habilitado = [True]
        field_frame_c = FieldFrame(frameI, "Criterio", criterios, "Valor", valores, habilitado, "Contactar", None)
        field_frame_c.config(highlightthickness=3, highlightbackground="black")
        field_frame_c.getLabelCriterios().config(font=("Arial", 12, "bold"), padx=30, pady=10)
        field_frame_c.getLabelValores().config(font=("Arial", 12, "bold"), padx=30, pady=10)
        field_frame_c.getLabelCriterio().config(font=("Arial", 12), padx=30, pady=20)
        field_frame_c.getBoton().config(font=("Arial", 12))
        field_frame_c.pack(padx=10, pady=20)
        field_frame_c.getBoton().config(font=("Arial", 12), command=capturarOpcion)

    def ventanaConsultarB():
        control = Deserializador("control").getObjeto()
        field_frame_re.forget()
        ventana = Frame(controladora.ventana, width=1000, height=620, bg="light blue")   
        ventana.place(x=0,y=0,relx=0.5, rely=0.5, anchor='center')
        frameI= Frame(ventana, width=1000, height=60, bg="light blue")
        frameI.place(x=25, y=20)
        proceso = Label(frameI, text="Consultar Bodega", font=("Arial Bold", 14, "bold"), bg="light blue")
        proceso.pack()
        descripcion_proceso = Label(frameI, text="A continuación, se le presenta la revisión de la compra seleccionada. ", font=("Arial", 12), bg="light blue")
        descripcion_proceso.pack( fill= 'both', expand=True)
        frameScrollR = Frame(frameI, background="white", height=60)
        frameScrollR.pack(padx=80, pady=10, fill="x")
        frameScrollR.config(highlightthickness=3, highlightbackground="black")
        scroll = Scrollbar(frameScrollR)
        scroll.pack(side="right", fill="y")
        text_widget = Text(frameScrollR, yscrollcommand=scroll.set, width=100, height=20)
        text_widget.pack(side="left", fill="both")
        scroll.config(command=text_widget.yview)

        text_widget.insert(END, "Bodega: \n")
        if control.getCompra().getTienda().getBodega().getProductosEnBodega() == []:
            text_widget.insert(END, "No hay productos en bodega\n")
        else:
            for producto in control.getCompra().getTienda().getBodega().getProductosEnBodega():
                text_widget.insert(END, str(producto) + "\n")
        text_widget.config(state=DISABLED)

        boton = Button(frameI, text="Continuar", font=("Arial", 12), command = ventanaInforme)
        boton.pack(padx=80, pady=10)
    
    def ventanaInforme():
        def capturarOpcion():
            try:
                opcion = field_frame.obtenerValores()["Informes"]
                if not opcion:
                    raise ErrorDatosIncompletos("Por favor seleccione un informe")
            except ErrorDatosIncompletos as e:
                messagebox.showerror("Error", e.message)

    
        field_frame_re.place_forget()
        ventana = Frame(controladora.ventana, width=1000, height=620, bg="light blue")   
        ventana.place(x=0,y=0,relx=0.5, rely=0.5, anchor='center')
        frameI= Frame(ventana, width=1000, height=60, bg="light blue")
        frameI.place(x=95, y=5)
        informes = ["Informe 1", "Informe 2", "Informe 3"]
        sets = []
        for informe in informes:
            sets.append(informe)
        criterios = ["Informes"]
        valores = [sets]
        habilitado = [True]

        ffInforme = FieldFrame2(frameI, None, criterios, "Valor", valores, habilitado, None)
        ffInforme.config(pady=0)
    
    #controladora.ventana.title("Control de calidad")
    ventana = Frame(controladora.ventana, width=1000, height=620, bg="light blue")   
    ventana.place(x=0,y=0,relx=0.5, rely=0.5, anchor='center')
    frameI= Frame(ventana, width=1000, height=100, bg="light blue")
    frameI.place(x=190, y=100)
    proceso = Label(frameI, text="Control de calidad", font=("Arial Bold", 16, "bold"), bg="light blue")
    proceso.pack()
    descripcion_proceso = Label(frameI, text="Permite a los gerentes mantener altos estándares de calidad en todas \n las ubicaciones. Al monitorear y mejorar constantemente la calidad, \nse mejora la satisfacción del cliente, se protege la reputación de la \nfranquicia y se impulsa el éxito general del negocio.\nA continuación se le presentan las opciones a ejecutar del módulo de Control de Calidad. ", font=("Arial", 12), bg="light blue")
    descripcion_proceso.pack( fill= 'both', expand=True)
    compras = ["Realizar revisión","Contactar al proveedor/transportista", "Consultar bodega", "Informe de calidad"]
    sets = []
    for compra in compras:
        sets.append(compra)
    criterios = ["Opciones"]
    valores = [sets]
    habilitado = [True]

    field_frame = FieldFrame(frameI, "Criterio", criterios, "Valor", valores, habilitado, "Seleccionar", administradorVentanas)
    field_frame.config(relief="sunken", bd= 5, highlightbackground="black")
    field_frame.config(relief="sunken", height=20, width=50)
    field_frame.pack(padx=5, pady=30)
    

# ventana Inicio
ventana_inicio = Tk()
ventana_inicio.title("Sistema de Administracion")
ventana_inicio.config(bg="light blue")
ventana_inicio.geometry("1000x620")
controladora = Controladora()

frame_1 = Frame(ventana_inicio, width=470, height=570, relief="sunken", bd=12)
frame_1.place(x=25, y=25)

frame_2 = Frame(ventana_inicio, width=440, height=560, relief="sunken", bd=12)
frame_2.place(x=525, y=25)

# menu
menu = Menu(ventana_inicio)
ventana_inicio.config(menu=menu)

menu_opciones = Menu(menu, tearoff=0)
menu_info = Menu(menu_opciones, tearoff=0)
menu.add_cascade(label="Inicio", menu=menu_opciones)

menu_opciones.add_command(label="Salir del sistema", command=Salir)
menu_opciones.add_cascade(label="Descripcion del sistema", menu=menu_info)
menu_info.add_command(label="Nuestro sistema de gestión de franquicias..", command=infoSistema)

# mensaje de bienvenida
frame_saludo = Frame(ventana_inicio, width=460, height=130, relief="sunken", bd=10)
frame_saludo.place(x=30, y=30)

mensaje_bienvenida = Label(frame_saludo, text="Hola usuario! bienvenido al sistema", font=("Arial", 20))
mensaje_bienvenida.place(x=0, y=30)

# imagen de presentacion
frame_boton = Frame(ventana_inicio, width=460, height=430, relief="sunken", bd=10)
frame_boton.place(x=30, y=160)

img = PhotoImage(file="presentacion0.png")
img_presentacion = Label(frame_boton, image=img)
img_presentacion.place(x=18, y=0)

# mensaje contrasena
mensaje_contrasena = Label(frame_boton, text="ingrese clave:")
mensaje_contrasena.place(x=172, y=285)

# contrasena
entrada = Entry(frame_boton, width=20, bg="white", fg="black", cursor="hand2")
entrada.place(x=172, y=310)

# boton
boton = Button(frame_boton, text="continuar", width=9, height=1, bg="white", fg="black", cursor="hand2")
boton.place(x=200, y=350)

# Descripcoin del integrante del equipo
frame_descripcion = Frame(ventana_inicio, width=430, height=130, relief="sunken", bd=10)
frame_descripcion.place(x=530, y=30)

descripcion_integrante = Label(frame_descripcion,
                               text="Mi nombre es Gabriel Serrano, tengo 22 anos y estudio \nciencias de la computacion, me suelen llamar de muchas\n maneras, la maquina, el maravilloso, semidios,\n boycode y la bestia. Me gusta comer, el futbol y las \nmujeres",
                               font=("Arial", 12))
descripcion_integrante.place(x=0, y=0)

# Imagenes Descripcion
frame_img_descripcion = Frame(ventana_inicio, width=500, height=250, relief="sunken", bd=10)
frame_img_descripcion.place(x=530, y=160)

img1 = PhotoImage(file="gabriel1.png")
img2 = PhotoImage(file="gabriel2.png")
img3 = PhotoImage(file="gabriel3.png")
img4 = PhotoImage(file="gabriel4.png")

img1_descripcion = Label(frame_img_descripcion, image=img1)
img1_descripcion.grid(row=0, column=0, padx=1, pady=1)
img2_descripcion = Label(frame_img_descripcion, image=img2)
img2_descripcion.grid(row=0, column=1, padx=1, pady=1)
img3_descripcion = Label(frame_img_descripcion, image=img3)
img3_descripcion.grid(row=1, column=0, padx=1, pady=1)
img4_descripcion = Label(frame_img_descripcion, image=img4)
img4_descripcion.grid(row=1, column=1, padx=1, pady=1)

# ventana2
ventana_menu = Tk()
ventana_menu.title("Sistema de administracion")
ventana_menu.geometry("1000x620")
ventana_menu.config(bg="light blue")
ventana_menu.withdraw()

# menu

menu_bar = Menu(ventana_menu)
ventana_menu.config(menu=menu_bar)

# menu archivo
menu_archivo = Menu(menu_bar, tearoff=0)

menu_bar.add_cascade(label="Archivo", menu=menu_archivo)
menu_archivo.add_command(label="Aplicacion", command=aplicacion)
menu_archivo.add_command(label="Salir", command=salirMenu)

# menu Procesos
menu_procesos = Menu(menu_bar, tearoff=0)

menu_bar.add_cascade(label="Procesos y Consultas", menu=menu_procesos)
menu_procesos.add_command(label="Gestion de alianzas estrategicas", command=None)
menu_procesos.add_command(label="Modulo de compra", command=moduloCompra)
menu_procesos.add_command(label="Control de calidad")
menu_procesos.add_command(label="Logistica de envios",command=logisticaEnvio)
menu_procesos.add_command(label="Gestion de creditos")

# Menu ayuda
menu_bar.add_command(label="Ayuda", command=ayuda)

# manejo de eventos
img_presentacion.bind("<Enter>", lambda event: cambioImgPresentacion())
descripcion_integrante.bind("<Button-1>", lambda event: cambioDescripcion())
boton.bind("<Button-1>", lambda event: ingresar())

ventana_inicio.mainloop()
