<%@page import="adri.logviewermain.model.BaseModel"%>
<%@page import="adri.logviewermain.model.Agent"%>
<%@page import="adri.logviewermain.model.BaseModelPagination"%>
<%@ include file="../includes/header.jsp" %>
<% BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination"); 
	Agent item = (Agent) request.getAttribute("item"); %>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Agent distant</h4>
                    </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
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
                	<div class="col-sm-12">
                        <div class="white-box">
                       		<a href="#recherche" data-toggle="collapse" class="pull-right">
                       			<i class="fa fa-plus fw"></i>
                       		</a>
                        	<h3 class="box-title">Recherche</h3>          
                            <div class="panel-body collapse" id="recherche">
                            	<p class="text-muted">Recherche d'agents distants</p>   
                                <form action="#" class="form-horizontal form-material">
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
                                                        <input name="item.port" value="<% out.print(item.getPort()); %>" type="text" class="form-control" placeholder="Port">
                                                     </div>
                                                </div>
                                            </div>
                                            <!--/span-->
                                        </div>
                                        <!--/row-->
                                        <div class="row">
                                            <div class="col-md-5">
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">Répertoire</label>
                                                    <div class="col-md-9">
                                                        <input name="item.repertoire" value="<% out.print(item.getRepertoire()); %>" type="text" class="form-control" placeholder="Chemin">
										</div>
                                                </div>
                                            </div>
                                            <!--/span-->
                                            <div class="col-md-5">
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
                        <% if(user.isGenerallyAllowed("Agent")){ %>
                        <a href="${pageContext.request.contextPath}/Agent/new" class="btn btn-info pull-right m-l-20 waves-effect waves-light"><i class="fa fa-plus fa-fw" aria-hidden="true"></i>Nouveau</a>
                        <% } %>
                            <h3 class="box-title">Liste</h3>
                            <p class="text-muted">Liste des agents distants enregistr&eacute;s</p>
                            <div class="table-responsive">
                            <%  if(pagination == null || pagination.getListe() == null || pagination.getListe().isEmpty()){%>
                    		<p class="text-danger">Aucun agent enregistré</p>
                    		<%  }else{ %>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nom</th>
                                            <th>Adresse</th>
                                            <th>Port</th>
                                            <th>Répertoire</th>
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
                                            <td><% out.print(i.getRepertoire()); %></td>
                                            <td><% out.print(i.getSyntaxe()); %></td>
                                            <td>
                                            	<a href="" class="btn btn-success m-l-20 waves-effect waves-light"><i class="fa fa-plug fa-fw" aria-hidden="true"></i>Connexion</a>
                                            	<a href="${pageContext.request.contextPath}/Agent/?item.id=<% out.print(i.getId()); %>" class="btn btn-info m-l-20 waves-effect waves-light"><i class="fa fa-chevron-right fa-fw" aria-hidden="true"></i>Voir l'agent</a>
                                            </td>
                                        </tr>
                                        <% }
                                    	} %>
                                    </tbody>
                                </table>
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
