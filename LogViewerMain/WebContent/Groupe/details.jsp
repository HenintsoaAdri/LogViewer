<%@page import="adri.logviewermain.model.Groupe"%>
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
                            <li class="active">D&eacute;tails</li>
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
                <div class="row">
                    <div class="col-md-12">
                    	<div class="white-box">
                    		<h3 class="box-title">Groupe #<% out.print(item.getId()); %></h3>
	                        <div class="form-horizontal">
	                            <div class="form-body">
	                            	<a href="${pageContext.request.contextPath}/Groupe/edit/?item.id=<% out.print(item.getId()); %>" class="btn btn-info pull-right m-l-20 waves-effect waves-light"> <i class="fa fa-pencil"></i> Modifier</a>
	                                <p class="text-muted">D&eacute;tails du groupe d'utilisateurs</p>
	                                <hr class="m-t-0 m-b-40">
	                                <div class="row">
	                                    <div class="col-md-6">
	                                        <div class="form-group">
	                                            <label class="control-label col-md-3">Nom : </label>
	                                            <div class="col-md-9">
	                                                <p class="form-control-static"> <% out.print(item.getNom()); %> </p>
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <!--/span-->
	                                    <div class="col-md-6">
	                                        <blockquote>
	                                        	<% out.print(item.getDescription()); %> 
	                                        </blockquote>
	                                    </div>
	                                    <!--/span-->
	                                </div>
	                                <!--/row-->
	                            </div>
	                        </div>
	                	</div>   
                    </div>
                </div>
                <!--./row-->
                <div class="row">
                	<div class="col-md-12">
                        <div class="white-box">
                        	<!-- tab -->
                            <ul class="nav customtab nav-tabs" role="tablist">
                                <li role="presentation" class="active"><a href="#profil" role="tab" data-toggle="tab" aria-expanded="true"><span class="visible-xs"><i class="fa fa-shield fa-fw"></i></span><span class="hidden-xs"> Profil</span></a></li>
                                <li role="presentation" class=""><a href="#utilisateur" role="tab" data-toggle="tab" aria-expanded="false"><span class="visible-xs"><i class="fa fa-address-card fa-fw"></i></span> <span class="hidden-xs">Utilisateurs</span></a></li>
                                <li role="presentation" class=""><a href="#agent" role="tab" data-toggle="tab" aria-expanded="false"><span class="visible-xs"><i class="fa fa-gears fa-fw"></i></span> <span class="hidden-xs">Agents distants</span></a></li>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane fade active in" id="profil">
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="utilisateur">
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="agent">
                                </div>
                            </div>
                            <!-- /.tab -->
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
<script type="text/javascript">
	var groupe = <% out.print(item.getId()); %>;
</script>
<%@ include file="../includes/footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/groupeFunction.js"></script>