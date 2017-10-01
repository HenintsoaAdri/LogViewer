<%@page import="java.text.ParseException"%>
<%@page import="javafx.scene.control.Pagination"%>
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
<%  String fileName = (String)request.getAttribute("file");
	Fichier file = (Fichier)request.getAttribute("fichier");
	String query = request.getQueryString() == null? "" : request.getQueryString();
	String previous = (String)request.getAttribute("previous");
	if(!query.isEmpty())query = query.replaceAll("&page=([0-9]*)", "") + "&"; %>
        <div class="container-fluid">
				<div class="row bg-title">
				    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<%  try{
							if(fileName != previous){ %>
							<a href="${pageContext.request.contextPath}/Fichier?file=<%out.print(previous);%>" class="btn btn-default btn-outline fa-1x"><i class="fa fa-caret-left fw"></i> <% out.print(previous); %></a>
						<% }
						}catch(Exception e){
							e.printStackTrace(); %>
				        <h4 class="page-title">Fichiers</h4>
					<% } %>
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
								<a class="btn btn-success" href="${pageContext.request.contextPath}/Fichier?file=<% out.print(fileName); %>"><i class="fa fa-outdent fw"></i> Visionner</a>
								<a class="btn-outline btn btn-success" href="${pageContext.request.contextPath}/Fichier/download?file=<% out.print(fileName); %>"><i class="fa fa-download fw"></i> T�l�charger</a>
							</div>
							<h3 class="box-title">
								<i class="fa fa-file-text-o" aria-hidden="true"></i> <% out.print(fileName); %>
							</h3>
						<% try{ 
							String pattern = file.getMainPattern();
						%>
							<p class="text-muted">Syntaxe : <% out.print(pattern); %></p>
							<hr class="m-t-0 m-b-20">
							<div class="btn-group">
							  <a href="${pageContext.request.contextPath}/Fichier/parse?file=<% out.print(fileName); %>&level=FATAL" class="level btn btn-outline btn-danger">FATAL</a>
							  <a href="${pageContext.request.contextPath}/Fichier/parse?file=<% out.print(fileName); %>&level=ERROR" class="level btn btn-outline btn-danger">ERROR</a>
							  <a href="${pageContext.request.contextPath}/Fichier/parse?file=<% out.print(fileName); %>&level=WARN" class="level btn btn-outline btn-warning">WARN</a>
							  <a href="${pageContext.request.contextPath}/Fichier/parse?file=<% out.print(fileName); %>&level=INFO" class="level btn btn-outline btn-info">INFO</a>
							  <a href="${pageContext.request.contextPath}/Fichier/parse?file=<% out.print(fileName); %>&level=DEBUG" class="level btn btn-outline btn-primary">DEBUG</a>
							  <a href="${pageContext.request.contextPath}/Fichier/parse?file=<% out.print(fileName); %>&level=TRACE" class="level btn btn-outline btn-success">TRACE</a>
							</div>
							<div class="row">
								<div class="col-sm-12">
		                            <%  if(file == null || file.getPagination().getListeLog() == null || file.getPagination().getListeLog().isEmpty()){%>
		                    		<p class="text-danger">Aucune ligne de log retrouv�e.</p>
		                    		<%  }else{ %>
		                            <div class="table-responsive">
		                                <table class="table">
		                                    <thead>
		                                        <tr>
		                                        	<th>Ligne</th>
		                                            <th>Date</th>
		                                            <th>Priorit�</th>
		                                            <th>Thread</th>
		                                            <th>Classe</th>
		                                            <th>M�thode</th>
		                                            <th>Message</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
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
		                                        	<td colspan="7"><a data-toggle="collapse" href="#<% out.print(i.hashCode()); %>"><b>D�tails</b></a>
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
		                            	<li><a href="${pageContext.request.contextPath}/Fichier/parse?<% out.print(query); %>page=<% out.print(file.getPagination().getPreviewStart()); %>">&lt; Pr�c�dent</a></li>
		                            <% } %>
		                            	<li><a href="${pageContext.request.contextPath}/Fichier/parse?<% out.print(query); %>page=<% out.print(file.getPagination().getCurrentEnd()+1); %>">Suivant &gt;</a></li>
		                            </ul>
		                        <% } %>
								</div>
				           </div>
				           <% }catch(NullPointerException ex){ %>
				           		<p class="text-danger">Parsage impossible.	
						        <% if(request.getAttribute("exception") != null){ 
						      		Exception e = (Exception)request.getAttribute("exception");
						        %>
						      	<p class="text-danger"><% 
					      			try{
					      				out.print(e.getMessage());
					            		out.print(e.getCause().getMessage());
					            		out.print(e.getClass());
					            	}catch(NullPointerException e1){}
						      		if(e instanceof ParseException){ %>
			      					<a class="btn btn-default" href="${pageContext.request.contextPath}/Fichier/parse?file=<% out.print(fileName); %>&force=true">Forcer le parsage</a>
			      				<%	} %>
			      				</p>
				                <!--./row-->
								<% } %>	
				           <% } %>
				        </div>
				    </div>
				</div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-xs-12">
                        <a data-toggle="modal" href="#delete" class="text-danger pull-right">Supprimer le fichier</a>
                    </div>
                </div>
                <!-- /.row -->
			    <!-- Modal -->
			    <div class="modal fade" id="delete" role="dialog">
			        <div class="modal-dialog">
			
			            <!-- Modal content-->
			            <div class="modal-content">
			                <div class="modal-header">
			                    <button type="button" class="close" data-dismiss="modal">&times;</button>
			                </div>
			                <div class="modal-body">
			                    <div class="row">
			                        <div class="col-sm-12">
			                            <p class="text-center">&Ecirc;tes vous s�r(e) de vouloir supprimer le fichier "<% out.print(fileName); %>" ?</p>
			                            <br>
			                        </div>
			                        <div class="col-sm-12 text-center">
			                            <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
			                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/Fichier/delete?file=<% out.print(fileName); %>">Supprimer</a>
			                        </div>
			                    </div>
			                </div>
			            </div>
			
			        </div>
			    </div>
                <!-- /.Modal -->
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
<script type="text/javascript">
	var level = '<% out.print(request.getAttribute("level")); %>';
	$('.level').each(function(){
		if($(this).html() == level){
			$(this).removeClass('btn-outline');
			href = $(this).attr("href");
			$(this).attr("href", href.substring(0,href.indexOf('&level')));
		}
	})
</script>