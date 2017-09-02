		      	<div class="alert alert-danger">
			        <%
			        	if(request.getAttribute("exception") != null){ 
			      			out.print(((Exception)request.getAttribute("exception")).getMessage());
			        	}
			        %>
	      			Vous n'êtes pas autorisé à accéder à cette page. Veuillez vous contacter l'administrateur.
	            </div>