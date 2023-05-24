package gestorAplicacion.clasesBase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import gestorAplicacion.clasesBase.CuentaBancaria.Pais;
import gestorAplicacion.clasesBase.Transferencia.EstadoPago;

public class Banco implements Serializable{


/**

*

*/

private static final long serialVersionUID = 1L;

private HashMap<CuentaBancaria, ArrayList<Credito>> historialesCrediticios=new HashMap<CuentaBancaria,ArrayList<Credito>>();

//private HashMap<Object, ArrayList<Credito>> historialesCrediticiosEntidades=new HashMap<Object,ArrayList<Credito>>();

private ArrayList<CuentaBancaria> cuentas= new ArrayList<CuentaBancaria>();

private HashMap<CuentaBancaria, ArrayList<Transferencia>> historialDePagos=new HashMap<CuentaBancaria,ArrayList<Transferencia>>();

private String nombre;


public Banco(String nombre){



this.nombre=nombre;

}











@SuppressWarnings("finally")

public PuntajeCredito darPuntajeCrediticio(CuentaBancaria cuenta){


double puntuacion=0.0;

//Frecuencia de retrasos

//Cantidad de tiendas

//Frecuencia de creditos que hago: Mientras mas generara creditos de forma reciente mas bajo era el puntaje




double proporcionRetrasos=0.0;

double AmortizacionFaltante= 0;

try {


//Calculemos el total faltante de mi deuda sin intereses

for(Credito c:historialesCrediticios.get(cuenta)){


AmortizacionFaltante=(c.getCuotaBaseMensual()*(c.getCantidadCuotas()-c.getCuotasPagadas().size()));

puntuacion+=100*(1-AmortizacionFaltante/c.getCantidadCredito());


}




//Puede ser que no esté en el historialDePagos


int cantidadDePagos=0, cantidadRetrasos = 0;


for(Transferencia t:historialDePagos.get(cuenta)){

cantidadDePagos++;

if(t.getPuntualidadPago()==EstadoPago.RETRASADO){

cantidadRetrasos++;

}

}


proporcionRetrasos=cantidadRetrasos/cantidadDePagos;


if(proporcionRetrasos<0.1) {


puntuacion+=50;


}

else if(0.1<proporcionRetrasos && proporcionRetrasos<=0.25){

puntuacion+=30;

}

else if(0.25<proporcionRetrasos && proporcionRetrasos<0.50) {


puntuacion+=20;


}

else if(0.5<=proporcionRetrasos){

puntuacion+=5;

}



}

catch(NullPointerException e){

if(puntuacion==0.0) {

return null;

//En este caso, la segunda parte del programa no hace nada

}

else {

return PuntajeCredito.BAJO;

}


//Significa que:

// *** No tengo Créditos realizados puntuacion==0.0

// *** Tengo creditos realizados pero no los he pagado nunca puntuacion!=0

}

finally{


if(puntuacion>=300){

return PuntajeCredito.ALTO;

}

else if(puntuacion<300 && puntuacion>=150){

return PuntajeCredito.MEDIO;

}

else{

return PuntajeCredito.BAJO;

}


}

}




public Credito generarCredito(Credito credito) {

try {


historialesCrediticios.get(credito.getDeudor()).add(credito);

}

catch(NullPointerException e) {

ArrayList<Credito> credito1=new ArrayList<Credito>(){{{add(credito);}}};

historialesCrediticios.put(credito.getDeudor(),credito1);

}


return credito;

}




public HashMap<CuentaBancaria, ArrayList<Credito>> getHistorialesCrediticios() {

return historialesCrediticios;

}


public void setHistorialesCrediticios(HashMap<CuentaBancaria, ArrayList<Credito>> historialesCrediticios) {

this.historialesCrediticios = historialesCrediticios;

}


public ArrayList<CuentaBancaria> getCuentas() {

return cuentas;

}


public void setCuentas(ArrayList<CuentaBancaria> cuentas) {

this.cuentas = cuentas;

}


public HashMap<CuentaBancaria, ArrayList<Transferencia>> getHistorialDePagos() {

return historialDePagos;

}


public void setHistorialDePagos(HashMap<CuentaBancaria, ArrayList<Transferencia>> historialDePagos) {

this.historialDePagos = historialDePagos;

}

public Banco autorizarCuenta(String IBAN) {

try {

for(CuentaBancaria c: cuentas) {


if(c.getIBAN().equals(IBAN)) {

return null;

}


}

return this;

}

catch(NullPointerException e) {

return this;

}

}



public String getNombre() {

return nombre;

}

public void setNombre(String nombre) {

this.nombre = nombre;

}


//Estar mal y tener dinero->Pague y le ofrezco un crédito nuevo PUNTAJE BAJO Y

public ArrayList<Transferencia> solucionarProblema(ArrayList<Credito> deudas, PuntajeCredito puntaje){

ArrayList<Transferencia> pagosDeudas=new ArrayList<Transferencia>();

for(Credito credito:deudas) {


Tienda.pagarTodo(puntaje, credito);

pagosDeudas.addAll(credito.getCuotasPagadas());

}

return pagosDeudas;


//Se ejecuta si tengo el dinero para pagar(tasa de interés total de las 24 cuotas)

//Retorna Transferencia con estados A_TIEMPO

}

public CuentaBancaria solucionarProblema(double cantidadDeuda){

Venta.setPorcentajeBanco(0.2);

CuentaBancaria fondoAuxiliar=new CuentaBancaria(0,Pais.COLOMBIA,this,cantidadDeuda,Tienda.getCuentaTienda());

return fondoAuxiliar;

}

public Transferencia abonarCuentaAuxiliar(double abono) {

CuentaBancaria fondoAuxiliar=null;

for(CuentaBancaria cuenta:cuentas) {

if(cuenta.getPropietario() instanceof Banco && cuenta.getDinero()+abono<cuenta.getCantidadLimite()) {

fondoAuxiliar=cuenta;

}

}

return new Transferencia(Tienda.getCuentaTienda(),fondoAuxiliar, abono);

}


//ABONANDO AL FONDO AUXILIAR


public Transferencia solucionarProblema(CuentaBancaria c) {

//PASANDO AL FONDO DE EMPLEADOS

return new Transferencia(Tienda.getCuentaTienda(),c,Tienda.getCuentaTienda().getDinero()*0.05);

}

public CuentaBancaria solucionarProblema() {

//Se destina el 10% de los salarios a este fondo

return new CuentaBancaria(0,Pais.COLOMBIA,this,Tienda.getCuentaTienda());


}





/*

*

* Estar ALTO o MEDIO y no tener dinero -> Le ofrezco un préstamo

* Estar ALTO y tener dinero -> ¿Quiere abrir un fondo de empleados? y le ofrezco un crédito nuevo

* Estar BAJO y no tener dinero-> Deme porcentaje de las ventas y genere un fondo para guardarlo

* Estar BAJO o MEDIO y tener dinero->Pague y le ofrezco un crédito nuevo

*

public void solucionarProblema(SolucionesProblemaFinanciero.NONE) {

//Cuenta

}

Se puede ofrecer la opción de generar un nuevo crédito return Crédito si el usuario no estaba mal

*/


enum PuntajeCredito{


BAJO(16.347f),MEDIO(10.53f),ALTO(4.216f);


private float tasaDeInteres;

private PuntajeCredito(float tasaDeInteres) {

this.setTasaDeInteres(tasaDeInteres);

}

public float getTasaDeInteres() {

return tasaDeInteres;

}

public void setTasaDeInteres(float tasaDeInteres) {

this.tasaDeInteres = tasaDeInteres;

}


}

enum SolucionesProblemaFinanciero{


PAGAR_DEUDAS(") Saldar las deudas pendientes: Hay "

+ "\nsuficiente cantidad de dinero en la"

+ "\ntienda para liquidar las deudas.\n"),

NONE(") Crear un fondo de empleados o, en caso\n"

+ "de ya contar con uno destinar el 5%\n"

+ "del presupuesto actual de la tienda\n"

+ "al mismo.\n"),

SOLICITAR_CREDITO(") Solicitar un nuevo credito: Puede que "

+ "\nla tienda tenga un presupuesto insuficiente"

+ "\npara cubrir sus actividades comerciales.\n"),

PORCENTAJE_VENTAS(") Dar al banco el 20% de las ganancias "

+ "\npor cada venta a un socio\n");


private String descripcion;


private SolucionesProblemaFinanciero(String descripcion){

this.setDescripcion(descripcion);

}

public String getDescripcion() {

return descripcion;

}

public void setDescripcion(String descripcion) {

this.descripcion = descripcion;

}


}


}