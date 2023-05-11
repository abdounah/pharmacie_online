package com.mycompany.myproj.dao;

import com.mycompany.myproj.entities.Lieu;
import java.util.List;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class LieuDao {

    private final SessionFactory factory;

    public LieuDao(SessionFactory factory) {
        this.factory = factory;
    }

    //save 
    public boolean saveLieu(Lieu lieu) {
        boolean f = false;
        try {
            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();

            session.save(lieu);

            tx.commit();
            session.close();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
            f = false;
        }
        return f;
    }

    //update
    public void updateLieu(Lieu lieu) {
        Session session = factory.getCurrentSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.update(lieu);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //delete 
    public boolean deleteLieu(int id) {
        boolean f = false;
        try {
            Session session = this.factory.openSession();
            Transaction tx = session.beginTransaction();
            
            Lieu lieu = session.get(Lieu.class, id);
            
            if(lieu != null) {
                session.delete(lieu);
                f = true;
            }
            tx.commit();
            session.close();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
            f = false;
        }
        return f;
    }
    
    //get place
    public Lieu getLieu(int id) {
        Lieu lieu = null;
        Transaction tx = null;
        
        try {
            Session session = this.factory.openSession();
            tx = session.beginTransaction();
            
            lieu = session.get(Lieu.class, id);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return lieu;
    }
    
    //get place by name
    public List<Lieu> searchByName(String nom) {
        Session session = this.factory.openSession();
        String hql = "from Lieu where nom like :n";
        Query<Lieu> query = session.createQuery(hql, Lieu.class);
        query.setParameter("n", "%" + nom + "%");
        List<Lieu> lieux = query.getResultList();
        session.close();
        return lieux;
    }
    
    //get all places
    public List<Lieu> getAllPlaces() {
        Session s = this.factory.openSession();
        Query query = s.createQuery("from Lieu");
        List<Lieu> list = query.list();

        return list;
    }

}
