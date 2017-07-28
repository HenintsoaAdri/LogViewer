<%@page import="adri.logviewermain.model.Utilisateur"%>
<%@page import="adri.logviewermain.model.PermissionType"%>
<%@ include file="../includes/header.jsp" %>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Profil</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
                            <li class="active">Mes informations</li>
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
                    		<p class="label label-success label-rouded pull-right "><i class="fa fa-user fw"></i> Utilisateur #<% out.print(user.getId()); %></p>
                    		<h3 class="box-title"> Mes Informations</h3>
	                        <div class="form-horizontal">
	                            <div class="form-body">
	                                <p class="text-muted">D&eacute;tails de l'utilisateur</p>
	                                <hr class="m-t-0 m-b-40">
	                                <div class="row">
	                                    <div class="col-md-6">
	                                        <div class="col-md-12 form-group">
	                                            <label class="control-label col-md-3">Nom : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(user.getNom()); %> </p>
	                                            </div>
	                                        </div>
	                                        <div class="col-md-12 form-group">
	                                            <label class="control-label col-md-3">Poste : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(user.getPoste()); %> </p>
	                                            </div>
	                                        </div>
	                                        <div class="col-md-12 form-group">
	                                            <label class="control-label col-md-3">Email : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(user.getEmail()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-6">
	                                        <div class="col-md-12 form-group">
	                                            <label class="control-label col-md-3">Prénom : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(user.getPrenom()); %> </p>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Sexe :</label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(user.getSexeString()); %> <i class="fa fw <% out.print(user.getSexeIcon()); %>"></i></p>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Activité :</label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> Connecté il y a <% out.print(user.getLastLoggedString()); %></p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                </div>
	                                <!--/row-->
	                            </div>
	                        </div>
	                	</div>   
                    </div>
                </div>
	            <!--./row-->
                <div class="row">
                    <div class="col-md-12">
                    	<div class="white-box">
                    		<p class="label label-primary label-rouded  pull-right"><i class="fa fa-group fw"></i> Groupe #<% out.print(user.getProfil().getGroupe().getId()); %></p>
                    		<h3 class="box-title">Mon Groupe</h3>
	                        <div class="form-horizontal">
	                            <div class="form-body">
	                                <p class="text-muted">D&eacute;tails du groupe d'utilisateurs</p>
	                                <hr class="m-t-0 m-b-40">
	                                <div class="row">
	                                    <div class="col-md-6">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Nom : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(user.getProfil().getGroupe().getNom()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-6">
	                                        <blockquote>
	                                        	<% out.print(user.getProfil().getGroupe().getDescription()); %> 
	                                        </blockquote>
	                                    </div>
	                                    <!--/span-->
	                                </div>
	                                <!--/row-->
	                            </div>
	                        </div>
	                	</div>   
                    </div>
                </div>
                <!--./row-->
                <div class="row">
                    <div class="col-md-12">
                    	<div class="white-box">
                    		<p class="label label-danger label-rouded pull-right "><i class="fa fa-shield fw"></i> Profil #<% out.print(user.getProfil().getId()); %></p>
                    		<h3 class="box-title"> Mon Profil </h3>
	                        <div class="form-horizontal">
	                            <div class="form-body">
	                                <p class="text-muted">D&eacute;tails du profil de groupe</p>
	                                <hr class="m-t-0 m-b-40">
	                                <div class="row">
	                                    <div class="col-md-6">
	                                        <div class="col-md-12 form-group">
	                                            <label class="control-label col-md-3">Nom : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(user.getProfil().getNom()); %> </p>
	                                            </div>
	                                        </div>
		                                    <div class="col-md-12">
		                                        <blockquote>
		                                        	<% out.print(user.getProfil().getDescription());%>
		                                        </blockquote>
		                                    </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-6">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Permission :</label>
	                                            <div class="col-md-9">
                                                	<% for(PermissionType p : PermissionType.values()) {%>
                                                    <div class="checkbox checkbox-success checkbox-circle">
				                                         <input id="permission<% out.print(p.ordinal()); %>" type="checkbox" name="item.listePermission" value="<% out.print(p.name()); %>" <% if(user.getProfil().isAllowed(p)) out.print("checked"); %> disabled>
				                                         <label for="permission<% out.print(p.ordinal()); %>"> <% out.print(p.getLibelle()); %> </label>
				                                     </div>
                                                	<% } %>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                </div>
	                                <!--/row-->
	                            </div>
	                        </div>
	                	</div>   
                    </div>
                </div>
                <!--./row-->
                <div class="row">
                    <div class="col-xs-12">
                        <a href="${pageContext.request.contextPath}/MesInformations/edit/?item.id=<% out.print(user.getId()); %>" class="text-primary pull-right">Modifier mes informations</a>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
