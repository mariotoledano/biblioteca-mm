<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" >
    <title>Nuevo Autor</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
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
<form id="form-autor">
    <!-- AUTOR -->
    <h3 class="mb-3">Autor</h3>
    <div class="ms-4">
        <!-- Autor -->
        <div class="mb-3 mt-3">
            <label for="nombreAutor" class="form-label">Autor:</label>
            <input
                    type="text"
                    class="form-control"
                    id="nombreAutor"
                    placeholder="Introduce el nombre"
                    name="nombreAutor"
            />
        </div>
        <!-- Fecha autor -->
        <div class="mb-3 mt-3">
            <label for="fechaAutor" class="form-label">Fecha del autor:</label>
            <input
                    type="text"
                    class="form-control"
                    id="fechaAutor"
                    placeholder="Introduce la fecha"
                    name="fechaAutor"
            />
        </div>
        <!-- Descripción breve -->
        <div class="mb-3 mt-3">
            <label class="form-label" for="descripcionBreve"
            >Descripción breve:</label
            >
            <textarea
                    class="form-control"
                    rows="2"
                    id="descripcionBreve"
                    name="descripcionBreve"
                    placeholder="Una descripción breve del autor"
            ></textarea>
        </div>
        <!-- Descripción larga -->
        <div class="mb-3 mt-3">
            <label class="form-label" for="descripcionLarga"
            >Descripción larga:</label
            >
            <textarea
                    class="form-control"
                    rows="4"
                    id="descripcionLarga"
                    name="descripcionLarga"
                    placeholder="Una descripción extendida del autor"
            ></textarea>
        </div>
    </div>
    <div>
        <button type="submit" class="btn btn-primary btn-bg mt-3 me-4">
            Registrar
        </button>
        <a href="index.html" class="btn btn-primary btn-bg mt-3">Volver</a>
    </div>
</form>
<!--Alerta Autor registrado en BBDD-->
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
      $("#form-autor").on("submit", function (e) {
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
            url: "/registrarNuevoAutor",
            data: json,
            dataType: 'json',
            success: function(data,status) {
                console.log("Guardado en BBDD: "+ JSON.stringify(data));
                console.log("Status "+ status);
                $("#alertaExito").html("El Autor: "+JSON.stringify(data)+" ha sido guardado en la BBDD correctamente.<br/>Status: "+status).show()+".";
                setTimeout(function(){
                    $("#alertaExito").hide();
                    },20000);
            document.getElementById("form-autor").reset();
            }
        });
      });
    </script>
</body>
</html>