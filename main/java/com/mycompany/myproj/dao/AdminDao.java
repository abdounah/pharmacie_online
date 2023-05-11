package com.mycompany.myproj.dao;

import com.mycompany.myproj.entities.Admin;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AdminDao {
    private final SessionFactory factory;

    public AdminDao(SessionFactory factory) {
        this.factory = factory;
    }
    
    //get admin by name and email
    public Admin getAdminByNameAndEmail(String email,int idAdmin){
        Admin admin = null;
        try{
            String query = "from Admin where email =: e and idAdmin =: i";
            
            try (Session session = this.factory.openSession()) {
                Query q = session.createQuery(query);
                q.setParameter("e", email);
                q.setParameter("i", idAdmin);
                
                admin = (Admin) q.uniqueResult();
            }
            
        }catch(HibernateException e) {
        }
        
        return admin;
    }
    
    // get admin
    public List<Admin> getAdmin() {
        Session s = this.factory.openSession();
        Query query = s.createQuery("from Admin");
        
        List<Admin> list = query.list();
        return list;
    }
    
    //
    public Admin getAdminById(int aId) {
        Admin admin = null;
        try {
            Session session = this.factory.openSession();
            admin = session.get(Admin.class, aId);
            
            session.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
}
