<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.myproj.entities.Lieu"%>
<%@page import="com.mycompany.myproj.dao.LieuDao"%>
<%@page import="com.mycompany.myproj.helper.FactoryProvider"%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="components/common_css_js.jsp" %>
        <meta charset="UTF-8">
        <title>Page Home</title>
        <link href="https://fonts.googleapis.com/css?family=Material+Icons|Material+Icons+Outlined|Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp" rel="stylesheet">
        <link rel="stylesheet" href="css/style_test_1.css">
        <style>
            .admin-btn:hover {
                background: linear-gradient(-135deg,#71b7e6,#9b59b6);
            }
        </style>
    </head>
    <body>
        <header>
            <div class="left-section">
                <i class="material-icons place">place</i>
                <h1><a href="index.jsp" style="text-decoration: none;">Localisation</a></h1>
            </div>
            <div class="mid-section">
                <form action="search_1.jsp" method="get">
                    <input name="name" type="text" placeholder="Rechercher..." />
                    <button><i class="material-icons">search</i></button>
                </form>
            </div>
            <div class="right-section">
                <a class="admin-btn" href="login.jsp" 
                   style="
                   text-decoration: none;
                   display: inline-block;
                   padding: 10px 10px;
                   border-radius: 7px;
                   margin-right: 32px;
                   margin-bottom: 4px;
                   cursor: pointer;
                   border: none;
                   background: linear-gradient(135deg,#71b7e6,#9b59b6);
                   color: #f7f6f9;
                   font-weight: bold;
                   font-size: 12px;
                   ">
                    Allez vers page admin</a>
            </div>
        </header>

        <main style="background: linear-gradient(135deg,#71b7e6,#9b59b6);">
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

                    <div class="lieu" style="background: rgba(255,255,255);">
                        <div class="name">
                            <h3 style="color: #1b2741;"><%= l.getNom()%></h3>
                        </div>
                        <div class="image">
                            <img src="imgs/<%= l.getImage()%>" alt="hopital" />
                        </div>
                        <p style="color: #1b2741;">
                            <%= l.getDescription()%>
                        </p>
                        <div class="buttons">
                            <a href="ShowPlace?idLieu=<%= l.getIdLieu()%>" style="text-align: center; background: linear-gradient(135deg,#71b7e6,#9b59b6);">Localisation</a>                            
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