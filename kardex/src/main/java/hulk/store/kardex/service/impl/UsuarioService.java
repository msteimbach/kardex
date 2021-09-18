package hulk.store.kardex.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import hulk.store.kardex.db.impl.PersisterService;
import hulk.store.kardex.model.Usuario;

public class UsuarioService {
	private static final String SQL_SELECT_USER = "SELECT usuario, password, administrador FROM Ent_Usuario WHERE usuario = '{1}'";
	private static final String SQL_SELECT_USER_LOGIN = "SELECT usuario, password, administrador FROM Ent_Usuario WHERE usuario = '{1}' AND password = '{2}'";
	private static final String SQL_UPDATE_USER = "UPDATE Ent_Usuario SET password='{1}', administrador={2} WHERE usuario = '{3}'";
	private static final String SQL_INSERT_USER = "INSERT INTO Ent_Usuario (usuario, password, administrador) VALUES ('{1}', '{2}', {3})";
	
	public Usuario findByUsername(String username) throws SQLException {
		Usuario user = null;
		String sql = setParameterValue(SQL_SELECT_USER, username, 1);
		ResultSet result = PersisterService.getInstance().executeSelect(sql);
		if (result.next()) {
			user = new Usuario();
			user.setUsuario(result.getString(1));
			user.setPassword(result.getString(2));
			user.setAdministrador(result.getBoolean(3));
		}
		return user;
	}
	
	public Usuario getLogin(String username, String password) throws SQLException {
		Usuario user = null;
		String sql = setParameterValue(SQL_SELECT_USER_LOGIN, username, 1);
		sql = setParameterValue(sql, password, 2);
		
		ResultSet result = PersisterService.getInstance().executeSelect(sql);
		if (result.next()) {
			user = new Usuario();
			user.setUsuario(result.getString(1));
			user.setPassword(result.getString(2));
			user.setAdministrador(result.getBoolean(3));
		}
		return user;
	}
	
	public Usuario insertUser(Usuario user) throws SQLException {
		String sql = setParameterValue(SQL_INSERT_USER, user.getUsuario(), 1);
		sql = setParameterValue(sql, user.getPassword(), 2);
		sql = setParameterValue(sql, user.isAdministrador()?"1":"0", 3);
		PersisterService.getInstance().excecuteUpdate(sql);
		return user;
	}
	
	public void updateUser(Usuario user) throws SQLException {
		String sql = setParameterValue(SQL_UPDATE_USER, user.getPassword(), 1);
		sql = setParameterValue(sql, user.isAdministrador()?"1":"0", 2);
		sql = setParameterValue(sql, user.getUsuario(), 3);
		PersisterService.getInstance().excecuteUpdate(sql);
	}
	
	private String setParameterValue (String sql, String val, int param) {
		return sql.replace("{"+param+"}", val);
	}

}
