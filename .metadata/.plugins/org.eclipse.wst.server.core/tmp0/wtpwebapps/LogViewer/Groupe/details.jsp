<%@page import="adri.logviewer.model.GroupeView"%>
<%@page import="adri.logviewer.model.Agent"%>
<%@page import="adri.logviewer.model.BaseModel"%>
<%@page import="adri.logviewer.model.BaseModelPagination"%>
<%  BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination");
	GroupeView item = (GroupeView)request.getAttribute("item"); %>
<%@ include file="../includes/header.jsp" %>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Groupe</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
                            <li><a href="${pageContext.request.contextPath}/Groupe">Groupe</a></li>
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
                    		<h3 class="box-title">Groupe #<% out.print(item.getId()); %></h3>
	                        <div class="form-horizontal">
	                            <div class="form-body">
                        			<% if(user.isAllowed(PermissionType.CRUDGROUPE)){ %>
	                            	<a href="${pageContext.request.contextPath}/Groupe/edit/<% out.print(item.getId()); %>" class="btn btn-info pull-right m-l-20 waves-effect waves-light"> <i class="fa fa-pencil"></i> Modifier</a>
	                            	<% } %>
	                                <p class="text-muted">D&eacute;tails du groupe d'agents</p>
	                                <hr class="m-t-0 m-b-40">
	                                <div class="row">
	                                    <div class="col-md-6">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Nom : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getNom()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-6">
	                                    	<p>
	                                        	<% out.print(item.getDescription()); %>
	                                    	</p> 
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
                                <li role="presentation" class="active"><a href="#Agent" role="tab" data-toggle="tab" aria-expanded="false"><span class="visible-xs"><i class="fa fa-gear fa-fw"></i></span> <span class="hidden-xs">Agents (<% out.print(item.getNombreAgent()); %>)</span></a></li>
                                <li role="presentation" class=""><a href="#Profil" role="tab" data-toggle="tab" aria-expanded="false"><span class="visible-xs"><i class="fa fa-gears fa-fw"></i></span> <span class="hidden-xs">Profil associ�s (<% out.print(item.getNombreProfil()); %>)</span></a></li>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane fade in active" id="Agent">
									<a href="${pageContext.request.contextPath}/Agent/new?item.listeGroupe.id=<% out.print(item.getId()); %>" class="btn btn-info pull-right m-l-20 waves-effect waves-light"><i class="fa fa-plus fa-fw" aria-hidden="true"></i>Nouveau</a>
									                                    
									<% if(pagination == null || pagination.getListe() == null || pagination.getListe().isEmpty() ){ %>
									    	<p class="text-danger">Aucun agent dans ce groupe</p>
									<% }else{ %>
									<div class="col-md-12">
									    <div class="table-responsive">
									        <table class="table">
									           <thead>
									              <tr>
									                  <th>#</th>
									                  <th>Nom</th>
									                  <th>Adresse</th>
									                  <th>Port</th>
									                  <th>Syntaxe</th>
									                  <th></th>
									              </tr>
									            </thead>
									            <tbody>	
											  	<%	for(BaseModel base : pagination.getListe()){ 
											  		Agent i = (Agent)base; %>	  		
											      <tr>
											          <td><% out.print(i.getId()); %></td>
											          <td><% out.print(i.getNom()); %></td>
											          <td><% out.print(i.getAdresse()); %></td>
											          <td><% out.print(i.getPort()); %></td>
											          <td><% out.print(i.getSyntaxe()); %></td>
											          <td>
											          	<a href="${pageContext.request.contextPath}/Agent/<% out.print(i.getId()); %>/connect" class="btn btn-success m-l-20 waves-effect waves-light"><i class="fa fa-plug fa-fw" aria-hidden="true"></i>Connexion</a>
											          	<a href="${pageContext.request.contextPath}/Agent/<% out.print(i.getId()); %>" class="btn btn-primary m-l-20 waves-effect waves-light"><i class="fa fa-chevron-right fa-fw" aria-hidden="true"></i>Voir l'agent</a>
											          </td>
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
										  <li <% if(pagination.getPage() == i)out.print("class=\"active\""); %>><a href="?page=<% out.print(i+1); %>"><% out.print(i+1); %></a></li>
										<% } %>
										</ul>
								  <%  }
		                            } %>
								</div>
                                <div role="tabpanel" class="tab-pane fade" id="Profil">
                                </div>
                        	</div>
                        </div>
                	</div>
                </div>
                <!--./row-->
                <% if(user.isAllowed(PermissionType.CRUDGROUPE)){ %>
                <div class="row">
                    <div class="col-xs-12">
                        <a data-toggle="modal" href="#delete" data-item="Groupe" data-id="<% out.print(item.getId()); %>" class="text-danger pull-right confirmDelete">Supprimer le groupe</a>
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
var item = "Groupe";
var detail = "Profil" ;
var id = <% out.print(item.getId());%> ;
</script>
<script src="${pageContext.request.contextPath}/js/detailFunction.js"></script>
<script src="${pageContext.request.contextPath}/js/function.js"></script>
</body>
</html>