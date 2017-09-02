<%@page import="adri.logviewermain.model.ProfilView"%>
<%@page import="adri.logviewermain.model.BaseModel"%>
<%@page import="adri.logviewermain.model.BaseModelPagination"%>
<% BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination"); 
	BaseModel item = (BaseModel)request.getAttribute("item"); %>

<a href="${pageContext.request.contextPath}/Profil/new?item.listeGroupe.id=<% out.print(item.getId()); %>" class="btn btn-info pull-right m-l-20 waves-effect waves-light"><i class="fa fa-plus fa-fw" aria-hidden="true"></i>Nouveau</a>
  	<% if(pagination == null || pagination.getListe() == null || pagination.getListe().isEmpty() ){ %>
      <p class="text-danger">Aucun profil dans cette entit�</p>
  	<%
  	}else{ %>
  <div class="col-md-12">
     <div class="table-responsive">
         <table class="table">
	         <thead>
	             <tr>
	                 <th>#</th>
	                 <th>Nom</th>
	                 <th>Permission</th>
	                 <th>Nombre d'utilisateur</th>
	                 <th></th>
	             </tr>
	         </thead>
	         <tbody>
	         	<% for(BaseModel base : pagination.getListe()){ 
	         		ProfilView i = (ProfilView)base;
	         	%>
	             <tr>
	                 <td><% out.print(i.getId()); %></td>
	                 <td><% out.print(i.getNom()); %></td>
	                 <td><% out.print(i.getListePermissionString()); %></td>
	                 <td><% out.print(i.getNombreUtilisateur()); %></td>
	                 <td><a href="${pageContext.request.contextPath}/Profil/<% out.print(i.getId()); %>" class="btn btn-primary m-l-20 waves-effect waves-light"><i class="fa fa-chevron-right fa-fw" aria-hidden="true"></i>Voir le profil</a></td>
	             </tr>
	             <% } %>
	         </tbody>
	     </table>
     </div>
 </div>
	<% if(pagination.getNombrePage()>1){%>
        <ul class="pagination">
        <% for(int i=0; i<pagination.getNombrePage(); i++){ %>
		  <li <% if(pagination.getPage() == i)out.print("class=\"active\""); %>><a href="?item.id=<% out.print(item.getId()); %>&page=<% out.print(i+1); %>"><% out.print(i+1); %></a></li>
		<% } %>
		</ul>
  <%  }
	} %>
 <div class="clearfix"></div>