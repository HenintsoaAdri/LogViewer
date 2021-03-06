<%@page import="adri.logviewer.model.BaseModel"%>
<%@page import="adri.logviewer.model.Agent"%>
<%@page import="adri.logviewer.model.BaseModelPagination"%>
<%@ include file="../includes/header.jsp" %>
<% BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination"); 
	Agent item = (Agent) request.getAttribute("item");
%>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Agent distant</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    	<form action="${pageContext.request.contextPath}/Agent/search" class="col-xs-4 pull-right">
                           <div class="input-group">
                           	<input value="<% out.print(item.getNom()); %>" type="text" name="item.nom" class="form-control input-sm" placeholder="Recherche">
                           	<span class="input-group-btn">
                      			<button type="submit" class="btn waves-effect waves-light btn-info input-sm"><i class="fa fa-search"></i></button>
                      		</span>
                      		<span class="input-group-btn">
                      			<button data-toggle="collapse" data-target="#recherche" type="button" class="btn-outline btn waves-effect waves-light btn-info input-sm"><i class="fa fa-plus-circle"></i></button>
                      		</span>
                		  </div>
                        </form>
                        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
                            <li class="active">Agent distant</li>
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
                	<div class="col-sm-12 collapse" id="recherche">
                        <div class="white-box">
                        	<h3 class="box-title">Recherche</h3>
                            <div class="panel-body">
                            	<p class="text-muted">Recherche d'agents distants</p>   
                                <form action="${pageContext.request.contextPath}/Agent/search" class="form-horizontal form-material">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="col-md-5">
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">Nom</label>
                                                    <div class="col-md-9">
                                                        <input name="item.nom" value="<% out.print(item.getNom()); %>" type="text" class="form-control" placeholder="Nom de l'agent">
                                                    </div>
                                                </div>
                                            </div>
                                            <!--/span-->
                                            <div class="col-md-5">
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">Adresse</label>
                                                    <div class="col-md-9">
                                                        <input name="item.adresse" value="<% out.print(item.getAdresse()); %>" type="text" class="form-control" placeholder="Adresse"> <span class="help-block"> Adresse de la machine distante. </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--/span-->
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">Port</label>
                                                    <div class="col-md-9">
                                                        <input name="item.port" value="<% out.print(item.getPortString()); %>" type="text" class="form-control" placeholder="Port">
                                                     </div>
                                                </div>
                                            </div>
                                            <!--/span-->
                                        </div>
                                        <!--/row-->
                                        <div class="row">
                                            <div class="col-md-10">
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">Syntaxe</label>
                                                    <div class="col-md-9">
                                                        <input name="item.syntaxe" value="<% out.print(item.getSyntaxe()); %>" type="text" class="form-control" placeholder="Syntaxe"> <span class="help-block"> Syntaxe des lignes de logs. </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--/span-->
                                            <div class="col-md-2">
                                                <div class="form-group">
                                                    <div class="col-md-12">
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
                        <% if(user.isAllowed(PermissionType.CRUDAGENT)){ %>
                        <a href="${pageContext.request.contextPath}/Agent/new" class="btn btn-outline btn-info pull-right m-l-20 waves-effect waves-light"><i class="fa fa-plus fa-fw" aria-hidden="true"></i>Nouveau</a>
                        <% } %>
                            <h3 class="box-title">Liste</h3>
                            <p class="text-muted">Liste des agents distants enregistr&eacute;s</p>
                            <%  if(pagination == null || pagination.getListe() == null || pagination.getListe().isEmpty()){%>
                    		<p class="text-danger">Aucun agent trouv�</p>
                    		<%  }else{ %>
                            <div class="table-responsive">
                                <table class="table tablesorter">
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
                                    	<% for(BaseModel base : pagination.getListe()){ 
                                    		Agent i = (Agent)base; %>
                                        <tr>
                                            <td><% out.print(i.getId()); %></td>
                                            <td><% out.print(i.getNom()); %></td>
                                            <td><% out.print(i.getAdresse()); %></td>
                                            <td><% out.print(i.getPort()); %></td>
                                            <td><% out.print(i.getSyntaxe()); %></td>
                                            <td>
                                            	<a href="${pageContext.request.contextPath}/Agent/<% out.print(i.getId()); %>/connect" class="btn btn-success m-l-20 waves-effect waves-light"><i class="fa fa-plug fa-fw" aria-hidden="true"></i>Connexion</a>
                                            	<a href="${pageContext.request.contextPath}/Agent/<% out.print(i.getId()); %>" class="btn btn-primary m-l-20 waves-effect waves-light"><i class="fa fa-chevron-right" aria-hidden="true"></i></a>
                                            </td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                           <% if(pagination.getNombrePage()>1){ %>
	                            <ul class="pagination">
	                            <% for(int i=0; i<pagination.getNombrePage(); i++){ %>
								  <li <% if(pagination.getPage() == i)out.print("class=\"active\""); %>><a href="?<% out.print(query); %>page=<% out.print(i+1); %>"><% out.print(i+1); %></a></li>
								<% } %>
								</ul>
						  <%  }
                            } %>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
</body>
</html>
