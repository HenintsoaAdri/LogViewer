$(document).ready(function(){
		$.ajax({
	        url : url + '/Includes/stat/Utilisateur/Pie',
	        type : 'GET',
	        success : function(html, statut){
	            if(html){
	                $("#Utilisateur").append(html);
	                $("#Utilisateur").html(html);
	                eval($(".script"));
	            }
	        },
	        error : function(resultat, statut, erreur){
	        }
	    });
		$.ajax({
	        url : url + '/Includes/stat/Profil/Pie',
	        type : 'GET',
	        success : function(html, statut){
	            if(html){
	                $("#Profil").html(html);
	                eval($(".script"));
	            }
	        },
	        error : function(resultat, statut, erreur){
	        }
	    });
		$.ajax({
	        url : url + '/Includes/statActivite',
	        type : 'GET',
	        success : function(html, statut){
	            if(html){
	                $("#Activite").html(html);
	                eval($(".script"));
	            }
	        },
	        error : function(resultat, statut, erreur){
	        }
	    });
		$.ajax({
	        url : url + '/Includes/reinitUtilisateur',
	        type : 'GET',
	        success : function(html, statut){
	            if(html){
	                $("#Notif").html(html);
	                if(totalUtilisateur > 0){
	                	$("a[href='#Notif']").append(" <span class=\"badge\">"+totalUtilisateur+"</span>");
	                }
	            }
	        },
	        error : function(resultat, statut, erreur){
	        }
	    });
	});