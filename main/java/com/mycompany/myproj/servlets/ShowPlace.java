package com.mycompany.myproj.servlets;

import com.mycompany.myproj.entities.Lieu;
import com.mycompany.myproj.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ShowPlace extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int idLieu = Integer.parseInt(request.getParameter("idLieu"));
            
            
            
            double altitude = 0.0;
            double longitude = 0.0;
            Transaction tx = null;
            Session s = null;
            Lieu lieu = null;
            try {
                s = FactoryProvider.getFactory().openSession();
                tx = s.beginTransaction();

                // Retrieve the place with the given name from the database
                lieu = (Lieu) s.createQuery("FROM Lieu WHERE idLieu = :idLieu")
                        .setParameter("idLieu", idLieu)
                        .uniqueResult();
                if (lieu != null) {
                    altitude = lieu.getAltitude();
                    longitude = lieu.getLongitude();
                }

                // Commit the transaction
                tx.commit();
            } catch (Exception e) {
                // Roll back the transaction on exception
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                // Close the session and session factory
                if (s != null) {
                    s.close();
                }
            }
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("altitude", lieu.getAltitude());
            httpSession.setAttribute("longitude", lieu.getLongitude());
            httpSession.setAttribute("nom", lieu.getNom());
            
            response.sendRedirect("location.jsp");
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
