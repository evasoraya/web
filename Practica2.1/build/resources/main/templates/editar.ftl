
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

    <form action="/editar" method="post">
            <div class="input-group">
                     <span class="input-group-addon" id="basic-addon3">Matricula</span>
                     <input class="form-control" type="text" value = "${estudiante.getMatricula()}" aria-describedby="basic-addon1" disabled/>

                   </div>
                   <input class="form-control hidden" name="matricula" type="text" value = "${estudiante.getMatricula()}" aria-describedby="basic-addon1"  /><br/>
                   <br>
         <div class="input-group">
          <span class="input-group-addon" id="basic-addon3">Nombre</span>
                    <input class="form-control" name="nombre" type="text" value = "${estudiante.getNombre()}" placeholder="nombre" aria-describedby="basic-addon1">

         </div>
         <br>
         <div class="input-group">
             <span class="input-group-addon" id="basic-addon3">Apelido</span>
           <input class="form-control" name="apellido" type="text" value = "${estudiante.getApellido()}" placeholder="apellido" aria-describedby="basic-addon1">

         </div>
         <br>


         <div class="input-group">
                     <span class="input-group-addon" id="basic-addon3">Telefono</span>
                   <input class="form-control"name="telefono" type="text" value = "${estudiante.getTelefono()}" placeholder="telefono" aria-describedby="basic-addon1">

          </div>


        <button name="Enviar" type="submit">Enviar</button>
        <a href="/home" >
                   <button name="home"  >ir a HOME</button>
            </a>
    </form>
</body>
</html>