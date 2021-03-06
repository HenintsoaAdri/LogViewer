<%@page import="adri.logviewermain.model.Groupe"%>
<%@ include file="../includes/header.jsp" %>
<%@page import="adri.logviewermain.model.Agent"%>
<% Agent item = (Agent)request.getAttribute("item"); %>
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
	            </div>
                <!--./row-->
				<% } %>
                <s:fielderror cssClass="alert alert-danger"/>
                <div class="row">
                    <div class="col-md-12">
                    	<div class="white-box">
                    		<h3 class="box-title">Agent #<% out.print(item.getId()); %></h3>
	                        <div class="form-horizontal">
	                            <div class="form-body">
	                            	<a href="${pageContext.request.contextPath}/Agent/edit/?item.id=<% out.print(item.getId()); %>" class="btn btn-info pull-right m-l-20 waves-effect waves-light"> <i class="fa fa-pencil"></i> Modifier</a>
	                                <p class="text-muted">D&eacute;tails de l'agent distant</p>
	                                <hr class="m-t-0 m-b-40">
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
	                                    <div class="col-md-4">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">R�pertoire:</label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getRepertoire()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-4">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Syntaxe:</label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getSyntaxe()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-4">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Cr�� le :</label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getDateCreationString()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                </div>
	                                <!--/row-->
	                                <div class="row">
	                                    <div class="col-md-4">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Groupe(s):</label>
	                                            <div class="col-md-9">
	                                            	<% for(Groupe g : item.getListeGroupe()){ %>
	                                                <p class="form-control-static"> <% out.print(g.getNom()); %> </p>
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
                            <h3 class="box-title">Logs</h3>
                        </div>
                	</div>
                </div>
                <!--./row-->
                <div class="row">
                    <div class="col-xs-12">
                        <a data-toggle="modal" href="#delete" data-item="Agent" data-id="<% out.print(item.getId()); %>" class="text-danger pull-right confirmDelete">Supprimer l'agent</a>
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