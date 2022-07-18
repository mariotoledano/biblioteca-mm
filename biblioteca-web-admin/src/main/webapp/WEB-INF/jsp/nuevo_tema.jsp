<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>Nuevo Tema</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Icono -->
    <link rel="shortcut icon" href="laurel-favicon.ico">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    <!-- CSS   -->
    <link rel="stylesheet" href="" type="text/css" media="all" />
    <!--jQuery-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<!-- Body -->
<body class="my-4 mx-5">

<!-- TEMA   -->
    <h3 class="mt-4 mb-3">Tema</h3>
    <!-- Nuevo Tema -->
    <form id="form-tema">
        <div class="mb-3 mt-3 ms-4">
            <label for="nombre-tema-nuevo" class="form-label">Nuevo tema:</label>
            <input class="form-control" id="nombre-tema-nuevo" name="nombreTema" type="text" placeholder="Introduce el nombre de un nuevo tema">
        </div>
    <!--Botón submit-->
        <div>
            <button type="submit" class="btn btn-primary btn-bg mt-3 me-4">Registrar</button>
            <a href="index.html" class="btn btn-primary btn-bg mt-3">Volver</a>
        </div>
    </form>
    <!--Alerta Autor registrado en BBDD-->
    <div id="alerta-exito-tema" class="alert alert-success my-4 mx-3" role="alert" style="width: fit-content; display: none"> </div>

<!-- SUPERTEMA   -->
    <h3 class="mt-5 mb-3">Supertema</h3>
    <!-- Nuevo Supertema -->
    <form id="form-supertema">
        <div class="mb-3 mt-3 ms-4">
            <label for="nombre-supertema-nuevo" class="form-label">Nuevo supertema:</label>
            <input type="text" class="form-control" id="nombre-supertema-nuevo" name="nombreSupertema" type="text" placeholder="Introduce el nombre de un nuevo supertema">
        </div>
    <!--Botón submit-->
        <div>
            <button type="submit" class="btn btn-primary btn-bg mt-3 me-4">Registrar</button>
            <a href="index.html" class="btn btn-primary btn-bg mt-3">Volver</a>
        </div>
    </form>
    <!--Alerta Autor registrado en BBDD-->
    <div id="alerta-exito-supertema" class="alert alert-success my-4 mx-3" role="alert" style="width: fit-content; display: none"> </div>

<!-- SCRIPTS DE JS -->
<script>
      <!--Función para transformar a JSON-->
      function convertFormToJSON(form) {
        const array = $(form).serializeArray(); // Encodes the set of form elements as an array of names and values.
        const jsObject = {};
        $.each(array, function () {
          jsObject[this.name] = this.value || "";
        });
        const json = JSON.stringify(jsObject);
        return json;
      }
      <!--Al ejecutar el submit Tema Nuevo-->
      $("#form-tema").on("submit", function (e) {
        e.preventDefault();
        const form = $(e.target);
        const json = convertFormToJSON(form);
        console.log(json);
        <!--Envío Post con accept parar que la recepción del body sea correcta-->
        <!--Se muestra y oculata la alerta de éxito con el objeto guardado y el Status HTTP-->
        <!--Se resetea el formulario-->
        $.ajax({
            headers: {
            Accept: 'application/json',
            "Content-Type": 'application/json'
            },
            type: 'POST',
            url: "/registrarNuevoTema",
            data: json,
            dataType: 'json',
            success: function(data,status) {
                console.log("Guardado en BBDD: "+ JSON.stringify(data));
                console.log("Status "+ status);
                $("#alerta-exito-tema").html("El Tema: "+JSON.stringify(data)+" ha sido guardado en la BBDD correctamente.<br/>Status: "+status).show()+".";
                setTimeout(function(){
                    $("#alerta-exito-tema").hide();
                    },5000);
            document.getElementById("form-tema").reset();
            }
        });
      });

      <!--Al ejecutar el submit Supertema Nuevo-->
      $("#form-supertema").on("submit", function (e) {
        e.preventDefault();
        const form = $(e.target);
        const json = convertFormToJSON(form);
        console.log(json);
        <!--Envío Post con accept parar que la recepción del body sea correcta-->
        <!--Se muestra y oculata la alerta de éxito con el objeto guardado y el Status HTTP-->
        <!--Se resetea el formulario-->
        $.ajax({
            headers: {
            Accept: 'application/json',
            "Content-Type": 'application/json'
            },
            type: 'POST',
            url: "/registrarNuevoSupertema",
            data: json,
            dataType: 'json',
            success: function(data,status) {
                console.log("Guardado en BBDD: "+ JSON.stringify(data));
                console.log("Status "+ status);
                $("#alerta-exito-supertema").html("El Supertema: "+JSON.stringify(data)+" ha sido guardado en la BBDD correctamente.<br/>Status: "+status).show()+".";
                setTimeout(function(){
                    $("#alerta-exito-supertema").hide();
                    },5000);
            document.getElementById("form-supertema").reset();
            }
        });
      });
    </script>

</body>
</html>