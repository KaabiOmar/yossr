package tn.esprit.tourbuddy.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "table_publication")
public class Publication {
    @PrimaryKey(autoGenerate = true)
    private int pid;

    @ColumnInfo(name ="titrep")
    private String titre;

    @ColumnInfo(name="contenup")
    private String contenu;

    @ColumnInfo(name="image")
    private String imageP;
    

    @Override
    public String toString() {
        return "Publication{" +
                "pid=" + pid +
                ", titre='" + titre + '\'' +
                ", contenu='" + contenu + '\'' +
                ", imageP='" + imageP + '\'' +
                '}';
    }

    public Publication(String titre, String contenu, String imageP) {
        this.titre = titre;
        this.contenu = contenu;
        this.imageP = imageP;
    }

    public Publication(int pid, String titre, String contenu, String imageP) {
        this.pid = pid;
        this.titre = titre;
        this.contenu = contenu;
        this.imageP = imageP;
    }

    public Publication() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImageP() {
        return imageP;
    }

    public void setImageP(String imageP) {
        this.imageP = imageP;
    }



}
