 package ejercicioFinal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Lacteo extends Producto implements Fecha{
	
	//public static SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	public static ArrayList<Lacteo> listaLacteo = new ArrayList<Lacteo>();
	public static LinkedList<Lacteo> filaLacteo = new LinkedList<Lacteo>();
	public static Scanner entrada = new Scanner(System.in);
 	private LocalDate fechaVencimiento;
 	private LocalDate fechaFabricacion;
 	//Constructor
	public Lacteo(int codigoBarra, String codigoCategoria, String marca, String descripcion,String precio, 
			int stock,LocalDate fechaVencimiento, LocalDate fechaFabricacion) 
	{
		super(codigoBarra, codigoCategoria,  marca,  descripcion,  precio,  stock);
		setCategoriaProducto(CodigoInterno.LACTEO);
		setCodigoInt(CodigoInterno.LACTEO.getNumerador());
		this.fechaVencimiento = fechaVencimiento;
		this.fechaFabricacion = fechaFabricacion;
	}

	public LocalDate getFechaFabricacion() {
		return fechaFabricacion;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public CharSequence getFechaFabricacions() {
		return fechaFabricacion.toString();
	}
	public CharSequence getFechaVencimientos() {
		return fechaVencimiento.toString();}

	//Metodo de producto
	public String verTicket(Producto s) 
	{
		return s.getMarca()+" / "+s.getDescripcion()+ " / "+ s.getPrecio();
	}
	
	//Instanciar Lacteos
	public static void crearLacteo()
	{ 
		int codigo = Metodos.IngresarEntero("Codigo de barras");
		while(Metodos.verificarDuplicados(codigo ,Producto.listaProducto)==true) {
			codigo = Metodos.IngresarEntero("Codigo de barras");
		}
		System.out.println("Ingrese codigo de Categoria (str): ");
		String categoria;
		categoria =	entrada.nextLine();//
		System.out.println("Ingrese marca: (str)");
		String marca =	entrada.nextLine();//
		System.out.println("Ingrese descripcion (str): ");
		String descripcion =	entrada.nextLine();//
		String precio = Metodos.IngresarFloat();
		int stock = Metodos.IngresarEntero("Stock");
		System.out.println("Ingrese datos de fabricacion: ");
		LocalDate fechaFabricacion = Metodos.formarFechaF();
		System.out.println("Ingrese datos de vencimiento: ");
		LocalDate fechaVencimiento = Metodos.formarFechaV();
		Lacteo elemento = new Lacteo(codigo, categoria, marca, descripcion, precio, stock, fechaVencimiento, fechaFabricacion);
		System.out.println("Producto dado de alta con exito");
		Lacteo.listaLacteo.add(elemento);
		Producto.listaProducto.add(elemento);
		filaLacteo.offer(elemento);
	}
	
	//Ver vencimiento
	public  String obtenerDiferenciaEnDias(LocalDate fechaInicial)
    {
		LocalDate fechaActual = LocalDate.now();
        long diferenciaEnDias = ChronoUnit.DAYS.between(fechaActual, fechaInicial);
        
    	if(fechaInicial.isBefore(LocalDate.now())) {
    		return "vencido";
    	}else if (diferenciaEnDias>10){
        	return "Vigente";
        }else if (diferenciaEnDias==0){
        	return "Vence hoy";
        }else {
			return "Pronto a Vencer";
        }
    }
    
	//Retorna fechas formateadas
    public  String mostrarFechas(Lacteo elemento) {
    	 // Formato original
        DateTimeFormatter formatoOriginal = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaF = LocalDate.parse(elemento.getFechaFabricacions(), formatoOriginal);
        LocalDate fechaV = LocalDate.parse(elemento.getFechaVencimientos(), formatoOriginal);
        // Nuevo formato
        DateTimeFormatter nuevoFormato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaFormateadaF = fechaF.format(nuevoFormato);
        String fechaFormateadaV = fechaV.format(nuevoFormato);

    	return "\nLa fecha de fabricacion es: " + fechaFormateadaF + "\nLa fecha de vencimiento es: " +fechaFormateadaV;
}

    public static void mostrarAtributo(ArrayList<Lacteo> productos) {
    	for (Lacteo producto : productos) {
        System.out.println(producto.verTicket(producto));  
        }      
}
        
    //Retorna Array de Lacteos que estan en el archivo
    public static ArrayList<Lacteo> cargarLacteosDesdeArchivo(String nombreArchivo) {
        ArrayList<Lacteo> lacteos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Lacteo lacteo = convertirALacteo(linea);
                lacteos.add(lacteo);
               // Producto.listaProducto.add(lacteo);
            }
            
        } catch (IOException | ParseException | IllegalArgumentException e) {
        	System.out.println("ERROR FATAL EN CARGA DE DATOS");
        	// e.printStackTrace();
        }

        return lacteos;
    }
    public static Lacteo convertirALacteo(String linea) throws ParseException {
        String[] partes = linea.split("#");
        if (partes.length == 8) {
     
            return new Lacteo(
                    Integer.parseInt(partes[0]), partes[1], partes[2], partes[3],
                    partes[4], Integer.parseInt(partes[5]),
                    LocalDate.parse(partes[6]), LocalDate.parse(partes[7])
            );
        }else{
            throw new IllegalArgumentException("Formato de línea incorrecto: " + linea);
        }
    }
  
    //Recibe un array y lo escribe en el archivo
    public static void guardarLacteoEnArchivo(ArrayList<Lacteo> lacteos, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Lacteo producto : lacteos) {
                writer.write(convertirAString(producto));
                writer.newLine();
            }
            
        } catch (IOException e) {
        	System.out.println("ERROR DE GUARDADO");
        	//e.printStackTrace();
        }

    }
    public static String convertirAString(Lacteo producto) {

        return String.format(
                "%d#%s#%s#%s#%s#%d#%s#%s",
                producto.getCodigoBarra(),
                producto.getCodigoCategoria(),
                producto.getMarca(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getFechaVencimiento(),
                producto.getFechaFabricacion()
        );
    }

   
	
}
