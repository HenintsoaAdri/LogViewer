<%@page import="adri.logviewer.model.GroupeView"%>
<%@page import="adri.logviewer.model.BaseModel"%>
<%@page import="adri.logviewer.model.BaseModelPagination"%>
<% BaseModelPagination pagination = (BaseModelPagination)request.getAttribute("pagination"); 
	BaseModel item = (BaseModel)request.getAttribute("item"); %>

<a href="${pageContext.request.contextPath}/<% out.print(item.instance()); %>/edit/<% out.print(item.getId()); %>" class="btn btn-info pull-right m-l-20 waves-effect waves-light"><i class="fa fa-plus fa-fw" aria-hidden="true"></i>Ajouter</a>
  	<% if(pagination == null || pagination.getListe() == null || pagination.getListe().isEmpty() ){ %>
      <p class="text-danger">Aucun groupe dans cette entité</p>
  	<%
  	}else{ %>
  <div class="col-md-12">
     <div class="table-responsive">
         <table class="table">
             <thead>
                 <tr>
                     <th>#</th>
                     <th>Nom</th>
                     <th>Description</th>
                     <th>Nombre d'agent</th>
                     <th></th>
                 </tr>
             </thead>
             <tbody>
  	<%	for(BaseModel base : pagination.getListe()){ 
    		GroupeView i = (GroupeView)base;
    	%>
		        <tr>
		            <td><% out.print(i.getId()); %></td>
		            <td><% out.print(i.getNom()); %></td>
		            <td><% out.print(i.getDescription()); %></td>
		            <td><% out.print(i.getNombreAgent()); %></td>
		            <td><a href="${pageContext.request.contextPath}/Groupe/<% out.print(i.getId()); %>" class="btn btn-primary m-l-20 waves-effect waves-light"><i class="fa fa-chevron-right fa-fw" aria-hidden="true"></i>Voir le groupe</a></td>
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
 <script>
	$(".page").on("click", function(){
		var page = $(this).attr("data-page");
		$.ajax({
	        url : url + '/Includes/detail/'+item+'/'+detail,
	        type : 'GET',
	        data : 'item.id=' + id + '&page=' + page,
	        success : function(html, statut){
	            if(html){
	                $('#' + detail).html(html);
	            }else{
	                $('#' + detail).load(url.value + '/Includes/Loader');
	            }
	        },
	        error : function(resultat, statut, erreur){
	        }
	    });
	});
 </script>
 <div class="clearfix"></div>