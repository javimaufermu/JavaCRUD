<%-- 
    Document   : list-dispositivos
    Created on : 5/03/2022, 5:19:36 p. m.
    Author     : Javier Fernandez
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Lista de Dispositivos</title>
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
        <div class="row">
            <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
            <div class="container">
                <h3 class="text-center">Lista de Dispositivos</h3>
                <hr>
                <div class="container text-left">
                    <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Nuevo Dispositivo</a>
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

                                <td><a href="edit?id=<c:out value='${dispositivo.id}' />">Editar</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${dispositivo.id}' />">Eliminar</a></td>
                            </tr>
                        </c:forEach>
                        <!-- } -->
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>