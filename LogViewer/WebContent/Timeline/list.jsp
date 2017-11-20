<%@page import="adri.logviewer.model.Timeline"%>
<%@page import="adri.logviewer.model.BaseModel"%>
<%@page import="adri.logviewer.model.BaseModelPagination"%>
<%@ include file="../includes/header.jsp" %>
<%  BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination");  %>
            <div class="container-fluid">
				<div class="row bg-title">
				    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				        <h4 class="page-title">Timeline &amp; Statistiques</h4>
				    </div>
				    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
				            <li class="active">Timeline &amp; Statistiques</li>
				        </ol>
				    </div>
				</div>
				<div class="row">
				    <div class="col-md-12">
				        <div class="white-box">
                        	<!-- tab -->
                            <ul class="nav customtab nav-tabs" role="tablist">
                                <li role="presentation" class="active"><a href="#Timeline" role="tab" data-toggle="tab" aria-expanded="false"><span class="visible-xs"><i class="fa fa-sliders fa-fw"></i></span> <span class="hidden-xs">Timeline</span></a></li>
                                <li role="presentation"><a href="#Stat" role="tab" data-toggle="tab" aria-expanded="false"><span class="visible-xs"><i class="fa fa-pie-chart fa-fw"></i></span> <span class="hidden-xs">Statistiques</span></a></li>
                                <li role="presentation"><a href="#Notif" role="tab" data-toggle="tab" aria-expanded="false"><span class="visible-xs"><i class="fa fa-flag fa-fw"></i></span> <span class="hidden-xs">Demande de réinitialisation</span></a></li>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane fade in active" id="Timeline">
								   	<ul class="timeline">
									<% if(pagination.getListe() != null && !pagination.getListe().isEmpty()){
											int j = 0;
											for(BaseModel m : pagination.getListe()){
												Timeline i = (Timeline)m;	
									%>
								      	<li class="<% out.print(j%2!=0?"timeline-inverted":""); %>">
		                                    <div class="timeline-badge info"><i class="fa fa-<% out.print(i.getAction()); %>"></i> </div>
		                                    <div class="timeline-panel">
		                                        <div class="timeline-heading">
		                                            <h5 class="timeline-title">
													<a href="${pageContext.request.contextPath}/Utilisateur/<% out.print(i.getUtilisateur().getId()); %>"><% out.print(i.getUtilisateur().getFullName()); %></a>,
								   					<% out.print(i.getUtilisateur().getPoste()); %>
													</h5>
												</div>
		                                        <div class="timeline-body">
		                                            <p> <% out.print(i.getActionString()); %>
								   						<% out.print(i.getModel()); %> </p>
								   					<p class="text-muted text-right"><% out.print(i.getDateString()); %></p>
								   					<a data-toggle="collapse" href="#details<% out.print(i.getId()); %>">Details</a>
								   					<div class="collapse" id="details<% out.print(i.getId()); %>">
								   						<% out.print(i.getDetails()); %>
								   					</div>
		                                        </div>
		                                    </div>
		                                </li>							
									<% j++; }
									}else{ out.print("Aucun enregistrement dans la timeline"); } %>
									</ul>
 									<div class="clearfix"></div>
								</div>
                                <div role="tabpanel" class="tab-pane fade" id="Stat">
                           			<div class="row">
	                                	<div class="col-md-6" id="Profil">
											<%@ include file="../includes/loader.html" %>
	                                	</div>
	                                	<div class="col-md-6" id="Utilisateur">
											<%@ include file="../includes/loader.html" %>
	                                	</div>
                           			</div>
                           			<div class="row">
	                                	<div class="col-md-12" id="Activite">
											<%@ include file="../includes/loader.html" %>
	                                	</div>
                           			</div>
                                </div>
                                <div role="tabpanel" class="tab-pane fade" id="Notif">
									<%@ include file="../includes/loader.html" %>
                                </div>								
				           </div>
				        </div>
				    </div>
				</div>
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/timeline.js"></script>
</body>
</html>