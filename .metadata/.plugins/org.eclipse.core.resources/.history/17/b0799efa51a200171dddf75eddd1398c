<%@page import="adri.logviewer.model.AgentView"%>
<%@page import="adri.logviewer.agent.file.LogFile"%>
<%@page import="adri.logviewer.filemanager.Log"%>
<%@page import="adri.logviewer.filemanager.Fichier"%>
<%@page import="java.io.File"%>
<%@ include file="../includes/header.jsp" %>
<% 
	AgentView item = (AgentView)request.getAttribute("item");
	LogFile file = (LogFile)request.getAttribute("log");
%>
<style>
.frame{
	height : 85vh;
	border : 1px solid #ccc;
	overflow: auto;
}
</style>
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
								<a class="btn btn-success" href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/parse?log.fileName=<% out.print(file.getFileName()); %>&file=<% out.print(file.getTempFile().getName()); %>"><i class="fa fa-indent fw"></i> Parser le fichier</a>
								<a class="btn-outline btn btn-success" href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/download?log.fileName=<% out.print(file.getFileName()); %>&file=<% out.print(file.getTempFile().getName()); %>"><i class="fa fa-download fw"></i> Télécharger le fichier</a>
								<a class="btn btn-success" href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/save?log.fileName=<% out.print(file.getFileName()); %>&file=<% out.print(file.getTempFile().getName()); %>"><i class="fa fa-save fw"></i> Enregistrer le fichier</a>
				        	</div>
							<h3 class="box-title">
								<i class="fa fa-file-text-o" aria-hidden="true"></i> <% out.print(file); %>
							</h3>
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
					            	}catch(NullPointerException e1){}
					      		%></p>
				                <!--./row-->
								<% } %>
									<%@ include file="../includes/DisplayFile.jsp" %>
									<ul class="pagination">
									<% int numberPage = (int) Math.ceil(total/100f);
										if(numberPage > 1){
											for(int i = 0; i < numberPage ; i++){ 
												if(i == 0 || i+1 == numberPage || currentPage-2 <= i && i <= currentPage+2){ %>
										  		<li <% if(i==currentPage) out.print("class=\"active\""); %>>
												  	<a href="${pageContext.request.contextPath}/Fichier?log.fileName=<% out.print(file.getFileName()); %>&amp;file=<% out.print(file.getTempFile().getName()); %>&amp;page=<% out.print(i); %>">
												  		<% out.print((i*100)+" - "+((i*100)+100)); %>
											  		</a>
									  			</li>
										  		<% }else if(i == currentPage - 3 || i == currentPage + 3){ %>
										  		<li>
											  		<a href="${pageContext.request.contextPath}/Fichier?log.fileName=<% out.print(file.getFileName()); %>&amp;file=<% out.print(file.getTempFile().getName()); %>&amp;page=<% out.print(i); %>">
												  		...
											  		</a>
										  		</li>
										  		<% }
									  		}
									  	} %>
									</ul>
								</div>
				           </div>
				        </div>
				    </div>
				</div>
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>