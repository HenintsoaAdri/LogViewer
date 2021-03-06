<%@page import="adri.logviewer.model.BaseModel"%>
<%@page import="adri.logviewer.model.BaseModelPagination"%>
<%@page import="adri.logviewer.model.ProfilView"%>
<%@page import="adri.logviewer.model.PermissionType"%>
<%@ include file="../includes/header.jsp" %>
<% ProfilView item = (ProfilView)request.getAttribute("item"); 
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
                        			<% if(user.isAllowed(PermissionType.CRUDUTILISATEUR)){ %>
	                            	<a href="${pageContext.request.contextPath}/Profil/edit/<% out.print(item.getId()); %>" class="btn btn-info pull-right m-l-20 waves-effect waves-light"> <i class="fa fa-pencil"></i> Modifier</a>
	                            	<% } %>
	                                <p class="text-muted">D&eacute;tails du profil d'utilisateurs</p>
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
	                                            <div class="col-md-9 col-md-offset-3">
	                                                <p class="form-control-static"> <% out.print(item.getDescription());%> </p>
	                                            </div>
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
                        	<!-- tab -->
                            <ul class="nav customtab nav-tabs" role="tablist">
                                <li role="presentation" class="active"><a href="#Utilisateur" role="tab" data-toggle="tab" aria-expanded="false"><span class="visible-xs"><i class="fa fa-address-card fa-fw"></i></span> <span class="hidden-xs">Utilisateurs (<% out.print(item.getNombreUtilisateur()); %>)</span></a></li>
                                <li role="presentation" class=""><a href="#Groupe" role="tab" data-toggle="tab" aria-expanded="false"><span class="visible-xs"><i class="fa fa-gears fa-fw"></i></span> <span class="hidden-xs">Groupes g�r�s (<% out.print(item.getNombreGroupe()); %>)</span></a></li>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane fade in active" id="Utilisateur">
			                        <a href="${pageContext.request.contextPath}/Utilisateur/new?item.profil.id=<% out.print(item.getId()); %>" class="btn btn-success pull-right m-l-20 hidden-xs waves-effect waves-light"><i class="fa fa-plus fa-fw" aria-hidden="true"></i>Nouveau</a>
			                        <% if(pagination == null || pagination.getListe() == null || pagination.getListe().isEmpty() ){ %>
									    	<p class="text-danger">Aucun utilisateur dans ce profil</p>
									<% }else{ %>
									<div class="col-md-12">
			                            <div class="table-responsive">
			                                <table class="table">
			                                    <thead>
			                                        <tr>
			                                            <th>#</th>
			                                            <th>Nom &amp; Pr&eacute;nom</th>
			                                            <th>Poste</th>
			                                            <th>Email</th>
			                                            <th>Sexe</th>
			                                            <th>Activit�</th>
			                                            <th></th>
			                                        </tr>
			                                    </thead>
			                                    <tbody>
			                                    	<% String prefix = "Connect� il y a ";
			                                    	for(BaseModel u : pagination.getListe()){
			                                    		Utilisateur i = (Utilisateur)u;
			                                    	%>
			                                        <tr>
			                                            <td><% out.print(i.getId()); %></td>
			                                            <td><% out.print(i.getFullName()); %></td>
			                                            <td><% out.print(i.getPoste()); %></td>
			                                            <td><% out.print(i.getEmail()); %></td>
			                                            <td><% out.print(i.getSexeString()); %></td>
			                                            <td><% out.print(i.getLastLoggedString(prefix)); %></td>
			                                            <td><a href="${pageContext.request.contextPath}/Utilisateur/edit/<% out.print(i.getId()); %>" class="btn btn-warning m-l-20 waves-effect waves-light"><i class="fa fa-user-times fa-fw" aria-hidden="true"></i>Retirer / Modifier</a></td>
			                                        </tr>
			                                        <% } %>
			                                    </tbody>
			                                </table>
			                            </div>
									</div>
 									<div class="clearfix"></div>
									<% if(pagination.getNombrePage()>1){%>
								        <ul class="pagination">
								        <% for(int i=0; i<pagination.getNombrePage(); i++){ %>
										  <li <% if(pagination.getPage() == i)out.print("class=\"active\""); %>><a href="?item.id=<% out.print(item.getId()); %>&page=<% out.print(i+1); %>"><% out.print(i+1); %></a></li>
										<% } %>
										</ul>
								  <%  }
									} %>
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="Groupe">
                                </div>
                            </div>
                            <!-- /.tab -->
                        </div>
                	</div>
                </div>
                <!--./row-->
                <% if(user.isAllowed(PermissionType.CRUDAGENT)){ %>
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
                <% } %>
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
<script type="text/javascript">
var item = "Profil";
var detail = "Groupe" ;
var id = <% out.print(item.getId());%> ;
</script>
<script src="${pageContext.request.contextPath}/js/detailFunction.js"></script>
<script src="${pageContext.request.contextPath}/js/function.js"></script>
</body>
</html>