from tkinter import *
import tkinter as tk
from tkinter import messagebox, StringVar
from tkinter import messagebox as MessageBox
from tkinter import ttk
from fieldFrame import *
from fieldFrame2 import *
from Excepciones import *
from Persona import Persona



class Controladora:
    controlDescripcion = 0
    controlPresentacion = 0
    ventana = None




def Salir():
    ventana_inicio.destroy()
    ventana_menu.destroy()


def salirMenu():
    ventana_menu.withdraw()
    ventana_inicio.deiconify()


def ingresar():
    if entrada.get() == "123":
        ventana_menu.deiconify()
        ventana_inicio.withdraw()


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
        file="..\\baseDatos\\img_png\\presentacion" + str(controladora.controlPresentacion) + ".png")
    img_presentacion.config(image=nueva_imagen)
    img_presentacion.image = nueva_imagen


def cambioDescripcion():
    # gabriel
    img_g1 = PhotoImage(file="..\\baseDatos\\img_png\\gabriel1.png")
    img_g2 = PhotoImage(file="..\\baseDatos\\img_png\\gabriel2.png")
    img_g3 = PhotoImage(file="..\\baseDatos\\img_png\\gabriel3.png")
    img_g4 = PhotoImage(file="..\\baseDatos\\img_png\\gabriel4.png")
    # aleja
    img_a1 = PhotoImage(file="..\\baseDatos\\img_png\\aleja1.png")
    img_a2 = PhotoImage(file="..\\baseDatos\\img_png\\aleja2.png")
    img_a3 = PhotoImage(file="..\\baseDatos\\img_png\\aleja3.png")
    img_a4 = PhotoImage(file="..\\baseDatos\\img_png\\aleja4.png")
    # Carmen
    img_c1 = PhotoImage(file="..\\baseDatos\\img_png\\carmen1.png")
    img_c2 = PhotoImage(file="..\\baseDatos\\img_png\\carmen2.png")
    img_c3 = PhotoImage(file="..\\baseDatos\\img_png\\carmen3.png")
    img_c4 = PhotoImage(file="..\\baseDatos\\img_png\\carmen4.png")
    # Juan
    img_j1 = PhotoImage(file="..\\baseDatos\\img_png\\juan1.png")
    img_j2 = PhotoImage(file="..\\baseDatos\\img_png\\juan2.png")
    img_j3 = PhotoImage(file="..\\baseDatos\\img_png\\juan3.png")
    img_j4 = PhotoImage(file="..\\baseDatos\\img_png\\juan4.png")
    # Mauricio
    img_m1 = PhotoImage(file="..\\baseDatos\\img_png\\mauro1.png")
    img_m2 = PhotoImage(file="..\\baseDatos\\img_png\\mauro2.png")
    img_m3 = PhotoImage(file="..\\baseDatos\\img_png\\mauro3.png")
    img_m4 = PhotoImage(file="..\\baseDatos\\img_png\\mauro4.png")

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


def seleccionDeFrames(combo):
    print(combo.get())


def gestionAlianzasEstrategicas():
    # Imagenes ventas

    def vender():
        global frameSubbtones
        global Fproductos
        global confirmar
        try:
            eleccionUsuario = Fsocios.obtenerValores()
            socioSeleccionado = eleccionUsuario['Seleccione un socio']


            Fsocios.pack_forget()
            verHistorial.grid_forget()



            '''
            socioSeleccionado
            socios = [] #Deserializar socios
            for s in socios:
                if(s.getNombre==eleccionUsuario):
                    socioSeleccionado = s

            productosSocio = socioSeleccionado.getProductosContrato()
            transportistas = #Deserializar transportistas

            '''

            productosSocio = ['Producto1', 'Producto2', 'Producto3']
            transportistas = ['T1', 'T2', 'T3']

            '''
            i1 = Inventariar.calcularCamisas(productosSocio)
            i2 = Inventariar.calcularAbrigos(productosSocio)
            i3 = Inventariar.calcularPantalon(productosSocio)

            criterios = ['Camisas[{}]'.formato(i1), 'Pantalones[{}]'.format(i2), 'Abrigos[i3]'.format(i3), 'Seleccione un transportista']
            '''


            criterios = ['Camisas[1]', 'Pantalones[2]', 'Abrigos[3]', 'Seleccione un transportista']

            if(socioSeleccionado == ''):
                raise ErrorDatosIncompletos('Socio seleccionado')






            p1=StringVar()
            p2=StringVar()
            p3=StringVar()

            valores = [Entry(textvariable=p1),Entry(textvariable=p2),Entry(textvariable=p3), transportistas]
            habilitado = []
            for p in productosSocio:
                habilitado.append(False)
            habilitado.append(True)
            Fproductos = FieldFrame(marco_socio, "Productos", criterios, "Precio", valores, habilitado, "Sugerir Oferta", sugerirOferta)
            Fproductos.pack(pady=100)

            confirmar = Button(frameSubbtones, text="Realizar venta",command=lambda: confirmarVenta(1))
            confirmar.grid(row=0, column=0, pady=5)
                #side=BOTTOM, pady=5, anchor="e"

        except ErrorDatosIncompletos:
                MessageBox.showwarning("Error", ErrorDatosIncompletos('Socio seleccionado').mostrarMensaje())



    def sugerirOferta():
        global Fproductos
        global oferta
        global porcentaje
        global ofertaDos


        '''
        ofertas [][] = Tienda.sugerirOferta(socioSeleccionado)
        oferta1 = oferta [0]
        oferta2 = oferta[1]

        i1 = Inventariar.calcularCamisas(oferta1)
        i2 = Inventariar.calcularAbrigos(oferta1)
        i3 = Inventariar.calcularPantalon(oferta1)

        i4 = Inventariar.calcularCamisas(oferta2)
        i5 = Inventariar.calcularAbrigos(oferta2)
        i6 = Inventariar.calcularPantalon(oferta2)

        criteriosO1 = ['Camisas[{}]'.formato(i1), 'Abrigos[{}]'.format(i2), 'Pantalones[i3]'.format(i3)]
        criteriosO2 = ['Camisas[{}]'.formato(i3), 'Abrigos[{}]'.format(i4), 'Pantalones[i3]'.format(i6)]
        '''


        Fproductos.pack_forget()
        verHistorial.grid_forget()
        confirmar.grid_forget()



        criterios0 = ['Descuento por camisa', 'Descuento por abrigo', 'Descuento por pantalón']
        habilitado = [True, True, True]
        porcentaje = FieldFrame(marco_socio, "Ingrese el porcentaje que desea descontar\n(numeros entre 0.0 y 50.0)", criterios0, "Valor", [0.0, 0.0, 0.0], habilitado, "Calcular", calcular)


        porcentaje.pack(side='top', expand=True, fill='both')

        oferta1 = ['producto1', 'producto2', 'producto3']

        criterios = ['Camisa', 'Pantalón', 'Abrigo']
        habilitado = [True, True, True]

        #"Criterio", criterios, "Valor"

        oferta = FieldFrame(marco_socio, 'Producto', criterios, 'Precio\npor unidad', [0,1,2], habilitado,"Realizar venta",lambda: confirmarVenta(1))
        oferta.pack(side='left', expand=True, fill='both')


        #Para la segunda oferta
        oferta2 = ['producto1', 'producto2', 'producto3']
        ofertaDos = FieldFrame(marco_socio, 'Producto', criterios, "Precio\npor unidad", [1,2,3], habilitado,"Cancelar oferta", cancelar)
        ofertaDos.pack(side='right', expand=True, fill='both')

        #side=BOTTOM, pady=5, anchor="w"





    def confirmarVenta(num):

        '''
        siOferta = ''
        venta = transportistaSeleccionado.entregaEspecial(productosVenta)

        if (venta == null):

            MessageBox.showwarning("Error", ErrorProductosInsuficientes().mostrarMensaje())

        elif venta.getProductosOferta != null:
            siOferta = ' y los productos ofertados '

        MessageBox.showinfo("Confirmacion de venta", 'El socio ha confirmado la compra de los productos del contrato' + siOferta)

        marco_socio.pack_forget()

        '''


        MessageBox.showinfo("Confirmacion de venta", 'El socio ha confirmado la compra de los productos del contrato')
        marco_socio.pack_forget()

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
        sets=['i1', 'i2', 'i3']
        valores = [sets]

        field_frame_2 = FieldFrame2(marcoFuncionalidad, "Criterio", criterios, "Valor", valores, habilitado,'')
        field_frame_2.pack()
        field_frame_2.agregarTexto("INSERTE INFORME")



    def calcular():
        pass



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






    marcoFuncionalidad = Frame(ventana_menu, height=2000, width=1500, bg="gray", pady=10, padx=50, relief="sunken")
    marcoFuncionalidad.place(relx=0.5, rely=0.5, anchor='center')

    cabecera = Entry(marcoFuncionalidad,background="gray", font=('Helvetica', 11, 'bold'), width=50)
    cabecera.insert(0, 'Gestion Alianzas Estrategicas')
    cabecera.configure( justify='center', state='disabled')
    #cabecera.insert(0, "Default Text")
    cabecera.pack( pady=10)


    marco_socio = Frame(marcoFuncionalidad, width=1000, height=500, relief="sunken", bd=10)
    marco_socio.pack(fill='both', expand=True)
    socios = ['Exito', 'Falabella', 'Primark']
    criterios = ["Seleccione un socio"]
    valores = [socios]
    habilitado = [True]

    Fsocios = FieldFrame(marco_socio, "Criterio", criterios, "Valor", valores, habilitado, "Vender", vender)
    Fsocios.pack(pady=110)

    frameSubbtones=Frame(marcoFuncionalidad,bg='gray')
    frameSubbtones.pack()

    verHistorial = Button(frameSubbtones, text="Ver historial de ventas", command=historialVentas)
    verHistorial.grid( row=0, column=0, pady=11)
    #side='bottom', pady=10















def moduloCompra():
    # Nombre funcionalidad
    frame_1 = Frame(ventana_menu, width=400, height=50, relief="sunken", bd=5)
    frame_1.place(x=380, y=20)
    mensaje_compra = Label(frame_1, text="Modulo de Compra", font=("arial", 20))
    mensaje_compra.pack()

    # descripcion de funcionalidad
    frame_2 = Frame(ventana_menu, width=600, height=150, relief="sunken", bd=5)
    frame_2.place(x=200, y=80)
    mensaje_descripcion = Label(frame_2,
                                text="Selecione el proceso que desea realizar, luego introduzca la informacion solicitada en los campos correspondientes")
    mensaje_descripcion.pack()

    # acciones
    frame_3 = Frame(ventana_menu, width=600, height=400, relief="sunken", bd=5)
    frame_3.place(x=200, y=120)

    valor_defecto = StringVar(value="Selecione...")

    combo = ttk.Combobox(frame_3, values=["Consultar bodegas", "Realizar compra", "Historial de compras"],
                         textvariable=valor_defecto)
    combo.pack()
    combo.bind("<<ComboboxSelected>>", lambda event: seleccionDeFrames(combo))

    print(1)

def logisticaEnvio():
    def ConsultaPedido():
        empezar.forget()
        titulo.config(text="Realizar Pedido")
        descripcion.config(text="Para realizar el pedido, es necesario que escojas un SET y una CANTIDAD.Los sets son combinaciones de prendas que se pueden pedir en combo.La cantidad establecerá cuántos combos son necesarios.")

        global marco_pedido1
        marco_pedido1= Frame(ventanaEnvio, width=800, height=300,relief="solid",bd=5)
        marco_pedido1.pack()
        sets= ["ONLY", "TU", "COMPLETO"]
        cantidades=[1,2,3,4,5]
        nombres = ["SET","CANTIDAD"]
        valores = [sets,cantidades]
        habilitado = [True,True]
        global Fset
        Fset = FieldFrame(marco_pedido1, "Criterios",nombres, "Pedido", valores, habilitado,"Realizar Pedido",realizarPedido)
        Fset.pack(side="bottom")
        global label
        label=Label(ventanaEnvio, text="Los sets disponibles son:\n- ONLY: compuesto por una camisa.\n- TU: compuesto por una camisa y un pantalón.\n- COMPLETO: compuesto por una camisa, un abrigo y un pantalón", font=("Verdana", 13, "bold"),relief="flat",bg="white" ,wraplength=450)
        label.pack(pady=7,padx=10)



    def realizarPedido():

        pedido = Fset.obtenerValores()
        if any(not valor for valor in pedido.values()):
            messagebox.showwarning("Error", ErrorDatosIncompletos('SET', 'CANTIDAD').mostrarMensaje())
            raise ErrorDatosIncompletos('SET', 'CANTIDAD')

        marco_pedido1.forget()
        label.forget()
        titulo.config(text="Estado del pedido")
        descripcion.config(text="Hay tres posibles casos para el pedido: el ideal es que tu pedido se haya completado exitosamente, lo que significa que todos los productos necesarios fueron extraídos de las tiendas. También puede ocurrir que no se haya podido completar todo el pedido, pero sí algunos productos. Por último, existe la opción de que no se haya podido completar ninguno de los productos.")

        pedidoSet=str(pedido["SET"])
        cantidad=int(pedido["CANTIDAD"])
        set=""
        for item in SETS:
            if item.name == pedidoSet:
                set= item
        global tiendas
        tiendas=Deserializador("tiendas").getObjeto()
        # Primera interacción
        global productos
        productos = Bodega.realizar_pedido(tiendas, set, cantidad)
        global marco_pedido2
        marco_pedido2 = Frame(ventanaEnvio, width=800, height=300,relief="ridge",bd=5)
        resumenpedido=Label(marco_pedido2,text=Bodega.get_resumen_pedido(), wraplength=200)
        if Bodega.get_resumen_pedido()!="El pedido se ha completado exitosamente":
            descripcion.config(text="Su pedido no se pudo completar todos los productos necesarios sin embargo puede continuar el proceso de envio con los productos disponibles o realizar los procesos de compra y control de calidad para tener disponibilidad de productos")
            if Bodega.get_resumen_pedido().split()[15]=="0":
                descripcion.config(text="No es posible continuar con el proceso de envio si no hay productos disponibles, es necesario que ejecute los procesos de compra y control de calidad para abastecer las tiendas y tener disponibilidad")
                messagebox.showwarning("Error", ErrorPedido().mostrarMensaje())
                raise ErrorDatosIncompletos()
        resumenpedido.pack()
        marco_pedido2.pack()
        continuar=Button(marco_pedido2,text="Continuar",command=consultaColeccion)
        continuar.pack()



    def consultaColeccion():
        marco_pedido2.forget()
        titulo.config(text="Coleccion")
        descripcion.config(text="La colección que escojas determinará el proceso de intervención de los productos seleccionados. Esto incluye el color, corte y estampado, además del precio final que tendrán los productos intervenidos.")
        global marco_coleccion1
        marco_coleccion1= Frame(ventanaEnvio, width=800, height=300,relief="solid",bd=5)
        marco_coleccion1.pack()
        colecciones= ["NATURALEZA", "GEOMETRIA", "INDUSTRIAL"]
        criterios = ["COLECCION"]
        valores = [colecciones]
        habilitado = [True]
        global Fcoleccion
        Fcoleccion = FieldFrame(marco_coleccion1, "Colecciones", criterios, "Coleccion", valores, habilitado,"Intervenir productos",intervenir)
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
        colec=Fcoleccion.obtenerValores()
        if any(not valor for valor in colec.values()):
            messagebox.showwarning("Error", ErrorDatosIncompletos('COLECCION').mostrarMensaje())
            raise ErrorDatosIncompletos('COLECCION')
        marco_coleccion1.forget()
        descripcion.config(text="El proceso de intervención se ha realizado exitosamente. A continuación, se le presentará la paleta de la colección escogida. Haga clic en cualquiera de los colores para ver las características de los productos intervenidos."
                           )
        colecSet=str(colec["COLECCION"])
        coleccion=""
        Colecciones=Intervenido.Colecciones
        for item in Colecciones:
            if item.name == colecSet:
                coleccion= item
        titulo.config(text=coleccion.name)
        # Segunda interacción
        global intervenidos
        intervenidos = Intervenido.intervenir(productos, coleccion)
        colores=Intervenido.colores(coleccion)
        global marco_coleccion2
        marco_coleccion2 = Frame(ventanaEnvio, width=800, height=300,relief="sunken",bd=10)
        resumenpedido=Label(marco_coleccion2,text="Paleta de colores\n", wraplength=200)
        resumenpedido.pack()
        paleta=Frame(marco_coleccion2,width=300, height=200)
        for i, color in enumerate(colores):
            boton = Button(paleta, bg=color, width=10, height=2,command=caracteristicas)
            boton.grid(row=i // 3, column=i % 3, padx=10, pady=10)
        paleta.pack()
        marco_coleccion2.pack()
        continuar=Button(marco_coleccion2,text="Continuar",command=buscarCliente)
        continuar.pack()



    def buscarCliente():
        marco_coleccion2.forget()
        titulo.config(text="Datos de envio")
        descripcion.config(text="Para poder procesar el envío, es fundamental que especifique el destinatario al que se dirigirá. Por favor, elija uno de los clientes registrados en nuestra base de datos para completar esta información."
                           )
        global marco_envio1
        marco_envio1= Frame(ventanaEnvio, width=800, height=300,relief="solid",bd=5)
        marco_envio1.pack()
        global clientes
        clientes=Deserializador("clientes").getObjeto()
        criterios = ["Usuario"]
        valores = [clientes]
        habilitado = [True]
        global Fclientes
        Fclientes = FieldFrame(marco_envio1, "Criterio", criterios, "Clientes", valores, habilitado,"Buscar cliente",consultaEnvio)
        Fclientes.pack(side="left")


    def consultaEnvio():
        dicC=Fclientes.obtenerValores()
        if any(not valor for valor in dicC.values()):
            messagebox.showwarning("Error", ErrorDatosIncompletos('USUARIO').mostrarMensaje())
            raise ErrorDatosIncompletos('USUARIO')
        usuario = dicC["Usuario"]
        global cliente
        for c in clientes:
            if usuario==c.__str__():
                cliente=c
        string="Los datos actuales del usuario escogido indican una calificacion de "+str(cliente.get_calificacion())+" puntos,la ciudad registrada es "+cliente.getCiudad().name+". Por favor confirme o cambie el destino del envio.Además seleccione el tipo de envio."
        marco_envio1.forget()
        titulo.config(text=usuario)
        descripcion.config(text=string)
        global marco_envio2
        marco_envio2= Frame(ventanaEnvio, width=800, height=300,relief="solid",bd=5)
        marco_envio2.pack()
        ciudades=[ciudad.name for ciudad in Cliente.Ciudades]

        tipos=["NORMAL","PRIORITARIO","LIBRE"]
        criterios = ["Ciudades disponibles","Tipo de Envio"]
        valores =[ciudades,tipos]
        habilitado = [True,True]
        global Fenvio
        Fenvio = FieldFrame(marco_envio2, "Criterio", criterios, "Valor", valores, habilitado,"Realizar Envio",enviar)
        Fenvio.pack()
        global label
        label=Label(ventanaEnvio, text="Los envios prioritarios tienen un sobrecargo de 15.000 y demoran 5 dias habiles Los envios libres tienen un descuento de 15.000 y demoran 30 dias habiles. Los envios normales variaran su costo y dias habiles dependiendo de la ciudad.", font=("Verdana", 13, "bold"),relief="flat",bg="white" ,wraplength=450)
        label.pack(pady=7,padx=10)

    def enviar():
        Senvio=Fenvio.obtenerValores()
        if any(not valor for valor in Senvio.values()):
            messagebox.showwarning("Error", ErrorDatosIncompletos('Ciudades disponibles','Tipo Envio').mostrarMensaje())
            raise ErrorDatosIncompletos('Ciudades disponibles','Tipo Envio')

        marco_envio2.forget()
        label.forget()
        Sciudad=Senvio["Ciudades disponibles"]
        Stipo=Senvio["Tipo de Envio"]
        ciudad=""
        tipo=""
        for item in Cliente.Ciudades:
            if item.name == Sciudad:
                ciudad=item
                break
        for item in Cliente.Tipo:
            if item.name == Stipo:
                item=item
                break

        titulo.config(text="Proceso de Logistica de envio finalizado")
        descripcion.config(text="El proceso de envío ha sido finalizado. A continuación, se mostrará en pantalla la factura del pedido y la confirmación del envío una vez se hayan procesado todas las transferencias necesarias."
                           )
        # Tercera interacción
        cliente.setCiudad(ciudad)
        cliente.enviar(intervenidos, tipo)
        pck=Serializador(clientes,"clientes")
        pck=Serializador(tiendas,"tiendas")

        global marco_envio3
        marco_envio3= Frame(ventanaEnvio, width=800, height=300,relief="sunken",bd=10)
        resumenPago=Label(marco_envio3, text=cliente.getResumenDePago(), font=("Arial", 10),wraplength=800)
        resumenPago.pack(pady=10)
        confirmacion=Label(marco_envio3,text=cliente.getConfirmacion(),font=("Arial", 10),wraplength=200)
        confirmacion.pack(pady=10)
        marco_envio3.pack()


    for widget in ventana_menu.winfo_children():
        widget.destroy()
    menu_bar = Menu(ventana_menu)
    ventana_menu.config(menu=menu_bar)

    #menu archivo
    menu_archivo = Menu(menu_bar, tearoff=0)

    menu_bar.add_cascade(label="Archivo", menu=menu_archivo)
    menu_archivo.add_command(label="Aplicacion",command=aplicacion)
    menu_archivo.add_command(label="Salir", command= salirMenu)

    #menu Procesos
    menu_procesos = Menu(menu_bar,tearoff=0)
    menu_bar.add_cascade(label="Procesos y Consultas",menu=menu_procesos)
    menu_procesos.add_command(label="Gestion de alianzas estrategicas")
    menu_procesos.add_command(label="Modulo de compra")
    menu_procesos.add_command(label="Control de calidad")
    menu_procesos.add_command(label="Logistica de envios",command=logisticaEnvio)
    menu_procesos.add_command(label="Gestion de creditos")

    #Menu ayuda
    menu_bar.add_command(label="Ayuda",command=ayuda)

    ventanaEnvio=Frame(ventana_menu,bg="#AB91C1",width=600,height=500,relief="ridge",bd=10)
    ventanaEnvio.place(relx=0.5, rely=0.5, anchor="center")
    ventanaEnvio.pack_propagate(False)


    titulo=Label(ventanaEnvio, text="Logistica de Envio ", font=("Arial", 16, "bold"),relief="raised",bg="#FFA532" )

    titulo.pack(side="top",pady=10)

    frame_descripcion=Frame(ventanaEnvio,bg="white",relief="groove",bd=5,width=500,height=105)

    descripcion=Label(frame_descripcion, text= "Bienvenido a la funcionalidad Logística de Envío. En esta sección, podrás realizar un pedido de los productos que necesitas, gestionar su intervención según la colección a la que pertenezcan y, finalmente, llevar a cabo las transferencias correspondientes para confirmar el envío de los productos."
                      , font=("Verdana", 10),wraplength=450,bg="white")
    descripcion.pack()
    frame_descripcion.pack(pady=20)
    frame_descripcion.pack_propagate(False)

    empezar=Button(ventanaEnvio,text="Empezar proceso",command=ConsultaPedido)
    empezar.pack(pady=50)

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

img = PhotoImage(file="..\\baseDatos\\img_png\\presentacion0.png")
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

img1 = PhotoImage(file="..\\baseDatos\\img_png\\gabriel1.png")
img2 = PhotoImage(file="..\\baseDatos\\img_png\\gabriel2.png")
img3 = PhotoImage(file="..\\baseDatos\\img_png\\gabriel3.png")
img4 = PhotoImage(file="..\\baseDatos\\img_png\\gabriel4.png")

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
menu_procesos.add_command(label="Gestion de alianzas estrategicas", command=gestionAlianzasEstrategicas)
menu_procesos.add_command(label="Modulo de compra", command=moduloCompra)
menu_procesos.add_command(label="Control de calidad")
menu_procesos.add_command(label="Logistica de envios")
menu_procesos.add_command(label="Gestion de creditos")

# Menu ayuda
menu_bar.add_command(label="Ayuda", command=ayuda)

# manejo de eventos
img_presentacion.bind("<Enter>", lambda event: cambioImgPresentacion())
descripcion_integrante.bind("<Button-1>", lambda event: cambioDescripcion())
boton.bind("<Button-1>", lambda event: ingresar())

ventana_inicio.mainloop()
