package libreria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Libreria {
	private static String ISBN;
	private static String titulo;
	private static String autor;
	private static double precio;
	static Scanner sc=null;
	static LibroDAO dao=null;

	 public static void main(String[] args) {
		 sc=new Scanner(System.in);
		 dao=new LibroDAO();
		 menuPrincipal();
	}
	public static void menuPrincipal(){
		int opcion=0;
		do{
			System.out.println("1-Nuevo libro");
			System.out.println("2-Modificar libro");
			System.out.println("3-Buscar libro por ISBN");
			System.out.println("4-Buscar libro por autor");
			System.out.println("5-Buscar libro por título");
			System.out.println("6-Buscar libro por precio");
			System.out.println("7-Eliminar libro");
			System.out.println("8-Todos los libros");
			System.out.println("9-Número de libros");
			System.out.println("10-Salir");
			opcion=sc.nextInt();
			switch(opcion) {
				case 1:
	                nuevoLibro();
	                break;
	            case 2:
	                modificarLibro();
	                break;
	            case 3:
	                busquedaISBN();
	                break;
	            case 4:
	                busquedaAutor();
	                break;
	            case 5:
	                busquedaTitulo();
	                break;
	            case 6:
	                busquedaPorPrecio(precio, precio);
	                break;
	            case 7:
	            	eliminarLibro();
	            	break;
	            case 8:
	            	mostrarLibros();
	            	break;
	            case 9:
	            	numeroDeLibros();
	            	break;
	            case 10:
	            	guardarYSalir();
	            	break;
	            default:
	            	System.out.println("Introduce un numero del 1-10");
			}	
		}while(opcion!=10);
	}
	public static void nuevoLibro(){
		sc.nextLine();
		System.out.println("Introduce el ISBN del libro:");
		ISBN=sc.nextLine();
		System.out.println("Introduce el título del libro:");
		titulo=sc.nextLine();
		System.out.println("Introduce al autor del libro:");
		autor=sc.nextLine();
		System.out.println("Introduce el precio del libro:");
		precio=sc.nextDouble();
		Libro nuevo= new Libro(ISBN,titulo,autor,precio);
		dao.create(nuevo);
		if(nuevo!=null) {
			System.out.println("Libro añadido correctamente.");
		}
	}
	public static void modificarLibro(){
		sc.nextLine();
		System.out.println("Introduce el ISBN del libro que vas a modificar:");
		ISBN=sc.nextLine();
		System.out.println("Introduce el titulo del libro modificado:");
		titulo=sc.nextLine();
		System.out.println("Introduce el autor del libro modificado:");
		autor=sc.nextLine();
		System.out.println("Introduce el precio del libro modificado:");
		precio=sc.nextDouble();
		dao.read(ISBN);
		Libro l=new Libro(ISBN,titulo,autor,precio);
		System.out.println(dao.update(l));
	}
	public static void busquedaISBN(){
		sc.nextLine();
		System.out.println("Introduce el ISBN del libro que quieres buscar:");
		ISBN=sc.nextLine();
		 Libro libro = dao.read(ISBN);
	        if (libro != null) {
	            System.out.println(libro.toString());
	        } else {
	            System.out.println("El libro no existe en la biblioteca");
	        }
	}
	public static void busquedaAutor(){
		sc.nextLine();
		System.out.println("Introduce el autor del libro que quieres buscar:");
		autor=sc.nextLine();
		 List<Libro> libros = dao.buscarAutor(autor);
	        if (libros != null) {	        	
	            for (Libro li : libros) {
                    System.out.println(li.lecturaAutor());
                }
	        } else {
	            System.out.println("El libro no existe en la biblioteca");
	        }
	}
	public static void busquedaTitulo(){
		sc.nextLine();
		System.out.println("Introduce el titulo del libro que quieres buscar:");
		titulo=sc.nextLine();
		 Libro libro = dao.buscarTitulo(titulo);
	        if (libro != null) {
	            System.out.println(libro.toString());
	        } else {
	            System.out.println("El libro no existe en la biblioteca");
	        }
	}
	public static void busquedaPorPrecio(double min, double max){
		System.out.println("Introduce el precio minimo de busqueda:");
		min=sc.nextDouble();
		System.out.println("Introduce el precio maximo de busqueda");
		max=sc.nextDouble();
	    List<Libro> libros = dao.buscarPorPrecio(min, max);
	    if(libros != null){
	         for (Libro libro : libros) {
	             System.out.println(libro);
	         }
	    }else{
	         System.out.println("No se encontraron libros en el rango de precios indicado");
	    }	         	   
	}
	public static void eliminarLibro(){
		sc.nextLine();
		System.out.println("Introduce el ISBN del libro a eliminar");
		ISBN=sc.nextLine();
		dao.delete(ISBN);
		System.out.println("Libro eliminado correctamente.");
	}
	public static void mostrarLibros(){
		sc.nextLine();
		System.out.println("Estos son los libros:");
		List<Libro> libros = new ArrayList<Libro>();
		libros=dao.getAllLibros();
	        if(libros != null){
	            System.out.println(libros.toString());
	        } else {
	            System.out.println("El libro no existe en la biblioteca");
	        }
	}
	public static void numeroDeLibros(){
		sc.nextLine();
		int numLibros = dao.numeroLibros();
        System.out.println("La biblioteca tiene " + numLibros + " libros");
        
	}
	public static void guardarYSalir(){
		dao.cerrarConexion();
		System.out.println("Se ha desconectado de la base de datos correctamente.");
		sc.close();
	}
}	