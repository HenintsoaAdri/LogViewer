<%@page import="adri.logviewer.filemanager.Log"%>
<%@page import="adri.logviewer.filemanager.Fichier"%>
<%@page import="java.io.File"%>
<%@ include file="../includes/header.jsp" %>
<% 
	String file = (String)request.getAttribute("file");
%>
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
								<a class="btn btn-success" href="${pageContext.request.contextPath}/Fichier/parse?file=<% out.print(file); %>"><i class="fa fa-indent fw"></i> Parser le fichier</a>
								<a class="btn-outline btn btn-success" href="${pageContext.request.contextPath}/Fichier/download?file=<% out.print(file); %>"><i class="fa fa-download fw"></i> TÚlÚcharger le fichier</a>
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
						      	<p class="text-danger">
					      			<% out.print(e.getMessage()); e.printStackTrace();%>
					            </p>
				                <!--./row-->
								<% } %>
								<iframe width="100%;" src="">
								</iframe>
								</div>
				           </div>
				        </div>
				    </div>
				</div>
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>