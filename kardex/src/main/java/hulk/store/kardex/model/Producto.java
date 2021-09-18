package hulk.store.kardex.model;

import java.io.Serializable;

public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4871250098420152676L;

	private long idProducto;
	private String descripcion;
	private long stock;
	private double precio;
	
	public Producto(long idProducto, String descripcion, long stock, double precio) {
		this.idProducto = idProducto;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
	}
	
	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public long getStock() {
		return stock;
	}
	
	public void setStock(long stock) {
		this.stock = stock;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
