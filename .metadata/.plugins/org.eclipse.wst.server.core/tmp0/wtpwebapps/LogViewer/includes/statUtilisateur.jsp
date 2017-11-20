<%@page import="adri.logviewer.model.UtilisateurStat"%>
<%@page import="adri.logviewer.model.Utilisateur"%>
<%@page import="adri.logviewer.model.BaseModel"%>
<%@page import="adri.logviewer.model.BaseModelPagination"%>
<% String detail = (String)request.getAttribute("detail");
	BaseModelPagination pagination = (BaseModelPagination) request.getAttribute("pagination");
%>
<div>
	<ul class="common-list">
	<% if(pagination != null && pagination.getListe() != null && !pagination.getListe().isEmpty()){ 
		for(BaseModel m : pagination.getListe()){
			UtilisateurStat i = (UtilisateurStat)m; %>
		<li><i class="fa fa-hashtag fa-fw"></i><% out.print(i.getId()); %> <% out.print(i.getFullName()); %>, <% out.print(i.getPoste()); %> : <% out.print(i.getValeurPourcent()); %></li>
	<% }} %>
	</ul>
</div>
<div id="pieUtilisateur" style="height: 90vh;">
<%  try{
		out.print(detail); 
	}catch(Exception e){
		out.print(e.getMessage());
	} %>
</div>
<script type="text/javascript" class="script">
	$("#pieUtilisateur").CanvasJSChart({
		title:{
			text: "Statistique d'activité d'Utilisateurs"
		},
		data: [{
			type: "pie",
			showInLegend: true,
			toolTipContent: "Nombre : {y}",
			yValueFormatString: "####",
			legendText: "{indexLabel}",
			dataPoints: <% out.print(detail); %>
		}]
	});
</script>