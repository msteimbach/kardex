<%@ include file="/views/header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Hulk Store - Kardex</title>
    </head>
    
    <body>
        <h1>
			Login Hulk Store - Kardex - <b>Admin</b>
        </h1>
        <p>
			<c:out value="${error}" />
        </p>
        <h3>Productos</h3>
        <c:forEach items="${productos}" var="producto">
            <c:out value="${producto.descripcion}" />
            <i>$<c:out value="${producto.precio}" /></i>
            <i>$<c:out value="${producto.stock}" /></i>
            <input type="text" id="${producto.idProducto}">
            <a href="<c:url value="agregarStock.htm"/>">AgregarStock</a>
            <br>
            <br>
        </c:forEach>
        <br>
        <a href="<c:url value="nuevoProducto.htm"/>">Nuevo producto</a>
        <br>
    </body>		
</html>