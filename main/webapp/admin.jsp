<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.myproj.entities.Admin"%>
<%
    Admin admin = (Admin) session.getAttribute("current-user");

    if (admin == null) {

        session.setAttribute("message", "Tu es n'est pas connecter !!");
        response.sendRedirect("login.jsp");
        return;
    }


%>


<%@page import="java.util.List"%>
<%@page import="com.mycompany.myproj.entities.Lieu"%>
<%@page import="com.mycompany.myproj.dao.LieuDao"%>
<%@page import="com.mycompany.myproj.helper.FactoryProvider"%>


<!DOCTYPE html>
<html>
    <head>
        <%@include file="components/common_css_js.jsp" %>
        <meta charset="UTF-8">
        <title>Page admin</title>
        
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link rel="stylesheet" href="css/style_test_1.css">

    </head>
    <body>
        <header>
            <div class="left-section">
                <i class="material-icons place">place</i>
                <h1><a href="admin.jsp" style="text-decoration: none;">Localisation</a></h1>
            </div>
            <div class="mid-section">
                <form action="search.jsp" method="get">
                    <input name="name" type="text" placeholder="Rechercher..." />
                    <button><i class="material-icons">search</i></button>
                </form>
            </div>
            <div class="right-section">
                <a href="components/ajouter.jsp" title="Ajouter"><i class="material-icons add">add</i></a>
                <a href="LogoutServlet" title="Déconnecter"><i class="material-icons add">logout</i></a>
            </div>
        </header>

        <main style="background: linear-gradient(135deg,#71b7e6,#9b59b6);">
            <%  
                String messages1 = (String) session.getAttribute("messages");
                if (messages1 != null) {
                    session.removeAttribute("messages");
                }
            %>
            <%@include file="components/messages.jsp" %>
            
            <div class="container">
                <div class="lieux" style="height: 400px;">
                    <%  LieuDao dao = new LieuDao(FactoryProvider.getFactory());
                        List<Lieu> list = dao.getAllPlaces();
                    %>
                    <!-- show places -->

                    <!-- traversing places -->
                    <%
                        for (Lieu l : list) {
                    %>

                    <div class="lieu" style="background: rgb(255,255,255)">
                        <div class="name">
                            <h3><%= l.getNom()%></h3>
                        </div>
                        <div class="image">
                            <img src="imgs/<%= l.getImage()%>" alt="hopital" />
                        </div>
                        <p>
                            <%= l.getDescription()%>
                        </p>
                        <div class="buttons">
                            <a href="ShowPlace?idLieu=<%= l.getIdLieu()%>" style="background: linear-gradient(135deg,#71b7e6,#9b59b6);">Localisation</a>
                            <a href="components/modifier.jsp?idLieu=<%= l.getIdLieu()%>" style="background: linear-gradient(135deg,#71b7e6,#9b59b6);">Modifier</a>
                            <a href="DeleteServlet?idLieu=<%= l.getIdLieu()%>" style="background: linear-gradient(135deg,#71b7e6,#9b59b6);">Supprimer</a>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>

            <!-- ************************************************************************ -->


        </main>        

    </body>
</html>