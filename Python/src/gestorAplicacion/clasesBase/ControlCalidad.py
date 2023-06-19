from Proveedor import *
from Transportista import *
from Informe import *

class ControlCalidad:
    def __init__(self, compra):
        self.compra = compra
        self.proveedor = compra.getProveedor()
        self.transportista = compra.getTransportista()
        self.productosDefectuosos = []
        self.productosExtraviados = []
        self.productosAReponerP = []
        self.productosAReponerT = []
        self.revision = []
        self.prodsFaltantesCompra = []
        self.contactarP = False
        self.contactarT = False
        self.informe = None

    def revisar(self, c):
        if self.compra == c:
            c.generarProductosExtraviados()
            c.generarCompraLlego()
            productosDefectuosos = Proveedor.generarProductos(c.getCompraLlego())
            productosExtraviados = c.getProductosExtraviados()
            productosRevision = []
            #prodsFaltantesCompra = ControlCalidad.listaSinLista(c.getPedido(), c.getProveedorSeleccionado().getBodega().getProductos())
            prodsFaltantesCompra = ControlCalidad.listaSinLista(c.getPedido(), c.getProductos())
            self.prodsFaltantesCompra = prodsFaltantesCompra
            self.productosDefectuosos = productosDefectuosos
            self.productosExtraviados = productosExtraviados
            productosRevision = productosDefectuosos + productosExtraviados
            self.revision = productosRevision
            c.setRevisado(True)
            return productosRevision
        else:
            return 
       
    @classmethod   
    def listaSinLista(cls, lista1, lista2):
        if len(lista1) == len(lista2) and set(lista1) == set(lista2):
            return []

        compraSinDefectuosos = []

        contadores1 = {}
        contadores2 = {}

        for producto1 in lista1:
            clave = producto1.getNombre() + "-" + str(producto1.getPrecio())
            contadores1[clave] = contadores1.get(clave, 0) + 1

        for producto2 in lista2:
            clave = producto2.getNombre() + "-" + str(producto2.getPrecio())
            contadores2[clave] = contadores2.get(clave, 0) + 1

        for producto1 in lista1:
            clave = producto1.getNombre() + "-" + str(producto1.getPrecio())
            if clave not in contadores2 or contadores2[clave] <= 0:
                compraSinDefectuosos.append(producto1)
            else:
                contadores2[clave] -= 1

        if not compraSinDefectuosos:
            return []

        return compraSinDefectuosos
    
    def contactar(self, persona):
        if isinstance(persona, Transportista):
            self.contactarTransportista(persona)
        elif isinstance(persona, Proveedor):
            self.contactarProveedor(persona)

    def contactarProveedor(self, proveedor):
        if self.proveedor == proveedor:
            self.productosAReponerP = proveedor.pDefectuososAReponer(self)
            self.compra.getTienda().getBodega().abastecerBodega(self, self.productosAReponerP)
            self.contactarP = True
            return proveedor
        else:
            return None

    def contactarTransportista(self, transportista):
        if self.transportista == transportista:
            self.productosAReponerT = Transportista.generarProductos(self.productosExtraviados)
            self.compra.getTienda().getBodega().abastecerBodega(self, self.productosAReponerT)
            self.contactarT = True
            return transportista
        else:
            return None
    
    def contactarForzado(self, persona):
        if isinstance(persona, Transportista):
            self.contactarTransportistaForzado(persona)
        elif isinstance(persona, Proveedor):
            self.contactarProveedorForzado(persona)

    def contactarProveedorForzado(self, proveedor):
        if self.proveedor == proveedor:
            self.productosAReponerP = self.productosDefectuosos
            self.compra.getTienda().getBodega().abastecerBodega(self, self.productosAReponerP)
            self.contactarP = True
            return proveedor
        else:
            return None

    def contactarTransportistaForzado(self, transportista):
        if self.transportista == transportista:
            self.productosAReponerT = self.productosExtraviados
            self.compra.getTienda().getBodega().abastecerBodega(self, self.productosAReponerT)
            self.contactarT = True
            return transportista
        else:
            return None

    def generarInforme(self, proveedor, transportista):
        if self.proveedor == proveedor and self.transportista == transportista:
            proveedor.calificar(self)
            transportista.calificar(self)
            informe = Informe(Informe.TipoInforme.INFORME_CONTROL_CALIDAD, self)
            self.informe = informe
            return informe
        else:
            return None
    
    def getInforme(self):
        return self.informe
    
    def setInforme(self, informe):
        self.informe = informe
        
    def getContactarT(self):
        return self.contactarT

    def getContactarP(self):
        return self.contactarP
    
    def setContactarT(self, bool):
        self.contactarT = bool

    def setContactarP(self, bool):
        self.contactarP = bool

    def getCompra(self):
        return self.compra
    
    def getProveedor(self):
        return self.proveedor
    
    def getTransportista(self):
        return self.transportista
    
    def getProductosDefectuosos(self):
        return self.productosDefectuosos
    
    def getProductosExtraviados(self):
        return self.productosExtraviados
    
    def getProductosAReponerP(self):
        return self.productosAReponerP
    
    def getProductosAReponerT(self):
        return self.productosAReponerT
    
    def getRevision(self):
        return self.revision
    
    def getProdsFaltantesCompra(self):
        return self.prodsFaltantesCompra
    
    def setCompra(self, compra):
        self.compra = compra

    def setProveedor(self, proveedor):
        self.proveedor = proveedor

    def setTransportista(self, transportista):
        self.transportista = transportista
    
    def setProductosDefectuosos(self, productosDefectuosos):
        self.productosDefectuosos = productosDefectuosos
    
    def setProductosExtraviados(self, productosExtraviados):
        self.productosExtraviados = productosExtraviados
    
    def setProductosAReponerP(self, productosAReponerP):
        self.productosAReponerP = productosAReponerP
    
    def setProductosAReponerT(self, productosAReponerT):
        self.productosAReponerT = productosAReponerT

    def setRevision(self, revision):
        self.revision = revision

    def setProdsFaltantesCompra(self, prodsFaltantesCompra):
        self.prodsFaltantesCompra = prodsFaltantesCompra


