<%@page import="java.time.Year"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.Month"%>
<%@page import="adri.logviewer.model.StatActivite"%>
<%@page import="adri.logviewer.util.DatePart"%>
<% StatActivite stat = (StatActivite)request.getAttribute("stat"); %>
<div id="ActiviteStat" style="height:90vh">
<a class="btn btn-info btn-outline" data-toggle="collapse" href="#filter"><i class="fa fa-filter fq-fw"></i>Filtrer</a>
<div id="filter" class="collapse">
<hr>
  <form class="form-horizontal form-material" action="${pageContext.request.contextPath}/Utilisateur/save" method="post">
  	<div class="row">
    	<div class="form-group col-md-3">
            <label class="col-md-12">Année</label>
            <div class="col-md-12">
                <input id="annee" name="stat.annee" value="<% out.print(stat.getAnnee()); %>" type="number" min="0" max="<% out.print(Year.now().getValue()); %>" placeholder="Année" class="form-control form-control-line">
			</div>
         </div>
        <div class="form-group col-md-3">
            <label class="col-sm-12">Mois</label>
            <div class="col-sm-12">
                <select id="mois" name="stat.mois" class="form-control form-control-line">
            		<% for(Month i : Month.values()){ %>
            		<option value="<% out.print(i.getValue()); %>" <% out.print(stat.getMois() == i.getValue()?"selected":""); %>><% out.print(i.getDisplayName(TextStyle.FULL, Locale.getDefault())); %></option>
            		<% } %>
                </select>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-sm-12">Grouper</label>
            <div class="col-sm-12">
                <select id="by" name="stat.by" class="form-control form-control-line">
            		<% for(DatePart i : DatePart.values()){ %>
            		<option value="<% out.print(i.name()); %>" <% out.print(stat.getBy().equals(i)?"selected":""); %>><% out.print(i.getLabel()); %></option>
            		<% } %>
                </select>
            </div>
        </div>
        <div class="form-group col-md-3">
            <div class="col-sm-12">
                <button class="btn btn-success" type="button" id="submitFilter">Filtrer</button>
            </div>
        </div>
  	</div>
  </form>
</div>
<hr>
<div id="statActivite">
<% out.print(stat.getJSON()); %>
</div>
<script type="text/javascript">
$("#submitFilter").on("click",function(){
    $('#ActiviteStat').load(url + '/Includes/Loader');	
	$.ajax({
        url : url + '/Includes/statActivite',
        type : 'GET',
        data : 'stat.annee='+$('#annee').val()+'&stat.mois='+$('#mois').val()+'&stat.by='+$('#by').val(),
        success : function(html, statut){
            if(html){
                $('#ActiviteStat').replaceWith(html);
                eval('.script');
            }else{
                $('#ActiviteStat').load(url + '/Includes/Loader');
            }
        },
        error : function(resultat, statut, erreur){
        }
    });
});
</script>
<script type="text/javascript" class="script">
$("#statActivite").CanvasJSChart({
	title:{
		text: "Statistique d'activité"
	},
	data: [{
		type: "line",
		dataPoints: <% out.print(stat.getJSON()); %>
	}]
});
</script>
</div>