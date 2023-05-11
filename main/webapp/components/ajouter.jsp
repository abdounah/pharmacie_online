<%-- 
    Document   : ajouter
    Created on : 25 mars 2023, 13:12:01
    Author     : abdou
--%>
<%@page import="com.mycompany.myproj.entities.Admin"%>
<%
    Admin admin = (Admin)session.getAttribute("current-user");
%>
<%@page import="com.mycompany.myproj.entities.Admin"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.myproj.helper.FactoryProvider"%>
<%@page import="com.mycompany.myproj.dao.AdminDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajouter lieu</title>
        <link rel="stylesheet" href="../css/add_style.css" />
        <%@include file="common_css_js.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="messages.jsp" %>
            <div class="title">Nauveau lieu</div>
            <form action="../LieuxOperationsServlet" method="post" enctype="multipart/form-data">
                <div class="user-details">
                    <div class="input-box">
                        
                        <span class="details">ID admin</span>
                        <select name="idAdmin">                          
                            <option value="<%= admin.getIdAdmin() %>"><%= admin.getIdAdmin() %></option>
                        </select>
                    </div>
                    <div class="input-box">
                        <span class="details">Nom du lieu</span>
                        <input name="nom" type="text" placeholder="nom" required/>
                    </div>
                    <div class="input-box">
                        <span class="details">Description</span>
                        <textarea name="desc" placeholder="description"></textarea>
                    </div>
                    <div class="input-box">
                        <span class="details">L'altitude</span>
                        <input name="altitude" type="text" placeholder="l'altitude"/>
                    </div>
                    <div class="input-box">
                        <span class="details">Longitude</span>
                        <input name="longitude" type="text" placeholder="longitude"/>
                    </div>
                    <div class="input-box">
                        <span class="details">Image</span>
                        <input name="img" type="file" required/>
                    </div>


                </div>
                <div class="submit">
                    <input type="submit" value="Ajouter" />

                </div>
                <div class="reset">
                    <input type="reset" value="Annuler" />
                </div>
            </form>
        </div>
    </body>
</html>
