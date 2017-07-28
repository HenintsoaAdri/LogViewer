$(document).ready(function(){
    $(".confirmDelete").on("click", function () {
        var item = $(this).attr('data-item');
        var id = $(this).attr('data-id');
        $.ajax({
            url : url+'/Includes/deleteConfirm/'+item,
            type : 'GET',
            data : 'item.id=' + id ,
            success : function(html, statut){
                if(html){
                    $('#delete').html(html);
                }
            },
            error : function(resultat, statut, erreur){
            }
        });
    });
});