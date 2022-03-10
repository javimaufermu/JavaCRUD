<%-- 
    Document   : dispositivo-form
    Created on : 5/03/2022, 5:25:03 p. m.
    Author     : Javier Fernandez
--%>
<jsp:include page="validar.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Lista de Dispositivos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark bg-primary">
                <div>
                    <a href="https://www.unicauca.edu.co" class="navbar-brand"> Aplicación Ejemplo Apliweb </a>
                </div>
                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/list" class="nav-link" style="color: white;">Lista Dispositivos</a></li>
                    <li><a href="<%=request.getContextPath()%>/RangoParametros" class="nav-link" style="color: white;">Rango de Parámetros</a></li>
                </ul>
                <div class="text-end" style="margin-left: auto;">
                    <a class="navbar-brand fw-bold" style="color: white;"><c:out value="${usuario.nombres}" /></a>
                    <a href="cerrarSesion" class="btn btn-outline-danger">
                        <i class='bi bi-power'></i>
                    </a>
                </div>
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
                                            <c:if test="${rango.id != idRango}">
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