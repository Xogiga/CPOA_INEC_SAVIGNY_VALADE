/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author p1623107
 */
public class JuresDAO {
    public ArrayList getInfoJures(int idJures) throws SQLException{
        String req = "Select \"nomJures\",\"metier\",\"origine\" IDFILM FROM \"Jures\" WHERE \"idJures\"="+idJures;
        ResultSet res1 = BD_co.main(req);
        ArrayList<String> restab = new ArrayList();
        while (res1.next()){
            String nomJ = res1.getString(1);
            String metier = res1.getString(2);
            String origine = res1.getString(3);            
            restab.add(nomJ);
            restab.add(metier);
            restab.add(origine);
        }
        BD_co.BD_close();
        return restab;
    }

    public JuresDAO() {
    }
    
    public boolean getPresident(int idJures) throws SQLException{
        String req = "Select \"president\" FROM \"Jures\" WHERE \"idjures\"="+idJures;
        ResultSet res1 = BD_co.main(req);
        int president = 0;
        while(res1.next()){
            president =res1.getInt(1);
        }
        BD_co.BD_close();
        if (president==0){
            return false;
        }else{
            return true;
        }
    }
    
    public int getNbproj(int idJures) throws SQLException{
        String req = "Select \"nb_proj_jour\" FROM \"Jures\" WHERE \"idjures\"="+idJures;
        ResultSet res1 = BD_co.main(req);
        int nbproj = 0;
        while (res1.next()){
            nbproj = res1.getInt(1);
        }
        BD_co.BD_close();
        return nbproj;
    }
    
    public int getType (int idJures) throws SQLException{
        String req = "Select idtype FROM \"Jures\" WHERE \"idjures\"="+idJures;
        ResultSet res1 = BD_co.main(req);
        int type = 0;
        while(res1.next()){
            type = res1.getInt(1);
        }
        BD_co.BD_close();
        return type;
    }
}
