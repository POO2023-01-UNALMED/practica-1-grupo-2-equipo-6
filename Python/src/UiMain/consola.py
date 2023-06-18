from Python.src.gestorAplicacion.clasesBase.Banco import Banco
from Python.src.gestorAplicacion.clasesBase.Bodega import Bodega
from Python.src.gestorAplicacion.clasesBase.CuentaBancaria import CuentaBancaria
from Python.src.gestorAplicacion.clasesBase.Producto import SETS
from Python.src.gestorAplicacion.clasesBase.Tienda import Tienda
from Python.src.gestorAplicacion.clasesHerencia import Intervenido, Cliente
from Python.src.gestorAplicacion.clasesHerencia.Cliente import *
from Python.src.gestorAplicacion.clasesHerencia.Intervenido import *
from Python.src.gestorAplicacion.clasesHerencia.Empleado import Empleado
from Python.src.gestorAplicacion.clasesHerencia.Transportista import Transportista


class Interfaz:
    @staticmethod
    def read_opcion():
        return int(input())

    @staticmethod
    def verificar(n, o):
        while True:
            o = Interfaz.read_opcion()
            if o <= 0 or o > n:
                print("El número ingresado no está en las opciones. Intenta nuevamente:")
            else:
                break
        return o

    @staticmethod
    def logistica_envio():
        o = 2
        while True:
            print("---------------------------------")
            print("          //Logistica de Envio       ")
            print("---------------------------------")
            print("Seleccione uno de los sets disponibles")
            print("1. SET ONLY:CAMISA")
            print("2. SET TU:CAMISA Y PANTALON")
            print("3. SET COMPLETO:ABRIGO,CAMISA Y PANTALON")
            Oset = Interfaz.verificar(3, 0)
            set=""
            if Oset == 1:
                set = SETS.ONLY
            elif Oset == 2:
                set= SETS.TU
            elif Oset == 3:
                set = SETS.COMPLETO

            print(set)
            print("---------------------------------")
            print(f"//{set.name}")
            print("---------------------------------")
            print("Seleccione el número de sets que necesita:")
            print("Puede pedir mínimo 1 set y máximo 5 sets")
            cantidad = Interfaz.verificar(5, 0)
            tiendas=[]

            

            # Primera interacción
            productos = Bodega.realizar_pedido(tiendas, set, cantidad)
            print(Bodega.get_resumen_pedido())
            if Bodega.get_resumen_pedido() != "El pedido se ha completado exitosamente":
                print("Seleccione una opción:")
                print("1. Seguir con el proceso de Logistica de Envio con los productos disponibles")
                print("2. Volver al menú principal para ejecutar los procesos de Compra y Control de calidad\n"
                      "para tener el stock necesario de productos")
                decision = Interfaz.verificar(2, 0)
                if decision == 2:
                    o = 1
                    break
            print()

            print("---------------------------------")
            print("          //COLECCION       ")
            print("---------------------------------")
            print("Seleccione una de las colecciones disponibles:")
            i = 1
            Colecciones=Intervenido.Colecciones
            for coleccion in Colecciones:
                print(f"{coleccion.value}. {coleccion.name}")
                i += 1
            Ocoleccion = Interfaz.verificar(i - 1, 0)
            coleccion = None

            for colec in Colecciones:
                if colec.value == Ocoleccion:
                    coleccion = colec
                    break

            print()
            print("---------------------------------")
            print(f"//{coleccion.name}")
            print("---------------------------------")
            # Segunda interacción
            intervenidos = Intervenido.intervenir(productos, coleccion)
            print("Las características de los productos intervenidos son:")
            for intervenido in intervenidos:
                print(intervenido)
            print()

            print("---------------------------------")
            print("  //Informacion del cliente      ")
            print("---------------------------------")
            print("Seleccione uno de los clientes registrados:")
            import pickle
            picklefile=open("serializados/clientes.pkl","rb")
            clientes=pickle.load(picklefile)
            picklefile.close()
            for i1 in range(len(clientes)):
                print(f"{i1+1}. {clientes[i1]}")
            Ocliente = Interfaz.verificar(len(clientes), 0)
            cliente = clientes[Ocliente - 1]
            print()
            print("---------------------------------")
            print("          //" + cliente.get_nombre())
            print("---------------------------------")
            print("CIUDAD DE ENVIO:", cliente.getCiudad().name)
            print("Seleccione el tipo de envío:")
            print("1. Envío prioritario 5 días hábiles sobrecargo de $15.000")
            print(f"2. Envío normal {cliente.getCiudad().value} días hábiles")
            print("3. Envío libre 30 días hábiles descuento de $15.000")
            Oenvio = Interfaz.verificar(3, 0)
            tipo = None
            Tipo=Cliente.Tipo
            if Oenvio == 1:
                tipo = Tipo.PRIORITARIO
            elif Oenvio == 2:
                tipo = Tipo.NORMAL
            elif Oenvio == 3:
                tipo = Tipo.LIBRE

            # Tercera interacción
            cliente.enviar(intervenidos, tipo)
            

            print()
            print("---------------------------------")
            print("          //Resumen de Pago")
            print("---------------------------------")
            print(cliente.getResumenDePago())

            print("\nRealizando Transferencias...")
            print(cliente.getConfirmacion())


            print("\nSeleccione una opción:")
            print("1. Volver al menú principal")
            print("2. Realizar nuevo proceso de Envío")

            o = Interfaz.verificar(2, 0)
            if o == 1:
                break

    @staticmethod
    def main():
        opcion = 1

        while opcion != 0:
            print("---------------------------------")
            print("    Sistema de Administracion    ")
            print("---------------------------------")
            print("1) Venta a socio")
            print("2) Modulo de compra")
            print("3) Control de calidad")
            print("4) Logistica de Envio")
            print("5) Gestion de credito")
            print("0) Salir")
            print("---------------------------------")
            print("Seleccione una opción: ")

            opcion = Interfaz.read_opcion()

            if opcion == 0:
                print("Saliendo del sistema...")
                break
            elif opcion == 1:
                print("Venta a socio")
            elif opcion == 2:
                # menuCompra()
                print("Modulo de compra")
            elif opcion == 3:
                # menuControl()
                print("Control de calidad")
            elif opcion == 4:
                Interfaz.logistica_envio()
            elif opcion == 5:
                # menuGestionCredito()
                print("Gestion de credito")
            else:
                print("Opción no válida. Intente nuevamente.")
   
def serializarEnvio():
    CARGOS=Empleado.CARGOS
    o1 = Empleado("Andrea Munera", 1, 234, 0, CARGOS.OPERARIO, 1200000)
    o2 = Empleado("Sol Perez", 3, 189, 0, CARGOS.OPERARIO, 1200000)
    o3 = Empleado("Ana Juarez", 5, 276, 0, CARGOS.OPERARIO, 1200000)

    listaEmpleados = [o1,o2,o3]
    import pickle
    picklefile=open("..\\baseDatos\\temp\\operarios.pkl","wb")
    pickle.dump(listaEmpleados,picklefile)
    picklefile.close()
    
    BancoElectronico = Banco("BancoElectronico")
    Pais=CuentaBancaria.Pais
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
    picklefile=open("..\\baseDatos\\temp\\clientes.pkl","wb")
    pickle.dump(clientes,picklefile)
    picklefile.close()
    
    cuenta = CuentaBancaria(1044450000, Pais.COLOMBIA, BancoElectronico)
    transportista = Transportista("Jimena Salgado", 3,cuenta)
    picklefile=open("..\\baseDatos\\temp\\transportistaNacional.pkl","wb")
    pickle.dump(transportista,picklefile)
    picklefile.close()

def tiendas():
    bodegaTienda1 = Bodega(50)
    bodegaTienda2 = Bodega(70)
    bodegaTienda3 = Bodega(90)

    camisa = Producto(Tipo.CAMISA, 40000)
    pantalon = Producto(Tipo.PANTALON, 50000)
    abrigo = Producto(Tipo.ABRIGO, 60000)

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

    tiendaLaureles = Tienda(1000000, bodegaTienda1, None)
    tiendaPoblado = Tienda(1200000, bodegaTienda2, None)
    tiendaEnvigado = Tienda(1100000, bodegaTienda3, None)

    tiendas=[tiendaEnvigado,tiendaPoblado,tiendaLaureles]
    pck=Serializador(tiendas,"tiendas")









if __name__ == "__main__":
    #serializarEnvio()
    #Interfaz.main()
    tiendas()

    

    