
package com.mycompany.myproj.entities;


import java.nio.charset.StandardCharsets;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "lieu")
public class Lieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLieu;
    private String nom;
    @Column(length = 3000)
    private String description;
    @Column(name = "altitude")
    private double altitude;
    @Column(name = "longitude")
    private double longitude;
    private String image;
    @ManyToOne
    private Admin admin;
    
    public Lieu() {
    }

    public Lieu(String nom, String description, double altitude, double longitude, String image,Admin admin) {
        this.nom = nom;
        this.description = description;
        this.altitude = altitude;
        this.longitude = longitude;
        this.image = image;
        this.admin = admin;
    }

    public Lieu(int idLieu, String nom, String description, double altitude, double longitude, String image) {
        this.idLieu = idLieu;
        this.nom = nom;
        this.description = description;
        this.altitude = altitude;
        this.longitude = longitude;
        this.image = image;
    }

    public int getIdLieu() {
        return idLieu;
    }

    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    } 

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Lieu{" + "idLieu=" + idLieu + ", nom=" + nom + ", description=" + description + ", altitude=" + altitude + ", longitude=" + longitude + ", image=" + image + ", admin=" + admin + '}';
    } 
    
}
