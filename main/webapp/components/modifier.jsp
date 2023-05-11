<%@page import="com.mycompany.myproj.entities.Admin"%>
<%
    Admin admin = (Admin) session.getAttribute("current-user");
%>
<%@page import="com.mycompany.myproj.entities.Lieu"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.mycompany.myproj.entities.Admin"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.myproj.helper.FactoryProvider"%>
<%@page import="com.mycompany.myproj.dao.AdminDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier lieu</title>
        <link rel="stylesheet" href="../css/add_style.css" />
        <%@include file="common_css_js.jsp" %>
    </head>
    <body>
        <div class="container">
            <%@include file="messages_1.jsp" %>
            <div class="title">Modifier lieu</div>
            <%                int idLieu = Integer.parseInt(request.getParameter("idLieu").trim());
                Session s = FactoryProvider.getFactory().openSession();
                Lieu lieu = (Lieu) s.get(Lieu.class, idLieu);
            %>
            <form action="../UpdateServlet" method="post" enctype="multipart/form-data">
                <div class="user-details">
                    <input name="idLieu" type="hidden"
                           value="<%= lieu.getIdLieu()%>"/>
                    <div class="input-box">
                        <span class="details">ID admin</span>
                        <select name="idAdmin">                          
                            <option value="<%= admin.getIdAdmin()%>"><%= admin.getIdAdmin()%></option>
                        </select>
                    </div>
                    <div class="input-box">
                        <span class="details">Nom du lieu</span>
                        <input name="nom" type="text" placeholder="nom" required
                               value="<%= lieu.getNom()%>"/>
                    </div>
                    <div class="input-box">
                        <span class="details">Description</span>
                        <textarea name="desc" placeholder="description"><%= lieu.getDescription()%></textarea>
                    </div>
                    <div class="input-box">
                        <span class="details">L'altitude</span>
                        <input name="altitude" type="text" placeholder="l'altitude" value="<%= lieu.getAltitude()%>"/>
                    </div>
                    <div class="input-box">
                        <span class="details">Longitude</span>
                        <input name="longitude" type="text" placeholder="longitude" value="<%= lieu.getLongitude()%>"/>
                    </div>
                    <div class="input-box">
                        <span class="details">Image</span>
                        <input name="img" type="file" required/>
                    </div>
                </div>
                <div class="submit">
                    <input type="submit" value="Modifier" />
                </div>
                <div class="reset">
                    <input type="reset" value="Annuler" />
                </div>
            </form>
        </div>
    </body>
</html>
