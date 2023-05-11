package com.mycompany.myproj.servlets;

import com.mycompany.myproj.dao.AdminDao;
import com.mycompany.myproj.entities.Admin;
import com.mycompany.myproj.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginAdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           //Partie code
            //Getting data from inputs:
            String email = request.getParameter("email");
            int idAdmin = Integer.parseInt(request.getParameter("password"));
            
            //Validations:
            
            //Authenticating admin
            AdminDao adminDao = new AdminDao(FactoryProvider.getFactory());
            Admin admin = adminDao.getAdminByNameAndEmail(email, idAdmin);
            //System.out.println(admin);
            HttpSession httpSession = request.getSession();
            if(admin == null) {
                httpSession.setAttribute("message","l'émail ou le mot de passe incorrect !!");
                response.sendRedirect("login.jsp");
                return;
            }else {       
               //Login
               httpSession.setAttribute("current-user", admin);
               response.sendRedirect("admin.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
