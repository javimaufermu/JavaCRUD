<%-- 
    Document   : list-dispositivos
    Created on : 5/03/2022, 5:19:36 p. m.
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
        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
            <div class="container">
                <h3 class="text-center">Lista de Dispositivos</h3>
                <!--a href="cerrarSesion">Cerrar sesión</a-->
                <hr>
                <div class="container text-left">
                    <table class="table">
                        <tbody>
                            <tr>
                                <th>
                                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success" style="margin-top: 12; margin-bottom: 12;">Nuevo Dispositivo</a>
                                </th>
                                <th>
                                    <% if (session.getAttribute("consulta") != null) {
                                            if (!session.getAttribute("consulta").equals("")) {%>
                                    <div class="alert alert-<%=session.getAttribute("mss_color")%> alert-dismissible fade show" role="alert">
                                        <span><%=session.getAttribute("consulta")%></span>
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" style="height: -webkit-fill-available;"></button>
                                    </div>                        

                                    <% }
                                            session.setAttribute("consulta", "");
                                        }%></th>
                            </tr>    
                        </tbody>
                    </table>
                </div>                
                <br>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Estado</th>
                            <th>Latitud</th>
                            <th>Longitud</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>

                        <!-- for (Todo todo: todos) { -->
                        <c:forEach var="dispositivo" items="${listDispositivos}">
                            <tr>
                                <td>
                                    <c:out value="${dispositivo.id}" />
                                </td>
                                <td>

                                    <c:out value="${dispositivo.nombre}" />
                                </td>
                                <td>

                                    <c:out value="${dispositivo.estado}" />
                                </td>
                                <td>

                                    <c:out value="${dispositivo.latitud}" />
                                </td>
                                <td>

                                    <c:out value="${dispositivo.longitud}" />
                                </td>

                                <td><a href="edit?id=<c:out value='${dispositivo.id}' />"class="btn btn-secondary">
                                        <i class="bi bi-pencil-square"></i></a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${dispositivo.id}' />"class="btn btn-danger">
                                        <i class="bi bi-trash3"></i></a></td>
                            </tr>
                        </c:forEach>
                        <!-- } -->
                    </tbody>
                </table>
            </div>
        </div>
        <!-- BOOTSTRAP SCRIPTS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>

    </body>
</html>