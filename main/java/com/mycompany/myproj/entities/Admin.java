
package com.mycompany.myproj.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10, name="id_admin")
    private int idAdmin;
    @Column(length = 100, name="nom")
    private String nom;
    @Column(length = 100, name="prenom")
    private String prenom;
    @Column(length = 80, name="email")
    private String email;
    @Column(length = 1500, name="adresse")
    private String adresse;
    @Column(length = 12, name="telephone")
    private String telephone;
    @OneToMany(mappedBy = "admin")
    private List<Lieu> lieux = new ArrayList<>();
    
    public Admin() {
    }

    public Admin(String nom, String prenom, String email, String adresse, String telephone,List<Lieu> lieux) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
        this.lieux = lieux;
    }

    public Admin(int idAdmin, String nom, String prenom, String email, String adresse, String telephone) {
        this.idAdmin = idAdmin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Lieu> getLieux() {
        return lieux;
    }

    public void setLieux(List<Lieu> lieux) {
        this.lieux = lieux;
    }
    
    

    @Override
    public String toString() {
        return "Admin{" + "idAdmin=" + idAdmin + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", adresse=" + adresse + ", telephone=" + telephone + '}';
    }
    
    
}
