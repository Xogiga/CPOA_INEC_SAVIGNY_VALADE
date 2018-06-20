/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author p1623107
 */
public class FilmDAO {
    public String getTitre(int idFilm) throws SQLException{
        String req = "Select \"Titre\" FROM \"Film\" WHERE \"idFilm\"="+idFilm;
        ResultSet res1 = BD_co.main(req);
        String titre = null;
        while (res1.next()){
            titre = res1.getString(1);
        }
        BD_co.BD_close();
        return titre;
    }
    
    public int getidFilm(String nom) throws SQLException{
        String req = "SELECT \"idFilm\" FROM \"Film\" WHERE \"Titre\"='"+nom+"'";
        ResultSet res1 = BD_co.main(req);
        res1.next();
        int id = res1.getInt(1);
        BD_co.BD_close();
        return id;
    }
    
    public String getRealisateur(int idFilm) throws SQLException{
        String req = "Select \"Realisateur\" FROM \"Film\" WHERE \"idFilm\"="+idFilm;
        ResultSet res1 = BD_co.main(req);
        String realisateur = "";
        while (res1.next()){
            realisateur = res1.getString(1);
        }
        BD_co.BD_close();
        return realisateur;
    }

    public FilmDAO() {
    }
    
    public String getDuree(int idFilm) throws SQLException{
        String req = "Select \"Duree\" FROM \"Film\" WHERE \"idFilm\"="+idFilm;
        ResultSet res1 = BD_co.main(req);
        String duree = "";
        while(res1.next()){
            duree = res1.getString(1);
        }
        BD_co.BD_close();
        return duree;
    }
    
    public int getnbProj(int idFilm) throws SQLException{
        String req = "Select \"nombre_proj\" FROM \"Film\" WHERE \"idFilm\"="+idFilm;
        ResultSet res1 = BD_co.main(req);
        res1.next();
        int nbProj = res1.getInt(1);
        BD_co.BD_close();
        return nbProj;
    }
    
    public boolean addProj(int nbproj) throws SQLException{
            String req = "INSERT INTO \"Film\"(\"nombre_proj\") VALUES ("+nbproj+")";
            BD_co.main(req);
            BD_co.BD_close();
            return true;
    }

    public int getType(int idFilm) throws SQLException{
        String req = "Select IDTYPE FROM \"Film\" WHERE \"idFilm\"="+idFilm;
        ResultSet res1 = BD_co.main(req);
        int id = 0;
        while (res1.next()){
            id = res1.getInt(1);
        }
        BD_co.BD_close();
        return id;
    }
    
    public void incrNb_proj(int idFilm){
        String req = "UPDATE \"Film\" SET \"nombre_proj\"=\"nombre_proj\"+1 WHERE \"idFilm\" = "+idFilm;
        BD_co.main(req);
        BD_co.BD_close();
    }
    
    public void setNb_proj0(){
        String req = "UPDATE \"Film\" SET \"nombre_proj\"= 0";
        String req1 = "UPDATE \"Creneaux\" SET \"dispo\"=0, IDFILM=null";
        BD_co.main(req);
        BD_co.main(req1);
        BD_co.BD_close();
    }
}
