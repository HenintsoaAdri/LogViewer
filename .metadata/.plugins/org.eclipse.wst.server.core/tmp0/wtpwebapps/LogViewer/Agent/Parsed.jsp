<%@page import="adri.logviewer.exception.InputException"%>
<%@page import="adri.logviewer.model.AgentView"%>
<%@page import="adri.logviewer.agent.file.LogFile"%>
<%@page import="adri.logviewer.filemanager.Log"%>
<%@page import="adri.logviewer.filemanager.Fichier"%>
<%@page import="java.io.File"%>
<%@ include file="../includes/header.jsp" %>
<style>
.table-responsive{
	height:75vh;
	overflow: auto;
}
</style>
<% 	AgentView item = (AgentView)request.getAttribute("item"); 
	LogFile logFile = (LogFile)request.getAttribute("log");
	Fichier file = (Fichier)request.getAttribute("fichier");
	String query = request.getQueryString() == null? "" : request.getQueryString();
	if(!query.isEmpty())query = query.replaceAll("&page=([0-9]*)", "") + "&"; %>
            <div class="container-fluid">
				<div class="row bg-title">
				    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				        <h4 class="page-title">Fichiers</h4>
				    </div>
				    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
				            <li class="active"><a href="${pageContext.request.contextPath}/Fichier">Fichiers</a></li>
				        </ol>
				    </div>
				    <!-- /.col-lg-12 -->
				</div>
				<div class="row">
				    <div class="col-md-12">
				        <div class="white-box">
				        	<div class="btn-group pull-right">
								<a class="btn btn-success" href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/view?log.fileName=<% out.print(logFile.getFileName()); %>&file=<% out.print(logFile.getTempFile().getName()); %>"><i class="fa fa-outdent fw"></i> Visionner le fichier</a>
								<a class="btn-outline btn btn-success" href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/download?log.fileName=<% out.print(logFile.getFileName()); %>&file=<% out.print(logFile.getTempFile().getName()); %>"><i class="fa fa-download fw"></i> Télécharger le fichier</a>
								<a class="btn btn-success" href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/save?log.fileName=<% out.print(logFile.getFileName()); %>&file=<% out.print(logFile.getTempFile().getName()); %>"><i class="fa fa-save fw"></i> Enregistrer le fichier</a>
							</div>
							<h3 class="box-title">
								<i class="fa fa-file-text-o" aria-hidden="true"></i> <% out.print(logFile.getFileName()); %>
							</h3>
							<p class="text-muted">Syntaxe : <% out.print(file.getMainPattern()); %>	
							</p>
							<hr class="m-t-0 m-b-20">
							<div class="row">
								<div class="col-sm-12">	
							        <% if(request.getAttribute("exception") != null){ 
							      		Exception e = (Exception)request.getAttribute("exception");
							        %>
							      	<p class="text-danger"><% 
						      			try{
						      				out.print(e.getMessage());
						            		out.print(e.getCause().getMessage());
						      				if(e instanceof InputException){ %>
						      					<a>Forcer le parsage</a>
						      			<%	}
						            	}catch(NullPointerException e1){}
						      		%></p>
					                <!--./row-->
									<% } %>	
		                            <%  if(file == null || file.getPagination().getListeLog() == null || file.getPagination().getListeLog().isEmpty()){ %>		                            %>
		                    		<p class="text-danger">Aucune ligne de log retrouvée.</p>
		                    		<%  }else{ %>
		                            <div class="table-responsive">
		                                <table class="table">
		                                    <thead>
		                                        <tr>
		                                        	<th>Ligne</th>
		                                            <th>Date</th>
		                                            <th>Priorité</th>
		                                            <th>Thread</th>
		                                            <th>Classe</th>
		                                            <th>Méthode</th>
		                                            <th>Message</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody class="frame">
		                                    	<% for(Log i : file.getPagination().getListeLog()){ %>
		                                        <tr>
		                                        	<td><% out.print(i.getLine()); %></td>
		                                            <td><% out.print(i.getDateString()); %></td>
		                                            <td><% out.print(i.getPriority()); %></td>
		                                            <td><% out.print(i.getThread()); %></td>
		                                            <td><% out.print(i.getClasse()); %></td>
		                                            <td><% out.print(i.getMethod()); %></td>
		                                            <td><% out.print(i.getMessage()); %></td>
		                                        </tr>
		                                        <% if(!(i.getDetails() == null || i.getDetails().isEmpty())){ %>
		                                        <tr>
		                                        	<td colspan="7"><a data-toggle="collapse" href="#<% out.print(i.hashCode()); %>"><b>Détails</b></a>
			                                            <div id="<% out.print(i.hashCode()); %>" class="collapse">
			                                            	<pre><% String details = i.getDetails().replaceAll("&", "&amp;");
			                                            			details = details.replace(">", "&gt;");
			                                            			details = details.replace("<", "&lt;");
			                                            			out.print(details); %></pre>
			                                            </div>
		                                            </td>
		                                        </tr>
		                                        <% } 
		                                        }
		                                    %>
		                                    </tbody>
		                                </table>
		                            </div>
		                            <ul class="pagination">
		                            <% if(!file.getPagination().isStart()){ %>
		                            	<li><a href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/parse?<% out.print(query); %>&page=<% out.print(file.getPagination().getPreviewStart()); %>">&lt; Précédent</a></li>
		                            <% } %>
		                            	<li><a href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/parse?<% out.print(query); %>&page=<% out.print(file.getPagination().getCurrentEnd()+1); %>">Suivant &gt; </a></li>
		                            </ul>
		                        <% } %>
								</div>
				           </div>
				        </div>
				    </div>
				</div>
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>