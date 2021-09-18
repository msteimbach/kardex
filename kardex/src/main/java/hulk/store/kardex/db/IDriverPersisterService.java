package hulk.store.kardex.db;

import java.sql.Connection;

public interface IDriverPersisterService {
	public Connection getConnection();

}
