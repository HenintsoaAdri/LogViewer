<%@page import="adri.logviewer.model.Permission"%>
<%@page import="adri.logviewer.model.Groupe"%>
<%@page import="adri.logviewer.model.BaseModel"%>
<%@page import="adri.logviewer.model.Profil"%>
<%@ include file="../includes/header.jsp" %>
<% Profil item = (Profil)request.getAttribute("item"); 
List<? extends BaseModel> liste = (List<? extends BaseModel>)request.getAttribute("liste");
%>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Profil</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
                            <li><a href="${pageContext.request.contextPath}/Profil">Profil</a></li>
                            <li class="active">Modifier</li>
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
                <s:fielderror cssClass="alert alert-danger list-unstyled"/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h3 class="box-title m-b-0">
                            	Profil #<% out.print(item.getId()); %>
                            </h3>
                            <p class="text-muted">Modifier un groupe d'utilisateur</p>
                            <form action="${pageContext.request.contextPath}/Profil/update" class="form-horizontal form-material" method="post">
                            	<input type="hidden" name="item.id" value="<% out.print(item.getId()); %>">
                                <div class="form-body">
                                    <hr class="m-t-0 m-b-40">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Nom</label>
                                                <div class="col-md-9">
                                                    <input name="item.nom" value="<% out.print(item.getNom()); %>" type="text" class="form-control" placeholder="Nom du profil">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                	<label class="control-label col-md-3">Description</label>
                                                <div class="col-md-9">
		                                        	<textarea rows="5" class="form-control form-control-line"><% out.print(item.getDescription()); %></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Permissions</label>
                                                <div class="col-md-9">
                                                	<% for(Permission p : user.getProfil().getListePermission()) { %>
                                                    <div class="checkbox checkbox-success checkbox-circle">
				                                         <input id="permission<% out.print(p.getPermission().ordinal()); %>" type="checkbox" name="item.listePermission.permission" value="<% out.print(p.getPermission().name()); %>" <% if(item.hasPermission(p)) out.print("checked"); %>>
				                                         <label for="permission<% out.print(p.getPermission().ordinal()); %>"> <% out.print(p.getPermission().getLibelle()); %> </label>
				                                     </div>
                                                	<% } %>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <h3 class="box-title m-b-0"><i class="fa fa-gears fa-fw" aria-hidden="true"></i> Groupes</h3>
                            		<p class="text-muted">Rajouter / Retirer les groupes � g�rer</p>
                                    <hr class="m-t-0 m-b-40">
                                    <div class="row">
                                        <div class="col-md-12">				           
				                           	<div class="form-group">
				                               <div class="radio-list col-md-9">
				                                   <% if(liste != null){
				                                	   for(BaseModel g : liste){ 
				                                   	Groupe i = (Groupe)g;
				                                   %>
			                                       <div class="checkbox checkbox-primary checkbox-circle">
			                                           <input type="checkbox" name="item.listeGroupe.id" id="groupe<% out.print(i.getId()); %>" value="<% out.print(i.getId()); %>" <% if(item.hasGroupe(i)) out.print("checked"); %>>
			                                           <label for="groupe<% out.print(i.getId()); %>"><% out.print(i.getNom()); %></label>
			                                       </div>
				                                   <% }
				                                   } %>
				                               </div>
				                           	</div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <div class="col-md-offset-8 col-md-4">
                                                    <button type="submit" class="btn btn-success">Modifier</button>
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
</body>
</html>