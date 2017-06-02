<!DOCTYPE html>
<html>
<head>
<title>Crear estudiante</title>
</head>
<body>
    <h1>Agregar estudiante</h1>
    <form action="/crear" method="post">
         Matricula: <input name="matricula" type="number"/><br/>
         Nombre: <input name="nombre" type="text"/><br/>
         Apellido: <input name="apellido" type="text"/><br/>
         Telefono: <input name="telefono" type="text"/><br/>
        <button name="Enviar" type="submit">Enviar</button>
        <a href="/home" >
                   <button name="home"  type="submit">ir a HOME</button>
            </a>
    </form>
</body>
</html>