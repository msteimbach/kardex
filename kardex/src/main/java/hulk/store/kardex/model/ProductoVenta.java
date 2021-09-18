package hulk.store.kardex.model;

public class ProductoVenta  {
	private long idProducto;
	private long idVenta;
	private long cantidad;
	private double importe;
	
	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	
	public long getIdVenta() {
		return idVenta;
	}
	
	public void setIdVenta(long idVenta) {
		this.idVenta = idVenta;
	}
	
	public long getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getImporte() {
		return importe;
	}
	
	public void setImporte(double importe) {
		this.importe = importe;
	}

}
