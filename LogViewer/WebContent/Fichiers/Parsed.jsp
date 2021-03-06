<%@page import="java.text.ParseException"%>
<%@page import="javafx.scene.control.Pagination"%>
<%@page import="adri.logviewer.filemanager.SampleLog"%>
<%@page import="adri.logviewer.filemanager.Log"%>
<%@page import="adri.logviewer.filemanager.Fichier"%>
<%@page import="java.io.File"%>
<%@ include file="../includes/header.jsp" %>
<%  String fileName = (String)request.getAttribute("file");
	Fichier file = (Fichier)request.getAttribute("fichier");
	SampleLog event = (SampleLog)request.getAttribute("event");
	String previous = (String)request.getAttribute("previous"); %>
<style>
.table-responsive{
	height:75vh;
	overflow: auto;
}
</style>
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
							<div class="row">
							<div class="col-sm-12">
								<form action="${pageContext.request.contextPath}/Fichier/parse">
									<input type="hidden" name="file" value="<% out.print(fileName); %>"/>
									<div class="form-body">	
										<div class="row">
			                           	  <div class="col-md-12">
			                           	  	<div class="row">
				                           	  	<div class="col-md-3">
			                                        <div class="checkbox checkbox-circle">
													  	<input id="traceLevel" name="event.level" type="checkbox" value="TRACE">
			                                            <label for="traceLevel">TRACE</label>
			                                        </div>
			                                        <div class="checkbox checkbox-circle checkbox-danger">
													  	<input id="fatalLevel" name="event.level" type="checkbox" value="FATAL">
			                                            <label for="fatalLevel">FATAL</label>
			                                        </div>
		                                        </div>
				                           	  	<div class="col-md-3">
			                                        <div class="checkbox checkbox-circle checkbox-inverse">
													  	<input id="debugLevel" name="event.level" type="checkbox" value="DEBUG">
			                                            <label for="debugLevel">DEBUG</label>
			                                        </div>
			                                        <div class="checkbox checkbox-circle checkbox-purple">
													  	<input id="errorLevel" name="event.level" type="checkbox" value="ERROR">
			                                            <label for="errorLevel">ERROR</label>
			                                        </div>
		                                        </div>
				                           	  	<div class="col-md-3">
			                                        <div class="checkbox checkbox-circle checkbox-info">
													  	<input id="infoLevel" name="event.level" type="checkbox" value="INFO">
			                                            <label for="infoLevel">INFO</label>
			                                        </div>
			                                        <div class="checkbox checkbox-circle checkbox-warning">
													  	<input id="warnLevel" name="event.level" type="checkbox" value="WARN">
			                                            <label for="warnLevel">WARN</label>
			                                        </div>
		                                        </div>
			                                    <div class="col-md-3">
			                           				<input value="<% try{ out.print(event.getSearch());}catch(Exception e){} %>" type="text" name="event.search" class="form-control col-md-12" placeholder="Recherche">
			                           				<button type="submit" class="btn btn-info col-md-12">Rechercher</button>
					                           	</div>
	                                        </div>
	                                      </div>
										</div>
									</div>
			                      </form>
								</div>
							</div>
						<% try{ 
							String pattern = file.getMainPattern();
						%>
							<p class="text-muted">Syntaxe : <% out.print(pattern); %></p>
							<hr class="m-t-0 m-b-20">
							<div class="row">
								<div class="col-sm-12">
		                            <%  if(file == null || file.getPagination().getListeLog() == null || file.getPagination().getListeLog().isEmpty()){%>
		                    		<p class="text-danger">Aucune ligne de log retrouv�e.</p>
		                    		<%  }else{ %>
		                            <div class="table-responsive">
		                                <table class="table tablesorter">
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
		                                        <tr class="<% out.print(i.getPriority()); %>">
		                                        	<td><% out.print(i.getLine()); %></td>
		                                            <td><% out.print(i.getDateString()); %></td>
		                                            <td><% out.print(i.getPriority()); %></td>
		                                            <td><% out.print(i.getThread()); %></td>
		                                            <td><% out.print(i.getClasse()); %></td>
		                                            <td><% out.print(i.getMethod()); %></td>
		                                            <td><% out.print(i.getMessage()); %></td>
		                                        </tr>
		                                        <% if(i.getDetails() != null && !i.getDetails().isEmpty()){ %>
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
	$('.ERROR').addClass('danger');
	$('.FATAL').addClass('danger');
	$('.WARN').addClass('warning');
</script>
</body>
</html>