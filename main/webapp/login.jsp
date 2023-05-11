<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>

<% 
   HttpSession session1 = request.getSession(false);
   if (session1 != null && session1.getAttribute("user") != null) {
      response.sendRedirect("admin.jsp");
   }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <%@include file="components/common_css_js.jsp" %>
    </head>
    <body>
        <div class="container">
            
            <div class="row">
                
                <div class="col-md-6 offset-md-3">
                    
                    <div class="card mt-3">
                        
                        <div class="card-header text-center text-white bg-info">
                            <h3>Login !!</h3>
                        </div>
                        <%@include file="components/message.jsp" %>
                        <div class="card-body">
                            <form action="LoginAdminServlet" method="post">
                                <div class="form-group">
                                    <label style="font-size: 16px;" for="exampleInputPassword1">Email</label>
                                    <input name="email" style="height: 30px;" type="email" class="form-control" id="exampleInputPassword1" placeholder="email">
                              </div>
                              <div class="form-group">
                                  <label style="font-size: 16px;" for="exampleInputEmail1">Password</label>
                                  <input name="password" style="height: 30px;" type="password" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="password" />
                              </div>
                              <div class="container text-center mt-3">
                                <button class="btn btn-outline-success ml-3">Login</button>
                                <button class="btn btn-outline-warning">Reset</button>
                              </div>
                            </form>
                            <a href="index.jsp">Page index</a>
                        </div>
                        
                        
                    
                    </div>
                
                </div>
            
            </div>
        </div>
    </body>
</html>
