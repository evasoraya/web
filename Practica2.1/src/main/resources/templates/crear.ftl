<!DOCTYPE html>
<html>
<head>
<title>Crear estudiante</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
    <h1>Agregar estudiante</h1>

    <form action="/crear" method="post">
            <div class="input-group">
                     <span class="input-group-addon" id="basic-addon3">Matricula</span>
                     <input type="number" class="form-control" name="matricula" aria-describedby="basic-addon3">
                   </div>
                   <br>
         <div class="input-group">
            <span class="input-group-addon" id="basic-addon3">Nombre</span>
           <input type="text" class="form-control" name="nombre" placeholder="nombre" aria-describedby="basic-addon3">
         </div>
         <br>
         <div class="input-group">
             <span class="input-group-addon" id="basic-addon3">Apellido</span>
           <input type="text" class="form-control" name = "apellido" placeholder="apellido" aria-describedby="basic-addon3">

         </div>
         <br>


         <div class="input-group">
                    <span class="input-group-addon" id="basic-addon3">Telefono</span>
                   <input type="text" class="form-control" name = "telefono" placeholder="telefono" aria-describedby="basic-addon3">

          </div>


        <button name="Enviar" type="submit">Enviar</button>
        <a href="/home" >
                   <button name="home"  >ir a HOME</button>
            </a>
    </form>
</body>
</html>