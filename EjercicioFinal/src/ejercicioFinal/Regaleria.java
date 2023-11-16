package ejercicioFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Regaleria extends Producto{
	public static ArrayList<Regaleria> listaRegaleria = new ArrayList<Regaleria>();
	public static Stack<Regaleria> pilaRegaleria = new Stack<Regaleria>();
	private String materialFabricacion;
	//Constructor
	public Regaleria(int codigoBarra, String codigoCategoria, String marca, String descripcion,
			String precio, int stock, String materialFabricacion) 
	{
		super(codigoBarra, codigoCategoria,  marca,  descripcion,  precio,  stock);
		this.setMaterialFabricacion(materialFabricacion);
		setCategoriaProducto(CodigoInterno.REGALERIA);
		setCodigoInt(CodigoInterno.REGALERIA.getNumerador());
	}
	
	public String getMaterialFabricacion() {
		return materialFabricacion;
	}
	public void setMaterialFabricacion(String materialFabricacion) {
		this.materialFabricacion = materialFabricacion;
	}

	//Metodo de producto
	public String verTicket(Producto s) {
		return s.getMarca()+" / "+s.getDescripcion()+ " / "+ s.getPrecio();}
	
	//Metodo para instanciar regaleria
	public static void crearRegaleria()
	{
		Scanner entrada = new Scanner(System.in);
		int codigo = Metodos.IngresarEntero("Codigo de barras");
		while(Metodos.verificarDuplicados(codigo ,Producto.listaProducto)==true) {
			codigo = Metodos.IngresarEntero("Codigo de barras");
		}
		System.out.println("Ingrese codigo de Categoria (str):");
		String categoria =	entrada.nextLine();//
		System.out.println("Ingrese marca: (str)");
		String marca =	entrada.nextLine();//
		System.out.println("Ingrese descripcion (str): ");
		String descripcion =	entrada.nextLine();//
		String precio = Metodos.IngresarFloat();
		int stock = Metodos.IngresarEntero("Stock");
		System.out.println("Ingrese el material de fabricacion: ");
		String Material = entrada.nextLine();
		Regaleria elemento = new Regaleria(codigo, categoria, marca, descripcion, precio, stock, Material);
		System.out.println("Producto dado de alta con exito");
		listaRegaleria.add(elemento);
		Producto.listaProducto.add(elemento);
		pilaRegaleria.push(elemento);
	}
 
    public static void mostrarAtributo(ArrayList<Regaleria> productos) {
    	for (Regaleria producto : productos) {
        System.out.println(producto.verTicket(producto));  
        }      
}
	
	//Retorna Array de regaleria que estan en el archivo
    public static ArrayList<Regaleria> cargarRegaleriasDesdeArchivo(String nombreArchivo) {
        ArrayList<Regaleria> regalerias = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Regaleria regaleria = convertirARegaleria(linea);
                regalerias.add(regaleria);
               // Producto.listaProducto.add(regaleria);
            }
            
        } catch (IOException | ParseException | IllegalArgumentException e) {
        	System.out.println("ERROR FATAL EN CARGA DE DATOS DE REGALERIA");
        	//e.printStackTrace();
        }

        return regalerias;
    }
    public static Regaleria convertirARegaleria(String linea) throws ParseException {
        String[] partes = linea.split("#");
        if (partes.length == 7) {
     
            return new Regaleria(
                    Integer.parseInt(partes[0]), partes[1], partes[2], partes[3],
                    partes[4], Integer.parseInt(partes[5]),partes[6]);
        } else {
            throw new IllegalArgumentException("Formato de línea incorrecto: " + linea);
        }
    }
    //Recibe un array y lo escribe en el archivo
    public static void guardarRegaleriaEnArchivo(ArrayList<Regaleria> regaleria, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Regaleria producto : regaleria) {
                writer.write(convertirAString(producto));
                writer.newLine();
            }
           
        } catch (IOException e) {
        	System.out.println("ERROR FATAL EN GUARDADO DE DATOS DE REGALERIA");
        	// e.printStackTrace();
        }

    }
    public static String convertirAString(Regaleria producto) {

        return String.format(
                "%d#%s#%s#%s#%s#%d#%s",
                producto.getCodigoBarra(),
                producto.getCodigoCategoria(),
                producto.getMarca(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getMaterialFabricacion()
        );
    }
}
