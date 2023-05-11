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
import org.hibernate.Transaction;

public class DeleteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                int idLieu = Integer.parseInt(request.getParameter("idLieu").trim());

                Session s = FactoryProvider.getFactory().openSession();
                Transaction tx = s.beginTransaction();
                
                Lieu lieu = (Lieu) s.get(Lieu.class, idLieu);
                s.delete(lieu);
                tx.commit();
                s.close();
                
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("messages", "Lieu supprimer avec sucess...");
                response.sendRedirect("admin.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    }

}
