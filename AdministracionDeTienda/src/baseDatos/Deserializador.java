package baseDatos;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializador {
	Object obj = null;

	public Deserializador(String nombre) {
	    
        try
        {  
            FileInputStream archivo = new FileInputStream("src\\baseDatos\\temp\\"+nombre+".txt");
            ObjectInputStream in = new ObjectInputStream(archivo);

            obj = in.readObject();
             
            in.close();
            archivo.close();
             
//            System.out.println("exito ");
          
        }
         
        catch(IOException ex)
        {
            System.out.println(ex);
        }
         
        catch(ClassNotFoundException ex)
        {
            System.out.println(ex);
        }
	        
	}
	
	public Object getObj() {
		return obj;
	}

}
