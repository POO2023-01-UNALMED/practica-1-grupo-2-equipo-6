
from Python.src.gestorAplicacion.clasesBase.Persona import Persona

from Python.src.baseDatos.Serializador import Serializador
from Python.src.baseDatos.Deserializador import Deserializador


if __name__ == "__main__":
    persona = Persona("gabriel",3)

    serializar = Serializador(persona,"persona")


    # bodegaProveedor1 = Bodega(100)
    # bodegaProveedor2 = Bodega(100)
    # bodegaProveedor3 = Bodega(100)
    #
    # proveedor1 = Proveedor("Ezze", bodegaProveedor1, 20000, 25000, 30000, 114)
    # proveedor2 = Proveedor("Jean", bodegaProveedor2, 20000, 25000, 30000, 11)
    # proveedor3 = Proveedor("Carla", bodegaProveedor3, 20000, 25000, 30000, 56)
    #
    # proveedores = [proveedor1, proveedor2, proveedor3]
    #
    # transportista1 = Transportista("Gabriel", 5000, 100, 200)
    # transportista2 = Transportista("Maria", 4000, 150, 200)
    # transportista3 = Transportista("Fatima", 3000, 200, 200)
    #
    # transportistas = [transportista1, transportista2, transportista3]
    #
    # camisa = Producto("CAMISA", 40000, 20000)
    # pantalon = Producto("PANTALON", 50000, 25000)
    # abrigo = Producto("ABRIGO", 60000, 30000)
    #
    # bodega = Bodega(50)
    #
    # tienda = Tienda(1000000, bodega, 60)
    #
    # for i in range(5):
    #     bodega.agregarProducto(camisa)
    #
    # for j in range(8):
    #     bodega.agregarProducto(pantalon)
    #
    # for j in range(4):
    #     bodega.agregarProducto(abrigo)
    #
    # compra = Compra(tienda)
    #
    # compra.hacerPedido(compra.getTienda())
    # compra.recomendarProveedor(compra.getPedido(), proveedores)
    # compra.recomendarTransportista(tienda, compra.getProveedor(), transportistas)
    # print(compra.getPedido()[0].getTipo())
    # # #print(compra.getProveedor().getNombre(), compra.getTransportista().getNombre())
    # # for producto in compra.getProveedor().getBodega().getProductosEnBodega():
    # #     # print(producto.getCosto(), producto.getPrecio())
    # #     print(producto.getTipo(), producto.getCosto())
    # # print(compra.getProveedor().getDescuento())
    #
    # Seriealizador(compra, "compra1")
    #
    # compraDeserializada = Deserializador("compra").getObjeto()
    #
    # print(compraDeserializada.getPedido()[0].getTipo())



