<%@page import="adri.logviewer.filemanager.FilePaginate"%>
<%@page import="adri.logviewer.model.AgentView"%>
<%@page import="adri.logviewer.agent.file.LogFile"%>
<%@page import="adri.logviewer.filemanager.Log"%>
<%@page import="adri.logviewer.filemanager.Fichier"%>
<%@page import="java.io.File"%>
<%@ include file="../includes/header.jsp" %>
<% 
	AgentView item = (AgentView)request.getAttribute("item");
	LogFile file = (LogFile)request.getAttribute("log");
	FilePaginate fileLine = (FilePaginate) request.getAttribute("fileLine");
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
				        <h4 class="page-title">Agent</h4>
				    </div>
				    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
				            <li><a href="${pageContext.request.contextPath}/Agent">Agent</a></li>
				            <li><a href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/connect"><% out.print(item.getNom()); %></a></li>
				            <li class="active">Lecture de fichier</li>
				        </ol>
				    </div>
				    <!-- /.col-lg-12 -->
				</div>
				<div class="row">
				    <div class="col-md-12">
				        <div class="white-box">
				        	<div class="btn-group pull-right">
								<a class="btn btn-success" href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/parse?log.fileName=<% out.print(file.getFileName()); %>"><i class="fa fa-indent fw"></i> Parser le fichier</a>
								<a class="btn-outline btn btn-success" href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/download?log.fileName=<% out.print(file.getFileName()); %>"><i class="fa fa-download fw"></i> TÚlÚcharger le fichier</a>
								<a class="btn btn-success" href="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/save?log.fileName=<% out.print(file.getFileName()); %>"><i class="fa fa-save fw"></i> Enregistrer le fichier</a>
				        	</div>
							<h3 class="box-title">
								<i class="fa fa-file-text-o" aria-hidden="true"></i> <% out.print(file.getFileName()); %>
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
								<div class="col-md-3 col-md-offset-9 ">
									<form action="${pageContext.request.contextPath}/Agent/<% out.print(item.getId()); %>/view">
			                           <input type="hidden" name="log.fileName" value="<% out.print(request.getAttribute("file")); %>">
			                        	<div class="input-group">
			                        		<input value="<% try{ out.print(fileLine.getSearch());}catch(Exception e){} %>" type="text" name="search" class="form-control input-sm" placeholder="Recherche">
			                           		<span class="input-group-btn">
			                      				<button type="submit" class="btn waves-effect waves-light btn-info input-sm"><i class="fa fa-search"></i></button>
			                      			</span>
										</div>
				                	</form>
								</div>
								<hr>
									<pre class="frame"><% out.print(fileLine.getLine()); %></pre>
									<ul class="pagination">
									<%
										if(fileLine.getNumberPage()> 1){
											for(int i = 0; i < fileLine.getNumberPage() ; i++){
										  		if(i == 0 || i+1 == fileLine.getNumberPage() || fileLine.getBeginLine()-2 <= i && i <= fileLine.getBeginLine()+2){ %>
										  		<li <% if(i==fileLine.getBeginLine()) out.print("class=\"active\""); %>>
												  	<a href="${pageContext.request.contextPath}/Fichier?file=<% out.print(request.getAttribute("file")); %>&amp;page=<% out.print(i); %>">
												  		<% out.print((i*fileLine.getMaxLine())+" - "+((i*fileLine.getMaxLine())+fileLine.getMaxLine())); %>
											  		</a>
									  			</li>
										  		<% }else if(i == fileLine.getMaxLine() - 3 || i == fileLine.getMaxLine() + 3){ %>
										  		<li>
											  		<a href="${pageContext.request.contextPath}/Fichier?file=<% out.print(request.getAttribute("file")); %>&amp;page=<% out.print(i); %>">
												  		...
											  		</a>
											  	</li>
										  		<% }
										  	}
								  	 	}%>
									</ul>
								</div>
				           </div>
				        </div>
				    </div>
				</div>
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
</body>
</html>