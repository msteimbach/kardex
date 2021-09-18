package hulk.store.kardex.db.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import hulk.store.kardex.db.IDriverPersisterService;

public class MemoryDbDriverPersisterService implements IDriverPersisterService {

	private static final Log LOGGER = LogFactory.getLog(MemoryDbDriverPersisterService.class);
    private static final String dbUrl="jdbc:derby:memory:hulkStoreDb;create=true";
    private static final String CREATE_TABLE_PRODUCTO = "CREATE TABLE Ent_Producto(idProducto numeric(18) NOT NULL, descripcion VARCHAR(255), precio numeric(18, 0) NOT NULL default 0, stock numeric(18), PRIMARY KEY (idProducto))";
    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE Ent_Usuario(usuario varchar(255) NOT NULL, password VARCHAR(255) NOT NULL, administrador NUMERIC(1) NOT NULL DEFAULT 0, PRIMARY KEY (usuario))";
    
    private static final String INSERT_PRODUCTO_1 = "INSERT INTO Ent_Producto (idProducto, descripcion, stock, precio) VALUES (1, 'Libro de comics 1', 55, 33.5)";
    private static final String INSERT_PRODUCTO_2 = "INSERT INTO Ent_Producto (idProducto, descripcion, stock, precio) VALUES (2, 'Libro de comics 2', 88, 101.5)";
    private static final String INSERT_PRODUCTO_3 = "INSERT INTO Ent_Producto (idProducto, descripcion, stock, precio) VALUES (3, 'Libro de comics 3', 5, 39.5)";
    
    private static Connection connection = null;
    
    @Override
	public synchronized Connection getConnection() {
    	if (connection != null)
    		return connection;
    	
        try {
        	//DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(dbUrl);
            initDb(connection);
        } catch (Exception e) {
        	LOGGER.error("Error al tratar de obtener la conexion a base de datos", e);
        }
        return connection;
	}

    private void initDb(Connection connection) throws SQLException {
    	//TODO
    	//Creamos las tablas
    	executeQuery(connection, CREATE_TABLE_PRODUCTO);
    	executeQuery(connection, CREATE_TABLE_USUARIO);
    	
    	
    	//Insertamos valores por defecto
    	executeQuery(connection, INSERT_PRODUCTO_1);
    	executeQuery(connection, INSERT_PRODUCTO_2);
    	executeQuery(connection, INSERT_PRODUCTO_3);
    	
    }
    
    private void executeQuery(Connection conn, String query) throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.execute(query);
    }
}
