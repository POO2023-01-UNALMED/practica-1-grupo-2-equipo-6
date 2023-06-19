import pickle


class Deserializador:
    def __init__(self, nombreArchivo):
        self.picklefile = open("..\\baseDatos\\temp\\" + nombreArchivo + '.pkl', "rb")
        self.objeto = pickle.load(self.picklefile)
        self.picklefile.close()

    def getObjeto(self):
        return self.objeto