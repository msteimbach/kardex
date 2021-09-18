package hulk.store.kardex.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hulk.store.kardex.db.impl.PersisterService;
import hulk.store.kardex.model.Producto;
import hulk.store.kardex.service.FormatterService;

public class ProductoService {
	private static final String SQL_SELECT_PRODUCT = "SELECT idProducto, descripcion, stock, precio FROM Ent_Producto";
	private static final String SQL_SELECT_PRODUCT_FROM_ID = SQL_SELECT_PRODUCT.concat(" WHERE idProducto = {1}");
	private static final String SQL_UPDATE_PRODUCT = "UPDATE Ent_producto SET descripcion='{1}', stock={2}, precio={3} WHERE idProducto = {4}";
	private static final String SQL_SELECT_MAX_ID_PRODUCT = "SELECT max(idProducto) FROM Ent_Producto";
	private static final String SQL_INSERT_PRODUCT = "INSERT INTO Ent_Producto (idProducto, descripcion, stock, precio) VALUES ({1}, '{2}', {3}, {4})";
	
	public Producto findById(long id) throws SQLException {
		Producto product = null;
		String sql = SQL_SELECT_PRODUCT_FROM_ID + id;
		ResultSet result = PersisterService.getInstance().executeSelect(sql);
		if (result.next()) {
			product = new Producto(result.getLong(1), result.getString(2), result.getLong(3), result.getDouble(4));
		}
		return product;
	}
	
	public List<Producto> getProducts() throws SQLException {
		List<Producto> products = new ArrayList<Producto>();
		String sql = SQL_SELECT_PRODUCT;
		ResultSet result = PersisterService.getInstance().executeSelect(sql);
		while (result.next()) {
			products.add(new Producto(result.getLong(1), result.getString(2), result.getLong(3), result.getDouble(4)));
		}
		return products;
	}
	
	public Producto insertProduct(Producto product) throws SQLException {
		long id = getMaxId() + 1;
		product.setIdProducto(id);
		String sql = setParameterValue(SQL_INSERT_PRODUCT, Long.toString(id), 1);
		sql = setParameterValue(sql, product.getDescripcion(), 2);
		sql = setParameterValue(sql, Long.toString(product.getStock()), 3);
		sql = setParameterValue(sql, FormatterService.doubleToString(product.getPrecio(), 2), 4);
		PersisterService.getInstance().excecuteUpdate(sql);
		return product;
	}
	
	public Producto insertProduct(String descripcion, long stock, double precio) throws SQLException {
		Producto product = new Producto(-1, descripcion, stock, precio);
		return insertProduct(product);
	}
	
	public void updateProduct(Producto product) throws SQLException {
		String sql = setParameterValue(SQL_UPDATE_PRODUCT, product.getDescripcion(), 1);
		sql = setParameterValue(sql, Long.toString(product.getStock()), 2);
		sql = setParameterValue(sql, FormatterService.doubleToString(product.getPrecio(), 2), 3);
		PersisterService.getInstance().excecuteUpdate(sql);
	}
	
	private long getMaxId() throws SQLException {
		ResultSet result = PersisterService.getInstance().executeSelect(SQL_SELECT_MAX_ID_PRODUCT);
		if (result.next()) {
			return result.getLong(1);
		}
		return 0l;
	}
	
	private String setParameterValue (String sql, String val, int param) {
		return sql.replace("{"+param+"}", val);
	}
}
