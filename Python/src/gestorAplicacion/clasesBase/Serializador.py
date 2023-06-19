import pickle


class Serializador:

    def __init__(self, objeto, nombreArchivo):
        self.picklefile = open("..\\baseDatos\\temp\\" + nombreArchivo + '.pkl', "wb")
        pickle.dump(objeto, self.picklefile)
        self.picklefile.close()
