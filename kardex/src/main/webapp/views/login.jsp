<%@ include file="/views/header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Hulk Store - Kardex</title>
    </head>
    <body>
        <div style="width: 50%">
            <div class="card-body">
                <form id="form" name="form" action="logon.htm" method="POST">
                    <div>
                        <h2>Hulk Store - Kardex Login</h2>
                    </div>
					<c:out value="${error}" />
                    <div>
                        <label>Usuario:</label>
                        <input type="text" name="usuario" value="admin">
                        <label>Contrase&ntilde;a:</label>
                        <input type="password" name="password" value="admin">
                    </div>
                    <br />&nbsp;
					<button id="ingresar" type="submit" name="accion" value="ingresar" >Ingresar</button>						
					<button id="registro" type="submit" name="accion" value="registro" >Registro</button>						
                </form>
            </div>
        </div>
    </body>
</html>