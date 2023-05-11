package com.mycompany.myproj.servlets;

import com.mycompany.myproj.dao.AdminDao;
import com.mycompany.myproj.dao.LieuDao;
import com.mycompany.myproj.entities.Admin;
import com.mycompany.myproj.entities.Lieu;
import com.mycompany.myproj.helper.FactoryProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class LieuxOperationsServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //add lieu
            
            int adminId = Integer.parseInt(request.getParameter("idAdmin"));
            String nom = request.getParameter("nom");
            String description = request.getParameter("desc");
            //Lieu géographique
            double altitude = Double.parseDouble(request.getParameter("altitude"));
            double longitude = Double.parseDouble(request.getParameter("longitude"));
            //image
            Part part = request.getPart("img");
        
            Lieu lieu = new Lieu();
            lieu.setNom(nom);
            lieu.setDescription(description);
            lieu.setImage(part.getSubmittedFileName());
            lieu.setAltitude(altitude);
            lieu.setLongitude(longitude);
            
            //get admin by id
            AdminDao adao = new AdminDao(FactoryProvider.getFactory());
            Admin admin = adao.getAdminById(adminId);
            
            lieu.setAdmin(admin);
            
            //Lieu save...
            LieuDao ldao = new LieuDao(FactoryProvider.getFactory());
            ldao.saveLieu(lieu);
            
            //image upload
            //find the path to upload image
            String path = request.getRealPath("imgs")+File.separator + part.getSubmittedFileName();
            System.out.println(path);
            //uploading code
            try {                            
                FileOutputStream fos = new FileOutputStream(path);
                InputStream is = part.getInputStream();
                //reading data
                byte[] data = new byte[is.available()];
                is.read(data);
                //writing data
                fos.write(data);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println("Lieu enregistrer avec sucess...");
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("messages", "Lieu enregistrer avec sucess...");
            response.sendRedirect("admin.jsp");
            return;
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
