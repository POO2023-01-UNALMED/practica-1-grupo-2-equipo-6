import pickle


class Serializador:

    def __init__(self, objeto, nombreArchivo):
        self.picklefile = open("" + nombreArchivo + '.pkl', "wb")
        pickle.dump(objeto, self.picklefile)
        self.picklefile.close()



