<%-- 
    Document   : validar
    Created on : 8/03/2022, 1:17:50 a. m.
    Author     : Javier Fernandez
--%>

<% 
	if (session.getAttribute("usuario") == null) {
		request.setAttribute("mensaje", "Debe autenticarse para ingresar al sistema");
		pageContext.forward("index.jsp");
	}
%>
