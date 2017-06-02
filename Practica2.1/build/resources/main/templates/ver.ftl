<!DOCTYPE html>
<html>
<head>
<title>Crear estudiante</title>
</head>
<body>
    <h1>Agregar estudiante</h1>
    <form action="/ver" method="post">
         Matricula: <input  type="text" value = "${estudiante.getMatricula()}" disabled/><br/>
         Nombre: <input name="nombre" type="text" value = "${estudiante.getNombre()}" disabled /><br/>
         Apellido: <input name="apellido" type="text" value = "${estudiante.getApellido()}" disabled/><br/>
         Telefono: <input name="telefono" type="text" value = "${estudiante.getTelefono()}" disabled/><br/>

        <a href="/home" >
                           <button name="home"  type="submit">ir a HOME</button>
                    </a>
    </form>
</body>
</html>