<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Tecno Blog</title>

    <!-- Bootstrap Core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="css/clean-blog.min.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-custom navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    Menu <i class="fa fa-bars"></i>
                </button>

                <#if (usuario) == 1>
                    <a class="navbar-brand" href="/logout">  ${name} Logout</a>
                 </#if>
                 <#if (sesion)== 0 >
                    <a class="navbar-brand" href="/login"> Login</a>
                 </#if>





            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                   <li>
                   </li>
                    <li>
                        <a href="/index">Home</a>
                    </li>
                    <li>
                        <a href="/crearArticulo">Crear Articulo</a>
                    </li>

                    <li>
                        <a href="/registrar">Registrar</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Header -->
    <!-- Set your background image for this header on the line below. -->
    <header class="intro-header" style="background-image: url('img/code.jpeg')">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <div class="site-heading">
                        <h1>Tech Blog</h1>
                        <hr class="small">
                        <span class="subheading">Technology is everywhere</span>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Main Content -->
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                <div class="post-preview">
                <#list articulos as a>
                    <br>
                    <a href="/post/${a.getId()}">
                        <h2 class="post-title col-xl"> ${a.getTitulo()} </h2> </a>
                        <hr size="30">


                        <form method="POST">
                            <button formaction="/borrar" value="${a.getId()}" style = "background-color: white;border-style:none; color: black" type="submit" class="btn btn-primary">
                              <i id="hovering" class="glyphicon glyphicon-trash"></i>
                            </button>
                            <button formaction="/editar" formmethod="GET" value="${a.getId()}"style = "background-color: white;border-style:none; color: black" type="submit" class="btn btn-primary">
                                                                  <i id="hovering" class="glyphicon glyphicon-edit"></i>
                                                                </button>

                                                    <input name="id" value="${a.getId()}" hidden />
                            </form>

                    <h5> Posted by: ${a.getAutor().getNombre()}, ${ a.getFecha()} </h5>

                    <h4 class="post-subtitle">
                      <i class="post-subtitle">
                         <#assign cuerpo=a.cuerpo>
                         <#if cuerpo?length &lt; 70>
                             ${cuerpo}
                         <#else>
                             ${cuerpo?substring(0,70) + "..."}
                         </#if>
                     </i>
                       <br>
                    </h4>
                    <#list a.getEtiquetas() as e>
                      <input class="col-xs-3 " name="etiquetas" size="4" style= "background-color: #EFEBE9; color: black; border-radius: 7px" type="text" value="${e.getEtiqueta()}" data-role="tagsinput" disabled/>
                      </#list>



                </#list>
                </div>
                <hr>
            </div>
        </div>
    </div>

    <hr>

    <!-- Footer -->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
                    <ul class="list-inline text-center">
                        <li>
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-twitter fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-facebook fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="fa-stack fa-lg">
                                    <i class="fa fa-circle fa-stack-2x"></i>
                                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                                </span>
                            </a>
                        </li>
                    </ul>
                    <p class="copyright text-muted">Copyright &copy; Your Website 2016</p>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Contact Form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/contact_me.js"></script>

    <!-- Theme JavaScript -->
    <script src="js/clean-blog.min.js"></script>

</body>

</html>
