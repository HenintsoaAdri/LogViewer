		      	<div class="alert alert-danger">
			        <%
			        	if(request.getAttribute("exception") != null){ 
			      			out.print(((Exception)request.getAttribute("exception")).getMessage());
			        	}
			        %>
	      			Vous n'�tes pas autoris� � acc�der � cette page. Veuillez vous contacter l'administrateur.
	            </div>