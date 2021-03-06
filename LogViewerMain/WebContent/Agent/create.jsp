<%@page import="adri.logviewermain.model.Groupe"%>
<%@page import="java.util.List"%>
<%@page import="adri.logviewermain.model.BaseModel"%>
<%@ include file="../includes/header.jsp" %>
<%@page import="adri.logviewermain.model.Agent"%>
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
                            <li class="active">Cr&eacute;er</li>
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
                            <h3 class="box-title">Nouvel agent</h3>
                            <p class="text-muted">Cr�er un nouvel agent distant</p>
                            <form class="form-horizontal form-material" method="post" action="${pageContext.request.contextPath}/Agent/save">
                            	<input type="hidden" name="item.createur.id" value="<% out.print(user.getId()); %>">
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
		                                <div class="form-group">
		                                    <label class="col-md-12">R�pertoire</label>
		                                    <div class="col-md-12">
		                                        <input name="item.repertoire" value="<% out.print(item.getRepertoire()); %>" type="text" placeholder="Chemin du r�pertoire des fichiers .log" class="form-control form-control-line">
		                                    </div>
		                                </div>
	                            	</div>
	                            	<div class="col-md-6">
		                                <div class="form-group">
		                                    <label class="col-md-12">Syntaxe</label>
		                                    <div class="col-md-12">
		                                        <input name="item.syntaxe" value="<% out.print(item.getSyntaxe()); %>" type="text" placeholder="Syntaxe des lignes de log" class="form-control form-control-line">
		                                    </div>
		                                </div>
	                            		<div class="form-group">
		                                    <label class="col-md-12">D&eacute;tails</label>
		                                    <div class="col-md-12">
		                                        <textarea name="item.details" rows="5" class="form-control form-control-line"><% out.print(item.getDetails()); %></textarea>
		                                    </div>
		                                </div>					           
			                           	<div class="form-group">
			                               <label class="col-md-12">Groupe</label>
			                               <div class="radio-list col-md-12">
			                                   <% if(liste != null){ 
			                                   	for(BaseModel g : liste){ 
			                                   	Groupe i = (Groupe)g;
			                                   %>
		                                       <div class="checkbox checkbox-success checkbox-circle">
		                                           <input type="checkbox" name="item.listeGroupe.id" id="groupe<% out.print(i.getId()); %>" value="<% out.print(i.getId()); %>" <% if(i.equals(user.getProfil().getGroupe().getId())) out.print("checked"); %>>
		                                           <label for="groupe<% out.print(i.getId()); %>"><% out.print(i.getNom()); %></label>
		                                       </div>
			                                   <% } 
			                                   } %>
			                               </div>
			                           	</div>
	                            	</div>
                            	</div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <button class="btn btn-success" type="submit">Cr&eacute;er</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>