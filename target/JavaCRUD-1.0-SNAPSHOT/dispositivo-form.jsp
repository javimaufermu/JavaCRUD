<%-- 
    Document   : dispositivo-form
    Created on : 5/03/2022, 5:25:03 p. m.
    Author     : Javier Fernandez
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Formulario Dispositivo</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                <div>
                    <a href="https://www.unicauca.edu.co" class="navbar-brand"> Aplicación Ejemplo Apliweb </a>
                </div>
                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Lista Dispositivos</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <c:if test="${dispositivo != null}">
                        <form action="update" method="post">
                        </c:if>

                        <c:if test="${dispositivo == null}">
                            <form action="insert" method="post">
                            </c:if>
                            <caption>
                                <h2>
                                    <c:if test="${dispositivo != null}">
                                        Editar Dispositivo
                                    </c:if>

                                    <c:if test="${dispositivo == null}">

                                        Nuevo Dispositivo
                                    </c:if>
                                </h2>
                            </caption>

                            <c:if test="${dispositivo != null}">

                                <input type="hidden" name="id" value="<c:out value='${dispositivo.id}' />" >
                            </c:if>
                            <fieldset class="form-group">
                                <label>Nombre</label> <input type="text" value="<c:out value='${dispositivo.nombre}' />"
                                                             class="form-control" name="nombre" required="required">
                            </fieldset>
                            <div class="input-group mb-3">
                                <fieldset class="form-group">
                                    <label>Estado <c:out value='${dispositivo.estado}' /></label>
                                    <c:if test="${dispositivo == null}">
                                        <select name="estado" class="form-control" required>
                                            <option selected disabled value="">Seleccione ...</option>                                
                                            <option value="Activo">Activo</option>;
                                            <option value="Inactivo">Inactivo</option>;                                
                                        </select>
                                    </c:if>
                                    <c:if test="${dispositivo.estado == 'Activo'}">                                    
                                        <select name="estado" class="form-control" required>
                                            <option selected disabled value="">Seleccione ...</option>                                
                                            <option value="Activo" selected>Activo</option>;
                                            <option value="Inactivo">Inactivo</option>;                                
                                        </select>
                                    </c:if>
                                    <c:if test="${dispositivo.estado == 'Inactivo'}">
                                        <select name="estado" class="form-control" required>
                                            <option selected disabled value="">Seleccione ...</option>                                
                                            <option value="Activo">Activo</option>;
                                            <option value="Inactivo" selected>Inactivo</option>;                                
                                        </select>
                                    </c:if>
                                </fieldset>
                            </div>
                            <fieldset class="form-group">
                                <label>Latitud</label> <input type="number" step="0.00001" value="<c:out value='${dispositivo.latitud}'
                                       />" class="form-control" name="latitud" required="required">
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Longitud</label> <input type="number" step="0.00001" value="<c:out value='${dispositivo.longitud}'
                                       />" class="form-control" name="longitud" required="required">
                            </fieldset>
                            <div class="input-group mb-3">
                                <fieldset class="form-group">
                                    <label>Rango de parámetros para alertas</label>                                    
                                    <select name="rango" class="form-control" required>
                                        <c:forEach var="rango" items="${listRangos}"> 
                                            <c:if test="${rango.id == idRango}">
                                                <option value="${rango.id}" selected><c:out value="${rango.id}" /></option>                                            
                                            </c:if>
                                            <c:if test="${rango.id != idrango}">
                                                <option value="${rango.id}"><c:out value="${rango.id}" /></option>                                            
                                            </c:if>    
                                        </c:forEach>
                                    </select>
                                </fieldset>
                            </div>
                            <button type="submit" class="btn btn-success">Guardar</button>
                        </form>
                </div>
            </div>
        </div>
    </body>
</html>