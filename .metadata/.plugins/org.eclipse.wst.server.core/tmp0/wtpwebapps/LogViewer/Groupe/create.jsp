<%@page import="adri.logviewer.model.PermissionType"%>
<%@page import="adri.logviewer.model.Groupe"%>
<%@ include file="../includes/header.jsp" %>
<% Groupe item = (Groupe)request.getAttribute("item"); %>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Groupe</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
                            <li><a href="${pageContext.request.contextPath}/Groupe">Groupe</a></li>
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
                <s:fielderror cssClass="alert alert-danger list-unstyled"/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h3 class="box-title">Nouveau groupe</h3>
                            <p class="text-muted">Créer un nouveau groupe d'utilisateurs</p>
                            <form action="${pageContext.request.contextPath}/Groupe/save" class="form-horizontal form-material" method="post">
                                <div class="form-body">
                                    <hr class="m-t-0 m-b-40">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Nom</label>
                                                <div class="col-md-9">
                                                    <input type="text" name="item.nom" value="<% item.getNom(); %>" class="form-control" placeholder="Nom du groupe">
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                               	<label class="control-label col-md-3">Description</label>
                                                <div class="col-md-9">
		                                        	<textarea rows="5" class="form-control form-control-line" name="item.description"><% item.getDescription(); %></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <div class="col-md-offset-8 col-md-4">
                                                    <button type="submit" class="btn btn-success">Cr&eacute;er</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
</body>
</html>