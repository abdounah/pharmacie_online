/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.myproj.servlets;

import com.mycompany.myproj.dao.AdminDao;
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
import org.hibernate.Session;
import org.hibernate.Transaction;

@MultipartConfig
public class UpdateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int idLieu = Integer.parseInt(request.getParameter("idLieu").trim());
            int adminId = Integer.parseInt(request.getParameter("idAdmin").trim());
            String nom = request.getParameter("nom");
            String description = request.getParameter("desc");
            //Lieu géographique
            double altitude = Double.parseDouble(request.getParameter("altitude"));
            double longitude = Double.parseDouble(request.getParameter("longitude"));
            //image
            Part part = request.getPart("img");
            //get admin by id
            AdminDao adao = new AdminDao(FactoryProvider.getFactory());
            Admin admin = adao.getAdminById(adminId);
            
            Session s = FactoryProvider.getFactory().openSession();
            Transaction tx = s.beginTransaction();
            
            Lieu lieu = s.get(Lieu.class, idLieu);
            
            lieu.setNom(nom);
            lieu.setDescription(description);
            lieu.setAltitude(altitude);
            lieu.setLongitude(longitude);
            lieu.setImage(part.getSubmittedFileName());
            lieu.setAdmin(admin);
            
            tx.commit();
            s.close();
            
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
            out.println("Lieu modifier avec sucess...");
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("messages", "Lieu modifier avec sucess...");
            response.sendRedirect("admin.jsp");
            return;
        }catch(Exception e) {
            e.printStackTrace();
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
