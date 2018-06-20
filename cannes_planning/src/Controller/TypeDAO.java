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
public class TypeDAO {
    public int getidType(String nomType) throws SQLException{
        String req = "SELECT \"idType\" FROM \"Type\" WHERE NOMTYPE = "+nomType;
        ResultSet res1 = BD_co.main(req);
        int id = 0;
        while (res1.next()){
            id = res1.getInt(1);
        }
        BD_co.BD_close();
        return id;
    }

    public TypeDAO() {
    }
    
    public String getnomType(int idType) throws SQLException{
        String req = "SELECT NOMTYPE FROM \"Type\" WHERE \"idType\" = "+idType;
        ResultSet res1 = BD_co.main(req);
        String nom = "";
        while (res1.next()){
            nom = res1.getString(1);
        }
        BD_co.BD_close();
        return nom;
    }
}
