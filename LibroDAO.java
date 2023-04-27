package libreria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class LibroDAO {
    private Connection conexion;
    public LibroDAO() {
        this.connect();
    }
    //Conectar BBDD
    private void connect(){
    	try {
    		String url="jdbc:mysql://localhost:3306/ejerciciosProgramacion";
    		String user="root";
    		String password="root";
    		this.conexion=DriverManager.getConnection(url, user, password);

    	} catch (SQLException ex) {
    		ex.printStackTrace();
    	}
    }
    //Desconectar BBDD
     public void cerrarConexion(){
    	 try{
    	    if(conexion!=null){
    	       conexion.close();
    	    }
    	 } catch(SQLException ex){
    	    ex.printStackTrace();
    	 }
    }
    //CREATE
    public boolean create(Libro libro) {
        boolean exito=false;
        try {
            String sql="INSERT INTO libros(ISBN,titulo,autor,precio) VALUES (?, ?, ?, ?)";
            PreparedStatement prepared=conexion.prepareStatement(sql);
            prepared.setString(1, libro.getISBN());
            prepared.setString(2,libro.getTitulo());
            prepared.setString(3, libro.getAutor());
            prepared.setDouble(4, libro.getPrecio());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exito;
    }
    //READ
    public Libro read(String lectorISBN) {
        Libro libro=null;
        try {
        	String sql="SELECT * FROM libros WHERE ISBN=?";
            PreparedStatement prepared=conexion.prepareStatement(sql);
            prepared.setString(1, lectorISBN);
            ResultSet resultados=prepared.executeQuery();
            if(resultados.next()) {
            	String ISBN=resultados.getString(1);
            	String titulo=resultados.getString(2);
            	String autor=resultados.getString(3);
            	double precio=resultados.getDouble(4);
            	libro=new Libro(ISBN,titulo,autor,precio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return libro;
    }
    //UPDATE
    public boolean update(Libro libro) {
    	boolean exito=false;
        try {
        	String sql="UPDATE libros SET titulo=?, autor=?, precio=? WHERE ISBN=?";
            PreparedStatement prepared=conexion.prepareStatement(sql);
            prepared.setString(1, libro.getTitulo());
            prepared.setString(2, libro.getAutor());
            prepared.setDouble(3, libro.getPrecio());
            prepared.setString(4, libro.getISBN());
            int registrosModificados=prepared.executeUpdate();
			if(registrosModificados==1) {
				exito=true;
			}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exito;
    }
    //DELETE
    public boolean delete(String ISBN) {
    	boolean exito=false;
        try {
            String sql="DELETE FROM libros WHERE ISBN=?";
            PreparedStatement prepared=conexion.prepareStatement(sql);
            prepared.setString(1, ISBN);
            int registrosModificados=prepared.executeUpdate();
			if(registrosModificados==1) {
				exito=true;
			}
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exito;
    }
    //Mostrar todos los libros
    public List<Libro> getAllLibros() {
       List<Libro> libros=new ArrayList<Libro>();
       String ISBN="";
       String titulo="", autor="";
       double precio=0;
       Libro l=null;
        try {
			Statement sentencia=conexion.createStatement();
        	String sql="SELECT * FROM libros";
            ResultSet resultados=sentencia.executeQuery(sql);
            while (resultados.next()) {
            	ISBN=resultados.getString(1);
            	titulo=resultados.getString(2);
            	autor=resultados.getString(3);
            	precio=resultados.getDouble(4);
            	l=new Libro(ISBN,titulo,autor,precio);
                libros.add(l);
            }
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
		return libros;
        }
    //Buscar por autor
    public List<Libro> buscarAutor(String lectorAutor) {
      	List<Libro> libros = new ArrayList<Libro>();
        String ISBN="", titulo="", autor="";
        double precio=0;
        Libro libro = null;
        try {

     		String sql="SELECT * FROM libros WHERE autor LIKE ?";
            PreparedStatement prepared = conexion.prepareStatement(sql);
            prepared.setString(1,"%" +lectorAutor +"%");
            ResultSet resultados = prepared.executeQuery();
            while (resultados.next()) {
                ISBN = resultados.getString(1);
                titulo = resultados.getString(2);
                autor = resultados.getString(3);
                precio = resultados.getDouble(4);
                libro = new Libro(ISBN, titulo, autor, precio);
                libros.add(libro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return libros;
    }
    //Buscar por titulo
    public Libro buscarTitulo(String lectorTitulo) {
        Libro libro=null;
        try {
        	String sql="SELECT * FROM libros WHERE titulo=?";
            PreparedStatement prepared=conexion.prepareStatement(sql);
            prepared.setString(1, lectorTitulo);
            ResultSet resultados=prepared.executeQuery();
            if(resultados.next()) {
            	String ISBN=resultados.getString(1);
            	String titulo=resultados.getString(2);
            	String autor=resultados.getString(3);
            	double precio=resultados.getDouble(4);
            	libro=new Libro(ISBN,titulo,autor,precio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return libro;
    }
    //Buscar por precio
    public List<Libro> buscarPorPrecio(double min, double max) {
    	  List<Libro> libros=new ArrayList<Libro>();
          String ISBN="";
          String titulo="", autor="";
          double precio=0;
          Libro libro=null;
           try {
           		String sql="SELECT * FROM libros where precio>"+min+" and precio<"+max;
           		PreparedStatement prepared=conexion.prepareStatement(sql);
                ResultSet resultados=prepared.executeQuery(sql);
                while (resultados.next()) {
               	ISBN=resultados.getString(1);
               	titulo=resultados.getString(2);
               	autor=resultados.getString(3);
               	precio=resultados.getDouble(4);
               	libro=new Libro(ISBN,titulo,autor,precio);
                   libros.add(libro);
               }
           } catch (SQLException ex) {
           	ex.printStackTrace();
           }
   		return libros;
    }
    //Conocer el número de libros que hay
    public int numeroLibros() {
        int conteo = 0;
        try {
            String sql = "SELECT COUNT(ISBN) FROM libros";
            PreparedStatement prepared = conexion.prepareStatement(sql);
            ResultSet resultados = prepared.executeQuery();
            if (resultados.next()) {
                conteo = resultados.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conteo;
    }    
}