package libreria;

public class Libro {

	private String ISBN;
	private String titulo;
	private String autor;
	private double precio;
	
	
	public Libro(String iSBN, String titulo, String autor, double precio) {
		this.ISBN = iSBN;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
	}
	
	public String toString() {
		String cad= "ISBN: "+this.ISBN+ "\tTítulo: " + this.titulo+"\tAutor: "+this.autor+"\tPrecio: "+this.precio;
		cad+="\n";
		return cad;
	}

	public String lecturaAutor() {
		String cad=autor+"\n\t"+this.titulo+"\n";
		return cad;
	}
	public String getISBN() {
		return ISBN;
	}


	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	
}