<%@ include file="../includes/header.jsp" %>
            <div class="container-fluid">
				<div class="row bg-title">
				    <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				        <h4 class="page-title">Accès interdit</h4>
				    </div>
				    <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				        <ol class="breadcrumb">
				            <li><a href="#">Log viewer</a></li>
				            <li class="active">Accès interdit</li>
				        </ol>
				    </div>
				    <!-- /.col-lg-12 -->
				</div>
		      	<div class="alert alert-danger">
			        <%
			        	if(request.getAttribute("exception") != null){ 
			      			out.print(((Exception)request.getAttribute("exception")).getMessage());
			        	}
			        %>
	      			<p>Vous n'êtes pas autorisé à accéder à cette page. Veuillez vous contacter l'administrateur.</p>
	            </div>
            </div>
            <!-- /.container-fluid -->
<%@ include file="../includes/footer.jsp" %>
</body>
</html>