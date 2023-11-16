package ejercicioFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Personal {
	private String nombre;
	private String apellido;
	private String legajo;
	private int dni;
	public static ArrayList<Personal> listaPersonal = new ArrayList<Personal>();
	public static HashMap<Integer, String> diccDniLegajo = new HashMap<Integer, String>();
	//Constructor
	public Personal(String nombre, String apellido, String legajo, int dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.legajo = legajo;
		this.dni = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getLegajo() {
		return legajo;
	}
	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	
	//Instanciar personal
	public static void crearPersonal() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Ingrese el nombre: ");
		String nombre = entrada.nextLine();
		System.out.println("Ingrese el apellido: ");
		String apellido = entrada.nextLine();
		//System.out.println("Ingrese el legajo: ");
		String legajo = ""+Metodos.IngresarEntero("el legajo");
		//System.out.println("Ingrese el DNI: ");
		int dni = Metodos.IngresarEntero("el DNI");
		while(verificarDuplicados(listaPersonal, legajo, dni)==true) {
			System.out.println("Ingrese el legajo: ");
			legajo = ""+Metodos.IngresarEntero("legajo");
			System.out.println("Ingrese el DNI: ");
			dni = Metodos.IngresarEntero("DNI");;
		}
		Personal elemento = new Personal(nombre, apellido, legajo, dni);
		System.out.println("Empleado dado de alta con exito");
		almacenarPersonal(elemento);
	}
	
	private static boolean verificarDuplicados(ArrayList<Personal> listaPersonal, String legajo, int dni) {
		boolean flag = false;
		for (Personal persona : listaPersonal) {
			if((legajo.equals(persona.getLegajo())) || (dni==persona.getDni())) {
				flag=true;
				System.out.println("Error los datos ingresados pertenecen a un empleado activo");
				break;
			}	
		}
		return flag;
	}
	
	 // Método para obtener el legajo a partir del dni
    private static String obtenerLegajoPorDni(int dni) {
        if (diccDniLegajo.containsKey(dni)) {
            return diccDniLegajo.get(dni);
        } else {
            return "No se encontro ningun empleado";
        }
    }
    // Método para obtener todos los datos del empleado a partir del legajo
    private static Personal obtenerEmpleadoPorLegajo(ArrayList<Personal> personalList, String legajo) {
        for (Personal empleado : personalList) {
            if (empleado.getLegajo().equals(legajo)) {
                return empleado;
            }
        }
        return null;
    	}
	
    // Método para almacenar objetos tipo Personal en un ArrayList y un diccionario
    private static void almacenarPersonal( Personal empleado) {
    	listaPersonal.add(empleado);
        diccDniLegajo.put(empleado.getDni(), empleado.getLegajo());
    }

    public static void subMenuPersonal() {
    	Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {System.out.println();
            System.out.println("SUB MENU PERSONAL");
            System.out.println("1. Obtener legajo ingresando DNI");
            System.out.println("2. Mostrar datos ingresando legajo");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
            	case 0:
                    break;
                case 1:
                    System.out.println("Ha seleccionado la opción 1.");
                    System.out.println(obtenerLegajoPorDni(Metodos.IngresarEntero("el DNI")));
                    break;
                case 2:
                    System.out.println("Ha seleccionado la opción 2.");
                    Personal empleado= obtenerEmpleadoPorLegajo(listaPersonal, ""+Metodos.IngresarEntero("el Legajo"));
                    if (empleado==null) {
                    	System.out.println("No se encontraron coincidencias");
                    }else {
                    	System.out.println("Nombre y Apellido: "+empleado.getNombre()+ " "+ empleado.getApellido());
                    	System.out.println("DNI: "+empleado.getDni()+" Legajo N°: "+empleado.getLegajo());
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);
    }
    
    public static void guardarEnArchivo(ArrayList<Personal> personalList, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Personal persona : personalList) {
                writer.write(persona.getNombre() + "#" + persona.getApellido() + "#" +
                        persona.getLegajo() + "#" + persona.getDni());
                writer.newLine();
            }
        } catch (IOException e) {
        	System.out.println("ERROR AL GUARDAR DATOS");
        	// e.printStackTrace();
        }
    }
    public static ArrayList<Personal> cargarDesdeArchivo(String nombreArchivo) {
        ArrayList<Personal> personal = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("#");
                if (partes.length == 4) {
                    String nombre = partes[0];
                    String apellido = partes[1];
                    String legajo = partes[2];
                    int dni = Integer.parseInt(partes[3]);

                  personal.add(new Personal(nombre, apellido, legajo, dni));
                }
            }
        } catch (IOException e) {
        	System.out.println("ERROR AL CARGAR DATOS DE PERSONAL");
        	//e.printStackTrace();
        }

        return personal;
    }
    public static void almacenarPersonal( ArrayList<Personal> empleados) {
    	for(Personal empleado: empleados) {
    	listaPersonal.add(empleado);
        diccDniLegajo.put(empleado.getDni(), empleado.getLegajo());
    	}
    }
}
