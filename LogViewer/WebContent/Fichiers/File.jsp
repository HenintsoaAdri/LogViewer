<%@page import="adri.logviewermain.filemanager.Log"%>
<%@page import="adri.logviewermain.filemanager.Fichier"%>
<%@page import="java.io.File"%>
<%@ include file="../includes/header.jsp" %>
<% 
	String fileName = (String)request.getAttribute("file");
	String fileString = (String)request.getAttribute("fileString");
	Fichier file = (Fichier)request.getAttribute("fichier");
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
							<a class="pull-right btn btn-success" href="${pageContext.request.contextPath}/Fichier/download?file=<% out.print(fileName); %>"><i class="fa fa-download fw"></i> T�l�charger le fichier</a>
							<h3 class="box-title">
								<% out.print(fileString); %>
							</h3>
							<p class="text-muted">Syntaxe : <% out.print(file.getMainPattern()); %>	
							</p>
							<hr class="m-t-0 m-b-20">
							<div class="row">
								<div class="col-sm-12">	
		                            <%  if(file == null || file.getListeLog() == null || file.getListeLog().isEmpty()){%>
		                    		<p class="text-danger">Aucune ligne de log enregistr�e.</p>
		                    		<%  }else{ %>
		                            <div class="table-responsive">
		                                <table class="table">
		                                    <thead>
		                                        <tr>
		                                            <th>Date</th>
		                                            <th>Priorit�</th>
		                                            <th>Thread</th>
		                                            <th>Classe</th>
		                                            <th>M�thode</th>
		                                            <th>Message</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                    	<% for(Log i : file.getListeLog()){ %>
		                                        <tr>
		                                            <td><% out.print(i.getDateString()); %></td>
		                                            <td><% out.print(i.getPriority()); %></td>
		                                            <td><% out.print(i.getThread()); %></td>
		                                            <td><% out.print(i.getClasse()); %></td>
		                                            <td><% out.print(i.getMethod()); %></td>
		                                            <td><% out.print(i.getMessage()); %></td>
		                                        </tr>
		                                        <% if(!(i.getDetails() == null || i.getDetails().isEmpty())){ %>
		                                        <tr>
		                                            <td colspan="6"><% out.print(i.getDetails()); %></td>
		                                        </tr>
		                                        <% } 
		                                        }
		                                    %>
		                                    </tbody>
		                                </table>
		                            </div>
		                        <% } %>
								</div>
				           </div>
				        </div>
				    </div>
				</div>
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>