package baseDatos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializador {
	
	public Serializador(Object obj, String nombre) {
		
        String archivo = "C:AdministracionDeTienda\\src\\baseDatos\\temp\\"+nombre+".txt";
      
        try
        {  
            FileOutputStream archivoStream = new FileOutputStream(archivo);
            ObjectOutputStream out = new ObjectOutputStream(archivoStream);
             
            out.writeObject(obj);
             
            out.close();
            archivoStream.close();
             
//            System.out.println("Exito");
 
        }
         
        catch(IOException ex)
        {
            System.out.println(ex);
        }
	}

}