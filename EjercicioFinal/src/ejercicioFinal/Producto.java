package ejercicioFinal;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Producto {
	public static ArrayList<Producto> listaProducto = new ArrayList<Producto>();
	private int codigoBarra;
	private String codigoCategoria;
	private String marca;
	private String descripcion;
	private String precio;
	private int stock;
	private CodigoInterno categoriaProducto;
	private int codigoInt;
	public CodigoInterno getCategoriaProducto() {
		return categoriaProducto;
	}
	
	public Producto(int codigoBarra, String codigoCategoria, String marca, String descripcion, String precio, int stock) {
		this.codigoBarra = codigoBarra;
		this.codigoCategoria = codigoCategoria;
		this.marca = marca;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	}
	
	public void setCategoriaProducto(CodigoInterno categoriaProducto) {
		this.categoriaProducto = categoriaProducto;
	}

	public int getCodigoInt() {
		return codigoInt;
	}
	public void setCodigoInt(int codigoInt) {
		this.codigoInt = codigoInt;
	}

	public int getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(int codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getCodigoCategoria() {
		return codigoCategoria;
	}
	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public abstract String verTicket(Producto s);
	

	
}
