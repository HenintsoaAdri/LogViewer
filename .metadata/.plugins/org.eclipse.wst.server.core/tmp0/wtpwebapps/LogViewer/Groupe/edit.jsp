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
                            <li class="active">Modifier</li>
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
                            <h3 class="box-title">
                            	Groupe #<% out.print(item.getId()); %>
                            </h3>
                            <p class="text-muted">Modifier un groupe d'agent</p>
                            <form action="${pageContext.request.contextPath}/Groupe/update" class="form-horizontal form-material" method="post">
                            	<input type="hidden" name="item.id" value="<% out.print(item.getId()); %>" />
                                <div class="form-body">
                                    <hr class="m-t-0 m-b-40">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="control-label col-md-3">Nom</label>
                                                <div class="col-md-9">
                                                    <input value="<% out.print(item.getNom()); %>" type="text" class="form-control" placeholder="Nom du groupe" name="item.nom">
                                                </div>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                	<label class="control-label col-md-3">Description</label>
                                                <div class="col-md-9">
		                                        	<textarea name="item.description" rows="5" class="form-control form-control-line"><% out.print(item.getDescription()); %></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <div class="col-md-offset-8 col-md-4">
                                                    <button type="submit" class="btn btn-success">Modifier</button>
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
                <!--./row-->
                <div class="row">
                    <div class="col-xs-12">
                        <a data-toggle="modal" href="#delete" data-item="Groupe" data-id="<% out.print(item.getId()); %>" class="text-danger pull-right confirmDelete">Supprimer le groupe</a>
                    </div>
                </div>
                <!-- /.row -->
			    <!-- Modal -->
			    <div class="modal fade" id="delete" role="dialog">
			    </div>
                <!-- /.Modal -->
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/function.js"></script>
</body>
</html>