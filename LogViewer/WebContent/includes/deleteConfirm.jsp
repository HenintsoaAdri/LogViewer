<%@page import="adri.logviewer.model.*"%>
<% BaseModel item = (BaseModel)request.getAttribute("item"); %>
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-12">
                            <p class="text-center">&Ecirc;tes vous s�r(e) de vouloir supprimer <% out.print(item.getNomString()); %> ?</p>
                            <br>
                        </div>
                        <div class="col-sm-12 text-center">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/<% out.print(item.instance()); %>/delete/<% out.print(item.getId()); %>">Supprimer</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>