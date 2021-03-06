<%@page import="adri.logviewer.model.Profil"%>
<%@page import="adri.logviewer.model.Groupe"%>
<%@page import="adri.logviewer.model.BaseModel"%>
<%@ include file="../includes/header.jsp" %>
<% Utilisateur item = (Utilisateur)request.getAttribute("item");
	List<? extends BaseModel> liste = (List<? extends BaseModel>)request.getAttribute("liste");
%>
            <div class="container-fluid">
                <div class="row bg-title">
                    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                        <h4 class="page-title">Utilisateur</h4> </div>
                    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                        <ol class="breadcrumb">
                            <li><a href="${pageContext.request.contextPath}">Log Viewer</a></li>
                            <li><a href="${pageContext.request.contextPath}/Utilisateur">Utilisateur</a></li>
                            <li class="active">Modifier</li>
                        </ol>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row --> 
		        <% if(request.getAttribute("exception") != null){ 
		      		Exception e = (Exception)request.getAttribute("exception");
		        %>
                <div class="row">
                	<div class="col-md-12">
                        <div class="white-box">
					      	<div class="alert alert-danger alert-dismissable">
				                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
				      			<% out.print(e.getMessage()); %>
				            </div>
                        </div>
                	</div>
                </div>
                <!--./row-->
				<% } %> 
                <s:fielderror cssClass="alert alert-danger list-unstyled"/>
                <div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <h3 class="box-title">
                            	Utilisateur #<% out.print(item.getId()); %>
                            </h3>
                            <p class="text-muted">Modifier un utilisateur</p>
                            <form class="form-horizontal form-material" action="${pageContext.request.contextPath}/Utilisateur/update" method="post">
                            	<input type="hidden" name="item.id" value="<% out.print(item.getId()); %>">
                            	<div class="row">
	                            	<div class="col-md-6">
		                            	<div class="form-group">
		                                    <label class="col-md-12">Nom</label>
		                                    <div class="col-md-12">
		                                        <input name="item.nom" value="<% out.print(item.getNom()); %>" type="text" placeholder="Nom" class="form-control form-control-line">
											</div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="col-md-12">Pr&eacute;nom</label>
		                                    <div class="col-md-12">
		                                        <input name="item.prenom" value="<% out.print(item.getPrenom()); %>" type="text" placeholder="Syntaxe des lignes de log" class="form-control form-control-line">
		                                    </div>
		                                </div>
                                        <div class="form-group">
                                            <label class="col-md-12">Sexe</label>
                                            <div class="radio-list col-md-12">
                                                <label class="radio-inline p-0">
                                                    <div class="radio radio-primary">
                                                        <input type="radio" name="item.sexe" id="homme" value="M" <% if(item.getSexe() == 'M') out.print("checked"); %>>
                                                        <label for="homme"><i class="fa fa-male"></i> Homme</label>
                                                    </div>
                                                </label>
                                                <label class="radio-inline  p-0">
                                                    <div class="radio radio-primary">
                                                        <input type="radio" name="item.sexe" id="femme" value="F" <% if(item.getSexe() == 'F') out.print("checked"); %>>
                                                        <label for="femme"><i class="fa fa-female"></i> Femme</label>
                                                    </div>
                                                </label>
                                            </div>
                                        </div>
		                                <div class="form-group">
		                                    <label class="col-md-12">Poste</label>
		                                    <div class="col-md-12">
		                                        <input name="item.poste" value="<% out.print(item.getPoste()); %>" type="text" placeholder="Poste" class="form-control form-control-line">
		                                    </div>
		                                </div>
	                            	</div>
	                            	<div class="col-md-6">
		                                <div class="form-group">
		                                    <label class="col-md-12">Email</label>
		                                    <div class="col-md-12">
		                                        <input name="item.email" value="<% out.print(item.getEmail()); %>" type="email" class="form-control form-control-line">
		                                    </div>
		                                </div>
		                                <% if(item.isReinitPassword()){ %>
		                                <div class="form-group">
		                                    <label class="col-md-12">Mot de passe <% if(item.isReinitPassword()){ %> <span class="animated flash infinite text-danger">*</span><% } %></label>
		                                    <div class="col-md-12">
		                                        <input name="item.password" type="password" placeholder="Mot de passe" class="form-control form-control-line">
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <label class="col-md-12">Confirmer votre mot de passe</label>
		                                    <div class="col-md-12">
		                                        <input name="item.confirm" type="password" placeholder="Mot de passe" class="form-control form-control-line">
		                                    </div>
		                                </div>
		                                <% } %>
		                                <div class="form-group" id="profil">
		                                    <label class="col-sm-12">Profil</label>
		                                    <input type="hidden" id="item.profil.id" value="<% if(item.getProfil() != null) out.print(item.getProfil().getId()); %>">
		                                    <div class="col-sm-12">
		                                        <select id="profilSelect" name="item.profil.id" class="form-control form-control-line">
		                                    		<option disabled>Veuillez sélectionner un profil</option>
		                                    		<% for(BaseModel p : liste){ 
		                                    			Profil i = (Profil)p;
		                                    		%>
		                                    		<option value="<% out.print(i.getId()); %>" <%if(i.equals(item.getProfil()))out.print("selected"); %>><% out.print(i.getNom()); %></option>
		                                    		<% } %>
		                                        </select>
		                                    </div>
		                                </div>
		                                <div class="form-group">
		                                    <div class="col-sm-12">
		                                        <button class="btn btn-success" type="submit">Modifier</button>
		                                    </div>
		                                </div>
	                            	</div>
                            	</div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-xs-12">
                        <a data-toggle="modal" href="#delete" data-item="Utilisateur" data-id="<% out.print(item.getId()); %>" class="text-danger pull-right confirmDelete">Supprimer l'utilisateur</a>
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
<script>
$(document).ready(function(){
    $('.reinit').popover({title: "Mot de passe oublié", content: "<p>Demande de réinitialisation</p>", html: true, trigger: "hover"}); 
});
</script>
</body>
</html>