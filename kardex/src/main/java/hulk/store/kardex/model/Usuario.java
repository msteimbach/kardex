package hulk.store.kardex.model;

import java.io.Serializable;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2061692363267846238L;

	private String usuario;
	private String password;
	private boolean administrador;
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAdministrador() {
		return administrador;
	}
	
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	
}
