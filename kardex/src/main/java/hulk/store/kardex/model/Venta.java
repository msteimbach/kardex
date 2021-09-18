package hulk.store.kardex.model;

import java.io.Serializable;
import java.util.List;

public class Venta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2195731734094682210L;

	private long idVenta;
	private String usuario;
	private String fecha;
	private List<ProductoVenta> productos;
	
	public long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(long idVenta) {
		this.idVenta = idVenta;
	}

	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public List<ProductoVenta> getProductos() {
		return productos;
	}
	
	public void setProductos(List<ProductoVenta> productos) {
		this.productos = productos;
	}

}
