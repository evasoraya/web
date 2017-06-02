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
</head>
<body>
    <table id='tabla'>
        <thead>
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
                                      <button name="Matricula" value="${est.getMatricula()}" type="submit">Editar</button>
                                    </form>
                                    <form action = "/borrar" method = "GET">
                                        <button name="Matricula" value="${est.getMatricula()}" type="submit">Borrar</button>
                                            </form>
                                            <form action = "/ver" method = "GET">
                                               <button name="Matricula" value="${est.getMatricula()}" type="submit">ver</button>
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