package ejercicioFinal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainMenu {
	public static void main(String[] args) {
	final String direccionProducto= ("C:\\Users\\franc\\git\\repositorioFinal\\EjercicioFinal\\src\\ejercicioFinal\\ArchivoProductos.txt");
	final String direccionLacteo= ("C:\\Users\\franc\\git\\repositorioFinal\\EjercicioFinal\\src\\ejercicioFinal\\ArchivoLacteos.txt");
	final String direccionRegaleria= ("C:\\Users\\franc\\git\\repositorioFinal\\EjercicioFinal\\src\\ejercicioFinal\\ArchivoRegalerias.txt");
	final String direccionPersonal = ("C:\\Users\\franc\\git\\repositorioFinal\\EjercicioFinal\\src\\ejercicioFinal\\ArchivoPersonal.txt");
	Personal.almacenarPersonal(Personal.cargarDesdeArchivo(direccionPersonal));
	Producto.listaProducto= Metodos.cargarProductosDesdeArchivo(direccionProducto);
	Lacteo.listaLacteo =  Lacteo.cargarLacteosDesdeArchivo(direccionLacteo);
	Regaleria.listaRegaleria = Regaleria.cargarRegaleriasDesdeArchivo(direccionRegaleria);
	Metodos.mostrarMenu();
	Personal.guardarEnArchivo(Personal.listaPersonal, direccionPersonal);
	Lacteo.guardarLacteoEnArchivo(Lacteo.listaLacteo, direccionLacteo);
	Regaleria.guardarRegaleriaEnArchivo(Regaleria.listaRegaleria, direccionRegaleria);
	Metodos.guardarProductosEnArchivo(Producto.listaProducto, direccionProducto);
	System.out.println("Datos guardados con exito.\n!Hasta Luego¡");

}
}