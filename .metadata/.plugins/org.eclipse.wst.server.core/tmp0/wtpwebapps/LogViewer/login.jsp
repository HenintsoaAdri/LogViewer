<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='${pageContext.request.contextPath}/css/login.css' rel='stylesheet' type='text/css'>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
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
<title>Log Viewer - Authentification</title>
</head>
<body>
  <div class="login-page">
	      <% if(request.getAttribute("exception") != null){ 
	      	Exception e = (Exception)request.getAttribute("exception");
	      %>
	      	<div class="alert alert-danger alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
      			<% out.print(e.getMessage()); e.printStackTrace(); %>
            </div>
		  <% } %>
	  <div class="form">
	    <form class="login-form" method="post" action="${pageContext.request.contextPath}/Connexion">
	      <input type="text" placeholder="Adresse email" id="username" name="email" required value="<% out.print(request.getAttribute("email")); %>"/>
	      <input type="password" placeholder="Mot de passe" id="password" name="password" required/>
	      <button type="submit">connexion</button>
	      <button id="reinit" type="submit" formaction="${pageContext.request.contextPath}/ReinitPassword" formnovalidate>Mot de passe oublié</button>
	    </form>
	  </div>
  </div>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>