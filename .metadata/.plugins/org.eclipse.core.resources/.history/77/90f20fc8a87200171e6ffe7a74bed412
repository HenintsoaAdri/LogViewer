<%@page import="adri.logviewermain.model.GroupeView"%>
<%@page import="adri.logviewermain.model.Groupe"%>
<%@page import="adri.logviewermain.model.BaseModel"%>
<%@page import="adri.logviewermain.model.BaseModelPagination"%>
<%@ include file="../includes/header.jsp" %>
<% BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination"); %>
<% Groupe item = (Groupe)request.getAttribute("item"); %>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Groupe</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
                            <li class="active">Groupe</li>
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
                            <h3 class="box-title">Recherche</h3>
                            <p class="text-muted">Recherche de groupe</p>
                            <div class="panel-body">
                                <form action="#" class="form-horizontal form-material">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">Nom</label>
                                                    <div class="col-md-9">
                                                        <input name="item.nom" value="<% out.print(item.getNom()); %>" type="text" class="form-control" placeholder="Nom de l'agent">
                                                    </div>
                                                </div>
                                            </div>
                                            <!--/span-->
                                            <div class="col-md-offset-2 col-md-4">
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
                        <a href="${pageContext.request.contextPath}/Groupe/new" class="btn btn-info pull-right m-l-20 waves-effect waves-light"><i class="fa fa-plus fa-fw" aria-hidden="true"></i>Nouveau</a>
                            <h3 class="box-title">Liste</h3>
                            <p class="text-muted">Liste des groupes enregistr&eacute;s</p>
                            <div class="table-responsive">
                            <%  if(pagination == null || pagination.getListe() == null ){%>
                    		<p class="text-danger">Aucun groupe enregistré</p>
                    		<%  }else{ %>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nom</th>
                                            <th>Nombre de profil</th>
                                            <th>Nombre d'utilisateur</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<% for(BaseModel base : pagination.getListe()){ 
                                    		GroupeView i = (GroupeView)base;
                                    	%>
                                        <tr>
                                            <td><% out.print(i.getId()); %></td>
                                            <td><% out.print(i.getNom()); %></td>
                                            <td><% out.print(i.getNombreProfil()); %></td>
                                            <td><% out.print(i.getNombreUtilisateur()); %></td>
                                            <td><a href="${pageContext.request.contextPath}/Groupe/?item.id=<% out.print(i.getId()); %>" class="btn btn-success m-l-20 waves-effect waves-light"><i class="fa fa-chevron-right fa-fw" aria-hidden="true"></i>Voir le groupe</a></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                                <% } %>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
