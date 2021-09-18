package hulk.store.kardex.db.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hulk.store.kardex.db.IDriverPersisterService;
import hulk.store.kardex.db.IPersisterService;

public class PersisterService implements IPersisterService {

	private static IDriverPersisterService db = new MemoryDbDriverPersisterService();
	private static PersisterService instance = null;
	
	private PersisterService () {
		
	}
	
	public static IPersisterService getInstance() {
		if (instance == null)
			instance = new PersisterService();
		return instance;
	}
	
	@Override
	public ResultSet executeSelect(String select) throws SQLException {
    	PreparedStatement statement=db.getConnection().prepareStatement(select);
    	return statement.executeQuery();
	}

	@Override
	public void excecuteUpdate(String update) throws SQLException {
		db.getConnection().prepareStatement(update).executeUpdate();
	}

	@Override
	public void excecuteDelete(String delete) throws SQLException {
		db.getConnection().prepareStatement(delete).executeUpdate();
	}

}
