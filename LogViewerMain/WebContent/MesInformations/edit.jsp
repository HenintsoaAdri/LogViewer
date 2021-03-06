<%@page import="adri.logviewermain.model.Utilisateur"%>
<%@ include file="../includes/header.jsp" %>
<% 
	String password = (String)request.getAttribute("password");
%>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Utilisateur</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
                            <li><a href="${pageContext.request.contextPath}/MesInformations">Mes Informations</a></li>
                            <li class="active">Modifier</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->   
		        <%
		        	if(request.getAttribute("exception") != null){ 
		      		Exception e = (Exception)request.getAttribute("exception");
		        %>
		      	<div class="alert alert-danger alert-dismissable">
	                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	      			<% out.print(e.getMessage()); %>
	            </div>
                <!--./row-->
				<% } %>
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                    		<p class="label label-success label-rouded pull-right "><i class="fa fa-shield fw"></i> Utilisateur #<% out.print(user.getId()); %></p>
                    		<h3 class="box-title"> Mes Informations</h3>
                            <p class="text-muted">Modifier mes Informations</p>
                            <form class="form-horizontal form-material" action="${pageContext.request.contextPath}/MesInformations/update" method="post">
                            	<input type="hidden" name="user.id" value="<% out.print(user.getId()); %>">
                            	<div class="row">
	                            	<div class="col-md-6">
		                            	<div class="form-group">
		                                    <label class="col-md-12">Nom</label>
		                                    <div class="col-md-12">
		                                        <input name="user.nom" value="<% out.print(user.getNom()); %>" type="text" placeholder="Nom" class="form-control form-control-line">
											</div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="col-md-12">Poste</label>
		                                    <div class="col-md-12">
		                                        <input name="user.poste" value="<% out.print(user.getPoste()); %>" type="text" placeholder="Poste" class="form-control form-control-line">
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="col-md-12">Email</label>
		                                    <div class="col-md-12">
		                                        <input name="email" value="<% out.print(user.getEmail()); %>" type="text" class="form-control form-control-line" required>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="col-md-12">Mot de passe</label>
		                                    <div class="col-md-12">
		                                        <input name="user.password" value="<% out.print(user.getPassword()); %>" type="password" placeholder="Mot de passe" class="form-control form-control-line" required>
		                                    </div>
		                                </div>
	                            	</div>
	                            	<div class="col-md-6">
		                                <div class="form-group">
		                                    <label class="col-md-12">Pr&eacute;nom</label>
		                                    <div class="col-md-12">
		                                        <input name="user.prenom" value="<% out.print(user.getPrenom()); %>" type="text" placeholder="Syntaxe des lignes de log" class="form-control form-control-line">
		                                    </div>
		                                </div>
                                        <div class="form-group">
                                            <label class="col-md-12">Sexe</label>
                                            <div class="radio-list col-md-12">
                                                <label class="radio-inline p-0">
                                                    <div class="radio radio-primary">
                                                        <input type="radio" name="user.sexe" id="homme" value="M" <% if(user.getSexe() == 'M') out.print("checked"); %>>
                                                        <label for="homme"><i class="fa fa-male"></i> Homme</label>
                                                    </div>
                                                </label>
                                                <label class="radio-inline  p-0">
                                                    <div class="radio radio-primary">
                                                        <input type="radio" name="user.sexe" id="femme" value="F" <% if(user.getSexe() == 'F') out.print("checked"); %>>
                                                        <label for="femme"><i class="fa fa-female"></i> Femme</label>
                                                    </div>
                                                </label>
                                            </div>
                                        </div>
		                                <div class="form-group">
		                                    <label class="col-md-12">Confirmer votre mot de passe</label>
		                                    <div class="col-md-12">
		                                        <input name="user.confirm" value="<% out.print(user.getPassword()); %>" type="password" placeholder="Mot de passe" class="form-control form-control-line" required>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <div class="col-md-offset-9 col-sm-3">
		                                        <button class="btn btn-primary" type="submit">Modifier</button>
		                                    </div>
		                                </div>
	                            	</div>
                            	</div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>