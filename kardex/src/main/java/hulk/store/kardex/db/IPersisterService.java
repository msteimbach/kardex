package hulk.store.kardex.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IPersisterService {
	public ResultSet executeSelect(String select) throws SQLException;
	public void excecuteUpdate(String update) throws SQLException;
	public void excecuteDelete(String delete) throws SQLException;
}
