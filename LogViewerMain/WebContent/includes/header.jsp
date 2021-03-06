<%@page import="adri.logviewermain.model.PermissionType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="apple-touch-icon" sizes="57x57" href="${pageContext.request.contextPath}/img/icon/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="${pageContext.request.contextPath}/img/icon/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="${pageContext.request.contextPath}/img/icon/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/img/icon/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="${pageContext.request.contextPath}/img/icon/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="${pageContext.request.contextPath}/img/icon/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="${pageContext.request.contextPath}/img/icon/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="${pageContext.request.contextPath}/img/icon/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/img/icon/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="${pageContext.request.contextPath}/img/icon/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/img/icon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="${pageContext.request.contextPath}/img/icon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/img/icon/favicon-16x16.png">
	<link rel="manifest" href="${pageContext.request.contextPath}/img/icon/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="${pageContext.request.contextPath}/img/icon/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">
    <title>LogViewer</title>
    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="${pageContext.request.contextPath}/css/sidebar-nav.min.css" rel="stylesheet">
    <!-- toast CSS -->
    <link href="${pageContext.request.contextPath}/css/jquery.toast.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <!-- FontAwesome CSS -->
    <link href="${pageContext.request.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="${pageContext.request.contextPath}/css/colors/default.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<%@page import="adri.logviewermain.model.Utilisateur"%>
<% Utilisateur user = (Utilisateur)request.getAttribute("user"); %>
<body class="fix-header">
	<script type="text/javascript">
		var url = '${pageContext.request.contextPath}';
	</script>
    <!-- ============================================================== -->
    <!-- Preloader -->
    <!-- ============================================================== -->
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" />
        </svg>
    </div>
    <!-- ============================================================== -->
    <!-- Wrapper -->
    <!-- ============================================================== -->
    <div id="wrapper">
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <div class="navbar-header">
                <div class="top-left-part">
                    <!-- Logo -->
                    <a class="logo" href="${pageContext.request.contextPath}/index">
                        <!-- Logo icon image, you can use font-icon also --><b>
                        <span class="visible-xs">
                        <!--This is dark logo icon--><img src="${pageContext.request.contextPath}/img/Logo-white.png" alt="home" class="dark-logo" /><!--This is light logo icon--><img src="${pageContext.request.contextPath}/img/Logo-transparent.png" alt="home" class="light-logo" />
                        </span>
                     </b>
                        <!-- Logo text image you can use text also -->
                        <span class="hidden-xs">
                        <!--This is dark logo text--><img src="${pageContext.request.contextPath}/img/Logo-large-white.png" alt="home" class="dark-logo" /><!--This is light logo text--><img src="${pageContext.request.contextPath}/img/Logo-large-transparent.png" alt="home" class="light-logo" />
                     	</span> 
                     </a>
                </div>
                <!-- /Logo -->
                <ul class="nav navbar-top-links navbar-right pull-right">
                	<li>
                		<a class="waves-effect waves-light" href="${pageContext.request.contextPath}/MesInformations"><% out.print(user.getFullName()); %></a>
                	</li>
                    <li>
                        <a class="waves-effect waves-light" href="${pageContext.request.contextPath}/Deconnexion"><i class="fa fa-sign-out fw"></i> D&eacute;connexion</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-header -->
            <!-- /.navbar-top-links -->
            <!-- /.navbar-static-side -->
        </nav>
        <!-- End Top Navigation -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav slimscrollsidebar">
                <div class="sidebar-head">
                    <h3><span class="fa-fw open-close"><i class="ti-close ti-menu"></i></span> <span class="hide-menu">Navigation</span></h3>
                </div>
                <ul class="nav" id="side-menu">
                    <li style="padding: 70px 0 0;">
                        <a href="${pageContext.request.contextPath}/index" class="waves-effect"><i class="fa fa-dashboard fa-fw" aria-hidden="true"></i>Board</a>
                    </li>
                    <% if(user.isGenerallyAllowed("Agent")||user.isAllowed(PermissionType.LECTURETELECHARGEMENT)){ %>
                    <li>
                        <a href="${pageContext.request.contextPath}/Agent" class="waves-effect"><i class="fa fa-gears fa-fw" aria-hidden="true"></i>Agent</a>
                    </li>
                    <% } if(user.isGenerallyAllowed("Groupe")){ %>
                    <li>
                        <a href="${pageContext.request.contextPath}/Groupe" class="waves-effect"><i class="fa fa-users fa-fw" aria-hidden="true"></i>Groupe</a>
                    </li>
                    <% } if(user.isGenerallyAllowed("Profil")){ %>
                    <li>
                        <a href="${pageContext.request.contextPath}/Profil" class="waves-effect"><i class="fa fa-shield fa-fw" aria-hidden="true"></i>Profil</a>
                    </li>
                    <% } if(user.isGenerallyAllowed("Utilisateur")){ %>
                    <li>
                        <a href="${pageContext.request.contextPath}/Utilisateur" class="waves-effect"><i class="fa fa-address-card fa-fw" aria-hidden="true"></i>Utilisateur</a>
                    </li>
                    <% } %>
                    <li>
                        <a href="${pageContext.request.contextPath}/Fichier" class="waves-effect"><i class="fa fa-save fa-fw" aria-hidden="true"></i>Fichiers enregistr&eacute;s</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/MesInformations" class="waves-effect"><i class="fa fa-user fa-fw" aria-hidden="true"></i>Mes Informations</a>
                    </li>

                </ul>
            </div>
            
        </div>
        <!-- ============================================================== -->
        <!-- End Left Sidebar -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page Content -->
        <!-- ============================================================== -->
        <div id="page-wrapper">