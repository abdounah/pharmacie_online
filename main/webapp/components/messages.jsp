<%
    String messages = (String)session.getAttribute("messages");
    if(messages != null) {
    %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        <strong><%= messages %></strong> 
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
    </div>
<%    
        session.removeAttribute("messages");
    }
%>
