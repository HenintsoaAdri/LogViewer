<%@page import="adri.logviewer.agent.file.LogFile"%>
<%@page import="adri.logviewer.filemanager.Log"%>
<%@page import="adri.logviewer.filemanager.Fichier"%>
<%@page import="java.io.File"%>
<%@ include file="../includes/header.jsp" %>
<% 
	String file = (String)request.getAttribute("file");
	File path = (File)request.getAttribute("path");
%>
<style>
.frame{
	height : 80vh;
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
								<a class="btn btn-success" href="${pageContext.request.contextPath}/Fichier/parse?file=<% out.print(file); %>"><i class="fa fa-indent fw"></i> Parser</a>
								<a class="btn-outline btn btn-success" href="${pageContext.request.contextPath}/Fichier/download?file=<% out.print(file); %>"><i class="fa fa-download fw"></i> TÚlÚcharger</a>
				        	</div>
							<h3 class="box-title">
								<i class="fa fa-file-text-o" aria-hidden="true"></i> <% out.print(file); %> (<% out.print(path.length()/(1024*1024)); %> Mo)
							</h3>
							<hr class="m-t-0 m-b-20">
							<div class="row">
								<div class="col-sm-12">	
						        <% if(request.getAttribute("exception") != null){ 
						      		Exception e = (Exception)request.getAttribute("exception"); %>
						      	<p class="text-danger"><% 
					      			try{
					      				out.print(e.getMessage());
					            		out.print(e.getCause().getMessage());
					            	}catch(NullPointerException e1){}
					      		%></p>
				                <!--./row-->
								<% } %>
									<%@include file="../includes/DisplayFile.jsp"  %>
									<ul class="pagination">
									<% int numberPage = (int) Math.ceil(total/100f);
										if(numberPage > 1){
											for(int i = 0; i < numberPage ; i++){
										  		if(i == 0 || i+1 == numberPage || currentPage-2 <= i && i <= currentPage+2){ %>
										  		<li <% if(i==currentPage) out.print("class=\"active\""); %>>
												  	<a href="${pageContext.request.contextPath}/Fichier?file=<% out.print(request.getAttribute("file")); %>&amp;page=<% out.print(i); %>">
												  		<% out.print((i*100)+" - "+((i*100)+100)); %>
											  		</a>
									  			</li>
										  		<% }else if(i == currentPage - 3 || i == currentPage + 3){ %>
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
                <!-- /.row -->
                <div class="row">
                    <div class="col-xs-12">
                        <a data-toggle="modal" href="${pageContext.request.contextPath}/Fichier/delete?file=<% out.print(file); %>" class="text-danger pull-right">Supprimer le fichier</a>
                    </div>
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>