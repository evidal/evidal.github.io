<!doctype html>
<html>
    <head>
        <title>Blog d'Eric Vidal</title>
        <!--
        <link href="http://fonts.googleapis.com/css?family=Corben:bold" rel="stylesheet" type="text/css">
        <link href="http://fonts.googleapis.com/css?family=Nobile" rel="stylesheet" type="text/css">
        -->
        <link rel="shortcut icon" href="/images/small_lightbulb.png" type="image/png">
        <link rel="icon" href="/images/small_lightbulb.png" type="image/png">
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/css/blog.css"/>
        <script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="/js/bootstrap.min.js"></script>
        <!--
        <script type="text/javascript">
            var _gaq = _gaq || [];
            _gaq.push(['_setAccount', 'UA-28850873-1']);
            _gaq.push(['_trackPageview']);

            (function() {
                var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
                ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
                var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
            })();
        </script>
        -->
    </head>
    <body>
        <div id="content">
            <div class="container header">
                <img src="/images/lightbulb.png"> <h1> Bloc notes</h1>
                <div class="row">
                    <div class="span12">
                        <div class="navbar">
                            <div class="navbar-inner">
                                <a class="btn btn-navbar" data-toggle="collapse"
                                   data-target=".nav-collapse"><span class="icon-bar"></span> <span
                                        class="icon-bar"></span> <span class="icon-bar"></span>
                                </a>
                                <div class="nav-collapse">
                                    <ul class="nav nav-pills">
                                        <li><a href="/">Blog</a></li>
                                        <li><a href="/blog/auteur">About</a></li>
                                        <% if (user && users.userAdmin) {%>
                                            <li><a href="/edit">Nouveau Blog</a></li>
                                            <li><a href="${users.createLogoutURL("/")}">D&eacute;connexion</a></li>
                                        <%} %>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
            </div>
            <div class="container">