
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
    <h1>Editar estudiante</h1>

    <form action="/crear" method="post">
            <div class="input-group">
                     <span class="input-group-addon" id="basic-addon3">Matricula</span>
                    <input class="form-control" name="matricula" type="text" value = "${estudiante.getMatricula()}" placeholder="matricula" aria-describedby="basic-addon1" disabled>

                   </div>
                   <br>
         <div class="input-group">
          <span class="input-group-addon" id="basic-addon3">Nombre</span>
                    <input class="form-control" name="nombre" type="text" value = "${estudiante.getNombre()}" placeholder="nombre" aria-describedby="basic-addon1" disabled>

         </div>
         <br>
         <div class="input-group">
             <span class="input-group-addon" id="basic-addon3">Apelido</span>
           <input class="form-control" name="apellido" type="text" value = "${estudiante.getApellido()}" placeholder="apellido" aria-describedby="basic-addon1" disabled>

         </div>
         <br>


         <div class="input-group">
                     <span class="input-group-addon" id="basic-addon3">Telefono</span>
                   <input class="form-control"name="telefono" type="text" value = "${estudiante.getTelefono()}" placeholder="telefono" aria-describedby="basic-addon1"disabled>

          </div>

        <a href="/home">ir a HOME</a>
    </form>
</body>
</html>