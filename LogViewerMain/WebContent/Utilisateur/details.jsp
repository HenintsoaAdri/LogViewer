<%@page import="adri.logviewermain.model.Utilisateur"%>
<%@page import="adri.logviewermain.model.PermissionType"%>
<%@ include file="../includes/header.jsp" %>
<% Utilisateur item = (Utilisateur)request.getAttribute("item"); %>
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
                    		<h3 class="box-title"> Utilisateurs</h3>
	                        <div class="form-horizontal">
	                            <div class="form-body">
	                            	<a href="${pageContext.request.contextPath}/Utilisateur/edit/?item.id=<% out.print(item.getId()); %>" class="btn btn-info pull-right m-l-20 waves-effect waves-light"> <i class="fa fa-pencil"></i> Modifier</a>
	                                <p class="text-muted">D&eacute;tails de l'utilisateur</p>
	                                <hr class="m-t-0 m-b-40">
	                                <div class="row">
	                                    <div class="col-md-6">
	                                        <div class="col-md-12 form-group">
	                                            <label class="control-label col-md-3">Nom : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getNom()); %> </p>
	                                            </div>
	                                        </div>
	                                        <div class="col-md-12 form-group">
	                                            <label class="control-label col-md-3">Poste : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getPoste()); %> </p>
	                                            </div>
	                                        </div>
	                                        <div class="col-md-12 form-group">
	                                            <label class="control-label col-md-3">Email : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getEmail()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-6">
	                                        <div class="col-md-12 form-group">
	                                            <label class="control-label col-md-3">Pr�nom : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getPrenom()); %> </p>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Sexe :</label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getSexeString()); %> <i class="fa fw <% out.print(item.getSexeIcon()); %>"></i></p>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Activit� :</label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> Connect� il y a<% out.print(item.getLastLoggedString()); %></p>
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
                <% if(item.getProfil() != null){ %>
                <div class="row">
                    <div class="col-md-12">
                    	<div class="white-box">
                    		<h3 class="box-title"> Groupe #<% out.print(item.getProfil().getGroupe().getId()); %></h3>
	                        <div class="form-horizontal">
	                            <div class="form-body">
	                                <p class="text-muted">D&eacute;tails du groupe d'utilisateurs</p>
	                                <hr class="m-t-0 m-b-40">
	                                <div class="row">
	                                    <div class="col-md-6">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Nom : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getProfil().getGroupe().getNom()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-6">
	                                        <blockquote>
	                                        	<% out.print(item.getProfil().getGroupe().getDescription()); %> 
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
                    		<h3 class="box-title"> Profil #<% out.print(item.getProfil().getId()); %></h3>
	                        <div class="form-horizontal">
	                            <div class="form-body">
	                                <p class="text-muted">D&eacute;tails du profil de groupe</p>
	                                <hr class="m-t-0 m-b-40">
	                                <div class="row">
	                                    <div class="col-md-6">
	                                        <div class="col-md-12 form-group">
	                                            <label class="control-label col-md-3">Nom : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getProfil().getNom()); %> </p>
	                                            </div>
	                                        </div>
		                                    <div class="col-md-12">
		                                        <blockquote>
		                                        	<% out.print(item.getProfil().getDescription());%>
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
				                                         <input id="permission<% out.print(p.ordinal()); %>" type="checkbox" name="item.listePermission" value="<% out.print(p.name()); %>" <% if(item.getProfil().isAllowed(p)) out.print("checked"); %> disabled>
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
                <% } %>
                <div class="row">
                    <div class="col-xs-12">
                        <a data-toggle="modal" href="#delete" data-item="Utilisateur" data-id="<% out.print(item.getId()); %>" class="text-danger pull-right confirmDelete">Supprimer l'utilisateur</a>
                    </div>
                </div>
                <!-- /.row -->
			    <!-- Modal -->
			    <div class="modal fade" id="delete" role="dialog">
			    </div>
                <!-- /.Modal -->
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/function.js"></script>
