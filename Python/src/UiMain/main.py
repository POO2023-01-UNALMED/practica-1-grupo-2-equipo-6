from tkinter import *
from tkinter import messagebox, StringVar
from tkinter import ttk


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
                          "y escuchar música. Subcampeon nacional en taekwondo dos\n" +
                          "años seguidos y amante de los gatos"]
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
    img5_descripcion.config(image=listaImagenes[controladora.controlDescripcion][4])
    img5_descripcion.image = listaImagenes[controladora.controlDescripcion][4]


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

    imgv1 = PhotoImage(file="../baseDatos/img_png/f.png")
    imgv2 = PhotoImage(file="../baseDatos/img_png/Logo_Exito_colombia.png")
    imgv3 = PhotoImage(file="../baseDatos/img_png/p.png")

    frame1 =  Frame(ventana_menu, height=60, bg="light blue")
    frame1.pack()

    top = Label(ventana_menu, text="Gestion Alianzas Estratégicas", font=('Helvetica', 15, 'bold'), bg="light blue")
    top.pack(side="top", fill="x", pady=9)

    frame2 = Frame(ventana_menu, height=500)
    frame2.pack()

    etiqueta0 = Label(frame2, text="Para efectuar una venta, seleccione un socio: ", height=3, width=50,
                      anchor="center", bg="light blue")

    etiqueta0.grid(row=0, column=0, columnspan=2)

    etiqueta = Label(frame2,
                     text="\nGrupo Éxito es una empresa\nconglomerado multinacional\ncolombiana. Nació en 1905\ncon la marca Carulla y desde\n1999 forma parte de nuestras\nempresas asociadas.",
                     compound="top", image=imgv1, height=300, width=200)
    etiqueta.grid(row=1, column=0, rowspan=10)

    etiqueta2 = Label(frame2,
                      text="\nFalabella es una de las\ncompañías más grandes\ny consolidadas de Amé-\nrica Latina. Desarrolla su\nactividad comercial a tra-\nvés de diferentes áreas de\nnegocio. En el año 1995\nse estableció el contrato\nde sociedad.",
                      compound="top", image=imgv2, height=300, width=200)
    etiqueta2.grid(row=1, column=1, rowspan=10)

    etiqueta3 = Label(frame2,
                      text="\nAsociada desde 1997, Pri-\nmark (Penneys en Irlanda)\n es una cadena irlandesa\nde ropa y complementos\nque pertenece al grupo\nAssociated British Foods.",
                      compound="top", image=imgv3, height=300, width=200)
    etiqueta3.grid(row=1, column=2)

    showInfo = Button(frame2, text="Historial de ventas")
    vender = Button(frame2, text="Vender")

    showInfo2 = Button(frame2, text="Historial de ventas")
    vender2 = Button(frame2, text="Vender")

    showInfo3 = Button(frame2, text="Historial de ventas")
    vender3 = Button(frame2, text="Vender")

    showInfo.grid(row=12, column=0, padx=10, pady=15)
    vender.grid(row=11, column=0, padx=10, pady=15)

    showInfo2.grid(row=12, column=1, padx=10, pady=15)
    vender2.grid(row=11, column=1, padx=10, pady=15)

    showInfo3.grid(row=12, column=2, padx=10, pady=15)
    vender3.grid(row=11, column=2, padx=10, pady=15)


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
