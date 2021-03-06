<%@page import="adri.logviewer.model.Groupe"%>
<%@page import="adri.logviewer.model.BaseModel"%>
<%@ include file="../includes/header.jsp" %>
<%@page import="adri.logviewer.model.Agent"%>
<% Agent item = (Agent)request.getAttribute("item"); 
List<? extends BaseModel> liste = (List<? extends BaseModel>)request.getAttribute("liste");
%>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Agent distant</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
                            <li><a href="${pageContext.request.contextPath}/Agent">Agent distant</a></li>
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
                            <h3 class="box-title">
                            	Agent #<% out.print(item.getId()); %>
                            </h3>
                            <p class="text-muted">Modifier un agent distant</p>
                            <form class="form-horizontal form-material" method="post" action="${pageContext.request.contextPath}/Agent/update">
                            	<input type="hidden" name="item.createur.id" value="<% out.print(item.getCreateur().getId()); %>">
                            	<input type="hidden" name="item.id" value="<% out.print(item.getId()); %>">
                            	<div class="row">
	                            	<div class="col-md-6">
		                            	<div class="form-group">
		                                    <label class="col-md-12">Nom</label>
		                                    <div class="col-md-12">
		                                        <input name="item.nom" value="<% out.print(item.getNom()); %>" type="text" placeholder="Nom" class="form-control form-control-line">
											</div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="col-md-12">Adresse</label>
		                                    <div class="col-md-12">
		                                        <input name="item.adresse" value="<% out.print(item.getAdresse()); %>" type="text" placeholder="Adresse de la machine distante" class="form-control form-control-line">
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="col-md-12">Port</label>
		                                    <div class="col-md-12">
		                                        <input name="item.port" value="<% out.print(item.getPort()); %>" type="text" class="form-control form-control-line">
		                                    </div>
		                                </div>
		                                <%  if(liste != null){ %>	           
			                           	<div class="form-group">
			                               <label class="col-md-12">Groupe</label>
			                               <div class="radio-list col-md-12">
			                                   <% 
			                                   	for(BaseModel p : liste){ 
			                                   	Groupe i = (Groupe)p;
			                                   %>
		                                       <div class="checkbox checkbox-success checkbox-circle">
		                                           <input type="checkbox" name="item.listeGroupe.id" id="groupe<% out.print(i.getId()); %>" value="<% out.print(i.getId()); %>" <% if(item.insideGroupe(i)) out.print("checked"); %>>
		                                           <label for="groupe<% out.print(i.getId()); %>"><% out.print(i.getNom()); %></label>
		                                       </div>
			                                   <% } %>
			                               </div>
			                           	</div>
			                           	<% } %>
	                            	</div>
	                            	<div class="col-md-6">
		                                <div class="form-group">
		                                    <label class="col-md-12">Syntaxe</label>
		                                    <div class="col-md-12">
		                                        <input name="item.syntaxe" value="<% out.print(item.getSyntaxe()); %>" type="text" placeholder="Syntaxe des lignes de log" class="form-control form-control-line">
		                                        <p class="text-muted">La syntaxe doit etre �crite suivant le format de vos fichiers logs avec tous les caract�res sp�ciaux </p>
		                                        	<ul class="text-muted">
		                                        		<li><b>TIMESTAMP{</b> format de la date selon le SimpleDateFormat <b>}</b> : pour le rep�re chronologique</li>
		                                        		<li><b>LEVEL</b> : pour la priorit� du log </li>
		                                        		<li><b>THREAD</b> : pour le thread execut�</li>
		                                        		<li><b>CLASS</b> : pour la classe</li>
		                                        		<li><b>METHOD</b> : pour la m�thode appel�e </li>
		                                        		<li><b>MESSAGE</b> : pour le message du log</li>
		                                        	</ul>
		                                        <p class="text-muted"> ex : TIMESTAMP{HH:mm:ss.SSS} THREAD LEVEL CLASS - MESSAGE
		                                        </p>
		                                    </div>
		                                </div>
	                            		<div class="form-group">
		                                    <label class="col-md-12">D&eacute;tails</label>
		                                    <div class="col-md-12">
		                                        <textarea name="item.details" rows="5" class="form-control form-control-line"><% out.print(item.getDetails()); %></textarea>
		                                    </div>
		                                </div>
	                            	</div>
                            	</div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button class="btn btn-success" type="submit">Enregistrer</button>
                                    </div>
                                </div>
                            </form>
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
</body>
</html>