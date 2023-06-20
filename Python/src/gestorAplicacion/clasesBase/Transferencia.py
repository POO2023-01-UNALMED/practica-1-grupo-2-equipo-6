class Transferencia:
    def __init__(self, cuenta, puntajeCrediticio = None, puntualidadPago=None, pagarSoloMes=True, cantidad =0, banco=None):



        self.cantidad = cantidad
        self.remitente = cuenta
        self.destinatario = banco
        self.puntualidadPago = None
        self.comprobante = False
        self.detalle = ""

        # LOS PAGOS QUE NO SEAN POR CRÉDITOS, NO VAN EN EL HISTORIAL DE PAGOS

        # Agregarse al historial correspondiente
        try:
            banco.historialDePagos[cuenta].append(self)
        except KeyError:
            banco.historialDePagos[cuenta] = [self]
        except AttributeError:
            None

        cuenta.transferencias.append(self)

    def isComprobante(self):
        return self.comprobante

    def setComprobante(self, comprobante):
        self.comprobante = comprobante

    def getDetalle(self):
        return self.detalle

    def setDetalle(self, detalle):
        self.detalle = detalle

    def getPuntualidadPago(self):
        return self.puntualidadPago

    def setPuntualidadPago(self, estadoPago):
        self.puntualidadPago = estadoPago

    def realizarTransferencia(self):
        self.remitente.setDinero(self.remitente.getDinero() - self.cantidad)
        self.destinatario.setDinero(self.destinatario.getDinero() + self.cantidad)
        self.comprobante = True

    def __str__(self):
        s = "Transferencia efectuada.\nCantidad transferida: $" + str(self.cantidad)
        if isinstance(self.destinatario, Persona):
            return s + "\nDestinatario: " + self.destinatario.getNombre()
        elif isinstance(self.destinatario, Socio):
            return s + "\nDestinatario: " + self.destinatario.getNombre()
        elif isinstance(self.destinatario, CuentaBancaria):
            return s + "\nDestinatario: Cuenta " + self.destinatario.getIBAN()

        return s

    def getRemitente(self):
        return self.remitente

    def setRemitente(self, remitente):
        self.remitente = remitente

    def getDestinatario(self):
        return self.destinatario

    def setDestinatario(self, destinatario):
        self.destinatario = destinatario
    def gestionPago(self,remitente,cantidad):
        self.remitente=remitente
        self.cantidad=cantidad
    def getCantidad(self):
        return self.cantidad
    def setCantidad(self,cantidad):
        return self.cantidad

    @staticmethod
    def pagoEnvio(costo, cliente, pagoTransporte):
        total = 0
        from Bodega import Bodega
        pagosPedido = Bodega.pagos

        for pago in pagosPedido:
            total += pago.getCantidad()

        ganancia = (costo - total) / (len(pagosPedido)+1)
        cuenta = cliente.getCuenta()
        transferencias = []

        for pago in pagosPedido:
            pago.setRemitente(cuenta)
            pago.setCantidad(pago.getCantidad() + ganancia)
            pago.realizarTransferencia()
            transferencias.append(pago)


        pagoTransporte.realizarTransferencia()
        transferencias.append(pagoTransporte)


        return transferencias


class EstadoPago:
    A_TIEMPO = "A_TIEMPO"
    RETRASADO = "RETRASADO"

