/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modeles;

import Controller.CreneauxDAO;
import Controller.FilmDAO;
import Controller.SalleDAO;
import Controller.TypeDAO;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Tristan
 */
public class C_Suppr_Film {
    
    public CreneauxDAO crenDAO = new CreneauxDAO();
    public FilmDAO filmDAO = new FilmDAO();
    public SalleDAO salleDAO = new SalleDAO();
    public TypeDAO typeDAO = new TypeDAO();
    
    DateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm");
    
    
     public ArrayList<Integer> listeCreneauxPris() throws SQLException{
        ArrayList<Integer> listCren;
        listCren = crenDAO.getIDpris();
        return listCren;
    }
    
    
    public ArrayList<String> RemplTableau(int idCren) throws SQLException{
        ArrayList<String> lTab = new ArrayList<String>();
        int idFilm = crenDAO.getidFilm(idCren);
        lTab.add(0, filmDAO.getTitre(idFilm));
        lTab.add(1, filmDAO.getRealisateur(idFilm));
        lTab.add(2, filmDAO.getDuree(idFilm));
        int idType = filmDAO.getType(idFilm);
        lTab.add(3, typeDAO.getnomType(idType));
        String dateCren = df.format(crenDAO.getDate(idCren));
        lTab.add(4, dateCren);
        int idSalle = crenDAO.getidSalle(idCren);
        lTab.add(5, salleDAO.getnomSalle(idSalle));
        
        return lTab;
    } 
    
    public void supprFilm(String date, String nom,String nomFilm) throws SQLException, ParseException{
        System.out.println(nom);
        int idFilm = filmDAO.getidFilm(nomFilm);
        int idS = salleDAO.getidSalle(nom);
        DateFormat df = new SimpleDateFormat("YYYY/MM/dd HH:mm");
        java.util.Date jDate = df.parse(date);
        int nbproj = filmDAO.getnbProj(idFilm);
        System.out.println("ICI");
        crenDAO.supprFilm(jDate,idS, idFilm, nbproj);
    }
}
