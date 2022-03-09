<%-- 
    Document   : rangos-form.jsp
    Created on : 8/03/2022, 7:21:47 p. m.
    Author     : natal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Formulario Rangos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
  </head>
  <body>
    <header>
      <nav class="navbar navbar-dark bg-secondary">
        <div class="container">
          <a class="navbar-brand" href="<%=request.getContextPath()%>/list">SISTEMA PROPERSEED</a>
          <a class="navbar-brand" href="<%=request.getContextPath()%>/list">Rango de parametros</a>
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
    <div class="container col-md-5">
      <div class="card">
        <div class="card-body">
          <c:if test="${rango != null}">
            <form action="updateRango" method="post">
            </c:if>
            <c:if test="${rango == null}">
              <form action="insertRango" method="post">
              </c:if>
              <caption>
                <h2 class="text-center fw-bold">
                  <c:if test="${rango != null}">
                    Editar Rango
                  </c:if>
                  <c:if test="${rango == null}">
                    Nuevo Rango
                  </c:if>
                </h2>
              </caption>
              <c:if test="${rango != null}">
                <input type="hidden" name="id" value="<c:out value='${rango.id}' />" />
              </c:if>
              <label class="text-dark fw-bold">Temperatura</label><br>
              <fielset class="input-group mb-3">
                <span class="input-group-text">Mín</span>
                <input name="Temperatura_Min" type="number" value="<c:out value='${rango.temperaturamin}' />" class="form-control" required>
                <span class="input-group-text">Máx</span>
                <input name="Temperatura_Max" type="number" value="<c:out value='${rango.temperaturamax}' />" class="form-control" required>
              </fielset>
              <label class="text-dark fw-bold">Humedad</label><br>
              <div class="input-group mb-3">
                <span class="input-group-text">Mín</span>
                <input name="Humedad_Min" type="number" value="<c:out value='${rango.humedadmin}' />" class="form-control" required>
                <span class="input-group-text">Máx</span>
                <input name="Humedad_Max" type="number" value="<c:out value='${rango.humedadmax}' />" class="form-control" required>
              </div>
              <label class="text-dark fw-bold">Intesidad de Luz</label><br>
              <div class="input-group mb-3">
                <span class="input-group-text">Mín</span>
                <input name="Intensidad_Luz_Min" type="number" value="<c:out value='${rango.intensidadluzmin}' />" class="form-control" required>
                <span class="input-group-text">Máx</span>
                <input name="Intensidad_Luz_Max" type="number" value="<c:out value='${rango.intensidadluzmax}' />" class="form-control" required>
              </div>
              <label class="text-dark fw-bold">Humedad Ambiente</label><br>
              <div class="input-group mb-3">
                <span class="input-group-text">Mín</span>
                <input name="Humedad_Ambiente_Min" type="number" value="<c:out value='${rango.humedadambientemin}' />" class="form-control" required>
                <span class="input-group-text">Máx</span>
                <input name="Humedad_Ambiente_Max" type="number" value="<c:out value='${rango.humedadambientemax}' />" class="form-control" required>
              </div><br>
              <button type="submit" class="btn btn-success">
                <c:if test="${rango != null}">
                    Actualizar Rango
                  </c:if>
                  <c:if test="${rango == null}">
                    Agregar Rango
                  </c:if>
              </button>
            </form>
        </div>
      </div>
    </div>
  </body>
</html>
