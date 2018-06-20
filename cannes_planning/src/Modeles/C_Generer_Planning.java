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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author p1623107
 */
public class C_Generer_Planning {
    
    public CreneauxDAO crenDAO = new CreneauxDAO();
    public FilmDAO filmDAO = new FilmDAO();
    public SalleDAO salleDAO = new SalleDAO();
    public TypeDAO typeDAO = new TypeDAO();
    
    /**
     * Ajoute le film à un ou plusieurs créneaux disponibles en respectant
     * les conditions
     * @param idFilm 
     */
    public void ajoutFilmPlanning (int idFilm) throws SQLException{
	int idSalle = -1;
        int idType = -1;
        idType = filmDAO.getType(idFilm);
        int nb_proj = -1;
        nb_proj = filmDAO.getnbProj(idFilm);
        int nouvCren = -1;
        
        
        if(idType==0) { //LM
            idSalle = 0;

            int duree = 0;
            duree = Integer.parseInt(filmDAO.getDuree(idFilm));
            
            if(duree>=150){
                while (nb_proj<=3){
                    nouvCren = crenDAO.getIdCrenDispo(idSalle).get(0);
                    crenDAO.addFilm(nouvCren, idFilm);
                    filmDAO.incrNb_proj(idFilm);
                    nb_proj++;
              }
            }else{
                while (nb_proj<=2){
                    nouvCren = crenDAO.getIdCrenDispo(idSalle).get(0);
                    crenDAO.addFilm(nouvCren, idFilm);
                    filmDAO.incrNb_proj(idFilm);
                    nb_proj++;						 
                }
            }
            //System.out.println(nouvCren+" ID CREN");
            //ddSeancedemain(idSalle, nouvCren);
        }
        
        if(idType == 1) { //HC
            idSalle = 0; //choix peu pertinent, mais manque de temps pour y réfléchir
            nouvCren = crenDAO.getIdCrenDispo(idSalle).get(0);
            crenDAO.addFilm(nouvCren, idFilm);
            filmDAO.incrNb_proj(idFilm);
        }
        
        if(idType == 2) { //UCR
            idSalle = 0;
            nouvCren = crenDAO.getIdCrenDispo(idSalle).get(0);
            crenDAO.addFilm(nouvCren, idFilm);
            filmDAO.incrNb_proj(idFilm);
            nb_proj++;
            //addSeancedemain(idSalle, nouvCren);
        }
        
        if(idType == 3) { //CM
            idSalle = 2;
            nouvCren = crenDAO.getIdCrenDispo(idSalle).get(0);
            crenDAO.addFilm(nouvCren, idFilm);
            filmDAO.incrNb_proj(idFilm);
        }
    }
        
    public void addSeancedemain(int idSalle,int idCren) throws SQLException{
        java.util.Date date_cren = crenDAO.getDate(idCren);
        System.out.println(date_cren);
        Calendar cal_cren, cal_demain;
        cal_cren = GregorianCalendar.getInstance();
        cal_cren.setTime(date_cren);
        cal_demain = GregorianCalendar.getInstance();
        cal_demain.setTime(date_cren);
        cal_demain.add(Calendar.DAY_OF_YEAR, 1);
        date_cren = cal_demain.getTime();
        System.out.println(date_cren);
        if (idSalle == 0){ 
            crenDAO.getIdcrenDate(date_cren,3);
        }else if(idSalle == 1){
            crenDAO.getIdcrenDate(date_cren,4);
        }
    }
    
}
