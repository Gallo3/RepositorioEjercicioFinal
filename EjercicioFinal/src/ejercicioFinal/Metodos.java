package ejercicioFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Metodos {
	//Menu Principal
    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {System.out.println();
            System.out.println("MENU");
            System.out.println("1. Alta Productos");
            System.out.println("2. Listar Productos");
            System.out.println("3. Alta Personal");
            System.out.println("4. Buscar Producto por codigo");
            System.out.println("5. Buscar datos de Personal");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ha seleccionado la opción 1.");
                    subMenu();
                    break;
                case 2:
                    System.out.println("Ha seleccionado la opción 2.");
                    menuListados();
                    break;
                case 3:
                    System.out.println("Ha seleccionado la opción 3.");
                    Personal.crearPersonal();
                    break;
                case 4:
                	System.out.println("Ha seleccionado la opción 4.");
                		Producto elemento = buscarProducto(Producto.listaProducto, IngresarEntero("Codigo del producto deseado"));
                		if (elemento==null) {
                			System.out.println("Producto no encontrado.");
                		}else {
                			realizarAcciones(elemento);
                		}   
                	break;
                case 5:
                    System.out.println("Ha seleccionado la opción 5.");
                    Personal.subMenuPersonal();
                    break;
                case 0:
                    System.out.println("Guardando Datos.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);
    }
    //Menu para listar productos
    public static void menuListados() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {System.out.println();
            System.out.println("MENU LISTADOS");
            System.out.println("1. Lacteos");
            System.out.println("2. Regaleria");
            System.out.println("3. Todos");
            System.out.println("0. volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ha seleccionado la opción 1.");
                    Lacteo.mostrarAtributo(Lacteo.listaLacteo);
                    break;
                case 2:
                    System.out.println("Ha seleccionado la opción 2.");
                    Regaleria.mostrarAtributo(Regaleria.listaRegaleria);
                    break;
                case 3:
                    System.out.println("Ha seleccionado la opción 3.");
                    mostrarAtributo(Producto.listaProducto);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);
    }
    //Menu para dar de alta productos
    public static void subMenu() {
    	Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {System.out.println();
            System.out.println("SUB MENU PRODUCTOS");
            System.out.println("1. Alta Lacteo");
            System.out.println("2. Alta Regaleria");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
            	case 0:
                    break;
                case 1:
                    System.out.println("Ha seleccionado la opción 1.");
                    Lacteo.crearLacteo();
                    break;
                case 2:
                    System.out.println("Ha seleccionado la opción 2.");
                    Regaleria.crearRegaleria();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);
    }
    //Menu para buscar productos y realizar acciones
    public static void realizarAcciones(Producto producto) {
    
    	if (producto.getCodigoInt()==1) {
    		Lacteo lacteo= (Lacteo)producto;
            Scanner scanner = new Scanner(System.in);
            int opcion;
            
            do {System.out.println();
            	System.out.println(lacteo.getDescripcion());
            	System.out.println("1. Ver estado(vencimiento)");
            	System.out.println("2. Ver datos completos");
            	System.out.println("0. volver");
            	System.out.println("Seleccione una opción: ");
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opción 1: Ver estado");
                        System.out.println(lacteo.obtenerDiferenciaEnDias(lacteo.getFechaVencimiento()));
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opción 2: Ver datos completos");
                        System.out.println(lacteo.getCategoriaProducto());
                        System.out.println("Codigos de Barra/Categoria "+lacteo.getCodigoBarra()+ " / " + lacteo.getCodigoCategoria());
                        System.out.println("Marca: "+ lacteo.getMarca() + " Descripcion: "+lacteo.getDescripcion());
                        System.out.println("Fechas de Fabricacion/Vencimiento: " + lacteo.mostrarFechas(lacteo));
                        System.out.println("Stock: "+ lacteo.getStock()+" Precio: "+lacteo.getPrecio());
                        break;
                    case 0:
                    	System.out.println();
                    	break;
                    default:
                        System.out.println("Opción no válida. Por favor, elija una opción del menú.");
                }
            } while (opcion != 0);
        }else {
        	Regaleria regaleria = (Regaleria) producto; 
        	Scanner scanner = new Scanner(System.in);
        	int opcion;
            do {
            System.out.println();	
            System.out.println(regaleria.getDescripcion());
        	System.out.println("1. Establecer Stock");
        	System.out.println("2. Ver datos completos");
        	System.out.println("0. volver");
        	System.out.println("Seleccione una opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado la opción 1: Modificar stock");
                    regaleria.setStock(IngresarEntero("el nuevo stock"));
                    System.out.println("Stock establecido con exito");
                    break;
                case 2:
                    System.out.println("Has seleccionado la opción 2: Ver datos completos");
                    System.out.println(regaleria.getCategoriaProducto());
                    System.out.println("Codigos de Barra/Categoria "+regaleria.getCodigoBarra()+ " / " + regaleria.getCodigoCategoria());
                    System.out.println("Marca: "+ regaleria.getMarca() + " Descripcion: "+regaleria.getDescripcion());
                    System.out.println("Material de fabricacion: " + regaleria.getMaterialFabricacion());
                    System.out.println("Stock: "+ regaleria.getStock()+" Precio: "+regaleria.getPrecio());
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del menú.");
            }
        } while (opcion != 0);	
        }
    	}
    
    public static LocalDate formarFechaF() {
		boolean flag =false;
		LocalDate fechabien=null;
	    Scanner scanner = new Scanner(System.in);
        do {
        System.out.print("Ingrese el día: ");
        int dia = scanner.nextInt();
        System.out.print("Ingrese el mes: ");
        int mes = scanner.nextInt();
        System.out.print("Ingrese el año: ");
        int anio = scanner.nextInt();
        
        if (Exepciones.verificarFecha(dia, mes, anio)) {
            LocalDate fecha = LocalDate.of(anio, mes, dia);
            if(fecha.isBefore(LocalDate.now())) {
           // System.out.println("La fecha formada es: " + fecha);
            fechabien = fecha;
            flag = true;
            }else {
            System.out.println("La fecha de Fabricacion no puede ser posterior a la actual");
            flag=false;
            }
        } else {
            System.out.println("Fecha ingresada no válida.");
            flag=false;
        }	
        }while (flag==false);
		return fechabien;
        }
   
    public static LocalDate formarFechaV() {
    		boolean flag =false;
    		LocalDate fechabien=null;
		    Scanner scanner = new Scanner(System.in);
	        do {
	        System.out.print("Ingrese el día: ");
	        int dia = scanner.nextInt();
	        System.out.print("Ingrese el mes: ");
	        int mes = scanner.nextInt();
	        System.out.print("Ingrese el año: ");
	        int anio = scanner.nextInt();
	        
	        if (Exepciones.verificarFecha(dia, mes, anio)) {
	            LocalDate fecha = LocalDate.of(anio, mes, dia);
	            if(fecha.isAfter(LocalDate.now())) {
	           // System.out.println("La fecha formada es: " + fecha);
	            fechabien = fecha;
	            flag = true;
	            }else {
	            System.out.println("La fecha de vencimiento no puede ser anterior a la actual");
	            flag=false;
	            }
	        } else {
	            System.out.println("Fecha ingresada no válida.");
	            flag=false;
	        }	
	        }while (flag==false);
			return fechabien;
	
    }
    
    public static int IngresarEntero(String Nombre) {
	   boolean flag= false; 
	   Scanner scanner = new Scanner(System.in);
	   System.out.println("Ingrese " + Nombre + ": ");
	   while (flag==false) {
		   String valor = scanner.nextLine();
		   if((Exepciones.esEntero(valor)==true) && (Integer.parseInt(valor)>=0)) {
			   return Integer.parseInt(valor);
		   }else{
			   System.out.println("Valor no admitido, Reingrese: ");
		   }
	   }
	   return 0;
   }
  
    public static String IngresarFloat() {
	   boolean flag= false; 
	   Scanner scanner = new Scanner(System.in);
	   System.out.println("Ingrese precio: ");
	   while (flag==false) {
		   String valor = scanner.nextLine();
		   if((Exepciones.esFloat(valor)==true)&&(Float.parseFloat(valor)>0)) {
			   //System.out.println("Ingreso: " + Float.parseFloat(valor));
			   return valor;
		   }else{
			   System.out.println("Valor no admitido, Reingrese: ");
		   }
	   }
	   return null;
   }

    public static Producto buscarProducto(ArrayList<Producto> arrayProductos, int codigo) {
    	for(Producto producto : arrayProductos) {
    		if (producto.getCodigoBarra()==codigo) {
    			System.out.println("Producto encontrado");
    			return producto;
    		}
    	}
    	System.out.println("Producto no encontrado");
		return null;	
    }
    
    public static void mostrarAtributo(ArrayList<Producto> productos) {
                	for (Producto producto : productos) {
                    System.out.println(producto.verTicket(producto));  
                    }      
        }
    
    public static boolean verificarDuplicados(int codigo, ArrayList<Producto> productos) {
    	boolean aux=false;
    	for(Producto producto: productos) {
    		if (producto.getCodigoBarra()==codigo) {
    			System.out.println("Error el codigo pertenece a un producto en existencia");
    			aux = true;
    			break;
    		}else {
    			aux = false;
    		}

    	}
		 return aux;
    }
  
    private static Producto convertirProducto(String linea) throws ParseException {
        String[] partes = linea.split("#");
        if (partes.length == 8) {
     
            return new Lacteo(
                    Integer.parseInt(partes[0]), partes[1], partes[2], partes[3],
                    partes[4], Integer.parseInt(partes[5]),
                    LocalDate.parse(partes[6]), LocalDate.parse(partes[7])
            );
        } else if (partes.length == 7) {
     
            return new Regaleria(
                    Integer.parseInt(partes[0]), partes[1], partes[2], partes[3],
                    partes[4], Integer.parseInt(partes[5]),partes[6]);
            }else{
            throw new IllegalArgumentException("Formato de línea incorrecto: " + linea);
        }
    }
    public static ArrayList<Producto> cargarProductosDesdeArchivo(String nombreArchivo) {
        ArrayList<Producto> productos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Producto producto = convertirProducto(linea);
                productos.add(producto);
            }
            
        } catch (IOException | ParseException | IllegalArgumentException e) {
        	System.out.println("ERROR FATAL EN LA CARGA DE DATOS DE PRODUCTOS");
            //e.printStackTrace();
        }
        return productos;
    }    
    public static void guardarProductosEnArchivo(ArrayList<Producto> productos, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Producto producto : productos) {
            	if (producto.getCodigoInt()==1) {
                writer.write(Lacteo.convertirAString((Lacteo) producto));
                writer.newLine();}else {
                writer.write(Regaleria.convertirAString((Regaleria) producto));
                writer.newLine();
                }
            }
           
        } catch (IOException e) {
        	System.out.println("ERROR FATAL EN EL GUARDADO DE DATOS DE PRODUCTOS");            
        	//e.printStackTrace();
        }

    }

    
}

//public static ArrayList<Producto> cargarDesdeArchivo(String nombreArchivo) {
//ArrayList<Producto> productos = new ArrayList<>();
//try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
//  String linea;
//  while ((linea = reader.readLine()) != null) {
//      String[] partes = linea.split(",");
//      if (partes.length == 8) {
//          int codigo = Integer.parseInt(partes[0]);
//          String codigoCategoria = partes[1];
//          String marca= partes[2];
//          String descripcion = partes[3];
//          float precio = Float.parseFloat(partes[4]);
//          int stock = Integer.parseInt(partes[5]);
//          LocalDate fechaVencimiento = LocalDate.parse(partes[6]);
//          LocalDate fechaFabricacion = LocalDate.parse(partes[7]);
//          productos.add(new Lacteo(codigo, codigoCategoria, marca, descripcion, precio, stock, fechaVencimiento, fechaFabricacion));
//      }else if (partes.length == 7) {
//      	 int codigo = Integer.parseInt(partes[0]);
//           String codigoCategoria = partes[1];
//           String marca= partes[2];
//           String descripcion = partes[3];
//           float precio = Float.parseFloat(partes[4]);
//           int stock = Integer.parseInt(partes[5]);
//           String Material = partes[6];
//           productos.add(new Regaleria(codigo, codigoCategoria, marca, descripcion, precio, stock, Material));
//      }
//  }
//} catch (IOException e) {
//  e.printStackTrace();
//}
//return productos;
//}
//public static void guardarLacteoEnArchivo(ArrayList<Lacteo> Lacteos, String nombreArchivo) {
//try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
//	for (Lacteo producto : Lacteos) 
//	{
//      writer.write(producto.getCodigoBarra() + "," + producto.getCodigoCategoria() + "," + 
//      producto.getMarca() + "," + producto.getDescripcion() + "," + producto.getPrecio()+ "," + 
//      producto.getStock() + "," + producto.getFechaVencimiento() + "," + producto.getFechaFabricacion());
//      writer.newLine(); 
//  }
//  System.out.println("Lacteos almacenados correctamente ");
// 
//} catch (IOException e) {
//  e.printStackTrace();
//}
//}
//public static void guardarRegaleriaEnArchivo(ArrayList<Regaleria> Regalerias, String nombreArchivo) {
//    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
//    	for (Regaleria producto : Regalerias) 
//    	{
//            writer.write(producto.getCodigoBarra() + "," + producto.getCodigoCategoria() + "," + 
//            producto.getMarca() + "," + producto.getDescripcion() + "," + producto.getPrecio()+ "," + 
//            producto.getStock() + "," + producto.getMaterialFabricacion());
//            writer.newLine(); 
//        }
//        System.out.println("Objetos de regaleria almacenados correctamente ");
//       
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//    }

