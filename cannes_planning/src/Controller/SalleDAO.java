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
 * @author arkan
 */
public class SalleDAO {
    public String getnomSalle(int idSalle) throws SQLException{
        String req = "SELECT \"nomSalle\" FROM \"Salle\" WHERE \"idSalle\"="+idSalle;
        ResultSet res1 = BD_co.main(req);
        res1.next();
        String nom = res1.getString(1);
        BD_co.BD_close();
        System.out.println(nom);
        return nom;
        
    }
    
    public int getidSalle(String nomSalle) throws SQLException{
        String req = "SELECT \"idSalle\" FROM \"Salle\" WHERE \"nomSalle\"='"+nomSalle+"'";
        ResultSet res1 = BD_co.main(req);
        res1.next();
        int id = res1.getInt(1);
        BD_co.BD_close();
        return id;
    }
    
    public int getnbPlace(int idSalle) throws SQLException{
        String req = "SELECT \"nb_place\" FROM \"Salle\" WHERE \"idSalle\"="+idSalle;
        ResultSet res1 = BD_co.main(req);
        int nbPlace = res1.getInt(1);
        BD_co.BD_close();
        return nbPlace;
    }
    
    public int getType (int idSalle) throws SQLException{
        String req = "SELECT idtype FROM \"Salle\" WHERE \"idSalle\"="+idSalle;
        ResultSet res1 = BD_co.main(req);
        int nbPlace = res1.getInt(1);
        BD_co.BD_close();
        return nbPlace;
    }
}
