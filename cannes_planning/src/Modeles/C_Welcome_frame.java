/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modeles;

import Controller.FilmDAO;

/**
 *
 * @author p1623107
 */
public class C_Welcome_frame {
    public FilmDAO filmDAO = new FilmDAO();
    
    public void resetPlanning(){
        filmDAO.setNb_proj0();
    }
}
