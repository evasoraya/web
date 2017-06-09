<!DOCTYPE html>
<html>
<head>
    <title>Estudiantes</title>
    <script src="JavaScript/jquery-2.2.4.min.js">

    <!--DataTables [ OPTIONAL ]-->
        <script src="JavaScript/datatables/media/js/jquery.dataTables.js"></script>
        <script src="JavaScript/datatables/extensions/Responsive/js/dataTables.responsive.min.js"></script>

        <!--DataTables Sample [ SAMPLE ]-->
        <script src="JavaScript/tables-datatables.js"></script>
        <link href="JavaScript/datatables/media/css/dataTables.bootstrap.css" rel="stylesheet">
        <link href="JavaScript/datatables/extensions/Responsive/css/dataTables.responsive.css" rel="stylesheet">

        <title>Crear estudiante</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>

<body>
    <table  class="table" id="tabla">
        <thead class="thead-inverse">
                        <tr>
                            <th>Mat</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Tel.</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#if (estudiantes) ??>
                            <#list estudiantes as est>
                                <tr>
                                    <#if (est.getMatricula())??>
                                        <td>${est.getMatricula()}</td>

                                    </#if>

                                    <#if (est.getNombre())??>
                                        <td>${est.getNombre()}</td>

                                    </#if>

                                    <#if (est.getApellido())??>
                                        <td>${est.getApellido()}</td>

                                    </#if>


                                    <#if (est.getTelefono())??>
                                        <td>${est.getTelefono()}</td>

                                    </#if>
                                    <td>
                                    <form action = "/editar" method = "GET">
                                      <button name="matricula" value="${est.getMatricula()}" type="submit">Editar</button>
                                    </form>
                                    <form action = "/borrar" method = "GET">
                                        <button name="matricula" value="${est.getMatricula()}" type="submit">Borrar</button>
                                            </form>
                                            <form action = "/ver" method = "GET">
                                               <button name="matricula" value="${est.getMatricula()}" type="submit">ver</button>
                                                   </form>
                                    </td>
                                </tr>

                            </#list>
                        </#if>
                    </tbody>
    </table>
    <a href="/crear" >
           <button name="crear"  type="submit">Crear</button>
    </a>

</body>
</html>