<%@page import="java.io.File"%>
<%@ include file="../includes/header.jsp" %>
<% 
	String file = (String)request.getAttribute("file");
	String fileString = (String)request.getAttribute("fileString");
	String fileStructure = (String)request.getAttribute("fileStructure");
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
				    <!-- /.col-lg-12 -->
				</div>
				<div class="row">
				    <div class="col-md-12">
				        <div class="white-box">
							<h3 class="box-title">Fichiers</h3>
							<p class="text-muted">Fichiers enregistrés</p>
							<hr class="m-t-0 m-b-20">
							<div class="row">
								<div class="col-sm-12">
								<h3 class="box-title">
									<% out.print(fileString); %>
								</h3>
								<% out.print(fileStructure); %>
								</div>
				           </div>
				        </div>
				    </div>
				</div>
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>