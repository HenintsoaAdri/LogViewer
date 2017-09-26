<%@page import="adri.logviewer.model.GroupeView"%>
<%@page import="adri.logviewer.model.Groupe"%>
<%@page import="adri.logviewer.model.BaseModel"%>
<%@page import="adri.logviewer.model.BaseModelPagination"%>
<%@ include file="../includes/header.jsp" %>
<% BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination");
   Groupe item = (Groupe)request.getAttribute("item");
	String query = request.getQueryString() == null? "" : request.getQueryString();
	if(!query.isEmpty())query = query.replaceAll("&page=([0-9]*)", "") + "&"; %>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Groupe</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                    	<form action="${pageContext.request.contextPath}/Groupe/search" class="col-xs-4 pull-right">
                           <div class="input-group">
                           	<input value="<% out.print(item.getNom()); %>" type="text" name="item.nom" class="form-control input-sm" placeholder="Recherche">
                           	<span class="input-group-btn">
                      			<button type="submit" class="btn waves-effect waves-light btn-info input-sm"><i class="fa fa-search"></i></button>
                      		</span>
                		  </div>
                        </form>
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
                        <a href="${pageContext.request.contextPath}/Groupe/new" class="btn-outline btn btn-info pull-right m-l-20 waves-effect waves-light"><i class="fa fa-plus fa-fw" aria-hidden="true"></i>Nouveau</a>
                            <h3 class="box-title">Liste</h3>
                            <p class="text-muted">Liste des groupes enregistr&eacute;s</p>
                            <div class="table-responsive">
                            <%  if(pagination == null || pagination.getListe() == null ){%>
                    		<p class="text-danger">Aucun groupe trouvé</p>
                    		<%  }else{ %>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nom</th>
                                            <th>Nombre de profil</th>
                                            <th>Nombre d'agent</th>
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
                                            <td><% out.print(i.getNombreAgent()); %></td>
                                            <td><a href="${pageContext.request.contextPath}/Groupe/<% out.print(i.getId()); %>" class="btn btn-primary m-l-20 waves-effect waves-light"><i class="fa fa-chevron-right fa-fw" aria-hidden="true"></i>Voir le groupe</a></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                           		<% if(pagination.getNombrePage()>1){%>
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
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
