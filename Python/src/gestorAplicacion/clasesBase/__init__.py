from Producto import *
from Banco import *
from Tienda import *
from Proveedor import *
from Bodega import *
from Transportista import *
from Persona import *
from Compra import *
from Transferencia import *


from Empleado import *
from CuentaBancaria import *
Pais=CuentaBancaria.Pais
from Cliente import *


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
def Socios():
    banco1 = Banco('Bancolombia')
    socio1 = Socio('Exito', [camisa, pantalon, abrigo], [], CuentaBancaria(123123, CuentaBancaria.Pais.COLOMBIA, banco1))
    socio2 = Socio('Falabella', [camisa, pantalon, abrigo, camisa], [], CuentaBancaria(123123, CuentaBancaria.Pais.VENEZUELA, banco1))
    socio3 = Socio('Primark', [camisa, camisa, pantalon, abrigo], [], CuentaBancaria(12312,CuentaBancaria.Pais.COLOMBIA, banco1))
    socios = [socio1, socio2, socio3]
    Serializador(socios, 'Socios')

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

    clientes=[c1,c2,c3,c4,c5,c6,c7,c8,c9,c10]
    Serializador(clientes,"clientes")


    cuenta = CuentaBancaria(1044450000, Pais.COLOMBIA, BancoElectronico)
    transportista = Transportista("Jimena Salgado", 3,cuenta,None,None,None)
    Serializador(transportista,"TransportistaNacional")

def Financiero():
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







serializarEnvio()
tiendas()
Socios()
Financiero()

tiendasd = Deserializador('tiendas').getObjeto()
