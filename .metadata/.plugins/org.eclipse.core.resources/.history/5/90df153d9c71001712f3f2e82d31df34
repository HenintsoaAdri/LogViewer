var profilId = document.getElementById('item.profil.id');
$(document).ready(function(){
	$.ajax({
        url : url+'/Includes/SelectProfil',
        type : 'GET',
        data : 'groupe.id=' + $("#groupeSelect").val() + '&item.id=' + profilId.value ,
        success : function(html, statut){
            if(html){
                $('#profilSelect').html(html);
            }else{
                $('#details .modal-body').load('Waiter');
            }
        },
        error : function(resultat, statut, erreur){
        }
    });
    $("#groupeSelect").on("change", function () {
        var groupe = $(this).val();
        $.ajax({
            url : url+'/Includes/SelectProfil',
            type : 'GET',
            data : 'groupe.id=' + groupe ,
            success : function(html, statut){
                if(html){
                    $('#profilSelect').html(html);
                }
            },
            error : function(resultat, statut, erreur){
            }
        });
    });
});