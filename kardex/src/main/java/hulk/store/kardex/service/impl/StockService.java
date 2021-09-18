package hulk.store.kardex.service.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import hulk.store.kardex.db.impl.PersisterService;
import hulk.store.kardex.model.Producto;
import hulk.store.kardex.model.ProductoVenta;
import hulk.store.kardex.model.Venta;
import hulk.store.kardex.service.FormatterService;
import hulk.store.kardex.service.exception.StockException;

public class StockService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6330387091231448548L;
	private static final String SQL_INSERT_VENTA = "INSERT INTO Obj_Venta (idVenta, usuario, fecha) VALUES ({1}, {2}, {3})";
	private static final String SQL_INSERT_VENTA_DETALLE = "INSERT INTO Obj_Venta_Detalle (idVenta, idProducto, cantidad, precio) VALUES ({1}, {2}, {3}, {4})";
	private static final String SQL_SELECT_MAX_ID_VENTA = "SELECT max(idVenta) FROM Obj_Venta";
	
	private ProductoService productService = new ProductoService();
	
	public boolean hasStock(long idProduct, long cant) throws SQLException {
		Producto product = productService.findById(idProduct);
		if (product != null && product.getStock() >= cant)
			return true;
		return false;
	}

	public synchronized boolean addStock(long idProduct, long cant) throws StockException, SQLException {
		if (cant == 0l)
			return false;
		Producto product = productService.findById(idProduct);
		if (product != null) {
			if (cant > 0l) {
				product.setStock(product.getStock() + cant);
			} else {
				if (product.getStock() >= cant)
					product.setStock(product.getStock() - cant);
				else
					throw new StockException ("Stock insuficiente");
			}
			productService.updateProduct(product);
			return true;
		}
			
		return false;
	}
	
	public void insertVenta(Venta sale) throws StockException, SQLException {
		long id = getMaxId() + 1;
		sale.setIdVenta(id);
		String sql = setParameterValue(SQL_INSERT_VENTA, Long.toString(id), 1);
		sql = setParameterValue(sql, sale.getUsuario(), 2);
		sql = setParameterValue(sql, sale.getFecha(), 3);
		PersisterService.getInstance().excecuteUpdate(sql);
		
		//Insertar el detalle de la venta
		for (ProductoVenta p : sale.getProductos()) {
			p.setIdVenta(id);
			sql = setParameterValue(SQL_INSERT_VENTA_DETALLE, Long.toString(id), 1);
			sql = setParameterValue(sql, Long.toString(p.getIdProducto()), 2);
			sql = setParameterValue(sql, Long.toString(p.getCantidad()), 3);
			sql = setParameterValue(sql, FormatterService.doubleToString(p.getImporte(), 2), 4);
			PersisterService.getInstance().excecuteUpdate(sql);
		}
	}
	
	private long getMaxId() throws SQLException {
		ResultSet result = PersisterService.getInstance().executeSelect(SQL_SELECT_MAX_ID_VENTA);
		if (result.next()) {
			return result.getLong(1);
		}
		return 0l;
	}
	
	private String setParameterValue (String sql, String val, int param) {
		return sql.replace("{"+param+"}", val);
	}

}
