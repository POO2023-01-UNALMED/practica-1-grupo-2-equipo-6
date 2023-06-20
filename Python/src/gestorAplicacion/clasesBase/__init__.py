from Producto import *
from Serializador import *
from Deserializador import *

p = Producto(Producto.Tipo.CAMISA)
Serializador(p, 'Producto1')
o = Deserializador('Producto1').getObjeto()

print(o.getNombre())