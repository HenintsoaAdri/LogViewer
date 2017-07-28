var page = document.getElementById('page');
$(document).ready(function(){
	$.ajax({
        url : url + '/Includes/groupeDetail/Profil',
        type : 'GET',
        data : 'groupe.id=' + groupe,
        success : function(html, statut){
            if(html){
                $('#profil').html(html);
            }else{
                $('#profil').load(url.value + '/Includes/Loader');
            }
        },
        error : function(resultat, statut, erreur){
        }
    });
	$.ajax({
        url : url + '/Includes/groupeDetail/Utilisateur',
        type : 'GET',
        data : 'groupe.id=' + groupe,
        success : function(html, statut){
            if(html){
                $('#utilisateur').html(html);
            }else{
                $('#utilisateur').load(url.value + '/Includes/Loader');
            }
        },
        error : function(resultat, statut, erreur){
        }
    });
	$.ajax({
        url : url + '/Includes/groupeDetail/Agent',
        type : 'GET',
        data : 'groupe.id=' + groupe ,
        success : function(html, statut){
            if(html){
                $('#agent').html(html);
            }else{
                $('#agent').load(url.value + '/Includes/Loader');
            }
        },
        error : function(resultat, statut, erreur){
        }
    });
});

