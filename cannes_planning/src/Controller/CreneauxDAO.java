/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author p1623107
 */
public class CreneauxDAO {
    public int getidCreneaux(int idSalle, int idFilm) throws SQLException{
        String req = "Select \"idCreneaux\" FROM \"Creneaux\" WHERE \"idSalle\"="+idSalle+" AND IDFILM="+idFilm;
        ResultSet res1 = BD_co.main(req);
        int id = res1.getInt(1);
        BD_co.BD_close();
        return id;
    }

    public CreneauxDAO() {
    }
    
    /*
    public ArrayList getidSalleFilm(int idCreneaux) throws SQLException{
        String req = "Select \"idSalle\", IDFILM FROM \"Creneaux\" WHERE \"idCreneaux\"="+idCreneaux;
        ResultSet res1 = BD_co.main(req);
        int idSalle = res1.getInt("\"idSalle\"");
        int idFilm = res1.getInt("IDFILM");
        ArrayList<Integer> restab = new ArrayList();
        restab.add(idSalle);
        restab.add(idFilm);
        return restab;
    }*/
    
    public int getidFilm(int idCreneaux) throws SQLException{
        String req = "Select IDFILM FROM \"Creneaux\" WHERE \"idCreneaux\"="+idCreneaux;
        ResultSet res1 = BD_co.main(req);
        int idFilm = 0;
        while (res1.next()){
            idFilm = res1.getInt(1);
        }
        BD_co.BD_close();
        return idFilm;
    }
    
    public int getidSalle(int idCreneaux) throws SQLException{
        String req = "Select \"idSalle\" FROM \"Creneaux\" WHERE \"idCreneaux\"="+idCreneaux;
        ResultSet res1 = BD_co.main(req);
        res1.next();
        int idSalle = res1.getInt(1);
        BD_co.BD_close();
        System.out.println(idSalle);
        return idSalle;
    }
    public java.util.Date getDate(int idCreneaux) throws SQLException{
        String req = "Select \"Date\" FROM \"Creneaux\" WHERE \"idCreneaux\"="+idCreneaux;
        ResultSet res1 = BD_co.main(req);
        java.sql.Date date;
        java.util.Date jDate = null;
        Calendar cal;
        while (res1.next()){
            date = res1.getDate(1);
            jDate = new Date(date.getTime());
            cal = GregorianCalendar.getInstance();
            cal.setTime(jDate);
        }
        BD_co.BD_close();
        return jDate;
    }
    
    public int getnbDispont() throws SQLException{
        String req1 = "COUNT(*) FROM \"Creneaux\" WHERE \"dispo\"=1";
        ResultSet res1 = BD_co.main(req1);
        int nbDispont = res1.getInt(1);
        BD_co.BD_close();
        return nbDispont;
    }
    
    public boolean getDispo(int idCreneaux) throws SQLException{
        String req1 = "SELECT \"dispo\" FROM \"Creneaux\" WHERE \"idCreneaux\"="+idCreneaux;
        ResultSet res1 = BD_co.main(req1);
        int notbool = res1.getInt(1);
        BD_co.BD_close();
        if(notbool == 0){
            return false;
        }else{
            return true;
        }
    }
   
    
    public ArrayList<Integer> getIDpris() throws SQLException{
        String req = "SELECT \"idCreneaux\" FROM \"Creneaux\" WHERE \"dispo\" = 1";
        ResultSet res1 = BD_co.main(req);
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(res1!=null){
            while (res1.next()){
                res.add(res1.getInt(1));
            }
        }
        BD_co.BD_close();
        return res;
    }
    
    public ArrayList<Integer> getIDprisSalle(int idSalle) throws SQLException{
        String req = "SELECT \"idCreneaux\" FROM \"Creneaux\" WHERE \"dispo\" = 1 AND \"idSalle\"="+idSalle;
        ResultSet res1 = BD_co.main(req);
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(res1!=null){
            while (res1.next()){
                res.add(res1.getInt(1));
                System.out.println(res.get(0));
            }
        }
        BD_co.BD_close();
        return res;
    }
    
    public ArrayList<Integer> getIdCrenDispo(int idSalle) throws SQLException{
        String req = "SELECT \"idCreneaux\" FROM \"Creneaux\" WHERE \"dispo\" = 0 AND \"idSalle\"="+idSalle+" ORDER BY \"Date\" ASC";
        ResultSet res1 = BD_co.main(req);
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(res1!=null){
            while (res1.next()){
                res.add(res1.getInt(1));
            }
        }
        BD_co.BD_close();
        return res;
    }
    
    public void addFilm(int idCren, int idFilm){
        String req = "UPDATE \"Creneaux\" SET \"dispo\"=1, IDFILM="+idFilm+" WHERE \"idCreneaux\"="+idCren;
        BD_co.main(req);
        BD_co.BD_close();
    }
    
    public int getIdcrenDate(java.util.Date jDate, int idSalle) throws SQLException{
        java.sql.Date sDate = new java.sql.Date(jDate.getTime());
        String req = "SELECT \"idCreneaux\" FROM \"Creneaux\" WHERE \"Date\"=TO_DATE("+sDate+",'YY-MM-DD') AND \"idSalle\" ="+idSalle;
        ResultSet res1 = BD_co.main(req);
        res1.next();
        int res = res1.getInt(1);
        BD_co.BD_close();
        return res;
    }
    
    public void supprFilm(java.util.Date date, int idSalle, int idFilm, int nbproj){
        java.sql.Date sDate = new java.sql.Date(date.getTime());
        nbproj --;
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String dateS = df.format(date);
        String req = "UPDATE \"Creneaux\" SET \"dispo\" = 0, IDFILM=null WHERE \"Date\"= TO_DATE('"+dateS+"','YYYY/MM/DD HH24:MI') AND \"idSalle\"="+idSalle;
        String req2 = "UPDATE \"Film\" SET \"nombre_proj\" = "+nbproj+" WHERE \"idFilm\"="+idFilm;
        BD_co.main(req);
        if(nbproj>=0)
            BD_co.main(req2);
        BD_co.BD_close();
    }
    
    public void setCreneaux(java.util.Date jDate, int idCren){
        java.sql.Date sDate = new java.sql.Date(jDate.getTime());
	String req = "UPDATE \"Creneaux\" SET \"Date\"="+sDate+" WHERE \"idCreneaux\"="+idCren;
        BD_co.main(req);
        BD_co.BD_close();
    }
    
    public void viderCren (int idCren){
        String req = "UPDATE \"Creneaux\" SET \"dispo\" = 0, IDFILM=null WHERE \"idCren\"="+idCren;
        BD_co.main(req);
        BD_co.BD_close();
    }
}
