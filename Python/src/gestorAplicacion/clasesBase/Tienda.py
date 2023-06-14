class Tienda:

    def __init__(self, presupuesto, bodega, calle):
        self.presupuesto = presupuesto
        self.bodega = bodega
        self.calle = calle

    def getPresupuesto(self):
        return self.presupuesto

    def setPresupuesto(self, presupuesto):
        self.presupuesto = presupuesto

    def getBodega(self):
        return self.bodega

    def setBodega(self, bodega):
        self.bodega = bodega

    def getCalle(self):
        return self.calle

    def setCalle(self, calle):
        self.calle = calle
