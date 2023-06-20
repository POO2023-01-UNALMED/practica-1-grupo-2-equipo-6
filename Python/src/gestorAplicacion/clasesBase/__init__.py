from Producto import *
from Banco import *
from Tienda import *
from Proveedor import *
from Bodega import *
from Transportista import *
from Persona import *
from Compra import Compra
from Transferencia import *


from Empleado import *
from CuentaBancaria import *
Pais=CuentaBancaria.Pais
from Cliente import *

from ControlCalidad import *

from Socio import *
from Serializador import *
from Deserializador import *

def tiendas():

    global camisa
    global pantalon
    global abrigo

    bodegaTienda1 = Bodega([],50)
    bodegaTienda2 = Bodega([],70)
    bodegaTienda3 = Bodega([],90)

    camisa = Producto(Producto.Tipo.CAMISA, 40000)
    pantalon = Producto(Producto.Tipo.PANTALON, 50000)
    abrigo = Producto(Producto.Tipo.ABRIGO, 60000)


    productos2=[]
    productos1=[]

# Tienda1
    for i in range(10):
        bodegaTienda1.agregarProductos(camisa)
        productos2.append(camisa)

    for i in range(5):
        bodegaTienda1.agregarProductos(pantalon)

    for i in range(10):
        bodegaTienda1.agregarProductos(abrigo)

# Tienda2
    for i in range(9):
        bodegaTienda2.agregarProductos(camisa)

    for i in range(8):
        bodegaTienda2.agregarProductos(pantalon)
        productos1.append(camisa)

    for i in range(17):
        bodegaTienda2.agregarProductos(abrigo)
        productos1.append(abrigo)

# Tienda3
    for i in range(10):
        bodegaTienda3.agregarProductos(camisa)

    for i in range(6):
        bodegaTienda3.agregarProductos(pantalon)
        productos1.append(pantalon)

    for i in range(10):
        bodegaTienda3.agregarProductos(abrigo)
        productos2.append(pantalon)

    tiendaLaureles = Tienda(1000000, bodegaTienda1, 100)
    tiendaPoblado = Tienda(1200000, bodegaTienda2, 90)
    tiendaEnvigado = Tienda(1100000, bodegaTienda3, 80)

    tiendas=[tiendaEnvigado,tiendaPoblado,tiendaLaureles]

    Serializador(tiendas,"tiendas")


def serializarEnvio():
    BancoElectronico = Banco("BancoElectronico")
    Pais = CuentaBancaria.Pais
    CARGOS=Empleado.CARGOS
    cuenta12 = CuentaBancaria(1000000, Pais.COLOMBIA, BancoElectronico)
    cuenta22 = CuentaBancaria(2000000, Pais.BRASIL, BancoElectronico)
    cuenta32 = CuentaBancaria(3000000, Pais.DINAMARCA, BancoElectronico)
    o1 = Empleado("Andrea Munera", 1,cuenta12, 234, 0, CARGOS.OPERARIO, 1200000)
    o2 = Empleado("Sol Perez", 3,cuenta22, 189, 0, CARGOS.OPERARIO, 1200000)
    o3 = Empleado("Ana Juarez", 5,cuenta32, 276, 0, CARGOS.OPERARIO, 1200000)

    listaEmpleados = [o1,o2,o3]
    Serializador(listaEmpleados,"operarios")



    cuenta1 = CuentaBancaria(1000000, Pais.COLOMBIA, BancoElectronico)
    cuenta2 = CuentaBancaria(2000000, Pais.BRASIL, BancoElectronico)
    cuenta3 = CuentaBancaria(3000000, Pais.DINAMARCA, BancoElectronico)
    cuenta4 = CuentaBancaria(4000000, Pais.ALEMANIA, BancoElectronico)
    cuenta5 = CuentaBancaria(5000000, Pais.MONACO, BancoElectronico)
    cuenta6 = CuentaBancaria(6000000, Pais.CHIPRE, BancoElectronico)
    cuenta7 = CuentaBancaria(7000000, Pais.VENEZUELA, BancoElectronico)
    cuenta8 = CuentaBancaria(8000000, Pais.COLOMBIA, BancoElectronico)
    cuenta9 = CuentaBancaria(9000000, Pais.BRASIL, BancoElectronico)
    cuenta10 = CuentaBancaria(10000000, Pais.DINAMARCA, BancoElectronico)
    cuenta11 = CuentaBancaria(10000000, Pais.DINAMARCA, BancoElectronico)
    cuenta12 = CuentaBancaria(10000000, Pais.DINAMARCA, BancoElectronico)

    Ciudades=Cliente.Ciudades
    c1 = Cliente("Juan Perez", 2, Ciudades.BOGOTA, cuenta1)
    c2 = Cliente("Maria Rodriguez", 4, Ciudades.MEDELLIN, cuenta2)
    c3 = Cliente("Carlos Gomez", 1, Ciudades.CALI, cuenta3)
    c4 = Cliente("Ana Lopez", 3, Ciudades.BARRANQUILLA, cuenta4)
    c5 = Cliente("Pedro Garcia", 5, Ciudades.CARTAGENA, cuenta5)
    c6 = Cliente("Laura Martinez", 2, Ciudades.BUCARAMANGA, cuenta6)
    c7 = Cliente("Andres Herrera", 3, Ciudades.PEREIRA, cuenta7)
    c8 = Cliente("Carolina Vargas", 1, Ciudades.CUCUTA, cuenta8)
    c9 = Cliente("Luisa Fernandez", 4, Ciudades.BOGOTA, cuenta9)
    c10 = Cliente("Mario Ramirez", 2, Ciudades.MEDELLIN, cuenta10)

    contador = Empleado("Margarita Sanchez Gutierrez", 5, cuenta11, 8 ,8, 8000, CARGOS.CONTADOR)
    archivista = Empleado("Jose Antonio Rodriguez Vega", 4, cuenta12,8, 8, 45621, CARGOS.ARCHIVISTA)
    Serializador(contador, 'contador0')
    Serializador(archivista, 'archivista0')
    clientes=[c1,c2,c3,c4,c5,c6,c7,c8,c9,c10]
    Serializador(clientes,"clientes")


    cuenta = CuentaBancaria(1044450000, Pais.COLOMBIA, BancoElectronico)
    transportista = Transportista("Jimena Salgado", 3,cuenta,None,None,None)
    Serializador(transportista,"TransportistaNacional")

def Financiero():
    global transportista1
    global transportista2
    global transportista3

    credito = Tienda.getCuentaTienda().getEntidad().generarCredito(Credito(Tienda.getCuentaTienda(), 70, Cuota.DOCE))
    credito1 = Tienda.getCuentaTienda().getEntidad().generarCredito(Credito(Tienda.getCuentaTienda(), 100, Cuota.CINCO))
    #Tienda.pagarCuotaMensual(Banco.PuntajeCredito.MEDIO, credito1)
    #Tienda.pagarCuotaMensual(Banco.PuntajeCredito.BAJO, credito1)
    #Tienda.pagarCuotaMensual(Banco.PuntajeCredito.ALTO, credito1)
    #Tienda.pagarCuotaMensual(Banco.PuntajeCredito.MEDIO, credito1)
    #Tienda.pagarCuotaMensual(Banco.PuntajeCredito.BAJO, credito1)
    #Tienda.pagarCuotaMensual(Banco.PuntajeCredito.BAJO, credito1)
    #Tienda.pagarTodo(Banco.PuntajeCredito.MEDIO, credito1)
    #Tienda.pagarTodo(Banco.PuntajeCredito.ALTO, credito1)
    bbbank = Banco("BBBank")
    cu = CuentaBancaria(25000, CuentaBancaria.Pais.COLOMBIA, bbbank)

    transportista1 = Transportista("Maria", 1000, 500, 16, 2, CuentaBancaria(25000, Pais.COLOMBIA, bbbank))
    transportista2 = Transportista("Carlos", 2000, 400, 15, 5, CuentaBancaria(25000, Pais.COLOMBIA, bbbank))
    transportista3 = Transportista("Rosa", 3000, 300, 10, 3,CuentaBancaria(25000, Pais.COLOMBIA, bbbank))





    bodegap1 = Bodega([], 100)
    bodegap2 = Bodega([], 100)
    bodegap3 = Bodega([], 100)

    proveedor1 = Proveedor("Maria", 0, cu, bodegap1, 20000, 25000, 30000, 100)
    proveedor2 = Proveedor("Carlo", 0, cu, bodegap2, 20000, 25000, 30000,123)
    proveedor3 = Proveedor("Julio", 0, cu, bodegap3, 20000, 25000, 30000,125)

    proveedores = [proveedor2, proveedor1, proveedor3]

    transportistas = [transportista1, transportista2, transportista3]
    Serializador(transportistas, "transportistas")
    Serializador(proveedores, 'proveedores')


def Socios():
    global transportista1
    global transportista2
    global transportista3

    banco1 = Banco('Bancolombia')
    socio1 = Socio('Exito', [camisa, pantalon, abrigo], [], CuentaBancaria(123123, CuentaBancaria.Pais.COLOMBIA, banco1))
    socio2 = Socio('Falabella', [camisa, pantalon, abrigo, camisa], [], CuentaBancaria(123123, CuentaBancaria.Pais.VENEZUELA, banco1))
    socio3 = Socio('Primark', [camisa, camisa, pantalon, abrigo], [], CuentaBancaria(12312,CuentaBancaria.Pais.COLOMBIA,banco1))
    socios = [socio1, socio2, socio3]

    v1 = Venta(socio1, [camisa, camisa, camisa,  abrigo], transportista1)
    v2 = Venta(socio2, [camisa, camisa, camisa, camisa, camisa, pantalon], transportista2)
    v3 = Venta(socio3, [abrigo, abrigo, abrigo, camisa, pantalon, pantalon, pantalon, pantalon, pantalon, pantalon], transportista3)
    ventasPorDefecto = [v1, v2, v3]
    Serializador(socios, 'Socios')
    Serializador(ventasPorDefecto, 'ventasPorDefecto')

def Compras():
    tiendasDeserializadas = Deserializador("tiendas").getObjeto()
    tienda1 = tiendasDeserializadas[0]
    tienda2 = tiendasDeserializadas[1]
    tienda3 = tiendasDeserializadas[2]
    proveedores = Deserializador("proveedores").getObjeto()
    pro1 = proveedores[0]
    pro2 = proveedores[1]
    pro3 = proveedores[2]
    transportistas = Deserializador("transportistas").getObjeto()
    tra1 = transportistas[0]
    tra2 = transportistas[1]
    tra3 = transportistas[2]

    compra1 = Compra(tienda1,pro1,tra1)
    compra2 = Compra(tienda2,pro2,tra2)
    compra3 = Compra(tienda3,pro3,tra3)
    compra4 = Compra(tienda1,pro3,tra2)
    compras =[compra1,compra2,compra3,compra4]
    Serializador(compras,"compras")

def Informes():
    tiendasDeserializadas = Deserializador("tiendas").getObjeto()
    tienda1 = tiendasDeserializadas[0]
    tienda2 = tiendasDeserializadas[1]
    tienda3 = tiendasDeserializadas[2]
    proveedores = Deserializador("proveedores").getObjeto()
    pro1 = proveedores[0]
    pro2 = proveedores[1]
    pro3 = proveedores[2]
    transportistas = Deserializador("transportistas").getObjeto()
    tra1 = transportistas[0]
    tra2 = transportistas[1]
    tra3 = transportistas[2]

    compra1 = Compra(tienda1,pro1,tra1)
    compra2 = Compra(tienda2,pro2,tra2)
    compra3 = Compra(tienda3,pro3,tra3)
    compra4 = Compra(tienda1,pro3,tra2)
    compras =[compra1,compra2,compra3,compra4]
    control1 = ControlCalidad(compra1)
    control2 = ControlCalidad(compra2)
    control3 = ControlCalidad(compra3)
    control4 = ControlCalidad(compra4)
    empleado1 = Empleado("Daniel",5, None, None,None, Empleado.CARGOS.ARCHIVISTA, 200000)
    control1.revisar(compra1)
    control2.revisar(compra2)
    control3.revisar(compra3)
    control4.revisar(compra4)
    control1.contactar(control1.getProveedor())
    control2.contactar(control2.getProveedor())
    control3.contactar(control3.getProveedor())
    control4.contactar(control4.getProveedor())
    control1.contactar(control1.getTransportista())
    control2.contactar(control2.getTransportista())
    control3.contactar(control3.getTransportista())
    control4.contactar(control4.getTransportista())
    informe1 = empleado1.generarInformeControlCalidad(control1, control1.getProveedor(), control1.getTransportista())
    informe2 = empleado1.generarInformeControlCalidad(control2, control2.getProveedor(), control2.getTransportista())
    informe3 = empleado1.generarInformeControlCalidad(control3, control3.getProveedor(), control3.getTransportista())
    informe4 = empleado1.generarInformeControlCalidad(control4, control4.getProveedor(), control4.getTransportista())
    informes = [informe1, informe2, informe3, informe4]
    infor = Serializador(informes, "informes")
    pck = Serializador(empleado1, "archivista")










tiendas()
serializarEnvio()
Financiero()
Socios()
Compras()
Informes()





