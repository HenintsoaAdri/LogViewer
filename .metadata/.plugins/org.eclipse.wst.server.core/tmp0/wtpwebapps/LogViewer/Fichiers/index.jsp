<%@page import="adri.logviewer.exception.InputException"%>
<%@page import="java.io.File"%>
<%@ include file="../includes/header.jsp" %>
<% 
	String file = (String)request.getAttribute("file");
	File path = (File)request.getAttribute("path");
%>
            <div class="container-fluid">
				<div class="row bg-title">
				    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				        <h4 class="page-title">Fichiers</h4>
				    </div>
				    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
				            <li class="active">Fichiers</li>
				        </ol>
				    </div>
				</div>
				<div class="row">
				    <div class="col-md-12">
				        <div class="white-box">
							<h3 class="box-title">Fichiers</h3>
							<p class="text-muted">Fichiers enregistr�s</p>
							<hr class="m-t-0 m-b-20">
							<div class="row">
								<div class="col-sm-12">
								<h3 class="box-title">
									<i class="fa fa-folder-open-o" aria-hidden="true"></i> <% out.print(file); %>
								</h3>
						        <% if(request.getAttribute("exception") != null){ 
						      		Exception e = (Exception)request.getAttribute("exception");
						        %>
						      	<p class="text-danger"><% 
					      			try{
					      				out.print(e.getMessage());
					            		out.print(e.getCause().getMessage());
					            		out.print(e.getClass());
					            	}catch(NullPointerException e1){} %>
			      				</p>
				                <!--./row-->
								<% }
								try{
									File[] liste = path.listFiles();
									if(liste != null && liste.length > 0){ %>
									<ul class="list-group list-inline">
									<% for(File f : path.listFiles()){ 
										long length = f.length()/(1024*1024);
									%>
										<li class="text-center p-20">
											<a href="?file=<% out.print(file + File.separator + f.getName()); %>">
												<i class="fa fa-<% if(f.isDirectory()){ out.print("folder"); 
												}else{ out.print("file-text"); } %>-o fa-5x text-info" aria-hidden="true"></i>
								 				<p><% out.print(f.getName()); %><% if(length>0)out.print("(" + length + " Mo)"); %></p>
								 			</a>
								 		</li>
									<% } %>
									</ul>
								<% }else{ %>
									<p class="text-danger">Aucun fichier n'a �t� enregistr�.</p>
								<% }
								}catch(NullPointerException e){ 
									if(file != null && !file.isEmpty()){ %>
									<a href="${pageContext.request.contextPath}/Fichier"> <i class="fa fa-fw fa-inbox"></i> Retour au r�pertoire principal</a>
								<% }
								} %>
								</div>
				           </div>
				        </div>
				    </div>
				</div>
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>