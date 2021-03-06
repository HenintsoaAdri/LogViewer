<%@page import="adri.logviewermain.model.Agent"%>
<%@page import="adri.logviewermain.model.BaseModel"%>
<%@page import="adri.logviewermain.model.BaseModelPagination"%>
<% BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination");
%>

<a href="${pageContext.request.contextPath}/Agent/new" class="btn btn-success pull-right m-l-20 waves-effect waves-light"><i class="fa fa-plus fa-fw" aria-hidden="true"></i>Nouveau</a>
                                    
<% if(pagination == null || pagination.getListe() == null || pagination.getListe().isEmpty() ){ %>
    	<p class="text-danger">Aucun agent dans ce groupe</p>
<% }else{ %>
<div class="col-md-12">
    <div class="table-responsive">
        <table class="table">
           <thead>
              <tr>
                  <th>#</th>
                  <th>Nom</th>
                  <th>Adresse</th>
                  <th>Port</th>
                  <th>Répertoire</th>
                  <th>Syntaxe</th>
                  <th></th>
              </tr>
            </thead>
            <tbody>	
  	<%	for(BaseModel base : pagination.getListe()){ 
  		Agent i = (Agent)base; %>	  		
		      <tr>
		          <td><% out.print(i.getId()); %></td>
		          <td><% out.print(i.getNom()); %></td>
		          <td><% out.print(i.getAdresse()); %></td>
		          <td><% out.print(i.getPort()); %></td>
		          <td><% out.print(i.getRepertoire()); %></td>
		          <td><% out.print(i.getSyntaxe()); %></td>
		          <td>
		          	<a href="" class="btn btn-success m-l-20 waves-effect waves-light"><i class="fa fa-plug fa-fw" aria-hidden="true"></i>Connexion</a>
		          	<a href="${pageContext.request.contextPath}/Agent/?item.id=<% out.print(i.getId()); %>" class="btn btn-primary m-l-20 waves-effect waves-light"><i class="fa fa-chevron-right fa-fw" aria-hidden="true"></i>Voir l'agent</a>
		          </td>
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