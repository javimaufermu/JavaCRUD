<%-- 
    Document   : list-rangos
    Created on : 8/03/2022, 6:56:22 p. m.
    Author     : natal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Rangos de Parámetros</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
  </head>
  <body>
    <header>
      <nav class="navbar navbar-dark bg-secondary">
        <div class="container">
          <a class="navbar-brand" href="<%=request.getContextPath()%>/list">SISTEMA PROPERSEED</a>
          <a class="navbar-brand" href="<%=request.getContextPath()%>/rangoParametros">Rango de parametros</a>
          <a class="navbar-brand" href="<%=request.getContextPath()%>/list">Parámetros</a>
          <a class="navbar-brand" href="<%=request.getContextPath()%>/list">Alertas</a>
          <div class="text-end">
            <a class="navbar-brand fw-bold text-dark">User</a>
            <a href="Autenticacion/cerrar_sesion.php" class="btn btn-outline-danger">
              <i class='bi bi-power'></i>
            </a>
          </div>
        </div>
      </nav>
    </header>
    <br>
    <h3 class="text-center">Rango de Parámetros</h3>
    <hr>
    <div class="container text-end">
      <a href="<%=request.getContextPath()%>/newRango" class="btn btn-success">Nuevo Rango</a>
    </div>
    <br>
    <div class="row">
      <table class="table table-bordered border-warning text-center table-striped">
        <thead class="table-primary">
          <tr>
            <th colspan="2">Temperatura</th>
            <th colspan="2">Humedad</th>
            <th colspan="2">Intensidad de Luz</th>
            <th colspan="2">Humedad Ambiente</th>
            <th rowspan="2">Opciones</th>
          </tr>
          <tr>
            <th>Mínima</th>
            <th>Máxima</th>
            <th>Mínima</th>
            <th>Máxima</th>
            <th>Mínima</th>
            <th>Máxima</th>
            <th>Mínima</th>
            <th>Máxima</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="rango" items="${listRangos}">
            <tr>
              <td><c:out value="${rango.temperaturamin}" /></td>
              <td><c:out value="${rango.temperaturamax}" /></td>
              <td><c:out value="${rango.humedadmin}" /></td>
              <td><c:out value="${rango.humedadmax}" /></td>
              <td><c:out value="${rango.intensidadluzmin}" /></td>
              <td><c:out value="${rango.intensidadluzmax}" /></td>
              <td><c:out value="${rango.humedadambientemin}" /></td>
              <td><c:out value="${rango.humedadambientemax}" /></td>
              <td>
                <a href="editRango?id=<c:out value='${rango.id}' />" class="btn btn-secondary">
                  <i class="bi bi-pencil-square"></i>
                </a>
                <a href="deleteRango?id=<c:out value='${rango.id}' />" class="btn btn-danger">
                  <i class="bi bi-trash3"></i>
                </a>
              </td>
            </tr>
          </c:forEach>
          <!-- } -->
        </tbody>
      </table>
    </div>
  </body>
</html>
