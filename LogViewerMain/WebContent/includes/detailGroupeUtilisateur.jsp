<%@page import="adri.logviewermain.model.Utilisateur"%>
<%@page import="adri.logviewermain.model.BaseModel"%>
<%@page import="adri.logviewermain.model.BaseModelPagination"%>
<% BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination"); 
	BaseModel item = (BaseModel)request.getAttribute("item");
%>

<a href="${pageContext.request.contextPath}/Utilisateur/new?item.profil.groupe.id=<% out.print(item.getId()); %>" class="btn btn-success pull-right m-l-20 waves-effect waves-light"><i class="fa fa-plus fa-fw" aria-hidden="true"></i>Nouveau</a>
  	<% if(pagination == null || pagination.getListe() == null || pagination.getListe().isEmpty() ){ %>
      <p class="text-danger">Aucun utilisateur dans ce groupe</p>
  	<%
  	}else{ %>
	<div class="col-md-12">
		<div class="table-responsive">
			<table class="table">
             <thead>
               <tr>
                   <th>#</th>
                   <th>Nom&amp;Pr&eacute;nom</th>
                   <th>Poste</th>
                   <th>Email</th>
                   <th>Sexe</th>
                   <th>Activit�</th>
                   <th></th>
               </tr>
             </thead>
			 <tbody>
  	<%	for(BaseModel base : pagination.getListe()){ 
    		Utilisateur i = (Utilisateur)base;
    	%>
			   <tr>
			       <td><% out.print(i.getId()); %></td>
			       <td><% out.print(i.getFullName()); %></td>
			       <td><% out.print(i.getPoste()); %></td>
			       <td><% out.print(i.getEmail()); %></td>
			       <td><% if(i.getProfil() != null)out.print(i.getProfil().getGroupe().getNom()); %></td>
			       <td><% if(i.getProfil() != null)out.print(i.getProfil().getNom()); %></td>
			       <td><% out.print(i.getSexeString()); %></td>
			       <td><% out.print(i.getSexeString()); %></td>
			       <td><a href="${pageContext.request.contextPath}/Utilisateur/?item.id=<% out.print(i.getId()); %>" class="btn btn-success m-l-20 waves-effect waves-light"><i class="fa fa-chevron-right fa-fw" aria-hidden="true"></i>Voir l'utilisateur</a></td>
			   </tr>
        <% } %>
			 </tbody>
			</table>
		</div>
	</div>
        <ul class="pagination">
        <% for(int i=0; i<pagination.getNombrePage(); i++){ %>
		  <li <% if(pagination.getPage() == i)out.print("class=\"active\""); %>><a href="?page=<% out.print(i+1); %>"><% out.print(i+1); %></a></li>
		<% } %>
		</ul>
    <% } %>
	<div class="clearfix"></div>