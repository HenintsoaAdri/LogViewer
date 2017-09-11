<%@page import="adri.logviewer.agent.file.LogFile"%>
<%@page import="adri.logviewer.model.BaseModelPagination"%>
<%@page import="adri.logviewer.model.BaseModel"%>
<%@page import="adri.logviewer.model.GroupeView"%>
<%@ include file="../includes/header.jsp" %>
<%@page import="adri.logviewer.model.AgentView"%>
<% AgentView item = (AgentView)request.getAttribute("item"); 
List<LogFile> listeLog = (List<LogFile>)request.getAttribute("listeLog");
%>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Agent distant</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
                            <li><a href="${pageContext.request.contextPath}/Agent">Agent distant</a></li>
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
					<a href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/connect" class="btn btn-default m-l-20 waves-effect waves-light"><i class="fa fa-plug fa-fw" aria-hidden="true"></i>Reconnexion</a>
	            </div>
                <!--./row-->
				<% } %>
                <s:fielderror cssClass="alert alert-danger"/>
                <div class="row">
                    <div class="col-md-12">
                    	<div class="white-box">
                    		<h3 class="box-title"><i class="fa fa-gear"></i> Agent #<% out.print(item.getId()); %></h3>
	                        <div class="form-horizontal">
	                            <div class="form-body">
                        			<% if(user.isAllowed(PermissionType.CRUDAGENT)){ %>
	                            	<a href="${pageContext.request.contextPath}/Agent/edit/<% out.print(item.getId()); %>" class="btn btn-info pull-right m-l-20 waves-effect waves-light"> <i class="fa fa-pencil"></i> Modifier</a>
	                            	<% } %>
	                                <p class="text-muted">D&eacute;tails de l'agent distant</p>
	                                <hr class="m-t-0 m-b-20">
	                                <div class="row">
	                                    <div class="col-md-4">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Nom:</label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getNom()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-4">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Adresse:</label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getAdresse()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-4">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Port:</label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getPort()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                </div>
	                                <!--/row-->
	                                <div class="row">
	                                    <div class="col-md-6">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Syntaxe:</label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getSyntaxe()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-6">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Cr�� le :</label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getDateCreationString()); %> </p>
	                                            </div>
	                                            <label class="control-label col-md-3">par </label>
	                                            <div class="col-md-9">
	                                                 <p class="form-control-static"><% out.print(item.getNomCreateur()); %></p>
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
                            <h3 class="box-title"><i class="fa fa-gears"></i> Fichiers log</h3>
                            <p class="text-muted">Liste des fichiers de log retrouv�s</p>
	                        <hr class="m-t-0 m-b-10">
                            <% if(listeLog == null || listeLog.isEmpty() ){ %>
						    	<p class="text-danger">Aucun fichier n'a �t� retourn� par cet agent</p>
							<% }else{ %>
	                            <ul class="list-group list-inline">
	                            <%	for(LogFile i : listeLog){  %>
	                              <li class="text-center p-20"><a href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/open?file=<% out.print(i.getDistantName()); %>"><i class="fa fa-file text-success"></i>
				                      <p><% out.print(i.getDistantName()); %></p>
	                              	  </a>
	                              </li>
	                            <% } %>
	                            </ul>
                           <% } %>
                        </div>
                	</div>
                </div>
                <!--./row-->
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/function.js"></script>