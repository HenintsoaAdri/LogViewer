<%@page import="adri.logviewermain.model.BaseModel"%>
<%@page import="adri.logviewermain.model.BaseModelPagination"%>
<%@page import="adri.logviewermain.model.Profil"%>
<%@page import="adri.logviewermain.model.PermissionType"%>
<%@ include file="../includes/header.jsp" %>
<% Profil item = (Profil)request.getAttribute("item"); 
 	BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination");
%>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Profil</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
                            <li><a href="${pageContext.request.contextPath}/Profil">Profil</a></li>
                            <li class="active">D&eacute;tails</li>
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
                    		<h3 class="box-title">Profil #<% out.print(item.getId()); %></h3>
	                        <div class="form-horizontal">
	                            <div class="form-body">
	                            	<a href="${pageContext.request.contextPath}/Profil/edit/?item.id=<% out.print(item.getId()); %>" class="btn btn-info pull-right m-l-20 waves-effect waves-light"> <i class="fa fa-pencil"></i> Modifier</a>
	                                <p class="text-muted">D&eacute;tails du profil de groupe</p>
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
	                                            <label class="control-label col-md-3">Groupe : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getGroupe().getNom()); %> </p>
	                                            </div>
	                                        </div>
		                                    <div class="col-md-12">
		                                        <blockquote>
		                                        	<% out.print(item.getDescription());%>
		                                        </blockquote>
		                                    </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-6">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Permission :</label>
	                                            <div class="col-md-9">
                                                	<% for(PermissionType p : PermissionType.values()) {%>
                                                    <div class="checkbox checkbox-primary checkbox-circle">
				                                         <input id="permission<% out.print(p.ordinal()); %>" type="checkbox" name="item.listePermission" value="<% out.print(p.name()); %>" <% if(item.isAllowed(p)) out.print("checked"); %> disabled>
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
                	<div class="col-md-12">
                        <div class="white-box">
                        <a href="${pageContext.request.contextPath}/Utilisateur/new?item.profil.id=<% out.print(item.getId()); %>&item.profil.groupe.id=<% out.print(item.getGroupe().getId()); %>" class="btn btn-success pull-right m-l-20 hidden-xs waves-effect waves-light"><i class="fa fa-plus fa-fw" aria-hidden="true"></i>Nouveau</a>
                            <h3 class="box-title">Utilisateurs</h3>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nom&amp;Pr&eacute;nom</th>
                                            <th>Poste</th>
                                            <th>Email</th>
                                            <th>Sexe</th>
                                            <th>Activit�</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<% for(BaseModel u : pagination.getListe()){
                                    		Utilisateur i = (Utilisateur)u;
                                    	%>
                                        <tr>
                                            <td><% out.print(i.getId()); %></td>
                                            <td><% out.print(i.getFullName()); %></td>
                                            <td><% out.print(i.getPoste()); %></td>
                                            <td><% out.print(i.getEmail()); %></td>
                                            <td><% out.print(i.getSexeString()); %></td>
                                            <td>Connect� il y a 2min</td>
                                            <td><a href="${pageContext.request.contextPath}/Utilisateur/edit/?item.id=<% out.print(i.getId()); %>" class="btn btn-warning m-l-20 waves-effect waves-light"><i class="fa fa-user-times fa-fw" aria-hidden="true"></i>Retirer / Modifier</a></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                	</div>
                </div>
                <!--./row-->
                <div class="row">
                    <div class="col-xs-12">
                        <a data-toggle="modal" href="#delete" data-item="Profil" data-id="<% out.print(item.getId()); %>" class="text-danger pull-right confirmDelete">Supprimer le profil</a>
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
