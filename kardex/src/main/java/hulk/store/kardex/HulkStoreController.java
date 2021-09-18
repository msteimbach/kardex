package hulk.store.kardex;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hulk.store.kardex.model.Usuario;
import hulk.store.kardex.service.Md5Service;
import hulk.store.kardex.service.impl.ProductoService;
import hulk.store.kardex.service.impl.UsuarioService;

@Controller
public class HulkStoreController {
	private static final Log LOGGER = LogFactory.getLog(HulkStoreController.class);
	private static final String USUARIO_ADMIN = "admin";
	private static final String PASS_ADMIN = "admin";
			
	
	@RequestMapping(value="/login.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView model = new ModelAndView("login");
		model.addObject("error", "");
        return model;
	}
	
	@RequestMapping(value="/logon.htm")
	public ModelAndView handleRequestLogon(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String accion = request.getParameter("accion");
        if ("registro".equals(accion)) {
        	return new ModelAndView("registro");
        } else if ("aceptar".equals(accion)) {
        	String newUsuario = request.getParameter("newUsuario");
        	String newPass = request.getParameter("newPassword");
        	String newPass2 = request.getParameter("newPassword2");
        	if (newUsuario != null && !"".equals(newUsuario.trim()) && newPass != null && !"".equals(newPass.trim()) && newPass2 != null && !"".equals(newPass2.trim())) {
        		if (newPass.equals(newPass2)) {
	        		Usuario usr = new Usuario();
	        		usr.setAdministrador(false);
	        		usr.setUsuario(Md5Service.hash(newUsuario));
	        		usr.setPassword(Md5Service.hash(newPass));
	        		UsuarioService usuarioService = new UsuarioService();
	            	try {
	            		usuarioService.insertUser(usr);
	            	} catch (SQLException ex) {
	            		ModelAndView model = new ModelAndView("registro");
	            		model.addObject("error", "El usuario ya existe");
	                    return model;
	            	}
	        		ModelAndView model = new ModelAndView("registro");
	        		model.addObject("error", "El usuario se dio de alta correctamente!");
	                return model;
        		} else {
	        		ModelAndView model = new ModelAndView("registro");
	        		model.addObject("error", "Las contraseñas no coinciden");
	                return model;
        		}
        	} else {
        		ModelAndView model = new ModelAndView("registro");
        		model.addObject("error", "Debe completar todos los campos");
                return model;
        	}
        } else {
            String usuario = request.getParameter("usuario");
            String pass = request.getParameter("password");
        	if (usuario != null && pass != null) {
                if(USUARIO_ADMIN.equals(usuario) && PASS_ADMIN.equals(pass)){
                	ProductoService productosService = new ProductoService();
            		ModelAndView model = new ModelAndView("mainAdmin");
            		model.addObject("productos", productosService.getProducts());
                    return model;
                } else {
                	//Validamos contra la base
                	String usrHash = Md5Service.hash(usuario);
                	String passHash = Md5Service.hash(pass);
                	UsuarioService usuarioService = new UsuarioService();
                	Usuario usr = usuarioService.getLogin(usrHash, passHash); 
                	if (usr != null) {
                		ModelAndView model = new ModelAndView("mainUsuario");
                		model.addObject("nombreUsuario", usuario);
                        return model;
                	}
                }
        	}
        }
		
		ModelAndView model = new ModelAndView("login");
		model.addObject("error", "Usuario o contraseña incorrecta!");
        return model;
	}
	
	@RequestMapping(value="/registro.htm")
	public ModelAndView handleRequestRegistro(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
        String usuario = request.getParameter("usuario");
        String pass = request.getParameter("password");
        
        if(USUARIO_ADMIN.equals(usuario) && PASS_ADMIN.equals(pass)){
        	ProductoService productosService = new ProductoService();
    		ModelAndView model = new ModelAndView("mainAdmin");
    		model.addObject("productos", productosService.getProducts());
            return model;
        } else {
        	//Validamos contra la base
        	String usrHash = Md5Service.hash(usuario);
        	String passHash = Md5Service.hash(pass);
        	UsuarioService usuarioService = new UsuarioService();
        	Usuario usr = usuarioService.getLogin(usrHash, passHash); 
        	if (usr != null) {
        		ModelAndView model = new ModelAndView("mainUsuario");
        		model.addObject("nombreUsuario", usuario);
                return model;
        	}
            
        	request.getRequestDispatcher("index.jsp").forward(request, response);
        }
		
		
		
		ModelAndView model = new ModelAndView("login");
		model.addObject("error", "Usuario o contraseña incorrecta!");
        return model;
	}
}
