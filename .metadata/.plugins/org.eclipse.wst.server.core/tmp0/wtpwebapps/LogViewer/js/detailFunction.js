var page = document.getElementById('page');
$(document).ready(function(){
	$.ajax({
        url : url + '/Includes/detail/'+item+'/'+detail,
        type : 'GET',
        data : 'item.id=' + id,
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
