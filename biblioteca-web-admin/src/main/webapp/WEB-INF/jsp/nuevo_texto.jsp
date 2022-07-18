<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>Nuevo Texto</title>
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
<form id="form-texto">
    <!-- TEXTO   -->
    <h3 class="mb-3">Texto</h3>
    <div class="ms-4">
        <!-- Texto: -->
        <div class="mb-3 mt-3">
            <label class="form-label" for="textoString">Nuevo texto:</label>
            <textarea class="form-control" rows="5" id="textoString" name="textoString" placeholder="Introduce el texto"></textarea>
        </div>
        <!-- Longitud: -->
        <div class="mb-3">
            <label class="form-check-label me-3" for="opciones-longitud">Longitud:</label>
            <div id="opciones-longitud" class="d-inline-flex">
                <div class="form-check me-3">
                    <input type="radio" class="form-check-input" id="breve" name="longitud" value="breve" checked>
                    <label class="form-check-label" for="breve">Breve</label>
                </div>
                <div class="form-check">
                    <input type="radio" class="form-check-input" id="largo" name="longitud" value="largo">
                    <label class="form-check-label" for="largo">Largo</label>
                </div>
            </div>
        </div>
    </div>
    <!-- AUTOR -->
    <h3 class="mb-3 mt-5">Autor</h3>
    <!-- Autor previamente registrado-->
    <div class="ms-4 mb-3 mt-3">
        <label for="autor" class="form-label">Autor:</label>
        <select class="form-select" placeholder="Introduce nombre de un autor previamente registrado" name="idAutor" id="autor">
            <c:forEach items="${listaAutores}" var="autor">
                <option value="${autor.idAutor}">${autor.nombreAutor}</option>
            </c:forEach>
        </select>
    </div>
    <!-- TEMA   -->
    <h3 class="mb-3 mt-5">Tema</h3>
    <div class="ms-4">
        <div class="mb-3 mt-3">
            <label for="tema" class="form-label">Tema:</label>
            <select class="form-select" placeholder="Introduce nombre de un tema previamente registrado" name="idTema" id="tema">
                <c:forEach items="${listaTemas}" var="tema">
                    <option value="${tema.idTema}">${tema.nombreTema}</option>
                </c:forEach>
             </select>
        </div>
    </div>
    <div>
        <button type="submit" class="btn btn-primary btn-bg mt-3 me-4">Registrar</button>
        <a href="index.html" class="btn btn-primary btn-bg mt-3">Volver</a>
    </div>
</form>
<!--Alerta Texto registrado en BBDD-->
<div id="alertaExito" class="alert alert-success my-5 mx-5" role="alert" style="width: fit-content; display: none"> </div>

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
<!--Al ejecutar el submit-->
      $("#form-texto").on("submit", function (e) {
        e.preventDefault();
        const form = $(e.target);
        const json = convertFormToJSON(form);
        console.log("Después de CONVERT: "+json);
<!--Envío Post con accept parar que la recepción del body sea correcta-->
<!--Se muestra y oculata la alerta de éxito con el objeto guardado y el Status HTTP-->
<!--Se resetea el formulario-->
        $.ajax({
            headers: {
            Accept: 'application/json',
            "Content-Type": 'application/json'
            },
            type: 'POST',
            url: "/registrarNuevoTexto",
            data: json,
            dataType: 'json',
            success: function(data,status) {
                console.log("Guardado en BBDD: "+ JSON.stringify(data));
                console.log("Status "+ status);
                $("#alertaExito").html("El Texto: "+JSON.stringify(data)+" ha sido guardado en la BBDD correctamente.<br/>Status: "+status).show()+".";
                setTimeout(function(){
                    $("#alertaExito").hide();
                    },5000);
            document.getElementById("form-texto").reset();
            }
        });
      });
    </script>
</body>
</html>