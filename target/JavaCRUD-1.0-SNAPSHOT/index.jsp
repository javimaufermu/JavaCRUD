<%-- 
    Document   : index
    Created on : 8/03/2022, 1:25:29 a. m.
    Author     : Javier Fernandez
--%>


<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Inicio de Sesión</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            .login-form {
                width: 340px;
                margin: 50px auto;
                font-size: 15px;
            }
            .login-form form {
                margin-bottom: 15px;
                background: #f7f7f7;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }
            .login-form h2 {
                margin: 0 0 15px;
            }
            .form-control, .btn {
                min-height: 38px;
                border-radius: 2px;
            }
            .btn {        
                font-size: 15px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="login-form">
            <form action="usuario" method="post">
                <input type="hidden" name="tipo" value="iniciarSesion" />
                <h2 class="text-center">Iniciar Sesión</h2>       
                <div class="form-group">
                    <!--input type="text" class="input" name="nombre" required-->
                    <input type="text" class="form-control" name="nombre" placeholder="Nombre de Usuario" required="required">
                </div>
                <div class="form-group">
                    <!--input type="password" class="input" name="clave" required-->
                    <input type="password" class="form-control" name="clave" placeholder="Contraseña" required="required">
                </div>
                <div class="form-group">
                    <!--input type="submit" class="btn" value="Iniciar Sesión"-->
                    <button type="submit" class="btn btn-primary btn-block" value="Iniciar Sesión">Iniciar Sesión</button>
                </div>
                <!--div class="clearfix">
                    <label class="float-left form-check-label"><input type="checkbox"> Remember me</label>
                    <a href="#" class="float-right">Forgot Password?</a>
                </div-->        
            </form>            
        </div>
    </body>
</html>
