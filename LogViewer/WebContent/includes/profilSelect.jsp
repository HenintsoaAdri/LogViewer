<%@page import="adri.logviewermain.model.BaseModel"%>
<%@page import="java.util.List"%>
<%@page import="adri.logviewermain.model.Profil"%>
<% List<? extends BaseModel> liste = (List<? extends BaseModel>)request.getAttribute("liste"); 
	Profil item = (Profil)request.getAttribute("item");
%>
                   
              <% for(BaseModel m : liste){  
               	Profil i = (Profil)m;
               %>
                   <option value="<% out.print(i.getId()); %>" <% if(i.getId() == item.getId() )out.print("selected"); %>><% out.print(i.getNom()); %></option>
               <% } %>