#from tkinter import messagebox as MessageBox
class ErrorAplicacion(Exception):
    def __init__(self, message="Manejo de errores de la Aplicacion"):
        self.message = message

        super().__init__(self.message)

    def mostrarMensaje(self):
        return self.message


#Clases que heredan del ErrorAplicacion

class ErrorDatos(ErrorAplicacion):

    def __init__(self, message="\nError en el ingreso de datos. "):
        super().__init__()
        self.message += message



class ErrorProcesoInvalido(ErrorAplicacion):
    def __init__(self, message="\nNo es posible llevar a cabo este proceso."):
        super().__init__()
        self.message += message
#Subclases de ErrorDatos
class ErrorDatosIncompletos(ErrorDatos):
    def __init__(self, *datosVacios):
        mensaje = 'Los siguientes campos no tienen el valor requerido:'

        for e in datosVacios:
            mensaje = mensaje + '\n' + e

        super().__init__()
        self.message += mensaje

class ErrorDatosIncorrectos(ErrorDatos):
    def __init__(self):
        super().__init__()
        self.message += '\nEl formato de los datos ingresados no es correcto'
class ErrorFondosInsuficientes(ErrorDatos):
    def __init__(self):
        super().__init__()
        self.message += '\nSu cuenta bancaria no dispone de la cantidad necesaria para realizar esta operación'

#Subclases para error de procesos
class ErrorPrevencionBancarrota(ErrorProcesoInvalido):
    def __init__(self):
        super().__init__()
        self.message += '\nSi continúa, podría dejar a la tienda en bancarrota'


class ErrorProductosInsuficientes(ErrorProcesoInvalido):
    def __init__(self):
        super().__init__()
        self.message += ' Ninguna bodega cuenta con la cantidad de productos solicitada.\nPara aprovisionar, escoja una y efectúe una compra.'


class ErrorNoContacted(ErrorProcesoInvalido):

    def __init__(self):
        super().__init__()
        self.message += ' Verifique que haya realizado el contacto solicitado.'
class ErrorPedido(ErrorProcesoInvalido):
    def __init__(self):
        super().__init__()
        self.message+="El pedido no ha podido completar ningun producto,\n se le recomienda hacer los procesos de compra y calidad para tener disponibilidad"

class ErrorCompras(ErrorProcesoInvalido):
    def __init__(self):
        super().__init__()
        self.message+="No se ha efectuado ninguna compra, \n se recomienda comprar antes de realizar el proceso de calidad"

class ErrorCompraRevisada(ErrorProcesoInvalido):
    def __init__(self):
        super().__init__()
        self.message += ' Esta compra ya fue revisada, por favor seleccione otra.'

class ErrorRevision(ErrorProcesoInvalido):
    def __init__(self):
        super().__init__()
        self.message+="No se ha revisado ninguna compra, \n se recomienda revisar alguna compra antes de realizar el proceso de calidad"

'''
#Ejemplo manejo de excepciones

try:
    raise ErrorNoContacted()

except ErrorNoContacted:

    MessageBox.showwarning("Error", ErrorDatosIncompletos('Campo1', 'Campo2', 'Campo3').mostrarMensaje())
'''