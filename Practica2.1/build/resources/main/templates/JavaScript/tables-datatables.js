$(window).on('load', function() {

    var tableEst = $('#datos-estudiantes').DataTable({
        "responsive": true,
        "bLengthChange": false,
        "dom": '<ftip>',
        "language": {
            "sProcessing":     "Procesando...",
            "sLengthMenu":     "Mostrar _MENU_ registros",
            "sZeroRecords":    "No se encontraron resultados",
            "sEmptyTable":     "Ningún dato disponible en esta tabla",
            "sInfo":           "Mostrando del _START_ al _END_ de un total de _TOTAL_",
            "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0",
            "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix":    "",
            "sSearch":         "Buscar:",
            "sUrl":            "",
            "sInfoThousands":  ",",
            "sLoadingRecords": "Cargando...",
            "paginate": {
                "previous": '<i class="demo-psi-arrow-left"></i>',
                "next": '<i class="demo-psi-arrow-right"></i>'
            }
        }
    });

    $('#datos-estudiantes').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            tableEst.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );


    $('#btnDelete').click( function () {
        var matricula = tableEst.row('.selected').data()[0];

            $.ajax({
               type:'POST',
               data: "Matricula="+matricula,
               url:"/Borrar",
                success:function () {
                    window.location.href = "/Home";
                }
            });
    });

    $('#btnModify').click( function () {
        var matricula = tableEst.row('.selected').data()[0];
        var nombre = tableEst.row('.selected').data()[1];
        var apellido = tableEst.row('.selected').data()[2];
        var telefono = tableEst.row('.selected').data()[3];

        function redirectPost(url, data) {
            var form = document.createElement('form');
            form.method = 'get';
            form.action = url;
            for (var name in data) {
                var input = document.createElement('input');
                input.type = 'hidden';
                input.name = name;
                input.value = data[name];
                form.appendChild(input);
            }
            $(document.body).append(form);
            form.submit();
        }

        redirectPost('/Modificar', { Mat: matricula, Nom: nombre, Ape: apellido, Tel: telefono });

    });

    $('#btnView').click( function () {
        var matricula = tableEst.row('.selected').data()[0];
        var nombre = tableEst.row('.selected').data()[1];
        var apellido = tableEst.row('.selected').data()[2];
        var telefono = tableEst.row('.selected').data()[3];

        function redirectPost(url, data) {
            var form = document.createElement('form');
            form.method = 'get';
            form.action = url;
            for (var name in data) {
                var input = document.createElement('input');
                input.type = 'hidden';
                input.name = name;
                input.value = data[name];
                form.appendChild(input);
            }
            $(document.body).append(form);
            form.submit();
        }

        redirectPost('/Visualizar', { Mat: matricula, Nom: nombre, Ape: apellido, Tel: telefono });

    });
});

