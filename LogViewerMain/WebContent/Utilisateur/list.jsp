<%@page import="adri.logviewermain.model.PermissionType"%>
<%@page import="adri.logviewermain.model.BaseModel"%>
<%@page import="adri.logviewermain.model.BaseModelPagination"%>
<%@page import="adri.logviewermain.model.Utilisateur"%>
<%@ include file="../includes/header.jsp" %>
<% BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination"); %>
<% Utilisateur item = (Utilisateur)request.getAttribute("item"); %>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Utilisateur</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
                            <li class="active">Utilisateur</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /row -->  
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
                	<div class="col-sm-12">
                        <div class="white-box">
                       		<a href="#recherche" data-toggle="collapse" class="pull-right">
                       			<i class="fa fa-plus fw"></i>
                       		</a>
                            <h3 class="box-title">Recherche</h3>
                                <div class="panel-body collapse" id="recherche">
                            		<p class="text-muted">Recherche d'utilisateur</p>
                                    <form action="#" class="form-horizontal form-material">
                                        <div class="form-body">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3">Utilisateur</label>
                                                        <div class="col-md-9">
                                                            <input name="item.nom" value="<% out.print(item.getNom()); %>" type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3">Poste</label>
                                                        <div class="col-md-9">
                                                            <input name="item.poste" value="<% out.print(item.getPoste()); %>" type="text" class="form-control">
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3">Sexe</label>
                                                        <div class="radio-list col-md-9">
                                                            <label class="radio-inline p-0">
                                                                <div class="radio radio-info">
                                                                    <input type="radio" name="sexe" id="radio0" value="" <% if(item.getSexe() == 0)out.print("checked"); %>>
                                                                    <label for="radio0">Tous </label>
                                                                </div>
                                                            </label>
                                                            <label class="radio-inline p-0">
                                                                <div class="radio radio-info">
                                                                    <input type="radio" name="sexe" id="radio1" value="M" <% if(item.getSexe() == 'M')out.print("checked"); %>>
                                                                    <label for="radio1">Homme </label>
                                                                </div>
                                                            </label>
                                                            <label class="radio-inline">
                                                                <div class="radio radio-info">
                                                                    <input type="radio" name="sexe" id="radio2" value="F" <% if(item.getSexe() == 'F')out.print("checked"); %>>
                                                                    <label for="radio2">Femme </label>
                                                                </div>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--/span-->
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label class="control-label col-md-3">Permissions</label>
                                                        <div class="col-md-9">
	                                                	<%  
	                                                	for(PermissionType p : PermissionType.values()) {%>
	                                                    <div class="checkbox checkbox-primary checkbox-circle">
					                                         <input id="permission<% out.print(p.ordinal()); %>" type="checkbox" name="item.profil.listePermission" value="<% out.print(p.name()); %>" <% if(item.getProfil() != null && item.getProfil().hasPermission(p)) out.print("checked"); %>>
					                                         <label for="permission<% out.print(p.ordinal()); %>"> <% out.print(p.getLibelle()); %> </label>
					                                     </div>
	                                                	<% } %>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--/span-->
                                            </div>
                                            <!--/row-->
                                            <div class="row">
                                                <div class="col-md-offset-8 col-md-4">
                                                    <div class="form-group">
                                                        <div class="col-md-9">
                                                            <button type="submit" class="btn btn-primary"><i class="fa fa-search fa-fw"></i> Rechercher</button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--/span-->
                                            </div>
                                            <!--/row-->
                                        </div>
                                    </form>
                                </div>
                        </div>
                    </div>
                    <div class="col-sm-12">
                        <div class="white-box">
                        <a href="${pageContext.request.contextPath}/Utilisateur/new" class="btn btn-info pull-right m-l-20 waves-effect waves-light"><i class="fa fa-plus fa-fw" aria-hidden="true"></i>Nouveau</a>
                            <h3 class="box-title">Liste</h3>
                            <p class="text-muted">Liste des utilisateurs enregistr&eacute;s</p>
                            <div class="table-responsive">
                            <%  if(pagination == null || pagination.getListe() == null ){%>
                    		<p class="text-danger">Aucun utilisateur enregistr�</p>
                    		<%  }else{ %>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nom &amp; Pr&eacute;nom</th>
                                            <th>Poste</th>
                                            <th>Email</th>
                                            <th>Groupe</th>
                                            <th>Profil</th>
                                            <th>Sexe</th>
                                            <th>Activit&eacute;</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<% for(BaseModel base : pagination.getListe()){ 
                                    		Utilisateur i = (Utilisateur)base;
                                    	%>
                                        <tr>
                                            <td><% out.print(i.getId()); %></td>
                                            <td><% out.print(i.getFullName()); %></td>
                                            <td><% out.print(i.getPoste()); %></td>
                                            <td><% out.print(i.getEmail()); %></td>
                                            <td><% if(i.getProfil() != null)out.print(i.getProfil().getGroupe().getNom()); %></td>
                                            <td><% if(i.getProfil() != null)out.print(i.getProfil().getNom()); %></td>
                                            <td><% out.print(i.getSexeString()); %></td>
                                            <td>Connect� il y a <% out.print(i.getLastLoggedString()); %></td>
                                            <td><a href="${pageContext.request.contextPath}/Utilisateur/?item.id=<% out.print(i.getId()); %>" class="btn btn-success m-l-20 waves-effect waves-light"><i class="fa fa-chevron-right fa-fw" aria-hidden="true"></i>Voir l'utilisateur</a></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                                <% } %>
                            </div> 
                            <ul class="pagination">
                            <% for(int i=0; i+1<pagination.getNombrePage() &&i<pagination.getNombrePage(); i++){ %>
							  <li><a href="?page=<% out.print(i+1); %>"><% out.print(i+1); %></a></li>
							<% } %>
							</ul>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
