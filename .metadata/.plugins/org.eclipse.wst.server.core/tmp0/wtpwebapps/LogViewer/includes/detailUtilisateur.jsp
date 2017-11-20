<%@page import="adri.logviewer.model.UtilisateurView"%>
<%@page import="adri.logviewer.model.BaseModel"%>
<%@page import="adri.logviewer.model.BaseModelPagination"%>
<% BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination"); 
	BaseModel item = (BaseModel)request.getAttribute("item"); %>

  	<% if(pagination == null || pagination.getListe() == null || pagination.getListe().isEmpty() ){ %>
      <p class="text-danger">Aucun utilisateur retourné</p>
  	<%
  	}else{ %>
  <div class="col-md-12">
     <div class="table-responsive">
         <table class="table tablesorter">
             <thead>
                 <tr>
                    <th>#</th>
                    <th>Nom &amp; Pr&eacute;nom</th>
                    <th>Poste</th>
                    <th>Email</th>
                    <th>Profil</th>
                    <th>Sexe</th>
                    <th>Activit&eacute;</th>
                    <th></th>
                 </tr>
             </thead>
             <tbody>
  			<% String prefix = "Connecté il y a ";
           		for(BaseModel base : pagination.getListe()){ 
           		UtilisateurView i = (UtilisateurView)base;
           	%>
               <tr>
                   <td><% out.print(i.getId()); %></td>
                   <td><% out.print(i.getFullName());%></td>
                   <td><% out.print(i.getPoste()); %></td>
                   <td><% out.print(i.getEmail()); %></td>
                   <td><% if(i.getProfil() != null)out.print(i.getProfil().getNom()); %></td>
                   <td><% out.print(i.getSexeString()); %></td>
                   <td><% out.print(i.getLastLoggedString(prefix)); %></td>
                   <td><a href="${pageContext.request.contextPath}/Utilisateur/<% out.print(i.getId()); %>" class="btn btn-primary m-l-20 waves-effect waves-light"><i class="fa fa-chevron-right fa-fw" aria-hidden="true"></i>Voir l'utilisateur</a></td>
               </tr>
               <% } %>
             </tbody>
         </table>
     </div>
 </div>
	<% if(pagination.getNombrePage()>1){%>
        <ul class="pagination">
        <% for(int i=0; i<pagination.getNombrePage(); i++){ %>
		  <li <% if(pagination.getPage() == i)out.print("class=\"active\""); %>><a href="#" class="page" data-page="<% out.print(i+1); %>" ><% out.print(i+1); %></a></li>
		<% } %>
		</ul>
  <%  }
	} %>
 <div class="clearfix"></div>
 <script type="text/javascript" class="">
 var totalUtilisateur = <% out.print(pagination.getTotalResult()); %>
	$(".page").on("click", function(){
		var page = $(this).attr("data-page");
		$.ajax({
	        url : url + '/Includes/reinitUtilisateur',
	        type : 'GET',
	        data : '&page=' + page,
	        success : function(html, statut){
	            if(html){
	                $('#' + detail).html(html);
	            }else{
	                $('#' + detail).load(url + '/Includes/Loader');
	            }
	        },
	        error : function(resultat, statut, erreur){
	        }
	    });
	});
 </script>
 <div class="clearfix"></div>