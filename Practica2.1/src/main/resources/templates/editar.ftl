<!DOCTYPE html>
<html>
<head>
<title>Crear estudiante</title>
</head>
<body>
    <h1>Agregar estudiante</h1>
    <form action="/editar" method="post">
         Matricula: <input  type="text" value = "${estudiante.getMatricula()}" disabled/><br/>
          <input name="matricula" type="text" value = "${estudiante.getMatricula()}" hidden/><br/>
         Nombre: <input name="nombre" type="text" value = "${estudiante.getNombre()}"  /><br/>
         Apellido: <input name="apellido" type="text" value = "${estudiante.getApellido()}"/><br/>
         Telefono: <input name="telefono" type="text" value = "${estudiante.getTelefono()}"/><br/>
        <button name="Enviar" type="submit">Enviar</button>
        <a href="/home" >
                           <button name="home"  type="submit">ir a HOME</button>
                    </a>
    </form>
</body>
</html>